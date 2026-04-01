package it.uniroma3.diadia;

import java.util.Scanner;
import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	
	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		
		scannerDiLinee = new Scanner(System.in);
		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
			
		
		scannerDiLinee.close();
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		}else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		}else if(comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		}else if(comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		}else {
			System.out.println("Comando sconosciuto");
		}
		
		if(this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		}
		
		return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.labirinto.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.labirinto.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.giocatore.getCfu();
			this.partita.giocatore.setCfu(cfu - 1);
			
			System.out.println("CFU rimantente: " + this.partita.giocatore.getCfu());
		}
		System.out.println(partita.labirinto.getStanzaCorrente().getDescrizione());
	}
	
	
	
	private void prendi(String nomeAttrezzo)
	{
		/*if(nomeAttrezzo == null) {
			System.out.println("Non hai scritto niente! ");
			
		}else{
			
			Stanza stanzaCorr = this.partita.labirinto.getStanzaCorrente();
			Attrezzo attrezzoStanza = stanzaCorr.getAttrezzo(nomeAttrezzo);
			
			if(attrezzoStanza == null)
			{
				System.out.println("Nella stanza non c'è l'attrezzo: " + nomeAttrezzo);
				return;
			}
			
			boolean aggiunto = this.partita.giocatore.borsa.addAttrezzo(attrezzoStanza);
			
			if(aggiunto)
			{
				stanzaCorr.removeAttrezzo(nomeAttrezzo);
				
				System.out.println("Attrezzo '" + attrezzoStanza.getNome() + "' preso e inserito nella borsa!");
				System.out.println("Peso attuale borsa: " + this.partita.giocatore.borsa.getPeso()+ "kg/" + this.partita.giocatore.borsa.getPesoMax() + "kg");
			}
			else
			{
				System.out.println("Borsa piena o troppo pesante! Non puoi prendere '" + attrezzoStanza.getNome() + "'");
			}		
		}*/
		if(nomeAttrezzo == null)
			System.out.println("Non hai scritto niente!");
		else
		{
			if(!this.partita.labirinto.corrente.hasAttrezzo(nomeAttrezzo))
			{
				System.out.println("L'attrezzo " + nomeAttrezzo + " non è presente in: " + this.partita.labirinto.corrente.getNome());
			}
			else
			{
				Attrezzo attrezzo = this.partita.labirinto.corrente.getAttrezzo(nomeAttrezzo);
					
				if(this.partita.giocatore.borsa.addAttrezzo(attrezzo)) {
					this.partita.labirinto.corrente.removeAttrezzo(nomeAttrezzo);
					System.out.println("L'attrezzo "+ nomeAttrezzo + " è stato aggiunto in borsa!");
					System.out.println("La borsa pesa: " + this.partita.giocatore.borsa.getPeso() + "kg/" + this.partita.giocatore.borsa.getPesoMax() + "kg");
				}
				else if(this.partita.giocatore.borsa.isPiena())
					System.out.println("Borsa piena!");
				else
					System.out.println("L'attrezzo è troppo pesante per essere preso!");
			}
		}
	}
	
	private void posa(String nomeAttrezzo)
	{
		/*if(nomeAttrezzo == null || nomeAttrezzo.isEmpty())
		{
			System.out.println("Nessun attrazzo da posare! ");
		}
		else
		{
			Stanza stanzaCorrente = this.partita.labirinto.getStanzaCorrente();
			Attrezzo attrezzo = this.partita.giocatore.borsa.getAttrezzo(nomeAttrezzo);
			
			if(attrezzo == null)
			{
				System.out.println("Nella borsa non abbiamo l'attrezzo: " + nomeAttrezzo);
				return;
			}
			
			boolean aggiunto = stanzaCorrente.addAttrezzo(attrezzo);
			
			if(aggiunto)
			{
				this.partita.giocatore.borsa.removeAttrezzo(attrezzo);
				
				System.out.println("Attrezzo '" + attrezzo.getNome() + "' posato in stanza: "+ stanzaCorrente.getNome());
				System.out.println("Peso attuale borsa: " + this.partita.giocatore.borsa.getPeso() + "kg/" + this.partita.giocatore.borsa.getPesoMax() + "kg");
			}
			else
			{
				System.out.println("Stanza piena din attrezzi impossibile posare: "+ attrezzo.getNome());
			}
		}*/
		
		if(nomeAttrezzo == null)
			System.out.println("Non hai scritto nulla!");
		else
		{
			if(!this.partita.giocatore.borsa.hasAttrezzo(nomeAttrezzo))
				System.out.println("L'attrezzo "+ nomeAttrezzo + " non è presente in borsa!");
			else{
				Attrezzo attrezzo = this.partita.giocatore.borsa.getAttrezzo(nomeAttrezzo);
				if(this.partita.labirinto.corrente.addAttrezzo(attrezzo)) {
					this.partita.giocatore.borsa.removeAttrezzo(attrezzo);
					System.out.println("Attrezzo" + attrezzo.getNome() + " posato in stanza: " + this.partita.labirinto.corrente.getNome());
					System.out.println("Peso attuale borsa: " + this.partita.giocatore.borsa.getPeso() + "kg/" + this.partita.giocatore.borsa.getPesoMax() + "kg");
				}else
					System.out.println("Stanza piena di attrezzi, IMPOSSIBILE POSARE!");
			}
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");// si desidera smettere
		System.out.println("cfu rimasti: " + this.partita.giocatore.getCfu());
		this.partita.isFinita();
		return;
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
	
	
	
}