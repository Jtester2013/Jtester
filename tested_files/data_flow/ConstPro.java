package data_flow;

public class ConstPro {
	public int test() {
		int a = 0;
		int b = 1;
		int x = a + b;
		int y = a * b;
		y = x + b;
		while (y > a + b) {
			a = a + 1;
			x = a + b;
			break;
		}
		return 0;
	}
}
