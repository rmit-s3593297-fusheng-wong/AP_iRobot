package assignment1;

public class Official extends Participant {

	public Official(String id,String name, int age, String state) {
		super(id,name,age,state);
	}
	
	public Athlete[] sumGame(Athlete[] athleteList){
		//Sort Athletes according to finishTimes (Bubble Sort Reference - http://www.java-examples.com/java-bubble-sort-example)
		for(int i=0;i<athleteList.length-1;i++){
			Athlete temp=null;
			for(int j=0;j<athleteList.length-i-1;j++){
				if(athleteList[j].getTime() > athleteList[j+1].getTime()){
					temp=athleteList[j];
					athleteList[j]=athleteList[j+1];
					athleteList[j+1]=temp;
				}
			}
		}
		return athleteList;		
	}
}

