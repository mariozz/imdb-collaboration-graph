package imdb;
import it.unimi.dsi.fastutil.io.BinIO;

public class PrintRanks {

	public static void main(String [] args) throws Exception {

		// The user must provide the  name of the file containing the pagerank scores
		if(args.length != 1){
			System.err.println("Usage: java PrintRanks RANK_FILENAME");
			System.exit(1);
		}

		String basename = args[0];

		double [] ranks = BinIO.loadDoubles(basename);

		for(double rank: ranks){

			System.out.println(rank);

		}

	}
}
