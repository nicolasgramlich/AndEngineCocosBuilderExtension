package org.andengine.extension.cocosbuilder.entity;

import org.andengine.entity.primitive.Gradient;
import org.andengine.entity.primitive.vbo.IGradientVertexBufferObject;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 13:07:52 - 24.04.2012
 */
public class CCLayerGradient extends Gradient {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCLayerGradient(final float pX, final float pY, final float pWidth, final float pHeight, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager);
	}

	public CCLayerGradient(final float pX, final float pY, final float pWidth, final float pHeight, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager, pDrawType);
	}

	public CCLayerGradient(final float pX, final float pY, final float pWidth, final float pHeight, final IGradientVertexBufferObject pGradientVertexBufferObject) {
		super(pX, pY, pWidth, pHeight, pGradientVertexBufferObject);
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
