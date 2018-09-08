package gregapi.code;

import gregapi.util.UT;


/**
 * @author Gregorius Techneticies
 */
public class ObjectStack<E> {
	public E mObject;
	public long mAmount;
	
	public ObjectStack(E aObject, long aAmount) {
		mObject = aObject;
		mAmount = aAmount;
	}
	
	public int amountInt() {return UT.Code.bindInt(mAmount);}
	public short amountShort() {return UT.Code.bindShort(mAmount);}
	public byte amountByte() {return UT.Code.bindByte(mAmount);}
	
	@Override
	public boolean equals(Object aCompared) {
		return aCompared instanceof ObjectStack && ((ObjectStack)aCompared).mObject == mObject;
	}
	
	@Override
	public int hashCode() {
		return mObject != null ? mObject.hashCode() : 0;
	}
}