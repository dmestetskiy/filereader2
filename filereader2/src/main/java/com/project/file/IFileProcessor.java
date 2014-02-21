package com.project.file;

import java.util.Map;
import java.util.SortedSet;

public interface IFileProcessor {

	public Boolean doesFileExist(String filePath);
	
	public Map<String, Long> processFileString();
	
	public String writeToFile(String filePath,SortedSet<Map.Entry<String, Long>> sortedWords);

	public void clear();
}
