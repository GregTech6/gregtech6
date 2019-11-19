/**
 * Copyright (c) 2019 Gregorius Techneticies
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

package gregapi.compat.buildcraft;

import static gregapi.data.CS.*;

import java.util.Collection;

import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.IStatementContainer;
import buildcraft.api.statements.IStatementParameter;
import buildcraft.api.statements.ITriggerExternal;
import buildcraft.api.statements.ITriggerInternal;
import buildcraft.api.statements.ITriggerProvider;
import buildcraft.api.statements.StatementManager;
import gregapi.code.ArrayListNoNulls;
import gregapi.data.LH;
import gregapi.lang.LanguageHandler;
import gregapi.util.UT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TriggerBC implements ITriggerExternal, ITriggerProvider {
	public final String mModID, mName;
	public IIcon mIcon;
	
	public TriggerBC(String aModID, String aName, String aDesciption) {
		mModID = aModID;
		mName = aName;
		LH.add("bc.trigger."+mModID+"."+mName, aDesciption);

		StatementManager.registerStatement(this);
		StatementManager.registerTriggerProvider(this);
	}
	
	
	@Override public String getUniqueTag() {return mModID + ":" + mName;}
	@Override public IIcon getIcon() {return mIcon;}
	@Override public void registerIcons(IIconRegister aIconRegister) {mIcon = aIconRegister.registerIcon(mModID + ":triggers/" + mName);}
	@Override public int maxParameters() {return 0;}
	@Override public int minParameters() {return 0;}
	@Override public String getDescription() {return LanguageHandler.translate("bc.trigger."+mModID+"."+mName);}
	@Override public IStatementParameter createParameter(int aIndex) {return null;}
	@Override public IStatement rotateLeft() {return null;}
	@Override public boolean isTriggerActive(TileEntity aTarget, ForgeDirection aSide, IStatementContainer aSource, IStatementParameter[] aParameters) {return isApplicable(aTarget, UT.Code.side(aSide)) ? isActive(aTarget, UT.Code.side(aSide), aSource, aParameters) : false;}
	@Override public Collection<ITriggerInternal> getInternalTriggers(IStatementContainer container) {return null;}
	@Override public Collection<ITriggerExternal> getExternalTriggers(ForgeDirection aSide, TileEntity aTarget) {return isApplicable(aTarget, UT.Code.side(aSide)) ? new ArrayListNoNulls<ITriggerExternal>(F, this) : null;}
	
	public abstract boolean isActive(TileEntity aTarget, byte aSideOfTileEntity, IStatementContainer aSource, IStatementParameter[] aParameters);
	public abstract boolean isApplicable(TileEntity aTarget, byte aSideOfTileEntity);
}
