package ontology;

public class Syndrome {
	public void symptom1() {
		int[] a = new int[10];
		int x = 10;
		int y = 2;
		if (x > y) {
			a[10] = x;
		} else {
			a[10] = y;
		}
	}
	
	public String symptom2() {
		try {
			int a = 1;
			int b = 2;
			int d = a;
			while (a < b) {
			}
		} catch (Exception e) {
			int c = 2;
		} finally {
			return "should not return from here";
		}
	}
	
	public void readFileByBytes(String fileName) {
		File file = new File(fileName);
        try {
            System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���ֽڣ�");
            // һ�ζ�һ���ֽ�
            InputStream in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            
            OutputStream out;
            //in.close();
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
	}
}