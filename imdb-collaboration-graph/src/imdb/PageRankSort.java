package imdb;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;


public class PageRankSort {	
	
	public static void main (String[] args) throws IOException{
		
		/* Read from imdbgraphpow.res (ordered by nodeID)*/
		BufferedReader br = new BufferedReader(new FileReader("results/pageranks/imdbgraphrankpow.res"));
		
		LinkedList<Line> list = new LinkedList<Line>();
		
		/* Covert the lines of imdbgraphpow.res in Line objects and add them to the list*/
		String s = br.readLine();
		int j = 0;
		Line l = null;
		
		while(s !=null){
			
			l = new Line(Double.parseDouble(s), j );
			list.add(l);
			s = br.readLine();
			j++;
		}
		br.close();
		
		/* Order the list*/
		Collections.sort(list);
		
		/* Write the elements of the list in imdbgraphrankpow.ord */
		BufferedWriter bw = new BufferedWriter(new FileWriter("results/pageranks/imdbgraphrankpow.ord"));
		
		Iterator<Line> it = list.iterator();
		
		while(it.hasNext()){
			bw.write(it.next().toString()+"\n");
		}
		
		bw.close();
	}

}
