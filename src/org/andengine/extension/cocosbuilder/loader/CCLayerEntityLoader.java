package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCLayer;
import org.xml.sax.Attributes;


/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:24:38 - 18.04.2012
 */
public class CCLayerEntityLoader extends CCNodeEntityLoader {
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
	protected IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException {
		return new CCLayer(pX, pY, pWidth, pHeight);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
