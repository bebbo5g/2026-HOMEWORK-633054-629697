package it.uniroma3.diadia;

import java.util.Scanner;

public class IOConsole implements IO {

	private final Scanner scanner;

	public IOConsole(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public String leggiRiga() {
		if (scanner.hasNextLine())
			return scanner.nextLine();
		return "";
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		System.out.println(messaggio);
	}
}