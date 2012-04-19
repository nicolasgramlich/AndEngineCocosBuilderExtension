package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.util.SAXUtils;
import org.andengine.util.level.EntityLoader;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:25:58 - 18.04.2012
 */
public class CCSpriteEntityLoader extends EntityLoader<CCBEntityLoaderDataSource> {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCSprite";

	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_X = "x";
	private static final float TAG_CCNODE_ATTRIBUTE_POSITION_X_VALUE_DEFAULT = 0;
	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_Y = "y";
	private static final float TAG_CCNODE_ATTRIBUTE_POSITION_Y_VALUE_DEFAULT = 0;

	private static final String TAG_CCSPRITE_ATTRIBUTE_TEXTUREREGION = "textureRegion";

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
	public IEntity onLoadEntity(final String pEntityName, final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final float x = SAXUtils.getFloatAttribute(pAttributes, TAG_CCNODE_ATTRIBUTE_POSITION_X, TAG_CCNODE_ATTRIBUTE_POSITION_X_VALUE_DEFAULT);
		final float y = SAXUtils.getFloatAttribute(pAttributes, TAG_CCNODE_ATTRIBUTE_POSITION_Y, TAG_CCNODE_ATTRIBUTE_POSITION_Y_VALUE_DEFAULT);

		final String textureName = SAXUtils.getAttributeOrThrow(pAttributes, TAG_CCSPRITE_ATTRIBUTE_TEXTUREREGION);
		final ITexture texture = pCCBEntityLoaderDataSource.getTextureManager().getTexture(textureName, pCCBEntityLoaderDataSource.getAssetManager(), pCCBEntityLoaderDataSource.getAssetBasePath() + textureName);
		texture.load();
		final ITextureRegion textureRegion = TextureRegionFactory.extractFromTexture(texture);

		return new Sprite(x, y, textureRegion, pCCBEntityLoaderDataSource.getVertexBufferObjectManager());
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
