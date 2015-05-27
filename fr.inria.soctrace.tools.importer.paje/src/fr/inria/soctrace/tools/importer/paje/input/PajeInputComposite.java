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
package fr.inria.soctrace.tools.importer.paje.input;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.ui.dialogs.IArgumentDialog;
import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInput;
import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInputComposite;

/**
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeInputComposite extends PajeDumpInputComposite {

	private boolean noStrictInput;
	private boolean flexInput;
	private boolean userDefinedInput;
	private boolean ignoreLinksInput;

	private boolean startInput;
	private boolean endInput;
	private boolean stopAtInput;

	private double startTimeStamp = 0.0;
	private double endTimeStamp = 0.0;
	private double stopAtTime = 0.0;

	public PajeInputComposite(Composite parent, int style) {
		super(parent, style);

		final Button noStrict = new Button(this, SWT.CHECK);
		noStrict.setSelection(false);
		noStrict.setText("No Strict");
		noStrict.setToolTipText("Support Old Field Names in Event Definitions (-n)");
		noStrict.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				noStrictInput = noStrict.getSelection();
			}
		});

		final Button flex = new Button(this, SWT.CHECK);
		flex.setSelection(false);
		flex.setText("Use Flex-based File Reader");
		flex.setToolTipText("Use flex-based File Reader (-f)");
		flex.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				flexInput = flex.getSelection();
			}
		});

		final Button userDefined = new Button(this, SWT.CHECK);
		userDefined.setSelection(false);
		userDefined.setText("Dump User-defined Fields");
		userDefined.setToolTipText("Dump User-defined Fields (-u)");
		userDefined.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				userDefinedInput = userDefined.getSelection();
			}
		});

		final Button ignoreLinks = new Button(this, SWT.CHECK);
		ignoreLinks.setSelection(false);
		ignoreLinks.setText("Ignore Incomplete Links");
		ignoreLinks
				.setToolTipText("Ignore Incomplete Links (Not Recommended) (-z)");
		ignoreLinks.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ignoreLinksInput = ignoreLinks.getSelection();
			}
		});

		final Button start = new Button(this, SWT.CHECK);
		start.setSelection(false);
		start.setText("Start At");
		start.setToolTipText("Dump Starts at Timestamp START (Instead of 0) (-s)");

		final Text txtStart = new Text(this, SWT.BORDER);
		txtStart.setText(String.valueOf(startTimeStamp));
		txtStart.setEnabled(false);
		txtStart.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtStart.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					startTimeStamp = Double.parseDouble(txtStart.getText());
				} catch (NumberFormatException ex) {
					// TODO tell Framesoc that the format is not valid and
					// the input is not OK
				}
			}
		});

		start.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				startInput = start.getSelection();
				txtStart.setEnabled(start.getSelection());
			}
		});

		final Button end = new Button(this, SWT.CHECK);
		end.setSelection(false);
		end.setText("End At");
		end.setToolTipText("Dump Ends at Timestamp END (Instead of EOF) (-e)");

		
		final Text txtEnd = new Text(this, SWT.BORDER);
		txtEnd.setText(String.valueOf(endTimeStamp));
		txtEnd.setEnabled(false);
		txtEnd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		txtEnd.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					endTimeStamp = Double.parseDouble(txtEnd.getText());
				} catch (NumberFormatException ex) {
					// TODO tell Framesoc that the format is not valid and
					// the input is not OK
				}
			}
		});
		
		end.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				endInput = end.getSelection();
				txtEnd.setEnabled(end.getSelection());
			}
		});

		final Button stop = new Button(this, SWT.CHECK);
		stop.setSelection(false);
		stop.setText("Stop At");
		stop.setToolTipText("Stop the Trace Simulation at TIME (-a)");
		
		final Text txtStop = new Text(this, SWT.BORDER);
		txtStop.setText(String.valueOf(stopAtTime));
		txtStop.setEnabled(false);
		txtStop.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtStop.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				try {
					stopAtTime = Double.parseDouble(txtStop.getText());
				} catch (NumberFormatException ex) {
					// TODO tell Framesoc that the format is not valid and
					// the input is not OK
				}
			}
		});
		
		stop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stopAtInput = stop.getSelection();
				txtStop.setEnabled(stop.getSelection());
			}
		});

	}

	@Override
	public void setArgumentDialog(IArgumentDialog dialog) {
		super.setArgumentDialog(dialog);
	}

	@Override
	public IFramesocToolInput getToolInput() {
		PajeDumpInput superInput = (PajeDumpInput) super.getToolInput();
		PajeInput input = new PajeInput();
		
		input.setDoublePrecision(superInput.isDoublePrecision());
		input.setFiles(superInput.getFiles());
		input.setPrecision(superInput.getPrecision());
		input.setTimeUnit(superInput.getTimeUnit());
		input.setArgumentLine(getArgumentsAsString());
		return input;
	}

	/**
	 * Transform the arguments provided through the GUI to a String to be given
	 * to the pj_dump executable
	 * 
	 * @return the String with the corresponding arguments
	 */
	protected String getArgumentsAsString() {
		String args = "";

		if (noStrictInput)
			args = args + "n";

		if (flexInput)
			args = args + "f";

		if (userDefinedInput)
			args = args + "u";

		if (ignoreLinksInput)
			args = args + "z";

		if (!args.isEmpty())
			args = "-" + args + " ";
		
		if(startInput)
			args = args + "-s " + startTimeStamp + " ";
		
		if(endInput)
			args = args + "-e " + endTimeStamp + " ";
		
		if(stopAtInput)
			args = args + "-a " + stopAtTime + " ";

		if (args.isEmpty())
			args = null;
		
		return args;
	}

}
