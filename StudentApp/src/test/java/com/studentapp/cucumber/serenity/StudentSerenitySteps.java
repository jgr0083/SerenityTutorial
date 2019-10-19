package com.studentapp.cucumber.serenity;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReuseableSpecifications;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {

	@Step("Creates new student with firstName:{0}, lastName:{1}, email:{2}, programme:{3}, courses:{4}")		//can be expressed as a step in the report
	public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses) {
		StudentClass student=new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		
		return SerenityRest.rest().given()
		.spec(ReuseableSpecifications.getGenericRequestSpec())
		.when()
		.body(student)
		.post()
		.then();
		
	}

	@Step("Gets the student info with firstName:{0}")	
	public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {
		return SerenityRest.rest().given()
		.when()
		.get("/list")
		.then()
		.statusCode(200)
		.extract()
		.path("findAll{it.firstName=='"+firstName+"'}.get(0)");	//returns a hashmap list with the first anem
		
	}
	
	@Step("Gets the student info with studentId:{0}")	
	public ValidatableResponse getStudentInfoByStudentId(int studentId) {
		return SerenityRest.rest().given()
		.when()
		.get("/"+studentId)
		.then();		
	}
	
	@Step("Updating student with studentId:{0}, student{1}")
	public ValidatableResponse updateStudent(int studentId, StudentClass student) {		
		
		return SerenityRest.rest().given()
		.spec(ReuseableSpecifications.getGenericRequestSpec())
		.log()
		.all()
		.when()
		.body(student)
		.put("/" +studentId)
		.then();
		
	}
	
	
	@Step("Deletes a student with studentID:{0}")	//can be expressed as a step in the report
	public void deleteStudentById(int studentId) {
		SerenityRest.rest().given()
		.when()
		.delete("/" + studentId);
				
		
		
	}
	
}
