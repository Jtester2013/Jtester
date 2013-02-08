package type_inference.data_detained;

@interface DataType {
	enum Level {
		tainted, untainted
	};
	Level level();
}


public class DatabaseAccessor {

	@DataType(level = DataType.Level.untainted)
	public String getCriticalData(@DataType(level = DataType.Level.untainted) String sql) {
		// Handle the sql statement and return database data.
		// Here is mock behavior
		return "Security Data";
	}
	
	public DatabaseAccessor(){}
}
