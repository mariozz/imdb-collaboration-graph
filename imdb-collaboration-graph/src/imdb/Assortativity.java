package imdb;

import it.unimi.dsi.webgraph.BVGraph;
import it.unimi.dsi.webgraph.LazyIntIterator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Assortativity {

	private static String graph = "results/graph/imdbgraph";

	public static void main(String[] args) throws IOException {
		
		/* Read pagerank file */
		BufferedReader br = new BufferedReader(new FileReader("results/pageranks/imdbgraphrankpow.res"));
		
		/* Write assortativity file */
		BufferedWriter bw = new BufferedWriter(new FileWriter("results/assortativity/assortativity"));

		/* Load the graph */
		BVGraph g = BVGraph.load(graph);
		int nodeNum = g.numNodes();
		
		/* Create pagerank's array */
		double[] pagerankArray = new double[nodeNum];

		String s;
		int i = 0;
		while ((s = br.readLine()) != null) {
			pagerankArray[i] = Double.parseDouble(s);
			i++;
		}

		/* Calculate the average assortativity coefficient  */
		double totalAssortativityCoeff = 0;
		double averageAssortativityCoeff = 0;
		
		
		/* For every node calculate the node assortativity coefficient */
		for (int j = 0; j < g.numNodes(); j++) {
			LazyIntIterator it = g.successors(j);

			int nodeneighborID;
			int neighbornum = 0;
			double ranksum = 0;
			double averageNeighborRank = 0;
			
			/* For every neighbor of a node calculate the rank sum */
			while ((nodeneighborID = it.nextInt()) != -1) {
				ranksum = ranksum + pagerankArray[nodeneighborID];
				neighbornum++;
			}
			
			/* Calculate the node assortativity coefficient */
			double nodeAssortativityCoeff = 0;
			
			if(neighbornum!=0){
				averageNeighborRank = ranksum / neighbornum;
				nodeAssortativityCoeff = pagerankArray[j] / averageNeighborRank;
			}
			
			/* Write the node assortativity coefficient to file */
			bw.write(nodeAssortativityCoeff+"\n");
			
			/* Calculate the total assortativity coefficient */
			totalAssortativityCoeff = totalAssortativityCoeff + nodeAssortativityCoeff;
		}
		
		/* Calculate the average assortativity coefficient */
		averageAssortativityCoeff = totalAssortativityCoeff / nodeNum;
		
		/* Write average assortativity value to a file */
		BufferedWriter aw = new BufferedWriter(new FileWriter("/results/assortativity/assortativityCoeff"));
		aw.write(String.valueOf(averageAssortativityCoeff));
		
		aw.close();
		br.close();
		bw.close();
	}

}
