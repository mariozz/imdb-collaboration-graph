import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class PageRankSort {
	
	
	public static void main (String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new FileReader("/Users/mario/imdb-graph-duplicates/powerrank-part.txt"));
		
		
		TreeMap<Double,Integer> m = new TreeMap<Double,Integer>();
		String s = br.readLine();
		int j = 0;
		
		
		while(s !=null){
			m.put(Double.parseDouble(s), j);
			s = br.readLine();
			j++;
		}
		Set<Entry<Double, Integer>>e = m.entrySet();
		Iterator<Entry<Double, Integer>> it = e.iterator();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/mario/imdb-graph-duplicates/powerrank-part-ord.txt"));
		StringTokenizer st;
		
		while(it.hasNext()){
			st= new StringTokenizer(it.next().toString(), "=");
			
			bw.write(st.nextToken()+" "+st.nextToken());
			bw.newLine();
			
		}
		
	}

}
