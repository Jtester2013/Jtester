package ontology;

public class ReturnFromFinallyBlock {
	public String foo() {
		try {
			int a = 1;
		} catch (Exception e) {
			int b = 2;
		} finally {
			return "should not return here"; 
		}
	}
}
