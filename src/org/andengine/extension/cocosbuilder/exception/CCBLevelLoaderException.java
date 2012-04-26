package org.andengine.extension.cocosbuilder.exception;

import org.andengine.util.exception.AndEngineRuntimeException;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 12:21:35 - 26.04.2012
 */
public class CCBLevelLoaderException extends AndEngineRuntimeException {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 6812847149205067943L;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCBLevelLoaderException() {

	}

	public CCBLevelLoaderException(final String pMessage) {
		super(pMessage);
	}

	public CCBLevelLoaderException(final Throwable pThrowable) {
		super(pThrowable);
	}

	public CCBLevelLoaderException(final String pMessage, final Throwable pThrowable) {
		super(pMessage, pThrowable);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

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
