
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IndexCreator {
    private static final String REGEX_ONLY_WORDS_PATTERN = "[^a-zA-Z0-9_\\-\\s]";
    private final String fileName;

    public IndexCreator(String fileName) {
        this.fileName = fileName;
    }

    public Index getIndex() {
        Index indice = new Index();
        int numLinhas = 1;
        int numPaginas = 1;


        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), Charset.defaultCharset())) {
            String line = null;
            while ((line = reader.readLine()) != null) {

                String [] words = line.toLowerCase()
                        .replaceAll(REGEX_ONLY_WORDS_PATTERN, "")
                        .trim()
                        .split(" ");

                for (String word : words) {
                    if (word.trim().isEmpty()) continue;
                    indice.addPalavraPagina(word, numPaginas);
                }

                if (++numLinhas % 40 == 0) {
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
