import java.util.Map;

public class AthleteRecord {
	
	// Static counter for unique record IDs
	private static int counter = 0;
	private int recordId;
	private int athleteId;
	private Map<String, Integer> symptomsSeverity;
	
	public AthleteRecord(int id, Map<String, Integer> symptomsSeverity) {
		this.athleteId = id;
		this.symptomsSeverity = symptomsSeverity;
		this.recordId = counter++;	
	}
	
	public int getRecordId() {
		return recordId;
	}
	
	public Map<String, Integer> getsymptomsSeverity(){
		return symptomsSeverity;
	}
	
	public int getTotalSymptoms() {
		int totalSymptoms = 0;
		// Iterate through all symptoms in the record. 
		// If score greater than 0, count it as a symptom.
		for (int score:symptomsSeverity.values()) {
			if (score > 0) {
				totalSymptoms += 1;
			}
		}
		return totalSymptoms;
	}
	
	public int getTotalSeverityScore() {
		int totalSeverityScore = 0;	
		for (int score:symptomsSeverity.values()) {
			// Add severity score to the total score
			totalSeverityScore += score;
		}
		return totalSeverityScore;
	}
	
}
