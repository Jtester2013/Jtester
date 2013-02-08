package var_interval;

public class VarInterval{
	public void testCase(int m){
		int i = 1;
		while(i<1000000){
			i= i + 1;
			if(m < 10){
				m = m + 1;
			}
		}
		return;
	}
}