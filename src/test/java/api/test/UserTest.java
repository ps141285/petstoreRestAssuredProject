package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest 
{
	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
		
	    //logs
		logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("****** creating users ******");
	Response response=UserEndPoints.createUser(userpayload);
	         response.then().log().all();
	         
	         Assert.assertEquals(response.getStatusCode(),200);
	    logger.info("****** users is created ******");
	}
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("****** reading users info ******");
		Response response=UserEndPoints.readUser(this.userpayload.getUsername());
		                   response.then().log().all();
		                   
		                   Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("****** User info is Displayed ******");
	}
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("****** Updated User Info ******");
		//update data using payload
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response=UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().body();
		logger.info("****** User Info is updated ******");
        
        
        
        //check response after update the data 
        Response responseAfterUpdate=UserEndPoints.readUser(this.userpayload.getUsername());
                 responseAfterUpdate.then().log().body();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		                   
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("****** Deleted User Info ******");
		Response response=UserEndPoints.deleteUser(this.userpayload.getUsername());
		                   //response.then().log().all();
		                   
		                   Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("****** User Info is Deleted ******");
	}
	
	

}
