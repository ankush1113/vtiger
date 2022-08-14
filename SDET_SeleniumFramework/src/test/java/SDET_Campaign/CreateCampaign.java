package SDET_Campaign;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import GenericUtility.ExcelUtility;
import GenericUtility.WebDriver_Utility;
import GenericUtility.javautility;
import ObjectRepo.CampaignPage;
import ObjectRepo.CreateCampaignPage;
import ObjectRepo.HomePage;
import ObjectRepo.ProductValidationPage;


public class CreateCampaign{

		
		@Test()
		public void createCampaign() throws Throwable {
			//fetch data from properties file
			WebDriver driver=null;
			WebDriver_Utility wlib = new WebDriver_Utility();
			 javautility jlib = new javautility();
			
			wlib.waitForPageToLoad(driver);

			HomePage hp = new HomePage(driver);
			hp.goToCampaignPage(driver);
		    CampaignPage cp = new CampaignPage(driver);
		    cp.createNewCampaignPage();
//		    Assert.assertEquals(true, false);
		    int ranNum = jlib.getRandomNum();

	        //fetching data from excel
			 ExcelUtility excel = new ExcelUtility();
			String cpnName = excel.getExcelData("./data/testdata.xlsx", "campaign", 1, 0)+ranNum;
			System.out.println(cpnName);

		    CreateCampaignPage ncp = new CreateCampaignPage(driver);
		    ncp.campaignTextfield(cpnName);
		    ncp.saveButton();

		    Thread.sleep(2000);
		    hp.takesScreenshotAs(driver, cpnName);
		    ProductValidationPage actualCampaignName = new ProductValidationPage(driver);
			String actualCampaignData = actualCampaignName.actualCampaignName();
			Assert.assertEquals(actualCampaignData.contains(cpnName),true);

			Thread.sleep(2000);
		//	hp.takesScreenshotAs(driver, cpnName);
			hp.signOut(driver);
			Thread.sleep(2000);
			driver.close();


		}
	
	
}