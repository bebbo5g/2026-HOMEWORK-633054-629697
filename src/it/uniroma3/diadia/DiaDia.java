package it.uniroma3.diadia;

import java.util.Scanner;

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

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {

		String istruzione;
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);

		scannerDiLinee = new Scanner(System.in);

		do {
			System.out.print("> ");
			istruzione = scannerDiLinee.nextLine();
			processaIstruzione(istruzione);
		} while (!this.partita.isFinita());

		scannerDiLinee.close();

		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
		} else if (this.partita.getGiocatore().getCfu() <= 0) {
			System.out.println("Hai esaurito i CFU! Game Over!");
		}

		System.out.println("Grazie di aver giocato!");
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
			System.out.println("Comando sconosciuto");
		}
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			System.out.print(elencoComandi[i] + " ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null) {
			System.out.println(partita.getStanzaCorrente().getDescrizione());
			System.out.println("Dove vuoi andare ?");
			return;
		}

		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);

		if (prossimaStanza == null) {
			System.out.println("Direzione inesistente");
			return;
		}

		this.partita.setStanzaCorrente(prossimaStanza);

		System.out.println(this.partita.getStanzaCorrente().getDescrizione());
		System.out.println("CFU rimantente: " + this.partita.getGiocatore().getCfu());
		System.out.println();
	}

	private void prendi(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			System.out.println("Non hai scritto niente!");
			return;
		}

		if (!this.partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) {
			System.out.println(
					"L'attrezzo " + nomeAttrezzo + " non è presente in: " + this.partita.getStanzaCorrente().getNome());
			return;
		}

		Borsa borsa = this.partita.getGiocatore().borsa;

		if (borsa.isPiena()) {
			System.out.println("Borsa piena!");
			return;
		}

		Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);

		if (borsa.addAttrezzo(attrezzo)) {
			this.partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
			System.out.println("L'attrezzo " + nomeAttrezzo + " è stato aggiunto in borsa!");
			System.out.println("La borsa pesa: " + borsa.getPeso() + "kg/" + borsa.getPesoMax() + "kg");
		} else {
			System.out.println("L'attrezzo è troppo pesante per essere preso!");
		}

	}

	private void posa(String nomeAttrezzo) {
		if (nomeAttrezzo == null) {
			System.out.println("Non hai scritto nulla!");
			return;
		}

		Borsa borsa = this.partita.getGiocatore().borsa;

		if (!borsa.hasAttrezzo(nomeAttrezzo)) {
			System.out.println("L'attrezzo " + nomeAttrezzo + " non è presente in borsa!");
			return;
		}

		Attrezzo attrezzo = borsa.getAttrezzo(nomeAttrezzo);

		if (this.partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			borsa.removeAttrezzo(attrezzo);
			System.out.println("Attrezzo" + attrezzo.getNome() + " posato in stanza: "
					+ this.partita.getStanzaCorrente().getNome());
			System.out.println("Peso attuale borsa: " + borsa.getPeso() + "kg/" + borsa.getPesoMax() + "kg");
		} else {
			System.out.println("Stanza piena di attrezzi, IMPOSSIBILE POSARE!");
		}

	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.partita.setFinita();
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

}