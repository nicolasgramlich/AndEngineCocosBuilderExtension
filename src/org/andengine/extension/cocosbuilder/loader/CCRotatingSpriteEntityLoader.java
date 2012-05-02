package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCRotatingSprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 16:36:17 - 01.05.2012
 */
public class CCRotatingSpriteEntityLoader extends CCSpriteEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCRotatingSprite";

	private static final String TAG_CCROTATINGSPRITE_ATTRIBUTE_SECONDS_PER_ROTATION = "secondsPerRotation";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCRotatingSpriteEntityLoader() {
		super(CCRotatingSpriteEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final ITextureRegion textureRegion = this.getTextureRegion(pParent, pAttributes, pCCBEntityLoaderData);

		return new CCRotatingSprite(pX, pY, pWidth, pHeight, textureRegion, pCCBEntityLoaderData.getVertexBufferObjectManager());
	}

	@Override
	protected void setAttributes(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		super.setAttributes(pEntity, pParent, pAttributes, pCCBEntityLoaderData);

		this.setCCRotatingSpriteAttributes((CCRotatingSprite)pEntity, pParent, pAttributes, pCCBEntityLoaderData);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void setCCRotatingSpriteAttributes(final CCRotatingSprite pCCRotatingSprite, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		this.setCCRotatingSpriteSecondsPerRotation(pCCRotatingSprite, pParent, pAttributes, pCCBEntityLoaderData);
	}

	private void setCCRotatingSpriteSecondsPerRotation(final CCRotatingSprite pCCRotatingSprite, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		final float secondsPerRotation = SAXUtils.getFloatAttributeOrThrow(pAttributes, CCRotatingSpriteEntityLoader.TAG_CCROTATINGSPRITE_ATTRIBUTE_SECONDS_PER_ROTATION);

		pCCRotatingSprite.setSecondsPerRotation(secondsPerRotation);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
