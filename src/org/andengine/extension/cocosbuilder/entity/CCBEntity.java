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
	private final int mStageWidth;
	private final int mStageHeight;
	private final boolean mCenteredOrigin;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCBEntity(final int pVersion, final int pStageWidth, final int pStageHeight, final boolean pCenteredOrigin) {
		this.mVersion = pVersion;
		this.mStageWidth = pStageWidth;
		this.mStageHeight = pStageHeight;
		this.mCenteredOrigin = pCenteredOrigin;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getVersion() {
		return this.mVersion;
	}

	public int getStageWidth() {
		return this.mStageWidth;
	}

	public int getStageHeight() {
		return this.mStageHeight;
	}

	public boolean isCenteredOrigin() {
		return this.mCenteredOrigin;
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
