package main;

public class ProjectEmployeePair implements Comparable<ProjectEmployeePair> {
	private final int employee1Id;
	private final int employee2Id;
	private final int projectId;
	private final long duration;
	
	public ProjectEmployeePair(int employee1Id, int employee2Id, int projectId, long duration) {
		this.employee1Id = employee1Id;
		this.employee2Id = employee2Id;
		this.projectId = projectId;
		this.duration = duration;
	}

	public int getEmployee1Id() {
		return employee1Id;
	}

	public int getEmployee2Id() {
		return employee2Id;
	}

	public int getProjectId() {
		return projectId;
	}

	public long getDuration() {
		return duration;
	}

	@Override
	public int compareTo(ProjectEmployeePair o) {
		return this.duration - o.duration > 0 ? 1 : -1;
	}
}
