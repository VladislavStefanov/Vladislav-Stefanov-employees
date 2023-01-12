package main;

import java.time.LocalDate;

public class EmployeeProjectMembership implements Comparable<EmployeeProjectMembership> {
	private final int employeeId;
	private final LocalDate startDate;
	private final LocalDate endDate;
	
	public EmployeeProjectMembership(int employeeId, LocalDate startDate, LocalDate endDate) {
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}

	@Override
	public String toString() {
		return "[employeeId=" + employeeId + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}

	@Override
	public int compareTo(EmployeeProjectMembership o) {
		if(this.startDate.isAfter(o.startDate)) return 1;
		if(this.startDate.isBefore(o.startDate)) return -1;
		if(this.endDate.isBefore(o.endDate)) return 1;
		if(this.endDate.isAfter(o.endDate)) return -1;
		return 0;
	}
}
