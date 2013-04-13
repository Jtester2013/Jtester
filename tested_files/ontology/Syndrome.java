package ontology;

public class Syndrome {
	public int symptom() {
		int sum = 0; // never used
		int array[] = new int[10];
		try{
			int a = 100;
			int b = 99;
			if(a > b){
				while(a < b){ //can't be executed
					throw new Exception("should not be here");
				}
			}else{ // if statement is always true
				System.out.println("result is: " + result); // ERROR: use un-defined variable
			}
		}catch(Exception e){
			array[10] = 1; // OutOfBoundaryException
		}
		finally{
			// return should not be in finally block
			return 1;
		}
	}
}