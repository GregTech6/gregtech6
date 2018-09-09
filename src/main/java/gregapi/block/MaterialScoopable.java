package gregapi.block;

import static gregapi.data.CS.*;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialScoopable extends Material {
	public static MaterialScoopable instance = new MaterialScoopable();
	
	private MaterialScoopable() {
		super(MapColor.foliageColor);
		setRequiresTool();
		setImmovableMobility();
	}
	
    @Override
	public boolean isOpaque() {
        return F;
    }
}