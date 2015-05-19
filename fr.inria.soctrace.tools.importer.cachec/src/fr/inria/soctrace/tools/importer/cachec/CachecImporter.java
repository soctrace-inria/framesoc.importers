/*******************************************************************************
 * Copyright (c) 2012-2014 Generoso Pagano, David Beniamine.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Generoso Pagano - initial API and implementation
 *     David Beniamine - Adaptation from PjDump to HeapInfo
 ******************************************************************************/
package fr.inria.soctrace.tools.importer.cachec;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.inria.soctrace.framesoc.core.FramesocManager;
import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.core.tools.model.FramesocTool;
import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.core.tools.model.IPluginToolJobBody;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.Configuration;
import fr.inria.soctrace.lib.utils.Configuration.SoCTraceProperty;
import fr.inria.soctrace.lib.utils.DeltaManager;
import fr.inria.soctrace.tools.importer.cachec.core.CachecParser;
import fr.inria.soctrace.tools.importer.cachec.input.CachecInput;

/**
 * cachec importer tool.
 * 
 * @author "David Beniamine <David.Beniamine@imag.fr>"
 * @author "Generoso Pagano <Generoso.Pagano@inria.fr>"
 */
public class CachecImporter extends FramesocTool {

	private final static Logger logger = LoggerFactory
			.getLogger(CachecImporter.class);

	/**
	 * Plugin Tool Job body: we use a Job since we have to perform a long
	 * operation and we don't want to freeze the UI.
	 */
	public class cachecImporterPluginJobBody implements IPluginToolJobBody {

		//private cachecInput input;
		private CachecInput input;
		public cachecImporterPluginJobBody(IFramesocToolInput input) {
			this.input = (CachecInput) input;
		}

		@Override
		public void run(IProgressMonitor monitor) {
			DeltaManager delta = new DeltaManager();

			logger.debug("Args: ");
			List<String> files = input.getFiles();

			for (String s : files) {
				logger.debug(s);
			}
			
			String pattern = Pattern
					.quote(System.getProperty("file.separator"));

			delta.start();

			String sysDbName = Configuration.getInstance().get(
					SoCTraceProperty.soctrace_db_name);

			String t[] = files.get(0).split(pattern);
			String traceName;
			
			// Set trace name as the directory name
			if (t.length - 2 >= 0)
				traceName = t[t.length - 2];
			else
				traceName = "cachec";
				
			String traceDbName = FramesocManager.getInstance().getTraceDBName(
					traceName);

			SystemDBObject sysDB = null;
			TraceDBObject traceDB = null;

			try {
				// open system DB
				sysDB = new SystemDBObject(sysDbName, DBMode.DB_OPEN);
				// create new trace DB
				traceDB = new TraceDBObject(traceDbName, DBMode.DB_CREATE);

				
				// parsing
				CachecParser parser = new CachecParser(sysDB, traceDB, files);
				parser.parseTrace(monitor);

				// close the traces and system DB (commit)
				traceDB.close();
				sysDB.close();

			} catch (SoCTraceException ex) {
				logger.error(ex.getMessage());
				ex.printStackTrace();
				logger.error("Import failure. Trying to rollback modifications in DB.");
				if (sysDB != null)
					try {
						sysDB.rollback();
					} catch (SoCTraceException e) {
						e.printStackTrace();
					}
					if (traceDB != null)
						try {
							traceDB.dropDatabase();
						} catch (SoCTraceException e) {
							e.printStackTrace();
						}
				
			} finally {
				delta.end("Import trace");
			}
		}
	}

	@Override
	public void launch(IFramesocToolInput input) {
		PluginImporterJob job = new PluginImporterJob("cachec Importer",
				new cachecImporterPluginJobBody(input));
		job.setUser(true);
		job.schedule();
	}

	@Override
	public ParameterCheckStatus canLaunch(IFramesocToolInput input) {
		CachecInput args = (CachecInput) input;
		
		if (args.getFiles().size() < 1)
			return new ParameterCheckStatus(false, "Not enough arguments.");

		for (String file : args.getFiles()) {
			File f = new File(file);
			if (!f.isFile())
				return new ParameterCheckStatus(false, f.getName()
						+ " does not exist.");
		}

		return new ParameterCheckStatus(true, "");
	}
	
}
