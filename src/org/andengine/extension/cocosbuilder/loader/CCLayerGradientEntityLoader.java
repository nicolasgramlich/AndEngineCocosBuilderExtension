package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.primitive.Gradient;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCLayerGradient;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;


/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:53:32 - 23.04.2012
 */
public class CCLayerGradientEntityLoader extends CCNodeEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final float COLOR_COMPONENT_MAX = 255;

	private static final String ENTITY_NAMES = "CCLayerGradient";

	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_RED = "fromRed";
	private static final int TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_RED_VALUE_DEFAULT = 255;
	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_GREEN = "fromGreen";
	private static final int TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_GREEN_VALUE_DEFAULT = 255;
	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_BLUE = "fromBlue";
	private static final int TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_BLUE_VALUE_DEFAULT = 255;
	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_ALPHA = "fromAlpha";
	private static final int TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_ALPHA_VALUE_DEFAULT = 255;

	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_RED = "toRed";
	private static final int TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_RED_VALUE_DEFAULT = 255;
	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_GREEN = "toGreen";
	private static final int TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_GREEN_VALUE_DEFAULT = 255;
	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_BLUE = "toBlue";
	private static final int TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_BLUE_VALUE_DEFAULT = 255;
	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_ALPHA = "toAlpha";
	private static final int TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_ALPHA_VALUE_DEFAULT = 255;

	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_VECTOR_X = "vectorX";
	private static final float TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_VECTOR_X_VALUE_DEFAULT = 0;
	private static final String TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_VECTOR_Y = "vectorY";
	private static final float TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_VECTOR_Y_VALUE_DEFAULT = -1;

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCLayerGradientEntityLoader() {
		super(CCLayerGradientEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(String pEntityName, float pX, float pY, float pWidth, float pHeight, Attributes pAttributes, CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final VertexBufferObjectManager vertexBufferObjectManager = pCCBEntityLoaderData.getVertexBufferObjectManager();

		return new CCLayerGradient(pX, pY, pWidth, pHeight, vertexBufferObjectManager);
	}

	@Override
	protected void setAttributes(final IEntity pEntity, final Attributes pAttributes) {
		super.setAttributes(pEntity, pAttributes);

		this.setCCLayerGradientAttributes((Gradient)pEntity, pAttributes);
	}

	@Override
	protected <T extends IEntity> void setCCNodeColor(final T pEntity, final Attributes pAttributes) {
		/* Nothing. */
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void setCCLayerGradientAttributes(final Gradient pGradient, final Attributes pAttributes) {
		this.setCCLayerGradientGradient(pGradient, pAttributes);
	}


	protected void setCCLayerGradientGradient(final Gradient pGradient, final Attributes pAttributes) {
		final float fromRed = this.getFromRed(pAttributes);
		final float toRed = this.getToRed(pAttributes);

		final float fromGreen = this.getFromGreen(pAttributes);
		final float toGreen = this.getToGreen(pAttributes);

		final float fromBlue = this.getFromBlue(pAttributes);
		final float toBlue = this.getToBlue(pAttributes);

		final float fromAlpha = this.getFromAlpha(pAttributes);
		final float toAlpha = this.getToAlpha(pAttributes);

		final float gradientVectorX = this.getGradientVectorX(pAttributes);
		final float gradientVectorY = this.getGradientVectorY(pAttributes);

		pGradient.setGradient(fromRed, toRed, fromGreen, toGreen, fromBlue, toBlue, fromAlpha, toAlpha, gradientVectorX, gradientVectorY);
	}


	protected float getGradientVectorX(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_VECTOR_X, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_VECTOR_X_VALUE_DEFAULT);
	}

	protected float getGradientVectorY(final Attributes pAttributes) {
		return SAXUtils.getFloatAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_VECTOR_Y, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_VECTOR_Y_VALUE_DEFAULT);
	}

	protected float getFromRed(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_RED, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_RED_VALUE_DEFAULT) / CCLayerGradientEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getFromGreen(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_GREEN, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_GREEN_VALUE_DEFAULT) / CCLayerGradientEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getFromBlue(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_BLUE, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_BLUE_VALUE_DEFAULT) / CCLayerGradientEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getFromAlpha(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_ALPHA, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_FROM_ALPHA_VALUE_DEFAULT) / CCLayerGradientEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getToRed(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_RED, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_RED_VALUE_DEFAULT) / CCLayerGradientEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getToGreen(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_GREEN, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_GREEN_VALUE_DEFAULT) / CCLayerGradientEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getToBlue(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_BLUE, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_BLUE_VALUE_DEFAULT) / CCLayerGradientEntityLoader.COLOR_COMPONENT_MAX;
	}

	protected float getToAlpha(final Attributes pAttributes) {
		return SAXUtils.getIntAttribute(pAttributes, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_ALPHA, CCLayerGradientEntityLoader.TAG_CCLAYERGRADIENT_ATTRIBUTE_GRADIENT_COLOR_TO_ALPHA_VALUE_DEFAULT) / CCLayerGradientEntityLoader.COLOR_COMPONENT_MAX;
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
