import objects.*
import org.junit.jupiter.api.Test
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.awt.Toolkit

class BothBrowserTest : SeleniumBothTest(mutableListOf({ driver ->
    driver.get(AviasalesPage.page_url)
    val aviasalesPage = AviasalesPage(driver)
    aviasalesPage.acceptCookiesIfExist()
    aviasalesPage.closeGoogleIFrameIfExist()
    aviasalesPage.clickKorocheButton()
    aviasalesPage.clickOnDivByContent("Можно без ви")
    aviasalesPage.clickOnDivByContent("Тбилиси")
    aviasalesPage.clickOnDivByContent("Непопсовое")
    if (driver is FirefoxDriver) {
        aviasalesPage.clickOnDivByContent("Дворец царицы")
    }
}))