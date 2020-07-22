/**
 * Copyright (c) 2020 GregTech-6 Team
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

package gregapi.item.multiitem;

import static gregapi.data.CS.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackSet;
import gregapi.code.ObjectStack;
import gregapi.data.IL;
import gregapi.data.LH;
import gregapi.data.MD;
import gregapi.data.MT;
import gregapi.data.TC.TC_AspectStack;
import gregapi.data.TD;
import gregapi.enchants.Enchantment_Radioactivity;
import gregapi.item.IItemEnergy;
import gregapi.item.IItemGTContainerTool;
import gregapi.item.IItemGTHandTool;
import gregapi.item.multiitem.energy.EnergyStat;
import gregapi.item.multiitem.tools.IToolStats;
import gregapi.old.Textures;
import gregapi.oredict.OreDictMaterial;
import gregapi.util.OM;
import gregapi.util.ST;
import gregapi.util.UT;
import gregapi.util.WD;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

/**
 * @author Gregorius Techneticies
 * 
 * This is an example on how you can create a Tool ItemStack, in this case a Bismuth Wrench:
 * gregapi.data.CS.ToolsGT.sMetaTool.getToolWithStats(CS.ToolIDs.WRENCH, 1, MT.Bismuth, MT.Bismuth, null);
 */
public class MultiItemTool extends MultiItem implements IItemGTHandTool, IItemGTContainerTool {
	public final HashMap<Short, IToolStats> mToolStats = new HashMap<>();
	
	public static ChunkCoordinates LAST_TOOL_COORDS_BEFORE_DAMAGE = null;
	
	/**
	 * Creates the Item using these Parameters.
	 * @param aUnlocalized The unlocalised Name of this Item. DO NOT START YOUR UNLOCALISED NAME WITH "gt."!!!
	 */
	public MultiItemTool(String aModID, String aUnlocalized) {
		super(aModID, aUnlocalized);
		setMaxStackSize(1);
	}
	
	/**
	 * This adds a Custom Item to the ending Range.
	 * @param aID The Id of the assigned Tool Class [0 - 32765] (only even Numbers allowed! Uneven ID's are empty electric Items)
	 * @param aEnglish The Default Localized Name of the created Item
	 * @param aToolTip The Default ToolTip of the created Item, you can also insert null for having no ToolTip
	 * @param aToolStats The Food Value of this Item. Can be null as well.
	 * @param aRandomParameters The OreDict Names you want to give the Item. Also used to assign Thaumcraft Aspects.
	 * @return An ItemStack containing the newly created Item, but without specific Stats.
	 */
	public final ItemStack addTool(int aID, String aEnglish, String aToolTip, IToolStats aToolStats, Object... aRandomParameters) {
		if (aToolTip == null) aToolTip = "";
		if (aID >= 0 && aID < 32766 && aID % 2 == 0) {
			LH.add(getUnlocalizedName() + "." +  aID    + ".name"       , aEnglish);
			LH.add(getUnlocalizedName() + "." +  aID    + ".tooltip"    , aToolTip);
			LH.add(getUnlocalizedName() + "." + (aID+1) + ".name"       , aEnglish + " (Empty)");
			LH.add(getUnlocalizedName() + "." + (aID+1) + ".tooltip"    , "You need to recharge it");
			mToolStats.put((short) aID   , aToolStats);
			mToolStats.put((short)(aID+1), aToolStats);
			aToolStats.onStatsAddedToTool(this, aID);
			ItemStack rStack = ST.make(this, 1, aID);
			List<TC_AspectStack> tAspects = new ArrayListNoNulls<>();
			for (Object tRandomParameter : aRandomParameters) {
				if (tRandomParameter instanceof TC_AspectStack)
					((TC_AspectStack)tRandomParameter).addToAspectList(tAspects);
				else if (tRandomParameter instanceof ItemStackSet)
					((ItemStackSet<?>)tRandomParameter).add(rStack.copy());
				else
					OM.reg(tRandomParameter, rStack);
			}
			if (COMPAT_TC != null) COMPAT_TC.registerThaumcraftAspectsToItem(rStack, tAspects, F);
			return rStack;
		}
		return null;
	}

	/**
	 * This Function gets an ItemStack Version of this Tool
	 * @param aToolID the ID of the Tool Class
	 * @param aPrimaryMaterial Primary Material of this Tool
	 * @param aSecondaryMaterial Secondary (Rod/Handle) Material of this Tool
	 */
	public final ItemStack getToolWithStats(int aToolID, OreDictMaterial aPrimaryMaterial, OreDictMaterial aSecondaryMaterial) {
		return getToolWithStats(aToolID, 1, aPrimaryMaterial, aSecondaryMaterial);
	}
	
	/**
	 * This Function gets an ItemStack Version of this Tool
	 * @param aToolID the ID of the Tool Class
	 * @param aAmount Amount of Items (well normally you only need 1)
	 * @param aPrimaryMaterial Primary Material of this Tool
	 * @param aSecondaryMaterial Secondary (Rod/Handle) Material of this Tool
	 */
	public final ItemStack getToolWithStats(int aToolID, int aAmount, OreDictMaterial aPrimaryMaterial, OreDictMaterial aSecondaryMaterial) {
		return getToolWithStats(aToolID, aAmount, aPrimaryMaterial, aSecondaryMaterial, 0, 0);
	}
	
	/**
	 * This Function gets an ItemStack Version of this Tool
	 * @param aToolID the ID of the Tool Class
	 * @param aAmount Amount of Items (well normally you only need 1)
	 * @param aPrimaryMaterial Primary Material of this Tool
	 * @param aSecondaryMaterial Secondary (Rod/Handle) Material of this Tool
	 */
	public final ItemStack getToolWithStats(int aToolID, int aAmount, OreDictMaterial aPrimaryMaterial, OreDictMaterial aSecondaryMaterial, long aMaxCharge, long aVoltage) {
		ItemStack rStack = ST.make(this, aAmount, aToolID);
		IToolStats tToolStats = getToolStats(rStack);
		if (tToolStats != null) {
			NBTTagCompound tMainNBT = UT.NBT.make(), tToolNBT = UT.NBT.make();
			if (aPrimaryMaterial != null) {
				if (aPrimaryMaterial.mID > 0) tToolNBT.setShort("a", aPrimaryMaterial.mID); else tToolNBT.setString("b", aPrimaryMaterial.toString());
				UT.NBT.setNumber(tToolNBT, "j", 100L*(long)(aPrimaryMaterial.mToolDurability * tToolStats.getMaxDurabilityMultiplier()));
			}
			if (aSecondaryMaterial != null) {
				if (aSecondaryMaterial.mID > 0) tToolNBT.setShort("c", aSecondaryMaterial.mID); else tToolNBT.setString("d", aSecondaryMaterial.toString());
			}
			if (aMaxCharge > 0) {
				tToolNBT.setBoolean("e", T);
				UT.NBT.setNumber(tToolNBT, "f", aMaxCharge);
				UT.NBT.setNumber(tToolNBT, "g", aVoltage);
			}
			tMainNBT.setTag("GT.ToolStats", tToolNBT);
			UT.NBT.set(rStack, tMainNBT);
		}
		isItemStackUsable(rStack);
		return rStack;
	}
	
	/**
	 * Called by the Block Harvesting Event within the GT_Proxy
	 */
	public void onHarvestBlockEvent(ArrayList<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, int aX, int aY, int aZ, byte aMeta, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		if (ST.torch(aBlock, aMeta)) return;
		IToolStats tStats = getToolStats(aStack);
		if (isItemStackUsable(aStack) && getDigSpeed(aStack, aBlock, aMeta) > 0) {
			int tDamage = tStats.convertBlockDrops(aDrops, aStack, aPlayer, aBlock, (getToolMaxDamage(aStack) - getToolDamage(aStack)) / tStats.getToolDamagePerDropConversion(), aX, aY, aZ, aMeta, aFortune, aSilkTouch, aEvent);
			if (aBlock == Blocks.ice && !aDrops.isEmpty()) aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
			if (WD.dimBTL(aPlayer.worldObj) && !getPrimaryMaterial(aStack).contains(TD.Properties.BETWEENLANDS)) tDamage *= 4;
			if (!UT.Entities.hasInfiniteItems(aPlayer)) doDamage(aStack, tDamage * tStats.getToolDamagePerDropConversion(), aPlayer);
		}
	}
	
	public boolean canCollectDropsDirectly(ItemStack aStack, Block aBlock, byte aMeta) {
		if (ST.torch(aBlock, aMeta)) return T;
		IToolStats tStats = getToolStats(aStack);
		return (tStats.canCollect() || getPrimaryMaterial(aStack).contains(TD.Properties.MAGNETIC_ACTIVE)) && isItemStackUsable(aStack) && getDigSpeed(aStack, aBlock, aMeta) > 0;
	}
	
	public float onBlockBreakSpeedEvent(float aDefault, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, int aX, int aY, int aZ, byte aMeta, PlayerEvent.BreakSpeed aEvent) {
		if (ST.torch(aBlock, aMeta)) return Float.MAX_VALUE;
		IToolStats tStats = getToolStats(aStack);
		return tStats == null ? aDefault : tStats.getMiningSpeed(aBlock, aMeta, aDefault, aPlayer, aPlayer.worldObj, aX, aY, aZ);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack aStack, EntityPlayer aPlayer, Entity aEntity) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null || !isItemStackUsable(aStack)) return T;
		if (TOOL_SOUNDS) UT.Sounds.play(tStats.getEntityHitSound(), 20, 1, aEntity);
		if (super.onLeftClickEntity(aStack, aPlayer, aEntity)) return T;
		if (aEntity.canAttackWithItem()) {
			int tFireAspect = EnchantmentHelper.getFireAspectModifier(aPlayer);
			boolean tIgnitesFire = !aEntity.isBurning() && tFireAspect > 0 && aEntity instanceof EntityLivingBase;
			if (tIgnitesFire) aEntity.setFire(1);
			if (aEntity.hitByEntity(aPlayer)) {
				if (tIgnitesFire) aEntity.extinguish();
			} else {
				float tMagicDamage = tStats.getMagicDamageAgainstEntity(aEntity instanceof EntityLivingBase?EnchantmentHelper.getEnchantmentModifierLiving(aPlayer, (EntityLivingBase)aEntity):0, aEntity, aStack, aPlayer), tDamage = tStats.getNormalDamageAgainstEntity((float)aPlayer.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue() + getToolCombatDamage(aStack), aEntity, aStack, aPlayer);
				if (tDamage + tMagicDamage > 0) {
					boolean tCriticalHit = aPlayer.fallDistance > 0 && !aPlayer.onGround && !aPlayer.isOnLadder() && !aPlayer.isInWater() && !aPlayer.isPotionActive(Potion.blindness) && aPlayer.ridingEntity == null && aEntity instanceof EntityLivingBase;
					if (tCriticalHit && tDamage > 0) tDamage *= 1.5;
					float tFullDamage = (MD.TFC.mLoaded || MD.TFCP.mLoaded ? (tDamage+tMagicDamage) * TFC_DAMAGE_MULTIPLIER : (tDamage+tMagicDamage));
					// Avoiding the Betweenlands Damage Cap in a fair way. Only Betweenlands Materials will avoid it. And maybe some super Lategame Items.
					if (MD.BTL.mLoaded && aEntity.getClass().getName().startsWith("thebetweenlands") && getPrimaryMaterial(aStack).contains(TD.Properties.BETWEENLANDS)) {
						float tDamageToDeal = tFullDamage;
						DamageSource tSource = tStats.getDamageSource(aPlayer, aEntity);
						
						while (tDamageToDeal > 0 && aEntity.attackEntityFrom(tSource, Math.min(tDamageToDeal, 12) / 0.3F)) {
							tDamageToDeal -= 12;
							if (tDamageToDeal > 0) aEntity.hurtResistantTime = 0;
						}
						if (tDamageToDeal < tFullDamage) {
							tStats.afterDealingDamage(tDamage, tMagicDamage, tFireAspect, tCriticalHit, aEntity, aStack, aPlayer);
							if (!UT.Entities.hasInfiniteItems(aPlayer)) doDamage(aStack, tStats.getToolDamagePerEntityAttack(), aPlayer);
						}
					} else {
						if (aEntity.attackEntityFrom(tStats.getDamageSource(aPlayer, aEntity), tFullDamage)) {
							tStats.afterDealingDamage(tDamage, tMagicDamage, tFireAspect, tCriticalHit, aEntity, aStack, aPlayer);
							if (!UT.Entities.hasInfiniteItems(aPlayer)) doDamage(aStack, tStats.getToolDamagePerEntityAttack(), aPlayer);
						}
					}
				}
			}
		}
		if (aStack.stackSize <= 0) aPlayer.destroyCurrentEquippedItem();
		return T;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats != null && tStats.canBlock()) aPlayer.setItemInUse(aStack, 72000);
		return super.onItemRightClick(aStack, aWorld, aPlayer);
	}
	
	@Override
	public final int getMaxItemUseDuration(ItemStack aStack) {
		return 72000;
	}
	
	@Override
	public final EnumAction getItemUseAction(ItemStack aStack) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats != null && tStats.canBlock()) return EnumAction.block;
		return EnumAction.none;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings("unchecked")
	public final void getSubItems(Item var1, CreativeTabs aCreativeTab, @SuppressWarnings("rawtypes") List aList) {
		for (int i = 0; i < 32766; i+=2) if (getToolStats(ST.make(this, 1, i)) != null) {
			ItemStack tStack = ST.make(this, 1, i);
			isItemStackUsable(tStack);
			aList.add(tStack);
		}
	}
	
	@Override
	public void addAdditionalToolTips(List<String> aList, ItemStack aStack, boolean aF3_H) {
		long tMaxDamage = getToolMaxDamage(aStack), tDamage = getToolDamage(aStack);
		OreDictMaterial tMaterial = getPrimaryMaterial(aStack, MT.NULL);
		IToolStats tStats = getToolStats(aStack);
		if (tMaxDamage > 0 && tStats != null) {
			aList.add(LH.Chat.WHITE + "Durability: " + LH.Chat.GREEN + (tMaxDamage - tDamage) + " / " + tMaxDamage + LH.Chat.GRAY);
			aList.add(LH.Chat.WHITE + tMaterial.getLocal() + LH.Chat.YELLOW + " lvl " + getHarvestLevel(aStack, "") + LH.Chat.GRAY);
			float tCombat = getToolCombatDamage(aStack);
			aList.add(LH.Chat.WHITE + "Attack Damage: " + LH.Chat.BLUE + "+" + (((MD.TFC.mLoaded || MD.TFCP.mLoaded) ? tCombat * TFC_DAMAGE_MULTIPLIER : tCombat) + LH.Chat.RED + " (= " + ((MD.TFC.mLoaded || MD.TFCP.mLoaded) ? ((tCombat+1)*(TFC_DAMAGE_MULTIPLIER/2.0)) + ")" : ((tCombat+1)/2) + " Hearts)"))  + LH.Chat.GRAY);
			aList.add(LH.Chat.WHITE + "Mining Speed: " + LH.Chat.PINK + Math.max(Float.MIN_NORMAL, tStats.getSpeedMultiplier() * getPrimaryMaterial(aStack, MT.NULL).mToolSpeed) + LH.Chat.GRAY);
			aList.add(LH.Chat.WHITE + "Crafting Uses: " + LH.Chat.GREEN + UT.Code.divup(getEnergyStats(aStack) == null ? tMaxDamage - tDamage : getEnergyStored(TD.Energy.EU, aStack), tStats.getToolDamagePerContainerCraft()) + LH.Chat.GRAY);
			if (MD.BTL.mLoaded && tMaterial.contains(TD.Properties.BETWEENLANDS)) aList.add(LH.Chat.GREEN + LH.get(LH.TOOLTIP_BETWEENLANDS_RESISTANCE));
			if (IL.TF_Mazestone.exists() && tMaterial.contains(TD.Properties.MAZEBREAKER) && canHarvestBlock(IL.TF_Mazestone.block(), aStack)) aList.add(LH.Chat.PINK + LH.get(LH.TOOLTIP_TWILIGHT_MAZE_BREAKING));
			if (tStats.canCollect() || getPrimaryMaterial(aStack).contains(TD.Properties.MAGNETIC_ACTIVE)) aList.add(LH.Chat.DGRAY + LH.get(LH.TOOLTIP_AUTOCOLLECT));
		}
	}
	
	public static final OreDictMaterial getPrimaryMaterial(ItemStack aStack, OreDictMaterial aDefault) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("GT.ToolStats");
			if (aNBT != null) {
				if (aNBT.hasKey("a")) return OreDictMaterial.MATERIAL_ARRAY[aNBT.getShort("a")];
				if (aNBT.hasKey("b")) return OreDictMaterial.get(aNBT.getString("b"));
				return OreDictMaterial.get(aNBT.getString("PrimaryMaterial"));
			}
		}
		return aDefault;
	}
	
	public static final OreDictMaterial getPrimaryMaterial(ItemStack aStack) {
		return getPrimaryMaterial(aStack, MT.NULL);
	}
	
	public static final OreDictMaterial getSecondaryMaterial(ItemStack aStack, OreDictMaterial aDefault) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("GT.ToolStats");
			if (aNBT != null) {
				if (aNBT.hasKey("c")) return OreDictMaterial.MATERIAL_ARRAY[aNBT.getShort("c")];
				if (aNBT.hasKey("d")) return OreDictMaterial.get(aNBT.getString("d"));
				return OreDictMaterial.get(aNBT.getString("SecondaryMaterial"));
			}
		}
		return aDefault;
	}
	
	public static final OreDictMaterial getSecondaryMaterial(ItemStack aStack) {
		return getSecondaryMaterial(aStack, MT.NULL);
	}
	
	@Override
	public IItemEnergy getEnergyStats(ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("GT.ToolStats");
			if (aNBT != null) {
				if (aNBT.getBoolean("e")) return EnergyStat.makeTool(TD.Energy.EU, aNBT.getLong("f"), aNBT.getLong("g"), 1, ST.make(aStack.getItem(), 1, getEmptyMetaData(aStack)), ST.make(aStack.getItem(), 1, getChargedMetaData(aStack)), ST.make(aStack.getItem(), 1, getChargedMetaData(aStack)));
			}
		}
		return null;
	}
	
	public float getToolCombatDamage(ItemStack aStack) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return 0;
		return tStats.getBaseDamage() + getPrimaryMaterial(aStack, MT.NULL).mToolQuality;
	}
	
	public static final long getToolMaxDamage(ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("GT.ToolStats");
			if (aNBT.hasKey("j")) return Math.max(1, aNBT.getLong("j"));
			return Math.max(1, aNBT.getLong("MaxDamage"));
		}
		return 1;
	}
	
	public static final long getToolDamage(ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("GT.ToolStats");
			if (aNBT.hasKey("k")) return aNBT.getLong("k");
			return aNBT.getLong("Damage");
		}
		return 0;
	}
	
	public static final boolean setToolDamage(ItemStack aStack, long aDamage) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			UT.NBT.setNumber(aNBT.getCompoundTag("GT.ToolStats"), "k", aDamage);
			return T;
		}
		return F;
	}
	
	public final boolean doDamage(ItemStack aStack, long aAmount) {
		return doDamage(aStack, aAmount, null);
	}
	
	public final boolean doDamage(ItemStack aStack, long aAmount, EntityLivingBase aPlayer) {
		if (!isItemStackUsable(aStack)) return F;
		IItemEnergy tElectric = getEnergyStats(aStack);
		if (tElectric == null || RNGSUS.nextInt(Math.max(5, getPrimaryMaterial(aStack, MT.NULL).mToolQuality * 25)) == 0) {
			long tNewDamage = getToolDamage(aStack) + aAmount;
			setToolDamage(aStack, tNewDamage);
			if (tNewDamage >= getToolMaxDamage(aStack)) {
				IToolStats tStats = getToolStats(aStack);
				if (tStats == null) {
					aStack.stackSize = 0;
				} else {
					if (TOOL_SOUNDS) {
						if (aPlayer == null) {
							if (LAST_TOOL_COORDS_BEFORE_DAMAGE == null) {
								UT.Sounds.play(tStats.getBreakingSound(), 200, 1);
							} else {
								UT.Sounds.play(tStats.getBreakingSound(), 200, 1, LAST_TOOL_COORDS_BEFORE_DAMAGE);
								LAST_TOOL_COORDS_BEFORE_DAMAGE = null;
							}
						} else {
							UT.Sounds.play(tStats.getBreakingSound(), 200, 1, aPlayer);
							LAST_TOOL_COORDS_BEFORE_DAMAGE = null;
						}
					}
					ItemStack tBroken = tStats.getBrokenItem(aStack);
					if (ST.invalid(tBroken) || tBroken.stackSize <= 0) {
						aStack.stackSize = 0;
					} else if (aPlayer instanceof EntityPlayer) {
						if (tBroken.stackSize > 64) tBroken.stackSize = 64;
						if (!aPlayer.worldObj.isRemote) UT.Inventories.addStackToPlayerInventoryOrDrop((EntityPlayer)aPlayer, tBroken, F);
						aStack.stackSize = 0;
					} else {
						if (tBroken.stackSize > 64) tBroken.stackSize = 64;
						ST.set(aStack, tBroken);
					}
				}
			}
			return tElectric == null || useEnergy(TD.Energy.EU, aStack, aAmount, aPlayer, null, null, 0, 0, 0, T);
		}
		return useEnergy(TD.Energy.EU, aStack, aAmount, aPlayer, null, null, 0, 0, 0, T);
	}
	
	@Override
	public float getDigSpeed(ItemStack aStack, Block aBlock, int aMeta) {
		if (aBlock == NB || aBlock == Blocks.bedrock) return 0;
		if (ST.torch(aBlock, aMeta)) return 10;
		if (!isItemStackUsable(aStack)) return 0;
		float tMultiplier = 1.0F;
		if (IL.TF_Mazestone.equal(aBlock) && getPrimaryMaterial(aStack).contains(TD.Properties.MAZEBREAKER)) tMultiplier *= 40;
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null || Math.max(0, getHarvestLevel(aStack, "")) < aBlock.getHarvestLevel(aMeta)) return 0;
		return tStats.getMiningSpeed(aBlock, (byte)aMeta) * Math.max(Float.MIN_NORMAL, tStats.getSpeedMultiplier() * tMultiplier * getPrimaryMaterial(aStack, MT.Steel).mToolSpeed);
	}
	
	@Override
	public final boolean canHarvestBlock(Block aBlock, ItemStack aStack) {
		return IL.TC_Block_Air.equal(aBlock) || MD.CARP.owns(aBlock) || getDigSpeed(aStack, aBlock, (byte)0) > 0;
	}
	
	@Override
	public final int getHarvestLevel(ItemStack aStack, String aToolClass) {
		IToolStats tStats = getToolStats(aStack);
		return tStats == null ? -1 : tStats.getBaseQuality() + getPrimaryMaterial(aStack, MT.NULL).mToolQuality; 
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack aStack, World aWorld, Block aBlock, int aX, int aY, int aZ, EntityLivingBase aPlayer) {
		if (ST.torch(aBlock)) return T;
		if (!isItemStackUsable(aStack)) return F;
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return F;
		if (TOOL_SOUNDS) UT.Sounds.play(tStats.getMiningSound(), 5, 1, aX, aY, aZ);
		boolean rReturn = (getDigSpeed(aStack, aBlock, aWorld.getBlockMetadata(aX, aY, aZ)) > 0);
		if (!UT.Entities.hasInfiniteItems(aPlayer)) {
			double tDamage = Math.max(1, tStats.getToolDamagePerBlockBreak() * aBlock.getBlockHardness(aWorld, aX, aY, aZ));
			if (WD.dimBTL(aWorld) && !getPrimaryMaterial(aStack).contains(TD.Properties.BETWEENLANDS)) tDamage *= 4;
			if (IL.TF_Mazestone.equal(aBlock) && getPrimaryMaterial(aStack).contains(TD.Properties.MAZEBREAKER)) tDamage /= 40;
			doDamage(aStack, (int)tDamage, aPlayer);
		}
		return rReturn;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack aStack) {
		if (!isItemStackUsable(aStack)) return null;
		aStack = ST.amount(1, aStack);
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return null;
		doDamage(aStack, tStats.getToolDamagePerContainerCraft(), null);
		aStack = aStack.stackSize > 0 ? aStack : null;
		if (TOOL_SOUNDS && aStack != null && LAST_TOOL_COORDS_BEFORE_DAMAGE != null) UT.Sounds.play(tStats.getCraftingSound(), 200, 1, LAST_TOOL_COORDS_BEFORE_DAMAGE);
		return aStack;
	}
	
	@Override
	public boolean hasContainerItem(ItemStack aStack) {
		if (!isItemStackUsable(aStack)) return F;
		aStack = ST.amount(1, aStack);
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return F;
		doDamage(aStack, tStats.getToolDamagePerContainerCraft(), null);
		return aStack.stackSize > 0;
	}
	
	public IToolStats getToolStats(ItemStack aStack) {
		isItemStackUsable(aStack);
		return getToolStatsInternal(aStack);
	}
	
	private IToolStats getToolStatsInternal(ItemStack aStack) {
		return aStack == null ? null : getToolStatsInternal(ST.meta_(aStack));
	}
	
	private IToolStats getToolStatsInternal(int aDamage) {
		return mToolStats.get((short)aDamage);
	}
	
	@Override
	public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats != null && aPlayer != null) tStats.onToolCrafted(aStack, aPlayer);
		super.onCreated(aStack, aWorld, aPlayer);
	}
	
	@Override
	public final boolean doesContainerItemLeaveCraftingGrid(ItemStack aStack) {
		return F;
	}
	
	@Override
	public final int getItemStackLimit(ItemStack aStack) {
		return 1;
	}
	
	@Override
	public boolean isFull3D() {
		return T;
	}
	
	@Override
	public boolean isItemStackUsable(ItemStack aStack) {
		if (aStack == null || aStack.stackSize <= 0) return F;
		IToolStats tStats = getToolStatsInternal(aStack);
		if (ST.meta_(aStack) % 2 == 1 || tStats == null) {
			NBTTagCompound aNBT = aStack.getTagCompound();
			if (aNBT != null) aNBT.removeTag("ench");
			return F;
		}
		OreDictMaterial aMaterial = getPrimaryMaterial(aStack, MT.NULL);
		HashMap<Integer, Integer> tMap = new HashMap<>(), tResult = new HashMap<>();
		for (ObjectStack<Enchantment> tEnchantment : aMaterial.mEnchantmentTools) {
			tMap.put(tEnchantment.mObject.effectId, tEnchantment.amountInt());
			if (tEnchantment.mObject == Enchantment.fortune) tMap.put(Enchantment.looting.effectId, tEnchantment.amountInt());
			if (tEnchantment.mObject == Enchantment.knockback) tMap.put(Enchantment.punch.effectId, tEnchantment.amountInt());
			if (tEnchantment.mObject == Enchantment.fireAspect) tMap.put(Enchantment.flame.effectId, tEnchantment.amountInt());
		}
		Enchantment[] tEnchants = tStats.getEnchantments(aStack, aMaterial);
		int[] tLevels = tStats.getEnchantmentLevels(aStack);
		for (int i = 0; i < tEnchants.length; i++) if (tLevels[i] > 0) {
			Integer tLevel = tMap.get(tEnchants[i].effectId);
			tMap.put(tEnchants[i].effectId, tLevel == null ? tLevels[i] : tLevel == tLevels[i] ? tLevel+1 : Math.max(tLevel, tLevels[i]));
		}
		for (Entry<Integer, Integer> tEntry : tMap.entrySet()) {
			if (tEntry.getKey() == 33 || (tEntry.getKey() == 20 && tEntry.getValue() > 2) || tEntry.getKey() == Enchantment_Radioactivity.INSTANCE.effectId) tResult.put(tEntry.getKey(), tEntry.getValue()); else
			if ("enchantment.railcraft.crowbar.implosion".equalsIgnoreCase(Enchantment.enchantmentsList[tEntry.getKey()].getName())) {
				if (tStats.isWeapon()) tResult.put(tEntry.getKey(), tEntry.getValue());
			} else switch(Enchantment.enchantmentsList[tEntry.getKey()].type) {
			case weapon:
				if (tStats.isWeapon()) tResult.put(tEntry.getKey(), tEntry.getValue());
				break;
			case all:
				tResult.put(tEntry.getKey(), tEntry.getValue());
				break;
			case armor: case armor_feet: case armor_head: case armor_legs: case armor_torso:
				break;
			case bow:
				if (tStats.isRangedWeapon()) tResult.put(tEntry.getKey(), tEntry.getValue());
				break;
			case breakable:
				break;
			case fishing_rod:
				break;
			case digger:
				if (tStats.isMiningTool()) tResult.put(tEntry.getKey(), tEntry.getValue());
				break;
			}
		}
		EnchantmentHelper.setEnchantments(tResult, aStack);
		return getToolDamage(aStack) <= getToolMaxDamage(aStack);
	}
	
	public short getChargedMetaData(ItemStack aStack) {
		return (short)(ST.meta_(aStack) - (ST.meta_(aStack) % 2));
	}
	
	public short getEmptyMetaData(ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) aNBT.removeTag("ench");
		return (short)(ST.meta_(aStack)+1-(ST.meta_(aStack) % 2));
	}
	
	@Override public int getItemEnchantability() {return 0;}
	@Override public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {return F;}
	@Override public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {return F;}
	
	@Override
	public int getRenderPasses(int aMetaData) {
		IToolStats tStats = getToolStatsInternal(aMetaData);
		if (tStats != null) return tStats.getRenderPasses()+2;
		return 2;
	}
	
	@Override
	public boolean requiresMultipleRenderPasses() {
		return T;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aIconRegister) {
		//
	}
	
	@Override
	public int getSpriteNumber() {
		return 1;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack aStack, int aRenderPass) {
		IToolStats tStats = getToolStatsInternal(aStack);
		if (tStats != null) return UT.Code.getRGBaInt(tStats.getRGBa(aStack, aRenderPass));
		return 16777215;
	}
	
	@Override
	public IIcon getIconIndex(ItemStack aStack) {
		return getIcon(aStack, 0);
	}
	
	@Override
	public IIcon getIconFromDamage(int aMetaData) {
		return getIconIndex(ST.make(this, 1, aMetaData));
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass) {
		return getIcon(aStack, aRenderPass, null, null, 0);
	}
	
	@Override
	public IIcon getIcon(ItemStack aStack, int aRenderPass, EntityPlayer aPlayer, ItemStack aUsedStack, int aUseRemaining) {
		IToolStats tStats = getToolStatsInternal(aStack);
		if (tStats == null) return Textures.ItemIcons.VOID.getIcon(0);
		if (aRenderPass < tStats.getRenderPasses()) {
			IIcon rIcon = tStats.getIcon(aStack, aRenderPass);
			return rIcon == null ? Textures.ItemIcons.VOID.getIcon(0) : rIcon;
		}
		if (aPlayer == null) {
			aRenderPass -= tStats.getRenderPasses();
			if (aRenderPass == 0) {
				long tDamage = MultiItemTool.getToolDamage(aStack), tMaxDamage = MultiItemTool.getToolMaxDamage(aStack);
				if (tMaxDamage <= 0) return Textures.ItemIcons.VOID.getIcon(0);
				if (tDamage <= 0) return Textures.ItemIcons.DURABILITY_BAR[8].getIcon(0);
				if (tDamage >= tMaxDamage) return Textures.ItemIcons.DURABILITY_BAR[0].getIcon(0);
				return Textures.ItemIcons.DURABILITY_BAR[(int)Math.max(0, Math.min(7, ((tMaxDamage-tDamage)*8L) / tMaxDamage))].getIcon(0);
			}
			if (aRenderPass == 1) {
				IItemEnergy tElectric = getEnergyStats(aStack);
				if (tElectric != null) {
					long tStored = tElectric.getEnergyStored(TD.Energy.EU, aStack), tCapacity = tElectric.getEnergyCapacity(TD.Energy.EU, aStack);
					if (tStored <= 0) return Textures.ItemIcons.ENERGY_BAR[0].getIcon(0);
					if (tStored >= tCapacity) return Textures.ItemIcons.ENERGY_BAR[8].getIcon(0);
					return Textures.ItemIcons.ENERGY_BAR[7-(int)Math.max(0, Math.min(6, ((tCapacity-tStored)*7L) / tCapacity))].getIcon(0);
				}
			}
		}
		return Textures.ItemIcons.VOID.getIcon(0);
	}
	
	@Override
	public IIcon getIconFromDamageForRenderPass(int aMetaData, int aRenderPass) {
		return getIconFromDamage(aMetaData);
	}
	
	@Override
	@SuppressWarnings("deprecation")
	public boolean hasEffect(ItemStack aStack) {
		return F;
	}
	
	@Override
	public boolean hasEffect(ItemStack aStack, int aRenderPass) {
		return F;
	}
	
	@Override
	public Long[] getFluidContainerStats(ItemStack aStack) {
		return null;
	}
}
