package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

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
		pSprite.setFlippedHorizontal(this.isFlippedHorizontal(pAttributes));
		pSprite.setFlippedVertical(this.isFlippedVertical(pAttributes));
	}

	protected boolean isFlippedHorizontal(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_HORIZONTAL_DEFAULT);
	}

	protected boolean isFlippedVertical(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_FLIPPED_VERTICAL_DEFAULT);
	}

	public static ITextureRegion getTextureRegion(final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final String textureName = SAXUtils.getAttributeOrThrow(pAttributes, CCSpriteEntityLoader.TAG_CCSPRITE_ATTRIBUTE_TEXTUREREGION);

		final ITexture texture = pCCBEntityLoaderDataSource.getTextureManager().getTexture(textureName, pCCBEntityLoaderDataSource.getAssetManager(), pCCBEntityLoaderDataSource.getAssetBasePath() + textureName);
		texture.load();

		return TextureRegionFactory.extractFromTexture(texture);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
