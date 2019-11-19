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

/**
 * @author Gregorius Techneticies
 * 
 * Very unfinished!
 */
public interface IMath<O> {
	public static final IMath<?> ZERO = new Val<>(0L);
	public static final IMath<?> ONE  = new Val<>(1L);
	
	/**
	 * @param aObject the Object to check the Maths on.
	 * @return the result of the Maths done by this Object
	 */
	public long getLong(O aObject);
	
	/**
	 * @param aObject the Object to check the Maths on.
	 * @return the result of the Maths done by this Object as double. This also means that the calculation CAN be more precise than the Long variant.
	 */
	public double getDouble(O aObject);
	
	// Utility Classes for adding relations between Numbers.
	
	public static class Val<O> implements IMath<O> {
		private final long mValueLong;
		private final double mValueDouble;
		public Val(long aValue) {mValueLong = aValue; mValueDouble = aValue;}
		public Val(double aValue) {mValueLong = (long)aValue; mValueDouble = aValue;}
		@Override public long getLong(O aObject) {return mValueLong;}
		@Override public double getDouble(O aObject) {return mValueDouble;}
	}
	
	public static class Neg<O> implements IMath<O> {
		private final IMath<O> mValue;
		public Neg(IMath<O> aValue) {mValue = aValue;}
		@Override public long getLong(O aObject) {return -mValue.getLong(aObject);}
		@Override public double getDouble(O aObject) {return -mValue.getDouble(aObject);}
	}
	
	public static class Ter<O> implements IMath<O> {
		private final ICondition<O> mCondition;
		private final IMath<O> mValueTrue, mValueFalse;
		public Ter(ICondition<O> aCondition, IMath<O> aValueTrue, IMath<O> aValueFalse) {mCondition = aCondition; mValueTrue = aValueTrue; mValueFalse = aValueFalse;}
		@Override public long getLong(O aObject) {return mCondition.isTrue(aObject) ? mValueTrue.getLong(aObject) : mValueFalse.getLong(aObject);}
		@Override public double getDouble(O aObject) {return mCondition.isTrue(aObject) ? mValueTrue.getDouble(aObject) : mValueFalse.getDouble(aObject);}
	}
	
	public static class Sum<O> implements IMath<O> {
		private final IMath<O>[] mValues;
		@SafeVarargs
		public Sum(IMath<O>... aValues) {mValues = aValues;}
		
		@Override
		public long getLong(O aObject) {
			long rValue = 0;
			for (IMath<O> tValue : mValues) rValue += tValue.getLong(aObject);
			return rValue;
		}
		
		@Override
		public double getDouble(O aObject) {
			double rValue = 0;
			for (IMath<O> tValue : mValues) rValue += tValue.getDouble(aObject);
			return rValue;
		}
	}
	
	public static class Mul<O> implements IMath<O> {
		private final IMath<O>[] mValues;
		@SafeVarargs
		public Mul(IMath<O>... aValues) {mValues = aValues;}
		
		@Override
		public long getLong(O aObject) {
			long rValue = 0;
			for (IMath<O> tValue : mValues) rValue *= tValue.getLong(aObject);
			return rValue;
		}
		
		@Override
		public double getDouble(O aObject) {
			double rValue = 0;
			for (IMath<O> tValue : mValues) rValue *= tValue.getDouble(aObject);
			return rValue;
		}
	}
}
