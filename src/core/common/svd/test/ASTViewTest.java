package core.common.svd.test;

public class ASTViewTest {
	public int member;

	// 表5-4 条件分支结构测试用例
	public void decisionTest(int b) {
		int c = 12 + b;
		int d = c;
		c = d - b;
		if (c > 0) // 不难发现此时c的值为12，恒大于0
			c = -1;
		else {
			c = 1;
		}
	}

//	public int max(int a, int b) {
//		int result = 3;
//		result = a + 90 * 3 - b;
//		int c = a + b;
//		// a = result - c;
//		c = a + b;
//
//		if (a > b) {
//			result = a;
//			result++;
//			result = this.member + result + 5;
//		} else {
//			result = b;
//		}
//
//		if (a < b) {
//			result--;
//		}
//
//		// if (a == b) {
//		// result--;
//		// }
//		// return result;
//		return c;
//	}
}
