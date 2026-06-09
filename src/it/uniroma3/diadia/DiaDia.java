package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

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
	private IO io;

	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.io = io;
	}

	public void gioca() {
		String istruzione;
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do {
			istruzione = this.io.leggiRiga();
		} while (!processaIstruzione(istruzione));
	}

	private boolean processaIstruzione(String istruzione) {
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		Comando comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita, this.io);
		if (this.partita.vinta()) {
			this.io.mostraMessaggio("Hai vinto!");
			return true;
		}
		if (!this.partita.giocatore.giocatoreIsVivo()) {
			this.io.mostraMessaggio("Hai esaurito i CFU...");
			return true;
		}
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		try (Scanner scanner = new Scanner(System.in)) {
			IO io = new IOConsole(scanner);
			Labirinto labirinto = Labirinto.newBuilder().addStanzaInizialeBloccata("Atrio", "passepartout", "nord")
					.addAttrezzo("Atrio", "bacchetta", 2).addStanzaBuia("Aula N11", "lanterna")
					.addAttrezzo("Aula N11", "osso", 1).addStanza("Aula N10").addAttrezzo("Aula N10", "lanterna", 3)
					.addAttrezzo("Aula N10", "passepartout", 1)
					.addCane("Aula N10", "Fido", "un cane molto ringhioso!", "osso", new Attrezzo("mantello", 1))
					.addStanza("Laboratorio Campus")
					.addMago("Laboratorio Campus", "Merlino", "Un mago potentissimo!", null)
					.addStanzaVincente("Biblioteca").addStrega("Aula N11", "Magda", "una strega molto permalosa!")
					.addAdiacenza("Atrio", "Biblioteca", Direzione.nord)
					.addAdiacenza("Atrio", "Aula N11", Direzione.est).addAdiacenza("Atrio", "Aula N10", Direzione.sud)
					.addAdiacenza("Atrio", "Laboratorio Campus", Direzione.ovest)
					.addAdiacenza("Aula N11", "Laboratorio Campus", Direzione.est)
					.addAdiacenza("Aula N11", "Atrio", Direzione.ovest)
					.addAdiacenza("Aula N10", "Atrio", Direzione.nord)
					.addAdiacenza("Aula N10", "Aula N11", Direzione.est)
					.addAdiacenza("Aula N10", "Laboratorio Campus", Direzione.ovest)
					.addAdiacenza("Laboratorio Campus", "Atrio", Direzione.est)
					.addAdiacenza("Laboratorio Campus", "Aula N11", Direzione.ovest)
					.addAdiacenza("Biblioteca", "Atrio", Direzione.sud).getLabirinto();

			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();
		}
	}
}