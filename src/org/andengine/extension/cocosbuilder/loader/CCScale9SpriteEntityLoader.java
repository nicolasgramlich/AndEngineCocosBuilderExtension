package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCScale9Sprite;
import org.andengine.extension.cocosbuilder.exception.CCBLevelLoaderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:45:07 - 25.04.2012
 */
public class CCScale9SpriteEntityLoader extends CCNodeEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCScale9Sprite";

	private static final String TAG_CCSCALE9SPRITE_ATTRIBUTE_TEXTUREPACK = "texturePack";
	private static final String TAG_CCSCALE9SPRITE_ATTRIBUTE_TEXTUREREGION = "textureRegion";

	private static final String TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_LEFT = "insetLeft";
	private static final float TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_LEFT_VALUE_DEFAULT = 0;
	private static final String TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_TOP = "insetTop";
	private static final float TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_TOP_VALUE_DEFAULT = 0;
	private static final String TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_RIGHT = "insetRight";
	private static final float TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_RIGHT_VALUE_DEFAULT = 0;
	private static final String TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_BOTTOM = "insetBottom";
	private static final float TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_BOTTOM_VALUE_DEFAULT = 0;


	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCScale9SpriteEntityLoader() {
		super(CCScale9SpriteEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException, CCBLevelLoaderException {
		final ITextureRegion textureRegion = this.getTextureRegion(pParent, pAttributes, pCCBEntityLoaderData);

		final float insetLeft = this.getInsetLeft(pParent, pAttributes, pCCBEntityLoaderData);
		final float insetRight = this.getInsetRight(pParent, pAttributes, pCCBEntityLoaderData);
		final float insetTop = this.getInsetTop(pParent, pAttributes, pCCBEntityLoaderData);
		final float insetBottom = this.getInsetBottom(pParent, pAttributes, pCCBEntityLoaderData);

		return new CCScale9Sprite(pX, pY, pWidth, pHeight, textureRegion, insetLeft, insetTop, insetRight, insetBottom, pCCBEntityLoaderData.getVertexBufferObjectManager());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected float getInsetLeft(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_LEFT, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_LEFT_VALUE_DEFAULT);
	}

	protected float getInsetTop(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_TOP, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_TOP_VALUE_DEFAULT);
	}

	protected float getInsetRight(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_RIGHT, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_RIGHT_VALUE_DEFAULT);
	}

	protected float getInsetBottom(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_BOTTOM, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_BOTTOM_VALUE_DEFAULT);
	}

	protected ITextureRegion getTextureRegion(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException, CCBLevelLoaderException {
		return CCSpriteEntityLoader.getTextureRegion(pParent, pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_TEXTUREPACK, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_TEXTUREREGION, pCCBEntityLoaderData);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
