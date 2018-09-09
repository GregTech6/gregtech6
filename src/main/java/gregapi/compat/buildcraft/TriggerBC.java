package gregapi.compat.buildcraft;

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
	@Override public Collection<ITriggerExternal> getExternalTriggers(ForgeDirection aSide, TileEntity aTarget) {return isApplicable(aTarget, UT.Code.side(aSide)) ? new ArrayListNoNulls(false, this) : null;}
	
	public abstract boolean isActive(TileEntity aTarget, byte aSideOfTileEntity, IStatementContainer aSource, IStatementParameter[] aParameters);
	public abstract boolean isApplicable(TileEntity aTarget, byte aSideOfTileEntity);
}