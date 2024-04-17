package org.avbo.tpsit.jdbcssample;

public class Studente {
	public Studente(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}
	
	public synchronized String getNome() {
		return nome;
	}
	public synchronized void setNome(String nome) {
		this.nome = nome;
	}
	public synchronized String getCognome() {
		return cognome;
	}
	public synchronized void setCognome(String cognome) {
		this.cognome = cognome;
	}
	private String nome;
	private String cognome;
}
