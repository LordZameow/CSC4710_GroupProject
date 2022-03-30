
public class tweets {

protected int tweetID;
protected String content;
protected String author;

public tweets() {
}

public tweets(int tweetID, String content, String author) {
	this.tweetID = tweetID;
	this.content = content;
	this.author= author;
}

public tweets(String content, String author) {
	this.content = content;
	this.author = author;
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
