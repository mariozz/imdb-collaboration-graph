package imdb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class NodeNeighborsNames {
	
	public static void main (String args[]) throws NumberFormatException, IOException{
		
		if(args.length != 1){
			System.err.println("Usage: java NodeNeighborsNames NODE-ID");
			System.exit(1);
		}	
		
		/* Read pageranks*/
		BufferedReader br = new BufferedReader(new FileReader("results/pageranks/imdbgraphrankpow.res"));
		
		LinkedList<Double> pageranks = new LinkedList<Double>();
		
		String s = br.readLine();
		
		while(s != null){
			pageranks.add(Double.parseDouble(s));
			s = br.readLine();
		}
		br.close();
		
		
		DoubleList dl = Utils.nodeNeighbors(Integer.parseInt(args[0]));
		LinkedList<String> neighborsNames = Utils.personsIDtoNames(dl.getPersonIDList());
		
		
		Iterator<String> itNames = neighborsNames.iterator();
		Iterator<Integer> itNodeID = dl.getNodeIDList().iterator();
		
		LinkedList<Line> list = new LinkedList<Line>();
		
		while(itNodeID.hasNext()){
			list.add(new Line(pageranks.get(itNodeID.next()), itNames.next()));
		}

		Collections.sort(list);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("results/neighborhoods/neighborsOf"+args[0]));
		
		Iterator<Line> it = list.iterator();
		
		while(it.hasNext()){
			bw.write(it.next().toString()+"\n");
		}
		
		bw.close();
		
	}
		
}

