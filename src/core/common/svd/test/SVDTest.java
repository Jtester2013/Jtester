package core.common.svd.test;

public class SVDTest {
	public int max(int a, int b) {
		int result = 0;
		if (a > b) {
			result = a;
			result = result + 5;
		}else {
			result = b;
		}

		while (result > 0) {
			result--;
			System.out.println();
		}
		return result;
	}
}
