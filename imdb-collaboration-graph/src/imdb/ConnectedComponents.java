package imdb;
import java.io.IOException;

import it.unimi.dsi.webgraph.BVGraph;
import it.unimi.dsi.webgraph.algo.StronglyConnectedComponents;


public class ConnectedComponents {
	private static String graph = "results/graph/imdbgraph";
	public static void main(String[] args) throws IOException {
		BVGraph g = BVGraph.load(graph);
		StronglyConnectedComponents scc=StronglyConnectedComponents.compute(g, false, null);
		int[]sizes=scc.computeSizes();
		scc.sortBySize(sizes);
		System.out.println(sizes);
	}

}
