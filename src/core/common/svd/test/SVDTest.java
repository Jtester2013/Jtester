package core.common.svd.test;

public class SVDTest {
	/*
	 * public int name() { int i = 3; i += 1; return i; }
	 */
	public int max(int a, int b) {
		int result = 0;
		if (a > b) {
			result = a;
			result = result + 5;
		} else {
			result = b;
		}

		/*while (result > 0) {
			result--;
			System.out.println();
		}*/
		return result;
	}
}
