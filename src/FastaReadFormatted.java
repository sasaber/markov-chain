import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class FastaReadFormatted {
	String rawData;
	private ArrayList<String> geneIDs;
	private ArrayList<String> seqs;

	public FastaReadFormatted(String path) throws FileNotFoundException, IOException {
		populateRaw(path);
		populateFormattedStrings();
	}
	public void populateRaw(String path) throws IOException, FileNotFoundException{
		rawData = "";
		FileReader inputStream = null;
		try {
            inputStream = new FileReader(path);

            int c;
            while ((c = inputStream.read()) != -1) {
            	//System.out.println((char)c);
                rawData += (char)c;
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
	}
	
	public void populateFormattedStrings() {
		geneIDs = new ArrayList<String>();
		seqs = new ArrayList<String>();
		String ID;
		String seq;
		String[] geneSplit = rawData.split(">");
		for(String gen: geneSplit) {
			//System.out.println(gen);
			String[] genData = gen.split(" ");
			if(genData.length == 2) {
				ID = genData[0].replace("\n", "").replace(" ", "");
				seq = genData[1].replace("\n", "").replace(" ", "");
				geneIDs.add(ID);
				seqs.add(seq);
			}

		}
			
	}
	public ArrayList<String> getGeneIDs() {
		return geneIDs;
	}
	public ArrayList<String> getSeqs() {
		return seqs;
	}
	
	
}
