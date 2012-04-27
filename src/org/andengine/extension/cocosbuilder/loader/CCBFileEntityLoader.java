package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.CCBLevelLoader;
import org.andengine.extension.cocosbuilder.CCBLevelLoaderResult;
import org.andengine.extension.cocosbuilder.entity.CCBFileEntity;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

import android.content.res.AssetManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:11:11 - 26.04.2012
 */
public class CCBFileEntityLoader extends CCNodeEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCBFile";

	private static final String TAG_CCBFILE_ATTRIBUTE_CCBFILENAME = "ccbFileName";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCBFileEntityLoader() {
		super(CCBFileEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final String ccbFileName = SAXUtils.getAttributeOrThrow(pAttributes, CCBFileEntityLoader.TAG_CCBFILE_ATTRIBUTE_CCBFILENAME);

		final AssetManager assetManager = pCCBEntityLoaderData.getAssetManager();
		final String assetBasePath = pCCBEntityLoaderData.getAssetBasePath();
		final VertexBufferObjectManager vertexBufferObjectManager = pCCBEntityLoaderData.getVertexBufferObjectManager();
		final TextureManager textureManager = pCCBEntityLoaderData.getTextureManager();
		final FontManager fontManager = pCCBEntityLoaderData.getFontManager();

		final CCBLevelLoader ccbLevelLoader = new CCBLevelLoader(assetManager, assetBasePath, vertexBufferObjectManager, textureManager, fontManager);
		final String ccbFilePath = assetBasePath + ccbFileName;
		final CCBLevelLoaderResult ccbLevelLoaderResult = ccbLevelLoader.loadLevelFromAsset(assetManager, ccbFilePath, pCCBEntityLoaderData);
		final IEntity rootEntity = ccbLevelLoaderResult.getRootEntity();

		final CCBFileEntity ccbFileEntity = new CCBFileEntity(pX, pY, pWidth, pHeight);

		ccbFileEntity.attachChild(rootEntity);

		return ccbFileEntity;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
