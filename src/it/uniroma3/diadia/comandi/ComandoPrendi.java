package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando {
	final private String nome = "prendi";
	final private String nomeAttrezzo;
	final private IO io;

	public ComandoPrendi(String nomeAttrezzo, IO io) {
		this.nomeAttrezzo = nomeAttrezzo;
		this.io = io;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public void esegui(Partita partita) {
		if (this.nomeAttrezzo == null) {
			this.io.mostraMessaggio("Non hai scritto niente!");
			return;

		}

		if (!partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			this.io.mostraMessaggio(
					"L'attrezzo " + nomeAttrezzo + " non è presente in: " + partita.getStanzaCorrente().getNome());
			return;
		}

		Borsa borsa = partita.getGiocatore().borsa;

		if (borsa.isPiena()) {
			this.io.mostraMessaggio("Borsa piena!");
			return;
		}

		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		if (borsa.addAttrezzo(attrezzo)) {
			partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
			this.io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " è stato aggiunto in borsa!");
			this.io.mostraMessaggio("La borsa pesa: " + borsa.getPeso() + "kg/" + borsa.getPesoMax() + "kg");
		} else {
			this.io.mostraMessaggio("L'attrezzo è troppo pesante per essere preso!");
		}

	}

}
