package com.zive.pub;

/**
 * 人员排班
 * 
 * @author lwf
 *
 */
public class Scheduling{
	private String employeeId;
	private String schedualType;
	private String workTime;
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getSchedualType() {
		return schedualType;
	}
	public void setSchedualType(String schedualType) {
		this.schedualType = schedualType;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	@Override
	public String toString() {
		return "Scheduling [employeeId=" + employeeId + ", schedualType=" + schedualType + ", workTime=" + workTime + "]";
	}
}
