package type_inference.data_detained;

public class User {
	
	public User(){}

	@DataType(level = DataType.Level.tainted)
	public String getUserInput() {
		return "Untrusted Data";
	}

	public void sendOutput(String result) {

	}
}