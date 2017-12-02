package Admin;

public class EnrollDetails {
	public static String Term, Year, Dept, SemId, CourseId;
	public static int Capacity;
	
	public static void setTerm(String term){
		Term=term;
	}
	
	public static String getTerm(){
		return Term;
	}
	
	public static void setYear(String year){
		Year=year;
	}
	
	public static String getYear(){
		return Year;
	}
	
	public static void setDept(String dept){
		Dept=dept;
	}
	
	public static String getDept(){
		return Dept;
	}
	
	public static void setSemId(String semId){
		SemId=semId;
	}
	
	public static String getSemId(){
		return SemId;
	}
	
	public static void setCourseId(String courseId){
		CourseId=courseId;
	}
	
	public static String getCourseId(){
		return CourseId;
	}
	
	public static void setCapacity(int capacity){
		Capacity=capacity;
	}
	
	public static int getCapacity(){
		return Capacity;
	}
}

