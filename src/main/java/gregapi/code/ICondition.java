/**
 * Copyright (c) 2025 GregTech-6 Team
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
 */
public interface ICondition<O> {
	public static final ICondition<?> TRUE = new True<>();
	public static final ICondition<?> FALSE = new False<>();
	public static final ICondition<?> NULL = new Null<>();
	public static final ICondition<?> NOTNULL = new NotNull<>();
	
	/**
	 * @param aObject the Object to check the Condition on
	 * @return if the Condition matches
	 */
	public boolean isTrue(O aObject);
	
	// Utility Classes for adding relations between Conditions.
	
	public static class Not<O> implements ICondition<O> {
		private final ICondition<O> mCondition;
		
		public Not(ICondition<O> aCondition) {
			mCondition = aCondition;
		}
		
		@Override
		public boolean isTrue(O aObject) {
			return !mCondition.isTrue(aObject);
		}
	}
	
	public static class Or<O> implements ICondition<O> {
		private final ICondition<O>[] mConditions;
		public final Not<O> NOT = new Not<>(this);
		
		@SafeVarargs
		public Or(ICondition<O>... aConditions) {
			mConditions = aConditions;
		}
		
		@Override
		public boolean isTrue(O aObject) {
			for (ICondition<O> tCondition : mConditions) if (tCondition.isTrue(aObject)) return true;
			return false;
		}
	}
	
	public static class Nor<O> implements ICondition<O> {
		private final ICondition<O>[] mConditions;
		public final Not<O> NOT = new Not<>(this);
		
		@SafeVarargs
		public Nor(ICondition<O>... aConditions) {
			mConditions = aConditions;
		}
		
		@Override
		public boolean isTrue(O aObject) {
			for (ICondition<O> tCondition : mConditions) if (tCondition.isTrue(aObject)) return false;
			return true;
		}
	}
	
	public static class And<O> implements ICondition<O> {
		private final ICondition<O>[] mConditions;
		public final Not<O> NOT = new Not<>(this);
		
		@SafeVarargs
		public And(ICondition<O>... aConditions) {
			mConditions = aConditions;
		}
		
		@Override
		public boolean isTrue(O aObject) {
			for (ICondition<O> tCondition : mConditions) if (!tCondition.isTrue(aObject)) return false;
			return true;
		}
	}
	
	public static class Nand<O> implements ICondition<O> {
		private final ICondition<O>[] mConditions;
		public final Not<O> NOT = new Not<>(this);
		
		@SafeVarargs
		public Nand(ICondition<O>... aConditions) {
			mConditions = aConditions;
		}
		
		@Override
		public boolean isTrue(O aObject) {
			for (ICondition<O> tCondition : mConditions) if (!tCondition.isTrue(aObject)) return true;
			return false;
		}
	}
	
	public static class Xor<O> implements ICondition<O> {
		private final ICondition<O> mCondition1, mCondition2;
		public final Not<O> NOT = new Not<>(this);
		
		public Xor(ICondition<O> aCondition1, ICondition<O> aCondition2) {
			mCondition1 = aCondition1;
			mCondition2 = aCondition2;
		}
		
		@Override
		public boolean isTrue(O aObject) {
			return mCondition1.isTrue(aObject) != mCondition2.isTrue(aObject);
		}
	}
	
	public static class Equal<O> implements ICondition<O> {
		private final ICondition<O> mCondition1, mCondition2;
		public final Not<O> NOT = new Not<>(this);
		
		public Equal(ICondition<O> aCondition1, ICondition<O> aCondition2) {
			mCondition1 = aCondition1;
			mCondition2 = aCondition2;
		}
		
		@Override
		public boolean isTrue(O aObject) {
			return mCondition1.isTrue(aObject) == mCondition2.isTrue(aObject);
		}
	}
	
	static class True <O>   implements ICondition<O> {True ()   {/**/} @Override public boolean isTrue(O aObject) {return true ;}}
	static class False<O>   implements ICondition<O> {False()   {/**/} @Override public boolean isTrue(O aObject) {return false;}}
	static class Null <O>   implements ICondition<O> {Null()    {/**/} @Override public boolean isTrue(O aObject) {return aObject == null;}}
	static class NotNull<O> implements ICondition<O> {NotNull() {/**/} @Override public boolean isTrue(O aObject) {return aObject != null;}}
}
