package DataTaintedExample;

public class DatabaseAccessor {

	public String getCriticalData(String sql) {
		// Handle the sql statement and return database data.
		// Here is mock behavior
		return "Security Data";
	}
	
	public DatabaseAccessor(){}
}
