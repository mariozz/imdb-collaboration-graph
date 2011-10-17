package imdb;

import it.unimi.dsi.webgraph.BVGraph;
import it.unimi.dsi.webgraph.LazyIntIterator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Node2Metis {

	private static String graph = "results/graph/imdbgraph";
	private static int nodeIds = 250000;

	/* Return the list of names and nodeIDs of neighbors of nodeID */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {

		/* Load the graph */
		BVGraph g = BVGraph.load(graph);

		/* Graph parameters */
		int nodes = nodeIds;
		long arcs = 27996546;

		/* Open the file where the graph will be written */
		BufferedWriter bw = new BufferedWriter(new FileWriter("results/metis/metis" + nodeIds + ".graph"));

		/* Write graph parameters on the first line of the file */
		bw.write(nodes + " " + arcs + "\n");

		/* For every node calculate its neighborhood and write it to file */
		Iterator it = g.nodeIterator();
		LazyIntIterator neighborsIt = null;
		int nodeneighborID;

		for (int i = 0; i < nodeIds; i++) {
			if (it.hasNext()) {
				neighborsIt = g.successors((Integer) it.next());

				while ((nodeneighborID = neighborsIt.nextInt()) != -1) {
					/* For metis nodeID starts from 1 */
					if (nodeneighborID >= 0 & nodeneighborID < nodeIds)
						bw.write((nodeneighborID + 1) + " ");
				}
				bw.write("\n");
			}
		}

		bw.close();
		
	}

}