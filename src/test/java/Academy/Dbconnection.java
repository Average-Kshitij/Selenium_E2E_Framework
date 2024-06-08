package Academy;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import resources.base;

public class Dbconnection extends base{

	
	
	@Test(enabled = false)
	public void connectDb() throws SQLException
	{
		
		ResultSet myData=connectTodatabase("Select * from employee");
		
		myData.next();
		System.out.println(myData.getString("NAME"));
		System.out.println(myData.getString("EMP_ID"));
	}
}
