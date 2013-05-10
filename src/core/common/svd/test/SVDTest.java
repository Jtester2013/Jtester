package core.common.svd.test;

public class SVDTest {
	/*
	 * public int name() { int i = 3; i += 1; return i; }
	 */
	public int max(int a, int b) {
		int result = 0;
//		if (a > b) {
//			result = a;
//			result = result + 5;
//			if (result < 3) {
//				System.out.println();
//			} else {
//				System.out.println();
//			}
//		} else {
//			result = b;
//		}
		
		switch (result) {
		case 0:
			System.out.println(0);
			break;
		case 1:
			System.out.println(1);
			break;
		case 2:
			System.out.println(2);
			break;
		default:
			break;
		}
		
		if(result<3){
			System.out.println();
		}else {
			System.out.println();
		}

//		while (result > 0) {
//			result--;
//			System.out.println();
//		}
		return result;
	}
}
