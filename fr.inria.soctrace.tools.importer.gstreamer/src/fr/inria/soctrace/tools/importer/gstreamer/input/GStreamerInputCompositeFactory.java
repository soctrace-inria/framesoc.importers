package fr.inria.soctrace.tools.importer.gstreamer.input;

import org.eclipse.swt.widgets.Composite;

import fr.inria.soctrace.framesoc.ui.input.AbstractToolInputComposite;
import fr.inria.soctrace.framesoc.ui.input.AbstractToolInputCompositeFactory;

/**
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class GStreamerInputCompositeFactory extends AbstractToolInputCompositeFactory {

	@Override
	public AbstractToolInputComposite getComposite(Composite parent, int style) {
		return new GStreamerInputComposite(parent, style);
	}

}
