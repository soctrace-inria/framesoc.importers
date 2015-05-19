package fr.inria.soctrace.tools.importer.cachec.input;

import java.util.Arrays;

import org.eclipse.swt.widgets.Composite;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.ui.input.DefaultImporterInputComposite;
import fr.inria.soctrace.framesoc.ui.listeners.LaunchTextListener;

public class CachecInputComposite extends DefaultImporterInputComposite {
	
	public CachecInputComposite(Composite parent, int style) {
		super(parent, style);
	}

	
	@Override
	public IFramesocToolInput getToolInput() {
		CachecInput input = new CachecInput();
		input.setFiles(Arrays.asList(LaunchTextListener.getTokens(traceFileListener.getText())));
		return input;
	}
}
