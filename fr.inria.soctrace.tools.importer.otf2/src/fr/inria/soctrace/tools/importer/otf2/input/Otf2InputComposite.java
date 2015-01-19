package fr.inria.soctrace.tools.importer.otf2.input;

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
public class Otf2InputComposite extends DefaultImporterInputComposite {

	private Otf2Input input = new Otf2Input();

	public Otf2InputComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		
		final Button btnImportVariables = new Button(this, SWT.CHECK);
		btnImportVariables.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				input.setImportVariable(btnImportVariables.getSelection());
			}
		});
		btnImportVariables.setText("Import Variables");
		btnImportVariables.setSelection(input.isImportVariable());
		
		final Button btnParseHierarchy = new Button(this, SWT.CHECK);
		btnParseHierarchy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				input.setParseHierarchy(btnParseHierarchy.getSelection());
			}
		});
		btnParseHierarchy.setText("Parse Hierarchy");
		btnParseHierarchy.setSelection(input.isParseHierarchy());

		// customize for single trace file
		traceFiles.setFileLabel("Trace file");
		traceFiles.setFileDialogStyle(SWT.SINGLE);

	}
	
	@Override
	public IFramesocToolInput getToolInput() {
		String[] tokens = LaunchTextListener.getTokens(traceFileListener.getText());
		if (tokens.length > 0) {
			input.setTraceFile(tokens[0]);
		} else {
			input.setTraceFile("");
		}
		return input;
	}

}
