package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	StanzaBuia stanzaBuia;
	String attLucente;

	@Before
	public void setUp() {
		stanzaBuia = new StanzaBuia("sala", "luce");
	}

	@Test
	public void testNoLuce() {
		assertEquals("qui c'è buio pesto", stanzaBuia.toString());
	}

	@Test
	public void testSiLuce() {
		Attrezzo luce = new Attrezzo("luce", 1);
		stanzaBuia.addAttrezzo(luce);
		assertNotEquals("qui c'è buio pesto", stanzaBuia.toString());
	}
}