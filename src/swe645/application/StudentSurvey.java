package swe645.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentSurvey {
	private Connection conn = null;
    private Statement statement = null;
    
    /*
     * This function inserts survey data into oracle database using ojdbc oracle connection
     * It fetches data using getter function of StudentBean object passed as argument
     */
	public String insertStudentSurvey(StudentSurveyData obStudentSurveyData) {
		try
		{	
			//Defining connection variables required to establish connection
			String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
			String username = "rmalve";
			String password = "stirtirg";
			String testString = "ABC";
			conn = DriverManager.getConnection(url, username, password);
			
			//Query for insert in database generated using getter methods for values
			String sqlQuery = "INSERT INTO STUDENTFORM VALUES "
					+ "('"+obStudentSurveyData.getStudentid()+"', '"+obStudentSurveyData.getFname()+"', '"+obStudentSurveyData.getLname()+"', "
					+ "'"+obStudentSurveyData.getStaddr()+"', '"+obStudentSurveyData.getZipcode()+"','"+obStudentSurveyData.getCity()+"','"+obStudentSurveyData.getState()+"','"+obStudentSurveyData.getContact()+"',"
					+ "'"+obStudentSurveyData.getEmail()+"','"+obStudentSurveyData.getSurveydate()+"',"
					+ "'"+obStudentSurveyData.getLiked()+"','"+obStudentSurveyData.getSource()+"','"+obStudentSurveyData.getRecommend()+"'";
			statement = conn.createStatement();
			statement.executeUpdate(sqlQuery);
			
			//Close connections and return 1 on success
			statement.close();
			conn.close();
			return "1";
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			return ex.toString();
		}	
	}
	
	/*
	 * This function retrieves survey data of provided gnum from the oracle database
	 * Function returns StudentBean object if the data exists for provided gnum
	 * Function returns null if data does not exist for provided gnum or in case of an exception
	 */
	public StudentSurveyData retrieveStudentSurvey(String gnum) {
		try
		{
			//Defining connection variables required to establish connection
			String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
			String username = "rmalve";
			String password = "stirtirg";
			conn = DriverManager.getConnection(url, username, password);
			StudentSurveyData obStudentSurveyData = new StudentSurveyData();
			
			//Check the count if the given gnum exists in the database
			String  sqlQuery = "SELECT COUNT(*) FROM STUDENTFORM WHERE GNUM='"+gnum+"'";
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlQuery);
			String outputString = "Count:";
            while(rs.next())
            {
                String empCount = rs.getString("COUNT(*)");
                outputString = outputString + empCount;
            }
            String[] outputStringArr = outputString.split(":",2);
            if(Integer.parseInt(outputStringArr[1]) > 0)
            {
            	//Fetch data for given gnum if data exists for given gnum
            	sqlQuery = "SELECT * FROM STUDENTFORM WHERE GNUM='"+gnum+"'";
    			statement = conn.createStatement();
    			rs = statement.executeQuery(sqlQuery);
    			while(rs.next())
    			{
    				obStudentSurveyData.setStudentid(rs.getString("GNUM"));	
    				obStudentSurveyData.setFname(rs.getString("FNAME"));
    				obStudentSurveyData.setLname(rs.getString("LNAME"));				
    				obStudentSurveyData.setStaddr(rs.getString("ADDRESS"));
    				obStudentSurveyData.setZipcode(rs.getString("ZIP"));
    				obStudentSurveyData.setCity(rs.getString("CITY"));
    				obStudentSurveyData.setState(rs.getString("STATE"));
    				obStudentSurveyData.setContact(rs.getString("MOBILE"));
    				obStudentSurveyData.setEmail(rs.getString("EMAIL"));
    				obStudentSurveyData.setSurveydate(rs.getString("DOS"));
    				obStudentSurveyData.setLiked(rs.getString("LIKED"));
    				obStudentSurveyData.setSource(rs.getString("INTERESTED"));
    				obStudentSurveyData.setRecommend(rs.getString("RECOMMENDATION"));
    			}
            }
            else
            {
            	//Return null if the given gnum/student id does not exist
            	return null;
            }
            
            //Close connections and return StudentBean object
			statement.close();
			conn.close();
			return obStudentSurveyData;
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			return null;
		}	
	}
}
