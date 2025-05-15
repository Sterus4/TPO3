package objects

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.WebDriverWait
import switchToAnotherWindow
import waitAndFindElement
import java.time.Duration
import java.util.concurrent.TimeUnit


// page_url = https://www.aviasales.ru/search/
class AviasalesSearchPage(val driver: WebDriver) {

    val buyUsingAviakassa = By.xpath("//div[contains(text(), 'Aviakassa')]")
    val buyUsingCupiBilet = By.xpath("//div[contains(text(), 'Купибилет')]")
    val buyUsingAviasales = By.xpath("//div[contains(text(), 'Напрямую у')]")

    val randomTicket = By.xpath("//button/div[contains(text(), 'Выбрать билет')]")
    val multiInput1 = By.xpath("//input[@id='multiway_form_origin-input' and @tabindex='1']")
    val multiInput2 = By.xpath("//input[@id='multiway_form_origin-input' and @tabindex='2']")
    val multiInputTo1 = By.xpath("//input[@id='multiway_form_destination-input' and @tabindex='1']")
    val multiInputTo2 = By.xpath("//input[@id='multiway_form_destination-input' and @tabindex='2']")
    private val firstCity = By.xpath("//div[@data-test-id='dropdown']/*")

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

    fun selectCity(city: String, form: By){
        driver.waitAndFindElement(form).click()
        driver.waitAndFindElement(form).sendKeys(city)
        driver.waitAndFindElement(firstCity).click()
        val wait: Wait<WebDriver> = WebDriverWait(driver, Duration.ofSeconds(2))
    }
    fun findRandomTicket(){
        driver.waitAndFindElement(randomTicket, timeToWait = 30)
    }

    fun createMultiPath(city1: String, city2: String, city3: String, date1: String, date2: String) {
        selectCity(city1, multiInput1)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1))
        selectCity(city2, multiInput2)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1))
        selectCity(city2, multiInputTo1)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1))
        selectCity(city3, multiInputTo2)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1))
        selectDate(date1, By.xpath("//button[@tabindex='1' and @data-test-id='multiway-date']"))
        selectDate(date2, By.xpath("//button[@tabindex='2' and @data-test-id='multiway-date']"))

    }

    private fun selectDate(date1: String, form: By) {
        driver.waitAndFindElement(form).click()
        driver.waitAndFindElement(By.xpath("//div[@data-test-id='date-${date1}']")).click()
    }

    fun likeQuery() {
        driver.waitAndFindElement(By.xpath("//*[@id=\"avs-modal-container\"]/div/div/div/div/div[1]/div/div/div[3]/div/div/button[1]"), 20).click()
    }

    fun closeQuery() {
        driver.waitAndFindElement(By.xpath("//*[@id=\"avs-modal-container\"]/div/div/div/div/div[1]/div/div/div[1]/div/button"))
    }
}

