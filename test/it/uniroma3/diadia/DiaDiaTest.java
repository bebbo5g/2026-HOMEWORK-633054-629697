package it.uniroma3.diadia;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class DiaDiaTest {

	@Test
	public void testFine() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("atrio").addStanzaVincente("biblioteca")
				.getLabirinto();
		IOSimulator ios = new IOSimulator(Arrays.asList("fine"));
		DiaDia gioco = new DiaDia(lab, ios);
		gioco.gioca();
		assertTrue(ios.contieneMessaggio("Grazie di aver giocato!"));
	}

	@Test
	public void testComandoNonValido() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("atrio").addStanzaVincente("biblioteca")
				.getLabirinto();
		IOSimulator ios = new IOSimulator(Arrays.asList("blabla", "fine"));
		DiaDia gioco = new DiaDia(lab, ios);
		gioco.gioca();
		assertTrue(ios.contieneMessaggio("Comando non valido"));
	}

	@Test
	public void testVaiEVinci() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("atrio").addStanzaVincente("biblioteca")
				.addAdiacenza("atrio", "biblioteca", Direzione.nord).getLabirinto();
		IOSimulator ios = new IOSimulator(Arrays.asList("vai nord"));
		DiaDia gioco = new DiaDia(lab, ios);
		gioco.gioca();
		assertTrue(ios.contieneMessaggio("Hai vinto!"));
	}

	@Test
	public void testVaiDirezioneInesistente() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("atrio").addStanzaVincente("biblioteca")
				.getLabirinto();
		IOSimulator ios = new IOSimulator(Arrays.asList("vai nord", "fine"));
		DiaDia gioco = new DiaDia(lab, ios);
		gioco.gioca();
		assertTrue(ios.contieneMessaggio("Direzione inesistente"));
	}

	@Test
	public void testCfuAzzerati() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("atrio").addStanza("stanza2")
				.addStanzaVincente("biblioteca").addAdiacenza("atrio", "stanza2", Direzione.nord)
				.addAdiacenza("stanza2", "atrio", Direzione.sud).getLabirinto();
		IOSimulator ios = new IOSimulator(Arrays.asList("vai nord", "vai sud", "vai nord", "vai sud", "vai nord",
				"vai sud", "vai nord", "vai sud", "vai nord", "vai sud", "vai nord", "vai sud", "vai nord", "vai sud",
				"vai nord", "vai sud", "vai nord", "vai sud", "vai nord", "vai sud"));
		DiaDia gioco = new DiaDia(lab, ios);
		gioco.gioca();
		assertTrue(ios.contieneMessaggio("Hai esaurito i CFU..."));
	}
}