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
package fr.inria.soctrace.tools.importer.gstreamer.input;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.ui.dialogs.IArgumentDialog;
import fr.inria.soctrace.framesoc.ui.input.DefaultImporterInputComposite;
import fr.inria.soctrace.framesoc.ui.listeners.LaunchTextListener;
import fr.inria.soctrace.tools.importer.gstreamer.core.GStreamerConstants;

/**
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class GStreamerInputComposite extends DefaultImporterInputComposite {

	protected LaunchTextListener typeListener;
	protected boolean framesOverlapping = GStreamerConstants.DEFAULT_FRAME_OVELAPPING;
	protected boolean typeSelected = false;
	protected Text typeText;
	private Button btnType;

	public GStreamerInputComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));

		final Button btnFramesOverlapping = new Button(this, SWT.CHECK);
		btnFramesOverlapping.setText("Allow overlapping frames");
		btnFramesOverlapping.setSelection(framesOverlapping);

		Composite composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		btnType = new Button(composite, SWT.CHECK);
		btnType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				typeText.setEnabled(btnType.getSelection());
				typeSelected = btnType.getSelection();
			}
		});
		btnType.setText("Frame start type");
		btnType.setSelection(false);

		typeText = new Text(composite, SWT.BORDER);
		typeText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnFramesOverlapping.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				framesOverlapping = btnFramesOverlapping.getSelection();
			}
		});
		typeText.setText(GStreamerConstants.DEFAULT_FRAME_START);
		typeText.setEnabled(framesOverlapping);

	}

	@Override
	public void setArgumentDialog(IArgumentDialog dialog) {
		super.setArgumentDialog(dialog);
		typeListener = new LaunchTextListener(typeText.getText(), dialog);
		typeText.addModifyListener(typeListener);
	}

	@Override
	public IFramesocToolInput getToolInput() {
		GStreamerInput input = new GStreamerInput();
		
		// file name
		String tokens[] = LaunchTextListener.getTokens(traceFileListener.getText());
		if (tokens.length > 0) {
			input.fileName = tokens[0];
		} else {
			input.fileName = "";
		}
		
		// frames overlapping
		input.framesOverlapping = framesOverlapping;
		
		// frame start type
		if (typeSelected) {
			input.frameStartType = typeListener.getText();
		} else {
			input.frameStartType = GStreamerConstants.DEFAULT_FRAME_START;
		}
		
		return input;
	}

}
