package imdb;
import it.unimi.dsi.fastutil.io.BinIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteRanks {
	public static void main(String[] args) {
		if(args.length != 1){
			System.err.println("Usage: java PrintRanks RANK_FILENAME");
			System.exit(1);
		}

		String basename = args[0];

		double[] ranks=null;
		try {
			ranks = BinIO.loadDoubles(basename);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedWriter bw;
		
		try {
			bw = new BufferedWriter(new FileWriter("results/ranks/"+basename));
			for(double rank: ranks){
				bw.write(String.valueOf(rank));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
