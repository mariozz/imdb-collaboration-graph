import it.unimi.dsi.fastutil.io.BinIO;
import it.unimi.dsi.webgraph.BVGraph;
import it.unimi.dsi.webgraph.LazyIntIterator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.martiansoftware.jsap.JSAPException;

/**
 * 
 * @author mario
 */
public class Main {

	public static String graph = "/Users/mario/imdb-graph-duplicates/imdbgraph";

	
	public static void main(String[] args) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, JSAPException,
			IOException, ClassNotFoundException {

		/* Load the graph */
		BVGraph g = BVGraph.load(graph);

		System.out.println("nodes: " + g.numNodes());
		System.out.println("randomAccess?: " + g.randomAccess());

		/* Read the ids file */
		long[] idsarray = BinIO.loadLongs((CharSequence) graph + ".ids");

		System.out.println(idsarray[100]);
		System.out.println(idsarray.length);

		/* Retrive all the neighbors of a node*/
		int node = 100;

		long personID = idsarray[node];
		long neighborID = 0;

		LazyIntIterator i1 = g.successors(node);

		System.out.println("Neighbors of " + personID + ":");
		int t;
		while ((t = i1.nextInt()) != -1) {
			neighborID = idsarray[t];
			System.out.println("    " + neighborID);
		}

		/* Given a personID retrieve from the DB the name */
		try {
			/* Load the driver*/
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			
			/* Create the string for the connection */
			String url = "jdbc:mysql://localhost/imdb";
			
			/* Obtain a connection */
			Connection con = DriverManager.getConnection(url, "root", "inter1");
			
			/* Create a statement */
			Statement cmd = con.createStatement();
			
			/* Execute the query and store the result */
			String qry = "SELECT name.name FROM name WHERE name.id = " + personID;
			ResultSet res = cmd.executeQuery(qry);
			
			/* Print the results */
			while (res.next()) {
				System.out.println(res.getString("name"));
			}
			res.close();
			cmd.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}