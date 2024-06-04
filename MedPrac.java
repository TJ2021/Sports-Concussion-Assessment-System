
import java.util.Scanner;

public class MedPrac {
	private int id;
	private String name;
	public static Scanner scanner = new Scanner(System.in);
	
	public MedPrac(String name) {
		this.id = 1000 + (int)(Math.random()*9000);
		this.name = name;
		System.out.println("\nRegistration successful!!!");
		System.out.println("Your ID is: " + id + ".\nPlease keep it secure for all future access to the system.");	
	}
	
	public int getId() {
		return id;
	}
	
	public String getName(){
		return name;
	}
	public void giveAdvise(Athlete athlete) {
			System.out.print("Enter your advice for this athlete: ");
		    String advise = scanner.nextLine();
		    athlete.setAdvise(advise);
		
	}
	
}
