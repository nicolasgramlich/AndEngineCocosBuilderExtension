package org.andengine.extension.cocosbuilder.loader;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.util.level.EntityLoader;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:38:59 - 19.04.2012
 */
public class CCBEntityLoader extends EntityLoader<CCBEntityLoaderDataSource> {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCB";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCBEntityLoader() {
		super(CCBEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public IEntity onLoadEntity(final String pEntityName, final Attributes pAttributes, final CCBEntityLoaderDataSource pEntityLoaderDataSource) {
		return new Entity();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
