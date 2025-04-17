package objects

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import switchToAnotherWindow
import waitAndFindElement

// page_url = https://www.aviasales.ru/search/
class AviasalesSearchPage(val driver: WebDriver) {

    val buyUsingAviakassa = By.xpath("//div[contains(text(), 'Aviakassa')]")
    val buyUsingCupiBilet = By.xpath("//div[contains(text(), 'Купибилет')]")
    val buyUsingAviasales = By.xpath("//div[contains(text(), 'Напрямую у')]")

    val randomTicket = By.xpath("//div[contains(text(), 'Выбрать билет')]")

    init {
        PageFactory.initElements(driver, this)
    }
    companion object {
        val page_url = "https://www.aviasales.ru/search/"
    }

    fun buyByAviakassa(){
        driver.waitAndFindElement(buyUsingAviakassa, 20).click()
    }

    fun buyByCupiBilet(){
        driver.waitAndFindElement(buyUsingCupiBilet, 20).click()
    }

    fun buyUsingAviasales(){
        driver.switchToAnotherWindow { driver.waitAndFindElement(buyUsingAviasales, 50).click() }
    }

    fun chooseRandomTicket(){
        driver.waitAndFindElement(randomTicket).click()
    }
}

