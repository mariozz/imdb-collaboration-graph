package imdb;

import it.unimi.dsi.fastutil.io.BinIO;
import it.unimi.dsi.webgraph.BVGraph;
import it.unimi.dsi.webgraph.LazyIntIterator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

public class Utils {

	private static String graph = "results/graph/imdbgraph";

	/* Return the list of names and nodeIDs of neighbors of nodeID */
	public static DoubleList nodeNeighbors(int nodeID) throws IOException {
		
		/* List of the PersonID of the neighbors of NodeID */
		LinkedList<Long> neighborsPersonID = new LinkedList<Long>();
		
		/* List of the NodeID of the neighbors of NodeID */
		LinkedList<Integer> neighborsNodeID = new LinkedList<Integer>();
		
		/* Load the graph */
		BVGraph g = BVGraph.load(graph);

		/* Read the ids file */
		long[] idsArray = BinIO.loadLongs((CharSequence) graph + ".ids");
		
		/* Retrieve all the neighbors of a node*/
		long personID = idsArray[nodeID];
		neighborsNodeID.add(nodeID);
		neighborsPersonID.add(personID);
		
		long neighborID = 0;

		LazyIntIterator i = g.successors(nodeID);

		int nodeneighborID;
		while ((nodeneighborID = i.nextInt()) != -1) {
			neighborID = idsArray[nodeneighborID];
			neighborsNodeID.add(nodeneighborID);
			neighborsPersonID.add(neighborID);
		}
		
		return new DoubleList(neighborsNodeID, neighborsPersonID);
	}
		
	/* Given a nodeID in Webgraph return a personID */
	public static long nodeIDtoPersonID(int nodeID) throws IOException {

		/* Read the ids file */
		long[] idsarray = BinIO.loadLongs((CharSequence) graph + ".ids");

		/* Retrieve all the neighbors of a node */
		long personID = idsarray[nodeID];
		
		return personID;
	}

	/* Given a personID query the DB and print a Name*/
	public static String personIDtoName(long personID) {
		/* Given a personID retrieve from the DB the name */
		try {
			/* Load the driver */
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);

			/* Create the string for the connection */
			String url = "jdbc:mysql://localhost/imdb";

			/* Obtain a connection */
			Connection con = DriverManager.getConnection(url, "root", "ingdis");

			/* Create a statement */
			Statement cmd = con.createStatement();

			/* Execute the query and store the result */
			String qry = "SELECT name.name FROM name WHERE name.id = "
					+ personID;
			ResultSet res = cmd.executeQuery(qry);

			/* Print the results */
			while (res.next()) {
				//System.out.println(res.getString("name"));
				return res.getString("name");
			}
			res.close();
			cmd.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static LinkedList<String> personsIDtoNames(LinkedList<Long> personsID) {
		
		LinkedList<String> neighborsNames = new LinkedList<String>(); 
		
		/* Given a personID retrieve from the DB the name */
		try {
			/* Load the driver */
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);

			/* Create the string for the connection */
			String url = "jdbc:mysql://localhost/imdb";

			/* Obtain a connection */
			Connection con = DriverManager.getConnection(url, "root", "ingdis");

			/* Create a statement */
			Statement cmd = con.createStatement();

			/**/
			Iterator<Long> it = personsID.iterator();
			
			/* Execute the query and store the result */
			String qry;
			ResultSet res;
			
			while (it.hasNext()){
				
				qry = "SELECT name.name FROM name WHERE name.id = " + it.next();
				//System.out.println(qry);
				res = cmd.executeQuery(qry);
				res.next();
				neighborsNames.add(res.getString("name"));
				res.close();
			}
			
			cmd.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return neighborsNames;
	}
	
	public static LinkedList<String> readNames(int nodeIds) throws IOException{
		
		/* Create the list of persons ids */
		long[] idsArray = BinIO.loadLongs("results/graph/imdbgraph.ids");
		LinkedList<Long> personsID = new LinkedList<Long>();
		
		/* Read the first 'nodeIds' ids and store them into a list*/
		for(int i=0; i<nodeIds; i++){
			personsID.add(idsArray[i]);
		}
		
		/* Read from the DB and convert persons ids into names*/
		LinkedList<String> names = Utils.personsIDtoNames(personsID);
		
		return names;
	}
	
	public static void main(String args[]) throws IOException{
		/* Create the list of persons ids */
		long[] idsArray = BinIO.loadLongs("results/graph/imdbgraph.ids");
		BufferedWriter bw = new BufferedWriter(new FileWriter("results/graph/imdbgraph.ids.txt"));
		
		for(int i=0; i<idsArray.length; i++){
			bw.write(idsArray[i]+"\n");
		}
	}
}
