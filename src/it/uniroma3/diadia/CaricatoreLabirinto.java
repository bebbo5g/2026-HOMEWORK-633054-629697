package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {
	private static final String STANZE_MARKER = "Stanze:";
	private static final String STANZE_BUIE_MARKER = "Stanze buie:";
	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate:";
	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche:";
	private static final String ESTREMI_MARKER = "Estremi:";
	private static final String ATTREZZI_MARKER = "Attrezzi:";
	private static final String PERSONAGGI_MARKER = "Personaggi:";
	private static final String USCITE_MARKER = "Uscite:";

	private LineNumberReader reader;
	private LabirintoBuilder builder;
	private String rigaInAttesa;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(Reader reader) {
		this.reader = new LineNumberReader(reader);
		this.builder = new LabirintoBuilder();
		this.rigaInAttesa = null;
	}

	public Labirinto carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiEstremi();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaPersonaggi();
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
			this.builder.addStanza(riga.trim());
		}
		pushBack(riga);
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String riga = leggiRigaNonVuota();
		if (riga == null || !riga.trim().equals(STANZE_BUIE_MARKER)) {
			pushBack(riga);
			return;
		}
		while ((riga = leggiRigaNonVuota()) != null && !isMarker(riga)) {
			Scanner s = new Scanner(riga.trim());
			check(s.hasNext(), msgTerminazionePrecoce("nome stanza buia"));
			String nome = s.next();
			check(s.hasNext(), msgTerminazionePrecoce("attrezzo luce per " + nome));
			String attrezzoLuce = s.next();
			this.builder.addStanzaBuia(nome, attrezzoLuce);
		}
		pushBack(riga);
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String riga = leggiRigaNonVuota();
		if (riga == null || !riga.trim().equals(STANZE_BLOCCATE_MARKER)) {
			pushBack(riga);
			return;
		}
		while ((riga = leggiRigaNonVuota()) != null && !isMarker(riga)) {
			Scanner s = new Scanner(riga.trim());
			check(s.hasNext(), msgTerminazionePrecoce("nome stanza bloccata"));
			String nome = s.next();
			check(s.hasNext(), msgTerminazionePrecoce("direzione bloccata per " + nome));
			String direzione = s.next();
			check(s.hasNext(), msgTerminazionePrecoce("attrezzo sblocca per " + nome));
			String attrezzoSblocca = s.next();
			this.builder.addStanzaBloccata(nome, direzione, attrezzoSblocca);
		}
		pushBack(riga);
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String riga = leggiRigaNonVuota();
		if (riga == null || !riga.trim().equals(STANZE_MAGICHE_MARKER)) {
			pushBack(riga);
			return;
		}
		while ((riga = leggiRigaNonVuota()) != null && !isMarker(riga)) {
			Scanner s = new Scanner(riga.trim());
			check(s.hasNext(), msgTerminazionePrecoce("nome stanza magica"));
			String nome = s.next();
			int soglia = s.hasNextInt() ? s.nextInt() : 3; // soglia default 3
			this.builder.addStanzaMagica(nome, soglia);
		}
		pushBack(riga);
	}

	private void leggiEstremi() throws FormatoFileNonValidoException {
		leggiRigaCheCominciaPer(ESTREMI_MARKER);
		String nomeIniziale = leggiRigaNonVuota().trim();
		String nomeFinale = leggiRigaNonVuota().trim();
		check(this.builder.getListaStanze().containsKey(nomeIniziale), "Stanza iniziale non definita: " + nomeIniziale);
		check(this.builder.getListaStanze().containsKey(nomeFinale), "Stanza vincente non definita: " + nomeFinale);
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
			check(s.hasNext(), msgTerminazionePrecoce("stanza per " + nomeAttrezzo));
			String nomeStanza = s.next();
			check(this.builder.getListaStanze().containsKey(nomeStanza), "Stanza non definita: " + nomeStanza);
			this.builder.getListaStanze().get(nomeStanza).addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
		}
		pushBack(riga);
	}

	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String riga = leggiRigaNonVuota();
		if (riga == null || !riga.trim().equals(PERSONAGGI_MARKER)) {
			pushBack(riga);
			return;
		}
		while ((riga = leggiRigaNonVuota()) != null && !isMarker(riga)) {
			Scanner s = new Scanner(riga.trim());
			check(s.hasNext(), msgTerminazionePrecoce("tipo personaggio"));
			String tipo = s.next();
			check(s.hasNext(), msgTerminazionePrecoce("nome personaggio"));
			String nome = s.next();
			check(s.hasNext(), msgTerminazionePrecoce("presentazione personaggio"));
			String presentazione = s.next();
			check(s.hasNext(), msgTerminazionePrecoce("stanza personaggio"));

			AbstractPersonaggio personaggio = null;

			switch (tipo.toLowerCase()) {
			case "mago": {
				check(s.hasNext(), msgTerminazionePrecoce("attrezzo del mago"));
				String nomeAttrezzo = s.next();
				check(s.hasNextInt(), msgTerminazionePrecoce("peso attrezzo del mago"));
				int peso = s.nextInt();
				check(s.hasNext(), msgTerminazionePrecoce("stanza del mago"));
				String nomeStanza = s.next();
				personaggio = new Mago(nome, presentazione, new Attrezzo(nomeAttrezzo, peso));
				check(this.builder.getListaStanze().containsKey(nomeStanza), "Stanza non definita: " + nomeStanza);
				this.builder.getListaStanze().get(nomeStanza).setPersonaggio(personaggio);
				break;
			}
			case "cane": {
				String nomeStanza = s.next();
				personaggio = new Cane(nome, presentazione);
				check(this.builder.getListaStanze().containsKey(nomeStanza), "Stanza non definita: " + nomeStanza);
				this.builder.getListaStanze().get(nomeStanza).setPersonaggio(personaggio);
				break;
			}
			case "strega": {
				String nomeStanza = s.next();
				personaggio = new Strega(nome, presentazione);
				check(this.builder.getListaStanze().containsKey(nomeStanza), "Stanza non definita: " + nomeStanza);
				this.builder.getListaStanze().get(nomeStanza).setPersonaggio(personaggio);
				break;
			}
			default:
				check(false, "Tipo personaggio sconosciuto: " + tipo);
			}
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
			check(this.builder.getListaStanze().containsKey(da), "Stanza partenza sconosciuta: " + da);
			check(this.builder.getListaStanze().containsKey(a), "Stanza destinazione sconosciuta: " + a);
			this.builder.addAdiacenza(da, a, dir);
		}
	}

	// ── utilità ──────────────────────────────────────────────

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
			while ((riga = this.reader.readLine()) != null)
				if (!riga.trim().isEmpty())
					return riga;
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
		return t.equals(STANZE_MARKER) || t.equals(STANZE_BUIE_MARKER) || t.equals(STANZE_BLOCCATE_MARKER)
				|| t.equals(STANZE_MAGICHE_MARKER) || t.equals(ESTREMI_MARKER) || t.equals(ATTREZZI_MARKER)
				|| t.equals(PERSONAGGI_MARKER) || t.equals(USCITE_MARKER);
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
