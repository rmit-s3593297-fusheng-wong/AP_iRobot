/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package jitender;

public class Official extends Participant {
	
	//Constructor
	public Official(String id,String name, int age, String state) {
		super(id,name,age,state);
	}
	
	public Athlete[] sumGame(Athlete[] athleteList){
		//using Bubble Sort to sort athleteList in ascending order of their Time
		for(int i=0;i<athleteList.length-1;i++){
			Athlete temp=null;
			for(int j=0;j<athleteList.length-i-1;j++){
				//Exception handling for possible Null references in array
				try{	
					if(athleteList[j].getTime() > athleteList[j+1].getTime()){
						temp=athleteList[j];
						athleteList[j]=athleteList[j+1];
						athleteList[j+1]=temp;
					}
				}
				catch(Exception NullPointerException){
					continue;
				}
			}
		}
		return athleteList;//sorted AthleteList in ascending order of their Time		
	}
}

