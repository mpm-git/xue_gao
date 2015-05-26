package per.cz.jnative.struct;

import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.AbstractBasicData;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;



/**
typedef struct sutHeartRecord {
		UINT16 	bRecordType;//0:标签记录，心跳记录
		UINT16	nDeviceAddress;//阅读器地址
		UINT32 	nTime;//时间
};
 */
public class SutHeartRecord extends CZAbstractBasicData<SutHeartRecord> {

    public short bRecordType;
    public short nDeviceAddress;
    public int nTime;
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
        return 8;
    }

    /**
     * 获取通过内存指针解析出结果
     */
    public SutHeartRecord getValueFromPointer() throws NativeException {
    	bRecordType=getNextShort();
    	nDeviceAddress=getNextShort();
    	nTime=getNextInt();
        return this;
    }

    public SutHeartRecord() throws NativeException {
        super(null);
        createPointer();
    }

    @Override
	public String toString() {
		return "sutHeartRecord [bRecordType=" + bRecordType
				+ ", nDeviceAddress=" + nDeviceAddress + ", nTime=" + nTime
				+ "]";
	}
}