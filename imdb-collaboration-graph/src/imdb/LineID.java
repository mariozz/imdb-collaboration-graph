package imdb;

public class LineID {
	
	public long personID;
	public int nodeID;
	
	public LineID (long p, int i){
		personID = p;
	    nodeID = i;
	}

	public long getPersonID(){
		return personID;
	}
	
	public int getNodeID(){
		return nodeID;
	}
	
	public String toString(){
		return personID +" "+ nodeID; 
	}
}
