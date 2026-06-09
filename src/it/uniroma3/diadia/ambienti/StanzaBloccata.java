package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {

	private String attSbloccante;
	private Direzione dirBloccata;

	public StanzaBloccata(String nome, String attSbloccante, String dirBloccata) {
		super(nome);
		this.attSbloccante = attSbloccante;
		this.dirBloccata = Direzione.valueOf(dirBloccata);
	}

	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (direzione.equals(this.dirBloccata)) {
			if (this.hasAttrezzo(this.attSbloccante))
				return this.stanzeAdiacenti.get(direzione);
			else
				return this;
		}
		return this.stanzeAdiacenti.get(direzione);
	}

	@Override
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet()) {
			if (direzione.equals(dirBloccata) && !this.hasAttrezzo(attSbloccante))
				risultato.append(" " + direzione + "->(bloccata)");
			else
				risultato.append(" " + direzione);
		}
		risultato.append("\nAttrezzi nella stanza: ");
		for (Attrezzo a : this.attrezzi)
			risultato.append(a.toString() + " ");
		return risultato.toString();
	}
}