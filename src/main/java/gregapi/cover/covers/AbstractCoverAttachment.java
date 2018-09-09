package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;
import net.minecraft.entity.Entity;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachment extends AbstractCoverDefault {
	@Override public boolean onCoverClickedLeft (byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean onCoverClickedRight(byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean interceptClickLeft (byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean interceptClickRight(byte aSide, CoverData aData, Entity aPlayer, byte aSideClicked, float aHitX, float aHitY, float aHitZ) {return F;}
	@Override public boolean isOpaque(byte aSide, CoverData aData) {return F;}
	@Override public boolean isSealable(byte aCoverSide, CoverData aData) {return F;}
	@Override public boolean onWalkOver(byte aCoverSide, CoverData aData, Entity aEntity) {return F;}
}