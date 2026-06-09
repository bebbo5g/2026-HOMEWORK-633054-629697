package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	@Override
	public void esegui(Partita partita, IO io) {
		if (this.getParametro() == null) {
			io.mostraMessaggio("Cosa vuoi regalare?");
			return;
		}
		AbstractPersonaggio personaggio = partita.lab.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			io.mostraMessaggio("Non c'è nessuno a cui regalare!");
			return;
		}
		Attrezzo attrezzo = partita.giocatore.borsa.getAttrezzo(this.getParametro());
		if (attrezzo == null) {
			io.mostraMessaggio("Non hai " + this.getParametro() + " nella borsa!");
			return;
		}
		partita.giocatore.borsa.removeAttrezzo(this.getParametro());
		io.mostraMessaggio(personaggio.riceviRegalo(attrezzo, partita));
	}
}