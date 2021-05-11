package br.com.local.listapadrinhosmagicos;

public class Temporadas {

    private String temporada;
    private String episodios;
    private String dataLancamento;

    public Temporadas(String temporada, String episodios, String dataLancamento) {
        this.temporada = temporada;
        this.episodios = episodios;
        this.dataLancamento = dataLancamento;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getEpisodios() {
        return episodios;
    }

    public void setEpisodios(String episodios) {
        this.episodios = episodios;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
