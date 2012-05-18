package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.clip.ClipEntity;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderData;
import org.andengine.extension.cocosbuilder.CCBLevelLoader;
import org.andengine.extension.cocosbuilder.CCBLevelLoaderResult;
import org.andengine.extension.cocosbuilder.entity.CCScrollView;
import org.andengine.extension.cocosbuilder.exception.CCBLevelLoaderException;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.SAXUtils;
import org.xml.sax.Attributes;

import android.content.res.AssetManager;

/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 15:32:34 - 18.05.2012
 */
public class CCScrollViewEntityLoader extends CCNodeEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCScrollView";

	private static final String TAG_CCSCROLLVIEW_ATTRIBUTE_SCALE = "scale";
	private static final float TAG_CCSCROLLVIEW_ATTRIBUTE_SCALE_VALUE_DEFAULT = 1;

	private static final String TAG_CCSCROLLVIEW_ATTRIBUTE_CCBFILENAME = "ccbFileName";
	private static final String TAG_CCSCROLLVIEW_ATTRIBUTE_DIRECTION = "direction";
	private static final String TAG_CCSCROLLVIEW_ATTRIBUTE_DIRECTION_VALUE_HORIZONTAL = "horizontal";
	private static final String TAG_CCSCROLLVIEW_ATTRIBUTE_DIRECTION_VALUE_VERTICAL = "vertical";
	private static final String TAG_CCSCROLLVIEW_ATTRIBUTE_DIRECTION_VALUE_BOTH = "both";
	private static final String TAG_CCSCROLLVIEW_ATTRIBUTE_CLIP_TO_BOUNDS = "clipToBounds";
	private static final boolean TAG_CCSCROLLVIEW_ATTRIBUTE_CLIP_TO_BOUNDS_VALUE_DEFAULT = true;
	private static final String TAG_CCSCROLLVIEW_ATTRIBUTE_BOUNCES = "bounces";

	private static final boolean TAG_CCSCROLLVIEW_ATTRIBUTE_BOUNCES_VALUE_DEFAULT = false;


	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCScrollViewEntityLoader() {
		super(CCScrollViewEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	protected IEntity createEntity(final String pEntityName, final IEntity pParent, final float pX, final float pY, final float pWidth, final float pHeight, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) throws IOException, CCBLevelLoaderException {
		final String ccbFileName = SAXUtils.getAttributeOrThrow(pAttributes, CCScrollViewEntityLoader.TAG_CCSCROLLVIEW_ATTRIBUTE_CCBFILENAME);

		final AssetManager assetManager = pCCBEntityLoaderData.getAssetManager();
		final String assetBasePath = pCCBEntityLoaderData.getAssetBasePath();
		final VertexBufferObjectManager vertexBufferObjectManager = pCCBEntityLoaderData.getVertexBufferObjectManager();
		final TextureManager textureManager = pCCBEntityLoaderData.getTextureManager();
		final FontManager fontManager = pCCBEntityLoaderData.getFontManager();

		final CCBLevelLoader ccbLevelLoader = new CCBLevelLoader(assetManager, assetBasePath, vertexBufferObjectManager, textureManager, fontManager);
		final String ccbFilePath = assetBasePath + ccbFileName;
		final CCBLevelLoaderResult ccbLevelLoaderResult = ccbLevelLoader.loadLevelFromAsset(assetManager, ccbFilePath, pCCBEntityLoaderData);
		final IEntity rootEntity = ccbLevelLoaderResult.getRootEntity();

		final CCScrollView ccScrollView = new CCScrollView(pX, pY, pWidth, pHeight);

		ccScrollView.attachChild(rootEntity);

		return ccScrollView;
	}

	@Override
	protected void setAttributes(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		super.setAttributes(pEntity, pParent, pAttributes, pCCBEntityLoaderData);

		this.setCCScrollViewAttributes((CCScrollView)pEntity, pParent, pAttributes, pCCBEntityLoaderData);
	}

	@Override
	protected void setCCNodeScale(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pEntity.setScale(this.getScale(pEntity, pParent, pAttributes, pCCBEntityLoaderData));
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected void setCCScrollViewAttributes(final ClipEntity pClipEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		this.setCCScrollViewBounces(pClipEntity, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCScrollViewClipToBounds(pClipEntity, pParent, pAttributes, pCCBEntityLoaderData);
		this.setCCScrollViewDirection(pClipEntity, pParent, pAttributes, pCCBEntityLoaderData);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	protected float getScale(final IEntity pEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getFloatAttribute(pAttributes, CCScrollViewEntityLoader.TAG_CCSCROLLVIEW_ATTRIBUTE_SCALE, CCScrollViewEntityLoader.TAG_CCSCROLLVIEW_ATTRIBUTE_SCALE_VALUE_DEFAULT);
	}

	protected void setCCScrollViewBounces(final ClipEntity pClipEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		final boolean bounces = this.isBounces(pClipEntity, pParent, pAttributes, pCCBEntityLoaderData);
		// TODO set bouncing...
	}

	protected void setCCScrollViewClipToBounds(final ClipEntity pClipEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		pClipEntity.setClippingEnabled(this.isClipToBounds(pClipEntity, pParent, pAttributes, pCCBEntityLoaderData));
	}

	protected void setCCScrollViewDirection(final ClipEntity pClipEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		// TODO set direction...
	}


	private boolean isBounces(final ClipEntity pClipEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCScrollViewEntityLoader.TAG_CCSCROLLVIEW_ATTRIBUTE_BOUNCES, CCScrollViewEntityLoader.TAG_CCSCROLLVIEW_ATTRIBUTE_BOUNCES_VALUE_DEFAULT);
	}

	protected boolean isClipToBounds(final ClipEntity pClipEntity, final IEntity pParent, final Attributes pAttributes, final CCBEntityLoaderData pCCBEntityLoaderData) {
		return SAXUtils.getBooleanAttribute(pAttributes, CCScrollViewEntityLoader.TAG_CCSCROLLVIEW_ATTRIBUTE_CLIP_TO_BOUNDS, CCScrollViewEntityLoader.TAG_CCSCROLLVIEW_ATTRIBUTE_CLIP_TO_BOUNDS_VALUE_DEFAULT);
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
