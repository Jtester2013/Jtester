package ontology;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Syndrome {
	public void symptom1() {
		int[] array = new int[10];
		int x = 10;
		int y = 2;
		if (x > y) {
			array[10] = x;
		} else {
			array[10] = y;
		}
	}
	
	public long divide() {
		long dividend = 100;
		long divisor = 0;
		
		long p = dividend;
		long q = p * 100;
		
		divisor = q * q  - 100 * p * q + p - 100;
		//divisor = 1;
		
		return dividend / divisor;
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
            
            OutputStream out = null;
            //in.close();
            //out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
	}
}