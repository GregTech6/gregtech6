package gregapi.block;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Gregorius Techneticies
 */
public interface IBlockBase extends IBlock {
	public String name(int aMeta);
	public boolean useGravity(int aMeta);
	public boolean doesWalkSpeed(short aMeta);
	public boolean canCreatureSpawn(int aMeta);
	public boolean isSealable(int aMeta, byte aSide);
	public void addInformation(ItemStack aStack, int aMeta, EntityPlayer aPlayer, List aList, boolean aF3_H);
	public float getExplosionResistance(int aMeta);
	public int getItemStackLimit(ItemStack aStack);
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer);
}