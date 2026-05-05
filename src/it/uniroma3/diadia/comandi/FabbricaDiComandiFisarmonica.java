package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {
	final private IO io;

	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;

		if (scannerDiParole.hasNext()) {
			nomeComando = scannerDiParole.next(); // prima parola: nome del comando
		}
		if (scannerDiParole.hasNext()) {
			parametro = scannerDiParole.next(); // seconda parola: eventuale param.
		}

		if (nomeComando == null) {
			return new ComandoNonValido("<empty>", this.io);
		}

		switch (nomeComando) {
		case "aiuto":
			return new ComandoAiuto(this.io);
		case "fine":
			return new ComandoFine(this.io);
		case "guarda":
			return new ComandoGuarda(this.io);
		case "posa":
			return new ComandoPosa(parametro, this.io);
		case "prendi":
			return new ComandoPrendi(parametro, this.io);
		case "vai":
			return new ComandoVai(parametro, this.io);
		default:
			return new ComandoNonValido(nomeComando, this.io);
		}
	}
}
