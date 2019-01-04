package com.adobe.PerformanceLogParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LogParser {

	private File logFile;

	public void setFile(String path) {
		logFile = new File(path);
	}
/**
 * This the main method, It parse the log file and generate an List of Action Objects.
 * This method should be called only after setFile(Path) method has been called
 * @return List of Action Objects
 */
	public List<Action> parse() {

		InputStream io = getDataStream();
		String rawData = getDataFromStream(io);
		String[] actionData = extractActions(rawData);
		List<Action> actions = objectify(actionData);
		return actions;
	}

	private List<Action> objectify(String[] actionDatas) {
		List<Action> actions = new ArrayList<Action>();
		for (String actionData : actionDatas) {
			Action action = new Action();
			String[] rows = actionData.split("\r\n");
			for (int i = 1; i < rows.length; i++) {
				String[] columns = rows[i].split(":");
				columns[1]=columns[1].trim();
				switch (i) {
				case 1:
					action.setName(columns[1]);
					break;
				case 2:
					action.setWorkingSetSize(columns[1]);
					break;
				case 3:
					action.setVirtualSize(columns[1]);
					break;
				case 4:
					action.setOsName(columns[1]);
					break;
				case 5:
					action.setTimeTaken(columns[1]);
					break;
				}
			}
			actions.add(action);
		}

		return actions;
	}

	private String[] extractActions(String rawData) {
		String actionString[] = rawData
				.split("\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*\\*");
		String[] trimmedActionString = new String[actionString.length];
		for(int i=0;i<actionString.length;i++) {
			trimmedActionString[i]=actionString[i].trim();
		}
		return trimmedActionString;
	}

	private String getDataFromStream(InputStream io) {
		int nextByte;
		StringBuffer data = new StringBuffer();

		try {
			while ((nextByte = io.read()) != -1) {
				data.append(Character.toChars(nextByte));
			}
		} catch (IOException e) {
			System.err.println("Error while reading file!");
			e.printStackTrace();
		}

		return data.toString();
	}

	private InputStream getDataStream() {
		InputStream io = null;
		try {
			io = new FileInputStream(logFile);
		} catch (FileNotFoundException e1) {
			System.err.println("LogFile not found!");
			e1.printStackTrace();
		}
		return io;
	}

}
