package org.andengine.extension.cocosbuilder;

import java.util.HashMap;

import org.andengine.extension.cocosbuilder.loader.CCBEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCBFileEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCLabelBMFontEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCLabelTTFEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCLayerColorEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCLayerEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCLayerGradientEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCNodeEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCScale9SpriteEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCScrollViewEntityLoader;
import org.andengine.extension.cocosbuilder.loader.CCSpriteEntityLoader;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.level.IEntityLoader;
import org.andengine.util.level.LevelLoader;

import android.content.res.AssetManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 16:32:04 - 18.04.2012
 */
public class CCBLevelLoader extends LevelLoader<CCBEntityLoaderData, ICCBEntityLoaderListener, CCBLevelLoaderResult> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final AssetManager mAssetManager;
	private final String mAssetBasePath;
	private final VertexBufferObjectManager mVertexBufferObjectManager;
	private final TextureManager mTextureManager;
	private final FontManager mFontManager;

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCBLevelLoader(final AssetManager pAssetManager, final String pAssetBasePath, final VertexBufferObjectManager pVertexBufferObjectManager, final TextureManager pTextureManager, final FontManager pFontManager) {
		this.mAssetManager = pAssetManager;
		this.mAssetBasePath = pAssetBasePath;
		this.mVertexBufferObjectManager = pVertexBufferObjectManager;
		this.mTextureManager = pTextureManager;
		this.mFontManager = pFontManager;

		this.registerEntityLoader(new CCBEntityLoader());
		this.registerEntityLoader(new CCBFileEntityLoader());
		this.registerEntityLoader(new CCNodeEntityLoader());
		this.registerEntityLoader(new CCLayerEntityLoader());
		this.registerEntityLoader(new CCLayerColorEntityLoader());
		this.registerEntityLoader(new CCLayerGradientEntityLoader());
		this.registerEntityLoader(new CCSpriteEntityLoader());
		this.registerEntityLoader(new CCLabelTTFEntityLoader());
		this.registerEntityLoader(new CCLabelBMFontEntityLoader());
		this.registerEntityLoader(new CCScale9SpriteEntityLoader());
		this.registerEntityLoader(new CCScrollViewEntityLoader());
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected CCBEntityLoaderData onCreateEntityLoaderData() {
		return new CCBEntityLoaderData(this.mAssetManager, this.mAssetBasePath, this.mVertexBufferObjectManager, this.mTextureManager, this.mFontManager);
	}

	@Override
	protected CCBLevelLoaderContentHandler onCreateLevelLoaderContentHandler(final HashMap<String, IEntityLoader<CCBEntityLoaderData>> pCCBEntityLoaders, final IEntityLoader<CCBEntityLoaderData> pDefaultCCBEntityLoader, final CCBEntityLoaderData pCCBEntityLoaderData, final ICCBEntityLoaderListener pCCBEntityLoaderListener) {
		return new CCBLevelLoaderContentHandler(pCCBEntityLoaders, pDefaultCCBEntityLoader, pCCBEntityLoaderData, pCCBEntityLoaderListener);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
