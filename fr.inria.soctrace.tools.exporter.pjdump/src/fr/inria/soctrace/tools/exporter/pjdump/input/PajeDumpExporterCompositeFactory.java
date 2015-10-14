package fr.inria.soctrace.tools.exporter.pjdump.input;

import org.eclipse.swt.widgets.Composite;

import fr.inria.soctrace.framesoc.ui.input.AbstractToolInputComposite;
import fr.inria.soctrace.framesoc.ui.input.AbstractToolInputCompositeFactory;

public class PajeDumpExporterCompositeFactory extends
		AbstractToolInputCompositeFactory {

	@Override
	public AbstractToolInputComposite getComposite(Composite parent, int style) {
		return new PajeDumpExporterComposite(parent, style);
	}

}
