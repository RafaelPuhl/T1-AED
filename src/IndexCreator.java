
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IndexCreator {
    private static final String REGEX_ONLY_WORDS_PATTERN = "[,.]";
    private final String fileName;

    public IndexCreator(String fileName) {
        this.fileName = fileName;
    }

    public Index getIndex() {
        Index indice = new Index();
        int numLinhas = 0;
        int numPaginas = 1;


        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), Charset.defaultCharset())) {
            String line = null;
            while ((line = reader.readLine()) != null) {

                for (String word : line.toLowerCase().trim().split(" ")) {
                    indice.addPalavraPagina(word.replaceAll(REGEX_ONLY_WORDS_PATTERN, ""), numPaginas);
                }

                if (++numLinhas % 5 == 0) {
                    numPaginas++;
                }
            }
            return indice;
        } catch (IOException e) {
            System.err.println("Erro na leitura do arquivo: " + e);
            throw new IllegalStateException(e);
        }
    }
}
