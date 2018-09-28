import java.io.*;
import java.util.ArrayList;

public class WordsReader {

    private String filePath;
    private File file;
    private FileReader reader;

    public WordsReader(String filePath) {
        this.filePath = filePath;
        this.reader = null;

        this.file = new File(filePath);
    }

    private String convertCharToBinary(int character) {
        String binaryCharacter = Integer.toBinaryString(character);
        
        if(binaryCharacter.length() < 8) {
            int dif = 8 - binaryCharacter.length();
            for(int i = 0; i < dif; i++) 
                binaryCharacter = "0" + binaryCharacter;
        }

        return binaryCharacter;
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
                    binaryWord += this.convertCharToBinary(character);
                    counter++;
                }
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}