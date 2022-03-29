
public class tweets {

protected int tweetID;
protected String content;

public tweets() {
}

public tweets(int tweetID, String content) {
	this.tweetID = tweetID;
	this.content = content;
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

}
