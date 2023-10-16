import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class SpotifyTest {

    @Test
    public void Spotify() {
        String username = "martha_st@gmx.com";
        String password = "Duotech123";
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize(); // maximize Window
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // set implicit wait
    driver.get("https://open.spotify.com/");

    //Log In
    driver.findElement(By.xpath("//span[@class='ButtonInner-sc-14ud5tc-0 bABUvF encore-inverted-light-set']")).click();
    driver.findElement(By.id("login-username")).sendKeys(username);
    driver.findElement(By.id("login-password")).sendKeys(password);
    driver.findElement(By.xpath("//span[@class='Wrapper-sc-16y5c87-0 imHfsA']")).click();
    driver.findElement(By.xpath("//span[.='Log In']")).click();

    //Validate that the profile icon is displayed at the top right,
    // indicating a successful login. (it is a <figure> element)
    Assert.assertTrue(driver.findElement(By.xpath("//figure[@class='tp8rO9vtqBGPLOhwcdYv']")).isDisplayed());

    //Music Search:
    // Click on Search link on the left bar and Use the search bar at the top to search for a specific artist and song
    driver.findElement(By.xpath("//*[@aria-label='Search']")).click();
    driver.findElement(By.xpath("//*[@data-testid='search-input']")).sendKeys("Måneskin", Keys.ENTER);

    // Play the relevant song (ZITTI E BUONI) by clicking on the play icon
    driver.findElement(By.xpath("//span[@class='ChipInner__ChipInnerComponent-sc-1ly6j4j-0 xFYaL'][.='Songs']")).click();

    driver.findElement(By.xpath("//button[@aria-label='Play ZITTI E BUONI by Måneskin']//parent::*")).click();

    // Once song is playing, validate the play functionality by verifying the song name and artist
    // in the now-playing section at the left bottom corner.
    String nameOfTheSong = driver.findElement(By.xpath("//a[@data-testid='context-item-link']")).getText();
    String nameOfTheArtist = driver.findElement(By.xpath("//a[@data-testid='context-item-info-artist']")).getText();
    String expectedSong = "Måneskin - ZITTI E BUONI";
    String actualSong = nameOfTheArtist + " - " + nameOfTheSong;
    Assert.assertEquals(actualSong.toLowerCase(), expectedSong.toLowerCase());

    //Logout
    // Click on the profile icon  at the top right to access the account dropdown and click Log Out.
    driver.findElement(By.xpath("//figure[@class='tp8rO9vtqBGPLOhwcdYv']")).click();
    driver.findElement(By.xpath("//button[@data-testid='user-widget-dropdown-logout']")).click();

    // Validate the user has been logged out by ensuring the 'Log In' button is present.
    Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ButtonInner-sc-14ud5tc-0 bABUvF encore-inverted-light-set']")).isDisplayed());

    }
}
