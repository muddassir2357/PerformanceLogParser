package com.adobe.PerformanceLogParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import lombok.Data;

@Data
public class StepFactory {
	
	private List<StepNode> steps;
	private StepNode root;
	
	public StepFactory() {
		steps= new ArrayList<StepNode>();
	}
	/**
	 * This method should only be called in StepFactory
	 * @param stepFilePath
	 * @return root of the generated Linked list
	 */
	public StepNode generateStepsList(String stepFilePath) {
		createSteps(stepFilePath);
		root = linkSteps();
		return this.root;
	}

	private StepNode linkSteps() {
		
		for(int i=0;i<steps.size();i++) {
			steps.get(i).setPrevious(null).setNext(null);
			if(i!=0)
				steps.get(i).setPrevious(steps.get(i-1));
			if(i!=steps.size()-1)
				steps.get(i).setNext(steps.get(i+1));
		}
		steps.get(0).setPrevious(steps.get(steps.size()-1));
		steps.get(steps.size()-1).setNext(steps.get(0));
		return steps.get(0);
	}

	private void createSteps(String stepFilePath)  {
		Properties stepProperty = new Properties();
		try {
			stepProperty.load(new FileInputStream(new File(stepFilePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int numberOfSteps=stepProperty.size();
		for(int i=1;i<=numberOfSteps;i++) {
			StepNode node = new StepNode(stepProperty.getProperty("Step"+i));
			this.steps.add(node);
		}
	}
}
