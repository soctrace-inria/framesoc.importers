/**
 * 
 */
package fr.inria.soctrace.tools.importer.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.IStartup;

import fr.inria.soctrace.framesoc.ui.colors.FramesocColor;
import fr.inria.soctrace.framesoc.ui.colors.FramesocColorManager;

/**
 * Color manager for different trace types.
 * 
 * The color files are in the /colors/ folder of this plugin. Each entity has its own subfolder
 * (e.g., types, producers).
 * 
 * @author "Generoso Pagano <generoso.pagano@inria.fr>"
 */
public class ColorManager implements IStartup {

	private final static String COLORS = "/colors/";
	private final static String TYPES = COLORS + "types/";
	private final static String PRODUCERS = COLORS + "producers/";

	@Override
	public void earlyStartup() {
		try {
			File bundleDir = FileLocator.getBundleFile(Activator.getDefault().getBundle());
			String bundlePath = bundleDir.getAbsolutePath();
			File types[] = getFiles(bundlePath + TYPES);
			if (types.length > 0) {
				FramesocColorManager.getInstance().addEventTypeColors(getColors(types));
			}
			File prods[] = getFiles(bundlePath + PRODUCERS);
			if (prods.length > 0) {
				FramesocColorManager.getInstance().addEventProducerColors(getColors(prods));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File[] getFiles(String dir) {
		File folder = new File(dir);
		return folder.listFiles();
	}

	private Map<String, FramesocColor> getColors(File files[]) {
		Map<String, FramesocColor> colors = new HashMap<>();
		for (File file : files) {
			if (file.getName().startsWith(".")) {
				// skip hidden files
				continue;
			}
			colors.putAll(loadColorFile(file));
		}
		return colors;
	}

	private Map<String, FramesocColor> loadColorFile(File file) {
		Map<String, FramesocColor> colors = new HashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.equals(""))
					continue;
				if (line.startsWith("#"))
					continue;
				String[] prop = line.split(FramesocColorManager.EQUAL);
				if (prop.length != 2) {
					br.close();
					System.err.println("Error at: " + line);
				}
				colors.put(prop[0], getFramesocColor(prop[1]));
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return colors;
	}

	private FramesocColor getFramesocColor(String color) {
		String[] colors = color.split(FramesocColorManager.SEPARATOR);
		if (colors.length != 3) {
			System.err.println("Error at: " + color);
		}
		try {
			int r = Integer.valueOf(colors[0]);
			int g = Integer.valueOf(colors[1]);
			int b = Integer.valueOf(colors[2]);
			return new FramesocColor(r, g, b);
		} catch (NumberFormatException e) {
			System.err.println("Error at: " + color);
		}
		return null;
	}

}
