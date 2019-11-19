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

package gregapi.old;


public class GT_Spray_Ice_Item extends GT_Tool_Item {
	public GT_Spray_Ice_Item(String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, aEnglish, "Very effective against Slimes", aMaxDamage, aEntityDamage, true);/*
		addToEffectiveList(EntitySlime.class.getName());
		addToEffectiveList("BlueSlime");
		addToEffectiveList("SlimeClone");
		addToEffectiveList("MetalSlime");
		addToEffectiveList("EntityTFFireBeetle");
		addToEffectiveList("EntityTFMazeSlime");
		addToEffectiveList("EntityTFSlimeBeetle");
		setCraftingSound(SFX.IC_SPRAY);
		setBreakingSound(SFX.IC_SPRAY);
		setEntityHitSound(SFX.IC_SPRAY);
		setUsageAmounts(4, 16, 1);*/
		/*
		for (Object tName : Arrays.asList(UT.Stacks.make(Items.water_bucket, 1, W), OP.cell.dat(MT.Water), OP.capsule.dat(MT.Water))) {
			GT_ModHandler.addShapelessCraftingRecipe(UT.Stacks.make(Blocks.ice, 1, 0), new Object[] {UT.Stacks.make(this, 1, W), tName});
		}*/
	}
	/*
	@Override
	public void onHitEntity(Entity aEntity) {
		if (aEntity instanceof EntityLiving) {
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.weakness.getId(), 400, 2, false));
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 400, 2, false));
		}
	}
	*/
	/*
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aWorld.isRemote) {
			return false;
		}
		
		aX += OFFSETS_X[aSide]; aY += OFFSETS_Y[aSide]; aZ += OFFSETS_Z[aSide];
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == null) return false;
		byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
//      TileEntity aTileEntity = aWorld.getTileEntity(aX, aY, aZ);
		
		if (aBlock == Blocks.water || aBlock == Blocks.flowing_water) {
			if (aMeta == 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				UT.Sounds.send(aWorld, SFX.IC_SPRAY, 1.0F, -1, aX, aY, aZ);
				aWorld.setBlock(aX, aY, aZ, Blocks.ice, 0, 3);
				return true;
			}
			return false;
		}
		
		if (aBlock == Blocks.lava || aBlock == Blocks.flowing_lava) {
			if (aMeta == 0 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
				UT.Sounds.send(aWorld, SFX.IC_SPRAY, 1.0F, -1, aX, aY, aZ);
				aWorld.setBlock(aX, aY, aZ, Blocks.obsidian, 0, 3);
				return true;
			}
			return false;
		}
		return false;
	}*/
}
