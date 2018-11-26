public class MarkovTester {

    public static void main(String[] args){
        Markov markov = new Markov(3, 1000);
        //String s = "It was another early sunset on a rainy day in Seattle. Andrew was walking with a paper bag of groceries back to his downtown studio apartment. He had gotten government permission to stay in this apartment as part of the Upbeat program, focusing on bettering drug-affected neighborhoods in King County. It was an artist supplement program, and Andrew was a published haiku poet and teacher of haiku in public education and sometimes colleges. He had to attend an audition for artistic achievements to be able to get the subsidy. Though it was miracle to live in downtown Seattle for $800 a month, it did have its downsides, with junkies visiting his weekly exhibitions of his haiku and accompanying minimalist art made by his neighbor, Patrick.";
//        String s = "It was another early sunset on a rainy day in Seattle. Andrew was walking with a paper bag of groceries back to his downtown studio apartment. He had gotten government permission to stay in this apartment as part of the Upbeat program, focusing on bettering drug-affected neighborhoods in King County. It was an artist supplement program, and Andrew was a published haiku poet and teacher of haiku in public education and sometimes colleges. He had to attend an audition for artistic achievements to be able to get the subsidy. Though it was miracle to live in downtown Seattle for $800 a month, it did have its downsides, with junkies visiting his weekly exhibitions of his haiku and accompanying minimalist art made by his neighbor, Patrick.\n" +
//                "\n" +
//                "Like a true Seattleite, he had no umbrella today—just a polyester jacket to wade off the rain. He was gripping his paper grocery bag with a regret that he tried to be so environmentally friendly, as his groceries seemed to be slipping away from him, slowly being soaked. Nearing the King County courthouse, he saw a young woman in the distance, shivering. She was sitting on the courtyard grass, despondently staring down, wearing a hoodie. There was cardboard sign on her left that read, “Anything will help.”\n" +
//                "\n" +
//                "Thoughts began to run through Andrew’s mind. Where is she going to sleep? Isn’t she going to get hypothermia? Is she sick, because she is not even holding up her sign, or is she just depressed? But as he came a few feet from her, his thoughts calmed down and he simply had a feeling of wanting to help her in some way.";
        String s = "ATGGAGGATCAAGTTGGGTTTGGGTTCCGTCCGAACGACGAGGAGCTCGTTGGTCACTATCTCCGTAACAAAATCGAAGG" +
                "AAACACTAGCCGCGACGTTGAAGTAGCCATCAGCGAGGTCAACATCTGTAGCTACGATCCTTGGAACTTGCGCTTCCAGT" +
                "CAAAGTACAAATSCGAGAGATGCTATGTGGTACTTCTTCTCTCGTAGAGAAAACAACAAAGGGAATCGACAGAGCAGGACA" +
                "ACGGTTTCTGGTAAATGGAAGCTTACCGGAGAATCTGTTGAGGTCAAGGACCAGTGGGGATTTTGTAGTGAGGGCTTTCG" +
                "TGGTAAGATTGGTCATAAAAGGGTTTTGGTGTTCCTCGATGGAAGATACCCTGACAAAACCAAATCTGATTGGGTTATCC" +
                "ACGAGTTCCACTACGACCTCTTACCAGAACATCAGAGGACATATGTCATCTGCAGACTTGAGTACAAGGGTGATGATGCG" +
                "GACATTCTATCTGCTTATGCAATAGATCCCACTCCCGCTTTTGTCCCCAATATGACTAGTAGTGCAGGTTCTGTGGTCAA" +
                "CCAATCACGTCAACGAAATTCAGGATCTTACAACACTTACTCTGAGTATGATTCAGCAAATCATGGCCAGCAGTTTAATG" +
                "AAAACTCTAACATTATGCAGCAGCAACCACTTCAAGGATCATTCAACCCTCTCCTTGAGTATGATTTTGCAAATCACGGC" +
                "GGTCAGTGGCTGAGTGACTATATCGACCTGCAACAGCAAGTTCCTTACTTGGCACCTTATGAAAATGAGTCGGAGATGAT" +
                "TTGGAAGCATGTGATTGAAGAAAATTTTGAGTTTTTGGTAGATGAAAGGACATCTATGCAACAGCATTACAGTGATCACC" +
                "GGCCCAAAAAACCTGTGTCTGGGGTTTTGCCTGATGATAGCAGTGATACTGAAACTGGATCAATGATTTTCGAAGACACT" +
                "TCGAGCTCCACTGATAGTGTTGGTAGTTCAGATGAACCGGGCCATACTCGTATAGATGATATTCCATCATTGAACATTAT" +
                "TGAGCCTTTGCACAATTATAAGGCACAAGAGCAACCAAAGCAGCAGAGCAAAGAAAAGGTGATAAGTTCGCAGAAAAGCG" +
                "AATGCGAGTGGAAAATGGCTGAAGACTCGATCAAGATACCTCCATCCACCAACACGGTGAAGCAGAGCTGGATTGTTTTG" +
                "GAGAATGCACAGTGGAACTATCTCAAGAACATGATCATTGGTGTCTTGTTGTTCATCTCCGTCATTAGTTGGATCATTCT" +
                "TGTTGGTTAA";
        markov.buildChain(s);
        System.out.println(markov.getModel().toString());
        markov.generateText();
        System.out.println(markov.getGeneratedText());
    }
}
