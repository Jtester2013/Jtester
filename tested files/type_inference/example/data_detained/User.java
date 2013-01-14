package DataTaintedExample;

public class User {
	
	public User(){}

	public String getUserInput() {
		return "Untrusted Data";
	}

	public void sendOutput(String result) {

	}

}