/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package jitender;

public abstract class Participant {
	private String id;
	private String name;
	private String state;
	private int age;
	
	//Constructor
	public Participant(String id,String name,int age,String state){
		this.id = id;
		this.name = name;
		this.age = age;
		this.state = state;
	}
	//getter for Id
	public String getId() {
		return id;
	}
	//setter for Id
	public void setId(String id) {
		this.id = id;
	}
	//getter for Name
	public String getName() {
		return name;
	}
	//setter for Name
	public void setName(String name) {
		this.name = name;
	}
	//getter for State
	public String getState() {
		return state;
	}
	//setter for State
	public void setState(String state) {
		this.state = state;
	}
	//getter for Age
	public int getAge() {
		return age;
	}
	//setter for Age
	public void setAge(int age) {
		this.age = age;
	}
}
