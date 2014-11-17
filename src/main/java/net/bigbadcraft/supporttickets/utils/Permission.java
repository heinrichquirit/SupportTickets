package main.java.net.bigbadcraft.supporttickets.utils;

public enum Permission {
	
	PLAYER_USE("supporttickets.use"), PLAYER_REQUEST("supporttickets.request"), PLAYER_CHECK("supporttickets.check"),
	MODERATOR_LIST("supporttickets.list"), MODERATOR_SELECT("supporttickets.select"), MODERATOR_CHANGE("supporttickets.change"),
	ADMIN_PROGRESS_CHECK("supporttickets.staff"), ADMIN_OVERRIDE("supporttickets.override");
	
	private final String perm;
	
	Permission(final String p) {
		perm = p;
	}
	
	public String toString() {
		return perm;
	}
	
}
