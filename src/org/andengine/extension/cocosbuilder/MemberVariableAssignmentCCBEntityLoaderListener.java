package org.andengine.extension.cocosbuilder;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.loader.adt.CCBMemberVariableAssignmentType;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;


/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:59:45 - 02.05.2012
 */
public class MemberVariableAssignmentCCBEntityLoaderListener implements ICCBEntityLoaderListener {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String TAG_CCNODE_ATTRIBUTE_MEMBERVARIABLEASSIGNMENT_NAME = "memberVariableAssignmentName";
	private static final String TAG_CCNODE_ATTRIBUTE_MEMBERVARIABLEASSIGNMENT_TYPE = "memberVariableAssignmentType";
	private static final String TAG_CCNODE_ATTRIBUTE_MEMBERVARIABLEASSIGNMENT_TYPE_DEFAULT = CCBMemberVariableAssignmentType.NONE_VALUE;

	// ===========================================================
	// Fields
	// ===========================================================

	private final Object mOwner;

	// ===========================================================
	// Constructors
	// ===========================================================

	/**
	 * @param pOwner the {@link Object} to assign the member variables to, when the {@link CCBMemberVariableAssignmentType} in {@link ICCBEntityLoaderListener#onEntityLoaded(IEntity, int, CCBMemberVariableAssignmentType, String)} is {@link CCBMemberVariableAssignmentType#ASSIGN_TO_OWNER}.
	 */
	public MemberVariableAssignmentCCBEntityLoaderListener(final Object pOwner) {
		this.mOwner = pOwner;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onEntityLoaded(final IEntity pEntity, final Attributes pAttributes) {
		final String memberVarAssignmentName = SAXUtils.getAttribute(pAttributes, TAG_CCNODE_ATTRIBUTE_MEMBERVARIABLEASSIGNMENT_NAME, null);

		/* Early exit. */
		if(memberVarAssignmentName == null || memberVarAssignmentName.length() == 0) {
			return;
		}

		final String memberVariableAssignmentTypeValue = SAXUtils.getAttribute(pAttributes, TAG_CCNODE_ATTRIBUTE_MEMBERVARIABLEASSIGNMENT_TYPE, TAG_CCNODE_ATTRIBUTE_MEMBERVARIABLEASSIGNMENT_TYPE_DEFAULT);

		final CCBMemberVariableAssignmentType ccbMemberVariableAssignmentType = CCBMemberVariableAssignmentType.fromString(memberVariableAssignmentTypeValue);

		if(ccbMemberVariableAssignmentType != CCBMemberVariableAssignmentType.NONE) {
			ccbMemberVariableAssignmentType.assignMemberVariable(pEntity, memberVarAssignmentName, pEntity.getRootEntity(), this.mOwner);
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
