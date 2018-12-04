import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Markov {

    // member variables
    private int order;                      // order of each n-gram
    private int length;                     // length of text to be generated
    private Model model;                    // Markov model after reading text
    private StringBuilder generatedText;    // generated text using info in model
    private Random randomGenerator;         // to randomly choose n-gram from list of possibilities
    private ArrayList<Integer> spliceSites; // stores the positions of predicted splice sites in a given gene

    // ctor
    public Markov(int order, int length){
        this.order = order;
        this.length = length;
        this.model = new Model();
        this.generatedText = new StringBuilder();
        this.randomGenerator = new Random();
        this.spliceSites = new ArrayList<>();
    }

    // build markov chain
    public void buildChain(String str){
        this.model.readAndBuild(str, this.order);
    }

    // build markov chain from file
    public void buildChainFromFile(String path){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder sequence = new StringBuilder();
            String str = reader.readLine();
            while (str != null){
                sequence.append(str);
                str = reader.readLine();
            }
           // System.out.println(sequence.toString());
            reader.close();
            this.model.readAndBuild(sequence.toString(), this.order);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void predictSpliceSites(String path){
        try{
            // read string from file
            BufferedReader reader = new BufferedReader(new FileReader(path));
            StringBuilder sequence = new StringBuilder();
            sequence.append(reader.readLine());
            // close buffered reader
            reader.close();

            // iterate over input string
            for (int i = 0; i < sequence.length() - order; i++) {
                // extract each n-gram
                String gram = sequence.substring(i, i + order);
                // get the list of characters that come after this n-gram in model
                ArrayList<Character> possibilities = this.model.getDictionary().get(gram);
                // if the char 's' has more than a 15% probability of being the next char
                // then, add the index position to the list of predicted splice sites
                if (Collections.frequency(possibilities, 's') > ((15 * possibilities.size())/100)) {
                    spliceSites.add(i + order);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //Addition by Nikola. Identical in purpose to method above, with two differences: spliceSites is cleared when called, and sequence is taken as an input as opposed to read from a file
    public void predictSpliceSitesFromString(String sequence) {
        spliceSites = new ArrayList<>();
        for (int i = 0; i < sequence.length() - order; i++) {
            // extract each n-gram
            String gram = sequence.substring(i, i + order);
            // get the list of characters that come after this n-gram in model
            ArrayList<Character> possibilities = this.model.getDictionary().get(gram);
            // if the char 's' has more than a 15% probability of being the next char
            // then, add the index position to the list of predicted splice sites
            if (Collections.frequency(possibilities, 's') > ((15 * possibilities.size())/100)) {
                spliceSites.add(i + order);
            }
        }
    }

    // setters and getters
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getGeneratedText() {
        return this.generatedText.toString();
    }

    public ArrayList<Integer> getSpliceSites() {
        return this.spliceSites;
    }
}
