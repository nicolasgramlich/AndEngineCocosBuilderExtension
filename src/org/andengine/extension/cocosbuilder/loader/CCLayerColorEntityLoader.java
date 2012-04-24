package org.andengine.extension.cocosbuilder.loader;

import java.io.IOException;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.extension.cocosbuilder.CCBEntityLoaderDataSource;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.xml.sax.Attributes;


/**
 * (c) Zynga 2012
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:53:12 - 23.04.2012
 */
public class CCLayerColorEntityLoader extends CCNodeEntityLoader {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final String ENTITY_NAMES = "CCLayerColor";

	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	public CCLayerColorEntityLoader() {
		super(CCLayerColorEntityLoader.ENTITY_NAMES);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public Rectangle onLoadEntity(final String pEntityName, final Attributes pAttributes, final CCBEntityLoaderDataSource pCCBEntityLoaderDataSource) throws IOException {
		final float x = this.getX(pAttributes);
		final float y = this.getY(pAttributes);
		final float width = this.getWidth(pAttributes);
		final float height = this.getHeight(pAttributes);

		final VertexBufferObjectManager vertexBufferObjectManager = pCCBEntityLoaderDataSource.getVertexBufferObjectManager();
		final Rectangle rectangle = new Rectangle(x, y, width, height, vertexBufferObjectManager);

		this.setAttributes(rectangle, pAttributes);

		return rectangle;
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
