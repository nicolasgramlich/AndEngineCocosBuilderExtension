package org.andengine.extension.cocosbuilder.loader;

import org.andengine.entity.IEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.entity.CCBEntity;
import org.andengine.util.SAXUtils;
import org.andengine.util.level.EntityLoader;
import org.xml.sax.Attributes;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 14:38:59 - 19.04.2012
 */
public class CCBEntityLoader extends EntityLoader<CCBEntityLoaderData> {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCB";

	private static final String TAG_CCB_ATTRIBUTE_VERSION = "version";
	private static final String TAG_CCB_ATTRIBUTE_STAGE_WIDTH = "stageWidth";
	private static final String TAG_CCB_ATTRIBUTE_STAGE_HEIGHT = "stageHeight";
	private static final String TAG_CCB_ATTRIBUTE_ORIGIN_CENTERED = "centeredOrigin";

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
	public IEntity onLoadEntity(final String pEntityName, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pEntityLoaderData) {
		final int version = SAXUtils.getIntAttributeOrThrow(pAttributes, CCBEntityLoader.TAG_CCB_ATTRIBUTE_VERSION);
		final int stageWidth = SAXUtils.getIntAttributeOrThrow(pAttributes, CCBEntityLoader.TAG_CCB_ATTRIBUTE_STAGE_WIDTH);
		final int stageHeight = SAXUtils.getIntAttributeOrThrow(pAttributes, CCBEntityLoader.TAG_CCB_ATTRIBUTE_STAGE_HEIGHT);
		final boolean centeredOrigin = SAXUtils.getBooleanAttributeOrThrow(pAttributes, CCBEntityLoader.TAG_CCB_ATTRIBUTE_ORIGIN_CENTERED);

		return new CCBEntity(version, stageWidth, stageHeight, centeredOrigin);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
