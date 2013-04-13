package data_flow;

public class VeyBsyExp {
	public int test() {
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
}

