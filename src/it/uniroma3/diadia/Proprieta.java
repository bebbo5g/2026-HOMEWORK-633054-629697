package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Proprieta {

	private static final String FILE = "diadia.properties";
	private static final Properties props = new Properties();

	// il blocco static viene eseguito una volta sola al caricamento della classe
	static {
		try (InputStream in = Proprieta.class.getClassLoader().getResourceAsStream(FILE)) {
			if (in == null)
				throw new RuntimeException(FILE + " non trovato!");
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException("Errore caricamento properties", e);
		}
	}

	public static int getCfuIniziali() {
		return Integer.parseInt(props.getProperty("cfu.iniziali", "20"));
	}

	public static int getPesoMaxBorsa() {
		return Integer.parseInt(props.getProperty("peso.max.borsa", "10"));
	}

	public static String getFileLabirinto() {
		return props.getProperty("file.labirinto", "labirinto.txt");
	}
}