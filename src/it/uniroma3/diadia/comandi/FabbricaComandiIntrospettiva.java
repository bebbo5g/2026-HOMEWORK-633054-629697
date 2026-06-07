package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaComandiIntrospettiva implements FabbricaDiComandi {
	private static final String PACKAGE_COMANDI = "it.uniroma3.diadia.comandi.Comando";

	private static final String[] NOMI_CLASSI = { "Vai", "Prendi", "Posa", "Guarda", "Aiuto", "Fine", "Regala" };

	private final IO io;

	public FabbricaComandiIntrospettiva(IO io) {
		this.io = io;
		preregistraComandi();
	}

	private void preregistraComandi() {
		for (String nome : NOMI_CLASSI) {
			try {
				Class<?> classe = Class.forName(PACKAGE_COMANDI + nome);
				// prova costruttore (IO)
				try {
					classe.getConstructor(IO.class).newInstance(this.io);
				} catch (NoSuchMethodException e) {
					// prova costruttore (String, IO)
					classe.getConstructor(String.class, IO.class).newInstance(null, this.io);
				}
			} catch (Exception e) {
				// classe non trovata, skip
			}
		}
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scanner = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;

		if (scanner.hasNext())
			nomeComando = scanner.next();
		if (scanner.hasNext())
			parametro = scanner.next();

		if (nomeComando == null)
			return new ComandoNonValido("<empty>", this.io);

		String nomeClasse = PACKAGE_COMANDI + Character.toUpperCase(nomeComando.charAt(0))
				+ nomeComando.substring(1).toLowerCase();

		try {
			Class<?> classe = Class.forName(nomeClasse);

			try {
				return (Comando) classe.getConstructor(String.class, IO.class).newInstance(parametro, this.io);
			} catch (NoSuchMethodException e) {
				return (Comando) classe.getConstructor(IO.class).newInstance(this.io);
			}

		} catch (ClassNotFoundException e) {
			return new ComandoNonValido(nomeComando, this.io);
		} catch (Exception e) {
			return new ComandoNonValido(nomeComando, this.io);
		}
	}
}
