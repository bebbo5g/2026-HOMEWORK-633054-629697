package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class Strega extends AbstractPersonaggio {
	private static final String MESSAGGIO_PERMALOSA = "Non mi hai salutata! Ti mando nella stanza più povera!";
	private static final String MESSAGGIO_GENTILE = "Sei stato gentile! Ti mando nella stanza più ricca!";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
		Stanza destinazione;

		if (!this.isSalutato()) {
			// non salutata → stanza adiacente con MENO attrezzi
			destinazione = getStanzaConMenoAttrezzi(corrente);
			partita.setStanzaCorrente(destinazione);
			return MESSAGGIO_PERMALOSA;
		} else {
			// salutata → stanza adiacente con PIÙ attrezzi
			destinazione = getStanzaConPiuAttrezzi(corrente);
			partita.setStanzaCorrente(destinazione);
			return MESSAGGIO_GENTILE;
		}
	}

	private Stanza getStanzaConMenoAttrezzi(Stanza corrente) {
		Stanza minima = null;
		for (String dir : corrente.getDirezioni()) {
			Stanza adiacente = corrente.getStanzaAdiacente(dir);
			if (minima == null || adiacente.getNumeroAttrezzi() < minima.getNumeroAttrezzi())
				minima = adiacente;
		}
		return minima != null ? minima : corrente;
	}

	private Stanza getStanzaConPiuAttrezzi(Stanza corrente) {
		Stanza massima = null;
		for (String dir : corrente.getDirezioni()) {
			Stanza adiacente = corrente.getStanzaAdiacente(dir);
			if (massima == null || adiacente.getNumeroAttrezzi() > massima.getNumeroAttrezzi())
				massima = adiacente;
		}
		return massima != null ? massima : corrente;
	}
}
