import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.awt.Toolkit
import kotlin.test.BeforeTest
import kotlin.test.Test

open class SeleniumBothTest(private val tests: MutableList<(driver: WebDriver) -> Unit>) {
    lateinit var drivers: List<WebDriver>

    @BeforeTest
    fun setUp(){
        drivers = listOf(ChromeDriver(), FirefoxDriver())
    }
    @Test
    fun multiTest() {
        for (testCase in tests){
            drivers.parallelStream().forEach { driver ->
                val screenSize = Toolkit.getDefaultToolkit().screenSize
                val halfWidth = screenSize.width / 2 - 10
                val windowSize = Dimension(halfWidth, screenSize.height - 40)
                driver.manage().window().size = windowSize
                if(driver is FirefoxDriver){
                    driver.manage().window().position = Point(0, 0)
                } else {
                    driver.manage().window().position = Point(screenSize.width / 2,0)
                }
                testCase(driver)
//                driver.quit()
            }
        }
    }
}