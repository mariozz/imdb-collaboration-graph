package imdb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Clusters {
	
	public static int nodeIds = 250000;
	public static int c = 10;
	
	public static void main (String[] args) throws IOException{
		
		/* Create the directory that will contains the results */
		new File("results/clusters/clusters"+c).mkdir();
		
		/* Open the file that will contain the clusters */
		BufferedWriter[] cluster = new BufferedWriter[c];
		
		for(int i=0; i<c; i++){
			cluster[i]=new BufferedWriter(new FileWriter("results/clusters/clusters"+c+"/cluster"+i));
		}
		
		/* Read the file with clusters*/
		BufferedReader clusters = new BufferedReader(new FileReader("results/metis/metis"+nodeIds+".graph.part."+c));
		
		/* Read pagerank */
		BufferedReader pagerank = new BufferedReader(new FileReader("results/pageranks/imdbgraphrankpow.res"));
		
		/* List of names */
		LinkedList<String> names = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new FileReader("results/names/names"+nodeIds));
		String n;
		while( (n = br.readLine()) != null){
			names.add(n);
		}
		
		/* According to the content of clusters file place names into the cluster they belong */
		String s;
		int j;
		int k=0;
		while((s = clusters.readLine()) != null){
			j=Integer.parseInt(s);
			cluster[j].write(k+"\t"+names.removeFirst()+"\t"+pagerank.readLine()+"\n");
			k++;
		}
		
		for(int i=0; i<c; i++){
			cluster[i].close();
		}
		
		clusters.close();
	}

}
