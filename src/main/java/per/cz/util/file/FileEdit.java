package per.cz.util.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import per.cz.util.file.handler.IRenameHandler;




/**
 * @author HJL
 * @since 2011-12-10
 */
public class FileEdit
{
	private static PrintWriter pw = null;
	private BufferedInputStream bfi;
	private static int findCounts;
	private static int copyCounts;
	public static final int FILESCOPY_COVER_ALL=1;  
	public static final int FILESCOPY_OVERRIDE_FILES=2;  
	public static final int FILESCOPY_OVERRIDE_DIR=3;  
	public static final int FILES=1;
	public static final int DIRECTORY=2;  
	

	public FileEdit()
	{
		findCounts = 0;
	}

	/**
	 * 锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷指锟斤拷 锟斤拷锟侥硷拷锟斤拷 file 锟斤， 锟斤拷锟揭碉拷锟侥硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞絝ileName
	 * 
	 * @param fileFrom
	 *            指锟斤拷 锟斤拷锟侥硷拷锟斤拷
	 * @param fileName
	 *            锟斤拷锟斤拷锟斤拷式
	 * @param pw
	 *            要锟斤拷锟斤拷锟絇rintWriter ,锟斤拷锟轿拷锟斤拷锟酵Ｖ�
	 */
	public static void searchFiles(File fileFrom, String fileName,OutputStream output, int tag)
	{
		findCounts=0;
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(output)), false);
		SearchCondition<String> condition = new SearchCondition<String>(fileName){
			
			@Override
			public  boolean hasCondition(File file) {
				if(file.getName().matches(this.getCondition()))
				{
					return true;
				}
				return false;
			}};
		search(fileFrom, condition, new ISearchHandle(){

			@Override
			public void handle(File file) {
				pw.println(file.getAbsolutePath());
			}},tag);
		pw.println("\t锟斤拷锟斤拷\t " + findCounts + "\t锟斤拷");
		pw.close();
	}
//锟斤拷锟斤拷 锟斤拷锟斤拷
	/**
	 * 锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷锟侥硷拷锟斤拷
	 * @param file 要锟斤拷锟斤拷锟侥凤拷围锟斤拷锟斤拷锟轿拷眨锟斤拷锟接筹拷锟斤拷锟斤拷锟斤拷目录锟斤拷锟揭ｏ拷
	 * @param condition 锟斤拷锟揭碉拷锟斤拷锟斤拷
	 * @param handle 锟斤拷锟揭碉拷锟斤拷拇锟斤拷?锟斤拷
	 * @param tag 锟斤拷锟斤拷锟窖�FileEdit.FILES|FileEdit.DIRECTORY锟斤拷锟斤拷示锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷锟侥硷拷锟叫ｏ拷锟斤拷锟角讹拷锟斤拷锟斤拷锟斤拷
	 */
	public static void search(File file,ISearchCondition condition, ISearchHandle handle,int tag)
	{
		if (handle == null)
		{
			return;
		}
		if (file == null)
		{
			file = new File(System.getProperty("user.dir"));
		}
		if (file.isDirectory())
		{
			if((tag&DIRECTORY)>0)
			{
				if (condition.hasCondition(file))
				{
					handle.handle(file);
					findCounts++;
				}
			}
			File[] files = file.listFiles();
			if (files == null || files.length == 0)
				return;
			for (File f : files)
			{
				search(f, condition, handle,tag);
			}
		}
		else if((tag&FILES)>0)
		{
			// if(file.getName().indexOf((fileName))>-1)
			if (condition.hasCondition(file))
			{
				// System.out.println(file.getAbsolutePath());
				handle.handle(file);
//				out.println(file.getAbsolutePath());
				findCounts++;
			}
			else
			{
				return;
			}
		}
	}

	/**
	 * 锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷锟侥硷拷锟侥硷拷锟叫ｏ拷锟斤拷指锟斤拷 锟斤拷锟侥硷拷锟斤拷 file 锟斤，锟斤拷锟揭碉拷锟侥硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞絝ileName
	 * 注锟解：锟剿凤拷锟斤拷锟斤拷锟矫ｏ拷锟斤拷为锟斤拷锟揭的斤拷锟结被锟斤拷录锟斤拷锟节达拷(锟斤拷锟斤拷returns)锟叫ｏ拷锟斤拷锟斤拷锟饺较多，锟斤拷影锟斤拷系统锟斤拷锟叫★拷
	 * @param file
	 *            指锟斤拷 锟斤拷锟侥硷拷锟斤拷
	 * @param fileName
	 *            锟斤拷锟斤拷锟斤拷式
	 * @param returns
	 *            要锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷牟锟斤拷锟斤拷锟轿拷眨锟斤拷锟�
	 * @param tag 锟斤拷锟斤拷锟窖�FileEdit.FILES|FileEdit.DIRECTORY锟斤拷锟斤拷示锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷锟侥硷拷锟叫ｏ拷锟斤拷锟角讹拷锟斤拷锟斤拷锟斤拷
	 */
	public static int searchFiles(File file, String fileName,final List<String> returns,int tag)
	{
		findCounts=0;
		SearchCondition<String> condition = new SearchCondition<String>(fileName){
			
			@Override
			public  boolean hasCondition(File file) {
				if(file.getName().matches(this.getCondition()))
				{
					return true;
				}
				return false;
			}};
		search(file, condition, new ISearchHandle(){

			@Override
			public void handle(File file) {
				returns.add(file.getAbsolutePath());
			}},tag);
		//pw.add("\t锟斤拷锟斤拷\t " + findCounts + "\t锟斤拷");
		return returns.size();
	}



	public static boolean fileCopy(File from, File to)
	{
		BufferedInputStream bfi;
		BufferedOutputStream bfo;
		try
		{
			bfi = new BufferedInputStream(new FileInputStream(from));
			bfo = new BufferedOutputStream(new FileOutputStream(to, false));
			byte[] bt = new byte[1024 * 1];
			int size=0;
			while ((size=bfi.read(bt)) > -1)
			{
				bfo.write(bt, 0, size);
			}
			bfi.close();
			bfo.close();
			return true;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param directory
	 * @param sort
	 * @param filter
	 * @param renameHandler
	 * @return
	 */
	public static int rename(File[] directory,Comparator<File> sort,FileFilter filter,IRenameHandler renameHandler)
	{
		ArrayList<File[]> arrayList = new ArrayList<File[]>();
		for (File file : directory) {
			if(!file.isDirectory())
			{
				return -1;
			}
			else
			{
				 File[] list = file.listFiles(filter);
				 Arrays.sort(list, sort);
				 arrayList.add(list);
			}
		}
		int num=0;
		File renameFile=null;
		for (File[] files : arrayList){
			for (int i=0; i<files.length;i++) {
				renameFile = renameHandler.execute(files[i],renameFile);
				num++;
			}
		}
		return num;
	}
	/**
	 * 拷贝文件
	 * 
	 * @param from
	 *           
	 * @param to
	 *            目锟斤拷锟侥硷拷锟斤拷
	 * @return 锟斤拷锟截匡拷锟斤拷锟缴癸拷锟斤拷锟侥硷拷锟侥革拷锟斤拷.
	 */
	public static int filesCopy(File from, File to,int type)
	{
		copyCounts = 0;
		if(to.exists())
		{
				if(type==FILESCOPY_COVER_ALL)//
				{
					if(new File(to.getAbsolutePath()+File.separator+from.getName()).exists())
					deleteFiles(new File(to.getAbsolutePath()+File.separator+from.getName()));//
					//creatDir(to, true);
					return fCopy(from, to,true);
				}
				else if(type==FILESCOPY_OVERRIDE_FILES)//锟斤拷写锟侥硷拷锟斤拷锟斤拷锟斤拷锟角ｏ拷
				{
					return fCopy(from, to,false);
				}
				else//(type==FILESCOPY_OVERRIDE_DIR)//锟斤拷写锟侥硷拷锟斤拷
				{
					if(from.isDirectory())
					{
						to=new File(to.getAbsolutePath()+File.separator+from.getName());
						to=creatDir1(to, false);
					}
					System.out.println(to.exists());
					return fCopyOnlyFrom(from, to);
			
				}
		}
		else
		{
			return fCopy(from, to,true);
		}
	}

	private static int fCopy(File from, File to,Boolean isCover)
	{
		// 锟斤拷锟斤拷锟斤拷,1,锟斤拷锟斤拷前锟斤拷锟�
		/*
		 * if(!to.exists()) { to.mkdirs(); }
		 */
			if (from.isDirectory())
			{
				to=new File(to.getAbsolutePath()+File.separator+from.getName());
				to.mkdirs();
				//File fto = creatDir(to.getAbsolutePath()+File.separator+from.getName(),isCover);
				File[] lsF = from.listFiles();
				if (lsF != null && lsF.length != 0)
				{
					for (File file : lsF)
					{
						fCopy(file,new File(to.getAbsolutePath()),isCover);
					}
				}
			}
			else if(from.isFile())
			{
				to = creatFile(new File(to.getAbsolutePath()+File.separator+from.getName()),isCover);
				fileCopy(from, to);
				copyCounts++;
			}
		return copyCounts;
	}
	/**锟斤拷锟斤拷from锟铰碉拷锟侥硷拷锟斤拷to锟侥硷拷锟斤拷(锟斤拷锟剿碉拷锟斤拷锟侥硷拷锟斤拷)
	 * 锟斤拷锟斤拷from 锟斤拷to 锟侥硷拷锟斤拷锟斤拷锟斤拷
	 * @param from
	 * @param to
	 * @return
	 */
	private static int fCopyOnlyFrom(File from, File to)
	{
		// 锟斤拷锟斤拷锟斤拷,1,锟斤拷锟斤拷前锟斤拷锟�
		/*
		 * if(!to.exists()) { to.mkdirs(); }
		 */
		if(from!=null)
		 {
			if (from.isDirectory())
			{
				//File fto = creatDir(to.getAbsolutePath()+File.separator+from.getName(),isCover);
				File[] lsF = from.listFiles();
				if (lsF != null)
				{
					for (File file : lsF)
					{
						if(file.isDirectory())
						{
							new File(to.getAbsolutePath()+File.separator+file.getName()).mkdirs();
							//to.mkdirs();
						}
						fCopyOnlyFrom(file,new File(to.getAbsolutePath()+File.separator+file.getName()));
					}
				}
			}
			else if(from.isFile())
			{
				to = creatFile(new File(to.getAbsolutePath()),true);
				fileCopy(from, to);
				copyCounts++;
			}
		 }
		return copyCounts;
	}

	/**
	 * 锟斤拷锟斤拷锟侥硷拷锟叫ｏ拷锟斤拷锟斤拷锟节的伙拷锟皆讹拷使锟矫★拷-锟斤拷锟斤拷锟斤拷锟斤拷牵锟�
	 * 
	 * @param file
	 *            要锟斤拷锟斤拷锟斤拷锟侥硷拷锟斤拷 锟斤拷
	 * @param isCover
	 *            锟角否覆革拷  ture 锟斤拷锟斤拷 锟斤拷false 锟斤拷锟斤拷锟斤拷
	 * @return 锟斤拷锟斤拷锟斤拷锟侥硷拷锟叫碉拷锟斤拷锟斤拷
	 */
	public static  File creatDir1(File file,Boolean isCover)
	{

		 int counts=2;
		 File f;
		 if(file.exists())
		 {
			if(isCover)
			{
				deleteFiles(file);
				f=file;
			}
			else
			{
				if(new File(file.getAbsolutePath()+" - 锟斤拷锟斤拷 ").exists())
				{
					while(new File(file.getAbsolutePath()+" - 锟斤拷锟斤拷 ("+counts+")").exists())
					{
						counts++;
					}
					f=new File(file.getAbsolutePath()+" - 锟斤拷锟斤拷 ("+counts+")");
				}
				else
				{
					f=new File(file.getAbsolutePath()+" - 锟斤拷锟斤拷 ");
				}
			}
		 }
		 else
		 {
			 f = file;
		 }
			f.mkdirs();
		return f;
	}

	public static File creatDir(File file,IRenameHandler handler)
	{
		if(file.exists())
		{
			if(handler==null)
			{
				handler=new IRenameHandler(){

					@Override
					public File execute(File nowFile, File preFile) {
						int counts=2;
						 File f;
						 if(nowFile.exists())
						 {
							 if(new File(nowFile.getAbsolutePath()+"_1").exists())
							 {
								 while(new File(nowFile.getAbsolutePath()+"_"+counts).exists())
								 {
									 counts++;
								 }
								 f=new File(nowFile.getAbsolutePath()+"_"+counts);
							 }
							 else
							 {
								 f=new File(nowFile.getAbsolutePath()+"_1");
							 }
						 }
						 else
						 {
							 f = nowFile;
						 }
							f.mkdirs();
						return f;
					}};
			}
			return handler.execute(file, null);
		}
		else
		{
			try{
				if(file.mkdirs())
				{
					return file;
				}
				else
				{
					return null;
				}
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * 锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷锟斤拷锟节的伙拷锟皆讹拷使锟矫★拷-锟斤拷锟斤拷锟斤拷锟斤拷牵锟�
	 * 
	 * @param newFile
	 *            要锟斤拷锟斤拷锟斤拷锟侥硷拷锟斤拷
	 * @return 锟斤拷锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷锟斤拷
	 */
	public static File creatFile(File newFile,Boolean isCover)
	{
		int counts = 2;
		String prefix ;
		String suffix ;
		if(newFile.getAbsolutePath().lastIndexOf(".")==-1)
		{
			prefix=newFile.getAbsolutePath();
			suffix="";
		}
		else
		{
			prefix = newFile.getAbsolutePath().substring(0, newFile.getAbsolutePath().lastIndexOf("."));
			suffix = newFile.getAbsolutePath().substring(newFile.getAbsolutePath().lastIndexOf("."));
		}
		File file;
		if (newFile.exists())
		{
			if(isCover)
			{
				deleteFiles(newFile);
				file=newFile;
			}
			else
			{
				if (new File(prefix + " - 锟斤拷锟斤拷 " + suffix).exists())
				{
					while (new File(prefix + " - 锟斤拷锟斤拷 (" + counts + ")" + suffix).exists())
					{
						counts++;
					}
					file = new File(prefix + " - 锟斤拷锟斤拷 (" + counts + ")" + suffix);
				}
				else
				{
					file = new File(prefix + " - 锟斤拷锟斤拷 " + suffix);
				}
			}
		}
		else
		{
			file = newFile;
			file.getParentFile().mkdirs();
		}
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return file;
	}

	private static int deleteCounts=0;
	
	
	/**删锟斤拷锟侥硷拷锟斤拷锟斤拷锟侥硷拷锟斤拷
	 * @param file 要删锟斤拷锟斤拷募锟斤拷锟斤拷锟斤拷募锟斤拷锟�
	 * @return 全锟斤拷删锟斤拷蠓祷锟絫rue 锟斤拷锟斤拷锟节伙拷锟斤拷删锟斤拷锟斤拷锟斤拷 锟斤拷锟斤拷false 
	 */
	public static boolean deleteFiles(File file)
	{
		if(!file.exists())
		{
			return false;
		}
		deleteCounts=0;
		int directorysNum=findDirectorysNum(file);
		int filesNum=findFilesNum(file);
		int deleteNum=deleteF(file);
		if(deleteNum==(filesNum+directorysNum))
		{
			return true;
		}
		return false;
	}
	/**删锟斤拷指锟斤拷锟斤拷锟侥硷拷锟斤拷路锟斤拷锟铰碉拷锟侥硷拷锟斤拷删锟斤拷锟斤拷募锟斤拷锟斤拷锟斤拷募锟斤拷蟹锟斤拷锟斤拷锟斤拷锟斤拷式regex
	 * @param path 要删锟斤拷锟斤拷募锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟斤拷诘锟侥柯硷拷锟�
	 * @param regex 删锟斤拷锟斤拷募锟斤拷锟斤拷锟斤拷募锟斤拷蟹锟斤拷锟斤拷锟斤拷锟斤拷式regex
	 * @return 锟斤拷path锟斤拷锟斤拷锟斤拷锟侥柯硷拷锟斤拷锟斤拷冢锟矫伙拷锟斤拷业锟斤拷锟斤拷要锟斤拷锟斤拷募锟斤拷锟斤拷锟缴撅拷锟斤拷锟斤拷锟�锟斤拷锟斤拷false锟斤拷
	 */
	public static boolean deleteFilesbyRegexName(String path,final String regex)
	{
		File directory=new File(path+"/".replaceAll("//", "/"));
		if(directory.isDirectory())
		{
			File[] listFiles = directory.listFiles(new FilenameFilter(){

				public boolean accept(File dir, String name) {
					if(name.matches(regex))
						return true;
					return false;
				}});
			boolean tag=true;
			for (File file : listFiles) {
				if(file.exists())
				{
					tag=tag&&deleteFiles(file);
				}
			}
			return tag&&listFiles.length!=0;
		}
		return false;
	}
	private  static int   deleteF(File file)
	{
		if (file == null)
		{}
		if (file.isDirectory())
		{
			File[] lf = file.listFiles();
			if(lf!=null)
			{
				for (File f : lf)
				{
					deleteF(f);
				}
			}
			file.delete();
			deleteCounts++;
		}
		else if(file.isFile())
		{
				file.delete();
				deleteCounts++;
		}
		return deleteCounts;
	}
	
	
	private static int findDirectorysCounts=0;
	/**统锟狡革拷锟斤拷募锟斤拷锟斤拷锟斤拷募锟斤拷械锟斤拷锟侥�锟斤拷锟斤拷锟皆硷拷锟斤拷锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷目锟斤拷
	 * @return 锟侥硷拷锟叫碉拷锟斤拷目
	 */
	public static int findDirectorysNum(File file)
	{
		findDirectorysCounts=0;
		return findDirectorys(file);
	}
	private static int findDirectorys(File file)
	{
		if(file.isDirectory())
		{
			findDirectorysCounts++;
			File[] files=file.listFiles();
			if(files!=null)
			{
				for(File f:files)
				{
					findDirectorys(f);
				}
				
			}
		}
		return findDirectorysCounts;
	}
	
	private static int findFilesCounts=0;
	
	/**统锟狡革拷锟斤拷募锟斤拷锟斤拷锟斤拷募锟斤拷械锟斤拷锟侥匡拷锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟侥匡拷锟�
	 * @return 锟侥硷拷锟叫碉拷锟斤拷目
	 */
	public  static int findFilesNum(File file)
	{
		findFilesCounts=0;
		return findFiles(file);
	}
	private  static int findFiles(File file)
	{
		if(file.isDirectory())
		{
			File[] files=file.listFiles();
			if(files!=null)
			{
				for(File f:files)
				{
					findFiles(f);
				}
				
			}
		}
		else if(file.isFile())
		{
			findFilesCounts++;
		}
		return findFilesCounts;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
/*		String s = "[a-z]*\\s?[a-z]*\\s?[a-z]*\\s?.+\\s?";
		String ss = "public  static void  a ()";
		if (ss.matches(s))
		{
			System.out.println(ss);
		}*/
	/*	Vector<String> arrayList = new Vector<String>();

			searchFiles(new File("F:\\a"),".+.txt$",arrayList);*/
			//String[] n=new String[arrayList];
			

		// new FileEditer().dirCopy("I:\\movie\\锟斤拷锟斤拷锟斤拷锟斤拷锟紺D1 锟斤拷英锟斤拷幕.RMVB",
		// "E:\\1.rmvb");
		// System.out.println(new
		// FileEditer().dirCopy("I:\\movie\\锟斤拷锟斤拷锟斤拷锟斤拷锟紺D1 锟斤拷英锟斤拷幕.RMVB", "E:\\a\\b"));
		//System.out.println(deleteFiles(new File("F:\\a")));
		//creatDir("F:\\a",true);
//		filesCopy(new File("F:\\a"),new File("F:\\b2"),1);
		//fCopyOnlyFrom(new File("F:\\a"),new File("F:\\b2\\a - 锟斤拷锟斤拷"));
		//deleteFiles(new File("F:\\a\\a"));
//		System.out.println(deleteFilesbyRegexName("E:\\Mine\\锟斤拷锟斤拷锟斤拷", ".*23.*"));
//		System.out.println("sfsfsdGoogleadfsd".matches(".*Google.*"));
//			searchFiles(new File("E:/"), ".*\\.((rmvb)|(RMVB))", System.out,1);
//			search(new File("E:/"), new SearchCondition<Integer>(1024*1024*1024){
//				
//				@Override
//				public boolean hasCondition(File file) {
//					if(file.length()>this.getCondition())
//					{
//						return true;
//					}
//					return false;
//				}}, new ISearchHandle(){
//
//					@Override
//					public void handle(File file) {
//						long length = file.length();
//						String l=(length<1024?length+"B":((length=length/1024)<1024?length+"KB":((length=length/1024)+"MB")));
//						System.out.println(file.getAbsolutePath()+"\t"+l);
//					}}, 2);
//		System.out.println("shudu.java".matches(".*shudu\\..+"));
//		FileEdit.creatDir(new File("F:/btc"), null);
		search(new File("C:\\wso2\\wso2am-1.8.0_2\\wso2am-1.8.0\\repository\\logs"), new ISearchCondition(){
		//search(new File("C:\\wso2\\wso2am-1.8.0_2\\wso2am-1.8.0"), new ISearchCondition(){
			@Override
			public boolean hasCondition(File file) {
				//resolver-status.properties
//				if(file.getName().matches("log4j.properties"))
//				{
//					return true;
//				}
//				else if (true)
//				{
//					return false;
//				}
					try {
						BufferedReader bf=new BufferedReader(new FileReader(file));
						String readLine = null;
						while((readLine=bf.readLine())!=null)
						{
							if(readLine!=null&&readLine.matches(".*apimgt\\.interceptor.*"))
//								if(readLine!=null&&readLine.matches(".*APIManagerInterceptorComponent.*"))
//							if(readLine!=null&&readLine.matches(".*start##.*"))
							{
								bf.close();
								return true;
							}
						}
//						if(readLine!=null&&readLine.matches(".*Robot.*"))
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//				if(file.getName().matches(".+\\.lastUpdated")||file.getName().matches("resolver-status.properties"))
					
//				}
				return false;
			}}, new ISearchHandle(){
				@Override
				public void handle(File file) {
					System.out.println(file.getAbsolutePath());
//					System.out.println("delete:"+file.getAbsolutePath()+(deleteFiles(file)?":success":":failed"));
				}}, FileEdit.FILES);//
//		search(new File("C:\\wso2\\wso2carbon-4.4.0"), new ISearchCondition(){
//			@Override
//			public boolean hasCondition(final File file) {
//				//resolver-status.properties
//				if(file.getName().matches(".*\\.jar"))
//				{
//					return true;
//				}
//				return false;
//			}}, new ISearchHandle(){
//				@Override
//				public void handle(final File file) {
//					try {
//						ZIPUtil.traverse(file.getAbsolutePath(), new ISearchConditionZip(){
//							@Override
//							public boolean hasCondition(ZipEntry zip) {
//								if(zip.getName().matches(".*APIManagerInterceptorValve.*"))
//								{
//									return true;
//								}
//								return false;
//							}},new ISearchHandle4Zip(){
//							@Override
//							public boolean handle(ZipEntry zip) {
//								System.out.println(file.getAbsolutePath()+":"+zip.getName());
//								return true;
//							}});
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
////					System.out.println("delete:"+file.getAbsolutePath()+(deleteFiles(file)?":success":":failed"));
//				}}, FileEdit.FILES);//
		
//		deleteFilesbyRegexName("D:\\_git_repository\\mpm","\\.lastUpdated");
//		final ArrayList<String> pw2 = new ArrayList<String>();
//		searchFiles(new File("E:/"), ".+\\.[XML|xml]$",pw2,FileEdit.FILES|FileEdit.DIRECTORY);//锟斤拷锟斤拷锟侥硷拷锟斤拷锟侥硷拷锟斤拷
//		for (String string : pw2) {
//			System.out.println(string);
//		}
//		int rename = rename(null,new Comparator<File>(){
//
//			@Override
//			public int compare(File o1, File o2) {
//				if(o1.lastModified()>o2.lastModified())
//				{
//					return 1;
//				}
//				else if(o1.lastModified()<o2.lastModified())
//				{
//					return -1;
//				}
//				return 0;
//			}},new FileFilter(){
//
//				@Override
//				public boolean accept(File pathname) {
//					if(pathname.getName().matches(".*x.{5}"))
//						return false;
//					return true;
//				}},new IRenameHandler(){
//
//					@Override
//					public File execute(File nowFile, File preFile) {
//						if(nowFile!=null&&nowFile.isFile())
//						{
//							File f=new File(nowFile.getParent()+File.separator+"0");
//							if(preFile==null)
//							{
//								
////								nowFile.renameTo(dest);
//							}
//						}
//						return null;
//					}});
	}

}

