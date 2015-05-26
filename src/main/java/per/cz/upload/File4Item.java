package per.cz.upload;

import java.io.File;
import java.io.Serializable;

import org.apache.commons.fileupload.FileItem;

/**
 * 上传操作后返回的结果将存放在此类中。 并存放着上传作的状态码。<br> 
 * 000 未知状态，表示出现问题或是还没有处理。<br>
 * 010 成功状态<br>
 * 110 上传的文件类型不符合上传要求<br>
 * 
 * 210 文件的数量不匹配状态<br>
 * 220 上传的目的文件夹没能创建（可能因为参数列表createable4BasePath为false），
 * 或是createable4BasePath为true但是创建的时失败,这时可能是要创建的文件夹带有系统不认可的关键字,或是位置错误。<br>
 * 230 缓冲文件存放的文件夹创建出现问题。<br>
 * 240 上传的文件中存在不成功的情况的状态。<br>
 * 250 配置文件中，配置信息获取失败状态。<br>
 * 260 上传的文件大小不符合限制要求<br>
 * 270 文件上传被取消(或是中断)。<br>
 * 
 * @author Huang.Jilong
 * 
 */
public class File4Item implements Serializable
{
	private static final long serialVersionUID = 20120420L;
	
	/**
	 * 文件还没有上传，或是出现异常的状态<br>
	 * File4Item 及 FileUpload 对象的状态
	 */
	public static final String UNKNOW_STATE		="000";
	
	/**
	 * 当所有的文件上传成功的状态<br>
	 * File4Item 及 FileUpload 对象的状态
	 */
	public static final String SUCCESS_STATE	="010";
	/**
	 * 上传的文件类型不符合上传要求<br>
	 * File4Item 对象的状态
	 */
	public static final String FILETYPE_STATE	="110";
	
	
	
	/**
	 * 上传的文件数量不符合限制要求<br>
	 * FileUpload 对象的状态
	 */
	public static final String FILENUMS_STATE	="210";
	/**
	 * 上传的目的文件夹没能创建（可能因为参数列表createable4BasePath为false），或是createable4BasePath为true但是创建的时失败,这时可能是要创建的文件夹带有系统不认可的关键字。<br>
	 * FileUpload 对象的状态
	 */
	public static final String UPLOADERBASEPATH_STATE	="220";
	/**
	 * 缓冲文件夹创建失败<br>
	 * FileUpload 对象的状态
	 */
	public static final String REPOSITORY_STATE	="230";
	/**
	 * 上传的文件中部分上传成功，但是存在失败的例子。<br>
	 * FileUpload 对象的状态
	 */
	public static final String NOTALLSUCCESS_STATE	="240";
	/**
	 * 配置文件中，配置信息获取失败状态
	 */
	public static final String PROPLOADFAILED_STATE	="250";
	
	/**
	 * 上传的文件大小不符合限制要求<br>
	 * File4Item 及FileUpload对象的状态
	 */
	public static final String FILESIZE_STATE	="260";
	/**
	 *文件上传被取消(或是中断) 
	 */
	public static final String FILEUPLOAD_CANCEL="270";
	
	private String fileRemoteName;
	private String fileLocalName;
	private long fileSize;
	private String fieldName;
	private transient FileItem fileItem;
	/**
	 * 记录文件的处理的状态码：<br>
	 * 000	未知状态，表示出现问题或是还没有处理。<br>
	 * 010	成功状态<br>
	 * 110	文件类型匹配失败状态<br>
	 * 120	文件的大小不匹配状态<br>
	 */
	private String runType;
	private String fileType;
	private String fileRemotePath;
	private String fileLocalPath;
	private StringBuffer context;
	public File4Item(FileItem file)
	{
		super();
		this.fileItem=file;
		this.fileRemotePath = file.getName();
		this.fileRemoteName=new File(fileRemotePath).getName();
		this.fileSize = file.getSize();
		this.fieldName = file.getFieldName();
		this.context=new StringBuffer(this.fileRemoteName+":");
		this.runType=File4Item.UNKNOW_STATE;
		int index = this.fileRemoteName.lastIndexOf(".");
		if(index!=-1)
		{
			this.fileType=this.fileRemoteName.substring(index+1);
		}
		else
		this.fileType=" ";
	}
	
	public String getFileLocalName() {
		return fileLocalName;
	}

	public void setFileLocalName(String fileLocalName) {
		this.fileLocalName = fileLocalName;
	}

	public String getFileLocalPath() {
		return fileLocalPath;
	}

	public void setFileLocalPath(String fileLocalPath) {
		this.fileLocalPath = fileLocalPath;
	}

	public String getFileRemotePath() {
		return fileRemotePath;
	}

	public String getFileType()
	{
		return fileType;
	}
	public String getRunType()
	{
		return runType;
	}

	void setRunType(String type)
	{
		this.runType = type;
	}

	public StringBuffer getContext()
	{
		return context;
	}

	public FileItem getFileItem()
	{
		return fileItem;
	}

	public String getFileRemoteName()
	{
		return fileRemoteName;
	}
	public long getFileSize()
	{
		return fileSize;
	}
	public String getFieldName()
	{
		return fieldName;
	}

	@Override
	public String toString() {
		return "File4Item [fileRemoteName=" + fileRemoteName
				+ ", fileLocalName=" + fileLocalName + ", fileSize=" + fileSize
				+ ", fieldName=" + fieldName +  ", runType="
				+ runType + ", fileType=" + fileType + ", fileRemotePath="
				+ fileRemotePath + ", fileLocalPath=" +fileLocalPath
				+ ", context=" + context + "]\n";
	}
public static void main(String[] args) {
	System.out.println(File4Item.class.getResource("upload.properties"));
}


}
