package gregapi.wooddict;

import gregapi.code.ItemStackContainer;
import gregapi.code.ItemStackMap;

/**
 * @author Gregorius Techneticies
 */
public class WoodDictionary {
	public static final ItemStackMap<ItemStackContainer, PlankEntry> PLANKS = new ItemStackMap();
	public static final ItemStackMap<ItemStackContainer, BeamEntry> BEAMS = new ItemStackMap();
	public static final ItemStackMap<ItemStackContainer, WoodEntry> WOODS = new ItemStackMap();
	public static final ItemStackMap<ItemStackContainer, SaplingEntry> SAPLINGS = new ItemStackMap();
	public static final ItemStackMap<ItemStackContainer, LeafEntry> LEAVES = new ItemStackMap();
	
	public static BeamEntry DEFAULT_BEAM;
	public static PlankEntry DEFAULT_PLANK;
}
