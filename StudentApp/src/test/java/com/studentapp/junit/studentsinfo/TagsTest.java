package com.studentapp.junit.studentsinfo;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;

@RunWith(SerenityRunner.class)
public class TagsTest{
	

	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://localhost:8080/student";
	}
	

	//Negative test
	@WithTag("studentfeature:NEGATIVE")
	@Title("Provide a 405 status when incorrect HTTP method is used to access a resource")
	@Test
	public void inValidMethod() {
		SerenityRest.
		rest().given().when()
		.post("/list")//incorrect method
		.then()
		.statusCode(405)
		.log().all();
	}
	

	@WithTags(
			{
			@WithTag("studentfeature:SMOKE"),
			@WithTag("studentfeature:POSITIVE")
			}
			)
	@Test
	@Title("This test verifies if status code is 200 for GET")
	public void verifyIfTheStatusCodeIs200() {
		SerenityRest.
		rest().given().when()
		.get("/list")
		.then()
		.statusCode(200);	
	}


	@WithTags(
			{
			@WithTag("studentfeature:SMOKE"),
			@WithTag("studentfeature:NEGATIVE")
			}
			)
	@Test
	@Title("This test verifies if status code is 400 when accessing invalid resource")
	public void incorrectResource() {
		SerenityRest.
		rest().given().when()
		.get("/placeNotExists")//invalid resource
		.then()
		.statusCode(400)
		.log().all();	
	}
	
	
	
	
}
