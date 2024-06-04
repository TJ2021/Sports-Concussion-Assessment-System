import java.util.ArrayList;
import java.util.Scanner;

public class AssessmentApp {
	
	// Array list to store athlete objects
	ArrayList <Athlete> athletes = new ArrayList <> ();
	//Array list to store medical practitioner objects.
	ArrayList <MedPrac> medicalPractitioners = new ArrayList <> ();
	
	public static Scanner scanner = new Scanner(System.in);
	
	public ArrayList <Athlete> getAthletes(){
		return athletes;
	}
	
	public ArrayList <MedPrac> getMedPracs(){
		return medicalPractitioners;
	}
	
	
	/**
	 * Description: Registers a new user based on the selected role.
	 * Input: roleChoice - An integer representing the user's selected role (1 for Athlete, 2 for Medical Practitioner). 
	 * Output: None
	 */
	public void register(int roleChoice) {
		// Register athlete
		if (roleChoice == 1) {
			addAthlete();	
		}
		// Register medical practitioner
		else if (roleChoice == 2) {
			addMedPrac();
		}
	}
	
	
	/**
	 * Description: Logs in an Athlete based on their provided login ID. 
	 * Input: None (reads user input via the console for the login ID). 
	 * Output:Returns an Athlete object corresponding to the provided login ID, if found. Otherwise, returns null.
	 */
	public Athlete athleteLogin() {
		System.out.print("Enter your login ID: ");
		int id = scanner.nextInt();
		Athlete athlete = getAthlete(id);
		return athlete;
	}
	
	
	/**
	 * Description: Logs in medical practitioner based on their provided login ID. 
	 * Input: None (reads user input via the console for the login ID). 
	 * Output:Returns a MedPrac object corresponding to the provided login ID, if found. Otherwise, returns null.
	 */
	public MedPrac medPracLogin() {
		System.out.print("Enter your login ID: ");
		int id = scanner.nextInt();
		MedPrac medPrac = getMedPrac(id);
		if (medPrac != null) {
			System.out.println("Logged in successfully.");
        }
		else {
			System.out.print("Login failed. Please check your ID and try again.\n");
			medPracLogin();
		}
		return medPrac;
	}
	
	
	
	/**
	 * Description: Adds a new Athlete to the system. 
	 * Input: None (reads user input via the console for name and age).
	 * Output: None
	 */
	private void addAthlete() {
		System.out.print("\nEnter your full name: ");
		String name = scanner.nextLine();
		System.out.print("\nEnter your age: ");
		int age = scanner.nextInt();
		Athlete athlete = new Athlete(name, age);
		athletes.add(athlete);
	}
	
	
	/**
	 * Description: Adds a new medical practitioner to the system. 
	 * Input: None (reads user input via the console for name).
	 * Output: None
	 */
	private void addMedPrac() {
		System.out.print("\nEnter your full name: ");
		String name = scanner.nextLine();
		MedPrac medPrac = new MedPrac(name);
		medicalPractitioners.add(medPrac);
	}
	
	
	
	/**
	 * Description: Retrieves an Athlete object based on the provided athlete ID.
	 * Input: athleteId - An integer representing the unique ID of the Athlete.
	 * Output: Returns an Athlete object corresponding to the provided athlete ID if found. Otherwise, returns null.
	 */
	public Athlete getAthlete(int athleteId) {
		for (Athlete athlete: athletes) {
			if (athlete.getId() == athleteId) {
				return athlete;
			}
		}
		return null;
	}
	
	/**
	 * Description: Retrieves a MedPrac object based on the provided login ID.
	 * Input: athleteId - An integer representing the unique ID of the medical practitioner.
	 * Output: Returns a MedPrac object corresponding to the provided  ID if found. Otherwise, returns null.
	 */
	
	public MedPrac getMedPrac(int medPracId) {
		for (MedPrac medPrac: medicalPractitioners) {
			if (medPrac.getId() == medPracId) {
				return medPrac;
			}
		}
		return null;
	}
}
