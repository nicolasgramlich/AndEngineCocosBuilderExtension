package org.andengine.extension.cocosbuilder.entity;

import org.andengine.entity.Entity;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 21:18:36 - 22.04.2012
 */
public class CCBEntity extends Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final int mVersion;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCBEntity(final int pVersion, final int pStageWidth, final int pStageHeight, final boolean pCenteredOrigin) {
		super(0, 0, pStageWidth, pStageHeight);

		if(!pCenteredOrigin) {
			this.setOffsetCenter(0, 0);
		}

		this.mVersion = pVersion;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getVersion() {
		return this.mVersion;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
