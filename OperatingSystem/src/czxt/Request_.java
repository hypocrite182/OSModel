package czxt;

public class Request_ {
	private int pc;
	private Resourse_ request;
	public Request_(int pc,Resourse_ request) {
		setPc(pc);
		setRequest(request);
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int count_id) {
		this.pc = count_id;
	}

	public Resourse_ getRequest() {
		return request;
	}
	public void setRequest(Resourse_ request) {
		this.request = request;
	}

}
