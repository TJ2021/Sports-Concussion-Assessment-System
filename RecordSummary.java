
public class RecordSummary {
	private int totalSymptoms;
	private int totalSeverityScore;
	private String overallRating;
	
	public RecordSummary(int totalSymptoms, int totalSeverityScore, String overallRating) {
		this.totalSymptoms = totalSymptoms;
		this.totalSeverityScore = totalSeverityScore;
		this.overallRating = overallRating;	
	}
	
	public int gettotalsymptoms() {
		return totalSymptoms;
	}
	
	public int gettotalSeverityScore() {
		return totalSeverityScore;
	}
	
	public String getoverallRating() {
		return overallRating;
	}
	
	
}
