package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {

    private Labirinto.LabirintoBuilder builder;

    @Before
    public void setUp() {
        builder = Labirinto.newBuilder();
    }

    @Test
    public void testMonolocaleStanzaIniziale() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaVincente("salotto")
                .getLabirinto();
        assertEquals("salotto", lab.getStanzaCorrente().getNome());
    }

    @Test
    public void testMonolocaleStanzaVincente() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaVincente("salotto")
                .getLabirinto();
        assertEquals("salotto", lab.getStanzaVincente().getNome());
    }

    @Test
    public void testMonolocaleVincitaSubito() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaVincente("salotto")
                .getLabirinto();
        assertEquals(lab.getStanzaCorrente(), lab.getStanzaVincente());
    }

    @Test
    public void testBilocaleStanzaIniziale() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaVincente("camera")
                .getLabirinto();
        assertEquals("salotto", lab.getStanzaCorrente().getNome());
    }

    @Test
    public void testBilocaleStanzaVincente() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaVincente("camera")
                .getLabirinto();
        assertEquals("camera", lab.getStanzaVincente().getNome());
    }

    @Test
    public void testBilocaleAdiacenza() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaVincente("camera")
                .addAdiacenza("salotto", "camera", Direzione.nord)
                .getLabirinto();
        assertEquals("camera", lab.getStanzaCorrente()
                .getStanzaAdiacente(Direzione.nord).getNome());
    }

    @Test
    public void testBilocaleAttrezzo() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaVincente("camera")
                .addAttrezzo("camera", "letto", 10)
                .getLabirinto();
        assertTrue(lab.getStanzaVincente().hasAttrezzo("letto"));
    }

    @Test
    public void testTrilocaleAdiacenze() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanza("cucina")
                .addStanzaVincente("camera")
                .addAdiacenza("salotto", "cucina", Direzione.nord)
                .addAdiacenza("cucina", "camera", Direzione.est)
                .getLabirinto();
        assertEquals("cucina", lab.getStanzaCorrente()
                .getStanzaAdiacente(Direzione.nord).getNome());
        assertEquals("camera", lab.getStanzaCorrente()
                .getStanzaAdiacente(Direzione.nord)
                .getStanzaAdiacente(Direzione.est).getNome());
    }

    @Test
    public void testStanzaBuia() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaBuia("buia", "lanterna")
                .addAdiacenza("salotto", "buia", Direzione.nord)
                .getLabirinto();
        Stanza buia = lab.getStanzaCorrente().getStanzaAdiacente(Direzione.nord);
        assertNotNull(buia);
        assertEquals("qui c'è buio pesto", buia.getDescrizione());
    }

    @Test
    public void testStanzaBloccata() {
        Labirinto lab = builder
                .addStanzaIniziale("salotto")
                .addStanzaBloccata("bloccata", "passepartout", "nord")
                .addAdiacenza("salotto", "bloccata", Direzione.est)
                .getLabirinto();
        Stanza bloccata = lab.getStanzaCorrente().getStanzaAdiacente(Direzione.est);
        assertNotNull(bloccata);
        assertEquals(bloccata, bloccata.getStanzaAdiacente(Direzione.nord));
    }
}