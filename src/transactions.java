import java.sql.Timestamp;

public class transactions {
protected int transID;
protected String sender;
protected String reciever;
protected double ppsAmount;
protected String transTime;
protected String transType;
protected double ppsPrice;


public transactions() {
}

public transactions(int transID, String sender, String reciever, double ppsAmount, String transTime, String transType, double ppsPrice) {
	this.transID=transID;
	this.sender=sender;
	this.reciever=reciever;
	this.ppsAmount=ppsAmount;
	this.transTime=transTime;
	this.transType=transType;
	this.ppsPrice=ppsPrice;
}

public int getTransID() {
	return transID;
}

public String getSender() {
	return sender;
}

public String getReciever() {
	return reciever;
}

public double getPPSAmount() {
	return ppsAmount;
}

public String getTransTime() {
	return transTime;
}

public String getTransType() {
	return transType;
}

public double getPPSPrice() {
	return ppsPrice;
}

public void setTransID(int transID) {
	this.transID=transID;
}

}
