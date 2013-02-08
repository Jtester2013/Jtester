package type_inference.data_detained;

public class SecurityExample {
	public static void main() {
		User user = new User();
		String input = user.getUserInput();
		/*
		 * This place is translate user input to sql And mock this behavior
		 */
		String sql = input;
		DatabaseAccessor db = new DatabaseAccessor();
		String output = db.getCriticalData(sql);
		user.sendOutput(output);
	}
}
