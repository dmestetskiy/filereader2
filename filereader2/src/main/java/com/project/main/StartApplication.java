package com.project.main;

import java.util.Map;
import java.util.SortedSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.project.common.Constants;
import com.project.file.IFileProcessor;
import com.project.input.UserInputReader;
import com.project.wordmap.util.IMapProcessor;

public class StartApplication {

	private IFileProcessor fileProcessor;
	
	private IMapProcessor mapProcessor;
	
	public StartApplication(){
		ApplicationContext context = new FileSystemXmlApplicationContext("**/applicationContext.xml");
		fileProcessor = (IFileProcessor)context.getBean("fileProcessor");
		mapProcessor = (IMapProcessor)context.getBean("mapProcessor");
	}
	
	public Boolean doesFileExist(String path){
		return fileProcessor.doesFileExist(path);
	}
	
	public void processFile(String path){
		Map<String, Long> wordTrack = fileProcessor.processFileString();
		SortedSet<Map.Entry<String, Long>> sortedWords = mapProcessor.processWords(wordTrack);
		String fileName = fileProcessor.writeToFile(path, sortedWords);
		System.out.println(Constants.YOUR_FILE_IS_HERE+":"+fileName);
		fileProcessor.clear();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String command = null;
		
		
		StartApplication startApplication = new StartApplication();
		
		while(!(command =UserInputReader.readCommands()).equals(Constants.Q)){
			if(startApplication.doesFileExist(command)){
				long startTime = System.currentTimeMillis();
				startApplication.processFile(command);
				long endTime   = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				System.out.println("Total time ran in ms:"+totalTime);
			}else{
				System.out.println(Constants.COULD_NOT_PROCESS_INPUT);
			}
			System.out.println();
		}
		
	}

}
