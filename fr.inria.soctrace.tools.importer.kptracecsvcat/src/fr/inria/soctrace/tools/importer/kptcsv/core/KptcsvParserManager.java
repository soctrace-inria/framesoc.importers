package fr.inria.soctrace.tools.importer.kptcsv.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.inria.soctrace.lib.model.Event;
import fr.inria.soctrace.lib.model.EventParam;
import fr.inria.soctrace.lib.model.EventParamType;
import fr.inria.soctrace.lib.model.EventProducer;
import fr.inria.soctrace.lib.model.EventType;
import fr.inria.soctrace.lib.model.utils.ModelConstants.EventCategory;
import fr.inria.soctrace.lib.model.utils.SoCTraceException;
import fr.inria.soctrace.lib.storage.TraceDBObject;
import fr.inria.soctrace.lib.utils.IdManager;
/**
 * KPTraceCSV Parser Tool
 *
 * @author "Alexis Martin <alexis.martin@inria.fr>"
 *
 */
public class KptcsvParserManager {

	private File tracefile;
	private TraceDBObject traceDB;
	
	private IdManager eIdManager;
	private IdManager epIdManager;
	private IdManager etIdManager;
	private IdManager eptIdManager;
	private IdManager eprodIdManager;
	
	private List<String> listEventTypeNames;
	private List<EventType> listEventType;
	
	private List<List<EventParamType>> listEventParamType;
	
	private List<String> listEventProducerName;
	private List<EventProducer> listEventProducer;
	
	
	public KptcsvParserManager(TraceDBObject traceDB, String path){
		
		this.traceDB = traceDB;
		tracefile = new File(path);
		this.listEventType = new ArrayList<EventType>();
		
		//create id managers for E EP ET EPT
		eIdManager = new IdManager();
		epIdManager = new IdManager();
		etIdManager = new IdManager();
		eptIdManager = new IdManager();
		eprodIdManager = new IdManager();
		
		listEventTypeNames = new ArrayList<String>();
		listEventType = new ArrayList<EventType>();
		
		listEventParamType = new ArrayList<List<EventParamType>>();
		
		listEventProducerName = new ArrayList<String>();
		listEventProducer = new ArrayList<EventProducer>();
		
	}
	
	public int parseTrace() throws FileNotFoundException, SoCTraceException{
	
		Integer numberOfEvents = 0;
		
		// scanner to read file
		Scanner tracescan = new Scanner(tracefile);
		
		//TODO : verify if file is correct : not empty and 1st line contains names
		
		// names of columns
		String names[] = tracescan.nextLine().split(",");
		
		
		while(tracescan.hasNextLine()){

			String tokens[] = tracescan.nextLine().split(",",-1);
			
			Event e = new Event(eIdManager.getNextId());
			
			
			//fill eventprod
			if(listEventProducerName.contains(tokens[5])){
				e.setEventProducer(listEventProducer.get(listEventProducerName.indexOf(tokens[5])));
			}else{
				EventProducer eprod = new EventProducer(eprodIdManager.getNextId());
				eprod.setName(tokens[5]);
				eprod.setType(tokens[5]);
				eprod.setLocalId(tokens[5]);
				traceDB.save(eprod);
				listEventProducerName.add(listEventProducerName.size(), tokens[5]);
				listEventProducer.add(listEventProducer.size(), eprod);
				e.setEventProducer(eprod);
			}
			
			//fill timestamp
			e.setTimestamp(Long.parseLong(tokens[0]));
						
			//fill CPU
			if(tokens[7].length() != 0){
				e.setCpu(Integer.parseInt(tokens[7]));
			}else{
				e.setCpu(-1);
			}
			
			//fill categoy and parameters
			if(tokens[6].length() != 0){
				e.setCategory(EventCategory.LINK);
				e.setLongPar(e.getTimestamp()); // end timestamp
				// previous prod
				if(listEventProducerName.contains(tokens[6])){
					e.setDoublePar(listEventProducerName.indexOf(tokens[6]));
				}else{
					EventProducer eprod = new EventProducer(eprodIdManager.getNextId());
					eprod.setName(tokens[6]);
					traceDB.save(eprod);
					listEventProducerName.add(listEventProducerName.size(), tokens[6]);
					listEventProducer.add(listEventProducer.size(), eprod);
					e.setDoublePar(eprod.getId());
				}
			}
			else if(tokens[1].length() != 0){
				e.setCategory(EventCategory.STATE);
				e.setLongPar(Long.parseLong(tokens[1]));
				e.setDoublePar(0);
			}
			else{
				e.setCategory(EventCategory.PUNCTUAL_EVENT);
			}
			
			
			//fill the event type
			if(listEventTypeNames.contains(tokens[4])){
				// manage bug from the trace that have for one type two category (ex: sys_select)
				if(e.getCategory() != listEventType.get(listEventTypeNames.indexOf(tokens[4])).getCategory() ){
					continue;
				}
				e.setType(listEventType.get(listEventTypeNames.indexOf(tokens[4])));
			}else{
				EventType et = new EventType(etIdManager.getNextId(),e.getCategory());
				et.setName(tokens[4]);
				traceDB.save(et);
				listEventTypeNames.add(listEventTypeNames.size(), tokens[4]);
				listEventType.add(listEventType.size(), et);
				e.setType(et);
				// create eventparamtypes for this event
				List<EventParamType> currentListEventParamType = new ArrayList<EventParamType>();
				for(int i=8;i<names.length;i++){
					if(tokens[i].length() != 0){
						EventParamType ept = new EventParamType(eptIdManager.getNextId());
						ept.setEventType(et);
						ept.setName(names[i]);
						ept.setType("STRING");
						traceDB.save(ept);
						currentListEventParamType.add(ept);
					}
				}
				listEventParamType.add(currentListEventParamType);
			}
			
			//add remains parameters
			int paramnumber=0;
			for(int i=8;i<names.length;i++){
				if(tokens[i].length() != 0){
					EventParam ep = new EventParam(epIdManager.getNextId());
					ep.setEvent(e);
					ep.setEventParamType(listEventParamType.get(e.getType().getId()).get(paramnumber));
					ep.setValue(tokens[i]);
					traceDB.save(ep);
					paramnumber++;
				}
			}
			
			traceDB.save(e);
				
			numberOfEvents++;
			if(numberOfEvents % 20000 == 0){
				traceDB.flushVisitorBatches();
			}
		}
		
		tracescan.close();
		
		return numberOfEvents;
	}
	
}
