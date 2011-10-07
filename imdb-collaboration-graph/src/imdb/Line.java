package imdb;

public class Line implements Comparable<Line> {
	
	public Double pagerank;
	public Integer nodeID;
	
	public Line (Double p, Integer n){
		pagerank = p;
		nodeID = n;
	}
	
	
	public int compareTo(Line l){
		
		if(this.pagerank > l.pagerank){
			return 1;
		}
		if(this.pagerank < l.pagerank){
			return -1;
		}
		
		return 0;
	}

	public String toString(){
		return pagerank +" "+ nodeID; 
	}
}
