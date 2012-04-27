package org.andengine.extension.cocosbuilder.loader.adt;

import org.andengine.entity.IEntity;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 10:19:21 - 27.04.2012
 */
public enum CCBPositionType {
	// ===========================================================
	// Elements
	// ===========================================================

	RELATIVE_TOPLEFT {
		@Override
		public float calculateX(final float pX, final IEntity pParent) {
			return pX;
		}

		@Override
		public float calculateY(final float pY, final IEntity pParent) {
			return pParent.getHeight() - pY;
		}
	},
	RELATIVE_TOPRIGHT {
		@Override
		public float calculateX(final float pX, final IEntity pParent) {
			return pParent.getWidth() - pX;
		}

		@Override
		public float calculateY(final float pY, final IEntity pParent) {
			return pParent.getHeight() - pY;
		}
	},
	RELATIVE_BOTTOMLEFT {
		@Override
		public float calculateX(final float pX, final IEntity pParent) {
			return pX;
		}

		@Override
		public float calculateY(final float pY, final IEntity pParent) {
			return pY;
		}
	},
	RELATIVE_BOTTOMRIGHT {
		@Override
		public float calculateX(final float pX, final IEntity pParent) {
			return pParent.getWidth() - pX;
		}

		@Override
		public float calculateY(final float pY, final IEntity pParent) {
			return pY;
		}
	},
	PERCENT_PARENT {
		@Override
		public float calculateX(final float pX, final IEntity pParent) {
			return pParent.getWidth() * pX / 100f;
		}

		@Override
		public float calculateY(final float pY, final IEntity pParent) {
			return pParent.getHeight() * pY / 100f;
		}
	};

	// ===========================================================
	// Constants
	// ===========================================================

	public static final String RELATIVE_TOPLEFT_VALUE = "relativeTopLeft";
	public static final String RELATIVE_TOPRIGHT_VALUE = "relativeTopRight";
	public static final String RELATIVE_BOTTOMLEFT_VALUE = "relativeBottomLeft";
	public static final String RELATIVE_BOTTOMRIGHT_VALUE = "relativeBottomRight";
	public static final String PERCENT_PARENT_VALUE = "percentParent";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public static CCBPositionType fromString(final String pString) {
		if(pString == null) {
			throw new IllegalArgumentException("pString must not be null!");
		} else if(pString.equals(CCBPositionType.RELATIVE_TOPLEFT_VALUE)) {
			return RELATIVE_TOPLEFT;
		} else if(pString.equals(CCBPositionType.RELATIVE_TOPRIGHT_VALUE)) {
			return RELATIVE_TOPRIGHT;
		} else if(pString.equals(CCBPositionType.RELATIVE_BOTTOMLEFT_VALUE)) {
			return RELATIVE_BOTTOMLEFT;
		} else if(pString.equals(CCBPositionType.RELATIVE_BOTTOMRIGHT_VALUE)) {
			return RELATIVE_BOTTOMRIGHT;
		} else if(pString.equals(CCBPositionType.PERCENT_PARENT_VALUE)) {
			return PERCENT_PARENT;
		} else {
			throw new IllegalArgumentException("Unexpected " + CCBPositionType.class.getSimpleName() + ": '" + pString + "!");
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

	public abstract float calculateX(final float pX, final IEntity pParent);
	public abstract float calculateY(final float pY, final IEntity pParent);

	public static float getX(final IEntity pParent, final Attributes pAttributes, final String pXAttributeName, final float pXDefault, final String pPositionTypeAttributeName, final String pPositionTypeAttributeValueDefault) {
		final float x = SAXUtils.getFloatAttribute(pAttributes, pXAttributeName, pXDefault);
		final CCBPositionType ccbPositionType = CCBPositionType.fromString(SAXUtils.getAttribute(pAttributes, pPositionTypeAttributeName, pPositionTypeAttributeValueDefault));
		return ccbPositionType.calculateX(x, pParent);
	}

	public static float getY(final IEntity pParent, final Attributes pAttributes, final String pYAttributeName, final float pYDefault, final String pPositionTypeAttributeName, final String pPositionTypeAttributeValueDefault) {
		final float y = SAXUtils.getFloatAttribute(pAttributes, pYAttributeName, pYDefault);
		final CCBPositionType ccbPositionType = CCBPositionType.fromString(SAXUtils.getAttribute(pAttributes, pPositionTypeAttributeName, pPositionTypeAttributeValueDefault));
		return ccbPositionType.calculateY(y, pParent);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
