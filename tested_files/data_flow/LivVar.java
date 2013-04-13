package data_flow;

public class LivVar {
	int test() {
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
}
