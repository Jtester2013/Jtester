package data_flow;

public class RchDef {
	int test() {
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
}