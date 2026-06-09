package it.uniroma3.diadia;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Proprieta {

	private static final String NOME_FILE = "diadia.properties";
	private static final Properties properties = new Properties();

	static {
		try {
			InputStream in = Proprieta.class.getClassLoader().getResourceAsStream(NOME_FILE);
			if (in != null)
				properties.load(in);
			else
				System.err.println("File " + NOME_FILE + " non trovato, uso valori di default.");
		} catch (IOException e) {
			throw new RuntimeException("Errore caricamento properties: " + e.getMessage());
		}
	}

	public static int getCfuIniziali() {
		return Integer.parseInt(properties.getProperty("cfu.iniziali", "20"));
	}

	public static int getPesoMaxBorsa() {
		return Integer.parseInt(properties.getProperty("peso.max.borsa", "10"));
	}
}
