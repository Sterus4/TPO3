import objects.AviasalesPage
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class SeleniumTest(
    val url: String
) {
    lateinit var driver: WebDriver

    @BeforeTest
    fun setUp() {
        driver = FirefoxDriver()
        driver.get(url)
        driver.manage().window().size = Dimension(1136, 692)
    }

    @AfterTest
    fun tearDown() {
        driver.quit()
    }

    abstract fun initPages()
}
fun WebDriver.waitAndFindElement(search: By?, timeToWait : Long = 10): WebElement {
    val wait = WebDriverWait(this, Duration.ofSeconds(timeToWait))
    return wait.until(ExpectedConditions.visibilityOfElementLocated(search))
}

fun WebDriver.switchToAnotherWindow(duration: Long = 5, action: () -> Unit, ){
    val oldWindows = windowHandles
    action()
    val wait = WebDriverWait(this, Duration.ofSeconds(duration))
    wait.until { this.windowHandles.size > oldWindows.size }

    val newWindows = windowHandles
    val newWindow = newWindows.minus(oldWindows).first()

    switchTo().window(newWindow)

}
