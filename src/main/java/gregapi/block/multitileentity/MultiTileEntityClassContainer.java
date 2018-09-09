package gregapi.block.multitileentity;

import static gregapi.data.CS.*;

import gregapi.util.UT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * @author Gregorius Techneticies
 */
public class MultiTileEntityClassContainer {
	public final Class<? extends TileEntity> mClass;
	public final MultiTileEntityBlock mBlock;
	public final TileEntity mCanonicalTileEntity;
	public final NBTTagCompound mParameters;
	public final byte mBlockMetaData, mStackSize;
	public final short mID, mCreativeTabID;
	public final boolean mHidden;
	
	public MultiTileEntityClassContainer(int aID, int aCreativeTabID, Class<? extends TileEntity> aClass, int aBlockMetaData, int aStackSize, MultiTileEntityBlock aBlock, NBTTagCompound aParameters) {
		if (!IMultiTileEntity.class.isAssignableFrom(aClass)) throw new IllegalArgumentException("MultiTileEntities must implement the Interface IMultiTileEntity!");
		mBlockMetaData = (byte)aBlockMetaData;
		mStackSize = (byte)aStackSize;
		mParameters = aParameters==null?UT.NBT.make():aParameters;
		mHidden = mParameters.getBoolean(NBT_HIDDEN);
		mID = (short)aID;
		mCreativeTabID = (short)aCreativeTabID;
		mBlock = aBlock;
		mClass = aClass;
		try {mCanonicalTileEntity = aClass.newInstance();} catch (Throwable e) {throw new IllegalArgumentException(e);}
		if (mCanonicalTileEntity instanceof IMultiTileEntity) ((IMultiTileEntity)mCanonicalTileEntity).initFromNBT(mParameters, mID, (short)-1);
	}
}