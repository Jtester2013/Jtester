package type_inference.data_detained;

public class SecurityExample {
	public static void main() {
		User user = new User();
		String input = user.getUserInput();
		
		String sql = input;
		DatabaseAccessor db = new DatabaseAccessor();
		String output = db.getCriticalData(sql);
		user.sendOutput(output);
	}
}
