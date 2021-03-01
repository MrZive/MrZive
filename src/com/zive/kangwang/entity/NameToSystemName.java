package com.zive.kangwang.entity;

public class NameToSystemName {

	private String OldName;
	
	private String NewName;
	
	private Integer time;
	
	private String type;

	public String getOldName() {
		return OldName;
	}

	public void setOldName(String oldName) {
		OldName = oldName;
	}

	public String getNewName() {
		return NewName;
	}

	public void setNewName(String newName) {
		NewName = newName;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
