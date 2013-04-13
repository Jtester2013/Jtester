package data_flow;

public class AvailableExp {
	public int test() {
		int a = 0;
		int b = 1;
		int x = a + b;
		int y = x + b;
		while (y > a + b) {
			a = a + 1;
			x = a + b;
			break;
		}
		return 0;
	}

}
