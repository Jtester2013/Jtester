package plugin.ui.window.configuration.confighandlers;

import java.util.LinkedList;

import plugin.ui.window.configuration.confighandlers.Static.MetricData;
import plugin.ui.window.configuration.confighandlers.Static.MetricsHandler;
import plugin.ui.window.configuration.confighandlers.Static.OptionsHandler;
import plugin.ui.window.configuration.confighandlers.Static.RulesTreeHandler;

public class StaticHandler {
	boolean enableStaticAnalysis;
	int reportLimitForPerRule;
	boolean skipGeneratedTestClasses;
	boolean applySuppresions;
	
	RulesTreeHandler rulesTreeHandler;
	MetricsHandler metricsHandler;
	OptionsHandler optionsHandler;
	
	public boolean setRule(String ruleName){
		return true;
	}
	public LinkedList<String> getRules(){
		return null;
	}
	public boolean setMetrics(LinkedList<MetricData> metrics){
		return true;
	}
	public LinkedList<MetricData> getMetrics(){
		return null;
	}
	
	
	
	public boolean isEnableStaticAnalysis() {
		return enableStaticAnalysis;
	}
	public void setEnableStaticAnalysis(boolean enableStaticAnalysis) {
		this.enableStaticAnalysis = enableStaticAnalysis;
	}
	public int getReportLimitForPerRule() {
		return reportLimitForPerRule;
	}
	public void setReportLimitForPerRule(int reportLimitForPerRule) {
		this.reportLimitForPerRule = reportLimitForPerRule;
	}
	public boolean isSkipGeneratedTestClasses() {
		return skipGeneratedTestClasses;
	}
	public void setSkipGeneratedTestClasses(boolean skipGeneratedTestClasses) {
		this.skipGeneratedTestClasses = skipGeneratedTestClasses;
	}
	public boolean isApplySuppresions() {
		return applySuppresions;
	}
	public void setApplySuppresions(boolean applySuppresions) {
		this.applySuppresions = applySuppresions;
	}
	public RulesTreeHandler getRulesTreeHandler() {
		return rulesTreeHandler;
	}
	public void setRulesTreeHandler(RulesTreeHandler rulesTreeHandler) {
		this.rulesTreeHandler = rulesTreeHandler;
	}
	public MetricsHandler getMetricsHandler() {
		return metricsHandler;
	}
	public void setMetricsHandler(MetricsHandler metricsHandler) {
		this.metricsHandler = metricsHandler;
	}
	public OptionsHandler getOptionsHandler() {
		return optionsHandler;
	}
	public void setOptionsHandler(OptionsHandler optionsHandler) {
		this.optionsHandler = optionsHandler;
	}
	public StaticHandler() {
	}
	
	
}
