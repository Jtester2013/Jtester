package data_flow;

public class AvailableExp {
	public int test1() {
		int a = 0;
		int b = 1;
		int x = a + b;
		int y = a * b;
		y = a - b;
		while (y > a + b) {
			a = a + 1;
			x = a + b;
			break;
		}
		test2(x, y);
		return 0;
	}

	public int test2(int a, int b) {
		int x = a + b;
		int y = a * b;
		y = x + y;
		return y;
	}
}
