package com.spt.ftb.framework.util;

/**
 * 
 * @ClassName: NumberConverter 
 * @Description: 数字转发工具类 
 * @author zq
 * @date 2014年1月16日 上午10:44:26
 */
public class NumberConverter
{
	public static String[] HEX = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

	public static String HEX_PRE = "0x";

	public static String HEX_REGEX = "^0[xX]{1}[0-9A-Fa-f]{1,}$";

	public static String toHex(byte b)
	{
		int l = b & 0xF;
		int h = (b & 0xF0) >> 4;
		return HEX_PRE + HEX[h] + HEX[l];
	}

	public static String toHex(byte b, String pre)
	{
		int l = b & 0xF;
		int h = (b & 0xF0) >> 4;
		return pre + HEX[h] + HEX[l];
	}

	public static String toHex(byte[] b)
	{
		if (b.length > 0)
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++)
			{
				sb.append(toHex(b[i]));
				sb.append(",");
			}
			sb.delete(sb.length() - 1, sb.length());
			return sb.toString();
		}
		return "";
	}

	public static String toHex(byte[] b, String pre, String split)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			sb.append(toHex(b[i], pre));
			sb.append(split);
		}
		return sb.toString();
	}

	public static byte[] toBytes(int integer)
	{
		byte[] b = new byte[4];
		for (int i = 3; i >= 0; i--)
		{
			b[i] = ((byte) (integer >> (b.length - i - 1) * 8));
		}
		return b;
	}

	public static byte hexStringToByte(String hex)
	{
		if (!hex.matches(HEX_REGEX))
		{
			throw new RuntimeException("输入字符非hex");
		}
		char[] c = hex.substring(2).toCharArray();
		int count = 0;
		for (int i = c.length - 1; i >= 0; i--)
		{
			int add = 0;
			String str = String.valueOf(c[i]);
			try
			{
				add = Integer.parseInt(str);
			}
			catch (Exception e)
			{
				if (str.equalsIgnoreCase("a"))
					add = 10;
				if (str.equalsIgnoreCase("b"))
					add = 11;
				if (str.equalsIgnoreCase("c"))
					add = 12;
				if (str.equalsIgnoreCase("d"))
					add = 13;
				if (str.equalsIgnoreCase("e"))
					add = 14;
				if (str.equalsIgnoreCase("f"))
					add = 15;
			}
			count |= add << (c.length - 1 - i) * 4;
		}
		return (byte) count;
	}

	public static int byteToInt(byte[] b)
	{
		byte[] a = new byte[4];
		if (b.length >= 4)
		{
			System.arraycopy(b, b.length - 4, a, 0, 4);
		}
		else
		{
			System.arraycopy(b, 0, a, 4 - b.length, b.length);
			for (int i = 0; i < 4 - b.length; i++)
			{
				a[i] = 0;
			}
		}
		int a1 = b[0] << 24 & 0xFF000000;
		int a2 = b[1] << 16 & 0xFF0000;
		int a3 = b[2] << 8 & 0xFF00;
		int a4 = b[3] & 0xFF;
		return a1 | a2 | a3 | a4;
	}

	public static byte[] reversed(byte[] b)
	{
		byte[] result = new byte[b.length];
		for (int i = 0; i < result.length; i++)
		{
			result[i] = b[(result.length - i - 1)];
		}
		return result;
	}

	public static byte[] stringToBytes(String str)
	{
		String s = str;
		while ((s.length() < 8) || (s.length() % 2 != 0))
		{
			s = "0" + s;
		}
		int count = s.length() / 2;
		byte[] result = new byte[count];
		for (int i = 0; i < count; i++)
		{
			result[i] = hexStringToByte(HEX_PRE + s.substring(i * 2, i * 2 + 2));
		}
		return result;
	}

	public static String byteToString(byte[] is)
	{
		StringBuffer sb = new StringBuffer();
		byte[] b = is;
		for (int i = 0; i < b.length; i++)
		{
			sb.append((char) b[i]);
		}
		return sb.toString();
	}

	public static short[] convertToShorts(byte[] data)
	{
		short[] sdata = new short[data.length / 2];
		for (int i = 0; i < sdata.length; i++)
			sdata[i] = toShort(data[i * 2], data[i * 2 + 1]);
		return sdata;
	}

	public static short toShort(byte b1, byte b2)
	{
		return (short) ((b1 << 8) | (b2 & 0xff));
	}

	public static void main(String[] args)
	{
		byte[] b = { 0, 22, -63, -76 };
		System.out.println(toHex(b, "", ""));
		System.out.println(byteToInt(b));
		System.out.println(toHex(stringToBytes("5")));
	}
}