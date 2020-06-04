import java.util.List;

public class Indice {

    private NodoPalavra refHeadPalavra;

    public boolean addPalavraPagina(String palavra, int pagina) {
        return true;
    }

    public int getPaginas(String palavra) {
        return 0;
    }

    private class NodoPalavra {
        private String palavra;
        private NodoPagina refHeadPagina;
        private NodoPalavra refNextPalavra;

        public NodoPalavra(String palavra, NodoPagina refHeadPagina) {
            this.palavra = palavra;
            this.refHeadPagina = refHeadPagina;
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

        public boolean addPagina(int numero){
            // TODO: 03/06/2020
            return true;
        }

        public List<Integer> getListaPaginas(){
            // TODO: 03/06/2020
            return null;
        }
    }

    private class NodoPagina {
        private int pagina;
        private NodoPagina refNextPagina;

        public NodoPagina(int pagina) {
            this.pagina = pagina;
        }

        public int getPagina() {
            return pagina;
        }

        public NodoPagina getRefNextPagina() {
            return refNextPagina;
        }

        public void setRefNextPagina(NodoPagina refNextPagina) {
            this.refNextPagina = refNextPagina;
        }
    }
}