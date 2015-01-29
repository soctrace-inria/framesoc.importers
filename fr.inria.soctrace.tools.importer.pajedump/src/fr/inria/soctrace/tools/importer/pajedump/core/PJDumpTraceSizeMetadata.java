package fr.inria.soctrace.tools.importer.pajedump.core;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.inria.soctrace.lib.model.Trace;
import fr.inria.soctrace.lib.model.TraceParam;
import fr.inria.soctrace.lib.model.TraceParamType;
import fr.inria.soctrace.lib.model.TraceType;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.query.TraceParamTypeQuery;
import fr.inria.soctrace.lib.query.TraceQuery;
import fr.inria.soctrace.lib.query.TraceTypeQuery;
import fr.inria.soctrace.lib.query.conditions.SimpleCondition;
import fr.inria.soctrace.lib.query.conditions.ConditionsConstants.ComparisonOperation;
import fr.inria.soctrace.lib.storage.DBObject;
import fr.inria.soctrace.lib.storage.SystemDBObject;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.storage.DBObject.DBMode;
import fr.inria.soctrace.lib.storage.utils.SQLConstants.FramesocTable;
import fr.inria.soctrace.lib.utils.Configuration;
import fr.inria.soctrace.lib.utils.DBMS;
import fr.inria.soctrace.lib.utils.IdManager;
import fr.inria.soctrace.lib.utils.Configuration.SoCTraceProperty;

public class PJDumpTraceSizeMetadata {
	
	public PJDumpTraceSizeMetadata(File input, String traceDbName) throws SoCTraceException{
		SystemDBObject sysDB = SystemDBObject.openNewIstance();
		TraceDBObject traceDB = new TraceDBObject(traceDbName, DBMode.DB_OPEN);
		final TraceQuery tQuery = new TraceQuery(sysDB);
		tQuery.setElementWhere(new SimpleCondition(
				"TRACE_DB_NAME", ComparisonOperation.EQ, traceDB.getDBName()));
		List<Trace> traces = tQuery.getList();
		if (!traces.isEmpty()){
		saveParam(traces.get(0), sysDB, "Trace size", "String", getStringSize(input));
		traceDB.close();
		sysDB.close();
		sysDB = SystemDBObject.openNewIstance();
		traceDB = new TraceDBObject(traceDbName, DBMode.DB_OPEN);
		String value = "unknown";
		String dbms = Configuration.getInstance().get(SoCTraceProperty.soctrace_dbms);
		if (dbms.equalsIgnoreCase(DBMS.SQLITE.toString())) {
			value=getStringSize(new File(Configuration.getInstance().get(SoCTraceProperty.sqlite_db_directory)+File.separator+traceDB.getDBName()));
		}
		saveParam(traces.get(0), sysDB, "DB size", "String", value);
		}
		traceDB.close();
		sysDB.close();
	}
	
	private void saveParam(Trace trace, SystemDBObject sysDB, String name, String type, String value)
			throws SoCTraceException {


		try {

			// trace type id -> (trace param type name -> trace param type)
			Map<Integer, Map<String, TraceParamType>> typeMap = new HashMap<>();

			// init all trace types
			TraceTypeQuery ttQuery = new TraceTypeQuery(sysDB);
			List<TraceType> ttList = ttQuery.getList();
			for (TraceType tt : ttList) {
				typeMap.put(tt.getId(), new HashMap<String, TraceParamType>());
			}

			// init all trace param types
			TraceParamTypeQuery query = new TraceParamTypeQuery(sysDB);
			List<TraceParamType> types = query.getList();
			for (TraceParamType tpt : types) {
				typeMap.get(tpt.getTraceType().getId()).put(tpt.getName(), tpt);
			}

			IdManager tptId = new IdManager();
			IdManager tpId = new IdManager();
			tptId.setNextId(1 + sysDB.getMaxId(FramesocTable.TRACE_PARAM_TYPE.toString(), "ID"));
			tpId.setNextId(1 + sysDB.getMaxId(FramesocTable.TRACE_PARAM.toString(), "ID"));

				Map<String, TraceParamType> tptMap = typeMap.get(trace.getType().getId());
				if (!tptMap.containsKey(name)) {
					// the trace param type is not present for this trace type: create it
					TraceParamType tpt = new TraceParamType(tptId.getNextId());
					tpt.setName(name);
					tpt.setType(type);
					tpt.setTraceType(trace.getType());
					tptMap.put(name, tpt);
					sysDB.save(tpt);
				}
				if (!trace.getParamMap().containsKey(name)) {
					// the trace param is not present for this trace param type: create it
					TraceParam tp = new TraceParam(tpId.getNextId());
					tp.setTrace(trace);
					tp.setValue(value);
					tp.setTraceParamType(tptMap.get(name));
					sysDB.save(tp);
				}



		} finally {

		}

	}
	

	private String getStringSize(File file) {
		double size = file.length();
		DecimalFormat df = new DecimalFormat("###.##");
		if (size < 1000)
			return df.format(size) + " B";
		size = size / 1000;
		if (size < 1000)
			return df.format(size) + " kB";
		size = size / 1000;
		if (size < 1000)
			return df.format(size) + " MB";
		size = size / 1000;
		if (size < 1000)
			return df.format(size) + " GB";
		size = size / 1000;
		if (size < 1000)
			return df.format(size) + " TB";
		size = size / 1000;
		if (size < 1000)
			return df.format(size) + " PB";
		size = size / 1000;
		if (size < 1000)
			return df.format(size) + " EB";
		size = size / 1000;
		if (size < 1000)
			return df.format(size) + " ZB";
		size = size / 1000;
		if (size < 1000)
			return df.format(size) + " YB";
		else
			return "unknown size";
	}

}
