package imdb;

import java.util.LinkedList;

public class DoubleList {
	
	public LinkedList<Integer> nodeIDList;
	public LinkedList<Long> personsIDlist;
	
	public DoubleList(LinkedList<Integer> n, LinkedList<Long> p){
	
		nodeIDList = n;
		personsIDlist = p;
	
	}

	public LinkedList<Integer> getNodeIDList(){
		return nodeIDList;
	}
	
	public LinkedList<Long> getPersonIDList(){
		return personsIDlist;
	}
}
