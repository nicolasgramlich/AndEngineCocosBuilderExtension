package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.exception.CCBLevelLoaderException;
import org.andengine.util.level.EntityLoader;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 13:49:02 - 24.04.2012
 */
public abstract class CCEntityLoader extends EntityLoader<CCBEntityLoaderData> {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	protected CCEntityLoader(final String ... pEntityNames) {
		super(pEntityNames);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	protected abstract IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException, CCBLevelLoaderException;

	@Override
	public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final float x = this.getX(pParent, pAttributes, pCCBEntityLoaderData);
		final float y = this.getY(pParent, pAttributes, pCCBEntityLoaderData);
		final float width = this.getWidth(pParent, pAttributes, pCCBEntityLoaderData);
		final float height = this.getHeight(pParent, pAttributes, pCCBEntityLoaderData);

		final IEntity entity = this.createEntity(pEntityName, pParent, x, y, width, height, pAttributes, pCCBEntityLoaderData);

		this.setAttributes(entity, pParent, pAttributes, pCCBEntityLoaderData);

		return entity;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected abstract float getX(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData);
	protected abstract float getY(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData);
	protected abstract float getWidth(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData);
	protected abstract float getHeight(final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData);

	protected abstract void setAttributes(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
