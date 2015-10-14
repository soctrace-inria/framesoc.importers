/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Youenn Corre - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.exporter.pjdump.input;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.ui.input.AbstractToolInputComposite;
import fr.inria.soctrace.tools.framesoc.exporter.dbexporter.TraceTableManager;


public class PajeDumpExporterComposite extends AbstractToolInputComposite {

	private static final int TABLE_MAX_HEIGHT = 350;
	
	private Text textDirectory;
	private TraceTableManager traceTableManager;
	protected PajeDumpExporterInput input = new PajeDumpExporterInput();
	private Table tableTraces;

	public PajeDumpExporterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		// Resize table, in order to limit its maximum size
		addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				Composite composite = (Composite) e.widget;
				tableTraces.setSize(composite.getBounds().width - 15,
						TABLE_MAX_HEIGHT);
			}
		});

		Group grpExportSettings = new Group(parent, SWT.NONE);
		grpExportSettings.setLayout(new GridLayout(1, false));
		grpExportSettings.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpExportSettings.setText("Export Settings");

		// Trace
		Label lblTrace = new Label(grpExportSettings, SWT.NONE);
		lblTrace.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		lblTrace.setText("Traces:");
		
		Composite compositeTrace = new Composite(grpExportSettings, SWT.NONE);
		compositeTrace.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		tableTraces = new Table(compositeTrace, SWT.CHECK| SWT.V_SCROLL | SWT.H_SCROLL);
		tableTraces.setLinesVisible(true);
		tableTraces.setSize(300, TABLE_MAX_HEIGHT);
		tableTraces.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		tableTraces.setLayout(new GridLayout(1, false));

		traceTableManager = new TraceTableManager(tableTraces);
		traceTableManager.loadAll();
		tableTraces.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateExporterInput();
			}
		});
		

		// Directory
		Composite compositeDirectory = new Composite(grpExportSettings, SWT.NONE);
		compositeDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeDirectory.setLayout(new GridLayout(3, false));

		Label lblDirectory = new Label(compositeDirectory, SWT.NONE);
		lblDirectory.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDirectory.setText("Export Directory:");

		textDirectory = new Text(compositeDirectory, SWT.BORDER);
		textDirectory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textDirectory.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				updateExporterInput();
			}
		});

		Button btnBrowse = new Button(compositeDirectory, SWT.NONE);
		btnBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textDirectory.setText("");
				DirectoryDialog dlg = new DirectoryDialog(getShell(), SWT.SINGLE);
				textDirectory.setText(dlg.open());
			}
		});
		btnBrowse.setText("Browse");
		
		input.setTraces(traceTableManager.getSelectedTraces());
		input.setDirectory("");
	}

	@Override
	public IFramesocToolInput getToolInput() {
		return input;
	}

	private void updateExporterInput() {
		// trace
		input.setTraces(traceTableManager.getSelectedTraces());
		// Directory
		input.setDirectory(textDirectory.getText());
		// update ok in argument dialog
		dialog.updateOk();
	}

}
