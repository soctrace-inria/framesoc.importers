/**
 * 
 */
package fr.inria.soctrace.tools.importer.gstreamer.input;

import fr.inria.soctrace.framesoc.core.tools.model.IFramesocToolInput;
import fr.inria.soctrace.tools.importer.gstreamer.core.GStreamerConstants;

/**
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class GStreamerInput implements IFramesocToolInput {

	public String fileName = "";
	public String frameStartType = GStreamerConstants.DEFAULT_FRAME_START;
	public boolean framesOverlapping = GStreamerConstants.DEFAULT_FRAME_OVELAPPING;
	
	@Override
	public String getCommand() {
		return "";
	}

	@Override
	public String toString() {
		return fileName + ", " + frameStartType + ", " + framesOverlapping;
	}

}
