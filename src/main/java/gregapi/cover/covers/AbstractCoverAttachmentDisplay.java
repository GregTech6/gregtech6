package gregapi.cover.covers;

import static gregapi.data.CS.*;

import gregapi.cover.CoverData;

/**
 * @author Gregorius Techneticies
 */
public abstract class AbstractCoverAttachmentDisplay extends AbstractCoverAttachment {
	@Override public boolean needsVisualsSaved(byte aSide, CoverData aData) {return T;}
}