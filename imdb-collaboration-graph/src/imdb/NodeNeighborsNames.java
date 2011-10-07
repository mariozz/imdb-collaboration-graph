package imdb;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class NodeNeighborsNames {
	
	public static void main (String args[]) throws NumberFormatException, IOException{
		
		if(args.length != 1){
			System.err.println("Usage: java NodeNeighborsNames NODE-ID");
			System.exit(1);
		}
		
		
		LinkedList<String> neighbors = Utils.personsIDtoNames(Utils.nodeNeighbors(Integer.parseInt(args[0])));
		
		Iterator<String> it = neighbors.iterator();
		
		System.out.println("Neighbors of "+ it.next()+" are:");
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		/**/
		
		
		
	}

}
