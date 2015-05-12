package fr.inria.soctrace.tools.importer.kptcsv.core;

import fr.inria.soctrace.lib.model.File;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.IdManager;
/**
 * KPTraceCSV Parser Tool
 *
 * @author "Alexis Martin <alexis.martin@inria.fr>"
 *
 */
public class KptcsvFileManager {
	
	private String path;
	private String description;
	private TraceDBObject traceDB;
	
	public KptcsvFileManager(TraceDBObject traceDB, String path, String description){
		this.path = path;
		this.description = description;
		this.traceDB = traceDB;
	}
	
	public void saveFile() throws SoCTraceException{
		IdManager fileIdManager = new IdManager();
		File f = new File(fileIdManager.getNextId());
		f.setPath(path);
		f.setDescription(description);
		traceDB.save(f);
	}
	
}
