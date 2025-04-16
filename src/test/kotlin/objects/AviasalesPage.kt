package objects

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import java.util.List

// page_url = https://www.aviasales.ru/?params=PAR1
class AviasalesPage(val driver: WebDriver) {
    private var acceptCookiesButton = By.xpath("//button[@data-test-id='accept-cookies-button']")
    private var openProfileButton = By.xpath("//button/div[2]")
    private var openMySettingsWindow = By.xpath("//div[2]/div/div/div/div/div/nav/ul/li/a/div[2]")
    companion object {
        val page_url = "https://www.aviasales.ru/?params=PAR1"
    }
    init {
        PageFactory.initElements(driver, this)
    }

    fun acceptCookiesIfExist(){
        try { driver.findElement(acceptCookiesButton).click() }
        catch (_ : Exception) {}
    }

    fun openProfilePopUbDialog(){
        driver.findElement(openProfileButton).click()
    }

    fun openMySettingsPage(){
        //TODO проверить что pop up окно открыто
        driver.findElement(openMySettingsWindow).click()
    }
}