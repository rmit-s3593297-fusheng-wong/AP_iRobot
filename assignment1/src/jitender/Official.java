/*****Author - Jitender Singh Padda***********
 *****Student Id - 3628144************************/
package jitender;

import java.util.ArrayList;
import java.util.Collections;

public class Official extends Participant {
	
	//Constructor
	public Official(String id,String name, int age, String state) {
		super(id,name,age,state);
	}
	public ArrayList<Athlete> sumGame(ArrayList<Athlete> athleteList){
		//Using Collections.sort to sort athleteList as Athlete implements Comparator
		Collections.sort(athleteList);
		return athleteList;//sorted AthleteList in ascending order of their Time		
	}
}

