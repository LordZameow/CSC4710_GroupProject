
public class User {
protected int age;
protected String firstName;
protected String lastName;
protected String userName;
protected String password;
protected double dollarBal;
protected double ppsBal;


public User() {
}

public User(String userName,String password) {
	this.userName = userName;
	this.password = password;
}

public User(String userName,String password, String firstName, String lastName,int age) {

	this.userName = userName;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.ppsBal = 0;
	this.dollarBal = 1000;
}

public User(String userName,String password, String firstName, String lastName,int age,double dollarBal,double ppsBal) {

	this.userName = userName;
	this.password = password;
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
	this.dollarBal = dollarBal;
	this.ppsBal = ppsBal;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName =firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public double getPPSBal() {
	return ppsBal;
}

public void setPPSBal(double ppSwapBal) {
	this.ppsBal = ppSwapBal;
}

public double getDollarBal() {
	return dollarBal;
}

public void setDollarBal(double dollarBal) {
	this.dollarBal = dollarBal;
}
	
}
