package per.cz.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 配置文件包含的项<br>
 * uploaderBasePath=d\:/temp <br>
 * createable4BasePath=true<br>
 * maxFilesNum=5<br>
 * minFilesNum=1 <br>
 * sizeThreshold=-1 <br>
 * repository=d:/repository <br>
 * maxFileSize=-1<br>
 * minFileSize=1 <br>
 * encoding=UTF-8 <br>
 * acceptFileTypes=png,jpeg,jpg,gif,*<br>
 * usingUserPath=false<br>
 */
public class UploadProperty
{
	private static Properties prop;
	private static String path = Config.PROP_PATH;
	private static long lastModified4Prop;

	static
	{
		init();
	}

	private static void init()
	{
		prop = new Properties();
		try
		{
			// prop.load(new BufferedReader(new FileReader(propPath)));
			prop.load(new BufferedInputStream(new FileInputStream(path)));
		}
		catch (Exception e)
		{
			System.out.println("read upload properties:" + path + "failed!");
			e.printStackTrace();
		}
	}

	private static void check()
	{
		if (prop == null || new File(path).lastModified() != lastModified4Prop)
		{
			init();
			lastModified4Prop = new File(path).lastModified();
		}
	}

	public static void setPropFilePath(String _path)
	{
		path = _path;
		init();
	}

	/**
	 * 获取上传组件接受的文件类型
	 * 
	 * @param deft
	 *            String 类型 "jpg,mp3,*"
	 * @return
	 */
	public static String[] getAcceptFileTypes(String deft)
	{
		check();
		try
		{
			return prop.getProperty("acceptFileTypes", deft).trim().split(",");
		}
		catch (Exception e)
		{
			System.out.println("get acceptFileTypes failed");
			e.printStackTrace();
			return null;
		}
	}

	public static Boolean getCreateable4BasePath(Boolean deft)
	{
		check();
		try
		{
			return Boolean.valueOf(prop.getProperty("createable4BasePath",
					deft == null ? null : deft.toString()).trim());
		}
		catch (Exception e)
		{
			System.out.println(" get createable4BasePath failed");
			e.printStackTrace();
			return null;
		}
	}

	public static String getEncoding(String deft)
	{
		check();
		try
		{
			return prop.getProperty("encoding", deft).trim();
		}
		catch (Exception e)
		{
			System.out.println("encoding error!");
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getMaxFilesNum(Integer deft)
	{
		check();
		try
		{
			return (int) getLongFromString(prop.getProperty("maxFilesNum",
					deft == null ? null : deft.toString()));
		}
		catch (Exception e)
		{
			System.out.println("get maxFilesNum failed");
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getMinFilesNum(Integer deft)
	{
		check();
		try
		{
			return (int) getLongFromString(prop.getProperty("minFilesNum",
					deft == null ? null : deft.toString()));
		}
		catch (Exception e)
		{
			System.out.println("get minFileNums failed");
			e.printStackTrace();
			return null;
		}
	}

	public static Long getMaxFileSize(Long deft)
	{
		check();
		try
		{
			return getLongFromString(prop.getProperty("maxFileSize",
					deft == null ? null : deft.toString()));
		}
		catch (Exception e)
		{
			System.out.println("get maxFileSize failed");
			e.printStackTrace();
			e.printStackTrace();
			return null;
		}
	}

	public static Long getMinFileSize(Long deft)
	{
		check();
		try
		{
			return getLongFromString(prop.getProperty("minFileSize",
					deft == null ? null : deft.toString()).trim());
		}
		catch (Exception e)
		{
			System.out.println("get minFileSize failed");
			e.printStackTrace();
			return null;
		}
	}

	public static String getRepository(String deft)
	{
		check();
		try
		{
			return prop.getProperty("repository",
					deft == null ? null : deft.toString()).trim();
		}
		catch (Exception e)
		{
			System.out.println("get repository failed");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取缓存区的大小
	 * 
	 * @param deft
	 *            String 类型 "1024*10*11"
	 * @return
	 */
	public static Integer getSizeThreshold(String deft)
	{
		check();
		try
		{
			return (int) getLongFromString(prop.getProperty("sizeThreshold",
					deft.toString()).trim());

		}
		catch (Exception e)
		{
			System.out.println("get sizeThreshold failed");
			e.printStackTrace();
			return null;
		}
	}

	public static Boolean isUsingUserPath(Boolean deft)
	{
		check();
		try
		{
			return Boolean.valueOf(prop.getProperty("usingUserPath",
					deft == null ? null : deft.toString()).trim());
		}
		catch (Exception e)
		{
			System.out.println("get usingUserPath failed");
			e.printStackTrace();
			return null;
		}
	}

	public static String getUploaderBasePath(String deft)
	{
		check();
		try
		{
			return prop.getProperty("uploaderBasePath", deft).trim();
		}
		catch (Exception e)
		{
			System.out.println("get uploaderBasePath failed");
			e.printStackTrace();
			return null;
		}
	}

	public static String getProp4XML()
	{
		/*
		 uploaderBasePath=d\:/temp <br>
		 createable4BasePath=true<br>
		 maxFilesNum=5<br>
		 minFilesNum=1 <br>
		 sizeThreshold=-1 <br>
		 repository=d:/repository <br>
		 maxFileSize=-1<br>
		 minFileSize=1 <br>
		 encoding=UTF-8 <br>
		 acceptFileTypes=png,jpeg,jpg,gif,*<br>
		 usingUserPath=false<br>*/
		StringBuffer buffer = new StringBuffer();
		buffer.append("<upload>");
		buffer.append("<maxFileSize>");
		buffer.append(getMaxFileSize(-1l));
		buffer.append("</maxFileSize>");
		buffer.append("<minFileSize>");
		buffer.append(getMinFileSize(1l));
		buffer.append("</minFileSize>");
		buffer.append("<maxFilesNum>");
		buffer.append(getMaxFilesNum(-1));
		buffer.append("</maxFilesNum>");
		buffer.append("<minFilesNum>");
		buffer.append(getMinFilesNum(1));
		buffer.append("</minFilesNum>");
		buffer.append("<acceptFileTypes>");
		String [] s=getAcceptFileTypes("*");
		for(int i=0;i<s.length;i++)
		{
			
			buffer.append("*.");
			buffer.append(s[i]);
			buffer.append(";");
		}
		buffer.append("</acceptFileTypes>");
		buffer.append("<encoding>");
		buffer.append(getEncoding("utf-8"));
		buffer.append("</encoding>");
		buffer.append("</upload>");
		return buffer.toString();
	}
	private static long getLongFromString(String str) throws Exception
	{
		if (str != null && !"".equals(str))
		{
			if (!str.trim().equals(""))
			{
				String[] nums = str.split("\\*");
				if (nums == null || nums.length == 0)
				{
					try
					{

						return Long.parseLong(str.trim());

					}
					catch (Exception e)
					{
						throw e;
					}
				}
				else
				{
					long res = 1;
					for (int i = 0; i < nums.length; i++)
					{
						if (nums[i].trim().equals(""))
						{
							throw new Exception("格式错误" + str);
						}
						try
						{
							res *= Long.parseLong(nums[i].trim());
						}
						catch (Exception e)
						{
							throw e;
						}
					}
					return res;
				}
			}
		}
		else
		{

		}
		return 0;
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println(getProp4XML());
	}
}
