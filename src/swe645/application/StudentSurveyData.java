package swe645.application;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "Student") 

public class StudentSurveyData implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String studentid,fname,lname,staddr,zipcode,city,state,contact,email,surveydate,liked,source,recommend;
	
	//Following are the getters and setters for the variables that are fields from survey form except data field
	public String getStudentid() {
		return studentid;
	}

	@XmlElement
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}

	public String getFname() {
		return fname;
	}

	@XmlElement
	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	@XmlElement
	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getStaddr() {
		return staddr;
	}

	@XmlElement
	public void setStaddr(String staddr) {
		this.staddr = staddr;
	}

	public String getZipcode() {
		return zipcode;
	}

	@XmlElement
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	@XmlElement
	public void setState(String state) {
		this.state = state;
	}

	public String getContact() {
		return contact;
	}

	@XmlElement
	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	@XmlElement
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSurveydate() {
		return surveydate;
	}

	@XmlElement
	public void setSurveydate(String surveydate) {
		this.surveydate = surveydate;
	}

	public String getLiked() {
		return this.liked;
	}

	@XmlElement
	public void setLiked(String liked) {
		this.liked = liked;
	}

	public String getSource() {
		return source;
	}

	@XmlElement
	public void setSource(String source) {
		this.source = source;
	}

	public String getRecommend() {
		return recommend;
	}

	@XmlElement
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
}
