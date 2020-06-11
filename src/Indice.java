import java.util.ArrayList;
import java.util.List;

public class Indice {

    private NodoPalavra headPalavra;
    private NodoPalavra tailPalavra;

    public boolean addPalavraPagina(String palavra, int pagina) {
        if (headPalavra == null) {
            headPalavra = new NodoPalavra(palavra, pagina);
            tailPalavra = headPalavra;
            return true;
        } else if (tailPalavra.getPalavra().equals(palavra)) {
            tailPalavra.addPagina(pagina);
            return true;
        } else if (!tailPalavra.getPalavra().equals(palavra)) {
            tailPalavra.setRefNextPalavra(new NodoPalavra(palavra, pagina));
            tailPalavra = tailPalavra.getRefNextPalavra();
            return true;
        }
        return false;
    }

    public List<Integer> getPaginas(String palavra) {
        NodoPalavra nodoPalavra = findPalavra(palavra);
        return nodoPalavra == null
                ? new ArrayList<>()
                : nodoPalavra.getListaPaginas();
    }

    public List<String> getPalavras(){
        List<String> palavras = new ArrayList();
        NodoPalavra palavra = headPalavra;
        if(palavra == null) return palavras;
        do {
            palavras.add(palavra.getPalavra());
            palavra = palavra.getRefNextPalavra();
        }while (palavra != null);
        return palavras;
    }

    @Override
    public String toString() {
        String aux = "";
        List<String> list = getPalavras();
        for (String palavra : list ) {
            aux = palavra + " {" + getPaginas(palavra) + "}";
            aux = "\n";
        }
        return "Indice{ " + aux +" }";
    }

    private NodoPalavra findPalavra(String palavra) {
        if(headPalavra == null) return null;
        NodoPalavra nodoPalavra = headPalavra;
        while (nodoPalavra != null){
            if (nodoPalavra.getPalavra().equals(palavra)) return nodoPalavra;
            nodoPalavra = nodoPalavra.getRefNextPalavra();
        }
        return null;
    }

    private class NodoPalavra {
        private String palavra;
        private NodoPagina nodePagina;
        private NodoPagina tailPagina;
        private NodoPalavra refNextPalavra;

        public NodoPalavra(String palavra, int pagina) {
            this.palavra = palavra;
            addPagina(pagina);
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