package com.sitech.basd.sxcloud.util.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PathConfig {
	private static PathConfig pc;

	private PathConfig() {
		read();
	}

	public static synchronized PathConfig getInstance() {
		if (pc != null) {
			return pc;
		} else {
			pc = new PathConfig();
			return pc;
		}
	}

	private String file = "path.properties";

	private String path = "";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void read() {
		Properties pp = load(file);

		if (pp != null) {
			path = pp.getProperty("file");
		}
	}

	private Properties load(String file) {
		Properties pp = new Properties();
		InputStream is = null;

		is = getClass().getResourceAsStream(file);

		try {
			pp.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return pp;
	}

}
