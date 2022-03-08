
public class User {
protected int age;
protected String firstName;
protected String lastName;
protected String userName;
protected String password;

public User() {
}

public User(String userName,String password) {
	this.userName = userName;
	this.password = password;
}

public User(String userName,String password, String firstName, String lastName,int age) {
	this(firstName,lastName, password, age);
	this.userName = userName;
}

public User(String firstName,String password,String lastName, int age) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.password = password;
	this.age = age;
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
}
