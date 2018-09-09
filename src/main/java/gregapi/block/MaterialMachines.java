package gregapi.block;

import static gregapi.data.CS.*;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialMachines extends Material {
	public static MaterialMachines instance = new MaterialMachines();
	
	private MaterialMachines() {
		super(MapColor.ironColor);
		setRequiresTool();
		setImmovableMobility();
	}
	
    @Override
	public boolean isOpaque() {
        return F;
    }
}