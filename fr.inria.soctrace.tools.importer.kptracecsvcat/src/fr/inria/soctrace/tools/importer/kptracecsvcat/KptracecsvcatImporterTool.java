package fr.inria.soctrace.tools.importer.kptracecsvcat;

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.inria.soctrace.framesoc.core.FramesocManager;
import fr.inria.soctrace.framesoc.core.tools.management.PluginImporterJob;
import fr.inria.soctrace.framesoc.core.tools.model.FileInput;
import fr.inria.soctrace.framesoc.core.tools.model.FramesocTool;
import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.framesoc.core.tools.model.IPluginToolJobBody;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
import fr.inria.soctrace.lib.utils.Configuration;
import fr.inria.soctrace.lib.utils.DeltaManager;
import fr.inria.soctrace.lib.utils.Configuration.SoCTraceProperty;
import fr.inria.soctrace.tools.importer.kptracecsvcat.core.KptracecsvcatManager;
import fr.inria.soctrace.tools.importer.kptracecsvcat.core.KptracecsvcatTraceSizeMetadata;

/**
 * KPTraceCSV Parser Tool
 *
 * @author "Alexis Martin <alexis.martin@inria.fr>"
 *
 */
public class KptracecsvcatImporterTool extends FramesocTool {

	/**
	 * Plugin Tool Job body: we use a Job since we have to
	 * perform a long operation and we don't want to freeze the UI.
	 */
	private class KptracecsvcatImporterPluginJobBody implements IPluginToolJobBody {
		
		private String filename;
		
		public KptracecsvcatImporterPluginJobBody(FileInput file) {
			this.filename = file.getFiles().get(0);
		}

		@Override
		public void run(IProgressMonitor monitor) throws SoCTraceException  {
			
			System.out.println(filename);
			
			DeltaManager delta = new DeltaManager();
			
				
			String sysDbName = Configuration.getInstance().get(SoCTraceProperty.soctrace_db_name);
			String traceDbName = FramesocManager.getInstance().getTraceDBName("Kptracecsvcat");
			
			SystemDBObject sysDB = null;
			TraceDBObject traceDB = null;
			
			
			try {
				
				// open system DB	
				sysDB = new SystemDBObject(sysDbName, DBMode.DB_OPEN);
				// create new trace DB
				traceDB = new TraceDBObject(traceDbName, DBMode.DB_CREATE);
				
				// parsing	
				KptracecsvcatManager manager = new KptracecsvcatManager(sysDB, traceDB, filename);
				delta.start();
				manager.importTrace();
				delta.end("Import trace");
				
				// close the trace DB and the system DB (commit)
				traceDB.close();
				sysDB.close();
				new KptracecsvcatTraceSizeMetadata(new File(filename), traceDbName);
				
			} catch ( SoCTraceException ex ) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
				System.err.println("Import failure. Trying to rollback modifications in DB.");
				if (sysDB!=null)
					try {
						sysDB.rollback();
					} catch (SoCTraceException e) {
						e.printStackTrace();
					}
				if (traceDB!=null)
					try {
						traceDB.dropDatabase();
					} catch (SoCTraceException e) {
						e.printStackTrace();
					}				
			}
			
			
		}

	
		
	}
	
	@Override
	public void launch(IFramesocToolInput input) {
		PluginImporterJob job = new PluginImporterJob("Kptracecsvcat Importer", new KptracecsvcatImporterPluginJobBody((FileInput)input));
		job.setUser(true);
		job.schedule();
	}


}
