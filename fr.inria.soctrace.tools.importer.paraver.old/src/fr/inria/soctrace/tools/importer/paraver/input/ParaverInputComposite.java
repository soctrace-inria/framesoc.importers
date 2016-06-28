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
package fr.inria.soctrace.tools.importer.paraver.input;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.ui.input.DefaultImporterInputComposite;
import fr.inria.soctrace.framesoc.ui.listeners.LaunchTextListener;

/**
 * 
 * @author "Youenn Corre <youenn.corre@inria.fr>"
 */
public class ParaverInputComposite extends DefaultImporterInputComposite {

	private boolean useEventForState = false;

	public ParaverInputComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		final Button eventState = new Button(this,SWT.CHECK);
		eventState.setText("Use events to build states");
		eventState.setSelection(false);
		eventState.setToolTipText("Use the paraver events to decide the type of the pjdump states.");
		eventState.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				useEventForState = eventState.getSelection();
			}
		});
		
		new Label(this, SWT.NONE);
	}

	@Override
	public IFramesocToolInput getToolInput() {
		ParaverInput input = new ParaverInput();
		input.setFiles(Arrays.asList(LaunchTextListener.getTokens(traceFileListener.getText())));
		input.setUseEventForState(useEventForState);
		return input;
	}

}
