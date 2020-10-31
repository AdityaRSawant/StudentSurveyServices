package swe645.application;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


@Path("/student")
public class StudentService {

	//REST service to get survey data of selected student with provided Gnumber
	@GET
	@Path("/getstudentsurvey/{gnum}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudentSurvey(@PathParam("gnum") String gnum)
	{
		StudentSurvey obStudentDAO = new StudentSurvey();
		Gson gson = new Gson();
		//Call retrieve data method from StudentSurvey class that fetches survey data
		StudentSurveyData obStudentSurveyData = obStudentDAO.retrieveStudentSurvey(gnum);
		if(obStudentSurveyData == null)
		{
			//Return null if no student found
			return Response.status(200)
				      .entity(null).build();
		}
		else
		{
			//Return StudentBean object containing the data
			String json = gson.toJson(obStudentSurveyData);
			return Response.status(200)
				      .entity(json).build(); 
		}
	}
	
	//REST service to insert Student survey data
	@POST
	@Path("/addstudentsurvey")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudentSurvey(StudentSurveyData dataObj)
	{
		StudentSurvey obStudentDAO = new StudentSurvey();
		Gson gson = new Gson();
		//Call Insert data method to POST survey data in database
		String output = obStudentDAO.insertStudentSurvey(dataObj);
		System.out.println(gson.toJson(dataObj, StudentSurveyData.class));
		if(output == "1")
		{
			//Return success message
			JsonObject responseObj = new JsonObject();
			responseObj.addProperty("message", "Inserted Successfully");
			return Response.status(200).build();
		}
		else
		{
			//Return error object
			JsonObject responseObj = new JsonObject();
			responseObj.addProperty("message", "There was an error:"+output);
			return Response.status(200)
					.entity(responseObj).build();
		}
	}
	
	
	//REST service to get all student gnumbers
	@GET
	@Path("/getallstudentgnum")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllStudentGnum()
	{
		try
		{
			Connection conn = null;
			Statement statement = null;
			String url = "jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
			String username = "rmalve";
			String password = "stirtirg";
			conn = DriverManager.getConnection(url, username, password);
		
			//Query to fetch all the Gnumbers
			List<String> obGnumList= new ArrayList<String>();
			//Query to fetch gnumbers from database
			String sqlQuery = "SELECT GNUM FROM STUDENTFORM";
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sqlQuery);
			while(rs.next())
			{
				obGnumList.add(rs.getString("GNUM")); 
			}
			statement.close();
			conn.close();
			if(obGnumList == null)
			{
				//Return null in case of no gnumbers found
				return Response.status(200)
						.entity(null).build();
			}
			else
			{
				//Return Gnumbers in form of Array as a JSON Array
				Gson gson = new Gson();
				String json = gson.toJson(obGnumList);
				return Response.status(200)
				      .entity(json).build(); 
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
			return Response.status(500)
					.entity(ex.toString()).build();
		}
	}
	
	
	
	
}
