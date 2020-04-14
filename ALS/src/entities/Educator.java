package entities;

public class Educator {
	private int educatorId;	
	private String educatorName;
	
	public Educator(int educatorId, String educatorName) {
		this.educatorId = educatorId;
		this.educatorName = educatorName;
	}

	public int getEducatorId() {
		return educatorId;
	}

	public String getEducatorName() {
		return educatorName;
	}
	
	@Override
	public String toString() {
		return educatorName;		
	}
}
