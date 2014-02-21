package com.project.wordmap.util;

import java.util.Map;
import java.util.SortedSet;

public interface IMapProcessor {

	
	public SortedSet<Map.Entry<String, Long>> processWords(Map<String, Long> wordTrack);
}
