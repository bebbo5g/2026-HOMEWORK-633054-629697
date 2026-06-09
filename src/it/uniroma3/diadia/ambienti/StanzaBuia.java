package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {

    private String attLucente;

    public StanzaBuia(String nome, String attLucente) {
        super(nome);
        this.attLucente = attLucente;
    }

    @Override
    public String getDescrizione() {
        if (this.hasAttrezzo(attLucente))
            return super.toString();
        else
            return "qui c'è buio pesto";
    }

    @Override
    public String toString() {
        return getDescrizione();
    }
}