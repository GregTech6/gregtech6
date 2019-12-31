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

package gregapi.data;

import java.util.List;

public class TC {
	public static final TC AER = new TC("Aer"), ALIENIS = new TC("Alienis"), AQUA = new TC("Aqua"), ARBOR = new TC("Arbor"), AURAM = new TC("Auram"), BESTIA = new TC("Bestia"), COGNITO = new TC("Cognito"), CORPUS = new TC("Corpus"), ELECTRUM = new TC("Electrum"), EXAMINIS = new TC("Examinis"), FABRICO = new TC("Fabrico"), FAMES = new TC("Fames"), GELUM = new TC("Gelum"), GRANUM = new TC("Granum"), HERBA = new TC("Herba"), HUMANUS = new TC("Humanus"), IGNIS = new TC("Ignis"), INSTRUMENTUM = new TC("Instrumentum"), ITER = new TC("Iter"), LIMUS = new TC("Limus"), LUCRUM = new TC("Lucrum"), LUX = new TC("Lux"), MACHINA = new TC("Machina"), MAGNETO = new TC("Magneto"), MESSIS = new TC("Messis"), METALLUM = new TC("Metallum"), METO = new TC("Meto"), MORTUUS = new TC("Mortuus"), MOTUS = new TC("Motus"), NEBRISUM = new TC("Nebrisum"), ORDO = new TC("Ordo"), PANNUS = new TC("Pannus"), PERDITIO = new TC("Perditio"), PERFODIO = new TC("Perfodio"), PERMUTATIO = new TC("Permutatio"), POTENTIA = new TC("Potentia"), PRAECANTIO = new TC("Praecantio"), RADIO = new TC("Radio"), REFLEXIO = new TC("Reflexio"), SANO = new TC("Sano"), SENSUS = new TC("Sensus"), SPIRITUS = new TC("Spiritus"), STRONTIO = new TC("Strontio"), TELUM = new TC("Telum"), TERRA = new TC("Terra"), TEMPESTAS = new TC("Tempestas"), TENEBRAE = new TC("Tenebrae"), TUTAMEN = new TC("Tutamen"), VACUOS = new TC("Vacuos"), VENENUM = new TC("Venemum"), VICTUS = new TC("Victus"), VINCULUM = new TC("Vinculum"), VITIUM = new TC("Vitium"), VITREUS = new TC("Vitreus"), VOLATUS = new TC("Volatus");
	
	public TC(String aName) {
		mName = aName;
	}
	
	public String mName;
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
