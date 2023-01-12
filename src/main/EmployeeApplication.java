package main;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

public class EmployeeApplication {
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("Employee csv file path should be specified.");
			return;
		}
		
		Path filePath = Paths.get(args[0]);
		try {
			Collection<Project> projects = new EmployeeReader(filePath).projects();
			
			ProjectEmployeePair result = projects.stream()
											.map(project -> project.getEmployeePairWithLongestCommonPeriod())
											.max(ProjectEmployeePair::compareTo)
											.get();
			
			if(result.getDuration() == -1) {
				System.out.println("No two employees worked on the same project at the same time");
				return;
			}
			
			System.out.println(String.format("%s, %s, %s", result.getEmployee1Id(), result.getEmployee2Id(), result.getProjectId()));
		} catch (IOException e) {
			System.out.println("Could not load employees file.");
		}
	}
}
