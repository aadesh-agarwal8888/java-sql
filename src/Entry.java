import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Entry {

	static Connection connection=null;
	static PreparedStatement stSelect=null;
	static ResultSet result=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost/sys","root","root"); //Here Database being used, Username, Password
			
			
			//This is to get all the data from database
			stSelect=connection.prepareStatement("select * from emp;");
			result = stSelect.executeQuery();
			
			while(result.next()) {
				System.out.println("Id:"+result.getInt(1));//here 1 is the column number as the column is of type int its getInt
				System.out.println("Name:"+result.getString(2));
				System.out.println("Age:"+result.getInt(3));
				System.out.println("Address:"+result.getString(4));
				System.out.println("Salary:"+result.getInt(5));
				System.out.println("*************");
			}
			
			//To get Salary of abc
			stSelect=connection.prepareStatement("select name,salary from emp where name=?;");
			stSelect.setString(1, "abc");
			result=stSelect.executeQuery();
			if(result.next()){
				System.out.println("Name:"+result.getString(1)); //here as we took name and salary only so obviously 1st column in result set will be name
				System.out.println("Salary: "+result.getInt(2));
			}
			
			//inserting values
			stSelect=connection.prepareStatement("insert into emp values(?,?,?,?,?);");
			stSelect.setInt(1, 4);
			stSelect.setString(2, "jkl");
			stSelect.setInt(3, 21);
			stSelect.setString(4, "jkl");
			stSelect.setInt(5, 1800);
			stSelect.executeUpdate();
			System.out.println("Inserted");
			
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
