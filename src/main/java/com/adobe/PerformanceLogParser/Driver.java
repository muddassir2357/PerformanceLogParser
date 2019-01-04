package com.adobe.PerformanceLogParser;

public class Driver {
	
	public static void main(String[] args) {
		String curDir=  System.getProperty("user.dir") + "/src/main/java/com/adobe/PerformanceLogParser/";
		Crawler crawler=Crawler.initialize(curDir+"Steps.properties","C:/Users/fazal/Desktop/Data/Trash/readings.txt" );
		crawler.crawl(crawler.getRoot(),0);
		ReportGenerator generator= new ReportGenerator();
		generator.generateReport(crawler.getRoot());
		
	}

}
