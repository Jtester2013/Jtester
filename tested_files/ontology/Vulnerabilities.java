import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Vulnerabilities {
	
	public void save(HttpServletRequest req) throws IOException {
		String sData = req.getParameter("user_params");
		//sData = validate(sData);
		
		String fileName = "user_data";
		FileOutputStream fOut = new FileOutputStream(fileName);
		fOut.write(sData.getBytes());
		fOut.close();
	}
	
	private String validate(String content){
		return "";
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
	    try {
	        Class.forName("someClass");
	    } catch(Exception e) {
	        try {
	        	PrintWriter writer = resp.getWriter();
	        	writer.write(e.getMessage()); //VIOLATION
	        } catch (IOException e1) {
	        	
	        }
	    }
	}
}