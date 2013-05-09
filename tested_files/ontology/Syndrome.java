package ontology;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;

public class Syndrome {
	public void setArray() {
		String[] array = new String[10];
		int arg1 = 10;
		int arg2 = 2;
		int arg3 = 5;
		
		if (arg1 < arg2) {
			array[10] = "hello world1";
		} else {
			array[10] = "hello world2";
		}
	}
	
	public long divide() {
		long dividend = 100; 
		long divisor = 0;
		
		long p = dividend; //100
		long q = p * 100; //10000

		divisor = q * q  - 100 * p * q + p - 100; // 
		//divisor = 1;
		
		long result = dividend / divisor; // 100\0
				
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
	
	public void disposeAll() {
		HashSet _hConsoles = new HashSet();

		Iterator iterator = _hConsoles.iterator();
		while (iterator.hasNext()) {
			String console = (String) iterator.next();
			if (console == null) {
				continue;
			}
			console.toCharArray();
			_hConsoles.remove(console); // VIOLATION
			
			//iterator.remove();
		}
	}
}