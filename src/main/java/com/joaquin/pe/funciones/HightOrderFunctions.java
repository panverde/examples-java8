package com.joaquin.pe.funciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HightOrderFunctions implements SumarInterface {

	public static void main(String[] args) {
		HightOrderFunctions hof= new HightOrderFunctions();
		
		System.out.println(hof.suma(2,3));
		
		System.out.println(hof.apply(2, 4));
		
		SumarInterface sumarInterface = (a,b)->a+b; /*new SumarInterface() {
			
			@Override
			public int apply(int a, int b) {
				return a+b;
			}
		};*/
		System.out.println(hof.sumaHighOrderFun(sumarInterface, 2, 5));
		
		//interface function
		
		Function<String, String> convertirMayuscula = e->e.toUpperCase();
		hof.imprimirMayuscula(convertirMayuscula,"Joaquin");
		
		//funtional bifunction & interface funtional predicate
		
		List<Integer> numeros=Arrays.asList(6,8,67,-65,-34,3,-5);
		BiFunction<
			List<Integer>,
				Predicate<Integer>, 
					List<Integer>> filtrar;
		filtrar = (lista,predicado)->lista.stream().filter(e->predicado.test(e))
				.collect(Collectors.toList());
		
		System.out.println(filtrar.apply(numeros, e->e>0));
		
		//interface consumer
		
		List<String> nombres = new ArrayList<String>();
		nombres.add("Joaquin");
		nombres.add("pac");
		nombres.add("ana");
		
		hof.filtrar(nombres,e->System.out.println(e),6);
		
		
	}
	
	public void filtrar(List<String> lista,Consumer<String> consumer,int maximoCaracters) {
		lista.stream().filter(logicaPredicado(maximoCaracters))
			.forEach(consumer);
	}
	
	public Predicate<String> logicaPredicado(int maximoCaracteres){
		return e->e.length()<maximoCaracteres;
	}
	
	
	public int suma(int a , int b) {
		return a+b;
	}

	@Override
	public int apply(int a, int b) {
		return a+b;
	}
	
	public int sumaHighOrderFun(SumarInterface sumar, int a , int b) {
		return sumar.apply(a, b);
	}
	
	public void imprimirMayuscula(Function<String, String> function,String nombre) {
		System.out.println(function.apply(nombre));
	}
}
