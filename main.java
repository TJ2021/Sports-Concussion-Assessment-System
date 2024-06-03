/*
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppUI{
	
	/* Attributes */
	public static Scanner scanner = new Scanner(System.in);
	private static Athlete athleteRef;
	private static MedPrac medPracRef;
	static String[] symptomsList;
	private static Athlete selectedAthlete = null;	
	private static AssessmentApp assessmentApp = new AssessmentApp();
	
	
	/* Methods */
	
	/* Entry point of the application */
	public static void main(String[] args) {
		start();	
	}
	
	
	/**
	 * Initiates the authentication process by prompting the user to select their role and authentication type,
	 * and then performs the authentication based on the selected choices.
	 */
	public static void start() {
		while(true) {
			int roleChoice = selectRole();
			int authChoice = selectAuthType();
			performAuthentication(roleChoice, authChoice);
		}

	}
	
	
	/**
	 * Description: Prompts the user to select their role as either an Athlete or a Medical Practitioner. 
	 *              Validate user input until a valid choice is made.
	 * Input: None (reads user input via the console).
	 * Output: Returns an integer representing user role (1 for Athlete, 2 for Medical practitioner).
	 */
	public static int selectRole() {
		int roleChoice;
		System.out.println("\nWelcome to Sports Concussion Assessment App!!");
		System.out.println("--------------------------------------------- \n");
		System.out.println("Are you an Athlete or Medical practitioner?");
		System.out.println("  1. Athlete");
		System.out.println("  2. Medical Practitioner");
		// Validate user input until a valid choice is made
		while(true) {
			System.out.print("\nEnter your choice: ");
			roleChoice = scanner.nextInt();
			if (roleChoice == 1 || roleChoice == 2) {
				break; // Exit the loop if the choice is valid
			}
			else {
				System.out.println("Invalid choice. Please enter 1 for Athlete or 2 for Medical Practitioner.");
			}	
		}
		return roleChoice;
	}
	
	
	/**
	 * Description: Prompts the user to select their authentication type. 
	 *              Validate user input until a valid choice is made.
	 * Input: None (reads user input via the console).
	 * Output: Returns an integer representing authentication type (1 for Register, 2 for Login).
	 */
	public static int selectAuthType() {
		int authChoice;
		System.out.println("\nIf you're new, please choose 'Register' to create a new account. If you already have an account, choose 'Login'.");
		System.out.println("  1. Register");
		System.out.println("  2. Login");
		// Validate user input until a valid choice is made
		while(true) {
			System.out.print("\nEnter your choice: ");
			authChoice = scanner.nextInt();
			if (authChoice == 1 || authChoice == 2) {
				break; // Exit the loop if the choice is valid
			}
			else {
				System.out.println("Invalid choice. Please enter 1 for Register or 2 for Login");
			}	
		}		
		return authChoice;
		
	}
	
	
	/**
	 * Description: Performs user authentication based on the user's role and authentication type.
	 * Input: roleChoice - An integer representing the user's role (1 for Athlete, 2 for Medical Practitioner).
	 *        authChoice - An integer representing authentication type (1 for Register, 2 for Login).
	 * Output:None
	 */
	public static void performAuthentication(int roleChoice, int authChoice) {
		switch (authChoice) {
		    /* Register */
			case 1:
				assessmentApp.register(roleChoice);
				System.out.println("\nPlease login to continue.");
				// Intentional fall-through to prompt login after registration
	       
			/* Login */	
			case 2:
				// Athlete login
				if (roleChoice == 1) {
					athleteRef = assessmentApp.athleteLogin();
					if (athleteRef != null) {
						System.out.println("\nLogin successful!!.");
						showAthleteOptions();
			        }
					else {
						System.out.print("Login failed. Please check your ID and try again!!!");
						start();
					}
				}
				// Medical practitioner login
				else if (roleChoice == 2) {
					medPracRef = assessmentApp.medPracLogin();
					if (medPracRef != null) {
						System.out.println("\nLogin successful!!.");
						showMedPracOptions();
			        }
					else {
						System.out.print("Login failed. Please check your ID and try again!!!");
						start();
					}
				}		
				break;
				
			/* Default case*/				
			default:
				System.out.println("Invalid choice.");			
		}		
		
	}
	
	/**
	 * Description: Displays a menu of options for Athlete users and prompts them to select an option.
	 * Input: None (reads user input via the console).
	 * Output: None (directly calls methods based on user choice).
	 */
	public static void showAthleteOptions() {
		int athleteChoice;
		System.out.println("\nPlease select an option from the following [1-5].");
		System.out.println("1. Enter symptoms for a new game");
		System.out.println("2. View symptoms summary");
		System.out.println("3. Check your risk level");
		System.out.println("4. View advise from the medical practitioner");
		System.out.println("5. Exit the application");
		// Read and validate the user input
		while(true) {
			System.out.print("\nEnter your choice: ");
			athleteChoice = scanner.nextInt();
			if (athleteChoice  >= 1 && athleteChoice <= 5) {
				break; // Exit the loop if the choice is valid
			}
			else {
				System.out.println("Invalid choice. Please select a valid option [1-5].");
			}	
		}
		processAthleteChoice(athleteChoice);
	}
	
	
	/**
	 * Description: Trigger corresponding functionality based on athlete's choice.
	 * Input: athleteChoice - An integer representing the athlete's choice
	 * Output: None 
	 */
	public static void processAthleteChoice(int athleteChoice) {
		switch (athleteChoice) {
			case 1:
				// Enter severity of each symptom
				Map<String, Integer> symptomsSeverity = getSymptomsSeverity();
				// Store the symptom severity
				athleteRef.setSymptomsSeverity(symptomsSeverity);
				break;	
				
			case 2:
				athleteRef.viewSummary();
				break;
				
			case 3:
				athleteRef.getRiskIndication();
				break;
				
			case 4:
				System.out.println(athleteRef.getAdvise());
				break;
				
			case 5:
				System.out.println("Exiting the application....!");
		        System.exit(0);
		        break;
		}
		
	}
	
	public static void showMedPracOptions() {
		int medPracChoice;
		System.out.println("Please select an option from the following [1-4]");
		System.out.println("1. Select athlete");
		System.out.println("2. Review an athlete's symptoms");
		System.out.println("3. Advise an athlete");
		System.out.println("4. Exit the application");
		// Read and validate the user input
		while(true) {
			System.out.print("\nEnter your choice: ");
			medPracChoice = scanner.nextInt();
			if (medPracChoice  >= 1 && medPracChoice <= 4) {
				break; // Exit the loop if the choice is valid
			}
			else {
				System.out.println("Invalid choice. Please select a valid option [1-5].");
			}	
		}	
		
		processMedPracChoice(medPracChoice);
	}
	
	public static void processMedPracChoice(int medPracChoice) {
		switch(medPracChoice) {
			case 1:
				selectedAthlete = selectAthlete();
				showMedPracOptions();
				break;
				
			case 2:
				while(selectedAthlete == null) {
					System.out.println("No athlete selected. Please select an athlete from the below list: ");
					selectedAthlete = selectAthlete();
				}
					selectedAthlete.viewSummary();
					System.out.print("Risky condition of the athlete: ");
					selectedAthlete.getRiskIndication();
				
				break;
				
			case 3:
				while (selectedAthlete == null) {
					System.out.println("No athlete selected.");
					selectedAthlete = selectAthlete();
				}		
				if (selectedAthlete.getRecords().size() == 0) {
					System.out.println("No recent medical records exist for this athlete.");
				}
				else {
					medPracRef.giveAdvise(selectedAthlete); 
				}			
				break;
				
			case 4:
				System.out.println("Exiting the application....!");
		        System.exit(0);
		        break;	
		}
	}

	public static Athlete selectAthlete() {
		int selectedAthleteId;
		System.out.println("Please select an athlete from the below list:\n");
		ArrayList <Athlete> athletes = assessmentApp.getAthletes();
		// Display list of athletes to choose from
		for (Athlete athlete: athletes) {
			System.out.println(athlete.getId() + " : " + athlete.getName());
		}
		// Prompt for user input to select an athlete
		System.out.println("Enter ID of the selected athlete : ");
		selectedAthleteId = scanner.nextInt(); 
		// Find the selected athlete
		for (Athlete athlete: athletes) {
			if (athlete.getId() == selectedAthleteId) {
				selectedAthlete = athlete;
			}
		}
		return selectedAthlete;
	}
		
	/**
	 * Description: Prompts the Athlete to rate the severity of each symptom.
	 * Input: None
	 * Output: symptomsSeverity - A Map<String, Integer> containing symptom-severity pairs entered by the Athlete.
	 */
	public static Map<String, Integer> getSymptomsSeverity() {
		int severity;
		Map<String, Integer> symptomsSeverity = new HashMap<>();
		
		// Array of symptoms
		symptomsList = new String[] { "Headache", "Pressure in Head", "Neck Pain", "Nausea or Vomiting", "Dizziness",
                					  "Blurred vision", "Balance problems", "Sensitivity to light", "Sensitivity to noise",
                					  "Feeling slowed down", "Feeling like 'in a fog'", "Don't feel right",
                					  "Difficulty concentrating", "Difficulty remembering", "Fatigue or low energy",
                					  "Confusion", "Drowsiness", "Trouble falling asleep", "More emotional",
                					  "Irritability", "Sadness", "Nervous or Anxious"};
		
		// Display symptoms and prompt for severity
		System.out.println("\nPlease rate the severity of each symptom\n");
		for (String symptom:symptomsList) {
			while(true) {
				System.out.print("Please enter your " + symptom + " score  (none (0), mild (1-2), moderate (3-4), & severe (5-6): ");
				severity = scanner.nextInt();
				if (severity >= 0 && severity <= 6) {
					break;
				}
				else {
					System.out.println("Invalid input.Try again!!\n");
				}
			}
			symptomsSeverity.put(symptom, severity);
		}

		return symptomsSeverity;
	}
	

}
