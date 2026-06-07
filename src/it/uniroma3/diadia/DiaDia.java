package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaComandiIntrospettiva;
import it.uniroma3.diadia.personaggi.Cane;

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

	private Partita partita;
	final private IO io;
	final private FabbricaComandiIntrospettiva fabbrica;

	public DiaDia(IO io) {
		this(new Labirinto(true), io);
	}

	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.io = io;
		this.fabbrica = new FabbricaComandiIntrospettiva(io);
	}

	public void gioca() {

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do {
			System.out.print("> ");
			String istruzione = this.io.leggiRiga();

			Comando cmd = this.fabbrica.costruisciComando(istruzione);

			cmd.esegui(this.partita);

		} while (!this.partita.isFinita());

		this.io.mostraMessaggio("");
		if (this.partita.vinta()) {
			this.io.mostraMessaggio("Hai vinto!");
		} else if (this.partita.getGiocatore().getCfu() <= 0) {
			this.io.mostraMessaggio("Hai esaurito i CFU! Game Over!");
		}

		this.io.mostraMessaggio("Grazie di aver giocato!");
	}

	public static void main(String[] argc) {
		IO io = new IOConsole();

		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("LabCampusOne").addStanzaVincente("Biblioteca")
				.addAdiacenza("LabCampusOne", "Biblioteca", "ovest").getLabirinto();

		labirinto.getStanzaIniziale().setPersonaggio(new Cane("Fido", "Sono un cane molto simpatico!"));

		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();
	}

}