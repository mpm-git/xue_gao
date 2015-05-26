package per.cz.jnative.struct;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.AbstractBasicData;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;



/**
typedef struct sutTagRecord {
	UINT32 nTagID;//标签编号
	UINT32 nDeviceAddress;//阅读器地址
	UINT32 bTagType;//0:标签记录，心跳记录	
	UINT32 nUserDefinition;//用户自定义
	UINT32 nOldTriggerID;//旧触发器地址
	UINT32 nNewTriggerID;//新触发器地址
	UINT32 nTime;//时间
	UINT32 bRSSI;//rssi值
	UINT32 bElecStatus;//电量状态true :充足false ：不足
	UINT32 bAlarm;//报警标志，true，报警，false，正常
	UINT32 nNumber;//在阅读器过滤时间内接收到的次数
};

 */
public class SutTagRecord extends CZAbstractBasicData<SutTagRecord> {

	private int nTagID;			//4
	private int nDeviceAddress;	//4
	private int bTagType;		//4
	private int bRSSI;			//4
	private int nOldTriggerID;	//4
	private int nNewTriggerID;	//4S
	private int nUserDefinition;	//4
	private int bElecStatus;		//4
	private int bAlarm;			//4
	private int nTime;			//4
	private int nNumber;			//4

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
        return 44;
    }

    /**
     * 获取通过内存指针解析出结果
     */
    public SutTagRecord getValueFromPointer() throws NativeException {
//        public byte 
    	nTagID=getNextInt();
//        public short
        nDeviceAddress=getNextInt();
        bTagType=getNextInt();
        nUserDefinition=getNextInt();
        nOldTriggerID=getNextInt();
        nNewTriggerID=getNextInt();
        nTime=getNextInt();
        bRSSI=getNextInt();
        bElecStatus=getNextInt();
        bAlarm=getNextInt();
        nNumber=getNextInt();
        return this;
    }
//    public R_sutTagRecord convert(){
//    	R_sutTagRecord r=new R_sutTagRecord();
//    	r.setbAlarm(bAlarm);
//    	r.setbElecStatus(bElecStatus);
//    	r.setbRSSI(bRSSI);
//    	r.setbTagType(bTagType);
//    	r.setnDeviceAddress(nDeviceAddress);
//    	r.setnNewTriggerID(nNewTriggerID);
//    	r.setnNumber(nNumber);
//    	r.setnOldTriggerID(nOldTriggerID);
//    	r.setnTagID(nTagID);
//    	r.setnTime(nTime*1000l);
//    	r.setnUserDefinition(nUserDefinition);
//    	return r;
//    }
    public SutTagRecord() throws NativeException {
        super(null);
        createPointer();
    }


    @Override
	public String toString() {
		return "sutTagRecord [bTagType=" + bTagType + ", nDeviceAddress="
				+ nDeviceAddress + ", nTagID=" + nTagID + ", bRSSI=" + bRSSI
				+ ", nOldTriggerID=" + nOldTriggerID + ", nNewTriggerID="
				+ nNewTriggerID + ", nUserDefinition=" + nUserDefinition
				+ ", bElecStatus=" + bElecStatus + ", bAlarm=" + bAlarm
				+ ", nTime=" + nTime + "]";
	}

	public static SutTagRecord GetSystemTime() throws NativeException, IllegalAccessException {
        // 创建对象
    	System.load("F://dll//DataMergeAndAnaly//DataMergeAndAnaly.dll");
//    	JNative nGetSystemTime = new JNative("DataMergeAndAnaly", "SplitData");
        JNative nGetSystemTime = new JNative("DataMergeAndAnaly", "AnalyTiming");
        SutTagRecord sutTagRecord = new SutTagRecord();
        // 设置参数
        nGetSystemTime.setParameter(0, sutTagRecord.getPointer());
//        nGetSystemTime.setParameter(0, systemTime.getPointer());
        nGetSystemTime.invoke();
        // 解析结构指针内容
        return sutTagRecord.getValueFromPointer();
    }

    public static void main(String[] args) throws NativeException, IllegalAccessException {
//    	//System.out.println( System.getProperty("java.library.path"));
    	System.err.println(GetSystemTime());
    }

	public int getnTagID() {
		return nTagID;
	}

	public int getnDeviceAddress() {
		return nDeviceAddress;
	}

	public int getbTagType() {
		return bTagType;
	}

	public int getbRSSI() {
		return bRSSI;
	}

	public int getnOldTriggerID() {
		return nOldTriggerID;
	}

	public int getnNewTriggerID() {
		return nNewTriggerID;
	}

	public int getnUserDefinition() {
		return nUserDefinition;
	}

	public int getbElecStatus() {
		return bElecStatus;
	}

	public int getbAlarm() {
		return bAlarm;
	}

	public int getnTime() {
		return nTime;
	}

	public int getnNumber() {
		return nNumber;
	}

}