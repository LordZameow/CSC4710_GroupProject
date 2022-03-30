
public class tweets {

protected int tweetID;
protected String content;
protected String author;
protected int likeCounter;
protected String transTime;

public tweets() {
}

public tweets(int tweetID, String content, String author,int likeCounter,String transTime) {
	this.tweetID = tweetID;
	this.content = content;
	this.author= author;
	this.likeCounter=likeCounter;
	this.transTime=transTime;
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

public String getAuthor(){
	return author;
}

public int getLikeCounter() {
	return likeCounter;
}

public String getTransTime() {
	return transTime;
}

}
