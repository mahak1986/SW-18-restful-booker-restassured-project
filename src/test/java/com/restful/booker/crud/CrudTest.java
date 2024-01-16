package com.restful.booker.crud;


import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingDates;
import com.restful.booker.model.BookingPojo;
import com.restful.booker.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CrudTest {
    static String username ="admin";
    static String password = "password123";

    static String firstName = TestUtils.getRandomValue() + "Mahak";
    static String lastName = TestUtils.getRandomValue() + "Agarwal";
    static int totalPrice = 350;
    static boolean depositPaid = true;
    static String additionalNeeds = "Dinner";


    static String token = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
    static int bookingId;
    static int userId;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }


    @Test //Create token
    public void test001(){
        AuthPojo authPojo = new AuthPojo();
        authPojo.setUsername(username);
        authPojo.setPassword(password);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(authPojo)
                .post("/auth");
        response.then().log().all().statusCode(200);

    }

    @Test //Create booking
    public void test002(){
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName(firstName);
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(250);
        bookingPojo.setDepositPaid(true);
        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("23-01-2024");
        bookingdates.setCheckout("28-01-2024");
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalNeeds(additionalNeeds);
        Response response = given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .when()
                .body(bookingPojo)
                .post("/booking");
        response.then().log().all().statusCode(200);

    }
    @Test
    public void test003() {
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstName("Mahak");
        bookingPojo.setLastName(lastName);
        bookingPojo.setTotalPrice(111);
        bookingPojo.setDepositPaid(true);
        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("23-01-2024");
        bookingdates.setCheckout("28-01-2024");
        bookingPojo.setBookingdates(bookingdates);
        bookingPojo.setAdditionalNeeds(additionalNeeds);
        Response response = given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .when()
                .body(bookingPojo)
                .put("/booking/"+userId);
        response.then().log().all().statusCode(200);
    }
    @Test
    public void test004() {

        given().log().all()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("id", userId)
                .when()
                .get("/booking/{id}")
                .then()
                .statusCode(200);

    }

    @Test
    public void test005() {
        given()
                .headers("Content-Type", "application/json", "Cookie", "token=" + token)
                .pathParam("id", userId)
                .when()
                .delete("/booking/{id}")
                .then()
                .statusCode(201);

    }
}



