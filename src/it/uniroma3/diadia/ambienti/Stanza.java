package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	private String nome;

	private Map<String, Attrezzo> attrezzi;

	private Map<String, Stanza> stanzeAdiacenti;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.attrezzi = new HashMap<>();
		this.stanzeAdiacenti = new HashMap<>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
		this.stanzeAdiacenti.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	@Override
	public String toString() {

		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);

		risultato.append("\nUscite: ");

		for (String direzione : this.stanzeAdiacenti.keySet())
			risultato.append(" ").append(direzione);

		risultato.append("\nAttrezzi nella stanza: ");

		for (Attrezzo attrezzo : this.attrezzi.values())
			risultato.append(attrezzo.toString()).append(" ");

		return risultato.toString();
	}

	/*
	 * java.lang.AssertionError: expected:<{nord=corridoio bloccato Uscite: Attrezzi
	 * nella stanza: , sud=Atrio Uscite: Attrezzi nella stanza: , est=stanza magica
	 * Uscite: Attrezzi nella stanza: , ovest=stanza buia Uscite: Attrezzi nella
	 * stanza: }> but was: <{nord=corridoio bloccato Uscite: nord sud Attrezzi nella
	 * stanza: , sud=Atrio Uscite: nord Attrezzi nella stanza: , est=stanza magica
	 * Uscite: ovest Attrezzi nella stanza: , ovest=stanza buia Uscite: est Attrezzi
	 * nella stanza: }> at org.junit.Assert.fail(Assert.java:89) at
	 * org.junit.Assert.failNotEquals(Assert.java:835) at
	 * org.junit.Assert.assertEquals(Assert.java:120) at
	 * org.junit.Assert.assertEquals(Assert.java:146) at
	 * it.uniroma3.diadia.ambienti.TestLabirintoBuilder.
	 * testLabirintoCompletoConTutteLeStanze(TestLabirintoBuilder.java:310) at
	 * java.lang.reflect.Method.invoke(Method.java:498) at
	 * java.util.ArrayList.forEach(ArrayList.java:1259) at
	 * java.util.ArrayList.forEach(ArrayList.java:1259)
	 * 
	 */
	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo) != null;
	}

	public int getNumeroStanzeAdiacenti() {
		return this.stanzeAdiacenti.size();
	}

	// restituisce la mappa delle stanze adiacenti (usata nei test con assertEquals
	// su Map)
	public Map<String, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	// getDirezioni() deve restituire List (i test usano Collections.singletonList e
	// containsAll)
	public List<String> getDirezioni() {
		return new ArrayList<>(this.stanzeAdiacenti.keySet());
	}

	// getAttrezzi() deve restituire List (i test usano Arrays.asList e indexOf)
	public List<Attrezzo> getAttrezzi() {
		return new ArrayList<>(this.attrezzi.values());
	}

}