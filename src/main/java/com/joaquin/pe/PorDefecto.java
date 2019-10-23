package com.joaquin.pe;

public interface PorDefecto {

	void mostrarNombre(String nombre);
	
	default String nombrePorDefecto(String nombre) {
		return nombre + "Default";
	};
	
}
