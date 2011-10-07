package imdb;

public class Line implements Comparable<Line> {
	
	public Double pagerank;
	public Object id;
	
	public Line (Double p, Object i){
		pagerank = p;
		id = i;
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
		return pagerank +" "+ id; 
	}
}
