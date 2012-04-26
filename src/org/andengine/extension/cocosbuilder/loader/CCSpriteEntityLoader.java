package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.extension.cocosbuilder.entity.CCSprite;
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
 * @since 18:25:58 - 18.04.2012
 */
public class CCSpriteEntityLoader extends CCNodeEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCSprite";

	private static final String TAG_CCSPRITE_ATTRIBUTE_TEXTUREREGION = "textureRegion";

	private static final String TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL = "flipX";
	private static final boolean TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL_VALUE_DEFAULT = false;
	private static final String TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL = "flipY";
	private static final boolean TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL_VALUE_DEFAULT = false;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCSpriteEntityLoader() {
		super(CCSpriteEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(String pEntityName, float pX, float pY, float pWidth, float pHeight, Attributes pAttributes, CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final ITextureRegion textureRegion = this.getTextureRegion(pAttributes, pCCBEntityLoaderDataSource);

		return new CCSprite(pX, pY, pWidth, pHeight, textureRegion, pCCBEntityLoaderDataSource.getVertexBufferObjectManager());
	}

	@Override
	protected void setAttributes(final IEntity pEntity, Attributes pAttributes) {
		super.setAttributes(pEntity, pAttributes);

		this.setCCSpriteAttributes((Sprite)pEntity, pAttributes);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void setCCSpriteAttributes(final Sprite pSprite, final Attributes pAttributes) {
		this.setCCSpriteFlipped(pSprite, pAttributes);
		this.setCCSpriteBlendFunction(pSprite, pAttributes);
	}


	protected void setCCSpriteBlendFunction(final Sprite pSprite, final Attributes pAttributes) {
		pSprite.setBlendFunction(this.getBlendFunctionSource(pAttributes), this.getBlendFunctionDestination(pAttributes));
	}


	protected void setCCSpriteFlipped(final Sprite pSprite, final Attributes pAttributes) {
		pSprite.setFlipped(this.isFlippedHorizontal(pAttributes), this.isFlippedVertical(pAttributes));
	}

	protected boolean isFlippedHorizontal(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL_VALUE_DEFAULT);
	}

	protected boolean isFlippedVertical(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL_VALUE_DEFAULT);
	}

	protected ITextureRegion getTextureRegion(final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final String textureName = SAXUtils.getAttributeOrThrow(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_TEXTUREREGION);

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
