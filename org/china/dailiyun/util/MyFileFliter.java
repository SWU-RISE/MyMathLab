package org.china.dailiyun.util;

import java.io.File;
import java.io.FilenameFilter;

class MyFileFliter implements FilenameFilter {
	public boolean accept(File dir, String name) {
		if (name.endsWith("result"))
			return true;
		else
			return false;
	}

}
