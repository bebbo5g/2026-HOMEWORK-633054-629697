package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole {
	
	
	public void MostraMessaggio(String msg)
	{
		System.out.println(msg);
	}
	
	@SuppressWarnings("resource")
	public String leggiRiga()
	{
		Scanner scannerDiLinee;
		scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}
