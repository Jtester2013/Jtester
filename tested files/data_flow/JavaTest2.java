package examples;

public class JavaTest2 {
	int a1() {
		int x = 3;
		int y = 4;
		x = 1;
		int z = 0;
		if ( y > x ){
			z = y;
		}else {
			z = y * y;
		}
		x = z;
		return 0;
	}

	int a2(int a) {

		if (a > 0)
			a = 1;
		else
			a = 2;

		return a;
	}
}