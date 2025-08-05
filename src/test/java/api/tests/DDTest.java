package api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.payload.User;
import api.routes.userEndPoints;
import api.utlilities.dataProvider;
import io.restassured.response.Response;

public class DDTest {
	
	User userPayload;
	@Test(priority=1, dataProvider="data", dataProviderClass=dataProvider.class )
	public void testPostUser(String userId, String username, String fname, String lname, String email, String pwd, String phone) {
		
		userPayload = new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(username);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response = userEndPoints.createUser(userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test(priority=2, dataProvider="usernames", dataProviderClass=dataProvider.class)
	public void deleteUser(String UserName) {
		
		Response response = userEndPoints.deleteUser(this.userPayload.getUsername());
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}

}
