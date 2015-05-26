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
public class Bytes extends CZAbstractBasicData<Bytes> {

//	public Bytes() throws NativeException {
//		super(null);
//		datas=new byte[24];
//		createPointer();
//	}
	private byte[] datas;	//256

	public Bytes(byte[] _datas) throws NativeException {
		super(null);
		datas=_datas;
		createPointer();
	}
	public Bytes(int size) throws NativeException {
		this(new byte[size]);
	}

	/**
	 * 分配内存，并返回指针
	 */
	public Pointer createPointer() throws NativeException {
		pointer = new Pointer(MemoryBlockFactory.createMemoryBlock(getSizeOf()));
		for (int i=0;i<datas.length;i++) {
			this.pointer.setByteAt(i, datas[i]);
		}
		return pointer;
	}

	/**
	 * 内存大小
	 */
	public int getSizeOf() {
		return datas.length;
	}

	/**
	 * 获取通过内存指针解析出结果
	 */
	public Bytes getValueFromPointer() throws NativeException {
		for(int i=0;i<datas.length;i++)
		{
			datas[i]=getNextByte();
		}
		return this;
	}

	public byte[] getDatas() {
		return datas;
	}

	@Override
	public String toString() {
		return "Bytes [datas=" + Arrays.toString(datas) + "]";
	}


}