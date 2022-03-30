
public class comment {
protected int tweetID;
protected String content;
protected String commenter;
protected int id;
protected String transTime;

public comment() {
	
}

public comment(int tweetID, String content,String commenter,int id,String transTime) {
	this.tweetID=tweetID;
	this.content =content;
	this.commenter=commenter;
	this.id=id;
	this.transTime=transTime;
}

public int getTweetID() {
	return tweetID;
}

public void setTweetID(int tweetID) {
	this.tweetID = tweetID;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

public String getCommenter() {
	return commenter;
}

public void setCommenter(String commenter) {
	this.commenter = commenter;
}

public int getID() {
	return id;
}

public String getTransTime() {
	return transTime;
}

public void setTransTime(String transTime) {
	this.transTime = transTime;
}
}
