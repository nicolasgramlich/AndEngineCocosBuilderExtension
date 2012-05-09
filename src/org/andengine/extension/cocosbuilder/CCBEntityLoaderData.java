package org.andengine.extension.cocosbuilder;

import java.util.HashMap;

import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.level.IEntityLoaderData;
import org.andengine.util.texturepack.TexturePack;

import android.content.res.AssetManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:13:37 - 19.04.2012
 */
public class CCBEntityLoaderData implements IEntityLoaderData {
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

	private final HashMap<String, TexturePack> mTexturePackMapping = new HashMap<String, TexturePack>();

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCBEntityLoaderData(final AssetManager pAssetManager, final String pAssetBasePath, final VertexBufferObjectManager pVertexBufferObjectManager, final TextureManager pTextureManager, final FontManager pFontManager) {
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

	public boolean hasTexturePack(final String pTexturePackName) {
		return this.mTexturePackMapping.containsKey(pTexturePackName);
	}

	public void putTexturePack(String pTexturePackName, TexturePack pTexturePack) {
		this.mTexturePackMapping.put(pTexturePackName, pTexturePack);
	}

	public TexturePack getTexturePack(String pTexturePackName) {
		return this.mTexturePackMapping.get(pTexturePackName);
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
