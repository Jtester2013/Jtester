package core.common.svd.test;

public class ASTViewTest {

	public int max(int a, int b) {
		int adsf;
		int result = 0;
		if (a > b) {
			result = a;
			result++;
			result = result + 5;
		} else {
			result = b;
		}
		return result;
	}
}
