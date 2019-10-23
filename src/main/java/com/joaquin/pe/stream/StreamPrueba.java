package com.joaquin.pe.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPrueba {

	private static List<User> users;
	
	public static void main(String[] args) {
		setUpUser();
		Stream stream =Stream.of(users);
		users.stream();
		
		users.stream().forEach(e->e.setNombre(e.getNombre()+" Apellido"));
		imprimirLista();
		//map
		List<String> lista= users.stream().map(e->e.getNombre()).collect(Collectors.toList());
		lista.stream().forEach(e->System.out.println(e));
		
		System.out.println("-----------FILTERS-----------");
		//filters
		setUpUser();
		List<User> userFilter=users.stream()
				.filter(e->e.getNombre()!="Joaquin")
				.filter(e->e.getId()<3)
				.collect(Collectors.toList());
		
		userFilter.stream().forEach(e->System.out.println(e.getId()+" "+e.getNombre()));
		System.out.println("-----------find first-----------");
		setUpUser();
		User user=users.stream()
				.filter(e->e.getNombre().equals("Joaquin"))
				.findFirst()
				.orElse(null);
		
		System.out.println(user.getId()+" "+user.getNombre());
		
		System.out.println("-----------Flat Map-----------");
		
		List<List<String>> nombresVariasListas=new ArrayList<List<String>>(
				Arrays.asList(
						new ArrayList<String>(Arrays.asList("Joaquin","Joaquin2","Joaquin3")),
						new ArrayList<String>(Arrays.asList("Joaquin4","Joaquin5"))
						));
		
		List<String> nombreUnicaLista = nombresVariasListas.stream()
				.flatMap(e->e.stream())
				.collect(Collectors.toList());
		
		nombreUnicaLista.stream().forEach(e->System.out.println(e));
		
		System.out.println("-----------Pick-----------");
		setUpUser();
		
		List<User> user2 = users.stream()
				.peek(e-> e.setNombre(e.getNombre() + " Apellido"))
				.collect(Collectors.toList());
		
		user2.stream().forEach(e-> System.out.println(e.getNombre()));
		
		System.out.println("-----------Count-----------");
		setUpUser();
		
		long numeroFiltrado = users.stream()
				.filter(e->e.getId()<3)
				.count();	
		System.out.println(numeroFiltrado);
		
		System.out.println("-----------Skip y Limmit-----------");		
		
		String[] abc = {"a","b","c","d","e","f","g","h","i","j"};
		List<String> abcFilter = Arrays.stream(abc)
				.skip(2)
				.limit(4)
				.collect(Collectors.toList());
		
		abcFilter.stream().forEach(e->System.out.println(e));
		
		System.out.println("-----------Sorted-----------");	
		
		setUpUser();
		
		users = users.stream()
			.sorted(Comparator.comparing(User::getNombre))
			.collect(Collectors.toList());
		
		imprimirLista();
		
		System.out.println("-----------Min mAX-----------");
		
		User userMin = users.stream()
				.min(Comparator.comparing(User::getId))
				.orElse(null);
		System.out.println(userMin.getId());
		
		User userMax = users.stream()
				.max(Comparator.comparing(User::getId))
				.orElse(null);
		System.out.println(userMax.getId());
		
		System.out.println("-----------Distinct-----------");
		
		String[] abc1 = {"a","b","c","d","e","f","g","h","i","j","a","c"};
		
		List<String> abcFilter1= Arrays.stream(abc1)
				.distinct().collect(Collectors.toList());
		abcFilter1.stream().forEach(e->System.out.println(e));
		
		
		System.out.println("-----------all Match, any match , nonematch-----------");
		
		List<Integer> listaNumeros = Arrays.asList(100,200,300,43000);
		
		boolean allMatch = listaNumeros.stream().allMatch(e->e>301);
		System.out.println(allMatch);
		
		boolean anyMatch = listaNumeros.stream().anyMatch(e->e>301);
		System.out.println(anyMatch);
		
		boolean noneMatch = listaNumeros.stream().noneMatch(e->e>10000);
		System.out.println(noneMatch);
		
		System.out.println("-----------Sum, Average range-----------");
		
		setUpUser();
		
		double result= users.stream()
				.mapToInt(User::getId)
				.average()
				.orElse(0);
		
		System.out.println(result);
		
		result=users.stream()
				.mapToInt(User::getId)
				.sum();
		
		System.out.println(result);
		
		System.out.println(IntStream.range(0, 100).sum());
		
		System.out.println("-----------Reduce-----------");
		
		setUpUser();
		
		int numero = users.stream()
				.map(User::getId)
				.reduce(100, Integer::sum);
		System.out.println(numero);
		
		System.out.println("-----------joining-----------");
		
		setUpUser();
		String name = users.stream()
				.map(User::getNombre)
				.collect(Collectors.joining(" - "))
				.toString();
		
		System.out.println(name);
		
		System.out.println("-----------toSet-----------");
		setUpUser();
		Set<String> setNames = users.stream()
				.map(User::getNombre)
				.collect(Collectors.toSet());
		
		setNames.stream().forEach(e -> System.out.println(e));
		
		System.out.println("-----------sumarizingDouble-----------");
		setUpUser();	
		
		DoubleSummaryStatistics statistics = users.stream()
				.collect(Collectors.summarizingDouble(User::getId));
		System.out.println(statistics.getAverage()+ " "+ statistics.getMax()+ " " + statistics.getMin()+" " +
							statistics.getCount() + " " + statistics.getSum());
		
		
		DoubleSummaryStatistics statistics1 = users.stream()
				.mapToDouble(User::getId)
				.summaryStatistics();
		System.out.println(statistics1.getAverage()+ " "+ statistics1.getMax()+ " " + statistics1.getMin()+" " +
				statistics1.getCount() + " " + statistics1.getSum());
		
		System.out.println("-----------partitioningBy-----------");
		setUpUser();
		List<Integer> numeros = Arrays.asList(5,6,78,34,67,232,89);
		Map<Boolean, List<Integer>> esMayor = numeros.stream()
				.collect(Collectors.partitioningBy(e->e>10));
		
		esMayor.get(true).stream().forEach(e->System.out.println(e));
		System.out.println("----");
		esMayor.get(false).stream().forEach(e->System.out.println(e));
		
		System.out.println("-----------groupingBy-----------");
		setUpUser();
		
		Map<Character,List<User>> grupoAlfa=users.stream()
				.collect(Collectors.groupingBy(e->new Character(e.getNombre().charAt(0))));
		
		grupoAlfa.get('J').stream().forEach(e->System.out.println(e.getNombre()));
	
		grupoAlfa.get('A').stream().forEach(e->System.out.println(e.getNombre()));
		
		grupoAlfa.get('M').stream().forEach(e->System.out.println(e.getNombre()));
	
		
		System.out.println("-----------Mapping-----------");
		setUpUser();
		
		List<String> personas=users.stream()
				.collect(Collectors.mapping(User::getNombre, Collectors.toList()));
		
		personas.stream().forEach(e->System.out.println(e));
		
		System.out.println("-----------Stream Paralelo-----------");
		setUpUser();
		
		long tiempo1 = System.currentTimeMillis();		
		lista.stream().forEach(e->convertirMayusculas(e));
		long tiempo2 = System.currentTimeMillis();
		System.out.println(tiempo2-tiempo1);
		
		tiempo1 = System.currentTimeMillis();	
		lista.parallelStream().forEach(e->convertirMayusculas(e));
		tiempo2 = System.currentTimeMillis();
		System.out.println(tiempo2-tiempo1);
		
		
		
	}
	private static String convertirMayusculas(String nombre) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return nombre.toUpperCase();
	}
	
	
	public static void setUpUser() {
		users = new ArrayList<>();
		users.add(new User(1,"Joaquin1"));
		users.add(new User(2,"Joaquin2"));
		users.add(new User(3,"Maria"));
		users.add(new User(4,"Joaquin4"));
		users.add(new User(5,"Ana"));
		users.add(new User(6,"Joaquin"));
	}
	
	
	private static void imprimirLista() {
		users.stream().forEach(e->System.out.println(e.getId()+ " "+e.getNombre()));
	}
	
	//map
	
	
	
}
