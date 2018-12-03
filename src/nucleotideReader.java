import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class nucleotideReader {
	private HashMap<String, String> geneSequences;
	private String rawData;
	private String[] splitData;
	
	
	public nucleotideReader(String path) throws IOException, FileNotFoundException{
		rawData = "";
		FileReader inputStream = null;
		int counter = 0;
		geneSequences = new HashMap<String, String>();
        try {
            inputStream = new FileReader(path);

            int c;
            while ((c = inputStream.read()) != -1) {
            	//System.out.println((char)c);
                rawData += (char)c;
                counter++;
                if (counter % 100000 == 0) {
                	System.out.println(counter);
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
//        System.out.println("populating map");
        System.out.println(rawData);
        splitData = rawData.split("\n");
        System.out.println(splitData.length);
//        for(String s: splitData) {
//        	System.out.println(s);
//        	System.out.println("item");
//        }
        this.populateMap(splitData);
	}
	
	public void populateMap(String[] data) {

		String[] seq;
		for(int i = 0; i < data.length; i ++) {
			seq = data[i].split(" ");
			geneSequences.put(seq[0], seq[1]);
		}
	}
	
	
	public HashMap<String, String> getGeneSequences() {
		return geneSequences;
	}

	public String getRawData() {
		return rawData;
	}

	public String[] getSplitData() {
		return splitData;
	}
	
	public static String insertSpliceToFile(HashMap<String, String> nucleotide, HashMap<String, ArrayList<Integer>> splice, String path) throws IOException{
		FileWriter spliceWriter = new FileWriter(path);
		String sequence;
		ArrayList<Integer> sites;
		int prevSite;
		int thisSite;
		String builtString = "";
		String spliceChar = "s";
		for(String key: nucleotide.keySet()) {
			sequence = nucleotide.get(key);
			sites = splice.get(key);
			prevSite = 0;
			for(int i = 0; i < sites.size(); i++) {
				thisSite = sites.get(i) - 1; //compensate for 1 vs 0 indexing
				spliceWriter.write(sequence.substring(prevSite, thisSite));
				spliceWriter.write(spliceChar);
				builtString += sequence.substring(prevSite, thisSite);
				builtString += spliceChar;
				prevSite = thisSite;
			}
		}	
		spliceWriter.close();
		return builtString;
	}
//	public static void main(String[] args) throws IOException, FileNotFoundException{
//
//		nucleotideReader reader = new nucleotideReader("C:\\Bioinformatics\\trainers\\seqsTrimmed.nohomologs");
//		System.out.println(reader.getGeneSequences());
//		siteReader splicer = new siteReader("C:\\Bioinformatics\\trainers\\exons.dat.nohomologs");
//		insertSpliceToFile(reader.getGeneSequences(), splicer.getGeneSites(),"C:\\Bioinformatics\\projOutputs\\trainer.txt");
//
//	}
}
