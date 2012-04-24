package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.text.Text;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.extension.cocosbuilder.entity.CCLabelTTF;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.EmptyTexture;
import org.andengine.opengl.texture.PixelFormat;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.util.SAXUtils;
import org.andengine.util.align.HorizontalAlign;
import org.andengine.util.align.VerticalAlign;
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
public class CCLabelTTFEntityLoader extends CCNodeEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCLabelTTF";

	private static final String TAG_CCLABELTTF_ATTRIBUTE_TEXT = "text";

	private static final String TAG_CCLABELTTF_ATTRIBUTE_FONT_NAME = "fontName";
	private static final String TAG_CCLABELTTF_ATTRIBUTE_FONT_SIZE = "fontSize";

	private static final String TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL = "horizontalAlign";
	private static final int TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_LEFT = 0;
	private static final int TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_CENTER = 1;
	private static final int TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_RIGHT = 2;
	private static final String TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL = "verticalAlign";
	private static final int TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL_VALUE_TOP = 0;
	private static final int TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL_VALUE_CENTER = 1;
	private static final int TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL_VALUE_BOTTOM = 2;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCLabelTTFEntityLoader() {
		super(CCLabelTTFEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(String pEntityName, float pX, float pY, float pWidth, float pHeight, Attributes pAttributes, CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final IFont font = this.getFont(pAttributes, pCCBEntityLoaderDataSource);
		final CharSequence text = this.getText(pAttributes);

		return new CCLabelTTF(pX, pY, font, text, pCCBEntityLoaderDataSource.getVertexBufferObjectManager());
	}

	@Override
	protected void setAttributes(final IEntity pEntity, Attributes pAttributes) {
		super.setAttributes(pEntity, pAttributes);

		this.setCCLabelTTFAttributes((Text)pEntity, pAttributes);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void setCCLabelTTFAttributes(final Text pText, final Attributes pAttributes) {
		this.setCCLabelTTFBlendFunction(pText, pAttributes);
		this.setCCLabelTTFHorizontalAlign(pText, pAttributes);
	}


	protected void setCCLabelTTFBlendFunction(final Text pText, final Attributes pAttributes) {
		pText.setBlendFunction(this.getBlendFunctionSource(pAttributes), this.getBlendFunctionDestination(pAttributes));
	}

	protected void setCCLabelTTFHorizontalAlign(final Text pText, final Attributes pAttributes) {
		final int horizontalAlignValue = SAXUtils.getIntAttributeOrThrow(pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL);
		final HorizontalAlign horizontalAlign;
		switch(horizontalAlignValue) {
			case CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_LEFT:
				horizontalAlign = HorizontalAlign.LEFT;
				break;
			case CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_CENTER:
				horizontalAlign = HorizontalAlign.CENTER;
				break;
			case CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_RIGHT:
				horizontalAlign = HorizontalAlign.RIGHT;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value for '" + CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_HORIZONTAL +"': '" + horizontalAlignValue + "'.");
		}
		pText.setHorizontalAlign(horizontalAlign);
	}

	protected void setCCLabelTTFVerticalAlign(final Text pText, final Attributes pAttributes) {
		final int verticalAlignValue = SAXUtils.getIntAttributeOrThrow(pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL);
		final VerticalAlign verticalAlign;
		switch(verticalAlignValue) {
			case CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL_VALUE_TOP:
				verticalAlign = VerticalAlign.TOP;
				break;
			case CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL_VALUE_CENTER:
				verticalAlign = VerticalAlign.CENTER;
				break;
			case CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL_VALUE_BOTTOM:
				verticalAlign = VerticalAlign.BOTTOM;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value for '" + CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_ALIGN_VERTICAL +"': '" + verticalAlignValue + "'.");
		}

		// TODO Vertical Align?
//		pText.setVerticalAlign(verticalAlign);
	}

	protected CharSequence getText(final Attributes pAttributes) {
		return SAXUtils.getAttributeOrThrow(pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_TEXT);
	}

	protected IFont getFont(final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final String fontName = SAXUtils.getAttributeOrThrow(pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_FONT_NAME);
		final float fontSize = SAXUtils.getFloatAttributeOrThrow(pAttributes, CCLabelTTFEntityLoader.TAG_CCLABELTTF_ATTRIBUTE_FONT_SIZE);

		final String fontID = fontName + "@" + fontSize;

		final FontManager fontManager = pCCBEntityLoaderDataSource.getFontManager();
		if(fontManager.hasMappedFont(fontID)) {
			return fontManager.getMappedFont(fontID);
		} else {
			final TextureManager textureManager = pCCBEntityLoaderDataSource.getTextureManager();
			final AssetManager assetManager = pCCBEntityLoaderDataSource.getAssetManager();

			final String fontPath = pCCBEntityLoaderDataSource.getAssetBasePath() + fontName;
			final Typeface typeface = Typeface.createFromAsset(assetManager, fontPath);
			final Font font = new Font(fontManager, new EmptyTexture(textureManager, 512, 512, PixelFormat.RGBA_8888, TextureOptions.BILINEAR), typeface, fontSize, true, Color.WHITE);
			font.load();

			fontManager.addMappedFont(fontID, font);

			return font;
		}
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
