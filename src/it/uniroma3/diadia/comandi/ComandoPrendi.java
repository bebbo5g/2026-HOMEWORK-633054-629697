package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {

	@Override
	public void esegui(Partita partita, IO io) {
		if (this.getParametro() == null) {
			io.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		Attrezzo att = partita.lab.getStanzaCorrente().getAttrezzo(this.getParametro());
		if (att == null) {
			io.mostraMessaggio("Nella stanza '" + partita.lab.getStanzaCorrente().getNome() + "' non è presente "
					+ this.getParametro());
			return;
		}
		if (att.getPeso() + partita.giocatore.borsa.getPeso() > partita.giocatore.borsa.getPesoMax()) {
			io.mostraMessaggio("Non puoi prendere " + this.getParametro() + ", sei troppo pesante");
			return;
		}
		partita.giocatore.borsa.addAttrezzo(att);
		partita.lab.getStanzaCorrente().removeAttrezzo(att);
		io.mostraMessaggio("Hai preso " + this.getParametro() + "!");
	}
}