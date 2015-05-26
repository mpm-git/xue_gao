package per.cz.jnative.struct;

import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.AbstractBasicData;
import org.xvolks.jnative.pointers.Pointer;

public abstract class CZAbstractBasicData<T> extends AbstractBasicData<T> {

	protected CZAbstractBasicData(T lValue) {
		super(lValue);
	}

	public void setVelueFromPointer(Pointer p,int fromOffset,int toOffset,int length) throws NativeException
	{
		for(int i=0;i<length;i++)
			pointer.setByteAt(toOffset+i, p.getAsByte(fromOffset+i));
	}
}
