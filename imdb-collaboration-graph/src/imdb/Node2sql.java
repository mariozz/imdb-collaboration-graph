package imdb;
import java.io.IOException;


public class Node2sql {

	public static void main(String[] args) throws NumberFormatException, IOException{

		if(args.length != 1){
			System.err.println("Usage: java Node2sql NODE-ID");
			System.exit(1);
		}

		System.out.println( Utils.personIDtoName( Utils.nodeIDtoPersonID( (Integer.parseInt(args[0])) ) ) );
	}

}