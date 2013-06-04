package core.common.svd.test;

public class ASTViewTest {
	public int member;

	public int max(int a, int b) {
		int result = 3;
		// result = a + 90 * 3 - b;
		int c = a + b;
		// a = result -c;
		// c = a + b;

		if (a > b) {
			result = a;
			result++;
			result = this.member + result + 5;
		} else {
			result = b;
		}

//		if(a<b){
//			result--;
//		}
//		
//		if (a == b) {
//			result--;
//		}
		// return result;
		return c;
	}
}
