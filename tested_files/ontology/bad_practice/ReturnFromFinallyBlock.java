package ontology;

public class ReturnFromFinallyBlock {
	public String foo() {
		try {
			int a = 1;
			int b = 2;
			while(a < b){
			}
		} catch (Exception e) {
			int c = 2;
		} finally {
			return "should not return from here"; 
		}
	}
}
