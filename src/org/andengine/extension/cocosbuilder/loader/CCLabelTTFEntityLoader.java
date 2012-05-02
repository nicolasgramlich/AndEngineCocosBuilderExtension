package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCLabelTTF;
import org.andengine.extension.cocosbuilder.loader.adt.CCBSizeType;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.EmptyTexture;
import org.andengine.opengl.texture.PixelFormat;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:34:26 - 24.04.2012
 */
public class CCLabelTTFEntityLoader extends CCLabelEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCLabelTTF";

	private static final String TAG_CCLABELTTF_ATTRIBUTE_FONT_NAME = "fontName";
	private static final String TAG_CCLABELTTF_ATTRIBUTE_FONT_SIZE = "fontSize";
	private static final String TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_WIDTH = "dimensionWidth";
	private static final float TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_WIDTH_DEFAULT = 0;
	private static final String TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_HEIGHT = "dimensionHeight";
	private static final float TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_HEIGHT_DEFAULT = 0;
	private static final String TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_TYPE = "dimensionType";
	private static final String TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_TYPE_VALUE_DEFAULT = CCBSizeType.ABSOLUTE_VALUE;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCLabelTTFEntityLoader() {
		super(CCLabelTTFEntityLoader.ENTITY_NAMES);
	}

	protected CCLabelTTFEntityLoader(final String ... pEntityNames) {
		super(pEntityNames);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IFont getFont(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final String fontName = SAXUtils.getAttributeOrThrow(pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_FONT_NAME);
		final float fontSize = SAXUtils.getFloatAttributeOrThrow(pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_FONT_SIZE);

		final String fontID = CCLabelEntityLoader.getFontID(fontName, fontSize);

		final FontManager fontManager = pCCBEntityLoaderData.getFontManager();
		if(fontManager.hasMappedFont(fontID)) {
			return fontManager.getMappedFont(fontID);
		} else {
			final TextureManager textureManager = pCCBEntityLoaderData.getTextureManager();
			final AssetManager assetManager = pCCBEntityLoaderData.getAssetManager();

			final String fontPath = pCCBEntityLoaderData.getAssetBasePath() + fontName;
			final Typeface typeface = Typeface.createFromAsset(assetManager, fontPath);
			final Font font = new Font(fontManager, new EmptyTexture(textureManager, 512, 512, PixelFormat.RGBA_8888, TextureOptions.BILINEAR), typeface, fontSize, true, Color.WHITE);
			font.load();

			fontManager.addMappedFont(fontID, font);

			return font;
		}
	}

	@Override
	protected IEntity createCCLabel(final IEntity pParent, final float pX, final float pY, final IFont pFont, final CharSequence pText, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final TextOptions textOptions = this.getTextOptions(pParent, pX, pY, pFont, pText, pAttributes, pCCBEntityLoaderData);

		return new CCLabelTTF(pX, pY, pFont, pText, textOptions, pCCBEntityLoaderData.getVertexBufferObjectManager());
	}

	@Override
	protected float getWidth(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return 0;
	}

	@Override
	protected float getHeight(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return 0;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected TextOptions getTextOptions(final IEntity pParent, final float pX, final float pY, final IFont pFont, final CharSequence pText, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		final float dimensionWidth = this.getDimensionWidth(pParent, pAttributes, pCCBEntityLoaderData);
		final float dimensionHeight = this.getDimensionHeight(pParent, pAttributes, pCCBEntityLoaderData);

		final TextOptions textOptions;
		if(dimensionWidth == TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_WIDTH_DEFAULT || dimensionHeight == TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_HEIGHT_DEFAULT) {
			textOptions = new TextOptions();
		} else {
			textOptions = new TextOptions(AutoWrap.WORDS, dimensionWidth);
		}
		return textOptions;
	}

	protected float getDimensionWidth(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return CCBSizeType.getWidth(pParent, pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_WIDTH, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_WIDTH_DEFAULT, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_TYPE, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_TYPE_VALUE_DEFAULT);
	}

	protected float getDimensionHeight(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return CCBSizeType.getHeight(pParent, pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_HEIGHT, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_HEIGHT_DEFAULT, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_TYPE, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_DIMENSION_TYPE_VALUE_DEFAULT);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
