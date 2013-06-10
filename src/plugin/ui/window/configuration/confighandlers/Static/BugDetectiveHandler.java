package plugin.ui.window.configuration.confighandlers.Static;

public class BugDetectiveHandler {
	int depthOfAnalysis;
	boolean reportViolations;
	int levelOfReportSimilarViolation;
	public int getDepthOfAnalysis() {
		return depthOfAnalysis;
	}
	public void setDepthOfAnalysis(int depthOfAnalysis) {
		this.depthOfAnalysis = depthOfAnalysis;
	}
	public boolean isReportViolations() {
		return reportViolations;
	}
	public void setReportViolations(boolean reportViolations) {
		this.reportViolations = reportViolations;
	}
	public int getLevelOfReportSimilarViolation() {
		return levelOfReportSimilarViolation;
	}
	public void setLevelOfReportSimilarViolation(int levelOfReportSimilarViolation) {
		this.levelOfReportSimilarViolation = levelOfReportSimilarViolation;
	}
}
