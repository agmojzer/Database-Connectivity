package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBUtil;

public class StatusDB {
	

	
	public StatusDB() {
			
		}

	public ArrayList<Status> getAll() {
		//gets all statuses.
		ArrayList <Status> all = new ArrayList<>();
		String sql = "SELECT * FROM status";
		//Below is a 'try with resources'. Allows Java to clean up the resources.
		try (Connection conn = DBUtil.getConnection(); //STEP 1.5. Calls the connection method
			Statement statement = conn.createStatement(); //STEP 2. Statement created from the connection
			ResultSet rs = statement.executeQuery(sql)) //STEP 3. Execute the query.) {
			//String sql = "SELECT * FROM status";
//			Connection conn = DBUtil.getConnection(); //STEP 1.5. Calls the connection method
//			Statement statement = conn.createStatement(); //STEP 2. Statement created from the connection
//			ResultSet rs = statement.executeQuery(sql); //STEP 3. Execute the query.
		{
			while (rs.next()) { //STEP 4. Whole while loop parses the result set.
				int id = rs.getInt(1);
				String desc = rs.getString(2);
				Status status = new Status(id, desc);
				all.add(status);
			}
		}
		catch (SQLException sqle) {
			System.out.println("Error getting all status rows.");
			sqle.printStackTrace();
		}
		return all;
	}
	
	public Status getStatus(int statusID) {
		//gets one row from status database
		Status s = null;
		try {
//			String sql = "SELECT * FROM status where id = "+statusID;
//			Connection conn = DBUtil.getConnection(); //STEP 1.5. Calls the connection method
//			Statement statement = conn.createStatement(); //STEP 2. Statement created from the connection
//			ResultSet rs = statement.executeQuery(sql); //STEP 3. Execute the query.
			
			//This deals with prepared statements, should not change printout from up above, but added security.
			//...from SQL injection attacks.
			String sql = "SELECT * FROM status where id = ?";
			Connection conn = DBUtil.getConnection(); //STEP 1.5. Calls the connection method
			PreparedStatement ps = conn.prepareStatement(sql); //STEP 2. Statement created from the connection
			ps.setInt(1, statusID); //the 1 refers to the ? three lines above.
			ResultSet rs = ps.executeQuery(); //STEP 3. Execute the query.
		
			
			while (rs.next()) { //STEP 4. Whole while loop parses the result set.
				int id = rs.getInt(1);
				String desc = rs.getString(2);
				s = new Status(id, desc);
		
			}
		}
		catch (SQLException sqle) {
			System.out.println("Error getting status for id '"+statusID+"'.");
			sqle.printStackTrace();
		}
		return s;
	}
}
