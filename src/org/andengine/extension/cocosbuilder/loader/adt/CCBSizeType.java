package org.andengine.extension.cocosbuilder.loader.adt;

import org.andengine.entity.IEntity;

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
	};

	// ===========================================================
	// Constants
	// ===========================================================

	public static final String ABSOLUTE_VALUE = "absolute";
	public static final String RELATIVE_PARENT_VALUE = "relativeParent";
	public static final String PERCENT_PARENT_VALUE = "percentParent";

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

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
