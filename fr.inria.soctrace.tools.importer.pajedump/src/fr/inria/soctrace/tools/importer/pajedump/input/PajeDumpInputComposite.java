package fr.inria.soctrace.tools.importer.pajedump.input;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.ui.input.DefaultImporterInputComposite;
import fr.inria.soctrace.framesoc.ui.listeners.LaunchTextListener;

/**
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeDumpInputComposite extends DefaultImporterInputComposite {

	protected boolean doublePrecision = true;

	public PajeDumpInputComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		final Button btnDoublePrecision = new Button(this, SWT.CHECK);
		btnDoublePrecision.setText("Double precision");
		btnDoublePrecision.setSelection(doublePrecision);
		btnDoublePrecision.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doublePrecision = btnDoublePrecision.getSelection();
			}
		});
	}
	
	@Override
	public IFramesocToolInput getToolInput() {
		PajeDumpInput input = new PajeDumpInput();
		input.setFiles(Arrays.asList(LaunchTextListener.getTokens(traceFileListener.getText())));
		input.setDoublePrecision(doublePrecision);
		return input;
	}

}
