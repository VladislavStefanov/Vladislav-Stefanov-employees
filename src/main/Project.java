package main;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Project {
	private final int id;
	private final List<EmployeeProjectMembership> employeeMemberships;
	
	private Project(Builder builder) {
		this.id = builder.id;
		this.employeeMemberships = builder.employeeMemberships;
	}
	
	public static Builder builder() {
		return new Project.Builder();
	}
	
	public int getId() {
		return id;
	}

	public ProjectEmployeePair getEmployeePairWithLongestCommonPeriod() {
		Collections.sort(employeeMemberships);
		
		long maxOverlap = 0;
		ProjectEmployeePair result = new ProjectEmployeePair(-1, -1, id, -1);
		EmployeeProjectMembership first = employeeMemberships.get(0);
		
		Iterator<EmployeeProjectMembership> iterator = employeeMemberships.iterator();
		if (!iterator.hasNext()) {
			return result;
		}
		iterator.next();
		while(iterator.hasNext()) {
			EmployeeProjectMembership second = iterator.next();
			LocalDate minEndDate = first.getEndDate().isBefore(second.getEndDate()) ? first.getEndDate() : second.getEndDate();
			long overlap = Duration.between(second.getStartDate().atStartOfDay(), minEndDate.atStartOfDay()).toDays();
			if (overlap > maxOverlap) {
				maxOverlap = overlap;
				result = new ProjectEmployeePair(first.getEmployeeId(), second.getEmployeeId(), id, overlap);
			}
			
			if(second.getEndDate().isAfter(first.getEndDate())) {
				first = second;
			}
		}
		
		return result;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", " + employeeMemberships + "]";
	}



	public static class Builder {
		private int id;
		private List<EmployeeProjectMembership> employeeMemberships = new LinkedList<>();
		
		public Builder setId(int id) {
			this.id = id;
			return this;
		}
		public Builder addEmployeeMembership(EmployeeProjectMembership employeeProjectMembership) {
			employeeMemberships.add(employeeProjectMembership);
			return this;
		}
		
		public Project build() {
			return new Project(this);
		}
	}
}
