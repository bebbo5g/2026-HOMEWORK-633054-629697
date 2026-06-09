package it.uniroma3.diadia.ambienti;

import java.io.Reader;
import java.util.Scanner;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class CaricatoreLabirinto {

	private static final String STANZE = "Stanze:";
	private static final String ESTREMI = "Estremi:";
	private static final String ATTREZZI = "Attrezzi:";
	private static final String USCITE = "Uscite:";
	private static final String PERSONAGGI = "Personaggi:";

	private final Labirinto.LabirintoBuilder builder;
	private final Scanner scanner;
	private boolean inizialeImpostata = false;

	public CaricatoreLabirinto(Reader reader) {
		this.builder = Labirinto.newBuilder();
		this.scanner = new Scanner(reader);
	}

	public Labirinto carica() throws FormatoLabirintoException {
		String sezione = null;
		while (scanner.hasNextLine()) {
			String linea = scanner.nextLine().trim();
			if (linea.isEmpty())
				continue;

			if (linea.equals(STANZE) || linea.equals(ESTREMI) || linea.equals(ATTREZZI) || linea.equals(USCITE)
					|| linea.equals(PERSONAGGI)) {
				sezione = linea;
				continue;
			}

			if (sezione == null)
				throw new FormatoLabirintoException("Sezione mancante prima di: " + linea);

			switch (sezione) {
			case STANZE:
				gestisciStanza(linea);
				break;
			case ESTREMI:
				gestisciEstremi(linea);
				break;
			case ATTREZZI:
				gestisciAttrezzo(linea);
				break;
			case USCITE:
				gestisciUscita(linea);
				break;
			case PERSONAGGI:
				gestisciPersonaggio(linea);
				break;
			}
		}
		return builder.getLabirinto();
	}

	private void gestisciStanza(String linea) throws FormatoLabirintoException {
		String[] p = linea.split(" ");
		switch (p[0]) {
		case "magica":
			builder.addStanzaMagica(p[1]);
			break;
		case "buia":
			if (p.length < 3)
				throw new FormatoLabirintoException("Formato buia non valido: " + linea);
			builder.addStanzaBuia(p[1], p[2]);
			break;
		case "bloccata":
			if (p.length < 4)
				throw new FormatoLabirintoException("Formato bloccata non valido: " + linea);
			builder.addStanzaBloccata(p[1], p[2], p[3]);
			break;
		default:
			builder.addStanza(linea);
			break;
		}
	}

	private void gestisciEstremi(String linea) {
		if (!inizialeImpostata) {
			builder.addStanzaIniziale(linea);
			inizialeImpostata = true;
		} else {
			builder.addStanzaVincente(linea);
		}
	}

	private void gestisciAttrezzo(String linea) throws FormatoLabirintoException {
		String[] p = linea.split(" ");
		if (p.length < 3)
			throw new FormatoLabirintoException("Formato attrezzo non valido: " + linea);
		int peso;
		try {
			peso = Integer.parseInt(p[1]);
		} catch (NumberFormatException e) {
			throw new FormatoLabirintoException("Peso non valido: " + p[1]);
		}
		builder.addAttrezzo(p[2], p[0], peso);
	}

	private void gestisciUscita(String linea) throws FormatoLabirintoException {
		String[] p = linea.split(" ");
		if (p.length < 3)
			throw new FormatoLabirintoException("Formato uscita non valido: " + linea);
		try {
			Direzione dir = Direzione.valueOf(p[1]);
			builder.addAdiacenza(p[0], p[2], dir);
		} catch (IllegalArgumentException e) {
			throw new FormatoLabirintoException("Direzione non valida: " + p[1]);
		}
	}

	private void gestisciPersonaggio(String linea) throws FormatoLabirintoException {
		String[] p = linea.split(" ");
		switch (p[0]) {
		case "cane":
			if (p.length < 7)
				throw new FormatoLabirintoException("Formato cane non valido: " + linea);
			builder.addCane(p[1], p[2], p[3], p[4], new Attrezzo(p[5], Integer.parseInt(p[6])));
			break;
		case "strega":
			if (p.length < 4)
				throw new FormatoLabirintoException("Formato strega non valido: " + linea);
			builder.addStrega(p[1], p[2], p[3]);
			break;
		case "mago":
			if (p.length < 6)
				throw new FormatoLabirintoException("Formato mago non valido: " + linea);
			builder.addMago(p[1], p[2], p[3], new Attrezzo(p[4], Integer.parseInt(p[5])));
			break;
		default:
			throw new FormatoLabirintoException("Personaggio sconosciuto: " + p[0]);
		}
	}
}