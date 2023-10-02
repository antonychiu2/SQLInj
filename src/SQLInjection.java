import java.sql.*;

public class SQLInjection 
{
	public static void main (String[] args) 
	{
		Connection conn = null;
		try 
		{
			String username = args [0];
			String password   = args [1];
			
			//String username = null;
			//String password = null;

			String[] role = null;
			String[] roletype = null;
			
			String uname = validate(username);
			String pass = validate_pass(password);
			
			setUserRoles setRoles = new setUserRoles();
			
			roletype = setRoles.setUserRoles(uname, role);
			
			/*for (int i=0; i <= roletype[i].length(); i++)
			{
				System.out.println("set role:" + role[i]);
			}*/

			System.out.println("Set Role:" +roletype);
			
			String query = "select uname, passwd from users where uname = '"+uname+"''"+pass+"'";

			conn = DriverManager.getConnection ("jdbc:odbc:logistics", "admin", "letmein");
			Statement stmnt = conn.createStatement ();
			ResultSet rs = stmnt.executeQuery(query);
			while ( rs.next() ) 
			{
				// do nothing
			}
			rs.close ();
			stmnt.close ();
			conn.close ();
		} catch (SQLException err) 
		
		{
			err.printStackTrace ();
		}
	}
	
	public static String validate(String name) 
	{
		if ( name.matches("[a-z][0-9a-zA-Z]{0,32}") ) 
		{
			return name;
		}
		else
		{
			throw new IllegalArgumentException("Invalid name");
		}
	}
	public static String validate_pass(String password)
	{
		if ( password.matches("[a-z][0-9a-zA-Z]{0,32}") ) 
		{
			return password;
		}
		else
		{
			throw new IllegalArgumentException("Invalid password");
		}
	}
}

	


