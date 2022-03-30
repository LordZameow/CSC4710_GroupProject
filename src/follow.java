
public class follow {
protected String followeeID;
protected String followerID;

public follow() {
	
}

public follow(String followeeID, String followerID) {
	this.followeeID=followeeID;
	this.followerID=followerID;
}

public String getFolloweeID() {
	return followeeID;
}

public void setFolloweeID(String followeeID) {
	this.followeeID=followeeID;
}

public String getFollowerID() {
	return followerID;
}

public void setFollowerID(String followerID) {
	this.followerID=followerID;
}
}
