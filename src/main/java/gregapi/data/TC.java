/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregapi.data;

import java.util.List;

public class TC {
	public static final TC ORDO = new TC("Ordo"), PERDITIO = new TC("Perditio"), TERRA = new TC("Terra"), AQUA = new TC("Aqua"), AER = new TC("Aer"), IGNIS = new TC("Ignis")
	
	// Tier 1
	, LUX          = new TC(AER         , IGNIS       , "Lux")
	, TEMPESTAS    = new TC(AER         , AQUA        , "Tempestas")
	, MOTUS        = new TC(AER         , ORDO        , "Motus")
	, VACUOS       = new TC(AER         , PERDITIO    , "Vacuos")
	, GELUM        = new TC(IGNIS       , PERDITIO    , "Gelum")
	, POTENTIA     = new TC(IGNIS       , ORDO        , "Potentia")
	, VITREUS      = new TC(TERRA       , ORDO        , "Vitreus")
	, VICTUS       = new TC(AQUA        , TERRA       , "Victus")
	, VENENUM      = new TC(AQUA        , PERDITIO    , "Venemum")
	, PERMUTATIO   = new TC(ORDO        , PERDITIO    , "Permutatio")
	
	// Tier 2
	, VOLATUS      = new TC(MOTUS       , AER         , "Volatus")
	, VINCULUM     = new TC(MOTUS       , PERDITIO    , "Vinculum")
	, ITER         = new TC(MOTUS       , TERRA       , "Iter")
	, METALLUM     = new TC(VITREUS     , TERRA       , "Metallum")
	, HERBA        = new TC(VICTUS      , TERRA       , "Herba"), GRANUM = HERBA
	, LIMUS        = new TC(VICTUS      , AQUA        , "Limus")
	, SANO         = new TC(VICTUS      , ORDO        , "Sano")
	, MORTUUS      = new TC(VICTUS      , PERDITIO    , "Mortuus")
	, BESTIA       = new TC(VICTUS      , MOTUS       , "Bestia")
	, FAMES        = new TC(VICTUS      , VACUOS      , "Fames")
	, TENEBRAE     = new TC(LUX         , VACUOS      , "Tenebrae")
	, PRAECANTIO   = new TC(POTENTIA    , VACUOS      , "Praecantio")
	, REFLEXIO     = new TC(POTENTIA    , PERMUTATIO  , "Reflexio")
	, RADIO        = new TC(POTENTIA    , LUX         , "Radio")
	
	// Tier 3
	, ARBOR        = new TC(HERBA       , AER         , "Arbor")
	, AURAM        = new TC(PRAECANTIO  , AER         , "Auram")
	, VITIUM       = new TC(PRAECANTIO  , PERDITIO    , "Vitium")
	, SPIRITUS     = new TC(MORTUUS     , VICTUS      , "Spiritus")
	, EXAMINIS     = new TC(MORTUUS     , MOTUS       , "Examinis")
	, ALIENIS      = new TC(TENEBRAE    , VACUOS      , "Alienis")
	, CORPUS       = new TC(BESTIA      , MORTUUS     , "Corpus")
	, MAGNETO      = new TC(ITER        , METALLUM    , "Magneto")
	
	// Tier 4
	, SENSUS       = new TC(SPIRITUS    , AER         , "Sensus")
	, COGNITIO     = new TC(SPIRITUS    , IGNIS       , "Cognitio"), COGNITO = COGNITIO
	
	// Tier 5
	, STRONTIO     = new TC(COGNITIO    , PERDITIO    , "Strontio")
	, HUMANUS      = new TC(COGNITIO    , BESTIA      , "Humanus")
	
	// Tier 6
	, PERFODIO     = new TC(HUMANUS     , TERRA       , "Perfodio")
	, INSTRUMENTUM = new TC(HUMANUS     , ORDO        , "Instrumentum")
	, MESSIS       = new TC(HUMANUS     , HERBA       , "Messis")
	, LUCRUM       = new TC(HUMANUS     , FAMES       , "Lucrum")
	
	// Tier 7
	, TELUM        = new TC(INSTRUMENTUM, IGNIS       , "Telum")
	, TUTAMEN      = new TC(INSTRUMENTUM, TERRA       , "Tutamen")
	, PANNUS       = new TC(INSTRUMENTUM, BESTIA      , "Pannus")
	, FABRICO      = new TC(INSTRUMENTUM, HUMANUS     , "Fabrico")
	, METO         = new TC(INSTRUMENTUM, MESSIS      , "Meto")
	, MACHINA      = new TC(INSTRUMENTUM, MOTUS       , "Machina")
	, NEBRISUM     = new TC(PERFODIO    , LUCRUM      , "Nebrisum")
	
	// Tier 8
	, ELECTRUM     = new TC(MACHINA     , POTENTIA    , "Electrum")
	;
	
	public TC(String aName) {
		mName = aName;
		mComponentA = this;
		mComponentB = this;
		mTier = 0;
	}
	public TC(TC aComponentA, TC aComponentB, String aName) {
		mName = aName;
		mComponentA = aComponentA;
		mComponentB = aComponentB;
		mTier = (byte)(1+Math.max(mComponentA.mTier, mComponentB.mTier));
	}
	
	public String mName;
	public TC mComponentA, mComponentB;
	public byte mTier;
	/** The ThaumCraft Aspect Object of the Mod itself. */
	public Object mAspect;
	
	public static TC_AspectStack stack(TC aAspect, long aAmount) {
		return new TC_AspectStack(aAspect, aAmount);
	}
	
	public TC_AspectStack get(long aAmount) {return new TC_AspectStack(this, aAmount);}
	
	public static class TC_AspectStack {
		public TC mAspect;
		public long mAmount;
		
		public TC_AspectStack(TC aAspect, long aAmount) {
			mAspect = aAspect;
			mAmount = aAmount;
		}
		
		public TC_AspectStack copy() {
			return new TC_AspectStack(mAspect, mAmount);
		}
		
		public TC_AspectStack copy(long aAmount) {
			return new TC_AspectStack(mAspect, aAmount);
		}
		
		public List<TC_AspectStack> addToAspectList(List<TC_AspectStack> aList) {
			if (mAmount <= 0) return aList;
			for (TC_AspectStack tAspect : aList) if (tAspect.mAspect == mAspect || (tAspect.mAspect.mAspect != null && tAspect.mAspect.mAspect == mAspect.mAspect)) {tAspect.mAmount += mAmount; return aList;}
			aList.add(copy());
			return aList;
		}
		
		public boolean removeFromAspectList(List<TC_AspectStack> aList) {
			for (TC_AspectStack tAspect : aList) if (tAspect.mAspect == mAspect || (tAspect.mAspect.mAspect != null && tAspect.mAspect.mAspect == mAspect.mAspect)) {
				if (tAspect.mAmount >= mAmount) {
					tAspect.mAmount -= mAmount;
					if (tAspect.mAmount == 0) aList.remove(tAspect);
					return true;
				}
			}
			return false;
		}
	}
}
