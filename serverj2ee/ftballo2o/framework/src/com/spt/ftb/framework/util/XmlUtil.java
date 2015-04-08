/**   
 * @Title: XmlUtil.java 
 * @Package com.iot.isp.framework.util 
 * @Description: (用一句话描述该文件做什么) 
 * @author zq 
 * @date 2014年1月22日 下午4:23:41 
 * @version V1R1  
 */
package com.spt.ftb.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.spt.ftb.framework.common.ResourceManager;
import com.spt.ftb.framework.common.log.LogManager;

/**
 * @ClassName: XmlUtil
 * @Description: 读取XML工具类
 * @author zq
 * @date 2014年1月22日 下午4:23:41
 */
public class XmlUtil
{
	private static Element loadXmlFile(String fileName)
	{
		File file = new File(fileName);
		return loadXmlFile(file);
	}

	private static Element loadXmlFile(File file)
	{
		Element root = null;
		if (!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				LogManager.error(e, e.getMessage());
			}
		}
		try
		{
			InputStream in = new FileInputStream(file);
			SAXReader reader = new SAXReader();
			Document document = reader.read(in);
			root = document.getRootElement();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage(), e);
		}
		return root;
	}

	/**
	 * 
	 * @Description: 创建采集器节点
	 * @param @param obj 设定文件
	 * @return void 返回类型
	 * @throws XmlUtil
	 */
	public static void createInstrument(Object obj)
	{
		File file = new File(ResourceManager.getConfPath("instrument.xml"));
		Element root = loadXmlFile(file);
		Element nodeElement = root.element("instrumentInfos");
		// 添加一个对象节点
		String nodeName = obj.getClass().getSimpleName();
		Element objElement = nodeElement.addElement(nodeName);
		HashMap<String, String> maps = ReflectUtil.getObjFieldNameAndValue(obj);
		Iterator<?> iter = maps.entrySet().iterator();
		// 添加文本节点,文本内容
		while (iter.hasNext())
		{
			@SuppressWarnings("unchecked")
			Entry<String, String> entry = (Entry<String, String>) iter.next();
			Element childElement = objElement.addElement(entry.getKey());
			childElement.setText(entry.getValue());
		}
		writeXml(file, root.getDocument());
	}

	/**
	 * 
	 * @Description: 修改节点
	 * @param @param obj
	 * @param @param xpath 设定文件
	 * @return void 返回类型
	 * @throws XmlUtil
	 */
	@SuppressWarnings("unchecked")
	public static void modifyInstrument(Object obj, String xpath)
	{
		File file = new File(ResourceManager.getConfPath("instrument.xml"));
		Element root = loadXmlFile(file);
		List<Node> infos = (List<Node>) root.selectNodes(xpath);
		HashMap<String, String> maps = ReflectUtil.getObjFieldNameAndValue(obj);
		Iterator<?> iter = maps.entrySet().iterator();
		for (Node node : infos)
		{
			while (iter.hasNext())
			{
				Entry<String, String> entry = (Entry<String, String>) iter.next();
				Node cnode = (Node) node.selectObject(entry.getKey());
				if (null != cnode && !"".equals(entry.getValue()))
				{
					cnode.setText(entry.getValue());
				}
			}
		}
		writeXml(file, root.getDocument());
	}

	public static void removeInstrument(String xpath)
	{
		File file = new File(ResourceManager.getConfPath("instrument.xml"));
		Element root = loadXmlFile(file);
		Node node = (Node) root.selectObject(xpath);
		node.getParent().remove(node);
		writeXml(file, root.getDocument());
	}

	public static Object getObject(String xpath)
	{
		File file = new File(ResourceManager.getConfPath("instrument.xml"));
		Element root = loadXmlFile(file);
		Node node = (Node) root.selectObject(xpath);
		return node;
	}

	private static void writeXml(File file, Document document)
	{
		try
		{
			XMLWriter writer = new XMLWriter(new FileWriter(file));
			writer.write(document);
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: 将XML中的值赋值给指定对象
	 * @param @param obj
	 * @param @param filePath 设定文件
	 * @return void 返回类型
	 * @throws XmlUtil
	 */
	public static List<?> loadObj(String classPath,String filePath)
	{
		String className = classPath.substring(classPath.lastIndexOf('.') + 1);
		Element root = loadXmlFile(filePath);
		List<Object> objs = new ArrayList<Object>();
		getObjs(objs, classPath, className, root);
		return objs;
	}

	/**
	 * 
	 * @Description: 获取xml参数类型的map集合
	 * @param @return    设定文件 
	 * @return Map<String,List<Entry<String,String>>> 返回类型 
	 * @throws XmlUtil
	 */
	public static Map<String, List<Entry<String, String>>> getArgMaps(String path)
	{
		Element root = loadXmlFile(path);//ResourceManager.getConfPath("argment.xml")
		Map<String, List<Entry<String, String>>> maps = new ConcurrentHashMap<String, List<Map.Entry<String, String>>>();
		List<Entry<String, String>> codes = null;
		HashMap<String, String> codeMap = null;
		Iterator<?> elements = root.elementIterator();
		Iterator<?> els = null;
		while (elements.hasNext())
		{
			Element el = (Element) elements.next();
			els = el.elementIterator();
			codeMap = new HashMap<String, String>();
			while (els.hasNext())
			{
				Element code = (Element) els.next();
				codeMap.put(code.getName(), code.getText());
			}
			codes = new ArrayList<Entry<String, String>>();
			codes.addAll(ResourceManager.sort4Map(codeMap));
			maps.put(el.attributeValue("argtype"), codes);
		}
		return maps;
	}

	private static List<Object> getObjs(List<Object> objs, String classPath, String className, Element elment)
	{
		Iterator<?> tempElements = elment.elementIterator();
		while (tempElements.hasNext())
		{
			Element el = (Element) tempElements.next();
			if (className.equals(el.getName()))
			{
				Object obj = ReflectUtil.createObj(classPath);
				Iterator<?> classIterator = el.elementIterator();
				while (classIterator.hasNext())
				{
					Element element = (Element) classIterator.next();
					String fieldName = element.getName();
					String value = element.getTextTrim();
					ReflectUtil.reflect(obj, fieldName, value);
				}
				objs.add(obj);
				continue;
			}
			getObjs(objs, classPath, className, el);
		}
		return objs;
	}

	/**
	 * 
	 * @Description: 获取匹配类的个数
	 * @param @param className
	 * @param @param filePath
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws XmlUtil
	 */
	public int getClassNum(String className)
	{
		Element root = loadXmlFile(ResourceManager.getConfPath("instrument.xml"));
		int count = 0;
		@SuppressWarnings("unchecked")
		Iterator<Element> elements = root.elementIterator();
		while (elements.hasNext())
		{
			Element el = (Element) elements.next();
			if (className.equals(el.getName()))
			{
				count++;
			}
		}
		return count;
	}
}
