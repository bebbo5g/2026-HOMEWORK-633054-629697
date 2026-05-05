package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public interface Comando {
  public String getNome();

  public String getParametro();

  public void esegui(Partita partita);
}
