package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDT_Test
{
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String id,String username,String fname,String lname,String email,String pwd,String ph)
	{
		User userpayload=new User();
		
		userpayload.setId(Integer.parseInt(id));
		userpayload.setUsername(username);
		userpayload.setFirstName(fname);
		userpayload.setLastName(lname);
		userpayload.setEmail(email);
		userpayload.setPassword(pwd);
		userpayload.setPhone(ph);
		
		Response response=UserEndPoints.createUser(userpayload);      
        Assert.assertEquals(response.getStatusCode(),200);
		
	}
	@Test(priority=1,dataProvider="UserNmaes",dataProviderClass=DataProviders.class)
	public void testDeleteUserByNmae(String userName)
	{
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
