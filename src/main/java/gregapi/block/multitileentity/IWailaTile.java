package gregapi.block.multitileentity;

import gregapi.code.TagData;
import gregapi.data.LH;
import gregapi.fluid.FluidTankGT;
import gregapi.tileentity.energy.ITileEntityEnergy;
import gregapi.tileentity.machines.ITileEntityRunningActively;
import gregapi.tileentity.machines.ITileEntityRunningPassively;
import gregapi.tileentity.machines.ITileEntityRunningPossible;
import gregapi.tileentity.machines.ITileEntitySwitchableOnOff;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.List;

public interface IWailaTile {
    /**
     * Modify the Stack of waila displaying
     **/
    public default ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    /**
     * Modify the Head info, default is the Stack name, super call is required if you used getWailaInfos().
     **/
    public default List<String> getWailaHead(List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        Arrays.stream(getWailaInfos()).forEach(info-> info.getWailaHead(currentTip,accessor,config));
        return currentTip;
    }

    /**
     * Modify the body info, default empty, super call is required if you used getWailaInfos().
     **/
    public default List<String> getWailaBody(List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        Arrays.stream(getWailaInfos()).forEach(info-> info.getWailaBody(currentTip,accessor,config));
        return currentTip;
    }

    /**
     * Modify the tail info, default the mod name, super call is required if you used getWailaInfos().
     **/
    public default List<String> getWailaTail(List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        Arrays.stream(getWailaInfos()).forEach(info-> info.getWailaTail(currentTip,accessor,config));
        return currentTip;
    }

    /**
     * Modify the NBT you need to sync, default empty, super call is required if you used getWailaInfos().
     **/
    public default NBTTagCompound getWailaNBT(TileEntity te, NBTTagCompound aNBT) {
        Arrays.stream(getWailaInfos()).forEach(info-> info.getWailaNBT(te,aNBT));
        return aNBT;
    }

    /**
     * the simplified infos, return an instance of IWailaInfoProvider below and show that infos.
     **/
    public default IWailaInfoProvider[] getWailaInfos(){
        return new IWailaInfoProvider[0];
    }

    public static List<String> addTankDesc(List<String> current, String prefix, FluidTankGT mTank, String suffix) {
        if (mTank == null || mTank.isEmpty()) return current;
        current.add(prefix + LH.Chat.WHITE + mTank.getFluid().amount + "/" + mTank.getCapacity() + LH.Chat.CYAN + " L " + LH.Chat.WHITE + mTank.getFluid().getLocalizedName() + suffix);
        return current;
    }


    public static List<String> addFluidStackDesc(List<String> current, String prefix, FluidStack stack, String suffix) {
        if (stack == null || stack.amount <= 0) return current;
        current.add(prefix + LH.Chat.WHITE + stack.amount + LH.Chat.CYAN + " L " + LH.Chat.WHITE + stack.getLocalizedName() + suffix);
        return current;
    }

    public static List<String> addEnergyFlowDesc(List<String> current, String prefix, TagData energyType, long voltage, long ampere, String suffix) {
        String content;
        if (ampere == 1) content = prefix + LH.Chat.WHITE + voltage + energyType.getLocalisedChatNameShort() + LH.Chat.WHITE + "/A" + LH.Chat.WHITE;
        else content = prefix + LH.Chat.WHITE + voltage + energyType.getLocalisedChatNameShort() + LH.Chat.WHITE + "/A * " + LH.Chat.CYAN + ampere + "A/t" + LH.Chat.WHITE;

        for (int i = 0; i < current.size(); i++) if(current.get(i).startsWith(content)) {
            current.set(i, current.get(i).concat(suffix));
            return current;
        }
        current.add(content+suffix);
        return current;
    }

    public static List<String> addEnergyFlowDesc(List<String> current, String prefix, TagData energyType, long voltageMin, long voltageMax, long ampere, String suffix) {
        String content;
        if (ampere == 1) content = prefix + LH.Chat.WHITE + voltageMin + " - " + voltageMax + energyType.getLocalisedChatNameShort() + LH.Chat.WHITE + "/A" + LH.Chat.WHITE;
        else content = prefix + LH.Chat.WHITE + voltageMin + " - " + voltageMax + energyType.getLocalisedChatNameShort() + LH.Chat.WHITE + "/A * " + LH.Chat.CYAN + ampere + "A/t" + LH.Chat.WHITE;

        for (int i = 0; i < current.size(); i++) if(current.get(i).startsWith(content)) {
            current.set(i, current.get(i).concat(suffix));
            return current;
        }
        current.add(content+suffix);
        return current;
    }

    public static List<String> addEnergyAmountDesc(List<String> current, String prefix, TagData energyType, long amount, String suffix) {
        current.add(prefix + LH.Chat.WHITE + amount + energyType.getLocalisedChatNameShort() + LH.Chat.WHITE + suffix);
        return current;
    }

    public interface IWailaInfoProvider extends IWailaTile {
        public default IWailaInfoProvider[] asArray(){
            return new IWailaInfoProvider[]{this};
        }
    }

    /**display state infos, using method: ITileEntitySwitchableOnOff.getStateOnOff(), ITileEntityRunningPossible.getStateRunningPossible(), ITileEntityRunningPassively.getStateRunningPassively(), ITileEntityRunningActively.getStateRunningActively()**/
    public static final InfoState instanceInfoState = new InfoState();
    public class InfoState implements IWailaInfoProvider {
        public static final byte STATE_NOT_POSSIBLE=0, STATE_STOPPED_FORCE = 1, STATE_STOPPED = 2, STATE_READY = 3, STATE_PASSIVE = 4, STATE_ACTIVE = 5;

        public byte getState(TileEntity te){
            if (te instanceof ITileEntitySwitchableOnOff && !((ITileEntitySwitchableOnOff) te).getStateOnOff()) return STATE_STOPPED_FORCE;

            if (te instanceof ITileEntityRunningPossible) {
                byte state = STATE_STOPPED;
                if(((ITileEntityRunningPossible) te).getStateRunningPossible())state = STATE_READY;
                if (te instanceof ITileEntityRunningPassively && ((ITileEntityRunningPassively) te).getStateRunningPassively()) state = STATE_PASSIVE;
                if (te instanceof ITileEntityRunningActively && ((ITileEntityRunningActively) te).getStateRunningActively()) state = STATE_ACTIVE;
                return state;
            }
            return STATE_NOT_POSSIBLE;
        }
        public NBTTagCompound getWailaNBT(TileEntity te, NBTTagCompound aNBT) {
            aNBT.setByte("gt.waila.state", getState(te));
            return aNBT;
        }

        public List<String> getWailaBody(List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
            if(!accessor.getNBTData().hasKey("gt.waila.state"))return currentTip;
            byte state = accessor.getNBTData().getByte("gt.waila.state");
            if (state == STATE_STOPPED_FORCE) currentTip.add(LH.get(LH.STATE) + " " + LH.Chat.YELLOW + LH.get(LH.STATE_STOPPED_FORCE));
            else if (state == STATE_ACTIVE)   currentTip.add(LH.get(LH.STATE) + " " + LH.Chat.GREEN + LH.get(LH.STATE_ACTIVE));
            else if (state == STATE_PASSIVE)  currentTip.add(LH.get(LH.STATE) + " " + LH.Chat.BLUE + LH.get(LH.STATE_PASSIVE));
            else if (state == STATE_READY)    currentTip.add(LH.get(LH.STATE) + " " + LH.Chat.RED + LH.get(LH.STATE_STOPPED) + LH.Chat.WHITE + " | " + LH.Chat.GREEN + LH.get(LH.STATE_READY));
            else if (state == STATE_STOPPED)  currentTip.add(LH.get(LH.STATE) + " " + LH.Chat.RED + LH.get(LH.STATE_STOPPED));
            return currentTip;
        }
    }
    /**display energy IO infos in range, using ITileEntityEnergy, note this only displays Voltage information **/
    public static final InfoEnergyIORange instanceInfoEnergyIORange = new InfoEnergyIORange();
    public class InfoEnergyIORange implements IWailaInfoProvider {
        public List<String> getWailaBody(List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
            if (!(accessor.getTileEntity() instanceof ITileEntityEnergy))return currentTip;

            ITileEntityEnergy tile = (ITileEntityEnergy) accessor.getTileEntity();
            for (byte i = 0; i < 6; i++) for (TagData energyType : tile.getEnergyTypes(i)) {
                if (!tile.isEnergyAcceptingFrom(energyType, i, true)) continue;
                long vMin = tile.getEnergySizeInputMin(energyType, i), vMax = tile.getEnergySizeInputMax(energyType, i);
                if(vMin == vMax)addEnergyFlowDesc(currentTip, LH.get(LH.ENERGY_INPUT)+" ", energyType, vMin, 1, "");
                else            addEnergyFlowDesc(currentTip, LH.get(LH.ENERGY_INPUT)+" ", energyType, vMin, vMax, 1, "");
            }
            for (byte i = 0; i < 6; i++) for (TagData energyType : tile.getEnergyTypes(i)) {
                if (!tile.isEnergyEmittingTo(energyType, i, true)) continue;
                long vMin =  tile.getEnergySizeOutputMin(energyType, i), vMax = tile.getEnergySizeOutputMax(energyType, i);
                if(vMin == vMax)addEnergyFlowDesc(currentTip, LH.get(LH.ENERGY_OUTPUT)+" ", energyType, vMin, 1, "");
                else            addEnergyFlowDesc(currentTip, LH.get(LH.ENERGY_OUTPUT)+" ", energyType, vMin, vMax, 1, "");
            }
            return currentTip;
        }
    }
    /**display energy IO infos only recommended, using ITileEntityEnergy, note this only displays Voltage information **/
    public static final InfoEnergyIORec instanceInfoEnergyIORec = new InfoEnergyIORec();
    public class InfoEnergyIORec implements IWailaInfoProvider {
        public List<String> getWailaBody(List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
            if (!(accessor.getTileEntity() instanceof ITileEntityEnergy))return currentTip;

            ITileEntityEnergy tile = (ITileEntityEnergy) accessor.getTileEntity();
            for (byte i = 0; i < 6; i++) for (TagData energyType : tile.getEnergyTypes(i)) {
                if (!tile.isEnergyEmittingTo(energyType, i, true)) continue;
                addEnergyFlowDesc(currentTip, LH.get(LH.ENERGY_OUTPUT)+" ", energyType, tile.getEnergySizeOutputRecommended(energyType, i), 1, "");
            }

            for (byte i = 0; i < 6; i++) for (TagData energyType : tile.getEnergyTypes(i)) {
                if (!tile.isEnergyAcceptingFrom(energyType, i, true)) continue;
                addEnergyFlowDesc(currentTip, LH.get(LH.ENERGY_INPUT)+" ", energyType, tile.getEnergySizeInputRecommended(energyType, i), 1, "");
            }
            return currentTip;
        }
    }
}
