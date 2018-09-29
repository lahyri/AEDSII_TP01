import java.util.ArrayList;
import java.util.Scanner;

public class TP01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo a ser lido: ");
        String nomeArq = scanner.next();

        WordsReader reader = new WordsReader(nomeArq);
        ArrayList<String> words = reader.getArrayOfWords();

        ArvorePatricia patricia = new ArvorePatricia();

        System.out.println("O processo de leitura do arquivo pode demorar um pouco, tenha paciência.");
        for(int i = 0; i < words.size(); i++) 
            patricia.insere(words.get(i));

        while(true) {
            System.out.print("Digite uma palavra: ");
            patricia.pesquisa(scanner.next());
        }
    }
}
