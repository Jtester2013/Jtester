package plugin.ui.window.configuration.confighandlers;



public class ConfigurationHandler {
	private ScopeHandler scopeHandler2;
	private CommonHandler commonHandler2;
	private StaticHandler staticHandler2;
	CodeReviewHandler codeReviewHandler;
	ExecutionHandler executionHandler;
	GenerationHandler generationHandler;
	GoalsHandler goalsHandler;
	ScopeHandler scopeHandler;
	StaticHandler staticHandler;
	CommonHandler commonHandler;
	
	/**
	 * refresh the data in GUI with the information saved in the configuration file
	 * linked to filePath
	 * @param filePath
	 * @return
	 */
	public boolean synchronizeConfiguration(String filePath){
		return true;
	}
	
	/**
	 * save the test configuration details in the GUI
	 * @param configrationName
	 * @return
	 */
	public boolean saveConfiguration(String configrationName){
		return true;
	}

	public ConfigurationHandler(StaticHandler staticHandler3, CommonHandler commonHandler3, ScopeHandler scopeHandler3) {
		staticHandler2 = staticHandler3;
		commonHandler2 = commonHandler3;
		scopeHandler2 = scopeHandler3;
	}

}
