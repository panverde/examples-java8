package com.joaquin.pe;

public class Lambda implements PorDefecto {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("java.runtime.version"));
	
		
		MiNombre miNombreAnonimo = new MiNombre() {
			
			public String miNombre() {
				// TODO Auto-generated method stub
				return "Joaquin Anonimo";
			}
		};
		System.out.println(miNombreAnonimo.miNombre());
		
		MiNombre miNombreLambda=()->"Joaquin Lambda";
		System.out.println(miNombreLambda.miNombre());
		
		Sumar suma = new Sumar() {			
			@Override
			public int suma(int a, int b) {
				// TODO Auto-generated method stub
				return a+b;
			}
		};
		System.out.println(suma.suma(2, 4));
		
		Sumar suma1=(a,b)->a+b;
		System.out.println(suma1.suma(2, 4));
		
		Sumar suma2=(a,b)->{
			a=b*b;
			a=a+b;
			System.out.println("dentro lambda");
			return a;
		};
		System.out.println(suma2.suma(2, 4));
		
		
		Lambda l = new Lambda();
		System.out.println(l.nombrePorDefecto("Joaquin "));
		
	}

	@Override
	public void mostrarNombre(String nombre) {
		// TODO Auto-generated method stub
		
	}

}
