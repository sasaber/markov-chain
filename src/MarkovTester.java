import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MarkovTester {

    public static void main(String[] args){
        Markov markov = new Markov(4, 1000);
        String path = "/Users/sarahsaber/Documents/Academics/Fall18/CS123A/project/MarkovChain/data/trainer.txt";


        markov.buildChainFromFile(path);
        markov.predictSpliceSites("/Users/sarahsaber/Documents/Academics/Fall18/CS123A/project/MarkovChain/data/test.txt");
        System.out.println(markov.getSpliceSites().toString());

        // get actual splice sites and print for comparison
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
//        markov.generateText();
//        System.out.println(markov.getGeneratedText());

    }
}
