package org.andengine.extension.cocosbuilder.entity;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.primitive.vbo.IRectangleVertexBufferObject;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 13:04:31 - 24.04.2012
 */
public class CCLayerColor extends Rectangle {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCLayerColor(final float pX, final float pY, final float pWidth, final float pHeight, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager);
	}

	public CCLayerColor(final float pX, final float pY, final float pWidth, final float pHeight, final IRectangleVertexBufferObject pRectangleVertexBufferObject) {
		super(pX, pY, pWidth, pHeight, pRectangleVertexBufferObject);
	}

	public CCLayerColor(final float pX, final float pY, final float pWidth, final float pHeight, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager, pDrawType);
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
