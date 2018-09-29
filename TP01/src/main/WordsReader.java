import java.io.*;
import java.util.*;

public class WordsReader {

    private String filePath;
    private File file;

    public WordsReader(String filePath) {
        this.filePath = filePath;

        this.file = new File(this.filePath);
    }

    private String cleanWord(String word) {
        word = word.replace(",", "");
        word = word.replace(".", "");
        word = word.replace(";", "");
        word = word.replace(":", "");
        word = word.replace("(", "");
        word = word.replace(")", "");
        word = word.replace("{", "");
        word = word.replace("}", "");
        word = word.replace("[", "");
        word = word.replace("]", "");
        word = word.replace("!", "");
        word = word.replace("?", "");
        return word;
    }

    public ArrayList<String> getArrayOfWords() {
        ArrayList<String> words = new ArrayList<String>();
        
        Scanner scan = null;
        try {
            scan = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
        while (scan.hasNextLine()) {
            Scanner scan2 = new Scanner(scan.nextLine());
            while (scan2.hasNext()) {
                String word = scan2.next();
                words.add(this.cleanWord(word));
            }
        }
        
        return words;
    }
}