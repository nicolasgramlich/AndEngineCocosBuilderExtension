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
 * @since 18:24:38 - 18.04.2012
 */
public class CCLayerEntityLoader extends EntityLoader<CCBEntityLoaderDataSource> {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCLayer";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCLayerEntityLoader() {
		super(CCLayerEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public IEntity onLoadEntity(final String pEntityName, final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) {
		return new Entity();
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
