package per.cz.jnative.struct;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.AbstractBasicData;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;


/**
enum DataType{PSD_TAG_DATA=1,PSD_HEART_DATA,PSD_TIMING_DATA}; 

PSD_TAG_DATA：标签数据。
PSD_HEART_DATA：心跳数据。
PSD_TIMING_DATA：校时数据。


 */
public class DataType extends AbstractBasicData<DataType> {

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
     * 内存大小
     */
    public int getSizeOf() {
        return 8 * 2;
    }

    /**
     * 获取通过内存指针解析出结果
     */
    public DataType getValueFromPointer() throws NativeException {
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

    public DataType() throws NativeException {
        super(null);
        createPointer();
    }

    public String toString() {
        return wYear + "/" + wMonth + "/" + wDay + " at + " + wHour + ":" + wMinute + ":" + wSecond
                + ":" + wMilliseconds;
    }

    public static DataType GetSystemTime() throws NativeException, IllegalAccessException {
        // 创建对象
    	System.load("F://dll//DataMergeAndAnaly//DataMergeAndAnaly.dll");
//    	JNative nGetSystemTime = new JNative("DataMergeAndAnaly", "SplitData");
        JNative nGetSystemTime = new JNative("DataMergeAndAnaly", "AnalyTiming");
        DataType systemTime = new DataType();
        // 设置参数
        nGetSystemTime.setParameter(0, systemTime.getPointer());
//        nGetSystemTime.setParameter(0, systemTime.getPointer());
        nGetSystemTime.invoke();
        // 解析结构指针内容
        return systemTime.getValueFromPointer();
    }

    public static void main(String[] args) throws NativeException, IllegalAccessException {
//    	//System.out.println( System.getProperty("java.library.path"));
    	System.err.println(GetSystemTime());
    }

}