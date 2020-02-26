package com.os.memory;

public class MemorySpan {
	private int size;
	
	private int startPosition;
	
	private int endPosition;
	
	private boolean isUsed;
	
	private Integer taskId;

	public MemorySpan(int startPosition,int size,boolean isUsed,Integer taskId) {
		this.size=size;
		this.startPosition=startPosition;
		this.endPosition=startPosition+size;
		this.isUsed=isUsed;
		this.taskId=taskId;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(int startPosition) {
		this.startPosition = startPosition;
	}

	public int getEndPosition() {
		return endPosition;
	}

	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
}
