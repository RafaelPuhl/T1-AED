import java.util.ArrayList;
import java.util.List;

public class Indice {

    private NodoPalavra headPalavra;
    private NodoPalavra tailPalavra;

    public boolean addPalavraPagina(String palavra, int pagina) {
        return true;
    }

    public int getPaginas(String palavra) {
        return 0;
    }

    private class NodoPalavra {
        private String palavra;
        private NodoPagina nodePagina;
        private NodoPagina tailPagina;
        private NodoPalavra refNextPalavra;

        public NodoPalavra(String palavra, NodoPagina refHeadPagina) {
            this.palavra = palavra;
            this.nodePagina = refHeadPagina;
        }

        public NodoPalavra getRefNextPalavra() {
            return refNextPalavra;
        }

        public void setRefNextPalavra(NodoPalavra refNextPalavra) {
            this.refNextPalavra = refNextPalavra;
        }

        public String getPalavra() {
            return palavra;
        }

        public boolean addPagina(int numeroPagina) {
            if (nodePagina == null) {
                nodePagina = new NodoPagina(numeroPagina);
                tailPagina = nodePagina;
                return true;
            } else if (tailPagina.getPagina() != numeroPagina) {
                NodoPagina newPagina = new NodoPagina(numeroPagina);
                tailPagina.setRefnextpagina(newPagina);
                tailPagina = newPagina;
                return true;
            } else {
                return false;
            }
        }

        public List<Integer> getListaPaginas() {
            List<Integer> paginas = new ArrayList<>();
            NodoPagina pagina = nodePagina;
            if (pagina == null) return paginas;
            do {
                paginas.add(pagina.getPagina());
                pagina = pagina.getNextpagina();
            } while (pagina != null);
            return paginas;
        }
    }

    private class NodoPagina {
        private int pagina;
        private NodoPagina refnextpagina;

        public NodoPagina(int pagina) {
            this.pagina = pagina;
        }

        public int getPagina() {
            return pagina;
        }

        public void setRefnextpagina(NodoPagina refnextpagina) {
            this.refnextpagina = refnextpagina;
        }

        public NodoPagina getNextpagina() {
            return refnextpagina;
        }
    }
}