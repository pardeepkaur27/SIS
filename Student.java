package studentSystem;

public class Student {
	public static String Uname, Name, Dept;
	
	public static void setUname(String UName){
		Uname=UName;
	}
	
	public static String getUname(){
		return Uname;
	}
	public static void setName(String name){
		Name=name;
	}
	
	public static String getName(){
		return Name;
	}
	
	public static void setDept(String dept){
		Dept=dept;
	}
	
	public static String getDept(){
		return Dept;
	}
}
