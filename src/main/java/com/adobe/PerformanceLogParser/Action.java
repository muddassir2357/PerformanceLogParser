package com.adobe.PerformanceLogParser;

import lombok.Data;

@Data
public class Action {

	private String name;
	private String workingSetSize;
	private String virtualSize;
	private String osName;
	private String timeTaken;
	
}
