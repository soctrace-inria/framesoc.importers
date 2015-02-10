/*******************************************************************************
 * Copyright (c) 2012-2015 INRIA.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.pajedump.input;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.ui.input.DefaultImporterInputComposite;
import fr.inria.soctrace.framesoc.ui.listeners.LaunchTextListener;
import fr.inria.soctrace.lib.model.utils.ModelConstants.TimeUnit;

/**
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeDumpInputComposite extends DefaultImporterInputComposite {

	protected boolean doublePrecision = true;
	protected int timeUnit = TimeUnit.UNKNOWN.getInt();

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
		
		final Label lblTimeUnit = new Label(this, SWT.NONE);
		lblTimeUnit.setText("Time Unit Selection:");
		
		final Combo comboTimeUnit = new Combo(this, SWT.READ_ONLY);
		comboTimeUnit.setToolTipText("Time Unit of the Trace");
		comboTimeUnit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				timeUnit = TimeUnit.getInt(comboTimeUnit.getText());
			}
		});
		for (final TimeUnit aTimeUnit : TimeUnit.values()) {
			comboTimeUnit.add(aTimeUnit.getLabel());
		}
		comboTimeUnit.setText(TimeUnit.UNKNOWN.getLabel());
	}
	
	@Override
	public IFramesocToolInput getToolInput() {
		PajeDumpInput input = new PajeDumpInput();
		input.setFiles(Arrays.asList(LaunchTextListener.getTokens(traceFileListener.getText())));
		input.setDoublePrecision(doublePrecision);
		input.setTimeUnit(timeUnit);
		return input;
	}

}
