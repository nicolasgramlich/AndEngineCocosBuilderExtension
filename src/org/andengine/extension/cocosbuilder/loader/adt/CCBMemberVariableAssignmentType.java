package org.andengine.extension.cocosbuilder.loader.adt;

import java.lang.reflect.Field;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.exception.CCBMemberVariableAssignmentException;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 15:04:35 - 02.05.2012
 */
public enum CCBMemberVariableAssignmentType {
	// ===========================================================
	// Elements
	// ===========================================================

	NONE {
		@Override
		public void assignMemberVariable(final IEntity pEntity, final String pMemberVariableName, final IEntity pRoot, final Object pOwner) {
			/* Nothing. */
		}
	},
	ROOT {
		@Override
		public void assignMemberVariable(final IEntity pEntity, final String pMemberVariableName, final IEntity pRoot, final Object pOwner) throws CCBMemberVariableAssignmentException {
			try {
				final Class<? extends IEntity> c = pRoot.getClass();

				final Field memberVariableField = c.getDeclaredField(pMemberVariableName);

				memberVariableField.set(pRoot, pEntity);
			} catch (NoSuchFieldException e) {
				throw new CCBMemberVariableAssignmentException(e);
			} catch (IllegalAccessException e) {
				throw new CCBMemberVariableAssignmentException(e);
			}
		}
	},
	OWNER {
		@Override
		public void assignMemberVariable(final IEntity pEntity, final String pMemberVariableName, final IEntity pRoot, final Object pOwner) throws CCBMemberVariableAssignmentException {
			try {
				final Class<?> clazz = pOwner.getClass();

				final Field memberVariableField = clazz.getDeclaredField(pMemberVariableName);

				memberVariableField.set(pOwner, pEntity);
			} catch (NoSuchFieldException e) {
				throw new CCBMemberVariableAssignmentException(e);
			} catch (IllegalAccessException e) {
				throw new CCBMemberVariableAssignmentException(e);
			}
		}
	};

	// ===========================================================
	// Constants
	// ===========================================================

	public static final String NONE_VALUE = "none";
	public static final String ROOT_VALUE = "root";
	public static final String OWNER_VALUE = "owner";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public static CCBMemberVariableAssignmentType fromString(final String pString) {
		if(pString == null) {
			throw new IllegalArgumentException("pString must not be null!");
		} else if(pString.equals(CCBMemberVariableAssignmentType.NONE_VALUE)) {
			return NONE;
		} else if(pString.equals(CCBMemberVariableAssignmentType.ROOT_VALUE)) {
			return ROOT;
		} else if(pString.equals(CCBMemberVariableAssignmentType.OWNER_VALUE)) {
			return OWNER;
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

	public abstract void assignMemberVariable(final IEntity pEntity, final String pMemberVariableName, final IEntity pRoot, final Object pOwner) throws CCBMemberVariableAssignmentException;

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
