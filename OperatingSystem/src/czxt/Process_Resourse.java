package czxt;

public class Process_Resourse {
	public Resourse_ need;
	public Resourse_ allocation;
	public Process_Resourse(Resourse_ need,Resourse_ allocation) {
		this.need=new Resourse_(need);
		this.allocation=new Resourse_(allocation);
	}
}
