package utilites.exception;

import java.sql.SQLException;

public class ExceptionSQLLecture extends Exception {
	private String  message="";
	

	public ExceptionSQLLecture(String message) {
		// TODO Auto-generated constructor stub
		this.message=message;
	}

	
	public ExceptionSQLLecture(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	
	public String getExceptionSQLLecture(){
		//String s="Une erreur s'est produite lors de la lecture des infos :";
		return message;
		
		
	}

}
