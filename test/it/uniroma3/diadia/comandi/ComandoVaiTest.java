package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoVaiTest {

	private Partita partita;
	private ComandoVai comandoVai;
	private IO io;

	@Before
	public void setUp() {
		comandoVai = new ComandoVai();
		io = new IO() {
			@Override
			public void mostraMessaggio(String msg) {
			}

			@Override
			public String leggiRiga() {
				return null;
			}
		};
	}

	@Test
	public void testVaiDirezioneNullNonCambia() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("stanza1").addStanzaVincente("stanza2")
				.addAdiacenza("stanza1", "stanza2", Direzione.nord).getLabirinto();
		partita = new Partita(lab);
		comandoVai.setParametro(null);
		comandoVai.esegui(partita, io);
		assertEquals("stanza1", partita.lab.getStanzaCorrente().getNome());
	}

	@Test
	public void testVaiDirezioneEsistenteCambia() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("stanza1").addStanzaVincente("stanza2")
				.addAdiacenza("stanza1", "stanza2", Direzione.nord).getLabirinto();
		partita = new Partita(lab);
		comandoVai.setParametro("nord");
		comandoVai.esegui(partita, io);
		assertEquals("stanza2", partita.lab.getStanzaCorrente().getNome());
	}

	@Test
	public void testVaiDirezioneInesistenteNonCambia() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("stanza1").addStanzaVincente("stanza2")
				.addAdiacenza("stanza1", "stanza2", Direzione.nord).getLabirinto();
		partita = new Partita(lab);
		comandoVai.setParametro("sud");
		comandoVai.esegui(partita, io);
		assertEquals("stanza1", partita.lab.getStanzaCorrente().getNome());
	}

	@Test
	public void testVaiDecrementaCfu() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("stanza1").addStanzaVincente("stanza2")
				.addAdiacenza("stanza1", "stanza2", Direzione.nord).getLabirinto();
		partita = new Partita(lab);
		int cfuPrima = partita.giocatore.getCfu();
		comandoVai.setParametro("nord");
		comandoVai.esegui(partita, io);
		assertEquals(cfuPrima - 1, partita.giocatore.getCfu());
	}

	@Test
	public void testVaiDirezioneSbagliataNonCambia() {
		Labirinto lab = Labirinto.newBuilder().addStanzaIniziale("stanza1").addStanza("stanza3")
				.addStanzaVincente("stanza2").addAdiacenza("stanza1", "stanza2", Direzione.nord)
				.addAdiacenza("stanza1", "stanza3", Direzione.est).getLabirinto();
		partita = new Partita(lab);
		comandoVai.setParametro("nord");
		comandoVai.esegui(partita, io);
		assertNotEquals("stanza3", partita.lab.getStanzaCorrente().getNome());
	}
}