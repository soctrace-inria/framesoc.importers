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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

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
	protected int precision = 0;
	private Text txtPrecision;
	private Label lblPrecision;

	public PajeDumpInputComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		final Button btnDoublePrecision = new Button(this, SWT.RADIO);
		btnDoublePrecision.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doublePrecision = btnDoublePrecision.getSelection();
				txtPrecision.setEnabled(doublePrecision);
				lblPrecision.setEnabled(doublePrecision);
			}
		});
		btnDoublePrecision.setText("Double timestamps");
		btnDoublePrecision
				.setToolTipText("The trace timestamp will be multiplied by 10 to the power of Timestamp Shift.");
		btnDoublePrecision.setSelection(doublePrecision);

		final Button btnLongPrecision = new Button(this, SWT.RADIO);
		btnLongPrecision.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doublePrecision = !btnLongPrecision.getSelection();
			}
		});
		btnLongPrecision.setText("Long timestamps");
		btnLongPrecision
				.setToolTipText("The trace timestamp will be parsed as a long value and not modified.");
		btnLongPrecision.setSelection(!doublePrecision);

		lblPrecision = new Label(this, SWT.NONE);
		lblPrecision.setToolTipText("Number of decimal positions the original number will be shifted by.");
		lblPrecision.setText("Timestamp Shift");

		txtPrecision = new Text(this, SWT.BORDER);
		txtPrecision.setText(String.valueOf(precision));
		txtPrecision.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtPrecision.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					precision = Integer.parseInt(txtPrecision.getText());
				} catch (NumberFormatException ex) {
					// TODO tell Framesoc that the format is not valid and 
					// the input is not OK
				}
			}
		});

		final Label lblTimeUnit = new Label(this, SWT.NONE);
		lblTimeUnit.setText("Time Unit");
		lblTimeUnit.setToolTipText("Time unit to be used. If double precision is checked, it is the time unit after the timestamp is shifted.");

		final Combo comboTimeUnit = new Combo(this, SWT.READ_ONLY);
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
		input.setPrecision(precision);
		return input;
	}

}
