package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.text.Text;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.opengl.font.IFont;
import org.andengine.util.SAXUtils;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.align.VerticalAlign;
import org.andengine.util.escape.XMLUnescaper;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:44:04 - 25.04.2012
 */
public abstract class CCLabelEntityLoader extends CCNodeEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String TAG_CCLABEL_ATTRIBUTE_TEXT = "text";

	private static final String TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL = "horizontalAlign";
	private static final int TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_LEFT = 0;
	private static final int TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_CENTER = 1;
	private static final int TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_RIGHT = 2;
	private static final int TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_DEFAULT = CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_LEFT;

	private static final String TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL = "verticalAlign";
	private static final int TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_VALUE_TOP = 0;
	private static final int TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_VALUE_CENTER = 1;
	private static final int TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_VALUE_BOTTOM = 2;
	private static final int TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_DEFAULT = CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_VALUE_TOP;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	protected CCLabelEntityLoader(final String ... pEntityNames) {
		super(pEntityNames);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract IFont getFont(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException;
	protected abstract IEntity createCCLabel(final IEntity pParent, float pX, float pY, final IFont font, final CharSequence text, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException;

	@Override
	protected IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final IFont font = this.getFont(pParent, pAttributes, pCCBEntityLoaderData);
		final CharSequence text = this.getText(pParent, pAttributes, pCCBEntityLoaderData);

		return this.createCCLabel(pParent, pX, pY, font, text, pAttributes, pCCBEntityLoaderData);
	}

	@Override
	protected void setAttributes(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		super.setAttributes(pEntity, pParent, pAttributes, pCCBEntityLoaderData);

		this.setCCLabelAttributes((Text)pEntity, pParent, pAttributes, pCCBEntityLoaderData);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void setCCLabelAttributes(final Text pText, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		this.setCCLabelBlendFunction(pText, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCLabelHorizontalAlign(pText, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCLabelVerticalAlign(pText, pParent, pAttributes, pCCBEntityLoaderData);
	}


	protected void setCCLabelBlendFunction(final Text pText, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pText.setBlendFunction(this.getBlendFunctionSource(pText, pParent, pAttributes, pCCBEntityLoaderData), this.getBlendFunctionDestination(pText, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected void setCCLabelHorizontalAlign(final Text pText, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		final int horizontalAlignValue = SAXUtils.getIntAttribute(pAttributes, CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL, CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_DEFAULT);
		final HorizontalAlign horizontalAlign;
		switch(horizontalAlignValue) {
			case CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_LEFT:
				horizontalAlign = HorizontalAlign.LEFT;
				break;
			case CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_CENTER:
				horizontalAlign = HorizontalAlign.CENTER;
				break;
			case CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL_VALUE_RIGHT:
				horizontalAlign = HorizontalAlign.RIGHT;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value for '" + CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_HORIZONTAL +"': '" + horizontalAlignValue + "'.");
		}
		pText.setHorizontalAlign(horizontalAlign);
	}

	protected void setCCLabelVerticalAlign(final Text pText, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		final int verticalAlignValue = SAXUtils.getIntAttribute(pAttributes, CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL, CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_DEFAULT);
		final VerticalAlign verticalAlign;
		switch(verticalAlignValue) {
			case CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_VALUE_TOP:
				verticalAlign = VerticalAlign.TOP;
				break;
			case CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_VALUE_CENTER:
				verticalAlign = VerticalAlign.CENTER;
				break;
			case CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL_VALUE_BOTTOM:
				verticalAlign = VerticalAlign.BOTTOM;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value for '" + CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_ALIGN_VERTICAL +"': '" + verticalAlignValue + "'.");
		}

		// TODO Vertical Align?
//		pText.setVerticalAlign(verticalAlign);
	}

	protected CharSequence getText(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		final String rawText = SAXUtils.getAttributeOrThrow(pAttributes, CCLabelEntityLoader.TAG_CCLABEL_ATTRIBUTE_TEXT);
		return XMLUnescaper.unescape(rawText);
	}

	protected static String getFontID(final String pFontName) {
		return pFontName + "@" + "NOSIZE";
	}

	protected static String getFontID(final String pFontName, final float pFontSize) {
		return pFontName + "@" + pFontSize;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
