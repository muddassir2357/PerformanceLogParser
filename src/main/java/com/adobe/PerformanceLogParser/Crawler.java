package com.adobe.PerformanceLogParser;

import java.util.List;

import lombok.Data;

@Data
public class Crawler {

	private StepNode root;
	private List<Action> actions;

	private Crawler() {
	}

	public static Crawler initialize(String stepFilePath, String logFilePath) {
		Crawler crawler = new Crawler();
		StepFactory factory = new StepFactory();
		crawler.root = factory.generateStepsList(stepFilePath);
		LogParser parser = new LogParser();
		parser.setFile(logFilePath);
		crawler.actions = parser.parse();
		return crawler;
	}

	public int crawl(StepNode currentStep, int actionIndex) {
		if (currentStep.getName().equals(actions.get(actionIndex).getName())) {
			
			String timeTaken=actions.get(actionIndex).getTimeTaken();
			int timeInt=Integer.parseInt(timeTaken.substring(0, timeTaken.length()-2));
			currentStep.setAppearanceCount(currentStep.getAppearanceCount() + 1);
			currentStep.setTimeTaken(currentStep.getTimeTaken() + timeInt);
		}
		else if(actionIndex<actions.size()-1)  {
			actionIndex=crawl(currentStep,++actionIndex);
		}
		if(actionIndex<actions.size()-1) {
			actionIndex=crawl(currentStep.getNext(),++actionIndex);
		}else {
			System.out.println("Congratulations ,Crawl Completed!");			
		}
		return actionIndex;
	}

}
