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

package gregapi.config;

import static gregapi.data.CS.*;

import java.io.File;

import gregapi.api.Abstract_Mod;
import gregapi.data.CS.DirectoriesGT;
import gregapi.data.MT;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.ST;
import gregapi.util.UT;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * @author Gregorius Techneticies
 */
public class Config implements Runnable {
	public static Configuration sConfigFileIDs;
	private static boolean sIDConfigNeedsSaving = F;
	private boolean mUsesDefaultsInNames = T;
	public boolean mSaveOnEdit = T;
	
	public static int addIDConfig(Object aCategory, String aName, int aDefault) {
		if (UT.Code.stringInvalid(aName)) return aDefault;
		Property tProperty = sConfigFileIDs.get(aCategory.toString().replaceAll("\\|", "."), aName.replaceAll("\\|", "."), aDefault);
		int rResult = tProperty.getInt(aDefault);
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI && !tProperty.wasRead()) sConfigFileIDs.save(); else sIDConfigNeedsSaving = T;
		return rResult;
	}
	
	public final Configuration mConfig;
	
	public Config(String aFileName) {this(DirectoriesGT.CONFIG_GT, aFileName);}
	public Config(String aPath, String aFileName) {this(new File(aPath), aFileName);}
	public Config(File aPath, String aFileName) {
		File tPath = new File(aPath, aFileName);
		if (!tPath.exists()) tPath = new File(aPath, aFileName.toLowerCase());
		mConfig = new Configuration(tPath);
		mConfig.load();
		mConfig.save();
		Abstract_Mod.sConfigs.add(this);
	}
	
	public Config(File aConfig) {this(new Configuration(aConfig));}
	public Config(Configuration aConfig) {mConfig = aConfig; mConfig.load(); mConfig.save(); Abstract_Mod.sConfigs.add(this);}
	
	@Override
	public void run() {
		mConfig.save();
		if (sIDConfigNeedsSaving) {
			sConfigFileIDs.save();
			sIDConfigNeedsSaving = F;
		}
	}
	
	public Config setUseDefaultInNames(boolean aUsesDefaultsInNames) {
		mUsesDefaultsInNames = aUsesDefaultsInNames;
		return this;
	}
	
	public boolean get(Object aCategory, ItemStack aStack, boolean aDefault) {
		return get(aCategory, ST.configName(aStack), aDefault);
	}
	
	public boolean get(Object aCategory, String aName, boolean aDefault) {
		if (UT.Code.stringInvalid(aName)) return aDefault;
		Property tProperty = mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName+(mUsesDefaultsInNames?"_"+aDefault:"")).replaceAll("\\|", "_"), aDefault);
		boolean rResult = tProperty.getBoolean(aDefault);
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI && mSaveOnEdit && !tProperty.wasRead()) mConfig.save();
		return rResult;
	}
	
	public int get(Object aCategory, ItemStack aStack, long aDefault) {
		return get(aCategory, ST.configName(aStack), aDefault);
	}
	
	public int get(Object aCategory, String aName, long aDefault) {
		if (UT.Code.stringInvalid(aName)) return UT.Code.bindInt(aDefault);
		Property tProperty = mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName+(mUsesDefaultsInNames?"_"+UT.Code.bindInt(aDefault):"")).replaceAll("\\|", "_"), UT.Code.bindInt(aDefault));
		int rResult = tProperty.getInt(UT.Code.bindInt(aDefault));
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI && mSaveOnEdit && !tProperty.wasRead()) mConfig.save();
		return rResult;
	}
	
	public String get(Object aCategory, ItemStack aStack, String aDefault) {
		return get(aCategory, ST.configName(aStack), aDefault);
	}
	
	public String get(Object aCategory, String aName, String aDefault) {
		if (UT.Code.stringInvalid(aName)) return aDefault;
		Property tProperty = mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName+(mUsesDefaultsInNames?"_"+aDefault:"")).replaceAll("\\|", "_"), aDefault);
		String rResult = tProperty.getString();
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI && mSaveOnEdit && !tProperty.wasRead()) mConfig.save();
		return rResult;
	}
	
	public OreDictMaterial get(Object aCategory, String aName, OreDictMaterial aDefault) {
		if (aDefault == null) aDefault = MT.NULL;
		if (UT.Code.stringInvalid(aName)) return aDefault;
		Property tProperty = mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName+(mUsesDefaultsInNames?"_"+aDefault.mNameInternal:"")).replaceAll("\\|", "_"), aDefault.mNameInternal);
		OreDictMaterial rResult = OreDictMaterial.get(tProperty.getString());
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI && mSaveOnEdit && !tProperty.wasRead()) mConfig.save();
		return rResult;
	}
	
	public double get(Object aCategory, ItemStack aStack, double aDefault) {
		return get(aCategory, ST.configName(aStack), aDefault);
	}
	
	public double get(Object aCategory, String aName, double aDefault) {
		if (UT.Code.stringInvalid(aName)) return aDefault;
		Property tProperty = mConfig.get(aCategory.toString().replaceAll("\\|", "_"), (aName+(mUsesDefaultsInNames?"_"+aDefault:"")).replaceAll("\\|", "_"), aDefault);
		double rResult = tProperty.getDouble(aDefault);
		if (Abstract_Mod.sFinalized >= Abstract_Mod.sModCountUsingGTAPI && mSaveOnEdit && !tProperty.wasRead()) mConfig.save();
		return rResult;
	}
	
	@Override public String toString() {return "Config File";}
}
