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
import fr.inria.soctrace.framesoc.ui.dialogs.IArgumentDialog;
import fr.inria.soctrace.framesoc.ui.input.CommandLineArgsInputComposite;
import fr.inria.soctrace.framesoc.ui.listeners.LaunchTextListener;
import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInput;
import fr.inria.soctrace.tools.importer.pajedump.input.PajeDumpInputComposite;

/**
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class PajeInputComposite extends PajeDumpInputComposite {

	private LaunchTextListener argumentListener;
	private CommandLineArgsInputComposite argComposite;

	public PajeInputComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		argComposite = new CommandLineArgsInputComposite(
				parent, SWT.NONE, true);
		argComposite.setDocText(PajeInput.getDoc());
	}
	
	@Override
	public void setArgumentDialog(IArgumentDialog dialog) {
		super.setArgumentDialog(dialog);
		argumentListener = new LaunchTextListener("", dialog);
		argComposite.addArgsModifyListener(argumentListener);
	}

	@Override
	public IFramesocToolInput getToolInput() {
		PajeDumpInput superInput = (PajeDumpInput) super.getToolInput();
		PajeInput input = new PajeInput();
		input.setDoublePrecision(superInput.isDoublePrecision());
		input.setFiles(superInput.getFiles());
		input.setArgumentLine(argumentListener.getText());
		return input;
	}

}
