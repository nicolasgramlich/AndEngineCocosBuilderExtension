package org.andengine.extension.cocosbuilder;

import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.level.IEntityLoaderDataSource;

import android.content.res.AssetManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:13:37 - 19.04.2012
 */
public class CCBEntityLoaderDataSource implements IEntityLoaderDataSource {
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

	public CCBEntityLoaderDataSource(final AssetManager pAssetManager, final String pAssetBasePath, final VertexBufferObjectManager pVertexBufferObjectManager, final TextureManager pTextureManager, final FontManager pFontManager) {
		this.mAssetManager = pAssetManager;
		this.mAssetBasePath = pAssetBasePath;
		this.mVertexBufferObjectManager = pVertexBufferObjectManager;
		this.mTextureManager = pTextureManager;
		this.mFontManager = pFontManager;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public AssetManager getAssetManager() {
		return this.mAssetManager;
	}

	public String getAssetBasePath() {
		return this.mAssetBasePath;
	}

	public VertexBufferObjectManager getVertexBufferObjectManager() {
		return this.mVertexBufferObjectManager;
	}

	public TextureManager getTextureManager() {
		return this.mTextureManager;
	}

	public FontManager getFontManager() {
		return this.mFontManager;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
