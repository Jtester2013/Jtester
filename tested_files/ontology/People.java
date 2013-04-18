import java.util.Date;


public class People {
	public int ID;
	public String name;
	public Date birthday;
	public String address;
	
	@Override
	public boolean equals(Object obj){
		if(obj == null){
			return false;
		}
		if(this == obj){
			return true;
		}
		if(obj instanceof People){
			People another = (People)obj;
			return this.ID == another.ID;
		}
		return false;
	}
	
//	@Override
//	public int hashCode() {
//		return ID;
//	}
}
