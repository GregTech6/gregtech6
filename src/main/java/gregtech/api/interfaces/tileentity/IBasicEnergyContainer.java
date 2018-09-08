package gregtech.api.interfaces.tileentity;

@Deprecated
public interface IBasicEnergyContainer extends IEnergyConnected {
	public boolean isUniversalEnergyStored(long aEnergyAmount);
	public long getUniversalEnergyStored();
	public long getUniversalEnergyCapacity();
	public long getOutputAmperage();
	public long getOutputVoltage();
	public long getInputAmperage();
	public long getInputVoltage();
	public boolean decreaseStoredEnergyUnits(long aEnergy, boolean aIgnoreTooLessEnergy);
	public boolean increaseStoredEnergyUnits(long aEnergy, boolean aIgnoreTooMuchEnergy);
	public boolean drainEnergyUnits(byte aSide, long aVoltage, long aAmperage);
	public long getAverageElectricInput();
	public long getAverageElectricOutput();
	public long getStoredEU();
	public long getEUCapacity();
	public long getStoredSteam();
	public long getSteamCapacity();
	public boolean increaseStoredSteam(long aEnergy, boolean aIgnoreTooMuchEnergy);
}