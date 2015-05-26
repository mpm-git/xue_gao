package per.cz.jnative.struct;

import java.util.Arrays;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.AbstractBasicData;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;


/**
		DataType  type;//数据类型
		BYTE 	 byData[256];//原始数据
		INT32	 nLength;//数据长度

 */
public class OriginalData extends CZAbstractBasicData<OriginalData> {

    public int type;		//4
    public byte[] byData;	//256
    public int nLength;		//4
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
        return 264;
    }

    /**
     * 获取通过内存指针解析出结果
     */
    public OriginalData getValueFromPointer() throws NativeException {
    	type=getNextInt();
    	byData=new byte[256];
    	for(int i=0;i<256;i++)
    	{
    		byData[i]=getNextByte();
    	}
    	nLength=getNextInt();
    	return this;
    }

    public OriginalData() throws NativeException {
        super(null);
        createPointer();
    }

	public String toString() {
		return "OriginalData [type=" + type + ", byData="
				+ Arrays.toString(byData) + ", nLength=" + nLength + "]";
	}
}