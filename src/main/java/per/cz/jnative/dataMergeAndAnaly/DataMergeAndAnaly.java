package per.cz.jnative.dataMergeAndAnaly;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

import per.cz.jnative.DLLConfig;
import per.cz.jnative.struct.Bytes;
import per.cz.jnative.struct.OriginalData;
import per.cz.jnative.struct.SutHeartRecord;
import per.cz.jnative.struct.SutTagRecord;

public class DataMergeAndAnaly {
	static JNative splitData;
//	static JNative analyHeartData;
//	static JNative analyTagData;
//	static JNative analyTiming;
	static JNative respondHeartCommand; 
	static JNative respondTagRecordCommand;
	static JNative respondTimingCommand;
	private static JNative analyTimingSharp;
	private static JNative analyTagDataSharp;
	private static JNative analyHeartDataSharp;
	static{
		/**
		 AnalyHeartData,
		 AnalyTagData,
		 AnalyTiming,
		 RespondHeartCommand, 
		 RespondTagRecordCommand, 
		 RespondTimingCommand,
		 SplitData
		 
		 * */
		//
//		System.loadLibrary ("DataMergeAndAnaly.dll");
		System.load(DLLConfig.getDataMergeAndAnaly_dll());
		try {
			
			splitData=new JNative("DataMergeAndAnaly", "SplitData");
//			analyHeartData=new JNative("DataMergeAndAnaly", "AnalyHeartData");
//			analyTagData=new JNative("DataMergeAndAnaly", "AnalyTagData");
//			analyTiming=new JNative("DataMergeAndAnaly", "AnalyTiming");
			analyHeartDataSharp=new JNative("DataMergeAndAnaly", "AnalyHeartDataSharp");
			analyTagDataSharp=new JNative("DataMergeAndAnaly", "AnalyTagDataSharp");
			analyTimingSharp=new JNative("DataMergeAndAnaly", "AnalyTimingSharp");
			respondHeartCommand=new JNative("DataMergeAndAnaly", "RespondHeartCommand"); 
			respondTagRecordCommand=new JNative("DataMergeAndAnaly", "RespondTagRecordCommand");
			respondTimingCommand=new JNative("DataMergeAndAnaly", "RespondTimingCommand");
		} catch (NativeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 分解TCP通信接收到的原始数据函数声明<br>
	 * 
		byData	BYTE *	[输入]	TCP接收到的原始数据<br>
		nLength	UINT32	[输入]	TCP接收到的原始数据长度<br>
		recordList	Void *	[输出]	OriginalData结构体对象的指针，存储分解后的数据<br>
		nBufferSize	UINT32	[输入]	OriginalData结构体对象的指针的大小<br>
	 * @return 
		@return 	数据包个数
	 * @throws Exception
	 * UINT32  SplitData(BYTE *byData,UINT32 nLength,void * recordList,UINT32 nBufferSize);
	 */
	public static int SplitData(byte[] bs,List<OriginalData> originalDataList) throws Exception
	{
		if(bs==null||bs.length==0)
			return -1;
		if(originalDataList==null)
			return -2;
		OriginalData o0=new OriginalData();
//		Bytes bytes=new Bytes(bs);
		Pointer pointer = new Pointer(MemoryBlockFactory.createMemoryBlock(bs.length));
		for(int i=0;i<bs.length;i++)
		{
			pointer.setByteAt(i, bs[i]);
		}
		Pointer ipPtroriginal = new Pointer(MemoryBlockFactory.createMemoryBlock(o0.getSizeOf()*160));
		// 设置参数
		splitData.setRetVal(Type.INT);   
		splitData.setParameter(0,pointer);
//		splitData.setParameter(0,bytes.getPointer());
		splitData.setParameter(1, Type.INT,""+bs.length);
//		splitData.setParameter(2, pointer2);
		splitData.setParameter(2, ipPtroriginal);
		splitData.setParameter(3, Type.INT,""+160);
		OriginalData od=new OriginalData();
		//        nGetSystemTime.setParameter(0, systemTime.getPointer());
		splitData.invoke();
		int retValAsInt = splitData.getRetValAsInt();
		for(int i=0;i<retValAsInt;i++)
		{
			OriginalData originalData=new OriginalData();
			originalData.setVelueFromPointer(ipPtroriginal,i*originalData.getSizeOf(),0,originalData.getSizeOf());
			
			OriginalData valueFromPointer = originalData.getValueFromPointer();
//			//System.out.println(valueFromPointer);
			originalDataList.add(valueFromPointer);
		}
		return retValAsInt;
	}
	/**
	 * 分析校时命令.
	origData	OriginalData	[输入]	由SplitData函数返回的校时命令结构体
	@return 	设备地址： >0.
	数据错误：-1.
	数据长度不足：-2.

	 * @throws Exception
	 * INT32 AnalyTiming(OriginalData origData);
	 */
//	public static int AnalyTiming(OriginalData origData) throws Exception
//	{
//		// 设置参数
//		analyTiming.setRetVal(Type.INT);   
//		analyTiming.setParameter(0, origData.getPointer());
//		//        nGetSystemTime.setParameter(0, systemTime.getPointer());
//		analyTiming.invoke();
//		return analyTiming.getRetValAsInt();
//	}
	public static short AnalyTimingSharp(byte[] byData) throws Exception
	{
//		byte bss=new byte()[255,255,255,255,203,182,193,2,0,6,71,176,192,6,0,55,188,243,1,12,187,71,161,121,237,0,0,0,1,0,0,0,204,243,1,12,157,71,161,121,116,192,62,1,0,0,0,0,240,243,1,12,229,252,160,121,0,0,0,0,252,161,62,1,100,91,62,1,0,0,0,0,128,192,62,1,100,91,62,1,0,0,0,0,16,244,1,12,52,8,160,121,100,91,62,1,255,255,255,255,116,192,62,1,12,0,0,0,116,192,62,1,116,192,62,1,108,244,1,12,113,6,160,121,80,93,62,1,134,6,160,121,4,70,17,212,100,91,62,1,236,244,1,12,0,0,0,0,0,0,0,0,15,0,0,0,15,0,0,0,0,164,36,3,252,161,62,1,72,14,88,1,132,222,5,121,96,244,1,12,0,0,0,0,80,93,62,1,100,91,62,1,0,0,0,0,176,244,1,12,232,100,172,121,255,255,255,255,188,244,1,12,240,7,160,121,188,244,1,12,177,94,181,121,182,94,181,121,212,70,17,212,28,249,1,12,236,244,1,12,72,14,88,1,72,14,88,1,116,192,62,1,6,0,0,0];
//		Bytes bytes = new Bytes(byData);
//		//System.out.println("byData:"+byData.length);
//		//System.out.println("byData:"+Arrays.toString(byData));
		Pointer pointer = new Pointer(MemoryBlockFactory.createMemoryBlock(byData.length));
		for(int i=0;i<byData.length;i++)
		{
			pointer.setByteAt(i, byData[i]);
		}
		// 设置参数
		analyTimingSharp.setRetVal(Type.INT);
		analyTimingSharp.setParameter(0, pointer);
//		analyTiming.setParameter(0, bytes.getPointer());
		//        nGetSystemTime.setParameter(0, systemTime.getPointer());
		analyTimingSharp.invoke();
		int retValAsInt = analyTimingSharp.getRetValAsInt();
//		//System.out.println("ddd:"+retValAsInt);
		return (short) retValAsInt;
	}
	/**
	 * 分析标签数据命令
origData	OriginalData	[输入]	由SplitData函数返回的标签数据类型的OriginalData结构体
recordList	void *	[输出]	sutTagRecord指针，存放解析出来的标签信息数据
nBufferSize	UINT32	[输入]	2参数的大小，请至少分配30个
	 * @return 
@return 标签数据个数
	 * @throws Exception
	 * INT32 AnalyTagData(OriginalData origData,void * recordList,UINT32 nBufferSize);
	 */
//	public static Map<Integer,Object> AnalyTagData(OriginalData origData) throws Exception
//	{
//		SutTagRecord sutTagRecord=new SutTagRecord();
//		analyTagData.setRetVal(Type.INT);
//		int i=0;
//		analyTagData.setParameter(i++, origData.getPointer());
//		analyTagData.setParameter(i++, sutTagRecord.getPointer());
//		analyTagData.setParameter(i++, Type.INT,""+origData.getSizeOf());
//		analyTagData.invoke();
//		Map<Integer, Object> res = new HashMap<Integer, Object>();
//		res.put(0, analyTagData.getRetValAsInt());
//		res.put(1, sutTagRecord);
//		return res;
//	}
	public static int AnalyTagDataSharp(byte[] byData,List<SutTagRecord> sutTagRecordList) throws Exception
	{
		if(sutTagRecordList==null)
			return -2;
		SutTagRecord s0=new SutTagRecord();
		Pointer ipPtroriginal = new Pointer(MemoryBlockFactory.createMemoryBlock(s0.getSizeOf()*32));
		Bytes bytes = new Bytes(byData);
		analyTagDataSharp.setRetVal(Type.INT);
		int i=0;
		analyTagDataSharp.setParameter(i++, bytes.getPointer());
		analyTagDataSharp.setParameter(i++, ipPtroriginal);
		analyTagDataSharp.setParameter(i++, Type.INT,""+32);
		analyTagDataSharp.invoke();
		int retValAsInt = analyTagDataSharp.getRetValAsInt();
		for(int k=0;k<retValAsInt;k++)
		{
			SutTagRecord sutTagRecord=new SutTagRecord();
			sutTagRecord.setVelueFromPointer(ipPtroriginal, k*sutTagRecord.getSizeOf(), 0, sutTagRecord.getSizeOf());
			sutTagRecordList.add(sutTagRecord.getValueFromPointer());
		}
		return retValAsInt;
	}
	/**
	 * 分析设备心跳数据命令.
origData	OriginalData	[输入]	由SplitData函数返回的心跳数据类型的OriginalData结构体
recordList	void *	[输出]	sutTagRecord指针，存放解析出来的心跳信息数据
	 * @return 
		@return 心跳数据个数
	 * @throws Exception
	 * INT32 AnalyHeartData(OriginalData origData,void * recordList,UINT32 nBufferSize);
	 */
//	public static Map<Integer, Object> AnalyHeartData(OriginalData origData) throws Exception
//	{
//		SutHeartRecord sutHeartRecord=new SutHeartRecord();
//		analyHeartData.setRetVal(Type.INT);
//		int i=0;
//		analyHeartData.setParameter(i++, origData.getPointer());
//		analyHeartData.setParameter(i++, sutHeartRecord.getPointer());
//		analyHeartData.invoke();
//		Map<Integer, Object> res = new HashMap<Integer, Object>();
//		res.put(0, analyHeartData.getRetValAsInt());
//		res.put(1, sutHeartRecord);
//		return res;
//	}
	public static int AnalyHeartDataSharp(byte[] byData,List<SutHeartRecord> sutHeartRecordList) throws Exception
	{
		Bytes bytes = new Bytes(byData);
		SutHeartRecord s0=new SutHeartRecord();
		Pointer ipPtroriginal = new Pointer(MemoryBlockFactory.createMemoryBlock(s0.getSizeOf()*32));
		analyHeartDataSharp.setRetVal(Type.INT);
		int i=0;
		analyHeartDataSharp.setParameter(i++, bytes.getPointer());
		analyHeartDataSharp.setParameter(i++, ipPtroriginal);
		analyHeartDataSharp.setParameter(i++, Type.INT,""+32);
		analyHeartDataSharp.invoke();
		int retValAsInt = analyHeartDataSharp.getRetValAsInt();
		for(int k=0;k<retValAsInt;k++)
		{
			SutHeartRecord sutHeartRecord=new SutHeartRecord();
			sutHeartRecord.setVelueFromPointer(ipPtroriginal, k*sutHeartRecord.getSizeOf(), 0, sutHeartRecord.getSizeOf());
			sutHeartRecordList.add(sutHeartRecord.getValueFromPointer());
		}
		return retValAsInt;
	}
	/**
	 * 构成回复设备校时的命令.
nReaderAddress	UINT16	[输入]	AnalyTiming 函数的返回值
byData	BYTE *	[输出]	合成的命令，请至少申请24个字节
	 * @return 

	 * @throws Exception
	 * Void RespondTimingCommand(UINT16 nReaderAddress,BYTE *byData);
	 */
	public static byte[] RespondTimingCommand(short nReaderAddress) throws Exception
	{
		Pointer pointer2 = new Pointer(MemoryBlockFactory.createMemoryBlock(24));
		int i=0;
		respondTimingCommand.setParameter(i++, Type.INT,""+nReaderAddress);
		respondTimingCommand.setParameter(i++, pointer2.getPointer());
		respondTimingCommand.invoke();
		return pointer2.getMemory();
	}
	/**
	 * 构成回复标签数据的命令.
nReaderAddress	UINT16	[输入]	AnalyTiming 函数的返回值
byTagNumber	BYTE	[输入]	AnalyTagData函数的返回值
byData	BYTE *	[输出]	合成的命令，请至少申请24个字节
	 * @return 

	 * @throws Exception
	 * void RespondTagRecordCommand(UINT16 nReaderAddress,BYTE byTagNumber,BYTE *byData )
	 */
	public static byte[] RespondTagRecordCommand(int nDeviceAddress,int analyTagDataSharpNum) throws Exception
	{
		Pointer pointer2 = new Pointer(MemoryBlockFactory.createMemoryBlock(24));
		
		int i=0;
		respondTagRecordCommand.setParameter(i++,Type.INT, ""+nDeviceAddress);
		respondTagRecordCommand.setParameter(i++,Type.INT, ""+analyTagDataSharpNum);
		respondTagRecordCommand.setParameter(i++, pointer2.getPointer());
		respondTagRecordCommand.invoke();
		return pointer2.getMemory();
	}
	/**
	 * 构成回复设备心跳数据的命令.
nReaderAddress	UINT16	[输入]	AnalyTiming  函数的返回值
byData	BYTE *	[输出]	合成的命令，请至少申请24个字节
	 * @return 

	 * @throws Exception
	 * void RespondHeartCommand(UINT16 nReaderAddress,BYTE *byData);
	 */
	public static byte[] RespondHeartCommand(short nReaderAddress) throws Exception
	{
		Pointer pointer2 = new Pointer(MemoryBlockFactory.createMemoryBlock(24));
		int i=0;
		respondHeartCommand.setParameter(i++,Type.INT, ""+nReaderAddress);
		respondHeartCommand.setParameter(i++, pointer2.getPointer());
//		respondHeartCommand.setParameter(i++, bytes.getPointer());
		respondHeartCommand.invoke();
		return pointer2.getMemory();
	}
	public static void main(String[] args) throws Exception {
		int[] is=new int[]{255,255,255,255,203,182,193,2,0,6,71,176,192,6,0,55,164,239,200,13,187,71,161,121,237,0,0,0,1,0,0,0,180,239,200,13,157,71,161,121,116,192,46,1,0,0,0,0,216,239,200,13,229,252,160,121,0,0,0,0,252,161,46,1,100,91,46,1,0,0,0,0,128,192,46,1,100,91,46,1,0,0,0,0,248,239,200,13,52,8,160,121,100,91,46,1,255,255,255,255,116,192,46,1,12,0,0,0,116,192,46,1,116,192,46,1,84,240,200,13,113,6,160,121,92,93,46,1,134,6,160,121,187,7,120,108,100,91,46,1,212,240,200,13,0,0,0,0,96,240,200,13,11,223,5,121,196,80,34,3,88,132,35,3,252,161,46,1,8,244,72,1,144,56,159,121,12,245,200,13,0,0,0,0,92,93,46,1,100,91,46,1,0,0,0,0,152,240,200,13,232,100,172,121,255,255,255,255,164,240,200,13,240,7,160,121,164,240,200,13,177,94,181,121,182,94,181,121,75,7,120,108,12,245,200,13,212,240,200,13,8,244,72,1,8,244,72,1,116,192,46,1,6,0,0,0};
		byte[] bss=new byte[is.length];
		for (int i=0; i<is.length; i++) {
			bss[i]=(byte) is[i];
		}
		byte s=(byte) 255;
//		//System.out.println(s);
//		//System.out.println(AnalyTimingSharp(bss));
//		//System.out.println(Arrays.toString(RespondHeartCommand(55)));
	}
}
