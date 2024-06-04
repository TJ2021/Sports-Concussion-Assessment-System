
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Athlete {
	private int id;
	private String name;
	private int age;
	private String advise = "Awaiting medical practitioner's review.";
	private Queue<AthleteRecord> records = new LinkedList<>();
	private Map<Integer, RecordSummary> summaries = new HashMap<>();
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
	
	// Constructor 
	public Athlete(String name, int age) {
		this.id = 1000 + (int)(Math.random()*9000);
		this.name = name;
		this.age = age;
		System.out.println("\nRegistration successful!!!");
		System.out.println("Your ID is: " + id + ".\nPlease keep it secure for all future access to the system.");		
	}
	
	// Getter method for id
	public int getId() {
		return id;
	}
	
	// Getter method for name
	public String getName() {
		return name;
	}
	
	// Setter method for name
	public int getAge() {
		return age;
		
	}
	public void setAdvise(String advise) {
		this.advise = advise;
	}
	
	public String getAdvise() {
		return advise;
	}
	
	public Queue<AthleteRecord> getRecords() {
		return records;
	}
	
	private void addRecord(AthleteRecord athleteRecord) {
		
		// Add a new record
		records.add(athleteRecord);
		// If the size exceeds 5, remove the least recent record
	    if (records.size() > 5) {
	    	AthleteRecord removedRecord = records.poll(); // Remove the least recent record
	    	int removedId = removedRecord.getRecordId(); 
	    	summaries.remove(removedId); // Remove corresponding record summary
	    }
		System.out.println("Record successfully saved");
	}
	
	
	/**
	 * Description: Create a new record for the athlete based on the provided symptom severity.
	 * Input: symptomsSeverity - A Map<String, Integer> containing symptom-severity pairs entered by the Athlete.
	 * Output: None.
	 */
	public void setSymptomsSeverity(Map<String, Integer> symptomsSeverity) {
		// Create a new record for the current game
		AthleteRecord athleteRecord = new AthleteRecord(id,symptomsSeverity);
		addRecord(athleteRecord);
		findRecordSummary(athleteRecord);
	}
	
	// Find summary of the current record
	private void findRecordSummary(AthleteRecord athleteRecord) {
		int recordId = athleteRecord.getRecordId();
		int totalSymptoms = athleteRecord.getTotalSymptoms();
		int totalSeverityScore = athleteRecord.getTotalSeverityScore();
		String overallRating = calculateOverallRating();
		RecordSummary summary = new RecordSummary(totalSymptoms, totalSeverityScore, overallRating);
		summaries.put(recordId, summary);
	}
	
	private String calculateOverallRating() {
		if (records.size() >= 2) {
			ArrayList <AthleteRecord> recordsList = new ArrayList <> (records);
			AthleteRecord lastRecord = recordsList.get(records.size() - 1);
			AthleteRecord prevRecord = recordsList.get(records.size() - 2);
			int totalsymptomsDiff = computeTotalSymptomsDiff(lastRecord, prevRecord);
			int severityScoreDiff = computeSeverityScoreDiff(lastRecord, prevRecord);	
			if (totalsymptomsDiff < 3 &&  severityScoreDiff < 10){
				return "No difference";
			}
			else if (totalsymptomsDiff < 3 &&  severityScoreDiff >= 10) {
				return "Unsure";
			}
			else if (totalsymptomsDiff >= 3 || severityScoreDiff >= 15){
				return "Very different";
			}
			else {
				return "Undefined";
			}
		}		
		else {
			return "Not enough data";
		}
			
	}
	
	public int computeTotalSymptomsDiff(AthleteRecord lastRecord, AthleteRecord prevRecord) {
		return Math.abs(lastRecord.getTotalSymptoms() - prevRecord.getTotalSymptoms());
	}
	
	public int computeSeverityScoreDiff(AthleteRecord lastRecord, AthleteRecord prevRecord) {
		return Math.abs(lastRecord.getTotalSeverityScore() - prevRecord.getTotalSeverityScore());
	}
	
	
	public void viewSummary() {
		if (records.size() <= 0) {
			System.out.println("\nNo records available");
		}
		else {
			int game = 1;
			System.out.println("Summary of last five games starting from the least recent one:");
			for (RecordSummary summary: summaries.values()) {
				System.out.println("Game " + game + " :");
				System.out.println("Total symptoms: " + summary.gettotalsymptoms());
				System.out.println("Severity score: " + summary.gettotalSeverityScore());
				System.out.println("Overall Rating: "+ summary.getoverallRating());
				game += 1;
			}
		}
		
	}
	public void getRiskIndication() {
		ArrayList <AthleteRecord> recordsList = new ArrayList <> (records);
		AthleteRecord lastRecord = recordsList.get(records.size() - 1);
		int recordId = lastRecord.getRecordId();
		RecordSummary recentSummary = summaries.get(recordId);
		String risk = recentSummary.getoverallRating();
		if (risk == "No difference") {
			System.out.println(GREEN + risk + RESET);
		}
		
		else if (risk == "Unsure") {
			System.out.println(YELLOW + risk + RESET);
		}
		else if (risk =="Very different") {
			System.out.println(RED + risk + RESET);
		}
		else {
			System.out.println(risk);
		}
	}
	
}
