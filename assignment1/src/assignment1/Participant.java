package assignment1;

public abstract class Participant {
	private String id;
	private String name;
	private String state;
	private int age;
	
	public Participant(String id,String name,int age,String state){
		this.id = id;
		this.name = name;
		this.age = age;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
