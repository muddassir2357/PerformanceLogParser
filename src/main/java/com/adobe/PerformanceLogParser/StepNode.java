package com.adobe.PerformanceLogParser;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class StepNode {
	
	private static int count=0;

	private String name;
	private int id;
	private int appearanceCount;
	private int timeTaken;
	private StepNode previous;
	private StepNode next;
	
	public StepNode(String name){
		this.name=name;
		this.id=++count;
		this.appearanceCount=0;		
	}
	
	public float getAverageTime() {
		if(timeTaken==0||appearanceCount==0)
			return 0;
		return (float)this.timeTaken/(float)(this.appearanceCount*1000);
	}
}
