package per.cz.util.binary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;

public final class Binary
{
	/**
	 * ���� �ַ� inputChar �Ķ����Ʊ��롣 ע��ֻ�� ���unicode��
	 * 
	 * @param inputChar
	 *            Ҫ������ַ�
	 * @return inputChar��16λ �Ķ����Ƶı���,�����˳���Ǵӵ� λ ����λ��
	 */
	public static byte[] getBinaryFromChar(final char inputChar)
	{
		byte temp[] = new byte[16];
		return getBinary(temp, inputChar);
	}

	/**
	 * ��ȡָ������ģ�byte ,short,char ,int,long���Ķ����Ƶ����顣
	 * 
	 * @param o
	 *            ֻ����byte ,short,char ,int,long����
	 * @return byte ���� [10100101]
	 * @throws NullPointerException
	 *             ���o Ϊnullʱ
	 * @throws IllegalAccessException
	 *             ���ǹ涨������ʱ
	 */
	public static byte[] getBinary(final Object o)
	throws IllegalAccessException
	{
		if (o == null)
		{
			throw new NullPointerException("what you input is null");
		}
		byte temp[] = null;
		if (o instanceof Character)
		{
			temp = new byte[16];
			return getBinary(temp, ((Character) o).charValue());
		}
		else if (o instanceof Byte)
		{
			temp = new byte[8];
			return getBinary(temp, ((Byte) o).byteValue());
		}
		else if (o instanceof Short)
		{
			temp = new byte[16];
			return getBinary(temp, ((Short) o).shortValue());
		}
		else if (o instanceof Integer)
		{
			temp = new byte[32];
			return getBinary(temp, ((Integer) o).intValue());
		}
		else if (o instanceof Long)
		{
			temp = new byte[64];
			return getBinary(temp, ((Long) o).longValue());
		}
		else
		{
			throw new IllegalAccessException("what you input is not aviliable");
		}
	}

	private static byte[] getBinary(byte[] temp, long input)
	{
		for (int i = 0; i < temp.length; i++)
		{
			long bin = (long) Math.scalb(1, i);// 1.0 �� 2^i
			if ((bin & input) != 0)
			{
				temp[i] = 1;
			}
			else
			{
				temp[i] = 0;
			}
		}
		return temp;
	}

	public static byte[] getBinaryFromByte(final byte inputByte)
	{
		byte temp[] = new byte[8];
		return getBinary(temp, inputByte);
	}

	/**
	 * �������������ת����char ���͡�
	 * 
	 * @param bt
	 *            Ҫת���Ķ����Ƶ����顣
	 * @return char
	 */
	public static char getCharFromBinary(final byte[] bt)
	{
		if (bt == null || bt.length != 16)
		{
			throw new NullPointerException(
			"what you input is null or what you input's length is not 16");
		}
		char temp = 0;
		for (int i = 0; i < 16; i++)
		{
			temp |= bt[i] << i;
		}
		return temp;
	}

	/**
	 * �������������ת����byte ���͡�
	 * 
	 * @param bt
	 *            Ҫת���Ķ����Ƶ����顣
	 * @return byte
	 */
	public static byte getByteFromBinary(final byte[] bt)
	{
		if (bt == null || bt.length != 8)
		{
			throw new NullPointerException(
			"what you input is null or what you input's length is not illegal");
		}
		byte res = 0;
		for (int j = 0; j < 8; j++)
		{
			res |= bt[j] << j;
		}
		return res;
	}

	/**
	 * �������������ת����byte ���͡�
	 * 
	 * @param bt
	 *            Ҫת���Ķ����Ƶ����顣
	 * @return byte
	 */
	public static long getLongFromBinary(final byte[] bt)
	{
		long res = 0;
		for (int j = 0; j < bt.length; j++)
		{
			res |= bt[j] << j;
		}
		return res;
	}
	public static String getUnicodeString(String s,String split,boolean ignoreFF)
	{
		char[] charArray = s.toCharArray();
		StringBuffer sb=new StringBuffer();
		for(char c:charArray)
		{
			if(c<'\u00ff'&&ignoreFF)
			{
				sb.append(split+c);
			}
			else
			{
				sb.append(split+Integer.toString(c, 16));
			}
		}
		return sb.toString();
	}
	/**
	 * ������ĸת��
	 * @param s
	 * @param split
	 * @return
	 */
	public static String getUnicodeStringIgnoreFF(String s)
	{
		char[] charArray = s.toCharArray();
		StringBuffer sb=new StringBuffer();
		for(char c:charArray)
		{
			if(c<'\u00ff')
			{
				sb.append(c);
			}
			else
			{
				sb.append("\\u"+Integer.toString(c, 16));
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) throws NullPointerException,
	IllegalAccessException, Exception
	{
		//System.out.println(getUnicodeString("�Ƽ���l","\\u",true));
		//System.out.println(getUnicodeStringIgnoreFF("�Ƽ���l"));
		//System.out.println("\u9ec4\u7ee7\u9f99l");
		//System.out.println('\u00ff');
		//System.out.println("\u9ec4\u7ee7\u9f99");
		//System.out.println(getUnicodeString("huangjilong","_",false));
		BufferedReader inputStream = new BufferedReader(new FileReader("d:\\1.txt"));
		String line = inputStream.readLine();
		//System.out.println(line.replaceAll("\\", ""));
		//System.out.println("\u0068\u0075\u0061\u006e\u0067\u006a\u0069\u006c\u006f\u006e\u0067");
		int a = 3;
		//System.out.println(a << 1);
		SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("D");
		// ��ȡ��ǰ������һ���е�����

		String dateFo = dateFormat.format(new java.util.Date());
		//System.out.println(dateFo);
		//System.out.println(dateFormat.parse("100"));
		// //System.out.println(Arrays.toString(getBinary(2<<1&0x1EF7BDE)));
		// //System.out.println(Arrays.toString(getBinary(0x1F7BDEF)));
		// //System.out.println(0x1ffffff);
		// //System.out.println(2<<24);
		// //System.out.println(getLongFromBinary(new byte[] {1, 1, 1, 1, 0, 1, 1,
		// 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0,
		// 0, 0}));
	}

}
