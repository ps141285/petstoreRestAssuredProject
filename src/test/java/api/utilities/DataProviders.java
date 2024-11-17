package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testdata//UserData.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rownum=xl.getrowcount("UserDetails");
		int colcount=xl.getcellcount("UserDetails",1);
		
		String apidata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				apidata[i-1][j]=xl.getcelldata("UserDetails", i, j);
			}
		}
		return apidata;
		
	}
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testdata//UserData.xlsx";
		ExcelUtility xl=new ExcelUtility(path);
		
		int rownum=xl.getrowcount("UserDetails");
		
		String apidata[]=new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{
			apidata[i-1]=xl.getcelldata("UserDetails", i, 1);
		}
		return apidata;
				
	}

}
