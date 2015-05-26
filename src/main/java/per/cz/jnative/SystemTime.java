package per.cz.jnative;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.AbstractBasicData;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

import per.cz.jnative.struct.Bytes;
import per.cz.jnative.struct.OriginalData;


/**
 * SystemTime
 * 
 * typedef struct _SYSTEMTIME {
 *     WORD wYear;
 *     WORD wMonth;
 *     WORD wDayOfWeek;
 *     WORD wDay;
 *     WORD wHour;
 *     WORD wMinute;
 *     WORD wSecond;
 *     WORD wMilliseconds;
 * } SYSTEMTIME, 
 */
public class SystemTime extends AbstractBasicData<SystemTime> {

	public short wYear;
	public short wMonth;
	public short wDayOfWeek;
	public short wDay;
	public short wHour;
	public short wMinute;
	public short wSecond;
	public short wMilliseconds;

	/**
	 * 分配内存，并返回指针
	 */
	public Pointer createPointer() throws NativeException {
		pointer = new Pointer(MemoryBlockFactory.createMemoryBlock(getSizeOf()));
		return pointer;
	}

	/**
	 * 字节数
	 */
	public int getSizeOf() {
		return 8 * 2;
	}

	/**
	 * 获取通过内存指针解析出结果
	 */
	public SystemTime getValueFromPointer() throws NativeException {
		wYear = getNextShort();
		wMonth = getNextShort();
		wDayOfWeek = getNextShort();
		wDay = getNextShort();
		wHour = getNextShort();
		wMinute = getNextShort();
		wSecond = getNextShort();
		wMilliseconds = getNextShort();
		return this;
	}

	public SystemTime() throws NativeException {
		super(null);
		createPointer();
	}

	public String toString() {
		return wYear + "/" + wMonth + "/" + wDay + " at + " + wHour + ":" + wMinute + ":" + wSecond
				+ ":" + wMilliseconds;
	}

	public static SystemTime GetSystemTime() throws NativeException, IllegalAccessException {
		// 创建对象
		System.load("F://dll//DataMergeAndAnaly//DataMergeAndAnaly.dll");
		//    	JNative nGetSystemTime = new JNative("DataMergeAndAnaly", "SplitData");
		JNative respondHeartCommand = new JNative("DataMergeAndAnaly", "RespondHeartCommand");
		SystemTime systemTime = new SystemTime();
		Bytes bs=new Bytes(24);
		// 设置参数
		respondHeartCommand.setParameter(0, Type.INT,"10");
		respondHeartCommand.setParameter(1, bs.getPointer());
		//        nGetSystemTime.setParameter(0, systemTime.getPointer());
		respondHeartCommand.invoke();
		// 解析结构指针内容
		return systemTime.getValueFromPointer();
	}

	public static void main(String[] args) throws NativeException, IllegalAccessException {
		System.load("F://dll//DataMergeAndAnaly//DataMergeAndAnaly.dll");
		//    	JNative nGetSystemTime = new JNative("DataMergeAndAnaly", "SplitData");
		JNative respondHeartCommand = new JNative("DataMergeAndAnaly", "RespondHeartCommand");
		Bytes bs=new Bytes(24);
		// 设置参数
		respondHeartCommand.setParameter(0, Type.INT,"10");
		respondHeartCommand.setParameter(1, bs.getPointer());
		//        nGetSystemTime.setParameter(0, systemTime.getPointer());
		respondHeartCommand.invoke();
		//System.out.println(bs);

		//        JNative analyTagData = new JNative("DataMergeAndAnaly", "AnalyTagData");
		//        Bytes bs=new Bytes(24);
		//        // 设置参数
		//        analyTagData.setParameter(0, Type.INT,"10");
		//        analyTagData.setParameter(1, bs.getPointer());
		////        nGetSystemTime.setParameter(0, systemTime.getPointer());
		//        analyTagData.invoke();
		//        //System.out.println(bs);
		//        
		//        
		//        JNative analyTiming = new JNative("DataMergeAndAnaly", "AnalyTiming");
		//        Bytes bs=new Bytes(24);
		//        // 设置参数
		//        analyTiming.setParameter(0, Type.INT,"10");
		//        analyTiming.setParameter(1, bs.getPointer());
		//        analyTiming.invoke();
		//        //System.out.println(bs);


		JNative splitData = new JNative("DataMergeAndAnaly", "SplitData");
		Bytes bs2=new Bytes(24);
		byte[] b=new byte[24];
		b[23]=1;
//		bs2.setDatas(b);
		OriginalData od=new OriginalData();
		// 设置参数
		splitData.setRetVal(Type.INT);   
		splitData.setParameter(0, bs2.getPointer());
		splitData.setParameter(1, Type.INT,"24");
		splitData.setParameter(2, od.getPointer());
		splitData.setParameter(3, Type.INT,"");
		//        nGetSystemTime.setParameter(0, systemTime.getPointer());
		splitData.invoke();

		//System.out.println( od);
		//System.out.println( splitData.getRetVal());
	}

}