package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCSprite;
import org.andengine.extension.cocosbuilder.exception.CCBLevelLoaderException;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.SAXUtils;
import org.andengine.util.texturepack.TexturePack;
import org.andengine.util.texturepack.TexturePackLoader;
import org.andengine.util.texturepack.exception.TexturePackParseException;
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

	private static final String TAG_CCSPRITE_ATTRIBUTE_TEXTUREPACK = "texturePack";
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

	protected CCSpriteEntityLoader(final String ... pEntityNames) {
		super(pEntityNames);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final ITextureRegion textureRegion = this.getTextureRegion(pParent, pAttributes, pCCBEntityLoaderData);

		return new CCSprite(pX, pY, pWidth, pHeight, textureRegion, pCCBEntityLoaderData.getVertexBufferObjectManager());
	}

	@Override
	protected void setAttributes(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		super.setAttributes(pEntity, pParent, pAttributes, pCCBEntityLoaderData);

		this.setCCSpriteAttributes((Sprite)pEntity, pParent, pAttributes, pCCBEntityLoaderData);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void setCCSpriteAttributes(final Sprite pSprite, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		this.setCCSpriteFlipped(pSprite, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCSpriteBlendFunction(pSprite, pParent, pAttributes, pCCBEntityLoaderData);
	}


	protected void setCCSpriteBlendFunction(final Sprite pSprite, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pSprite.setBlendFunction(this.getBlendFunctionSource(pSprite, pParent, pAttributes, pCCBEntityLoaderData), this.getBlendFunctionDestination(pSprite, pParent, pAttributes, pCCBEntityLoaderData));
	}


	protected void setCCSpriteFlipped(final Sprite pSprite, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pSprite.setFlipped(this.isFlippedHorizontal(pSprite, pParent, pAttributes, pCCBEntityLoaderData), this.isFlippedVertical(pSprite, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected boolean isFlippedHorizontal(final Sprite pSprite, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL_VALUE_DEFAULT);
	}

	protected boolean isFlippedVertical(final Sprite pSprite, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL_VALUE_DEFAULT);
	}

	protected ITextureRegion getTextureRegion(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException, CCBLevelLoaderException {
		return CCSpriteEntityLoader.getTextureRegion(pParent, pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_TEXTUREPACK, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_TEXTUREREGION, pCCBEntityLoaderData);
	}

	public static ITextureRegion getTextureRegion(final IEntity pParent, final Attributes pAttributes, final String pTexturePackAttributeName, final String pTextureRegionAttributeName, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException, CCBLevelLoaderException {
		final boolean isOnTexturePack = SAXUtils.hasAttribute(pAttributes, pTexturePackAttributeName);

		if(isOnTexturePack) {
			return getTextureRegionFromTexturePack(pAttributes, pTexturePackAttributeName, pTextureRegionAttributeName, pCCBEntityLoaderData);
		} else {
			return getTextureRegionFromTexture(pAttributes, pTextureRegionAttributeName, pCCBEntityLoaderData);
		}
	}

	private static ITextureRegion getTextureRegionFromTexturePack(final Attributes pAttributes, final String pTexturePackAttributeName, final String pTextureRegionAttributeName, final CCBEntityLoaderData pCCBEntityLoaderData) {
		final TextureManager textureManager = pCCBEntityLoaderData.getTextureManager();
		final AssetManager assetManager = pCCBEntityLoaderData.getAssetManager();

		final String texturePackNameRaw = SAXUtils.getAttributeOrThrow(pAttributes, pTexturePackAttributeName);

		final String texturePackName;
		final String assetBasePath;
		if(texturePackNameRaw.indexOf('/') == -1) {
			texturePackName = texturePackNameRaw;
			assetBasePath = pCCBEntityLoaderData.getAssetBasePath();
		} else {
			final int splitPosition = texturePackNameRaw.lastIndexOf('/') + 1;
			texturePackName = texturePackNameRaw.substring(splitPosition);
			assetBasePath = pCCBEntityLoaderData.getAssetBasePath() + texturePackNameRaw.substring(0, splitPosition);
		}

		final String textureRegionName = SAXUtils.getAttributeOrThrow(pAttributes, pTextureRegionAttributeName);

		if(!pCCBEntityLoaderData.hasTexturePack(texturePackName)) {
			final TexturePack texturePack;
			try {
				final String texturePackPath = assetBasePath + texturePackName;

				final TexturePackLoader texturePackLoader = new TexturePackLoader(assetManager, textureManager);

				texturePack = texturePackLoader.loadFromAsset(texturePackPath, assetBasePath);
			} catch (final TexturePackParseException e) {
				throw new CCBLevelLoaderException("Error loading TexturePack: '" + texturePackName + "'.", e);
			}
			texturePack.loadTexture();
			pCCBEntityLoaderData.putTexturePack(texturePackName, texturePack);
		}
		final TexturePack texturePack = pCCBEntityLoaderData.getTexturePack(texturePackName);

		return texturePack.getTexturePackTextureRegionLibrary().get(textureRegionName);
	}

	private static ITextureRegion getTextureRegionFromTexture(final Attributes pAttributes, final String pTextureRegionAttributeName, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final TextureManager textureManager = pCCBEntityLoaderData.getTextureManager();
		final AssetManager assetManager = pCCBEntityLoaderData.getAssetManager();

		final String assetBasePath = pCCBEntityLoaderData.getAssetBasePath();
		final String textureName = SAXUtils.getAttributeOrThrow(pAttributes, pTextureRegionAttributeName);
		final String texturePath = assetBasePath + textureName;

		final ITexture texture;
		if(textureManager.hasMappedTexture(textureName)) {
			texture = textureManager.getMappedTexture(textureName);
		} else {
			texture = textureManager.getTexture(textureName, assetManager, texturePath);
		}

		return TextureRegionFactory.extractFromTexture(texture);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
