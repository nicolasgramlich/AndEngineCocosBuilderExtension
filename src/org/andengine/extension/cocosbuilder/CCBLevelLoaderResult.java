package org.andengine.extension.cocosbuilder;

import org.andengine.entity.IEntity;
import org.andengine.util.level.ILevelLoaderResult;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 15:24:53 - 19.04.2012
 */
public class CCBLevelLoaderResult implements ILevelLoaderResult {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final IEntity mRootEntity;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCBLevelLoaderResult(final IEntity pRootEntity) {
		this.mRootEntity = pRootEntity;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public IEntity getRootEntity() {
		return this.mRootEntity;
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
