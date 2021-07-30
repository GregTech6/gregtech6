/**
 * Copyright (c) 2021 GregTech-6 Team
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

package gregapi.render;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.GT_API;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.MD;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/** 
 * @author Gregorius Techneticies
 */
public class TextureSet {
	public static final List<TextureSet> INSTANCES_ITEM = new ArrayListNoNulls<>();
	public static final List<TextureSet> INSTANCES_BLOCK = new ArrayListNoNulls<>();
	public static final List<String> FILENAMES_ITEM = new ArrayListNoNulls<>();
	public static final List<String> FILENAMES_BLOCK = new ArrayListNoNulls<>();
	public final List<IIconContainer> mList = new ArrayListNoNulls<>();
	
	private final boolean mIsItem;
	private final String mNameSet;
	
	private TextureSet(boolean aIsItem, String aNameSet) {
		mIsItem = aIsItem;
		mNameSet = aNameSet;
	}
	
	/**
	 * Adds a new Texture Set, unless another Set with the same Name already exists.
	 * @param aModID the Mod responsible for adding the Icons (which are located in either assets\aModID.toLowerCase()\textures\items\materialicons\ or assets\aModID.toLowerCase()\textures\blocks\materialicons\).
	 * @param aIsItem true if this is an Item Icon, false if this is a Block Icon.
	 */
	public static TextureSet addTextureSet(String aModID, boolean aIsItem, String aNameSet) {
		List<TextureSet> tTextureSetList = (aIsItem?INSTANCES_ITEM:INSTANCES_BLOCK);
		List<String> tFileNameList = (aIsItem?FILENAMES_ITEM:FILENAMES_BLOCK);
		for (TextureSet tSet : tTextureSetList) if (tSet.mNameSet.equals(aNameSet)) return tSet;
		TextureSet rSet = new TextureSet(aIsItem, aNameSet);
		tTextureSetList.add(rSet);
		for (int i = 0, j = tFileNameList.size(); i < j; i++) rSet.mList.add(aIsItem?new TextureSetIconItem(aModID, aNameSet+"/"+tFileNameList.get(i)):new TextureSetIconBlock(aModID, aNameSet+"/"+tFileNameList.get(i)));
		return rSet;
	}
	
	/**
	 * Adds a new Icon Name to all Texture Sets, or just returns the Index of an already existing Set with the same File Name.
	 * @param aModID the Mod responsible for adding the Icons (which are located in either assets\aModID.toLowerCase()\textures\items\materialicons\ or assets\aModID.toLowerCase()\textures\blocks\materialicons\).
	 * @param aNameFile the Name of the File inside each Set Folder.
	 * @return the Index of this Icon inside the Set Lists.
	 */
	public static int addToAll(String aModID, boolean aIsItem, String aNameFile) {
		List<String> tFileNameList = (aIsItem?FILENAMES_ITEM:FILENAMES_BLOCK);
		int tIndex = tFileNameList.indexOf(aNameFile);
		if (tIndex >= 0) return tIndex;
		tFileNameList.add(aNameFile);
		for (TextureSet tSet : (aIsItem?INSTANCES_ITEM:INSTANCES_BLOCK)) tSet.mList.add(tSet.mIsItem?new TextureSetIconItem(aModID, tSet.mNameSet+"/"+aNameFile):new TextureSetIconBlock(aModID, tSet.mNameSet+"/"+aNameFile));
		return tFileNameList.size() - 1;
	}
	
	public static class TextureSetIconItem implements IIconContainer, Runnable {
		private final String mMod, mName;
		private IIcon mIconColored, mIconOverlay;
		
		public TextureSetIconItem(String aMod, String aName) {
			mName = aName;
			mMod = aMod.toLowerCase();
			if (GT_API.sItemIconload != null) GT_API.sItemIconload.add(this);
		}
		
		@Override
		public IIcon getIcon(int aRenderPass) {
			return aRenderPass == 0 ? mIconColored : mIconOverlay;
		}
		
		@Override
		public short[] getIconColor(int aRenderPass) {
			return UNCOLOURED;
		}
		
		@Override
		public int getIconPasses() {
			return 2;
		}
		
		@Override
		public ResourceLocation getTextureFile() {
			return TextureMap.locationItemsTexture;
		}
		
		@Override
		public void registerIcons(IIconRegister aIconRegister) {
			mIconColored = aIconRegister.registerIcon(mMod+":materialicons/"+mName);
			mIconOverlay = aIconRegister.registerIcon(mMod+":materialicons/"+mName+"_OVERLAY");
		}
		
		@Override
		public String toString() {
			return mMod+":materialicons/"+mName;
		}
		
		@Override
		public boolean isUsingColorModulation(int aRenderPass) {
			return aRenderPass == 0;
		}
		
		@Override
		public void run() {
			registerIcons(GT_API.sItemIcons);
		}
	}
	
	public static class TextureSetIconBlock implements IIconContainer, Runnable {
		private final String mMod, mName;
		private IIcon mIconColored, mIconOverlay;
		
		public TextureSetIconBlock(String aMod, String aName) {
			mName = aName;
			mMod = aMod;
			if (GT_API.sBlockIconload != null) GT_API.sBlockIconload.add(this);
		}
		
		@Override
		public IIcon getIcon(int aRenderPass) {
			return aRenderPass == 0 ? mIconColored : mIconOverlay;
		}
		
		@Override
		public short[] getIconColor(int aRenderPass) {
			return UNCOLOURED;
		}
		
		@Override
		public int getIconPasses() {
			return 2;
		}
		
		@Override
		public ResourceLocation getTextureFile() {
			return TextureMap.locationBlocksTexture;
		}
		
		@Override
		public void registerIcons(IIconRegister aIconRegister) {
			mIconColored = aIconRegister.registerIcon(mMod+":materialicons/"+mName);
			mIconOverlay = aIconRegister.registerIcon(mMod+":materialicons/"+mName+"_OVERLAY");
		}
		
		@Override
		public String toString() {
			return mMod+":materialicons/"+mName;
		}
		
		@Override
		public boolean isUsingColorModulation(int aRenderPass) {
			return aRenderPass == 0;
		}
		
		@Override
		public void run() {
			registerIcons(GT_API.sBlockIcons);
		}
	}
	
	/** Arrays with all Sets, which belong to each other. Shortens the Parameter amount inside MT.java */
	public static final TextureSet[]
	  SET_NONE                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "NONE")          , addTextureSet(MD.GT.mID, T, "NONE")          }
	, SET_DULL                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "DULL")          , addTextureSet(MD.GT.mID, T, "DULL")          }
	, SET_RAD                       = new TextureSet[] {addTextureSet(MD.GT.mID, F, "RAD")           , addTextureSet(MD.GT.mID, T, "RAD")           }
	, SET_HEX                       = new TextureSet[] {addTextureSet(MD.GT.mID, F, "HEX")           , addTextureSet(MD.GT.mID, T, "HEX")           }
	, SET_RUBY                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "RUBY")          , addTextureSet(MD.GT.mID, T, "RUBY")          }
	, SET_OPAL                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "OPAL")          , addTextureSet(MD.GT.mID, T, "OPAL")          }
	, SET_LEAF                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "LEAF")          , addTextureSet(MD.GT.mID, T, "LEAF")          }
	, SET_SAND                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "SAND")          , addTextureSet(MD.GT.mID, T, "SAND")          }
	, SET_FINE                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "FINE")          , addTextureSet(MD.GT.mID, T, "FINE")          }
	, SET_FOOD                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "FOOD")          , addTextureSet(MD.GT.mID, T, "FOOD")          }
	, SET_WOOD                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "WOOD")          , addTextureSet(MD.GT.mID, T, "WOOD")          }
	, SET_CUBE                      = new TextureSet[] {addTextureSet(MD.GT.mID, F, "CUBE")          , addTextureSet(MD.GT.mID, T, "CUBE")          }
	, SET_FIERY                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "FIERY")         , addTextureSet(MD.GT.mID, T, "FIERY")         }
	, SET_GAS                       = new TextureSet[] {addTextureSet(MD.GT.mID, F, "GAS")           , addTextureSet(MD.GT.mID, T, "GAS")           }
	, SET_FLUID                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "FLUID")         , addTextureSet(MD.GT.mID, T, "FLUID")         }
	, SET_PLASMA                    = new TextureSet[] {addTextureSet(MD.GT.mID, F, "PLASMA")        , addTextureSet(MD.GT.mID, T, "PLASMA")        }
	, SET_ROUGH                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "ROUGH")         , addTextureSet(MD.GT.mID, T, "ROUGH")         }
	, SET_STONE                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "STONE")         , addTextureSet(MD.GT.mID, T, "STONE")         }
	, SET_SPACE                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "SPACE")         , addTextureSet(MD.GT.mID, T, "SPACE")         }
	, SET_PAPER                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "PAPER")         , addTextureSet(MD.GT.mID, T, "PAPER")         }
	, SET_GLASS                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "GLASS")         , addTextureSet(MD.GT.mID, T, "GLASS")         }
	, SET_FLINT                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "FLINT")         , addTextureSet(MD.GT.mID, T, "FLINT")         }
	, SET_LAPIS                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "LAPIS")         , addTextureSet(MD.GT.mID, T, "LAPIS")         }
	, SET_SHINY                     = new TextureSet[] {addTextureSet(MD.GT.mID, F, "SHINY")         , addTextureSet(MD.GT.mID, T, "SHINY")         }
	, SET_RUBBER                    = new TextureSet[] {addTextureSet(MD.GT.mID, F, "RUBBER")        , addTextureSet(MD.GT.mID, T, "RUBBER")        }
	, SET_SHARDS                    = new TextureSet[] {addTextureSet(MD.GT.mID, F, "SHARDS")        , addTextureSet(MD.GT.mID, T, "SHARDS")        }
	, SET_POWDER                    = new TextureSet[] {addTextureSet(MD.GT.mID, F, "POWDER")        , addTextureSet(MD.GT.mID, T, "POWDER")        }
	, SET_COPPER                    = new TextureSet[] {addTextureSet(MD.GT.mID, F, "COPPER")        , addTextureSet(MD.GT.mID, T, "COPPER")        }
	, SET_QUARTZ                    = new TextureSet[] {addTextureSet(MD.GT.mID, F, "QUARTZ")        , addTextureSet(MD.GT.mID, T, "QUARTZ")        }
	, SET_EMERALD                   = new TextureSet[] {addTextureSet(MD.GT.mID, F, "EMERALD")       , addTextureSet(MD.GT.mID, T, "EMERALD")       }
	, SET_DIAMOND                   = new TextureSet[] {addTextureSet(MD.GT.mID, F, "DIAMOND")       , addTextureSet(MD.GT.mID, T, "DIAMOND")       }
	, SET_LIGNITE                   = new TextureSet[] {addTextureSet(MD.GT.mID, F, "LIGNITE")       , addTextureSet(MD.GT.mID, T, "LIGNITE")       }
	, SET_REDSTONE                  = new TextureSet[] {addTextureSet(MD.GT.mID, F, "REDSTONE")      , addTextureSet(MD.GT.mID, T, "REDSTONE")      }
	, SET_MAGNETIC                  = new TextureSet[] {addTextureSet(MD.GT.mID, F, "MAGNETIC")      , addTextureSet(MD.GT.mID, T, "MAGNETIC")      }
	, SET_METALLIC                  = new TextureSet[] {addTextureSet(MD.GT.mID, F, "METALLIC")      , addTextureSet(MD.GT.mID, T, "METALLIC")      }
	, SET_PRISMARINE                = new TextureSet[] {addTextureSet(MD.GT.mID, F, "PRISMARINE")    , addTextureSet(MD.GT.mID, T, "PRISMARINE")    }
	, SET_CUBE_SHINY                = new TextureSet[] {addTextureSet(MD.GT.mID, F, "CUBE_SHINY")    , addTextureSet(MD.GT.mID, T, "CUBE_SHINY")    }
	, SET_NETHERSTAR                = new TextureSet[] {addTextureSet(MD.GT.mID, F, "NETHERSTAR")    , addTextureSet(MD.GT.mID, T, "NETHERSTAR")    }
	, SET_GEM_VERTICAL              = new TextureSet[] {addTextureSet(MD.GT.mID, F, "GEM_VERTICAL")  , addTextureSet(MD.GT.mID, T, "GEM_VERTICAL")  }
	, SET_GEM_HORIZONTAL            = new TextureSet[] {addTextureSet(MD.GT.mID, F, "GEM_HORIZONTAL"), addTextureSet(MD.GT.mID, T, "GEM_HORIZONTAL")}
	;
}
