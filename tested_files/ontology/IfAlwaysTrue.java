package ontology;

public class IfAlwaysTrue {
	public void test() {
		int a = 5;
		int b = 3;
		if (a > b) {
			a = a - b;
		} else {
			a = a + b;
		}
	}
}
