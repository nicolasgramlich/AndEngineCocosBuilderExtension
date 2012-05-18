package org.andengine.extension.cocosbuilder.loader.adt;

import org.andengine.entity.IEntity;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 11:50:22 - 27.04.2012
 */
public enum CCBSizeType {
	// ===========================================================
	// Elements
	// ===========================================================

	ABSOLUTE {
		@Override
		public float calculateWidth(final float pWidth, final IEntity pParent) {
			return pWidth;
		}

		@Override
		public float calculateHeight(final float pHeight, final IEntity pParent) {
			return pHeight;
		}
	},
	RELATIVE_PARENT {
		@Override
		public float calculateWidth(final float pWidth, final IEntity pParent) {
			return pParent.getWidth() - pWidth;
		}

		@Override
		public float calculateHeight(final float pHeight, final IEntity pParent) {
			return pParent.getHeight() - pHeight;
		}
	},
	PERCENT_PARENT {
		@Override
		public float calculateWidth(final float pWidth, final IEntity pParent) {
			return pParent.getWidth() * pWidth / 100f;
		}

		@Override
		public float calculateHeight(final float pHeight, final IEntity pParent) {
			return pParent.getHeight() * pHeight / 100f;
		}
	},
	PERCENT_PARENT_WIDTH_FIXED_HEIGHT {
		@Override
		public float calculateWidth(final float pWidth, final IEntity pParent) {
			return pParent.getWidth() * pWidth / 100f;
		}

		@Override
		public float calculateHeight(final float pHeight, final IEntity pParent) {
			return pHeight;
		}
	},
	PERCENT_PARENT_HEIGHT_FIXED_WIDTH {
		@Override
		public float calculateWidth(final float pWidth, final IEntity pParent) {
			return pWidth;
		}

		@Override
		public float calculateHeight(final float pHeight, final IEntity pParent) {
			return pParent.getHeight() * pHeight / 100f;
		}
	};

	// ===========================================================
	// Constants
	// ===========================================================

	public static final String ABSOLUTE_VALUE = "absolute";
	public static final String RELATIVE_PARENT_VALUE = "relativeParent";
	public static final String PERCENT_PARENT_VALUE = "percentParent";
	public static final String PERCENT_PARENT_WIDTH_FIXED_HEIGHT_VALUE = "percentParentWidthFixedHeight";
	public static final String PERCENT_PARENT_HEIGHT_FIXED_WIDTH_VALUE = "percentParentHeightFixedWidth";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public static CCBSizeType fromString(final String pString) {
		if(pString == null) {
			throw new IllegalArgumentException("pString must not be null!");
		} else if(pString.equals(ABSOLUTE_VALUE)) {
			return ABSOLUTE;
		} else if(pString.equals(RELATIVE_PARENT_VALUE)) {
			return RELATIVE_PARENT;
		} else if(pString.equals(PERCENT_PARENT_VALUE)) {
			return PERCENT_PARENT;
		} else if(pString.equals(PERCENT_PARENT_WIDTH_FIXED_HEIGHT_VALUE)) {
			return PERCENT_PARENT_WIDTH_FIXED_HEIGHT;
		} else if(pString.equals(PERCENT_PARENT_HEIGHT_FIXED_WIDTH_VALUE)) {
			return PERCENT_PARENT_HEIGHT_FIXED_WIDTH;
		} else {
			throw new IllegalArgumentException("Unexpected " + CCBSizeType.class.getSimpleName() + ": '" + pString + "!");
		}
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public abstract float calculateWidth(final float pWidth, final IEntity pParent);
	public abstract float calculateHeight(final float pHeight, final IEntity pParent);

	public static float getWidth(final IEntity pParent, final Attributes pAttributes, final String pWidthAttributeName, final float pWidthDefault, final String pWidthTypeAttributeName, final String pWidthTypeAttributeValueDefault) {
		final float width = SAXUtils.getFloatAttribute(pAttributes, pWidthAttributeName, pWidthDefault);
		final CCBSizeType ccbSizeType = CCBSizeType.fromString(SAXUtils.getAttribute(pAttributes, pWidthTypeAttributeName, pWidthTypeAttributeValueDefault));
		return ccbSizeType.calculateWidth(width, pParent);
	}

	public static float getWidthOrThrow(final IEntity pParent, final Attributes pAttributes, final String pWidthAttributeName, final String pWidthTypeAttributeName, final String pWidthTypeAttributeValueDefault) {
		final float width = SAXUtils.getFloatAttributeOrThrow(pAttributes, pWidthAttributeName);
		final CCBSizeType ccbSizeType = CCBSizeType.fromString(SAXUtils.getAttribute(pAttributes, pWidthTypeAttributeName, pWidthTypeAttributeValueDefault));
		return ccbSizeType.calculateWidth(width, pParent);
	}

	public static float getHeight(final IEntity pParent, final Attributes pAttributes, final String pHeightAttributeName, final float pHeightDefault, final String pHeightTypeAttributeName, final String pHeightTypeAttributeValueDefault) {
		final float height = SAXUtils.getFloatAttribute(pAttributes, pHeightAttributeName, pHeightDefault);
		final CCBSizeType ccbSizeType = CCBSizeType.fromString(SAXUtils.getAttribute(pAttributes, pHeightTypeAttributeName, pHeightTypeAttributeValueDefault));
		return ccbSizeType.calculateHeight(height, pParent);
	}

	public static float getHeightOrThrow(final IEntity pParent, final Attributes pAttributes, final String pHeightAttributeName, final String pHeightTypeAttributeName, final String pHeightTypeAttributeValueDefault) {
		final float height = SAXUtils.getFloatAttributeOrThrow(pAttributes, pHeightAttributeName);
		final CCBSizeType ccbSizeType = CCBSizeType.fromString(SAXUtils.getAttribute(pAttributes, pHeightTypeAttributeName, pHeightTypeAttributeValueDefault));
		return ccbSizeType.calculateHeight(height, pParent);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
