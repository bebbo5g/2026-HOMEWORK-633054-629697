package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PartitaTest {
	
	private Partita partita;
	
	@BeforeEach
	public void setUp()
	{
        partita = new Partita();
	}
	
	@Test
	public void testPartitaNonVinta() {
		assertFalse(partita.vinta());
	}
	
	@Test
	public void testPartitaNonFinitaAllInizio(){
		assertFalse(partita.isFinita());
	}
	
	@Test
	public void testSetFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testPartitaFinitaQuandoCfuZero()
	{
		partita.giocatore.setCfu(0);
		assertTrue(partita.isFinita());
	}
	
	@Test
	public void testPartitaVintaStanzaVincente()
	{
		partita.labirinto.setStanzaCorrente(partita.labirinto.getStanzaVincente());
		assertTrue(partita.vinta());
	}
	
	@Test
	public void testPartitaFinitaStanzaVincente()
	{
		partita.labirinto.setStanzaCorrente(partita.labirinto.getStanzaVincente());
		assertTrue(partita.isFinita());
	}

}
