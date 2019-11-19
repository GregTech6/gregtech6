/**
 * Copyright (c) 2019 Gregorius Techneticies
 *
 * This file is part of GregTech.
 *
 * GregTech is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GregTech is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GregTech. If not, see <http://www.gnu.org/licenses/>.
 */

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
		return aCompared instanceof ObjectStack && ((ObjectStack<?>)aCompared).mObject == mObject;
	}
	
	@Override
	public int hashCode() {
		return mObject != null ? mObject.hashCode() : 0;
	}
}
