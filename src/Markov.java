import java.lang.reflect.Array;
import java.util.ArrayList;
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

    public void generateText(){
        // get a beginning n-gram
        int index = randomGenerator.nextInt(this.model.getPossibleBeginnings().size());
        String currentGram = this.model.getPossibleBeginnings().get(index);
        this.generatedText.append(currentGram);

        // Generate a new gram from model until you hit the target text length
        for (int i = 0; i < this.length; i++){
            // if the current n-gram is valid (found in model)
            if (this.model.hasGram(currentGram)){
                // get all next possibilities
                ArrayList<Character> possibilities = this.model.getPossibilities(currentGram);

                // choose a random character from the list of possibilities
                index = randomGenerator.nextInt(possibilities.size());
                String next = possibilities.get(index).toString();

                // add to generated text
                this.generatedText.append(next);

                // get length of generated text so far
                int generatedLengthSoFar = this.generatedText.length();
                // update current gram to use it for looking up an n-gram in the next iteration
                currentGram = this.generatedText.substring(generatedLengthSoFar - this.order, generatedLengthSoFar);
            }
            // if the current n-gram is not valid (the chain cannot find more possibilities)
            else {
                break;
            }
        }
    }

    public void predictSpliceSites(String str){

    }

//    // choose a random character from list of possibilities
//    public String getRandomElement(ArrayList<Character> arr) {
//        int index = randomGenerator.nextInt(arr.size());
//        return arr.get(index).toString();
//    }

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
}
