package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.ProjectUtils;

import java.util.List;

public class FreestyleProjectTest extends BaseTest {
// Navigation Test
    @Test
    public void testCreateNewJobPlusIconNavigation() {
        final String expectedUrl = ProjectUtils.getBaseUrl() + "/newJob";
       final  String expectedTitle = "New Item [Jenkins]";

        String oldUrl = getDriver().getCurrentUrl();
        String oldTitle = getDriver().getTitle();

        getDriver().findElement(By.xpath("//a[@href='newJob']/span[@class='trailing-icon']")).click();

       final  String newUrl = getDriver().getCurrentUrl();
       final String newTitle = getDriver().getTitle();

        Assert.assertNotEquals(newUrl, oldUrl);
        Assert.assertNotEquals(newTitle, oldTitle);

        Assert.assertEquals(newUrl, expectedUrl);
        Assert.assertEquals(newTitle, expectedTitle);
    }
//Functional Create Freestyle project Job test
    @Test(dependsOnMethods = "testCreateNewJobPlusIconNavigation")
    public void testCreateFreestyleProject() throws InterruptedException {
        final int expectedAmountOfJobsCreated = 1;
        final String expectedJobNameCreated = "Test";
        getDriver().findElement(By.xpath("//a[@href='newJob']/span[@class='trailing-icon']")).click();
        getDriver().findElement(By.id("name")).sendKeys("Test");
        getDriver().findElement(By.xpath("//li[@class='hudson_model_FreeStyleProject']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.id("jenkins-name-icon")).click();
        List<WebElement> jobs = getDriver().findElements(By.xpath("//table[@id='projectstatus']/tbody//tr"));

        Assert.assertEquals(jobs.size(), expectedAmountOfJobsCreated);

        WebElement jobNameElement = jobs.get(0).findElement(By.xpath("//td/a/span"));
        final String actualJobName = jobNameElement.getText();

        Assert.assertTrue(jobNameElement.isDisplayed()1);

        Assert.assertEquals(actualJobName, expectedJobNameCreated);
    }
}



