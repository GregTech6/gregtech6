package gregapi.old;


public class GT_Spray_Hydration_Item extends GT_Tool_Item {
	public GT_Spray_Hydration_Item(String aUnlocalized, String aEnglish, int aMaxDamage, int aEntityDamage) {
		super(aUnlocalized, aEnglish, "To hydrate Crops and similar", aMaxDamage, aEntityDamage, true);/*
		setCraftingSound(SFX.IC_SPRAY);
		setBreakingSound(SFX.IC_SPRAY);
		setEntityHitSound(SFX.IC_SPRAY);
		setUsageAmounts(20, 3, 1);*/
	}/*
	
	@Override
    public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
		super.onItemUseFirst(aStack, aPlayer, aWorld, aX, aY, aZ, aSide, hitX, hitY, hitZ);
		if (aWorld.isRemote) {
    		return false;
    	}
    	Block aBlock = aWorld.getBlock(aX, aY, aZ);
    	if (aBlock == null) return false;
//    	byte aMeta = (byte)aWorld.getBlockMetadata(aX, aY, aZ);
    	TileEntity aTileEntity = UT.Worlds.getTileEntity(aWorld, aX, aY, aZ, true);
    	
    	try {
    		if (aTileEntity instanceof ic2.api.crops.ICropTile) {
    			int tCropBefore = ((ic2.api.crops.ICropTile)aTileEntity).getHydrationStorage();
	    		if (tCropBefore <= 100 && GT_ModHandler.damageOrDechargeItem(aStack, 1, 1000, aPlayer)) {
	    			((ic2.api.crops.ICropTile)aTileEntity).setHydrationStorage(tCropBefore+100);
	    			UT.Sounds.send(aWorld, SFX.IC_SPRAY, 1.0F, -1, aX, aY, aZ);
	    		}
        		return true;
    		}
    	} catch (Throwable e) {}
    	
    	return false;
    }*/
}