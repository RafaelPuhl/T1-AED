
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Leitura {

    public Indice getIndice() {
        Indice indice = new Indice();
        int numLinhas = 0;
        int numPaginas = 1;

        Path path1 = Paths.get("teste.txt");// java.txt

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                linhas[numLinhas] = line;
                numLinhas++;
                if (numLinhas % 40 == 0) {
                    numPaginas++;
                }
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

        int n = 0;
        for (int i = 1; i <= numPaginas; i++) {
            System.out.println("--------------------- Pagina " + i + " ---------------------");
            for (int j = 0; j < 40 && n < numLinhas; j++, n++) {
                System.out.println(linhas[n]);
            }
        }
        // TODO: 15/06/2020
        for (int i = 1; i <= 10; i++) {
            System.out.println("\nPalavras da linha " + i + ": ");
            linhas[i-1] = linhas[i-1].replaceAll("\\t"," "); // substitui tab por espaco em branco
            linhas[i-1] = linhas[i-1].replaceAll(",",""); // para remover vírgulas
            String[] tokens = linhas[i-1].split(" "); // divide a string pelo espaco em branco
            for (String s : tokens) {
                s = s.toLowerCase();
                System.out.println("->" + s);
            }
        }
        return indice;
    }
}
