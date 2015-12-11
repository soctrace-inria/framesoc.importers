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

import java.util.List;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;

/**
 * @author "Youenn Corre <youenn.corre@inria.fr>"
 */
public class ParaverInput implements IFramesocToolInput {

	protected List<String> files;
	protected boolean useEventForState = false;
	
	@Override
	public String getCommand() {
		return "";
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}
	
	public boolean isUseEventForState() {
		return useEventForState;
	}

	public void setUseEventForState(boolean useEventForState) {
		this.useEventForState = useEventForState;
	}


	@Override
	public String toString() {
		return "Paraver [files=" + files + ", useEventForState="
				+ useEventForState + "]";
	}
	
}
