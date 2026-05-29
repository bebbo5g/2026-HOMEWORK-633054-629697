package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {
	private List<String> input;
	private int inputIndex;

	// per ogni riga letta (comando), lista dei messaggi prodotti
	private Map<String, List<String>> outputPerComando;
	private String ultimoComando;

	// lista piatta di tutti i messaggi (per accesso semplice)
	private List<String> output;

	public IOSimulator() {
		this(new ArrayList<>());
	}

	public IOSimulator(List<String> input) {
		this.input = input;
		this.inputIndex = 0;
		this.output = new ArrayList<>();
		this.outputPerComando = new LinkedHashMap<>();
		this.ultimoComando = null;
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.output.add(msg);
		// associa il messaggio all'ultimo comando letto
		if (this.ultimoComando != null) {
			this.outputPerComando.computeIfAbsent(this.ultimoComando, k -> new ArrayList<>()).add(msg);
		}
	}

	@Override
	public String leggiRiga() {
		String riga = this.input.get(this.inputIndex++);
		this.ultimoComando = riga;
		this.outputPerComando.putIfAbsent(riga, new ArrayList<>());
		return riga;
	}

	/** Restituisce tutti i messaggi in ordine */
	public List<String> getOutput() {
		return this.output;
	}

	/** Restituisce i messaggi prodotti da un certo comando */
	public List<String> getOutputPerComando(String comando) {
		return this.outputPerComando.getOrDefault(comando, new ArrayList<>());
	}

	/** Restituisce la mappa comando → messaggi */
	public Map<String, List<String>> getOutputPerComandi() {
		return this.outputPerComando;
	}

	/** Restituisce l'i-esimo messaggio prodotto */
	public String getOutput(int i) {
		if (i >= this.output.size())
			return null;
		return this.output.get(i);
	}
}
