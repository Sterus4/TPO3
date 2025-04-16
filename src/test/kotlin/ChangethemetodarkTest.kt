import objects.AviasalesMySettingsPage
import objects.AviasalesPage
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test


class ChangethemetodarkTest {
    private lateinit var driver: WebDriver
    private var vars: Map<String, Any>? = null
    private lateinit var js: JavascriptExecutor
    @BeforeTest
    fun setUp() {
        driver = ChromeDriver()
        js = (driver as JavascriptExecutor?)!!
        vars = HashMap()
    }

    @AfterTest
    fun tearDown() {
        driver.quit()
    }

    @Test
    fun changethemetodark() {
        driver.get(AviasalesPage.page_url)
        val settingsPage = AviasalesMySettingsPage(driver)
        val homePage = AviasalesPage(driver)

        driver.manage().window().size = Dimension(1136, 692)
        Thread.sleep(3000)

        homePage.acceptCookiesIfExist()
        homePage.openProfilePopUbDialog()
        homePage.openMySettingsPage()

        settingsPage.changeToDarkTheme()

        //TODO assert theme changed
    }
}
