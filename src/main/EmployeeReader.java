package main;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EmployeeReader {
	private static final String PRESENT_DATE = "NULL";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final String CSV_SEPERATOR = ",";
	private final Path path;

	public EmployeeReader(Path path) {
		this.path = path;
	}
	
	Collection<Project> projects() throws IOException {
		Map<Integer, Project.Builder> projectBuilders = new HashMap<>();
		
		Files.lines(path).map(line -> line.split(CSV_SEPERATOR)).forEach(employeeMembership -> {
			int employeeId = Integer.parseInt(employeeMembership[0].trim());
			int projectId = Integer.parseInt(employeeMembership[1].trim());
			
			LocalDate startDate = LocalDate.from(DATE_TIME_FORMATTER.parse(employeeMembership[2].trim()));
			LocalDate endDate = PRESENT_DATE.equals(employeeMembership[3].trim()) ? LocalDate.now() 
					: LocalDate.from(DATE_TIME_FORMATTER.parse(employeeMembership[3].trim()));
			
			projectBuilders.computeIfAbsent(projectId, id -> Project.builder().setId(id));
			projectBuilders.get(projectId).addEmployeeMembership(new EmployeeProjectMembership(employeeId, startDate, endDate));
		});
		
		return projectBuilders.values().stream()
				.map(builder -> builder.build())
				.collect(toList());
	}
}
