package per.cz.util.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZIPUtil {
	public static void traverse(String file,ISearchConditionZip searchCondition,ISearchHandle4Zip searchHandle) throws IOException
	{
		ZipFile zipf=new ZipFile(new File(file));
		 Enumeration<? extends ZipEntry> entries = zipf.entries();
		 while(entries.hasMoreElements())
		 {
			 ZipEntry pZipEntry = entries.nextElement();
			 if(searchCondition.hasCondition(pZipEntry))
			 {
				 if(!searchHandle.handle(pZipEntry))
				 {
					 return;
				 }
			 }
			 InputStream inputStream = zipf.getInputStream(pZipEntry);
			 if(inputStream==null)
				 continue;
			 ZipInputStream zpIn=new ZipInputStream(inputStream);
			 if(zpIn==null)
				 continue;
			 ZipEntry zipEntry=null;
//			 String jarName=pZipEntry.getName().replaceAll(path, "");
			 while((zipEntry = zpIn.getNextEntry())!=null)
			 {
				 if(searchCondition.hasCondition(zipEntry))
				 {
					 if(!searchHandle.handle(zipEntry))
					 {
						 return;
					 }
				 }
			 }
		 }
	}
	public static void main(String[] args) throws IOException {
//		FileEdit.search(new File("C:\\wso2\\wso2carbon-4.4.0"), new ISearchCondition(){
		FileEdit.search(new File("C:\\wso2\\wso2am-1.8.0_1\\wso2am-1.8.0\\"), new ISearchCondition(){
			@Override
			public boolean hasCondition(final File file) {
				//resolver-status.properties
//				if(file.getName().matches(".*apimgt\\.interceptor"))
//				{
////					System.out.println(file.getAbsolutePath());
//				}
			 if(file.getName().matches(".*\\.jar"))
				{
					return true;
				}
				return false;
			}}, new ISearchHandle(){
				@Override
				public void handle(final File file) {
					try {
						
						ZIPUtil.traverse(file.getAbsolutePath(), new ISearchConditionZip(){
							@Override
							public boolean hasCondition(ZipEntry zip) {
								if(zip.getName().matches(".*apimgt.interceptor.*"))
								{
									return true;
								}
								return false;
							}},new ISearchHandle4Zip(){
							@Override
							public boolean handle(ZipEntry zip) {
								System.out.println(file.getAbsolutePath()+":"+zip.getName());
								return true;
							}});
					} catch (Exception e) {
						e.printStackTrace();
					}
//					System.out.println("delete:"+file.getAbsolutePath()+(deleteFiles(file)?":success":":failed"));
				}}, FileEdit.FILES);//
	}
}
