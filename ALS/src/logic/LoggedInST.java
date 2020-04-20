package logic;

import entities.Educator;

public class LoggedInST {
	
	private static LoggedInST inst = null;
	private static Educator user;
	
	private LoggedInST() {		
	}
	
	public static LoggedInST instance() {
		
		if (inst == null) {
			inst = new LoggedInST();
		}				
		return inst;		
	}

	public static Educator getUser() {
		return user;
	}

	public static void setUser(Educator user) {
		LoggedInST.user = user;
	}	
}
