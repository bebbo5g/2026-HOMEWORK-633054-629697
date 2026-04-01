package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	final private IOConsole IO;

	public DiaDia(IOConsole IO) {
		this.partita = new Partita();
		this.IO = IO;
	}

	public void gioca() {

		this.IO.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do {
			System.out.print("> ");
			String istruzione = this.IO.leggiRiga();

			processaIstruzione(istruzione);

		} while (!this.partita.isFinita());

		this.IO.mostraMessaggio("");
		if (this.partita.vinta()) {
			this.IO.mostraMessaggio("Hai vinto!");
		} else if (this.partita.getGiocatore().getCfu() <= 0) {
			this.IO.mostraMessaggio("Hai esaurito i CFU! Game Over!");
		}

		this.IO.mostraMessaggio("Grazie di aver giocato!");
	}

	/**
	 * Processa una istruzione
	 */
	private void processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		} else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		} else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		} else {
			this.IO.mostraMessaggio("Comando sconosciuto");
		}
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		StringBuilder msg_aiuto = new StringBuilder();

		for (int i = 0; i < elencoComandi.length; i++) {
			msg_aiuto.append(elencoComandi[i] + " ");
		}

		this.IO.mostraMessaggio(msg_aiuto.toString());
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null) {
			this.IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			this.IO.mostraMessaggio("Dove vuoi andare ?");
			return;
		}

		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);

		if (prossimaStanza == null) {
			this.IO.mostraMessaggio("Direzione inesistente");
			return;
		}

		this.partita.setStanzaCorrente(prossimaStanza);

		this.IO.mostraMessaggio(this.partita.getStanzaCorrente().getDescrizione());
		this.IO.mostraMessaggio("CFU rimantente: " + this.partita.getGiocatore().getCfu());
	}

	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.IO.mostraMessaggio("Non hai scritto niente!");
			return;
		}

		if (!this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			this.IO.mostraMessaggio(
					"L'attrezzo " + nomeAttrezzo + " non è presente in: " + this.partita.getStanzaCorrente().getNome());
			return;
		}

		Borsa borsa = this.partita.getGiocatore().borsa;

		if (borsa.isPiena()) {
			this.IO.mostraMessaggio("Borsa piena!");
			return;
		}

		Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		if (borsa.addAttrezzo(attrezzo)) {
			this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
			this.IO.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " è stato aggiunto in borsa!");
			this.IO.mostraMessaggio("La borsa pesa: " + borsa.getPeso() + "kg/" + borsa.getPesoMax() + "kg");
		} else {
			this.IO.mostraMessaggio("L'attrezzo è troppo pesante per essere preso!");
		}

	}

	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			this.IO.mostraMessaggio("Non hai scritto nulla!");
			return;
		}

		Borsa borsa = this.partita.getGiocatore().borsa;

		if (!borsa.hasAttrezzo(nomeAttrezzo)) {
			this.IO.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " non è presente in borsa!");
			return;
		}

		Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);

		if (this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			borsa.removeAttrezzo(attrezzo);
			this.IO.mostraMessaggio("Attrezzo" + attrezzo.getNome() + " posato in stanza: "
					+ this.partita.getStanzaCorrente().getNome());
			this.IO.mostraMessaggio("Peso attuale borsa: " + borsa.getPeso() + "kg/" + borsa.getPesoMax() + "kg");
		} else {
			this.IO.mostraMessaggio("Stanza piena di attrezzi, IMPOSSIBILE POSARE!");
		}

	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.partita.setFinita();
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();

		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}

}