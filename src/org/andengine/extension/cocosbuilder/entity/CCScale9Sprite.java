package org.andengine.extension.cocosbuilder.entity;

import org.andengine.entity.sprite.NineSliceSprite;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:31:32 - 25.04.2012
 */
public class CCScale9Sprite extends NineSliceSprite {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCScale9Sprite(final float pX, final float pY, final ITextureRegion pTextureRegion, final float pInsetLeft, final float pInsetTop, final float pInsetRight, final float pInsetBottom, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom, pVertexBufferObjectManager);
	}

	public CCScale9Sprite(final float pX, final float pY, final ITextureRegion pTextureRegion, final float pInsetLeft, final float pInsetTop, final float pInsetRight, final float pInsetBottom, final VertexBufferObjectManager pVertexBufferObjectManager, final ShaderProgram pShaderProgram) {
		super(pX, pY, pTextureRegion, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom, pVertexBufferObjectManager, pShaderProgram);
	}

	public CCScale9Sprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final float pInsetLeft, final float pInsetTop, final float pInsetRight, final float pInsetBottom, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom, pVertexBufferObjectManager);
	}

	public CCScale9Sprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final float pInsetLeft, final float pInsetTop, final float pInsetRight, final float pInsetBottom, final VertexBufferObjectManager pVertexBufferObjectManager, final ShaderProgram pShaderProgram) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pInsetLeft, pInsetTop, pInsetRight, pInsetBottom, pVertexBufferObjectManager, pShaderProgram);
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
