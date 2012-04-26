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

	protected abstract IEntity createEntity(final String pEntityName, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException, CCBLevelLoaderException;

	@Override
	public IEntity onLoadEntity(final String pEntityName, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		final float x = this.getX(pAttributes);
		final float y = this.getY(pAttributes);
		final float width = this.getWidth(pAttributes);
		final float height = this.getHeight(pAttributes);

		final IEntity entity = this.createEntity(pEntityName, x, y, width, height, pAttributes, pCCBEntityLoaderData);

		this.setAttributes(entity, pAttributes);

		return entity;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected abstract float getX(final Attributes pAttributes);
	protected abstract float getY(final Attributes pAttributes);
	protected abstract float getWidth(final Attributes pAttributes);
	protected abstract float getHeight(final Attributes pAttributes);

	protected abstract void setAttributes(final IEntity pEntity, final Attributes pAttributes);

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
