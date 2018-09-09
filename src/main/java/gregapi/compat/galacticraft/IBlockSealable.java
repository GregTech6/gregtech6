package gregapi.compat.galacticraft;

import cpw.mods.fml.common.Optional;
import gregapi.data.CS.ModIDs;
import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;

/**
 * @author Gregorius Techneticies
 */
@Optional.InterfaceList(value = {
	@Optional.Interface(iface = "micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock", modid = ModIDs.GC)
})
public interface IBlockSealable extends IPartialSealableBlock {
	//
}