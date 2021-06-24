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

package gregapi.api.example;

/**
 * @author Your Name Here, also might be worth replacing that automatically generated Copyright notice with your LPGL compatible License/Name instead of mine.
 * 
 * An example implementation for a Mod using my System. Copy and rename this File into your source Directory.
 * 
 * If you have ANY Problems with the examples here, then you can contact me on the Forums or IRC.
 * 
 * You may ask yourself why there are no imports on this File.
 * I decided to do that, so Beginners cannot mess up by choosing wrong imports when they copy and paste Stuff.
 * Also I avoided creating Variables, because people tend to copy them over for no reason, because they don't understand what they were for, and that they could be removed easily.
 * 
 * Note: it is important to load after "gregapi_post".
 * 
 * Note: There are NO TEXTURES contained in GT that correspond to the Examples. Those you will have to do or copy them yourself.
 * 
 * uncomment the @cpw.mods.fml.common.Mod-Annotation for actual usage.
 */
//@cpw.mods.fml.common.Mod(modid=Example_Mod.MOD_ID, name=Example_Mod.MOD_NAME, version=Example_Mod.VERSION, dependencies="required-after:gregapi_post")
public final class Example_Mod extends gregapi.api.Abstract_Mod {
	/** Your Mod-ID has to be LOWERCASE and without Spaces. Uppercase Chars and Spaces can create problems with Resource Packs. This is a vanilla forge "Issue". */
	public static final String MOD_ID = "insert_your_modid_here"; // <-- TODO: you need to change this to the ID of your own Mod, and then remove this Comment after you did that.
	/** This is your Mods Name */
	public static final String MOD_NAME = "Insert_your_Mod_Name_here"; // <-- TODO: you need to change this to the Name of your own Mod, and then remove this Comment after you did that.
	/** This is your Mods Version */
	public static final String VERSION = "EXAMPLE-MC1710"; // <-- TODO: you need to change this to the Version of your own Mod, and then remove this Comment after you did that.
	/** Contains a ModData Object for ID and Name. Doesn't have to be changed. */
	public static gregapi.code.ModData MOD_DATA = new gregapi.code.ModData(MOD_ID, MOD_NAME);
	
	@cpw.mods.fml.common.SidedProxy(modId = MOD_ID, clientSide = "gregapi.api.example.Example_Proxy_Client", serverSide = "gregapi.api.example.Example_Proxy_Server")
	public static gregapi.api.Abstract_Proxy PROXY;
	
	@Override public String getModID() {return MOD_ID;}
	@Override public String getModName() {return MOD_NAME;}
	@Override public String getModNameForLog() {return "Example_Mod";}
	@Override public gregapi.api.Abstract_Proxy getProxy() {return PROXY;}
	
	// Do not change these 7 Functions. Just keep them this way.
	@cpw.mods.fml.common.Mod.EventHandler public final void onPreLoad           (cpw.mods.fml.common.event.FMLPreInitializationEvent    aEvent) {onModPreInit(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onLoad              (cpw.mods.fml.common.event.FMLInitializationEvent       aEvent) {onModInit(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onPostLoad          (cpw.mods.fml.common.event.FMLPostInitializationEvent   aEvent) {onModPostInit(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onServerStarting    (cpw.mods.fml.common.event.FMLServerStartingEvent       aEvent) {onModServerStarting(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onServerStarted     (cpw.mods.fml.common.event.FMLServerStartedEvent        aEvent) {onModServerStarted(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onServerStopping    (cpw.mods.fml.common.event.FMLServerStoppingEvent       aEvent) {onModServerStopping(aEvent);}
	@cpw.mods.fml.common.Mod.EventHandler public final void onServerStopped     (cpw.mods.fml.common.event.FMLServerStoppedEvent        aEvent) {onModServerStopped(aEvent);}
	
	@Override
	public void onModPreInit2(cpw.mods.fml.common.event.FMLPreInitializationEvent aEvent) {
		// If you want to make yourself a new OreDict Prefix for your Component Items or similar.
		final gregapi.oredict.OreDictPrefix tExamplePrefix = gregapi.oredict.OreDictPrefix.createPrefix("exampleprefix"); // This newly created OreDict Prefix is named "exampleprefix", so an Aluminium Item with this Prefix would be named "exampleprefixAluminium" in the OreDict.
		tExamplePrefix.setCategoryName("Examples"); // That is what the Creative Tab of it would be named.
		tExamplePrefix.setLocalItemName("Small ", " Example"); // Generic Items will follow this naming Guideline, so for example "Small Aluminium Example" for an Aluminium Item with that Prefix.
		tExamplePrefix.setCondition(gregapi.code.ICondition.TRUE); // The Condition under which Items of this Prefix should generate in general. In this case TRUE to have ALL the Items.
		tExamplePrefix.add(gregapi.data.TD.Prefix.UNIFICATABLE); // OreDict Unification can apply to this Prefix.
		tExamplePrefix.add(gregapi.data.TD.Prefix.RECYCLABLE); // Items of this can be recycled for Resources.
		tExamplePrefix.setMaterialStats(gregapi.data.CS.U); // Any Item of this example Prefix has the value of 1 Material Unit (U), this is exactly equal to one Ingot/Dust/Gem.
		tExamplePrefix.aspects(gregapi.data.TC.FABRICO, 1); // Thaumcraft Aspects related to this Prefix.
		tExamplePrefix.setStacksize(16, 8); // Sets the Maximum ItemStack Size of this Prefix to 16, and allows the Config to go as far down as 8 when people manually select a StackSize using it.
		
		// If you want to make yourself a new OreDict Material. Please look up proper IDs. So replace 32766 with a Number inside YOUR own ID Range. (you can look that up in gregapi.oredict.OreDictMaterial.java)
		final gregapi.oredict.OreDictMaterial tExamplium = gregapi.oredict.OreDictMaterial.createMaterial(32766, "Examplium", "Examplium"); // Creates a Material called "Examplium".
		tExamplium.setTextures(gregapi.render.TextureSet.SET_METALLIC); // gives this Material the Metallic Texture Set.
		tExamplium.setRGBa(100, 100, 200,   0); // Sets the RGBa Color of the Material. In this case some random blue Color.
		tExamplium.put(gregapi.data.TD.Processing.SMITHABLE); // This Material is smithable like regular Metal things.
		tExamplium.put(gregapi.data.TD.Processing.MELTING); // This Material can melt.
		tExamplium.put(gregapi.data.TD.Processing.FURNACE); // This Material can be molten in a regular Furnace.
		tExamplium.put(gregapi.data.TD.Processing.CENTRIFUGE); // This Material can be centrifuged to recycle it.
		tExamplium.put(gregapi.data.TD.Compounds.DECOMPOSABLE); // This Material can be decomposed in general.
		tExamplium.put(gregapi.data.TD.ItemGenerator.G_INGOT_MACHINE_ORES); // This Material is a typical Ingot, that can be used for Machine Parts, and the Material can be found as Ore too.
		tExamplium.heat(2000, 4000); // This Material melts at 2000 Kelvin and Boils at 4000 Kelvin.
		tExamplium.qual(3 // Type 3 = The Material can be used for every Type of Tool 
					, 6.0 // Speed is 6.0 what is as fast as Steel at mining stuff
					, 512 // Durability is 512 what equals Steel too
					, 3); // Quality is 3 for Diamond Tool Level
		tExamplium.setMcfg(0, gregapi.data.MT.Steel, 1*gregapi.data.CS.U); // This Material consists out of one Unit of Steel.
		tExamplium.setOriginalMod(MOD_DATA); // Gives your Mod the credit for creating this Material.
		tExamplium.aspects(gregapi.data.TC.METALLUM, 3); // Thaumcraft Aspects related to this Material.
		
		// If you want to make your Prefix an Item
		// Creates the generic Item for the new Prefix. Assets go into "/assets/insert_your_modid_here/textures/items/materialicons". And yes, every TextureSet for every Material Type has its own Folder.
		new gregapi.item.prefixitem.PrefixItem(MOD_ID, MOD_ID, "example.meta.item.exampleprefix", tExamplePrefix, gregapi.oredict.OreDictMaterial.MATERIAL_ARRAY);
		
		// If you want to make your Prefix a Block
		// Creates the generic Block for the new Prefix. Assets go into "/assets/insert_your_modid_here/textures/blocks/materialicons". And yes, every TextureSet for every Material Type has its own Folder.
		new gregapi.block.prefixblock.PrefixBlock_(MOD_ID, MOD_ID, "example.meta.block.exampleprefix", tExamplePrefix, null, null, null, null, net.minecraft.block.material.Material.rock, net.minecraft.block.Block.soundTypeStone, gregapi.data.CS.TOOL_pickaxe, 1.5F, 4.5F,   0,   0, 999, 0, 0, 0, 1, 1, 1, false, false, false, false, true, true, true, true, true, true, false, true, true, true, gregapi.oredict.OreDictMaterial.MATERIAL_ARRAY); 
		
		// You may think that you don't want to add all the PrefixItems for all the Materials, since you only need certain ones yourself and don't want a clutter like the one GregTech itself causes.
		// No Problem, you can add single Items too, if you just need those.
		// Assets go into "/assets/insert_your_modid_here/textures/items/example.multiitem.resources/..."
		// The Textures themselves are just the IDs you insert down there. So "0.png" for the Tiny Pile of Examplium Dust.
		new gregapi.item.multiitem.MultiItemRandom(MOD_ID, "example.multiitem.resources") {@Override public void addItems() {
		// Did you know that you can use a variable from outside this Block by just making it "final"? I didn't, but now I know more and use tExamplium, even though it wouldn't be accessible otherwise.
		// And yes you can use all the 32766 possible Meta-IDs of this Item.
		addItem(    0, "Tiny Pile of Examplium Dust"    , "", gregapi.data.OP.dustTiny  .dat(tExamplium));
		addItem(    1, "Small Pile of Examplium Dust"   , "", gregapi.data.OP.dustSmall .dat(tExamplium));
		addItem(    2, "Examplium Dust"                 , "", gregapi.data.OP.dust      .dat(tExamplium));
		addItem(    3, "Examplium Nugget"               , "", gregapi.data.OP.nugget    .dat(tExamplium));
		addItem(    4, "Examplium Chunk"                , "", gregapi.data.OP.chunkGt   .dat(tExamplium));
		addItem(    5, "Examplium Ingot"                , "", gregapi.data.OP.ingot     .dat(tExamplium));
		addItem(    6, "Examplium Plate"                , "", gregapi.data.OP.plate     .dat(tExamplium));
		addItem(    7, "Examplium Rod"                  , "", gregapi.data.OP.stick     .dat(tExamplium));
		addItem(    8, "Examplium Ring"                 , "", gregapi.data.OP.ring      .dat(tExamplium));
		
		// Here would be a right place to add Crafting Recipes or Machine Recipes using your new Items.
		
		// Crafting the Dusts together.
		gregapi.util.CR.shapeless(gregapi.data.OP.dust.mat(tExamplium, 1), gregapi.util.CR.DEF, new Object[] {gregapi.data.OP.dustTiny.dat(tExamplium), gregapi.data.OP.dustTiny.dat(tExamplium), gregapi.data.OP.dustTiny.dat(tExamplium), gregapi.data.OP.dustTiny.dat(tExamplium), gregapi.data.OP.dustTiny.dat(tExamplium), gregapi.data.OP.dustTiny.dat(tExamplium), gregapi.data.OP.dustTiny.dat(tExamplium), gregapi.data.OP.dustTiny.dat(tExamplium), gregapi.data.OP.dustTiny.dat(tExamplium)});
		gregapi.util.CR.shapeless(gregapi.data.OP.dust.mat(tExamplium, 1), gregapi.util.CR.DEF, new Object[] {gregapi.data.OP.dustSmall.dat(tExamplium), gregapi.data.OP.dustSmall.dat(tExamplium), gregapi.data.OP.dustSmall.dat(tExamplium), gregapi.data.OP.dustSmall.dat(tExamplium)});
		
		// Smelting the Dusts into Ingots/Nuggets
		gregapi.data.RM.add_smelting(gregapi.util.ST.make(this, 1, 0), gregapi.util.ST.make(this, 1, 3), 0.1F, false, false, true);
		gregapi.data.RM.add_smelting(gregapi.util.ST.make(this, 1, 1), gregapi.util.ST.make(this, 1, 4), 0.1F, false, false, true);
		gregapi.data.RM.add_smelting(gregapi.util.ST.make(this, 1, 2), gregapi.util.ST.make(this, 1, 5), 0.1F, false, false, true);
		}};
		
		// This gives you your very own 32767 Machine IDs.
		new gregapi.block.multitileentity.MultiTileEntityRegistry("example.multitileentity");
		
		// Every Machine can have another Block, vanilla-material, vanilla-step-sound or Harvest Tool
		gregapi.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "iron"       , net.minecraft.block.material.Material.iron    , net.minecraft.block.Block.soundTypeMetal  , gregapi.data.CS.TOOL_pickaxe  , 0, 0, 15, false, false);
		gregapi.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "machine"    , gregapi.block.MaterialMachines.instance       , net.minecraft.block.Block.soundTypeMetal  , gregapi.data.CS.TOOL_cutter   , 0, 0, 15, false, false);
		gregapi.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "machine"    , gregapi.block.MaterialMachines.instance       , net.minecraft.block.Block.soundTypeMetal  , gregapi.data.CS.TOOL_wrench   , 0, 0, 15, false, false);
	}
	
	@Override
	public void onModInit2(cpw.mods.fml.common.event.FMLInitializationEvent aEvent) {
		// Gets your initialised Registry.
		gregapi.block.multitileentity.MultiTileEntityRegistry tExampleRegistry = gregapi.block.multitileentity.MultiTileEntityRegistry.getRegistry("example.multitileentity");
		
		// Gets your Examplium by Name if you initialised it above.
		gregapi.oredict.OreDictMaterial tExamplium = gregapi.oredict.OreDictMaterial.get("Examplium");
		
		// YES this Registry Stuff can and should be in @Init. That way, all the OreDict Items needed for Crafting Recipes are available and registered.
		
		// Take the Metal Block from the Set, that you initialised above in @PreInit.
		gregapi.block.multitileentity.MultiTileEntityBlock tMetalBlock = gregapi.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "iron", net.minecraft.block.material.Material.iron, net.minecraft.block.Block.soundTypeMetal, gregapi.data.CS.TOOL_pickaxe, 0, 0, 15, false, false);
		// Makes an Examplium Chest out of your Example Material.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have the Stick, Ring and Plate PrefixItems.
		tExampleRegistry.add(tExamplium.getLocal() + " Chest", "Chests", 0, 0, gregapi.block.multitileentity.example.MultiTileEntityChest.class, 0, 16, tMetalBlock, gregapi.util.UT.NBT.make(gregapi.data.CS.NBT_MATERIAL, tExamplium, gregapi.data.CS.NBT_INV_SIZE, 54, gregapi.data.CS.NBT_TEXTURE, "metalchest", gregapi.data.CS.NBT_HARDNESS, 6.0F, gregapi.data.CS.NBT_RESISTANCE, 6.0F, gregapi.data.CS.NBT_COLOR, gregapi.util.UT.Code.getRGBInt(tExamplium.fRGBaSolid)), "sPw", "RSR", "PPP", 'P', gregapi.data.OP.plate.dat(tExamplium), 'R', gregapi.data.OP.ring.dat(tExamplium), 'S', gregapi.data.OP.stick.dat(tExamplium));
		
		// Take the Machine Block from the Set, that you initialised above in @PreInit.
		gregapi.block.multitileentity.MultiTileEntityBlock tMachineBlock = gregapi.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "machine", gregapi.block.MaterialMachines.instance, net.minecraft.block.Block.soundTypeMetal, gregapi.data.CS.TOOL_wrench, 0, 0, 15, false, false);
		// Makes a vanilla Furnace out of your Example Material.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have all the PrefixItems.
		// GUI has to be at: "/assets/insert_your_modid_here/textures/gui/machines/Oven.png"
		// Block Textures have to be at: "/assets/gregtech/textures/blocks/machines/basicmachines/oven/..." Yes that is not a Typo, it is actually the GregTech Mod-ID in that path. I noticed that flaw way too late to fix it. And look at how GT has the Textures for its Oven for Details.
		tExampleRegistry.add("Oven ("+tExamplium.getLocal()+")", "Example Mod", 1, 0, gregapi.tileentity.machines.MultiTileEntityBasicMachine.class, tExamplium.mToolQuality, 16, tMachineBlock, gregapi.util.UT.NBT.make(gregapi.data.CS.NBT_MATERIAL, tExamplium, gregapi.data.CS.NBT_HARDNESS, 6.0F, gregapi.data.CS.NBT_RESISTANCE, 6.0F, gregapi.data.CS.NBT_COLOR, gregapi.util.UT.Code.getRGBInt(tExamplium.fRGBaSolid), gregapi.data.CS.NBT_INPUT, 128, gregapi.data.CS.NBT_TEXTURE, "oven", gregapi.data.CS.NBT_GUI, MOD_ID+":textures/gui/machines/Oven", gregapi.data.CS.NBT_ENERGY_ACCEPTED, gregapi.data.TD.Energy.HU, gregapi.data.CS.NBT_RECIPEMAP, gregapi.data.RM.Furnace, gregapi.data.CS.NBT_EFFICIENCY, 10000, gregapi.data.CS.NBT_INV_SIDE_IN, gregapi.data.CS.SBIT_L, gregapi.data.CS.NBT_INV_SIDE_AUTO_IN, gregapi.data.CS.SIDE_LEFT, gregapi.data.CS.NBT_INV_SIDE_OUT, gregapi.data.CS.SBIT_R, gregapi.data.CS.NBT_INV_SIDE_AUTO_OUT, gregapi.data.CS.SIDE_RIGHT, gregapi.data.CS.NBT_ENERGY_ACCEPTED_SIDES, gregapi.data.CS.SBIT_D), "wMh", "BCB", 'M', gregapi.data.OP.casingMachine.dat(tExamplium), 'B', gregapi.util.ST.make(net.minecraft.init.Blocks.brick_block, 1, gregapi.data.CS.W), 'C', gregapi.data.OP.plateDouble.dat(gregapi.data.ANY.Cu));
		
		// The above just makes a vanilla Furnace that is made of Examplium, you cannot obtain the Examplium right now. For that you will need your own Recipe Handler.
		gregapi.recipes.Recipe.RecipeMap tRecipeMap = new gregapi.recipes.Recipe.RecipeMap(null, "example.recipe.exampliumfurnace", "Examplium Furnace", null, 0, 1, MOD_ID+":textures/gui/machines/ExampliumFurnace",/*IN-OUT-MIN-ITEM=*/ 1, 1, 1,/*IN-OUT-MIN-FLUID=*/ 0, 0, 0,/*MIN*/ 0,/*AMP=*/ 1, "", 1, "", true, true, true, true, false, true, true);
		// This Recipe will turn an Iron Ingot into an Examplium Ingot at 64 Units per Tick for 128 Ticks.
		tRecipeMap.addRecipe1(true, 64, 128, gregapi.util.OM.ingot(gregapi.data.MT.Fe           ), gregapi.util.OM.ingot(tExamplium));
		// This Recipe will turn a Wrought Iron Ingot into an Examplium Ingot at 64 Units per Tick for 96 Ticks. So a slightly cheaper variant
		tRecipeMap.addRecipe1(true, 64,  96, gregapi.util.OM.ingot(gregapi.data.MT.WroughtIron  ), gregapi.util.OM.ingot(tExamplium));
		// This Recipe will turn a Steel Ingot into an Examplium Ingot at 64 Units per Tick for 64 Ticks. So a cheaper variant
		tRecipeMap.addRecipe1(true, 64,  64, gregapi.util.OM.ingot(gregapi.data.MT.Steel        ), gregapi.util.OM.ingot(tExamplium));
		
		// Makes an Examplium Furnace out of Iron.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have all the PrefixItems.
		// GUI has to be at: "/assets/insert_your_modid_here/textures/gui/machines/Oven.png"
		// Block Textures have to be at: "/assets/gregtech/textures/blocks/machines/basicmachines/exampliumfurnace/..." Yes that is not a Typo, it is actually the GregTech Mod-ID in that path. I noticed that flaw way too late to fix it. And look at how GT has the Textures for its Oven for Details.
		tExampleRegistry.add("Examplium Furnace"            , "Example Mod", 2, 0, gregapi.tileentity.machines.MultiTileEntityBasicMachine          .class, gregapi.data.MT.Fe      .mToolQuality, 16, tMachineBlock, gregapi.util.UT.NBT.make(gregapi.data.CS.NBT_MATERIAL, gregapi.data.MT.Fe   , gregapi.data.CS.NBT_HARDNESS, 6.0F, gregapi.data.CS.NBT_RESISTANCE, 6.0F, gregapi.data.CS.NBT_COLOR, gregapi.util.UT.Code.getRGBInt(gregapi.data.MT.Fe   .fRGBaSolid), gregapi.data.CS.NBT_INPUT, 128, gregapi.data.CS.NBT_TEXTURE, "exampliumfurnace", gregapi.data.CS.NBT_GUI, MOD_ID+":textures/gui/machines/ExampliumFurnace", gregapi.data.CS.NBT_ENERGY_ACCEPTED, gregapi.data.TD.Energy.HU, gregapi.data.CS.NBT_RECIPEMAP, tRecipeMap, gregapi.data.CS.NBT_EFFICIENCY, 10000, gregapi.data.CS.NBT_INV_SIDE_IN, gregapi.data.CS.SBIT_L, gregapi.data.CS.NBT_INV_SIDE_AUTO_IN, gregapi.data.CS.SIDE_LEFT, gregapi.data.CS.NBT_INV_SIDE_OUT, gregapi.data.CS.SBIT_R, gregapi.data.CS.NBT_INV_SIDE_AUTO_OUT, gregapi.data.CS.SIDE_RIGHT, gregapi.data.CS.NBT_ENERGY_ACCEPTED_SIDES, gregapi.data.CS.SBIT_D), "wMh", "BCB", 'M', gregapi.data.OP.casingMachineDouble.dat(gregapi.data.MT.Fe   ), 'B', gregapi.util.ST.make(net.minecraft.init.Blocks.brick_block, 1, gregapi.data.CS.W), 'C', gregapi.data.OP.plateDouble.dat(gregapi.data.ANY.Cu));
		
		// Makes an electric Examplium Furnace out of Steel.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have all the PrefixItems.
		tExampleRegistry.add("Electric Examplium Furnace"   , "Example Mod", 3, 0, gregapi.tileentity.machines.MultiTileEntityBasicMachineElectric  .class, gregapi.data.MT.Steel   .mToolQuality, 16, tMachineBlock, gregapi.util.UT.NBT.make(gregapi.data.CS.NBT_MATERIAL, gregapi.data.MT.Steel, gregapi.data.CS.NBT_HARDNESS, 6.0F, gregapi.data.CS.NBT_RESISTANCE, 6.0F, gregapi.data.CS.NBT_COLOR, gregapi.util.UT.Code.getRGBInt(gregapi.data.MT.Steel.fRGBaSolid), gregapi.data.CS.NBT_INPUT, 128, gregapi.data.CS.NBT_TEXTURE, "exampliumfurnace", gregapi.data.CS.NBT_GUI, MOD_ID+":textures/gui/machines/ExampliumFurnace", gregapi.data.CS.NBT_ENERGY_ACCEPTED, gregapi.data.TD.Energy.EU, gregapi.data.CS.NBT_RECIPEMAP, tRecipeMap, gregapi.data.CS.NBT_EFFICIENCY, 10000, gregapi.data.CS.NBT_INV_SIDE_IN, gregapi.data.CS.SBIT_L, gregapi.data.CS.NBT_INV_SIDE_AUTO_IN, gregapi.data.CS.SIDE_LEFT, gregapi.data.CS.NBT_INV_SIDE_OUT, gregapi.data.CS.SBIT_R, gregapi.data.CS.NBT_INV_SIDE_AUTO_OUT, gregapi.data.CS.SIDE_RIGHT, gregapi.data.CS.NBT_ENERGY_ACCEPTED_SIDES, gregapi.data.CS.SBIT_D), "wMh", "BCB", 'M', gregapi.data.OP.casingMachineDouble.dat(gregapi.data.MT.Steel), 'B', gregapi.util.ST.make(net.minecraft.init.Blocks.brick_block, 1, gregapi.data.CS.W), 'C', gregapi.data.MT.DATA.CIRCUITS[1]);
		
		// Makes a Flux Examplium Furnace out of Invar.
		// Note: the Crafting Recipe only works in conjunction with GT since you don't have all the PrefixItems.
		// Note: Since it takes RF, the Input has to be 4 times larger.
		tExampleRegistry.add("Flux Examplium Furnace"       , "Example Mod", 4, 0, gregapi.tileentity.machines.MultiTileEntityBasicMachineFlux      .class, gregapi.data.MT.Invar   .mToolQuality, 16, tMachineBlock, gregapi.util.UT.NBT.make(gregapi.data.CS.NBT_MATERIAL, gregapi.data.MT.Invar, gregapi.data.CS.NBT_HARDNESS, 6.0F, gregapi.data.CS.NBT_RESISTANCE, 6.0F, gregapi.data.CS.NBT_COLOR, gregapi.util.UT.Code.getRGBInt(gregapi.data.MT.Invar.fRGBaSolid), gregapi.data.CS.NBT_INPUT, 512, gregapi.data.CS.NBT_TEXTURE, "exampliumfurnace", gregapi.data.CS.NBT_GUI, MOD_ID+":textures/gui/machines/ExampliumFurnace", gregapi.data.CS.NBT_ENERGY_ACCEPTED, gregapi.data.TD.Energy.RF, gregapi.data.CS.NBT_RECIPEMAP, tRecipeMap, gregapi.data.CS.NBT_EFFICIENCY, 10000, gregapi.data.CS.NBT_INV_SIDE_IN, gregapi.data.CS.SBIT_L, gregapi.data.CS.NBT_INV_SIDE_AUTO_IN, gregapi.data.CS.SIDE_LEFT, gregapi.data.CS.NBT_INV_SIDE_OUT, gregapi.data.CS.SBIT_R, gregapi.data.CS.NBT_INV_SIDE_AUTO_OUT, gregapi.data.CS.SIDE_RIGHT, gregapi.data.CS.NBT_ENERGY_ACCEPTED_SIDES, gregapi.data.CS.SBIT_D), "wMh", "BCB", 'M', gregapi.data.OP.casingMachineDouble.dat(gregapi.data.MT.Invar), 'B', gregapi.util.ST.make(net.minecraft.init.Blocks.brick_block, 1, gregapi.data.CS.W), 'C', gregapi.data.MT.DATA.CIRCUITS[3]);
		
		// Makes Examplium Fluid Pipes, which are as good as Stainless Steel ones of GT.
		gregapi.tileentity.connectors.MultiTileEntityPipeFluid.addFluidPipes(30, 0, 250, true, true, false, true, false, true, true, tExampleRegistry, tMachineBlock, gregapi.tileentity.connectors.MultiTileEntityPipeFluid.class, (long)(tExamplium.mMeltingPoint * 1.25), tExamplium);
		
		// Makes Iron Item Pipes, which are as good as Brass ones. Why not Examplium? Because the Recipes would conflict with the Fluid Pipe above in that case.
		gregapi.tileentity.connectors.MultiTileEntityPipeItem.addItemPipes(100, 0, 32768, 1, true, true, tExampleRegistry, tMetalBlock, gregapi.tileentity.connectors.MultiTileEntityPipeItem.class, gregapi.data.MT.Fe);
		
		// Take the Wire Block from the Set, that you initialised above in @PreInit.
		gregapi.block.multitileentity.MultiTileEntityBlock tWireBlock = gregapi.block.multitileentity.MultiTileEntityBlock.getOrCreate(MOD_ID, "machine", gregapi.block.MaterialMachines.instance, net.minecraft.block.Block.soundTypeMetal, gregapi.data.CS.TOOL_cutter, 0, 0, 15, false, false);
		
		// Makes Examplium Wires, which can carry twice the Voltage of Steel and have a lower loss.
		gregapi.tileentity.connectors.MultiTileEntityWireElectric.addElectricWires(50, 0, gregapi.data.CS.VMAX[4], 1, 2, 1, true, false, true, tExampleRegistry, tWireBlock, gregapi.tileentity.connectors.MultiTileEntityWireElectric.class, tExamplium);
	}
	
	@Override
	public void onModPostInit2(cpw.mods.fml.common.event.FMLPostInitializationEvent aEvent) {
		// Insert your PostInit Code here and not above
	}
	
	@Override
	public void onModServerStarting2(cpw.mods.fml.common.event.FMLServerStartingEvent aEvent) {
		// Insert your ServerStarting Code here and not above
	}
	
	@Override
	public void onModServerStarted2(cpw.mods.fml.common.event.FMLServerStartedEvent aEvent) {
		// Insert your ServerStarted Code here and not above
	}
	
	@Override
	public void onModServerStopping2(cpw.mods.fml.common.event.FMLServerStoppingEvent aEvent) {
		// Insert your ServerStopping Code here and not above
	}
	
	@Override
	public void onModServerStopped2(cpw.mods.fml.common.event.FMLServerStoppedEvent aEvent) {
		// Insert your ServerStopped Code here and not above
	}
}
