package ontology;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Syndrome {
	public void setArray() {
		String[] array = new String[10];
		int arg1 = 10;
		int arg2 = 2;
		
		while(arg2> 0){
			arg2 = arg2-1;
		}
		
		if (arg1 < arg2) {
			array[10] = "hello world1";
		} else {
			array[10] = "hello world2";
		}
	}
	
	public long divide() {
		long dividend = 100;
		long divisor = 0;
		
		long p = dividend;
		long q = p * 100;

		divisor = q * q  - 100 * p * q + p - 100;
		//divisor = 1;
		
		long result = dividend / divisor
		
		return result;
	}
	
	public void readFileByBytes(String fileName) {
		File file = new File(fileName);
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            InputStream in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            
            OutputStream out = null;
            //in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
	}
}