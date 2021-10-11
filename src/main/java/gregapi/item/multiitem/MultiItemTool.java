/**
 * Copyright (c) 2021 GregTech-6 Team
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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregapi.code.ArrayListNoNulls;
import gregapi.code.ItemStackSet;
import gregapi.code.ObjectStack;
import gregapi.code.TagData;
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
import net.minecraft.nbt.NBTTagList;
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
		/*
		if (MD.BG2.mLoaded) try {
			UT.Reflection.callPublicMethod(Class.forName("mods.battlegear2.api.weapons.WeaponRegistry"), "addTwoHanded", make(0));
			UT.Reflection.callPublicMethod(Class.forName("mods.battlegear2.api.weapons.WeaponRegistry"), "addTwoHanded", make(W));
			UT.Reflection.callPublicMethod(Class.forName("mods.battlegear2.api.weapons.WeaponRegistry"), "setWeapon", "MainHand", make(0));
			UT.Reflection.callPublicMethod(Class.forName("mods.battlegear2.api.weapons.WeaponRegistry"), "setWeapon", "MainHand", make(W));
		} catch(Throwable e) {
			e.printStackTrace(ERR);
		}
		*/
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
		if (aID >= 0 && aID < 32766 && isUsableMeta((short)aID)) {
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
		return getToolWithStats(aToolID, aAmount, aPrimaryMaterial, aSecondaryMaterial, aMaxCharge, aVoltage, 0);
	}
	
	/**
	 * This Function gets an ItemStack Version of this Tool
	 * @param aToolID the ID of the Tool Class
	 * @param aAmount Amount of Items (well normally you only need 1)
	 * @param aPrimaryMaterial Primary Material of this Tool
	 * @param aSecondaryMaterial Secondary (Rod/Handle) Material of this Tool
	 */
	public final ItemStack getToolWithStats(int aToolID, int aAmount, OreDictMaterial aPrimaryMaterial, OreDictMaterial aSecondaryMaterial, long aMaxCharge, long aVoltage, long aCharge) {
		ItemStack rStack = ST.make(this, aAmount, aToolID);
		IToolStats tToolStats = getToolStats(rStack);
		if (tToolStats != null) {
			NBTTagCompound tMainNBT = UT.NBT.make(), tToolNBT = UT.NBT.make();
			if (aPrimaryMaterial != null) {
				if (aPrimaryMaterial.mID > 0) tToolNBT.setShort("a", aPrimaryMaterial.mID); else tToolNBT.setString("b", aPrimaryMaterial.toString());
				UT.NBT.setNumber(tToolNBT, "j", (long)((aPrimaryMaterial.mToolDurability * 100L) * tToolStats.getMaxDurabilityMultiplier()));
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
			if (aCharge > 0 && aMaxCharge > 0) for (TagData tEnergyType : getEnergyTypes(rStack)) setEnergyStored(tEnergyType, rStack, Math.min(aCharge, aMaxCharge));
		}
		isItemStackUsable(rStack);
		return rStack;
	}
	
	/**
	 * Called by the Block Harvesting Event within the GT_Proxy
	 */
	public void onHarvestBlockEvent(ArrayList<ItemStack> aDrops, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, int aX, int aY, int aZ, byte aMeta, int aFortune, boolean aSilkTouch, BlockEvent.HarvestDropsEvent aEvent) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null || ST.instaharvest(aBlock, aMeta) || !isItemStackUsable(aStack) || getDigSpeed(aStack, aBlock, aMeta) <= 0) {
			doDamage(aStack, 0, aPlayer, T);
			return;
		}
		int tDamage = tStats.convertBlockDrops(aDrops, aStack, aPlayer, aBlock, (getToolMaxDamage(aStack) - getToolDamage(aStack)) / tStats.getToolDamagePerDropConversion(), aX, aY, aZ, aMeta, aFortune, aSilkTouch, aEvent);
		if (aBlock == Blocks.ice && !aDrops.isEmpty()) aPlayer.worldObj.setBlockToAir(aX, aY, aZ);
		if (WD.dimBTL(aPlayer.worldObj) && !getPrimaryMaterial(aStack).contains(TD.Properties.BETWEENLANDS)) tDamage *= 4;
		doDamage(aStack, tDamage * tStats.getToolDamagePerDropConversion(), aPlayer, T);
	}
	
	public boolean canCollectDropsDirectly(ItemStack aStack, Block aBlock, byte aMeta) {
		if (ST.instaharvest(aBlock, aMeta)) return T;
		IToolStats tStats = getToolStats(aStack);
		return (tStats.canCollect() || getPrimaryMaterial(aStack).contains(TD.Properties.MAGNETIC_ACTIVE) || getSecondaryMaterial(aStack).contains(TD.Properties.MAGNETIC_ACTIVE)) && isItemStackUsable(aStack) && getDigSpeed(aStack, aBlock, aMeta) > 0;
	}
	
	public float onBlockBreakSpeedEvent(float aDefault, ItemStack aStack, EntityPlayer aPlayer, Block aBlock, int aX, int aY, int aZ, byte aMeta, PlayerEvent.BreakSpeed aEvent) {
		if (aBlock == NB || WD.bedrock(aBlock)) return aDefault;
		if (ST.instaharvest(aBlock, aMeta)) return Float.MAX_VALUE;
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
					float tFullDamage = (tDamage+tMagicDamage) * TFC_DAMAGE_MULTIPLIER;
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
							doDamage(aStack, tStats.getToolDamagePerEntityAttack(), aPlayer, F);
						}
					} else {
						if (aEntity.attackEntityFrom(tStats.getDamageSource(aPlayer, aEntity), tFullDamage)) {
							tStats.afterDealingDamage(tDamage, tMagicDamage, tFireAspect, tCriticalHit, aEntity, aStack, aPlayer);
							doDamage(aStack, tStats.getToolDamagePerEntityAttack(), aPlayer, F);
						}
					}
				}
			}
		}
		return T;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats != null && tStats.canBlock()) aPlayer.setItemInUse(aStack, 72000);
		return super.onItemRightClick(aStack, aWorld, aPlayer);
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack aStack) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats != null && tStats.canBlock()) return EnumAction.block;
		return EnumAction.none;
	}
	@Override
	public int getMaxItemUseDuration(ItemStack aStack) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats != null && tStats.canBlock()) return 72000;
		return 0;
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
		OreDictMaterial tMat1 = getPrimaryMaterial(aStack), tMat2 = getSecondaryMaterial(aStack);
		IToolStats tStats = getToolStats(aStack);
		if (tMaxDamage > 0 && tStats != null) {
			if (tMat1 == MT.NULL) {
				aList.add(LH.Chat.WHITE + "Durability: x" + LH.Chat.GREEN + tStats.getMaxDurabilityMultiplier());
				aList.add(LH.Chat.WHITE + "Level: +" + LH.Chat.YELLOW + tStats.getBaseQuality());
				float tCombat = getToolCombatDamage(aStack);
				aList.add(LH.Chat.WHITE + "Attack Damage: +" + LH.Chat.BLUE + (tCombat * TFC_DAMAGE_MULTIPLIER) + LH.Chat.RED + " (= " + (TFC_DAMAGE_MULTIPLIER > 1 ? ((tCombat+1)*(TFC_DAMAGE_MULTIPLIER/2.0)) + ")" : ((tCombat+1)/2) + " Hearts)"));
				aList.add(LH.Chat.WHITE + "Mining Speed: x" + LH.Chat.PINK + tStats.getSpeedMultiplier());
				if (tStats.canCollect()) aList.add(LH.Chat.DGRAY + LH.get(LH.TOOLTIP_AUTOCOLLECT));
			} else {
				aList.add(LH.Chat.WHITE + "Durability: " + LH.Chat.GREEN + UT.Code.makeString(tMaxDamage - tDamage) + " / " + UT.Code.makeString(tMaxDamage));
				aList.add(LH.Chat.WHITE + tMat1.getLocal() + LH.Chat.YELLOW + " Level: " + (tStats.getBaseQuality() + tMat1.mToolQuality));
				float tCombat = getToolCombatDamage(aStack);
				aList.add(LH.Chat.WHITE + "Attack Damage: " + LH.Chat.BLUE + "+" + (tCombat * TFC_DAMAGE_MULTIPLIER) + LH.Chat.RED + " (= " + (TFC_DAMAGE_MULTIPLIER > 1 ? ((tCombat+1)*(TFC_DAMAGE_MULTIPLIER/2.0)) + ")" : ((tCombat+1)/2) + " Hearts)"));
				aList.add(LH.Chat.WHITE + "Mining Speed: " + LH.Chat.PINK + Math.max(Float.MIN_NORMAL, tStats.getSpeedMultiplier() * tMat1.mToolSpeed));
				aList.add(LH.Chat.WHITE + "Crafting Uses: " + LH.Chat.GREEN + UT.Code.divup(getEnergyStats(aStack) == null ? tMaxDamage - tDamage : Math.min(getEnergyStored(TD.Energy.EU, aStack), getEnergyCapacity(TD.Energy.EU, aStack)), tStats.getToolDamagePerContainerCraft()));
				if (MD.BTL.mLoaded && tMat1.contains(TD.Properties.BETWEENLANDS)) aList.add(LH.Chat.GREEN + LH.get(LH.TOOLTIP_BETWEENLANDS_RESISTANCE));
				if ((IL.TF_Mazestone.exists() || IL.TF_Mazehedge.exists()) && tMat1.contains(TD.Properties.MAZEBREAKER)) {
					if (canHarvestBlock(IL.TF_Mazestone.block(), aStack)) aList.add(LH.Chat.PINK + LH.get(LH.TOOLTIP_TWILIGHT_MAZE_STONE_BREAKING));
					if (canHarvestBlock(IL.TF_Mazehedge.block(), aStack)) aList.add(LH.Chat.PINK + LH.get(LH.TOOLTIP_TWILIGHT_MAZE_HEDGE_BREAKING));
				}
				if (tMat1.contains(TD.Properties.UNBURNABLE) || tMat2.contains(TD.Properties.UNBURNABLE)) aList.add(LH.Chat.GREEN + LH.get(LH.TOOLTIP_UNBURNABLE));
				if (tStats.canCollect() || tMat1.contains(TD.Properties.MAGNETIC_ACTIVE) || tMat2.contains(TD.Properties.MAGNETIC_ACTIVE)) aList.add(LH.Chat.DGRAY + LH.get(LH.TOOLTIP_AUTOCOLLECT));
			}
		}
	}
	
	public static final OreDictMaterial getPrimaryMaterial(ItemStack aStack) {return getPrimaryMaterial(aStack, MT.NULL);}
	public static final OreDictMaterial getPrimaryMaterial(ItemStack aStack, OreDictMaterial aDefault) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("GT.ToolStats");
			if (aNBT != null) {
				if (aNBT.hasKey("a")) return OreDictMaterial.get(aNBT.getShort ("a"), aDefault);
				if (aNBT.hasKey("b")) return OreDictMaterial.get(aNBT.getString("b"), aDefault);
			}
		}
		return aDefault;
	}
	
	public static final OreDictMaterial getSecondaryMaterial(ItemStack aStack) {return getSecondaryMaterial(aStack, MT.NULL);}
	public static final OreDictMaterial getSecondaryMaterial(ItemStack aStack, OreDictMaterial aDefault) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("GT.ToolStats");
			if (aNBT != null) {
				if (aNBT.hasKey("c")) return OreDictMaterial.get(aNBT.getShort ("c"), aDefault);
				if (aNBT.hasKey("d")) return OreDictMaterial.get(aNBT.getString("d"), aDefault);
			}
		}
		return aDefault;
	}
	
	@Override
	public IItemEnergy getEnergyStats(ItemStack aStack) {
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT != null) {
			aNBT = aNBT.getCompoundTag("GT.ToolStats");
			if (aNBT != null) {
				if (aNBT.getBoolean("e")) return EnergyStat.makeTool(TD.Energy.EU, aNBT.getLong("f"), aNBT.getLong("g"), 64, ST.make(this, 1, getUnusableMeta(aStack)), ST.make(this, 1, getUsableMeta(aStack)), ST.make(this, 1, getUsableMeta(aStack)));
			}
		}
		return null;
	}
	
	public float getToolCombatDamage(ItemStack aStack) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return 0;
		return tStats.getBaseDamage() + getPrimaryMaterial(aStack).mToolQuality;
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
	
	@Override
	public boolean destroyCheck(ItemStack aStack, EntityPlayer aPlayer) {
		if (getToolDamage(aStack) >= getToolMaxDamage(aStack)) doDamage(aStack, 0, aPlayer, T);
		return super.destroyCheck(aStack, aPlayer);
	}
	
	public boolean doDamage(ItemStack aStack, long aAmount) {return doDamage(aStack, aAmount, null, T);}
	public boolean doDamage(ItemStack aStack, long aAmount, EntityLivingBase aPlayer) {return doDamage(aStack, aAmount, aPlayer, T);}
	public boolean doDamage(ItemStack aStack, long aAmount, EntityLivingBase aPlayer, boolean aAllowBreaking) {
		if (UT.Entities.hasInfiniteItems(aPlayer)) return T;
		if (!isItemStackUsable(aStack)) return F;
		IItemEnergy tElectric = getEnergyStats(aStack);
		if (tElectric == null || RNGSUS.nextInt(Math.max(10, getPrimaryMaterial(aStack).mToolQuality * 20)) == 0) {
			long tNewDamage = getToolDamage(aStack) + aAmount;
			setToolDamage(aStack, tNewDamage);
			if (aAllowBreaking && tNewDamage >= getToolMaxDamage(aStack)) {
				IToolStats tStats = getToolStats(aStack);
				if (tStats == null) {
					ST.use(aPlayer, T, aStack);
				} else {
					if (TOOL_SOUNDS) {
						if (aPlayer != null) {
							UT.Sounds.send(tStats.getBreakingSound(), aPlayer);
						} else {
							UT.Sounds.play(tStats.getBreakingSound(), 100, 1, LAST_TOOL_COORDS_BEFORE_DAMAGE);
						}
					}
					LAST_TOOL_COORDS_BEFORE_DAMAGE = null;
					ItemStack tBroken = tStats.getBrokenItem(aStack);
					if (ST.invalid(tBroken) || tBroken.stackSize <= 0) {
						ST.use(aPlayer, T, aStack);
					} else if (aPlayer instanceof EntityPlayer) {
						if (tBroken.stackSize > 64) tBroken.stackSize = 64;
						if (!aPlayer.worldObj.isRemote) UT.Inventories.addStackToPlayerInventoryOrDrop((EntityPlayer)aPlayer, tBroken, F);
						ST.use(aPlayer, T, aStack);
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
		if (aBlock == NB || WD.bedrock(aBlock)) return 0;
		if (ST.instaharvest(aBlock, aMeta)) return 10;
		if (!isItemStackUsable(aStack)) return 0;
		float tMultiplier = 1.0F;
		OreDictMaterial tMaterial = getPrimaryMaterial(aStack);
		if ((IL.TF_Mazestone.equal(aBlock) || IL.TF_Mazehedge.equal(aBlock)) && tMaterial.contains(TD.Properties.MAZEBREAKER)) tMultiplier *= 40;
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null || tStats.getBaseQuality() + tMaterial.mToolQuality < UT.Code.bind4(aBlock.getHarvestLevel(aMeta))) return 0;
		return tStats.getMiningSpeed(aBlock, (byte)aMeta) * Math.max(Float.MIN_NORMAL, tStats.getSpeedMultiplier() * tMultiplier * tMaterial.mToolSpeed);
	}
	
	@Override
	public final boolean canHarvestBlock(Block aBlock, ItemStack aStack) {
		return IL.TC_Block_Air.equal(aBlock) || MD.CARP.owns(aBlock) || getDigSpeed(aStack, aBlock, (byte)0) > 0;
	}
	
	@Override
	public final int getHarvestLevel(ItemStack aStack, String aToolClass) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return -1;
		int rValue = tStats.getBaseQuality() + getPrimaryMaterial(aStack).mToolQuality;
		return rValue < 15 ? rValue : Integer.MAX_VALUE; 
	}
	
	@Override
	public boolean onBlockDestroyed(ItemStack aStack, World aWorld, Block aBlock, int aX, int aY, int aZ, EntityLivingBase aPlayer) {
		if (ST.instaharvest(aBlock) || UT.Entities.hasInfiniteItems(aPlayer)) return T;
		if (!isItemStackUsable(aStack)) return F;
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return F;
		if (TOOL_SOUNDS) UT.Sounds.play(tStats.getMiningSound(), 5, 1, aX, aY, aZ);
		byte aMeta = WD.meta(aWorld, aX, aY, aZ);
		boolean rReturn = (getDigSpeed(aStack, aBlock, aMeta) > 0);
		double tDamage = tStats.getToolDamagePerBlockBreak() * aBlock.getBlockHardness(aWorld, aX, aY, aZ);
		OreDictMaterial tMaterial = getPrimaryMaterial(aStack);
		if (WD.dimBTL(aWorld) && tMaterial.contains(TD.Properties.BETWEENLANDS)) tDamage *= 4;
		if (IL.TF_Mazestone.equal(aBlock)) if (tMaterial.contains(TD.Properties.MAZEBREAKER)) tDamage /= 40; else tDamage *= 16;
		if (IL.TF_Mazehedge.equal(aBlock)) {
			if (tMaterial.contains(TD.Properties.MAZEBREAKER)) tDamage /= 40; else tDamage *= 16;
			if (!aWorld.isRemote && EnchantmentHelper.getEnchantmentLevel(Enchantment.silkTouch.effectId, aStack) <= 0) {
				if (aPlayer instanceof EntityPlayer && canCollectDropsDirectly(aStack, aBlock, aMeta)) {
					UT.Inventories.addStackToPlayerInventoryOrDrop((EntityPlayer)aPlayer, IL.TF_Mazehedge.get(1), aWorld, aX, aY, aZ);
				} else {
					ST.drop(aWorld, aX, aY, aZ, IL.TF_Mazehedge.get(1));
				}
			}
		}
		doDamage(aStack, UT.Code.roundUp(tDamage), aPlayer, F);
		return rReturn;
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack aStack) {
		if (!isUsableMeta(aStack)) return null;
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return null;
		if (TOOL_SOUNDS) UT.Sounds.play(tStats.getCraftingSound(), 200, 1, LAST_TOOL_COORDS_BEFORE_DAMAGE);
		aStack = ST.amount(1, aStack);
		doDamage(aStack, tStats.getToolDamagePerContainerCraft(), null, T);
		return aStack.stackSize > 0 ? aStack : null;
	}
	
	@Override
	public boolean hasContainerItem(ItemStack aStack) {
		if (!isUsableMeta(aStack)) return F;
		IToolStats tStats = getToolStats(aStack);
		if (tStats == null) return F;
		aStack = ST.amount(1, aStack);
		doDamage(aStack, tStats.getToolDamagePerContainerCraft(), null, T);
		return aStack.stackSize > 0;
	}
	
	@Override
	public void onCreated(ItemStack aStack, World aWorld, EntityPlayer aPlayer) {
		IToolStats tStats = getToolStats(aStack);
		if (tStats != null && aPlayer != null) tStats.onToolCrafted(aStack, aPlayer);
		super.onCreated(aStack, aWorld, aPlayer);
	}
	
	@Override
	public boolean isItemStackUsable(ItemStack aStack) {
		if (aStack.stackSize <= 0) return F;
		
		NBTTagCompound aNBT = aStack.getTagCompound();
		if (aNBT == null) return T;
		
		if (!isUsableMeta(aStack)) {
			aNBT.removeTag("ench");
			return F;
		}
		
		IToolStats tStats = getToolStatsInternal(aStack);
		if (tStats == null || !super.isItemStackUsable(aStack)) {
			aNBT.removeTag("ench");
			return F;
		}
		
		if (aNBT.hasKey("ench")) return T;
		
		// Abuse a potentially empty List as a boolean to see if a Tool already has enchants or not.
		aNBT.setTag("ench", new NBTTagList());
		
		OreDictMaterial aMaterial = getPrimaryMaterial(aStack);
		
		List<ObjectStack<Enchantment>> tEnchantments = new ArrayListNoNulls<>();
		
		for (ObjectStack<Enchantment> tEnchantment : aMaterial.mEnchantmentTools) {
			tEnchantments.add(new ObjectStack<>(tEnchantment.mObject, tEnchantment.mAmount));
			if (tEnchantment.mObject == Enchantment.fortune   ) tEnchantments.add(new ObjectStack<>(Enchantment.looting, tEnchantment.mAmount));
			if (tEnchantment.mObject == Enchantment.knockback ) tEnchantments.add(new ObjectStack<>(Enchantment.punch  , tEnchantment.mAmount));
			if (tEnchantment.mObject == Enchantment.fireAspect) tEnchantments.add(new ObjectStack<>(Enchantment.flame  , tEnchantment.mAmount));
		}
		
		Enchantment[] tEnchants = tStats.getEnchantments(aStack, aMaterial);
		int[] tLevels = tStats.getEnchantmentLevels(aStack, aMaterial);
		
		for (int i = 0; i < tEnchants.length; i++) if (tLevels[i] > 0) {
			boolean temp = T;
			for (ObjectStack<Enchantment> tEnchantment : tEnchantments) if (tEnchantment.mObject == tEnchants[i]) {
				if (tEnchantment.mAmount == tLevels[i]) {
					tEnchantment.mAmount++;
				} else if (tEnchantment.mAmount < tLevels[i]) {
					tEnchantment.mAmount = tLevels[i];
				}
				temp = F;
				break;
			}
			if (temp) tEnchantments.add(new ObjectStack<>(tEnchants[i], tLevels[i]));
		}
		
		for (ObjectStack<Enchantment> tEnchantment : tEnchantments) {
			if (tEnchantment.mObject == Enchantment.silkTouch || tEnchantment.mObject == Enchantment_Radioactivity.INSTANCE) {
				aStack.addEnchantment(tEnchantment.mObject, tEnchantment.amountShort());
				continue;
			}
			if (tEnchantment.mObject == Enchantment.fireAspect) {
				if (tEnchantment.mAmount > 2 || tStats.isWeapon())
				aStack.addEnchantment(tEnchantment.mObject, tEnchantment.amountShort());
				continue;
			}
			if ("enchantment.railcraft.crowbar.implosion".equalsIgnoreCase(tEnchantment.mObject.getName())) {
				if (tStats.isWeapon())
				aStack.addEnchantment(tEnchantment.mObject, tEnchantment.amountShort());
				continue;
			}
			switch(tEnchantment.mObject.type) {
			case all        :                              aStack.addEnchantment(tEnchantment.mObject, tEnchantment.amountShort()); break;
			case weapon     : if (tStats.isWeapon      ()) aStack.addEnchantment(tEnchantment.mObject, tEnchantment.amountShort()); break;
			case bow        : if (tStats.isRangedWeapon()) aStack.addEnchantment(tEnchantment.mObject, tEnchantment.amountShort()); break;
			case digger     : if (tStats.isMiningTool  ()) aStack.addEnchantment(tEnchantment.mObject, tEnchantment.amountShort()); break;
			case armor      : break;
			case armor_feet : break;
			case armor_head : break;
			case armor_legs : break;
			case armor_torso: break;
			case breakable  : break;
			case fishing_rod: break;
			}
		}
		return T;
	}
	
	public boolean isUsableMeta(short aMeta) {
		return aMeta % 2 == 0;
	}
	public boolean isUsableMeta(ItemStack aStack) {
		return isUsableMeta(ST.meta(aStack));
	}
	public short getUsableMeta(short aMeta) {
		return (short)(aMeta  -(aMeta % 2));
	}
	public short getUsableMeta(ItemStack aStack) {
		return getUsableMeta(ST.meta(aStack));
	}
	public short getUnusableMeta(short aMeta) {
		return (short)(aMeta+1-(aMeta % 2));
	}
	public short getUnusableMeta(ItemStack aStack) {
		return getUnusableMeta(ST.meta(aStack));
	}
	
	@Override
	public int getRenderPasses(int aMetaData) {
		IToolStats tStats = getToolStatsInternal(aMetaData);
		if (tStats != null) return tStats.getRenderPasses()+2;
		return 2;
	}
	
	@Override
	public int getColorFromItemStack(ItemStack aStack, int aRenderPass) {
		IToolStats tStats = getToolStatsInternal(aStack);
		if (tStats != null) return UT.Code.getRGBaInt(tStats.getRGBa(aStack, aRenderPass));
		return 16777215;
	}
	
	@Override public IIcon getIconIndex(ItemStack aStack) {return getIcon(aStack, 0);}
	@Override public IIcon getIconFromDamageForRenderPass(int aMetaData, int aRenderPass) {return getIconFromDamage(aMetaData);}
	@Override public IIcon getIconFromDamage(int aMetaData) {return getIconIndex(ST.make(this, 1, aMetaData));}
	@Override public IIcon getIcon(ItemStack aStack, int aRenderPass) {return getIcon(aStack, aRenderPass, null, null, 0);}
	@Override public IIcon getIcon(ItemStack aStack, int aRenderPass, EntityPlayer aPlayer, ItemStack aUsedStack, int aUseRemaining) {
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
	
	public IToolStats getToolStats(ItemStack aStack) {isItemStackUsable(aStack); return getToolStatsInternal(aStack);}
	public IToolStats getToolStatsInternal(ItemStack aStack) {return aStack == null ? null : getToolStatsInternal(ST.meta_(aStack));}
	public IToolStats getToolStatsInternal(int aDamage) {return mToolStats.get((short)aDamage);}
	@Override public final boolean doesContainerItemLeaveCraftingGrid(ItemStack aStack) {return F;}
	@Override public final int getItemStackLimit(ItemStack aStack) {return 1;}
	@Override public boolean isFull3D() {return T;}
	@Override public int getSpriteNumber() {return 1;}
	@Override public boolean requiresMultipleRenderPasses() {return T;}
	@Override @SideOnly(Side.CLIENT) public void registerIcons(IIconRegister aIconRegister) {/**/}
	@Override @SuppressWarnings("deprecation") public boolean hasEffect(ItemStack aStack) {return F;}
	@Override public boolean hasEffect(ItemStack aStack, int aRenderPass) {return F;}
	@Override public int getItemEnchantability() {return 0;}
	@Override public boolean isBookEnchantable(ItemStack aStack, ItemStack aBook) {return F;}
	@Override public boolean getIsRepairable(ItemStack aStack, ItemStack aMaterial) {return F;}
	@Override public Long[] getFluidContainerStats(ItemStack aStack) {return null;}
}
