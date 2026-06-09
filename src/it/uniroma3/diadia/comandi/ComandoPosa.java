package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {

	@Override
	public void esegui(Partita partita, IO io) {
		if (partita.giocatore.borsa.isEmpty()) {
			io.mostraMessaggio("La tua borsa è vuota non hai nulla da posare!");
			return;
		}
		if (this.getParametro() == null) {
			io.mostraMessaggio("Cosa vorresti posare?");
			return;
		}
		if (!partita.giocatore.borsa.hasAttrezzo(this.getParametro())) {
			io.mostraMessaggio("Non possiedi " + this.getParametro());
			return;
		}
		Attrezzo attrezzoDaPosare = partita.giocatore.borsa.removeAttrezzo(this.getParametro());
		partita.lab.getStanzaCorrente().addAttrezzo(attrezzoDaPosare);
		io.mostraMessaggio("Hai posato " + this.getParametro() + " in " + partita.lab.getStanzaCorrente().getNome());
	}
}