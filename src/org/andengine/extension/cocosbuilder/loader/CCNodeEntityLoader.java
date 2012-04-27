package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCNode;
import org.andengine.extension.cocosbuilder.loader.adt.CCBPositionType;
import org.andengine.extension.cocosbuilder.loader.adt.CCBSizeType;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

import android.opengl.GLES20;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:25:58 - 18.04.2012
 */
public class CCNodeEntityLoader extends CCEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float COLOR_COMPONENT_MAX = 255f;

	private static final String ENTITY_NAMES = "CCNode";

	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_TYPE = "positionType";
	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_TYPE_VALUE_DEFAULT = CCBPositionType.RELATIVE_BOTTOMLEFT_VALUE;
	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_X = "x";
	private static final float TAG_CCNODE_ATTRIBUTE_POSITION_X_VALUE_DEFAULT = 0;
	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_Y = "y";
	private static final float TAG_CCNODE_ATTRIBUTE_POSITION_Y_VALUE_DEFAULT = 0;

	private static final String TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH = "width";
	private static final float TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH_VALUE_DEFAULT = 0;
	private static final String TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT = "height";
	private static final float TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT_VALUE_DEFAULT = 0;
	private static final String TAG_CCNODE_ATTRIBUTE_SIZE_TYPE = "sizeType";
	private static final String TAG_CCNODE_ATTRIBUTE_SIZE_TYPE_VALUE_DEFAULT = CCBSizeType.ABSOLUTE_VALUE;

	private static final String TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X = "anchorPointX";
	private static final float TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X_VALUE_DEFAULT = 0.5f;
	private static final String TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y = "anchorPointY";
	private static final float TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y_VALUE_DEFAULT = 0.5f;

	private static final String TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET = "ignoreAnchorPointForOffset";
	private static final boolean TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET_VALUE_DEFAULT = false;

	private static final String TAG_CCNODE_ATTRIBUTE_SCALE_X = "scaleX";
	private static final float TAG_CCNODE_ATTRIBUTE_SCALE_X_VALUE_DEFAULT = 1;
	private static final String TAG_CCNODE_ATTRIBUTE_SCALE_Y = "scaleY";
	private static final float TAG_CCNODE_ATTRIBUTE_SCALE_Y_VALUE_DEFAULT = 1;

	private static final String TAG_CCNODE_ATTRIBUTE_ROTATION = "rotation";
	private static final float TAG_CCNODE_ATTRIBUTE_ROTATION_VALUE_DEFAULT = 0;

	private static final String TAG_CCNODE_ATTRIBUTE_TAG = "tag";
	private static final int TAG_CCNODE_ATTRIBUTE_TAG_VALUE_DEFAULT = -1; // TODO

	private static final String TAG_CCNODE_ATTRIBUTE_VISIBLE = "visible";
	private static final boolean TAG_CCNODE_ATTRIBUTE_VISIBLE_VALUE_DEFAULT = true;

	private static final String TAG_CCNODE_ATTRIBUTE_COLOR_RED = "red";
	private static final int TAG_CCNODE_ATTRIBUTE_COLOR_RED_VALUE_DEFAULT = 255;
	private static final String TAG_CCNODE_ATTRIBUTE_COLOR_GREEN = "green";
	private static final int TAG_CCNODE_ATTRIBUTE_COLOR_GREEN_VALUE_DEFAULT = 255;
	private static final String TAG_CCNODE_ATTRIBUTE_COLOR_BLUE = "blue";
	private static final int TAG_CCNODE_ATTRIBUTE_COLOR_BLUE_VALUE_DEFAULT = 255;
	private static final String TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA = "alpha";
	private static final int TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA_VALUE_DEFAULT = 255;

	private static final String TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_SOURCE= "blendFunctionSource";
	private static final int TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_SOURCE_VALUE_DEFAULT = GLES20.GL_SRC_ALPHA;
	private static final String TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_DESTINATION = "blendFunctionDestination";
	private static final int TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_DESTINATION_VALUE_DEFAULT = GLES20.GL_ONE_MINUS_SRC_ALPHA;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCNodeEntityLoader() {
		super(CCNodeEntityLoader.ENTITY_NAMES);
	}

	protected CCNodeEntityLoader(final String ... pEntityNames) {
		super(pEntityNames);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		return new CCNode(pX, pY, pWidth, pHeight);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	protected void setAttributes(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		this.setCCNodeAttributes(pEntity, pParent, pAttributes, pCCBEntityLoaderData);
	}


	public void setCCNodeAttributes(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		this.setCCNodeVisible(pEntity, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCNodeColor(pEntity, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCNodeRotation(pEntity, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCNodeScale(pEntity, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCNodeTag(pEntity, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCNodeAnchorCenter(pEntity, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCNodeIsIgnoreAnchorCenterForOffset(pEntity, pParent, pAttributes, pCCBEntityLoaderData);
	}


	protected void setCCNodeVisible(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pEntity.setVisible(this.isVisible(pEntity, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected void setCCNodeTag(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pEntity.setTag(this.getTag(pEntity, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected void setCCNodeScale(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pEntity.setScale(this.getScaleX(pEntity, pParent, pAttributes, pCCBEntityLoaderData), this.getScaleY(pEntity, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected void setCCNodeColor(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pEntity.setColor(this.getRed(pEntity, pParent, pAttributes, pCCBEntityLoaderData), this.getGreen(pEntity, pParent, pAttributes, pCCBEntityLoaderData), this.getBlue(pEntity, pParent, pAttributes, pCCBEntityLoaderData), this.getAlpha(pEntity, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected void setCCNodeRotation(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pEntity.setRotation(this.getRotation(pEntity, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected void setCCNodeAnchorCenter(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pEntity.setAnchorCenter(this.getAnchorPointX(pEntity, pParent, pAttributes, pCCBEntityLoaderData), this.getAnchorPointY(pEntity, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected void setCCNodeIsIgnoreAnchorCenterForOffset(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		if(this.isIgnoreAnchorPointForOffset(pEntity, pParent, pAttributes, pCCBEntityLoaderData)) {
			pEntity.setOffsetCenter(0, 0);
		}
	}


	@Override
	protected float getX(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return CCBPositionType.getX(pParent, pAttributes,  CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_X_VALUE_DEFAULT, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_TYPE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_TYPE_VALUE_DEFAULT);
	}

	@Override
	protected float getY(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return CCBPositionType.getY(pParent, pAttributes,  CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_Y_VALUE_DEFAULT, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_TYPE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_TYPE_VALUE_DEFAULT);
	}

	@Override
	protected float getWidth(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return CCBSizeType.getWidth(pParent, pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH_VALUE_DEFAULT, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_TYPE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_TYPE_VALUE_DEFAULT);
	}

	@Override
	protected float getHeight(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return CCBSizeType.getHeight(pParent, pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT_VALUE_DEFAULT, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_TYPE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_TYPE_VALUE_DEFAULT);
	}

	private boolean isIgnoreAnchorPointForOffset(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET_VALUE_DEFAULT);
	}

	protected int getTag(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_TAG, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_TAG_VALUE_DEFAULT);
	}

	protected float getAnchorPointX(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X_VALUE_DEFAULT);
	}

	protected float getAnchorPointY(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y_VALUE_DEFAULT);
	}

	protected float getRotation(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ROTATION, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ROTATION_VALUE_DEFAULT);
	}

	protected float getScaleX(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_X_VALUE_DEFAULT);
	}

	protected float getScaleY(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_Y_VALUE_DEFAULT);
	}

	protected float getRed(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_RED, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_RED_VALUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getGreen(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_GREEN, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_GREEN_VALUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getBlue(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_BLUE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_BLUE_VALUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getAlpha(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA_VALUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected boolean isVisible(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_VISIBLE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_VISIBLE_VALUE_DEFAULT);
	}

	protected int getBlendFunctionSource(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_SOURCE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_SOURCE_VALUE_DEFAULT);
	}

	protected int getBlendFunctionDestination(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_DESTINATION, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_DESTINATION_VALUE_DEFAULT);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
