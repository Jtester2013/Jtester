package examples;

public class JavaTest3 {
	int foo5() {
		int x = 5;
		int y = 0;
		int z = 1;
		while (x > 0){
			if ( x == 1 ){
				y = 4;
			}else {
				y = z + 1;
			}
			x = x - 1;
		}
		return 0;
	}

	int foo(int a) {	

		if (a > 0)
			a = 1;
		else
			a = 2;

		return a;
	}
}