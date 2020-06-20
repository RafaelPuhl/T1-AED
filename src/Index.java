import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Index {

    private NodoPalavra headPalavra;
    private NodoPalavra tailPalavra;

    public boolean addPalavraPagina(String palavra, int pagina) {
        if (headPalavra == null) {
            headPalavra = new NodoPalavra(palavra, pagina);
            tailPalavra = headPalavra;
            return true;
        } else {
            NodoPalavra nodoPalavra = findPalavra(palavra);
            if (nodoPalavra != null) {
                return nodoPalavra.addPagina(pagina);
            }
            return insertOnAlphabeticOrder(new NodoPalavra(palavra, pagina));
        }
    }

    private boolean insertOnAlphabeticOrder(NodoPalavra toInsert) {
        if (headPalavra.getPalavra().compareTo(toInsert.getPalavra()) > 0) {
            toInsert.setRefNextPalavra(headPalavra);
            headPalavra = toInsert;
            return true;
        } else {
            return insertOnAlphabeticOrder(headPalavra, headPalavra.getRefNextPalavra(), toInsert);
        }
    }

    private boolean insertOnAlphabeticOrder(NodoPalavra before, NodoPalavra after, NodoPalavra toInsert) {
        if (after == null) {
            before.setRefNextPalavra(toInsert);
            return true;
        } else if (after.getPalavra().compareTo(toInsert.getPalavra()) > 0) {
            toInsert.setRefNextPalavra(after);
            before.setRefNextPalavra(toInsert);
            return true;
        } else {
            return insertOnAlphabeticOrder(after, after.getRefNextPalavra(), toInsert);
        }
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
        StringBuilder aux = new StringBuilder();
        List<String> list = getPalavras();
        for (String palavra : list ) {
            aux.append(palavra).append(" {").append(getPaginas(palavra)).append("}");
            aux.append("\n");
        }
        return "Indice{ palvras=" + aux +" }";
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

        @Override
        public String toString() {
            return "NodoPalavra{" +
                    "palavra='" + palavra + '\'' + ',' +
                    "Paginas=" + getListaPaginas() +
                    '}';
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