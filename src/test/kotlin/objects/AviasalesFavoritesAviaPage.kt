package objects

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

// page_url = https://www.aviasales.ru/favorites/avia
class AviasalesFavoritesAviaPage(val driver: WebDriver) {
    init {
        PageFactory.initElements(driver, this)
    }
    companion object {
        val page_url = "https://www.aviasales.ru/favorites/avia"
    }
}