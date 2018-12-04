import java.util.ArrayList;
import java.util.HashMap;

public class Model {
    // member variables
    private HashMap<String, ArrayList<Character>> dictionary; // a map of n-grams to list of possible next elements
    private ArrayList<String> possibleBeginnings;

    // ctor
    public Model() {
        dictionary = new HashMap<>();
        possibleBeginnings = new ArrayList<>();
    }

    // read text to build model
    public void readAndBuild(String str, int order){
        // save the first encountered n-gram for a possible beginning
        if (str.length() >= order){
            this.possibleBeginnings.add(str.substring(0, order));
        }

        // build model by iterating over the given text
        for (int i = 0; i < str.length() - order; i++) {
            // extract gram
            String gram = str.substring(i, i + order);
            // get next character
            Character nextChar = str.charAt(i + order);
            // if the gram is not defined in the dictionary
            if (!dictionary.containsKey(gram)){
                this.dictionary.put(gram, new ArrayList<Character>());
            }
            ArrayList<Character> dummy = this.dictionary.get(gram);
            dummy.add(nextChar);
            this.dictionary.put(gram, dummy);
        }
    }

    // overrides Object toString method
    public String toString(){
        StringBuilder s = new StringBuilder();

        for (String key : dictionary.keySet()){
            s.append(key + " " + dictionary.get(key).toString() + "\n");
        }

        return s.toString();
    }

    // returns whether an n-gram is found in the model or not
    public boolean hasGram(String str){
        return this.dictionary.containsKey(str);
    }

    // returns a list of all possible characters that come after a given n-gram
    public ArrayList<Character> getPossibilities(String key){
        if (!this.dictionary.containsKey(key)){
            return null;
        }
        return this.dictionary.get(key);
    }

    // getter
    public ArrayList<String> getPossibleBeginnings() {
        return possibleBeginnings;
    }


    public HashMap<String, ArrayList<Character>> getDictionary() {
        return dictionary;
    }


}
