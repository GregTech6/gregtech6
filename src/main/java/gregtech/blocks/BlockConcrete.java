package gregtech.blocks;

import static gregapi.data.CS.*;

import java.util.List;

import gregapi.block.IBlockToolable;
import gregapi.block.ToolCompat;
import gregapi.block.metatype.BlockColored;
import gregapi.block.metatype.BlockMetaType;
import gregapi.block.metatype.ItemBlockMetaType;
import gregapi.data.CS.BlocksGT;
import gregapi.data.MT;
import gregapi.data.OP;
import gregapi.old.Textures;
import gregapi.oredict.OreDictItemData;
import gregapi.oredict.OreDictMaterial;
import gregapi.render.IIconContainer;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class BlockConcrete extends BlockColored implements IBlockToolable {
	public BlockConcrete(String aUnlocalised) {
		super(ItemBlockMetaType.class, Material.rock, soundTypeStone, aUnlocalised, "Concrete", null, 2.0F, 1.0F, 1, Textures.BlockIcons.CONCRETES);
		for (int i = 0; i < 16; i++) OM.reg(ST.make(this, 1, i), OP.stone, MT.Concrete);
	}
	
	@Override
	protected BlockMetaType makeSlab(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		return new BlockConcrete(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
	}
	
	protected BlockConcrete(Class<? extends ItemBlock> aItemClass, Material aVanillaMaterial, SoundType aVanillaSoundType, String aName, String aDefaultLocalised, OreDictMaterial aMaterial, float aResistanceMultiplier, float aHardnessMultiplier, int aHarvestLevel, int aCount, IIconContainer[] aIcons, byte aSlabType, BlockMetaType aBlock) {
		super(aItemClass, aVanillaMaterial, aVanillaSoundType, aName, aDefaultLocalised, aMaterial, aResistanceMultiplier, aHardnessMultiplier, aHarvestLevel, aCount, aIcons, aSlabType, aBlock);
		OM.data(ST.make(this, 1, W), new OreDictItemData(MT.Concrete, U2));
	}
	
	@Override
	public long onToolClick(String aTool, long aRemainingDurability, long aQuality, Entity aPlayer, List<String> aChatReturn, IInventory aPlayerInventory, boolean aSneaking, ItemStack aStack, World aWorld, byte aSide, int aX, int aY, int aZ, float aHitX, float aHitY, float aHitZ) {
		if (aTool.equals(TOOL_drill)) {
			if (mBlock == this && aPlayer instanceof EntityPlayer) {
				for (int i = 0; i < ((EntityPlayer)aPlayer).inventory.mainInventory.length; i++) {
					int tIndex = ((EntityPlayer)aPlayer).inventory.mainInventory.length-i-1;
					ItemStack tStack = ((EntityPlayer)aPlayer).inventory.mainInventory[tIndex];
					if (OM.is("stickAnyIronOrSteel", tStack)) {
						if (WD.set(aWorld, aX, aY, aZ, BlocksGT.ConcreteReinforced, WD.meta(aWorld, aX, aY, aZ), 3)) {
							if (!UT.Entities.hasInfiniteItems(aPlayer)) {
								if (--tStack.stackSize <= 0) {
									ForgeEventFactory.onPlayerDestroyItem(((EntityPlayer)aPlayer), tStack);
									((EntityPlayer)aPlayer).inventory.mainInventory[tIndex] = null;
								}
								if (((EntityPlayer)aPlayer).openContainer != null) ((EntityPlayer)aPlayer).openContainer.detectAndSendChanges();
							}
							return 10000;
						}
						break;
					}
				}
			}
		}
		return ToolCompat.onToolClick(this, aTool, aRemainingDurability, aQuality, aPlayer, aChatReturn, aPlayerInventory, aSneaking, aStack, aWorld, aSide, aX, aY, aZ, aHitX, aHitY, aHitZ);
	}
}