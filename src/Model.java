import java.util.ArrayList;
import java.util.HashMap;

public class Model {

    // member variables
    private HashMap<String, ArrayList<Character>> dictionary; // a map of n-grams to list of possible next elements

    // ctor
    public Model() {
        dictionary = new HashMap<>();
    }

    // read text to build model
    public void readAndBuild(String str, int order){
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

    // abcdef   order = 3       length = 6
    // abc
    // bcd
    // cde
    // def


}
