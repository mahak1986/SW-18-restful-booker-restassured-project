package com.restful.booker.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class BookingAssertion {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
     response = given()
                .when()
                .get("/booking/1")
                .then().statusCode(200);
    }


    @Test
    public void tes001() {
        response.body(".", hasKey("firstname"));
    }


    @Test
    public void test002() {

        response.body(".", hasKey("lastname"));
    }


    @Test
    public void test003() {

        response.body(".", hasKey("totalprice"));
    }



    @Test
    public void test004() {

        response.body(".", hasKey("depositpaid"));
    }



    @Test
    public void test005() {
        response.body("bookingdates", hasKey("checkin"));

    }

    @Test
    public void test008() {
        response.body(".", hasKey("bookingdates"));

    }

    @Test
    public void test006() {

        response.body("bookingdates", hasKey("checkout"));

    }


    // 7. Verify the Gender = male of user name is  "Ganaka Prajapat DVM"

    @Test
    public void test007() {

        response.body(".", hasKey("additionalneeds"));

    }
}

