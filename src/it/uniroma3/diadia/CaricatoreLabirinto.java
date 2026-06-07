package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class CaricatoreLabirinto {
	private static final String STANZE_MARKER = "Stanze:";
	private static final String ESTREMI_MARKER = "Estremi:";
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	private static final String USCITE_MARKER = "Uscite:";

	private LineNumberReader reader;
	private LabirintoBuilder builder;

	// costruttore da file
	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new FileReader(nomeFile));
	}

	// costruttore con Reader generico — usato nei test con StringReader
	public CaricatoreLabirinto(Reader reader) {
		this.reader = new LineNumberReader(reader);
		this.builder = new LabirintoBuilder();
	}

	public Labirinto carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiEstremi();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			return this.builder.getLabirinto();
		} finally {
			try {
				this.reader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException {
		leggiRigaCheCominciaPer(STANZE_MARKER);
		String riga;
		while ((riga = leggiRigaNonVuota()) != null && !isMarker(riga)) {
			String nomeStanza = riga.trim();
			this.builder.addStanza(nomeStanza);
		}
		// rimetti in testa la riga letta che era un marker
		pushBack(riga);
	}

	private void leggiEstremi() throws FormatoFileNonValidoException {
		leggiRigaCheCominciaPer(ESTREMI_MARKER);
		String nomeIniziale = leggiRigaNonVuota().trim();
		String nomeFinale = leggiRigaNonVuota().trim();

		check(this.builder.getListaStanze().containsKey(nomeIniziale), "Stanza iniziale non definita: " + nomeIniziale);
		check(this.builder.getListaStanze().containsKey(nomeFinale), "Stanza vincente non definita: " + nomeFinale);

		// ricrea come iniziale/vincente usando il builder
		this.builder.addStanzaIniziale(nomeIniziale);
		this.builder.addStanzaVincente(nomeFinale);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		String riga;
		while ((riga = leggiRigaNonVuota()) != null && !isMarker(riga)) {
			Scanner s = new Scanner(riga.trim());
			check(s.hasNext(), msgTerminazionePrecoce("nome attrezzo"));
			String nomeAttrezzo = s.next();
			check(s.hasNextInt(), msgTerminazionePrecoce("peso attrezzo " + nomeAttrezzo));
			int peso = s.nextInt();
			check(s.hasNext(), msgTerminazionePrecoce("stanza per attrezzo " + nomeAttrezzo));
			String nomeStanza = s.next();
			check(this.builder.getListaStanze().containsKey(nomeStanza),
					"Stanza " + nomeStanza + " non definita per attrezzo " + nomeAttrezzo);
			// imposta ultima stanza aggiunta e aggiungi attrezzo
			Stanza stanza = this.builder.getListaStanze().get(nomeStanza);
			stanza.addAttrezzo(new it.uniroma3.diadia.attrezzi.Attrezzo(nomeAttrezzo, peso));
		}
		pushBack(riga);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		leggiRigaCheCominciaPer(USCITE_MARKER);
		String riga;
		while ((riga = leggiRigaNonVuota()) != null) {
			Scanner s = new Scanner(riga.trim());
			check(s.hasNext(), msgTerminazionePrecoce("stanza partenza"));
			String da = s.next();
			check(s.hasNext(), msgTerminazionePrecoce("direzione"));
			String dir = s.next();
			check(s.hasNext(), msgTerminazionePrecoce("stanza destinazione"));
			String a = s.next();
			check(this.builder.getListaStanze().containsKey(da), "Stanza di partenza sconosciuta: " + da);
			check(this.builder.getListaStanze().containsKey(a), "Stanza di destinazione sconosciuta: " + a);
			this.builder.addAdiacenza(da, a, dir);
		}
	}

	// ── utilità ──────────────────────────────────────────────

	private String rigaInAttesa = null;

	private void pushBack(String riga) {
		this.rigaInAttesa = riga;
	}

	private String leggiRigaNonVuota() throws FormatoFileNonValidoException {
		if (this.rigaInAttesa != null) {
			String r = this.rigaInAttesa;
			this.rigaInAttesa = null;
			return r;
		}
		try {
			String riga;
			while ((riga = this.reader.readLine()) != null) {
				if (!riga.trim().isEmpty())
					return riga;
			}
			return null;
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		String riga = leggiRigaNonVuota();
		check(riga != null && riga.trim().equals(marker), "Atteso marker: " + marker + " ma trovato: " + riga);
	}

	private boolean isMarker(String riga) {
		String t = riga.trim();
		return t.equals(STANZE_MARKER) || t.equals(ESTREMI_MARKER) || t.equals(ATTREZZI_MARKER)
				|| t.equals(USCITE_MARKER);
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce prima di leggere: " + msg;
	}

	private void check(boolean condizione, String messaggio) throws FormatoFileNonValidoException {
		if (!condizione)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [riga " + this.reader.getLineNumber() + "]: " + messaggio);
	}
}
