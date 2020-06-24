package cse222.group8.server;
import java.io.File;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

import cse222.group8.server.DataStructures.*;


/**
 * The type Main.
 */
public class Main {


	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		ShelterSystem system = new ShelterSystem();
		readCityInfo(system);
		insertTownsTests(system);
		System.out.println("Hello word");
		printCitiesInfo(system);
//		JavalinServer javalinServer = new JavalinServer(system);
//		javalinServer.run();
	}
	
	private static void testAdminUI() {
		
		// TEST
		ShelterSystem system = new ShelterSystem();
		Shelter shelter = new Shelter("Test1", null, null, 10, 10,
									 "","","pass1",system);
		system.addCapChangeRequest(new CapacityChangeRequest("Ist", "Krtl", shelter, 120,130));
		system.addNewShelterRequest(new ShelterRequest("Ist", "Krtl2", shelter));
		system.addRemoveShelterRequest(new ShelterRequest("Ist3", "Krtl3", shelter));
		AdminUI ui = new AdminUI(system);
		ui.run();
		
	}


	private static void testCities() {
		
		//its a dummy system for test	
		ShelterSystem system = new ShelterSystem();
		BinarySearchTree<City> cities 	= system.getCitiesBST();
		TreeMap<Integer, City> cityIds	= system.getCityIdsMap();
		ListGraph borderCities 			= system.getBorderCities();
		
		

		readCityInfo(system);
		
		
		City city = cities.find(new City("Mersin", 0,null));
		System.out.println(city.getCityId());
		
		System.out.println(cityIds.get(city.getCityId()).getName());
		
		System.out.println(" ");
		
		
		Iterator<Edge> iter = borderCities.edgeIterator(city.getCityId());
		while(iter.hasNext()) {
			
			int id = iter.next().getDest();
			
			System.out.println(cityIds.get(id).getName());
			
		}
	}

	protected static void printCitiesInfo(ShelterSystem system){
		BinarySearchTree<City> cities 	= system.getCitiesBST();
		System.out.println(cities.toString());
	}

	protected static void insertTownsTests(ShelterSystem system){
		BinarySearchTree<City> cities 	= system.getCitiesBST();

		File file = new File("C:\\Users\\seyma\\Desktop\\Veri Proje\\Files\\Towns.txt");

		try {

			Scanner sc = new Scanner(file);

			while( sc.hasNextLine() ) {

				String str = sc.nextLine();
				String[] townsInfo = str.split("-");
 				String id = townsInfo[1];
				City city = system.getCity(Integer.parseInt(id));

				city.towns.add(new Town(townsInfo[0], city, system));

			}

			sc.close();

		}
		catch ( Exception e ) {
			System.out.println(e.getMessage());
			System.err.println("Something went wrong while reading towns from file");
			System.exit(0);

		}

	}


	/**
	 * Read city ınfo.
	 *
	 * @param system the system
	 */
	protected static void readCityInfo(ShelterSystem system) {
		
		BinarySearchTree<City> cities 	= system.getCitiesBST();
		TreeMap<Integer, City> cityIds	= system.getCityIdsMap();
		ListGraph borderCities 			= system.getBorderCities();
		
		
		File file = new File("C:\\Users\\seyma\\Desktop\\Veri Proje\\Files\\Cities.txt");
		
		try {
			
			Scanner sc = new Scanner(file);
			
			while( sc.hasNextLine() ) {
				
				String str = sc.nextLine();
				String[] keys = str.split(" ");
				
				int cityID = Integer.parseInt(keys[0]);
				City city = new City( keys[1], cityID, system );
				
				
				cities.add(city);
				
				cityIds.put(cityID, city);
				
				for(int i=2; i < keys.length; i++ ) {
					
					Edge edge = new Edge(cityID, Integer.parseInt(keys[i]));
					borderCities.insert(edge);
								
				}
						
				
			}
			
			sc.close();
				
		}
		catch ( Exception e ) {
			System.out.println(e.getMessage());
			System.err.println("Something went wrong while reading cities from file");
			System.exit(0);
			
		}
		
		
	}
	
	
	

}
