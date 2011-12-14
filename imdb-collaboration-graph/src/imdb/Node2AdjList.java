package imdb;

import it.unimi.dsi.webgraph.BVGraph;
import it.unimi.dsi.webgraph.LazyIntIterator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Node2AdjList {

	private static String graph = "results/graph/imdbgraph";
	private static int nodeIds = 1000;

	/* Return the list of names and nodeIDs of neighbors of nodeID */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {

		/* Load the graph */
		BVGraph g = BVGraph.load(graph);

		/* Open the file where the graph will be written */
		BufferedWriter bw = new BufferedWriter(new FileWriter("results/viz/adjlist.txt"));

		/* For every node calculate its neighborhood and write it to file */
		Iterator it = g.nodeIterator();
		LazyIntIterator neighborsIt = null;
		int nodeneighborID;
		int source;

		for (int i = 0; i < nodeIds; i++) {
			if (it.hasNext()) {
				source = (Integer)it.next();
				bw.write(source+" ");
				neighborsIt = g.successors(source);

				while ((nodeneighborID = neighborsIt.nextInt()) != -1) {
					/* For metis nodeID starts from 1 */
					if (nodeneighborID < nodeIds)
						bw.write((nodeneighborID) + " ");
				}
				bw.write("\n");
			}
		}

		bw.close();
		
	}

}