package it.uniroma3.diadia.personaggi;

import java.util.Set;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza stanzaCorrente = partita.lab.getStanzaCorrente();
		Set<Direzione> direzioni = stanzaCorrente.getDirezioni();
		Stanza destinazione = null;
		int attrezziDestinazione = -1;
		for (Direzione dir : direzioni) {
			Stanza adiacente = stanzaCorrente.getStanzaAdiacente(dir);
			int numAttrezzi = adiacente.getAttrezzi().size();
			if (!this.haSalutato()) {
				if (destinazione == null || numAttrezzi < attrezziDestinazione) {
					destinazione = adiacente;
					attrezziDestinazione = numAttrezzi;
				}
			} else {
				if (destinazione == null || numAttrezzi > attrezziDestinazione) {
					destinazione = adiacente;
					attrezziDestinazione = numAttrezzi;
				}
			}
		}
		if (destinazione != null) {
			partita.lab.setStanzaCorrente(destinazione);
			return "La strega ti ha trasportato in " + destinazione.getNome() + "!";
		}
		return "La strega non sa dove mandarti!";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return "La strega trattiene " + attrezzo.getNome() + " scoppiando a ridere!";
	}
}