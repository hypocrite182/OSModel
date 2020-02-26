package com.os.memory;

import java.util.ArrayList;

public class Memory {
	private int size;

	private ContinualMemoryManage manageRule;

	private ArrayList<MemorySpan> memoryParts = new ArrayList<>();

	public int getSize() {
		return size;
	}

	public ContinualMemoryManage getManageRule() {
		return manageRule;
	}

	public void setManageRule(ContinualMemoryManage manageRule) {
		this.manageRule = manageRule;
	}

	public ArrayList<MemorySpan> getMemoryParts() {
		return memoryParts;
	}

	public Memory(int size, ContinualMemoryManage manageRule) {
		this.size = size;
		this.manageRule = manageRule;
		this.memoryParts.add(new MemorySpan(0, size, false, null));
	}

	public boolean disposeTask(int taskId, int taskSize) {
		switch (this.manageRule) {
		case FirstFit:
			return firstFit(taskId, taskSize);
		case WorstFit:
			return worstFit(taskId, taskSize);
		case BestFit:
			return bestFit(taskId, taskSize);
		default:
			return false;
		}
	}

	public void releaseTask(int taskId) throws Exception {
		int index = -1;
		for (int i = 0; i < this.memoryParts.size(); i++) {
			if (this.memoryParts.get(i).isUsed() == true) {
				if (this.memoryParts.get(i).getTaskId().equals(taskId)) {
					index = i;
				}
			}
		}

		if (index == -1)
			throw new Exception("没有找到该任务");

		this.memoryParts.get(index).setTaskId(null);
		this.memoryParts.get(index).setUsed(false);

		if (index == 0 && index != (this.memoryParts.size() - 1)) {
			if(this.memoryParts.get(index+1).isUsed()==false) {
				mergeFreeSpace(index);
			}
		}
		if ((index == this.memoryParts.size() - 1) && index != 0) {
			if(this.memoryParts.get(index-1).isUsed()==false) {
				mergeFreeSpace(index-1);
			}
		}
		if (index > 0 && index < (this.memoryParts.size() - 1)) {
			if(this.memoryParts.get(index+1).isUsed()==false) {
				mergeFreeSpace(index);
			}
			if(this.memoryParts.get(index-1).isUsed()==false) {
				mergeFreeSpace(index-1);
			}
		}
	}

	private boolean firstFit(int taskId, int taskSize) {
		int index = -1;
		for (int i = 0; i < this.memoryParts.size(); i++) {
			if (this.memoryParts.get(i).isUsed() == false) {
				if (this.memoryParts.get(i).getSize() >= taskSize) {
					index = i;
					break;
				}
			}
		}

		return distributeNewSpace(index, taskId, taskSize);
	}

	private boolean worstFit(int taskId, int taskSize) {
		int maxSize = Integer.MIN_VALUE;
		int index = -1;
		for (int i = 0; i < this.memoryParts.size(); i++) {
			if (this.memoryParts.get(i).isUsed() == false) {
				if (this.memoryParts.get(i).getSize() >= taskSize) {
					if (this.memoryParts.get(i).getSize() > maxSize) {
						maxSize = this.memoryParts.get(i).getSize();
						index = i;
					}
				}
			}
		}

		return distributeNewSpace(index, taskId, taskSize);
	}

	private boolean bestFit(int taskId, int taskSize) {
		int minSize = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < this.memoryParts.size(); i++) {
			if (this.memoryParts.get(i).isUsed() == false) {
				if (this.memoryParts.get(i).getSize() >= taskSize) {
					if (this.memoryParts.get(i).getSize() < minSize) {
						minSize = this.memoryParts.get(i).getSize();
						index = i;
					}
				}
			}
		}

		return distributeNewSpace(index, taskId, taskSize);
	}

	private boolean distributeNewSpace(int index, int taskId, int taskSize) {
		if (index == -1)
			return false;

		int startPosition = this.memoryParts.get(index).getStartPosition();
		int oldSize = this.memoryParts.get(index).getSize();
		this.memoryParts.add(index,
				new MemorySpan(startPosition, taskSize, true, taskId));
		this.memoryParts.remove(index + 1);
		if (oldSize - taskSize > 0)
			this.memoryParts.add(index + 1, new MemorySpan(
					startPosition + taskSize, oldSize - taskSize, false, null));
		return true;
	}
	
	private void mergeFreeSpace(int index) {
		int startPosition=this.memoryParts.get(index).getStartPosition();
		int size=this.memoryParts.get(index).getSize()+this.memoryParts.get(index+1).getSize();
		this.memoryParts.remove(index);
		this.memoryParts.remove(index);
		this.memoryParts.add(index,new MemorySpan(startPosition, size, false, null));
	}
}
