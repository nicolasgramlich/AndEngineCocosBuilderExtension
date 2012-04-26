package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.extension.cocosbuilder.entity.CCScale9Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

import android.content.res.AssetManager;

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
	protected IEntity createEntity(final String pEntityName, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final ITextureRegion textureRegion = this.getTextureRegion(pAttributes, pCCBEntityLoaderDataSource);

		final float insetLeft = this.getInsetLeft(pAttributes);
		final float insetRight = this.getInsetRight(pAttributes);
		final float insetTop = this.getInsetTop(pAttributes);
		final float insetBottom = this.getInsetBottom(pAttributes);

		return new CCScale9Sprite(pX, pY, pWidth, pHeight, textureRegion, insetLeft, insetTop, insetRight, insetBottom, pCCBEntityLoaderDataSource.getVertexBufferObjectManager());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected float getInsetLeft(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_LEFT, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_LEFT_VALUE_DEFAULT);
	}

	protected float getInsetTop(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_TOP, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_TOP_VALUE_DEFAULT);
	}

	protected float getInsetRight(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_RIGHT, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_RIGHT_VALUE_DEFAULT);
	}

	protected float getInsetBottom(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_BOTTOM, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_INSET_BOTTOM_VALUE_DEFAULT);
	}

	protected ITextureRegion getTextureRegion(final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final String textureName = SAXUtils.getAttributeOrThrow(pAttributes, CCScale9SpriteEntityLoader.TAG_CCSCALE9SPRITE_ATTRIBUTE_TEXTUREREGION);

		final TextureManager textureManager = pCCBEntityLoaderDataSource.getTextureManager();
		final AssetManager assetManager = pCCBEntityLoaderDataSource.getAssetManager();
		final String texturePath = pCCBEntityLoaderDataSource.getAssetBasePath() + textureName;

		final ITexture texture = textureManager.getTexture(textureName, assetManager, texturePath);

		texture.load();

		return TextureRegionFactory.extractFromTexture(texture);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
