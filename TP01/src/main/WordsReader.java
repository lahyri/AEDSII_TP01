import java.io.*;
import java.util.ArrayList;

public class WordsReader {

    private String filePath;
    private File file;
    private FileReader reader;

    public WordsReader(String filePath) {
        this.filePath = filePath;
        this.reader = null;

        this.file = new File(this.filePath);
    }

    public ArrayList<String> getArrayOfWords() {
        ArrayList<String> words = new ArrayList<String>();
        
        try {
            this.reader = new FileReader(this.file);
            
            int counter = 1;
            int character;
            String binaryWord = "";

            while ((character = this.reader.read()) != -1) {
                if(((char)character) == ' ') {
                    words.add(binaryWord);
                    binaryWord = "";
                    counter = 0;
                } else if(counter < 16) {
                    binaryWord += (char)character;
                    counter++;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}