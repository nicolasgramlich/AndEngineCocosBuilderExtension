package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

import android.content.res.AssetManager;
import android.opengl.GLES20;

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
	private static final boolean TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL_DEFAULT = false;
	private static final String TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL = "flipY";
	private static final boolean TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL_DEFAULT = false;

	private static final String TAG_CCSPRITE_ATTRIBUTE_BLENDFUNCTION_SOURCE= "blendFunctionSource";
	private static final int TAG_CCSPRITE_ATTRIBUTE_BLENDFUNCTION_SOURCE_DEFAULT = GLES20.GL_SRC_ALPHA;
	private static final String TAG_CCSPRITE_ATTRIBUTE_BLENDFUNCTION_DESTINATION = "blendFunctionDestination";
	private static final int TAG_CCSPRITE_ATTRIBUTE_BLENDFUNCTION_DESTINATION_DEFAULT = GLES20.GL_ONE_MINUS_SRC_ALPHA;

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
	public Sprite onLoadEntity(final String pEntityName, final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final float x = this.getX(pAttributes);
		final float y = this.getY(pAttributes);
		final float width = this.getWidth(pAttributes);
		final float height = this.getHeight(pAttributes);

		final ITextureRegion textureRegion = CCSpriteEntityLoader.getTextureRegion(pAttributes, pCCBEntityLoaderDataSource);

		final Sprite sprite = new Sprite(x, y, width, height, textureRegion, pCCBEntityLoaderDataSource.getVertexBufferObjectManager());

		this.setAttributes(sprite, pAttributes);

		return sprite;
	}

	@Override
	protected <T extends IEntity> void setAttributes(final T pEntity, final Attributes pAttributes) {
		super.setAttributes(pEntity, pAttributes);

		this.setCCSpriteAttributes((Sprite)pEntity, pAttributes);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void setCCSpriteAttributes(final Sprite pSprite, final Attributes pAttributes) {
		pSprite.setFlipped(this.isFlippedHorizontal(pAttributes), this.isFlippedVertical(pAttributes));
		//pSprite.setBlendFunction(this.getBlendFunctionSource(pAttributes), this.getBlendFunctionDestination(pAttributes));
	}

	protected int getBlendFunctionDestination(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_BLENDFUNCTION_SOURCE, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_BLENDFUNCTION_SOURCE_DEFAULT);
	}

	protected int getBlendFunctionSource(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_BLENDFUNCTION_DESTINATION, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_BLENDFUNCTION_DESTINATION_DEFAULT);
	}

	protected boolean isFlippedHorizontal(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL_DEFAULT);
	}

	protected boolean isFlippedVertical(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL_DEFAULT);
	}

	protected static ITextureRegion getTextureRegion(final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
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
