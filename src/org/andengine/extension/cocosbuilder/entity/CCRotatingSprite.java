package org.andengine.extension.cocosbuilder.entity;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationModifier;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 13:10:45 - 24.04.2012
 */
public class CCRotatingSprite extends CCSprite {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float SECONDS_PER_ROTATION_DEFAULT = 2;

	// ===========================================================
	// Fields
	// ===========================================================

	private float mSecondsPerRotation = CCRotatingSprite.SECONDS_PER_ROTATION_DEFAULT;

	private LoopEntityModifier mLoopModifier;
	private RotationModifier mRotationModifier;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCRotatingSprite(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager, final ShaderProgram pShaderProgram) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, pShaderProgram);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, pDrawType);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType, final ShaderProgram pShaderProgram) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager, pDrawType, pShaderProgram);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final ITextureRegion pTextureRegion, final ISpriteVertexBufferObject pVertexBufferObject) {
		super(pX, pY, pTextureRegion, pVertexBufferObject);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final ITextureRegion pTextureRegion, final ISpriteVertexBufferObject pVertexBufferObject, final ShaderProgram pShaderProgram) {
		super(pX, pY, pTextureRegion, pVertexBufferObject, pShaderProgram);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager, final ShaderProgram pShaderProgram) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager, pShaderProgram);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager, pDrawType);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final VertexBufferObjectManager pVertexBufferObjectManager, final DrawType pDrawType, final ShaderProgram pShaderProgram) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pVertexBufferObjectManager, pDrawType, pShaderProgram);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final ISpriteVertexBufferObject pSpriteVertexBufferObject) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pSpriteVertexBufferObject);

		this.updateModifiers();
	}

	public CCRotatingSprite(final float pX, final float pY, final float pWidth, final float pHeight, final ITextureRegion pTextureRegion, final ISpriteVertexBufferObject pSpriteVertexBufferObject, final ShaderProgram pShaderProgram) {
		super(pX, pY, pWidth, pHeight, pTextureRegion, pSpriteVertexBufferObject, pShaderProgram);

		this.updateModifiers();
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setSecondsPerRotation(final float pSecondsPerRotation) {
		this.mSecondsPerRotation = pSecondsPerRotation;

		this.updateModifiers();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	private void updateModifiers() {
		final float fromRotation;
		final float toRotation;
		if(this.mSecondsPerRotation > 0) {
			fromRotation = 0;
			toRotation = 360;
		} else {
			fromRotation = 0;
			toRotation = -360;
		}

		if(this.mLoopModifier == null) {
			this.mRotationModifier = new RotationModifier(this.mSecondsPerRotation, fromRotation, toRotation);
			this.mLoopModifier = new LoopEntityModifier(this.mRotationModifier);
			this.registerEntityModifier(this.mLoopModifier);
		} else {
			this.mLoopModifier.reset();
			this.mRotationModifier.reset(this.mSecondsPerRotation, fromRotation, toRotation);
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
