package plugin.ui.window.configuration.confighandlers.Static;

import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.swt.widgets.Table;

public class MetricsHandler {
	boolean ifReportMetrics;
	boolean ifReportOutOfRanges;
	Table metricsTable;
	HashMap<String, MetricData> metricsMap;
	
	LinkedList<MetricData> getSelectedMethics() {
		return null;
	}
	
	boolean setMetricsTable(LinkedList<MetricData> metrics){
		return true;
	}
	public boolean isIfReportMetrics() {
		return ifReportMetrics;
	}

	public void setIfReportMetrics(boolean ifReportMetrics) {
		this.ifReportMetrics = ifReportMetrics;
	}

	public boolean isIfReportOutOfRanges() {
		return ifReportOutOfRanges;
	}

	public void setIfReportOutOfRanges(boolean ifReportOutOfRanges) {
		this.ifReportOutOfRanges = ifReportOutOfRanges;
	}

}
