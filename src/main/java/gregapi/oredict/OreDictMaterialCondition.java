/**
 * Copyright (c) 2024 GregTech-6 Team
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

package gregapi.oredict;

import gregapi.code.ICondition;

import static gregapi.data.CS.U;

/**
 * @author Gregorius Techneticies
 * 
 * A Collection of Classes, which check for certain Material Conditions.
 */
public class OreDictMaterialCondition {
	public static ICondition<OreDictMaterial> selfcrush()                       {return SelfCrush.INSTANCE;}
	public static ICondition<OreDictMaterial> fullpulver()                      {return FullPulver.INSTANCE;}
	public static ICondition<OreDictMaterial> selfforge()                       {return SelfForge.INSTANCE;}
	public static ICondition<OreDictMaterial> fullforge()                       {return FullForge.INSTANCE;}
	public static ICondition<OreDictMaterial> meltmin   (long aMeltingPoint)    {return new MeltingPointMin(aMeltingPoint);}
	public static ICondition<OreDictMaterial> meltmax   (long aMeltingPoint)    {return new MeltingPointMax(aMeltingPoint);}
	public static ICondition<OreDictMaterial> boilmin   (long aBoilingPoint)    {return new BoilingPointMin(aBoilingPoint);}
	public static ICondition<OreDictMaterial> boilmax   (long aBoilingPoint)    {return new BoilingPointMax(aBoilingPoint);}
	public static ICondition<OreDictMaterial> plasmin   (long aPlasmaPoint)     {return new PlasmaPointMin(aPlasmaPoint);}
	public static ICondition<OreDictMaterial> plasmax   (long aPlasmaPoint)     {return new PlasmaPointMax(aPlasmaPoint);}
	public static ICondition<OreDictMaterial> qualmin   (long aMinQuality)      {return new QualityMin(aMinQuality);}
	public static ICondition<OreDictMaterial> qualmax   (long aMaxQuality)      {return new QualityMax(aMaxQuality);}
	public static ICondition<OreDictMaterial> typemin   (long aMinQuality)      {return new TypeMin(aMinQuality);}
	public static ICondition<OreDictMaterial> typemax   (long aMaxQuality)      {return new TypeMax(aMaxQuality);}
	
	private static class SelfCrush implements ICondition<OreDictMaterial> {
		public static final SelfCrush INSTANCE = new SelfCrush();
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mTargetCrushing.mMaterial == aMaterial;}
	}
	
	private static class FullPulver implements ICondition<OreDictMaterial> {
		public static final FullPulver INSTANCE = new FullPulver();
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mTargetPulver.mAmount >= U;}
	}
	
	private static class SelfForge implements ICondition<OreDictMaterial> {
		public static final SelfForge INSTANCE = new SelfForge();
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mTargetForging.mMaterial == aMaterial;}
	}
	
	private static class FullForge implements ICondition<OreDictMaterial> {
		public static final FullForge INSTANCE = new FullForge();
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mTargetForging.mAmount >= U;}
	}
	
	private static class TypeMin implements ICondition<OreDictMaterial> {
		private final long mMinType;
		public TypeMin(long aMinType) {mMinType = aMinType;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mToolTypes >= mMinType;}
	}
	
	private static class TypeMax implements ICondition<OreDictMaterial> {
		private final long mMaxType;
		public TypeMax(long aMaxType) {mMaxType = aMaxType;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mToolTypes <= mMaxType;}
	}
	
	private static class QualityMin implements ICondition<OreDictMaterial> {
		private final long mMinQuality;
		public QualityMin(long aMinQuality) {mMinQuality = aMinQuality;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mToolQuality >= mMinQuality;}
	}
	
	private static class QualityMax implements ICondition<OreDictMaterial> {
		private final long mMaxQuality;
		public QualityMax(long aMaxQuality) {mMaxQuality = aMaxQuality;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mToolQuality <= mMaxQuality;}
	}
	
	private static class MeltingPointMin implements ICondition<OreDictMaterial> {
		private final long mMeltingPoint;
		public MeltingPointMin(long aMeltingPoint) {mMeltingPoint = aMeltingPoint;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mMeltingPoint >= mMeltingPoint;}
	}
	
	private static class MeltingPointMax implements ICondition<OreDictMaterial> {
		private final long mMeltingPoint;
		public MeltingPointMax(long aMeltingPoint) {mMeltingPoint = aMeltingPoint;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mMeltingPoint <= mMeltingPoint;}
	}
	
	private static class BoilingPointMin implements ICondition<OreDictMaterial> {
		private final long mBoilingPoint;
		public BoilingPointMin(long aBoilingPoint) {mBoilingPoint = aBoilingPoint;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mBoilingPoint >= mBoilingPoint;}
	}
	
	private static class BoilingPointMax implements ICondition<OreDictMaterial> {
		private final long mBoilingPoint;
		public BoilingPointMax(long aBoilingPoint) {mBoilingPoint = aBoilingPoint;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mBoilingPoint <= mBoilingPoint;}
	}
	
	private static class PlasmaPointMin implements ICondition<OreDictMaterial> {
		private final long mPlasmaPoint;
		public PlasmaPointMin(long aPlasmaPoint) {mPlasmaPoint = aPlasmaPoint;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mPlasmaPoint >= mPlasmaPoint;}
	}
	
	private static class PlasmaPointMax implements ICondition<OreDictMaterial> {
		private final long mPlasmaPoint;
		public PlasmaPointMax(long aPlasmaPoint) {mPlasmaPoint = aPlasmaPoint;}
		@Override public boolean isTrue(OreDictMaterial aMaterial) {return aMaterial.mPlasmaPoint <= mPlasmaPoint;}
	}
}
