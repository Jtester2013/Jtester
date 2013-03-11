package type_inference.data_detained;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@interface DataType {
	enum Level {
		tainted, untainted
	};
	Level level();
}

@DataType
class DatabaseAccessor {
	@DataType
	public String getCriticalData(
	@DataType(level = DataType.Level.untainted) String sql) {
		return "Security data";
	}
}

class User {
	@DataType(level = DataType.Level.tainted)
	public String getUserInput() {
		return "Untrusted Data";
	}
}
