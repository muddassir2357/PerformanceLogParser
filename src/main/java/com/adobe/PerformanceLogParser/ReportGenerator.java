package com.adobe.PerformanceLogParser;

public class ReportGenerator {
	
	public void generateReport(StepNode root) {
		StepNode currentNode=root;
		boolean flag=true;
		do {
			System.out.println(currentNode.getName()+": "+ currentNode.getAverageTime());
			currentNode=currentNode.getNext();

		}while(!(currentNode.getId()<currentNode.getPrevious().getId()));
	}

}
