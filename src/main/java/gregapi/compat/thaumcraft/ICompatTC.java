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

package gregapi.compat.thaumcraft;

import gregapi.compat.ICompat;
import gregapi.data.TC.TC_AspectStack;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface ICompatTC extends ICompat {
	public static final int RESEARCH_TYPE_NORMAL = 0, RESEARCH_TYPE_SECONDARY = 1, RESEARCH_TYPE_FREE = 2, RESEARCH_TYPE_HIDDEN = 4, RESEARCH_TYPE_VIRTUAL = 8, RESEARCH_TYPE_ROUND = 16, RESEARCH_TYPE_SPECIAL = 32, RESEARCH_TYPE_AUTOUNLOCK = 64, RESEARCH_TYPE_LOST = 128;
	public static final String CATEGORY_ALCHEMY = "ALCHEMY";
	
	/** The Research Keys of GT */
	public static final String
	  IRON_TO_STEEL                 = "GT_IRON_TO_STEEL"
	, FILL_WATER_BUCKET             = "GT_FILL_WATER_BUCKET"
	, WOOD_TO_CHARCOAL              = "GT_WOOD_TO_CHARCOAL"
	, TRANSZINC                     = "GT_TRANSZINC"
	, TRANSNICKEL                   = "GT_TRANSNICKEL"
	, TRANSCOBALT                   = "GT_TRANSCOBALT"
	, TRANSBISMUTH                  = "GT_TRANSBISMUTH"
	, TRANSANTIMONY                 = "GT_TRANSANTIMONY"
	, TRANSCONSTANTAN               = "GT_TRANSCUPRONICKEL"
	, TRANSBATTERYALLOY             = "GT_TRANSBATTERYALLOY"
	, TRANSSOLDERINGALLOY           = "GT_TRANSSOLDERINGALLOY"
	, TRANSBRASS                    = "GT_TRANSBRASS"
	, TRANSBRONZE                   = "GT_TRANSBRONZE"
	, TRANSINVAR                    = "GT_TRANSINVAR"
	, TRANSELECTRUM                 = "GT_TRANSELECTRUM"
	, TRANSALUMINIUM                = "GT_TRANSALUMINIUM"
	, CRYSTALLISATION               = "GT_CRYSTALLISATION"
	, ADVANCEDENTROPICPROCESSING    = "GT_ADVANCEDENTROPICPROCESSING"
	, ADVANCEDMETALLURGY            = "GT_ADVANCEDMETALLURGY"
	;
	
	public boolean registerPortholeBlacklistedBlock(Block aBlock);
	
	public boolean registerThaumcraftAspectsToItem(ItemStack aStack, List<TC_AspectStack> aAspects, boolean aAdditive);
	public boolean registerThaumcraftAspectsToItem(ItemStack aStack, boolean aAdditive, TC_AspectStack... aAspects);
	
	/** Just dont use this anymore, it does not work properly. */ @Deprecated public boolean registerThaumcraftAspectsToItem(ItemStack aExampleStack, List<TC_AspectStack> aAspects, String aOreDict);
	/** Just dont use this anymore, it does not work properly. */ @Deprecated public boolean registerThaumcraftAspectsToItem(ItemStack aExampleStack, String aOreDict, TC_AspectStack... aAspects);
	
	public ItemStack[] lootbag(long aMeta);
	
	public Object addCrucibleRecipe(String aResearch, Object aInput, ItemStack aOutput, List<TC_AspectStack> aAspects);
	public Object addCrucibleRecipe(String aResearch, Object aInput, ItemStack aOutput, TC_AspectStack... aAspects);
	
	public Object addResearch(String aResearch, String aName, String aText, String[] aParentResearches, String aCategory, ItemStack aIcon, int aComplexity, int aType, int aX, int aY, List<TC_AspectStack> aAspects, ItemStack[] aResearchTriggers, Object... aPages);
	
	public Object getAspectList(List<TC_AspectStack> aAspects);
	public Object getAspectList(TC_AspectStack... aAspects);
}
