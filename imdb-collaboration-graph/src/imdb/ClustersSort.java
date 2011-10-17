package imdb;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class ClustersSort {	
	
	public static int c = 10;
	
	public static void main (String[] args) throws IOException{
		
		/* Read */
		BufferedReader[] cluster = new BufferedReader[c];
		
		new File("results/clusters/clusters"+c).mkdir();
		
		
		for(int i=0; i<cluster.length; i++){
			cluster[i] = new BufferedReader(new FileReader("results/clusters/clusters"+c+"/cluster"+i));
		
			LinkedList<Line> list = new LinkedList<Line>();
		
			/* Covert the lines of imdbgraphpow.res in Line objects and add them to the list*/
			String row = cluster[i].readLine();
			Line l = null;
		
			while(row !=null){
				StringTokenizer st = new StringTokenizer(row, "\t");
				String id = st.nextToken() +" "+st.nextToken();
				Double d = Double.parseDouble(st.nextToken());
				
				l = new Line(d, id );
				list.add(l);
				row = cluster[i].readLine();
			}
		
			cluster[i].close();
		
			/* Order the list*/
			Collections.sort(list);
		
			/* Write the elements of the list in imdbgraphrankpow.ord */
			BufferedWriter bw = new BufferedWriter(new FileWriter("results/clusters/clusters"+c+"/cluster"+i+".ord"));
		
			Iterator<Line> it = list.iterator();
		
			while(it.hasNext()){
				bw.write(it.next().toString()+"\n");
			}
		
			bw.close();
		}
	}

}
