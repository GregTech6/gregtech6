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


public class GT_Spray_Bug_Item extends GT_Tool_Item {
	public GT_Spray_Bug_Item(String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, aEnglish, "A very 'buggy' Spray", aMaxDamage, aEntityDamage, true);/*
		addToEffectiveList(EntityCaveSpider.class.getName());
		addToEffectiveList(EntitySpider.class.getName());
		addToEffectiveList("EntityTFHedgeSpider");
		addToEffectiveList("EntityTFKingSpider");
		addToEffectiveList("EntityTFSwarmSpider");
		addToEffectiveList("EntityTFTowerBroodling");
		addToEffectiveList("EntityTFFireBeetle");
		addToEffectiveList("EntityTFSlimeBeetle");
		setCraftingSound(SFX.IC_SPRAY);
		setBreakingSound(SFX.IC_SPRAY);
		setEntityHitSound(SFX.IC_SPRAY);
		setUsageAmounts(8, 4, 1);*/
	}
	/*
	@Override
	public void onHitEntity(Entity aEntity) {
		if (aEntity instanceof EntityLiving) {
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.poison.getId(), 60, 1, false));
			((EntityLiving)aEntity).addPotionEffect(new PotionEffect(Potion.confusion.getId(), 600, 1, false));
		}
	}
	
	@Override
	public ItemStack getEmptiedItem(ItemStack aStack) {
		return ItemList.Spray_Empty.get(1);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aWorld.isRemote) {
			return false;
		}
		Block aBlock = aWorld.getBlock(aX, aY, aZ);
		if (aBlock == null) return false;
//      byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
		TileEntity aTileEntity = UT.Worlds.getTileEntity(aWorld, aX, aY, aZ, true);
		
		try {
			if (aTileEntity instanceof ic2.api.crops.ICropTile) {
				int tCropBefore = ((ic2.api.crops.ICropTile)aTileEntity).getWeedExStorage();
				if (tCropBefore <= 100 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
					((ic2.api.crops.ICropTile)aTileEntity).setWeedExStorage(tCropBefore+100);
					UT.Sounds.send(aWorld, SFX.IC_SPRAY, 1.0F, -1, aX, aY, aZ);
					return true;
				}
			}
		} catch (Throwable e) {}
		
		return false;
	}*/
}
