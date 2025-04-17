package objects

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import waitAndFindElement

// page_url = https://www.aviasales.ru/my/settings
class AviasalesMySettingsPage(val driver: WebDriver) {
    private val darkThemeButton = By.xpath("//div[@title='dark'][.//*[@id=':profile_settingsr1:']]")
    private val lightThemeButton = By.xpath("//div[@title='light'][.//*[@id=':profile_settingsr0:']]")
    companion object {
        val page_url = "https://www.aviasales.ru/my/settings"
    }
    init {
        PageFactory.initElements(driver, this)
    }

    fun changeToDarkTheme(){
        driver.waitAndFindElement(darkThemeButton).click()
    }

    fun changeToLightTheme(){
        driver.waitAndFindElement(lightThemeButton).click()
    }
}