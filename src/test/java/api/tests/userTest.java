package api.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import com.github.javafaker.Faker;

import api.payload.User;
import api.routes.userEndPoints;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class userTest {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void setup() {
		
		faker = new Faker();
		userPayload = new User();  //faker object we have to assign is user payload.
		
		userPayload.setId(faker.idNumber().hashCode()); //hasCode will generate random ids.
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().phoneNumber());
		
		//logs
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	
	@Test(priority=1)
	public void postUser() {
		
		logger.info("********User creating***********");
		Response response = userEndPoints.createUser(userPayload);
		response.then().log().all();
		logger.info("********User created***********");

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
	}
	
	@Test(priority=2)
	public void getUser() {
		logger.info("********Getting user deatils***********");

		Response response = userEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		logger.info("********User list reterived***********");

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void updateUser() {
		
		logger.info("********updating user ***********");

		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		
		Response response = userEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().statusCode(200).log().body();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		Response responseAfterUpdate = userEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		logger.info("********User updated***********");

		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void deleteUser() {
		logger.info("********User delete***********");

		Response response = userEndPoints.deleteUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		logger.info("********User deleted successfully***********");

	}

}
