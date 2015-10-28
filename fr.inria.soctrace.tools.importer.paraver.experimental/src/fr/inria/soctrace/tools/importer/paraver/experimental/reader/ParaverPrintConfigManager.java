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
package fr.inria.soctrace.tools.importer.paraver.experimental.reader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

import fr.inria.soctrace.framesoc.core.tools.management.ExternalProgramConfigManager;
import fr.inria.soctrace.tools.importer.paraver.experimental.Activator;

/**
 * Configuration manager for Paraver Print Wrapper.
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
/* package */class ParaverPrintConfigManager extends ExternalProgramConfigManager {

	/**
	 * Configuration directory
	 */
	private final static String CONF_DIR = "configuration" + File.separator + Activator.PLUGIN_ID
			+ File.separator;

	/**
	 * Configuration file
	 */
	private final static String CONF_FILE = CONF_DIR + "paraver.path";

	/**
	 * Default prv2pjdump executable location
	 */
	private static final String DEFAULT_PATH = "perl" + File.separator + "prv2pjdump.pl";

	@Override
	protected String getConfDir() {
		String eclipseDir = Platform.getInstallLocation().getURL().getPath();
		return eclipseDir + CONF_DIR;
	}

	@Override
	protected String getConfFilePath() {
		String eclipseDir = Platform.getInstallLocation().getURL().getPath();
		return eclipseDir + CONF_FILE;
	}

	@Override
	protected String getDefaultExePath() {
		Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		Path path = new Path(DEFAULT_PATH);
		URL fileURL = FileLocator.find(bundle, path, null);
		try {
			return FileLocator.resolve(fileURL).getPath().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
