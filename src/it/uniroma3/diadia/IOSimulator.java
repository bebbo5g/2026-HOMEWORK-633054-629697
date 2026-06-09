package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IOSimulator implements IO {

	private List<String> comandi;
	private List<String> messaggi;
	private Map<String, List<String>> comandoMessaggi;
	private int posizioneComando;

	public IOSimulator(List<String> comandi) {
		this.comandi = comandi;
		this.messaggi = new ArrayList<>();
		this.comandoMessaggi = new LinkedHashMap<>();
		this.posizioneComando = 0;
	}

	@Override
	public String leggiRiga() {
		String comando = this.comandi.get(posizioneComando++);
		this.comandoMessaggi.put(comando, new ArrayList<>());
		return comando;
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggi.add(msg);
		if (!this.comandoMessaggi.isEmpty()) {
			String ultimoComando = this.comandi.get(posizioneComando - 1);
			this.comandoMessaggi.get(ultimoComando).add(msg);
		}
	}

	public String getMessaggio(int posizione) {
		return this.messaggi.get(posizione);
	}

	public boolean contieneMessaggio(String messaggio) {
		return this.messaggi.contains(messaggio);
	}

	public List<String> getMessaggiDelComando(String comando) {
		return this.comandoMessaggi.get(comando);
	}

	public List<String> getMessaggi() {
		return this.messaggi;
	}
}
