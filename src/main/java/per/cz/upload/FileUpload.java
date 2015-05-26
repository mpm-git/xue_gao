package per.cz.upload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * 
 * 核心的方法是uploading(HttpServletRequest)<br>
 * 
 * 只有两个方法是可以用来设置参数的<br>
 * setiRenameFile(IRenameFile iRenameFile); 	//设置文件重命名方案。<br>
 * setUploaderBasePath(String uploaderBasePath);//设置上传路径（此方法可能无效，在配置文件中允许上传者设置上传的路径）
 * 
 * 对commom-uploader包的简单封装，提供 需要设置的参数列表如下：
 * <table>
 *  1 uploaderBasePath 		上传得文件的存放路径						"/basepath/"<br>
 * 2  createable4BasePath 	如果存放的路径不存在是否创建 				true<br>
 * 3  maxFilesNum 		允许上传得最大文件数目 					1<br>
 * 4  minFilesNum 		许上传的最小文件数目						1 <br>
 * 5  sizeThreshold 		缓存的大小 								10KB<br>
 * 6  maxFileSize 			允许上传得最大文件大小 					-1 不限制文件大小 <br>
 * 7  minFileSize 			允许上传的最小文件大小 					1 字节<br>
 * 8  encoding 				个参数的编码方式 							UTF-8<br>
 * 9  acceptFileTypes		允许上传的文件类型<br>
 * 10 repository			缓冲区的位置								/StoreLocation/<br>
 * iFileSameName 			上传得文件和现有文件同名时的处理方案<br>
 * iRenameFile 			上传得文件的命名方案<br><br>
 *  
 *  	runType					记录文件的处理的状态码<br>
 * 									000	未知状态，表示出现问题或是还没有处理。<br>
 * 									010	成功状态<br>
 * 									110	 上传的文件类型不符合上传要求<br>
 * 
 * 									210	文件的数量不匹配状态<br>
 * 									220	上传的目的文件夹没能创建（可能因为参数列表createable4BasePath为false），
 *  										或是createable4BasePath为true但是创建的时失败,这时可能是要创建的文件夹带有系统不认可的关键字。<br>
 *  								230	缓冲文件存放的文件夹创建出现问题。<br>
 *  								240	上传的文件中存在不成功的情况的状态。<br>
 *  								250	 配置文件中，配置信息获取失败状态。<br>
 *  								260	上传的文件大小不符合限制要求<br>
 *  								270	文件上传被取消(或是中断)。<br>
 * </table>
 * @author Huangjilong
 */

public class FileUpload
{
	public static boolean isUsingUserPath() {
		return usingUserPath;
	}

	private final static String PARAM_NAME=Config.PARAM_NAME;

	private static final long serialVersionUID = 670829239023754119L;


	/**
	 * 保存普通form表单域
	 */
	protected Map<String, String> parameters;

	
	/**
	 * 保存上传的文件
	 */
	protected Map<String, File4Item> files;


	/**
	 * 默认的文件上传的基本路径默认为当前路径下的temp文件夹下
	 */
	private static String uploaderSYSBasePath = "/basepath/";
	private  String uploaderSYSBasePath_ ;

	/**
	 * 如果存储文件的路径不存在是否创建。 默认为true（自动创建）
	 */
	private static boolean  createable4BasePath = true;
	private static boolean  usingUserPath = false;
	/**
	 * 最大允许上传的文件的数目。默认为1
	 */
	private static int maxFilesNum = 1;

	/**
	 * 最小允许上传文件的数目。默认为1
	 */
	private static int minFileNum = 1;


	/**
	 * 缓存的大小，默认为10kB The directory in which uploaded files will be stored, if
	 * stored on disk.
	 */

	private static int sizeThreshold = DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;

	private static String repository="/StoreLocation/";


	/**
	 * 允许上传得的最大的文件的大小。默认为-1 表示不限制上传得文件大小。 {@link #maxFileSize}.
	 */

	private static long maxFileSize = -1;
	/**
	 * 允许上传得的最小的文件的大小。默认为1 字节(不允许为空文件)。 取值小于等于允许的上传得文件最大值。
	 */
	private static long minFileSize = 1;
	/**
	 * 字符编码，当读取上传表单的各部分时会用到该encoding。默认为UTF-8
	 */
	private static String encoding = "utf-8";
	private static String[] acceptFileTypes =
	{"*"};
	private String[] acceptFileTypes_;
	public void setAcceptFileTypes(String[] acceptFileTypes) {
		this.acceptFileTypes_ = acceptFileTypes;
	}

	private StringBuffer context=new StringBuffer();

	private static Properties prop;
	private static long lastModified4Prop=0;
 
	
	/**
	 * 无参的构造方法，默认使用与苯类在同一个包中的Properties.properties文件作为参数的来源。
	 */
	public FileUpload()
	{
		//System.out.println("配置前:"+prop);
		try {
			//if(prop==null||new File(this.getClass().getResource("Properties.properties").toURI()).lastModified()!=lastModified4Prop)
			if(prop==null||new File(Config.PROP_PATH).lastModified()!=lastModified4Prop)
			{
				loaderDefaultProperty();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("配置后:"+prop);
	}
	/**
	 * 根据propPath路径中的参数，所对应的文件获取配置
	 * @param propPath
	 */
	public FileUpload(String propPath)
	{
		loaderProperty(propPath);
	}
	private void loaderDefaultProperty()
	{
			Properties temp = new Properties();
			try {
				//InputStream resourceAsStream = this.getClass().getResourceAsStream(new String("Properties.properties"));
				//resourceAsStream.
			//	temp.load(new BufferedReader(new FileReader(Config.PROP_PATH)));
				temp.load(new BufferedInputStream(new FileInputStream(Config.PROP_PATH)));
				lastModified4Prop=new File(Config.PROP_PATH).lastModified();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			prop=temp;
			loaderProperty(prop);

	}
	/**
	 * 获取配置指定路径中的配置信息，用于在本类事例化后再次设置配置信息。
	 * @param propPath
	 */
	public void loaderProperty(String propPath)
	{
		prop=new Properties();
		try {
			
//			prop.load(new BufferedReader(new FileReader(propPath)));
			prop.load(new BufferedInputStream(new FileInputStream(propPath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		loaderProperty(prop);
	}
	
	/**
	 * 直接从Properties对象中获取配置信息，用于在本类事例化后再次设置配置信息。
	 * @param prop
	 */
	public void loaderProperty(Properties prop)
	{
		try{acceptFileTypes=prop.getProperty("acceptFileTypes").trim().split(",");}catch(Exception e){context.append("acceptFileTypes参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		
		try{createable4BasePath=Boolean.valueOf(prop.getProperty("createable4BasePath",String.valueOf(createable4BasePath)).trim());}catch(Exception e){context.append("createable4BasePath："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		try{encoding=prop.getProperty("encoding",encoding).trim();}catch(Exception e){context.append("encoding："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		Integer _maxFilesNum=maxFilesNum;
		Integer _minFilesNum=minFileNum;
		try{_maxFilesNum = (int)getLongFromString(prop.getProperty("maxFilesNum",String.valueOf(maxFilesNum)).trim());}catch(Exception e){context.append("maxFilesNum参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		try{_minFilesNum =(int)getLongFromString(prop.getProperty("minFilesNum",String.valueOf(minFileNum)).trim());}catch(Exception e){context.append("minFileNums参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		setFileNums(_minFilesNum,_maxFilesNum);
		
		long _maxFileSize=maxFileSize;
		long _minFileSize=minFileSize;
		try{_maxFileSize =getLongFromString( prop.getProperty("maxFileSize",String.valueOf(_maxFileSize)).trim());}catch(Exception e){context.append("maxFileSize参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		try{_minFileSize =getLongFromString(prop.getProperty("minFileSize",String.valueOf(_minFileSize)).trim());}catch(Exception e){context.append("minFileSize参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		setFileSize(_minFileSize, _maxFileSize);
		
		try{repository=prop.getProperty("repository",String.valueOf(repository)).trim();}catch(Exception e){context.append("repository参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		try{
			int temp = (int)getLongFromString(prop.getProperty("sizeThreshold",String.valueOf(sizeThreshold)).trim());
			if(temp!=-1)
			{
				sizeThreshold=temp;
			}
		}catch(Exception e){context.append("sizeThreshold参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		try{usingUserPath=Boolean.valueOf(prop.getProperty("usingUserPath",String.valueOf(usingUserPath)));}catch(Exception e){context.append("usingUserPath参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		try{uploaderSYSBasePath=prop.getProperty("uploaderBasePath",String.valueOf(uploaderSYSBasePath)).trim();}catch(Exception e){context.append("uploaderBasePath参数获取失败："+e.getMessage());setRunType(File4Item.PROPLOADFAILED_STATE);}
		uploaderSYSBasePath=(uploaderSYSBasePath+"/").replaceAll("/+", "/");
		
	}
	public StringBuffer getContext() {
		return context;
	}
	public String getRepository()
	{
		return repository;
	}
	public void setiRenameFile(IRenameFile iRenameFile)
	{
		this.iRenameFile = iRenameFile;
	}


	
	/**
	 * 设置上传的文件存放的位置。只有此一个属性是允许设置的，为了灵活的使用存放的目录，达到可以在通过编程实现上传到不同的路径中。<br>
	 * 注意： 当参数文件upload.properties中的usingUserPath（是否允许上传者自己设置上传路径）参数为true时并且上传者使用了参数设置路径时（参数非空），此方法无效。
	 * 而使用上传者的参数中的路径。
	 * @param uploaderBasePath 将要上传到的文件夹
	 */
	public void setUploaderBasePath(String uploaderBasePath)
	{
//		synchronized (uploaderSYSBasePath_)
		{
			this.uploaderSYSBasePath_ = (uploaderBasePath+"/").replaceAll("/+", "/");
		}
	}

	private IRenameFile iRenameFile=new IRenameFile()
	{
		
		public String rename(String oldName)
		{
			return new Date().getTime()+"_"+oldName;
		}
	};
	/**
	 * 记录文件的处理的状态码：
	 * 00	 未知状态，表示出现问题或是还没有处理。
	 * 10	成功状态
	 * 20	文件大小问题
	 * 30	文件的数量问题
	 * 40	允许上传得文件类型问题
	 */
	private String runType=File4Item.UNKNOW_STATE;


	private String tempPath;


	
	/**返回上传操作的状态码
	 * 详细的状态说明在{@link com.accelet.upload.File4Item} 类中定义
	 * @return
	 */
	public String getRunType()
	{
		return runType;
	}


	private void setRunType(String runType)
	{
		this.runType = runType;
	}


	public String[] getAcceptFileTypes()
	{
		if(acceptFileTypes_==null)
			return acceptFileTypes;
		else
			return acceptFileTypes_;
	}

	public IRenameFile getiRenameFile()
	{
		return iRenameFile;
	}

	
	private void setFileNums(int _minFileNum,int _maxFileNum)
	{
		if(_minFileNum<0)
		{
			_minFileNum = 1;
		}
		if(_maxFileNum<0)
		{
			_maxFileNum=1;
		}else if(_maxFileNum<_minFileNum)
		{
			_maxFileNum=_minFileNum;
		}
			minFileNum=_minFileNum;
			maxFilesNum=_maxFileNum;
	}
	/**
	 * 设置上传得文件的大小限制，如果参数的minFileSize<0则计为0,如果maxFileSize<-1则计为-1，如果minFileSize>maxFileSize则maxFileSize=minFileSize
	 * @param _minFileSize 上传文件的最小限制
	 * @param _maxFileSize 上传文件的最大限制
	 */
	private void setFileSize(long _minFileSize,long _maxFileSize)
	{
		if(_minFileSize<0)
		{
			_minFileSize=0;
		}
		if (_maxFileSize < -1)
		{
			_maxFileSize = -1;
		}
		else if (_maxFileSize<_minFileSize&&_maxFileSize!=-1)
		{
			_maxFileSize = _minFileSize;
		}
		minFileSize=_minFileSize;
		maxFileSize = _maxFileSize;
	}

	public long getMaxFileSize()
	{

		return maxFileSize;

	}

	public Map<String, String> getParameters()
	{
		return parameters;
	}

	public Map<String, File4Item> getFiles()
	{
		return files;
	}

	public String getUploaderBasePath()
	{
		if(uploaderSYSBasePath_==null)
		{
			return uploaderSYSBasePath;
		}
		else
		{
			return uploaderSYSBasePath_;
		}
	}

	public boolean isCreateable4BasePath()
	{
		return createable4BasePath;
	}

	public int getMaxFileNums()
	{
		return maxFilesNum;
	}

	public int getminFilesNum()
	{
		return minFileNum;
	}

	public int getSizeThreshold()
	{
		return sizeThreshold;
	}

	public long getMinFileSize()
	{
		return minFileSize;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public  static long getLongFromString(String str) throws Exception
	{
		if(str!=null&&!"".equals(str))
		{
			if(!str.trim().equals(""))
			{
				String[] nums=str.split("\\*");
				if(nums==null||nums.length==0)
				{
					try {
						
						return Long.parseLong(str.trim());
						
					}
					catch(Exception e)
					{
						throw e;
					}
				}
				else
				{
					long res=1;
					for(int i=0;i<nums.length;i++)
					{
						if(nums[i].trim().equals(""))
						{
							throw new Exception("格式错误"+str);
						}
						try {
							res*=Long.parseLong(nums[i].trim());
						}
						catch(Exception e)
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
	/**执行上传操作的关键方法，将HttpServletRequest请求中的文件按照配置文件中的要求上传。
	 * @param request
	 * @throws UnsupportedEncodingException 
	 */
	public void uploading(HttpServletRequest request) throws UnsupportedEncodingException
	{   
		request.setCharacterEncoding("utf-8");
//		parameters = new Hashtable<String, String>();
		parameters=getParameters(request);
		setRunType(File4Item.UNKNOW_STATE);
		

		files = new Hashtable<String, File4Item>();

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Set factory constraints
		
		/*****************/
		File file4repository = new File(repository);
		if(!file4repository.exists())
		{
			if(!file4repository.mkdirs())
			{
				this.runType=File4Item.REPOSITORY_STATE;
				return;
			}
		}
		
		factory.setRepository(file4repository);
		
		
		factory.setSizeThreshold(sizeThreshold);

		// factory.setRepository(repository);

		// Create a new file upload handler

		ServletFileUpload upload = new ServletFileUpload(factory);//org.apache.commons.io.output.DeferredFileOutputStream

		// Set overall request size constraint
		
		//*********************************//
		//此处不限制文件的大小，有后面的机制限制
		//*********************************//
		upload.setSizeMax(maxFileSize);
		System.out.println("the pic max size："+maxFileSize);
		upload.setHeaderEncoding(encoding);
		try
		{

			
			System.out.println("begin parse");
		
			List items = upload.parseRequest(request);			
			System.out.println("parse end");
//			Collections.sort(items, new Comparator<FileItem>()
//			{
//
//				public int compare(FileItem o1, FileItem o2)
//				{
//					return 0;
//				}
//			});		
			// 文件处理的顺序没有定义
			for (Object object : items)
			{			
				FileItem item = (FileItem) object;
				if (item.isFormField())
				{
					String fieldName = item.getFieldName();
					String value = item.getString(encoding);
					if(PARAM_NAME.equals(fieldName))
					{
						this.tempPath=value.trim();
					}
					parameters.put(fieldName, value);

				}
				else
				{
					if(!"".equals(item.getName()))//限制其它的条件，如文件的大小
					{
						File4Item file = new File4Item(item);
						checkFileSize(file);
						System.out.println("the file size:"+file.getFileSize());
						checkAcceptFileTypes(file);
						
						files.put(item.getName(), file);

					}
					//无法排序使用其它的map类型
				}
			}
			if(checkFileNums(files)&&checkBasePath())
			{
				this._uploading(files);//当上传得文件数量符合要求的是执行上传操作。
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getClass());
			if(e.getClass() == org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException.class)
			{
				this.runType=File4Item.FILESIZE_STATE;
			}
			if(e.getCause()!=null&&e.getCause().getClass() == org.apache.commons.fileupload.MultipartStream.MalformedStreamException.class)
				this.runType=File4Item.FILEUPLOAD_CANCEL;
			try
			{
				request.getInputStream().close();
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			System.out.println("上传出错！");
		}
	}
	private boolean checkBasePath()
	{
		File file;
		if(usingUserPath&&tempPath!=null&&!"".equals(tempPath))
		{
			tempPath=(tempPath+"/").replaceAll("/+","/");
			file=new File(tempPath);
		}
		else
		{
			file = new File(getUploaderBasePath());
		}
		if(file.isDirectory())
		{
			return true;
		}
		else
		{
			if(createable4BasePath)
			{
				if(file.mkdirs())
				{
					return true;
				}
			}
			this.runType=File4Item.UPLOADERBASEPATH_STATE;
			return false;
		}
	}
	private boolean checkFileNums(Map<String, File4Item> files)
	{
		if(files.size()>maxFilesNum||files.size()<minFileNum)
		{
			this.runType=File4Item.FILENUMS_STATE;
			return false;
		}
		return true;
	}

	private void checkAcceptFileTypes(File4Item file)
	{
		for(int i=0;i<getAcceptFileTypes().length;i++)
		{
			if(getAcceptFileTypes()[i].trim().equals(file.getFileType()))
			{
				return;
			}
			else if(getAcceptFileTypes()[i].trim().equals("*"))
			{
				return;
			}
		}
		file.getContext().append("文件类型不匹配:"+file.getFileType()+"\n");
		file.setRunType(File4Item.FILETYPE_STATE);
		
	}

	private void checkFileSize(File4Item file)
	{
		if(minFileSize>file.getFileSize())
		{
			file.getContext().append("文件大小<最小的上传限制:"+minFileSize+"B\n");
			file.setRunType(File4Item.FILESIZE_STATE);
		}
		if(maxFileSize<file.getFileSize()&&maxFileSize!=-1)
		{
			
			file.getContext().append("文件大>最大的上传限制:"+maxFileSize+"B\n");
			file.setRunType(File4Item.FILESIZE_STATE);
		}
		
	}
	private void _uploading(Map<String, File4Item> files) 
	{
		 Collection<File4Item> values = files.values();
		 Iterator<File4Item> iterator = values.iterator();
		 runType=File4Item.SUCCESS_STATE;
		while(iterator.hasNext())
		{
			File4Item item = iterator.next();
			String rename = iRenameFile.rename(item.getFileRemoteName());
			item.setFileLocalName(rename);//设置本地文件名
			if(File4Item.UNKNOW_STATE.equals(item.getRunType()))
			{
				item.setRunType(File4Item.SUCCESS_STATE);
				try
				{
					if(tempPath==null||"".equals(tempPath)||!usingUserPath)
					{
						item.setFileLocalPath(getUploaderBasePath()+rename);//设置本地文件的存放位置
						item.getFileItem().write(new File(getUploaderBasePath()+rename));
					}else
					{
						item.setFileLocalPath(tempPath+rename);//设置本地文件的存放位置
						item.getFileItem().write(new File(tempPath+rename));
					}
				}
				catch (Exception e)
				{
					//异常的捕捉还没有写					
					item.setRunType(File4Item.UNKNOW_STATE);
					item.getContext().append("出现异常:"+e.getMessage()+"\n");
					e.printStackTrace();
				}
			}
			//如果上传的文件中有一个出现 错误则 整个FileUpload的对象的 运行 状态变为NOTALLSUCCESS_STATE
			if(!File4Item.SUCCESS_STATE.equals(item.getRunType()))
			{
				this.runType=File4Item.NOTALLSUCCESS_STATE;
			}
		}
	}
	public static Hashtable<String, String> getParameters(HttpServletRequest request)
	{
		
		Hashtable<String, String> param=new Hashtable<String, String>();
		Object nextElement=null;
		String _encoding = request.getCharacterEncoding();
		try
		{
			request.setCharacterEncoding(_encoding==null? encoding:_encoding);
			Enumeration parameterNames = request.getParameterNames(); 
			while(parameterNames.hasMoreElements())
			{
				nextElement=parameterNames.nextElement();
				String parameter = request.getParameter(nextElement.toString());
				byte[] bytes4ISO = parameter.getBytes(_encoding==null? "iso-8859-1":_encoding);
				String p4Utf=new String(bytes4ISO,encoding);
				//			System.out.println("\t"+nextElement+":"+parameter);
				System.out.println("\t"+nextElement+":"+parameter+" \tencode: "+p4Utf);
				param.put(nextElement.toString(), p4Utf);
			}
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return param;
	}
	public static Hashtable<String, String> getParameters(HttpServletRequest request,String encoding)
	{
		
		Hashtable<String, String> param=new Hashtable<String, String>();
		Object nextElement=null;
		String _encoding = request.getCharacterEncoding();
		try
		{
			request.setCharacterEncoding(_encoding==null? encoding:_encoding);
			Enumeration parameterNames = request.getParameterNames(); 
			while(parameterNames.hasMoreElements())
			{
				nextElement=parameterNames.nextElement();
				String parameter = request.getParameter(nextElement.toString());
				byte[] bytes4ISO = parameter.getBytes(_encoding==null? "iso-8859-1":_encoding);
				String p4Utf=new String(bytes4ISO,encoding);
				//			System.out.println("\t"+nextElement+":"+parameter);
				System.out.println("\t"+nextElement+":"+parameter+" \tencode: "+p4Utf);
				param.put(nextElement.toString(), p4Utf);
			}
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return param;
	}
}
