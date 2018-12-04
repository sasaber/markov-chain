import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MarkovTester {

    public static Markov markov = new Markov(4, 1000);
    public static String path = "/Users/sarahsaber/Documents/Academics/Fall18/CS123A/project/MarkovChain/data/trainer.txt";

    public static void printActualSpliceSites(){
        try{
            ArrayList<Integer> spliceSites = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder sequence = new StringBuilder();
            String str = reader.readLine();
            while (str != null){
                sequence.append(str);
                str = reader.readLine();
            }
            //System.out.println(sequence.toString());
            reader.close();
            for (int i = 0; i < sequence.length(); i++){
                if (sequence.charAt(i) == 's'){
                    spliceSites.add(i);
                }
            }
            System.out.println();
            System.out.println(spliceSites.toString());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){

        try {
            FastaReadFormatted data = new FastaReadFormatted("/Users/sarahsaber/Documents/Academics/Fall18/CS123A/project/MarkovChain/data/genesFastaFormat.txt");
            ArrayList<String> seqs = data.getSeqs();
            ArrayList<String> Ids = data.getGeneIDs();
            String ID;
            String seq;
            markov.buildChainFromFile(path);
            for(int i = 0; i < Ids.size(); i++) {
                ID = Ids.get(i);
                seq = seqs.get(i);

                System.out.println("Predicted splice sites for: " + ID);
//			System.out.println(seq);
                 markov.predictSpliceSitesFromString(seq);
                System.out.println(markov.getSpliceSites().toString());
            }
        } catch (IOException e) {
            e.getMessage();
        }

        // get actual splice sites and print for comparison
        // printActualSpliceSites();

    }
}
