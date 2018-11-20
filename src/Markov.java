public class Markov {

    // member variables
    private int order;                      // order of each n-gram
    private int length;                     // length of text to be generated
    private Model model;                    // Markov model after reading text
    private StringBuilder generatedText;    // generated text using info in model

    // ctor
    public Markov(int order, int length){
        this.order = order;
        this.length = length;
        this.model = new Model();
        this.generatedText = new StringBuilder();
    }

    // build markov chain
    public void buildChain(String str){
        this.model.readAndBuild(str, this.order);
    }

    public String generateText(){

        // Generate a new gram with the specified length
        for (int i = 0; i < this.length; i++){

        }
        return this.generatedText.toString();
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
}
