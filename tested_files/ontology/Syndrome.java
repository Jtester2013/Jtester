package ontology;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
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