package com.studentapp.junit.studentsId;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;

@RunWith(SerenityRunner.class)
public class AnotherSerenityTest {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://localhost:8080/student";
	}
	
	@Test
	public void getAllStudents4() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.log()
		.all()
		.statusCode(200);
	}
	

	@Test
	public void failTest4() {
		SerenityRest.given()
		.when()
		.get("/list")
		.then()
		.statusCode(500);		
	}

}
