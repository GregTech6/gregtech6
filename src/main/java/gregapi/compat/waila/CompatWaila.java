package gregapi.compat.waila;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import gregapi.block.multitileentity.IMultiTileEntity;
import gregapi.compat.CompatBase;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

public class CompatWaila extends CompatBase implements IWailaDataProvider, ICompatWaila{
    @Override
    public void onLoad(FMLInitializationEvent aEvent) {
        FMLInterModComms.sendMessage("Waila", "register", "gregapi.compat.waila.CompatWaila.callbackRegister");
    }

    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity tileEntity = accessor.getTileEntity();
        if (!(tileEntity instanceof IMultiTileEntity.IMTE_WailaDetectable)) return null;
        return ((IMultiTileEntity.IMTE_WailaDetectable) tileEntity).getWailaStack(accessor, config);
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity tileEntity = accessor.getTileEntity();
        if (!(tileEntity instanceof IMultiTileEntity.IMTE_WailaDetectable)) return currenttip;
        return ((IMultiTileEntity.IMTE_WailaDetectable) tileEntity).getWailaHead(currenttip, accessor, config);
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity tileEntity = accessor.getTileEntity();
        if (!(tileEntity instanceof IMultiTileEntity.IMTE_WailaDetectable)) return currenttip;
        return ((IMultiTileEntity.IMTE_WailaDetectable) tileEntity).getWailaBody(currenttip, accessor, config);
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileEntity tileEntity = accessor.getTileEntity();
        if (!(tileEntity instanceof IMultiTileEntity.IMTE_WailaDetectable)) return currenttip;
        return ((IMultiTileEntity.IMTE_WailaDetectable) tileEntity).getWailaTail(currenttip, accessor, config);
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        if (te instanceof IMultiTileEntity.IMTE_WailaDetectable) return ((IMultiTileEntity.IMTE_WailaDetectable) te).getWailaNBT(te,tag);
        return tag;
    }

    public static void callbackRegister(IWailaRegistrar reg)
    {
        reg.registerStackProvider(new CompatWaila(), IMultiTileEntity.IMTE_WailaDetectable.class);
        reg.registerBodyProvider(new CompatWaila(), IMultiTileEntity.IMTE_WailaDetectable.class);
        reg.registerNBTProvider(new CompatWaila(), IMultiTileEntity.IMTE_WailaDetectable.class);

    }
}
