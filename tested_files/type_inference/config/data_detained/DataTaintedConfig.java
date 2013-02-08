package type_inference.data_detained;

@interface DataType {
	enum Level {
		tainted, untainted
	};
	Level level();
}

class DatabaseAccessor {
	public String getCriticalData(
	@DataType(level = DataType.Level.untainted) String sql) {
	}
}

class User {
	@DataType(level = DataType.Level.tainted)
	public String getUserInput() {
	}
}
