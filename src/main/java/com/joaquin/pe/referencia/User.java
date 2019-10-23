package com.joaquin.pe.referencia;

public class User {

	private String nombre;

	public User(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public static void referenciaAMetodoEstatico() {
		
		System.out.println("Probando referencia a Metodo Estatico");
	}
	
	public void referenciaAMetodoParticular() {
		
		System.out.println("Probando referencia a Metodo Particular");
	}
	
	public void mostrarNombre() {
		System.out.println(nombre);
	}
	
}
