package com.studentapp.junit.studentsinfo;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReuseableSpecifications;
import com.studentapp.utils.TestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnotherSerenityTest extends TestBase{

	static String firstName = "SMOKEUSER" + TestUtils.getRandomValue();
	static String lastName = "SMOKEUSER" + TestUtils.getRandomValue();
	static String programme = "CompSci";
	static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
	static int studentId;
	
	
	@Steps
	StudentSerenitySteps steps;
	
	@Title("This test creates new student")
	@Test
	public void test001() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201)
		.spec(ReuseableSpecifications.getGenericResponseSpec());
		
	}
	
	@Title("Verify if student exists")
	@Test
	public void test002() {
		HashMap<String, Object> value= steps.getStudentInfoByFirstName(firstName);
		System.out.println("The value is: " + value);
		
		assertThat(value, hasValue(firstName));	//Verifies that value has the firstName value
		
		studentId= (int) value.get("id");	//sets id key
		
	}
	
	@Title("Edits student info and verify information")
	@Test
	public void test003() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");
		firstName= firstName + "_UPDATED";
		StudentClass student=new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		steps.updateStudent(studentId, student);
		
		
		HashMap<String, Object> value= steps.getStudentInfoByFirstName(firstName);	//returns a hashmap list with the first anem
								
		assertThat(value, hasValue(firstName));	//Verifies that value has the firstName value
				
		
	}

	@Title("Deletes a student and verifies")
	@Test
	public void test004() {
		steps.deleteStudentById(studentId);
		
		steps.getStudentInfoByStudentId(studentId)
		.log()
		.all()
		.statusCode(404);	//Verifies it cannnot find student (404 means not there)
	}
	
	
}
