package studentSystem;

import java.util.Date;
	  
	public class  checkDeadline{
	public static void main(String[] args) {
	  
    checkDeadline d = new checkDeadline();
	d.check();
	  
	}
	  
	public boolean check(){
	 boolean status=false; 
	Date date1 = new Date(System.currentTimeMillis());
	Date deadline = new Date("2017/12/08"); 
	status=date1.before(deadline);
	System.out.println("Result before: " + (date1.before(deadline)));
    return status;
	}
	}
	  

