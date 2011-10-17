package imdb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Nodes2Names {

	public static int nodeIds = 250000;
	
	public static void main(String[] args) throws IOException {
		
		/* List of names */
		LinkedList<String> names = Utils.readNames(nodeIds);
		
		/* Write the list of names into a file */
		BufferedWriter bw = new BufferedWriter(new FileWriter("results/names/names"+nodeIds));
		
		Iterator<String> it = names.iterator();
		
		while(it.hasNext()){
			bw.write(it.next()+"\n");
		}

		bw.close();
	}

}
