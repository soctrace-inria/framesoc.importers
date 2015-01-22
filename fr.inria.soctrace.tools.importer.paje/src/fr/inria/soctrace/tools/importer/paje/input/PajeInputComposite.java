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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.ui.input.CommandLineArgsInputComposite;
import fr.inria.soctrace.framesoc.ui.listeners.TextListener;
import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInput;
import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInputComposite;

/**
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeInputComposite extends PajeDumpInputComposite {

	private final static String PJDUMP_DOC = ""
			+ "-a, --stop-at=TIME         Stop the trace simulation at TIME\n"
			+ "-e, --end=END              Dump ends at timestamp END (instead of EOF)\n"
			+ "-f, --flex                 Use flex-based file reader\n"
			+ "-n, --no-strict            Support old field names in event definitions\n"
			+ "-q, --quiet                Do not dump, only simulate\n"
			+ "-s, --start=START          Dump starts at timestamp START (instead of 0)\n"
			+ "-u, --user-defined         Dump user-defined fields\n"
			+ "-z, --ignore-incomplete-links   Ignore incomplete links (not recommended)\n";

	private TextListener argumentListener;

	public PajeInputComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		argumentListener = new TextListener("");
		CommandLineArgsInputComposite argComposite = new CommandLineArgsInputComposite(
				parent, SWT.NONE, true);
		argComposite.addArgsModifyListener(argumentListener);
		argComposite.setDocText(PJDUMP_DOC);
	}

	@Override
	public IFramesocToolInput getToolInput() {
		PajeDumpInput superInput = (PajeDumpInput) super.getToolInput();
		PajeInput input = new PajeInput();
		input.setDoublePrecision(superInput.isDoublePrecision());
		input.setFiles(superInput.getFiles());
		input.setArguments(argumentListener.getText());
		return input;
	}

}
