import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class siteReader {
	private HashMap<String, ArrayList<Integer>> geneSites;
	private String rawData;
	private String[] splitData;
	
	public siteReader(String path) throws IOException, FileNotFoundException{
		//rawFile = new File("C:\\Bioinformatics\\genSplicer\\GeneSplicer.tar\\training_data_sets\\Arabidopsis\\exons.dat.nohomologs");
		rawData = "";
		FileReader inputStream = null;
		geneSites = new HashMap<String, ArrayList<Integer>>();
        try {
            inputStream = new FileReader(path);

            int c;
            while ((c = inputStream.read()) != -1) {
                rawData += (char)c;
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
//        System.out.println("populating map");
//        System.out.println(rawData);
        splitData = rawData.split("\n");
//        for(String s: splitData) {
//        	System.out.println(s);
//        	System.out.println("item");
//        }
        this.populateMap(splitData);
	}
	
//	public String[] preformatData() {
//		String formattedString = "";
//		char c;
//		char nextC;
//		for (int i = 0; i < (rawData.length()-1); i++){
//			c = rawData.charAt(i);
//			nextC = rawData.charAt(i+1);
//			formattedString += c;
//			if (Character.isDigit(c) && Character.isLetter(nextC)) {
//				formattedString += " ";
//			}
//		}
//		String[] itemList = formattedString.split(" ");
////		for(String s:itemList) {
////			System.out.print(s);
////			System.out.print(" ");
////		}
//		return itemList;
//	}
	public void populateMap(String[] items) {
		String key;
		int siteA;
		int siteB;
		String[] item;
		for(int i = 0; i < (items.length -1); i ++) {
			item = items[i].split(" ");
			if(item.length == 3) {
			
			for(String o: item) {
				System.out.print(o);
				System.out.print(" ");
			}
			System.out.println(item.length);
			key = item[0];
			siteA = Integer.parseInt(item[1]);
			siteB =  Integer.parseInt(item[2]);
//			System.out.println(key);
//			System.out.println(siteA);
//			System.out.println(siteB);
			if(geneSites.containsKey(key)) {
				geneSites.get(key).add(siteA);
				geneSites.get(key).add(siteB);
			} else {
				ArrayList<Integer> spliceSites = new ArrayList<Integer>();
				spliceSites.add(siteA);
				spliceSites.add(siteB + 1);
				geneSites.put(key, spliceSites);
			}
		}

		}
	}
	
	
	public HashMap<String, ArrayList<Integer>> getGeneSites() {
		return geneSites;
	}


	public String getRawData() {
		return rawData;
	}


	public static void main(String[] args) throws IOException, FileNotFoundException{
		siteReader reader = new siteReader("C:\\Bioinformatics\\genSplicer\\GeneSplicer.tar\\training_data_sets\\Arabidopsis\\exons.dat.nohomologs");
		System.out.println(reader.getGeneSites());
	}
}
