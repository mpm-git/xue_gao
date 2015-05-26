package per.cz.jnative;

import java.util.Arrays;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.FreeDiskSpace;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

public class Test {
	public static final FreeDiskSpace getDiskFreeSpaceEx(String drive)
			throws NativeException, IllegalAccessException {
		if (drive == null)
			throw new NullPointerException("The drive name cannot be null !");
		Pointer lpFreeBytesAvailable = new Pointer(MemoryBlockFactory.createMemoryBlock(24));
		int i = 0;
		JNative fs = new JNative("Kernel32.dll", "GetDiskFreeSpaceExA");
		fs.setRetVal(Type.INT);
		fs.setParameter(i++, Type.STRING, drive);
		fs.setParameter(i++, lpFreeBytesAvailable.getPointer());
		fs.setParameter(i++, lpFreeBytesAvailable.getPointer() + 8);
		fs.setParameter(i++, lpFreeBytesAvailable.getPointer() + 16);
		fs.invoke();
		//System.out.println(fs.getRetValAsInt());
//		FreeDiskSpace dsp = new FreeDiskSpace(drive, lpFreeBytesAvailable);
		lpFreeBytesAvailable.dispose();
		return null;
	}
	public static void main(String[] args) throws IllegalAccessException, NativeException, InterruptedException {
//		getDiskFreeSpaceEx("d");
		Test.getDiskFreeSpaceEx("D");
		System.load("F://dll//DataMergeAndAnaly//DataMergeAndAnaly.dll");
		//System.out.println(JNative.getCurrentModule());
//		//System.out.println(JNative.getNativeSideVersion());
		//System.out.println(Arrays.toString(JNative.getDLLFileExports("F://dll//DataMergeAndAnaly//DataMergeAndAnaly.dll")));
	}
}
