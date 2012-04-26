package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCNode;
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

	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_X = "x";
	private static final float TAG_CCNODE_ATTRIBUTE_POSITION_X_VALUE_DEFAULT = 0;
	private static final String TAG_CCNODE_ATTRIBUTE_POSITION_Y = "y";
	private static final float TAG_CCNODE_ATTRIBUTE_POSITION_Y_VALUE_DEFAULT = 0;

	private static final String TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH = "width";
	private static final float TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH_VALUE_DEFAULT = 0;
	private static final String TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT = "height";
	private static final float TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT_VALUE_DEFAULT = 0;

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
	protected IEntity createEntity(final String pEntityName, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		return new CCNode(pX, pY, pWidth, pHeight);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	@Override
	protected void setAttributes(final IEntity pEntity, final Attributes pAttributes) {
		this.setCCNodeAttributes(pEntity, pAttributes);
	}


	public void setCCNodeAttributes(final IEntity pEntity, final Attributes pAttributes) {
		this.setCCNodeVisible(pEntity, pAttributes);
		this.setCCNodeColor(pEntity, pAttributes);
		this.setCCNodeRotation(pEntity, pAttributes);
		this.setCCNodeScale(pEntity, pAttributes);
		this.setCCNodeTag(pEntity, pAttributes);
		this.setCCNodeAnchorCenter(pEntity, pAttributes);
		this.setCCNodeIsIgnoreAnchorCenterForOffset(pEntity, pAttributes);
	}


	protected <T extends IEntity> void setCCNodeVisible(final T pEntity, final Attributes pAttributes) {
		pEntity.setVisible(this.isVisible(pAttributes));
	}

	protected <T extends IEntity> void setCCNodeTag(final T pEntity, final Attributes pAttributes) {
		pEntity.setTag(this.getTag(pAttributes));
	}

	protected <T extends IEntity> void setCCNodeScale(final T pEntity, final Attributes pAttributes) {
		pEntity.setScale(this.getScaleX(pAttributes), this.getScaleY(pAttributes));
	}

	protected <T extends IEntity> void setCCNodeColor(final T pEntity, final Attributes pAttributes) {
		pEntity.setColor(this.getRed(pAttributes), this.getGreen(pAttributes), this.getBlue(pAttributes), this.getAlpha(pAttributes));
	}

	protected <T extends IEntity> void setCCNodeRotation(final T pEntity, final Attributes pAttributes) {
		pEntity.setRotation(this.getRotation(pAttributes));
	}

	protected <T extends IEntity> void setCCNodeAnchorCenter(final T pEntity, final Attributes pAttributes) {
		pEntity.setAnchorCenter(this.getAnchorPointX(pAttributes), this.getAnchorPointY(pAttributes));
	}

	protected <T extends IEntity> void setCCNodeIsIgnoreAnchorCenterForOffset(final T pEntity, final Attributes pAttributes) {
		if(this.isIgnoreAnchorPointForOffset(pAttributes)) {
			pEntity.setOffsetCenter(0, 0);
		}
	}


	@Override
	protected float getX(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_X_VALUE_DEFAULT);
	}

	@Override
	protected float getY(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_POSITION_Y_VALUE_DEFAULT);
	}

	@Override
	protected float getWidth(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_WIDTH_VALUE_DEFAULT);
	}

	@Override
	protected float getHeight(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SIZE_HEIGHT_VALUE_DEFAULT);
	}

	private boolean isIgnoreAnchorPointForOffset(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_IGNORE_FOR_OFFSET_VALUE_DEFAULT);
	}

	protected int getTag(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_TAG, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_TAG_VALUE_DEFAULT);
	}

	protected float getAnchorPointX(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_X_VALUE_DEFAULT);
	}

	protected float getAnchorPointY(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ANCHORPOINT_Y_VALUE_DEFAULT);
	}

	protected float getRotation(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ROTATION, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_ROTATION_VALUE_DEFAULT);
	}

	protected float getScaleX(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_X, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_X_VALUE_DEFAULT);
	}

	protected float getScaleY(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_Y, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_SCALE_Y_VALUE_DEFAULT);
	}

	protected float getRed(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_RED, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_RED_VALUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getGreen(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_GREEN, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_GREEN_VALUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getBlue(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_BLUE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_BLUE_VALUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getAlpha(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_COLOR_ALPHA_VALUE_DEFAULT) / CCNodeEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected boolean isVisible(final Attributes pAttributes) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_VISIBLE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_VISIBLE_VALUE_DEFAULT);
	}

	protected int getBlendFunctionSource(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_SOURCE, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_SOURCE_VALUE_DEFAULT);
	}

	protected int getBlendFunctionDestination(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_DESTINATION, CCNodeEntityLoader.TAG_CCNODE_ATTRIBUTE_BLENDFUNCTION_DESTINATION_VALUE_DEFAULT);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
