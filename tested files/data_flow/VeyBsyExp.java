package data_flow;

public class VeyBsyExp {
	public int foo2() {
		int a = 0;
		int b = 1;
		int x, y ;
		if (a > b){
			x = b - a;
			y = a - b;
		}
		else {
			y = b - a;
			x = a - b;
		}
		return 0;
	}

	public int foo1(int a) {

		if (a > 0)
			a = 1;
		else
			a = 2;

		return a;
	}
}

