package objects

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import switchToAnotherWindow
import waitAndFindElement
import java.time.Duration

// page_url = https://www.aviasales.ru/?params=PAR1
class AviasalesPage(val driver: WebDriver) {
    private val acceptCookiesButton = By.xpath("//button[@data-test-id='accept-cookies-button']")
    private val openProfileButton = By.xpath("//button/div[2]")
    private val openMySettingsWindow = By.xpath("//div[2]/div/div/div/div/div/nav/ul/li/a/div[2]")
    private val loginButton = By.xpath("//div[2]/div/div/div/div/div/div/button")
    private val currencyListButton = By.xpath("//div/button")
    private val loginUsingGoogle = By.xpath("/html/body/div[12]/div/div/div/div/div[2]/div/div/div[2]/div[1]/button[1]")
    private val loginUsingVK = By.xpath("/html/body/div[12]/div/div/div/div/div[2]/div/div/div[2]/div[1]/button[2]")
    private val fromCity = By.xpath("//*[@id=\"avia_form_origin-input\"]")
    private val fromCityFirst = By.xpath("//li[@id='avia_form_origin-item-0']/div[2]/div/span/span[2]")
    private val toCity = By.xpath("//input[@id=\"avia_form_destination-input\"]")
    private val toCityFirst = By.xpath("//li[@id='avia_form_destination-item-0']/div[2]/div/span/span[2]")
    private val whenToGo = By.xpath("//*[contains(text(), 'Когд')]")
    private val whenToComeBack = By.xpath("//*[contains(text(), 'Обратн')]")
    protected val findTickets = By.xpath("//button[@data-test-id='form-submit']")
    private val cheapestTicket = By.xpath("//span[contains(text(), 'Самый де')]")
    private val firstTicket = By.xpath("//a[@data-test-id='ticket-1']/div/div/div")
    private val randomTicket = By.xpath("//div[contains(text(), 'Выбрать билет')]")
    private val supportButton = By.xpath("//a[@data-test-id='header-support-button']")
    private val muitipathButton = By.xpath("//button[@data-test-id='switch-to-multiwayform']")

    companion object {
        val page_url = "https://www.aviasales.ru/"
    }
    init {
        PageFactory.initElements(driver, this)
    }

    fun acceptCookiesIfExist(){
        try { driver.waitAndFindElement(acceptCookiesButton, timeToWait = 1).click() }
        catch (_ : Exception) {}
    }

    fun openProfilePopUbDialog(){
        driver.waitAndFindElement(openProfileButton).click()
    }

    fun openLoginPopUp(){
        driver.waitAndFindElement(loginButton).click()
    }

    fun loginUsingGoogle(){
        driver.waitAndFindElement(loginUsingGoogle).click()
    }

    fun loginUsingVK(phoneNumber: String) {
        val originalWindow = driver.windowHandle
        driver.switchToAnotherWindow { driver.waitAndFindElement(loginUsingVK).click() }
        driver.waitAndFindElement(
            By.xpath("/html/body/div/div/div/div/div/div/div/div[2]/div/div/div/form/div[1]/section/div/div/div/div[3]/span/div/div[2]/input")
        ).sendKeys(phoneNumber)
        driver.waitAndFindElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div[2]/div/div/div/form/div[2]/div[1]/button[1]")).click()
        // Вводим верный пароль
        Thread.sleep(15000)
        driver.waitAndFindElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div/div/div/div/form/div[2]/button")).click()
        // Вводим капчу
        Thread.sleep(15000)
        driver.waitAndFindElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div/div/div/div/form/div[3]/button")).click()
        driver.switchTo().window(originalWindow)
    }

    fun openMySettingsPage(){
        //TODO проверить что pop up окно открыто
        driver.waitAndFindElement(openMySettingsWindow).click()
    }

    fun createMultiPath(city1: String, city2: String, city3: String){

    }

    fun changeCurrencyTo(curr: String){

        driver.waitAndFindElement(currencyListButton).click()
        driver.waitAndFindElement(By.xpath("//p[contains(text(), '${curr}')]")).click()
    }

    fun selectFromCity(city: String){
        driver.waitAndFindElement(fromCity).click()
        driver.waitAndFindElement(fromCity).sendKeys(city)
        Thread.sleep(1000)
        driver.waitAndFindElement(fromCityFirst).click()
    }

    fun selectToCity(city: String){
        driver.waitAndFindElement(toCity).click()
        driver.waitAndFindElement(toCity).sendKeys(city)
        Thread.sleep(1000)
        driver.waitAndFindElement(toCityFirst).click()
    }

    fun selectWhenToGo(date: String){
        driver.waitAndFindElement(whenToGo).click()
        driver.waitAndFindElement(By.xpath("//div[@data-test-id=\"date-${date}\"]")).click()
    }
    fun selectWhenToComeBack(date: String){
        driver.waitAndFindElement(whenToComeBack).click()
        driver.waitAndFindElement(By.xpath("//div[@data-test-id=\"date-${date}\"]")).click()
    }

    fun findTickets(){
        driver.findElement(findTickets).click()
    }

    fun chooseOnPriceChartAndCommit(date: String){
        driver.waitAndFindElement(By.xpath("//tr[@data-test-id='column-${date}']")).click()
        driver.waitAndFindElement(By.xpath("//button[@data-test-id='one-way-button']")).click()
    }

    fun showCheapestTicket(){
        driver.switchToAnotherWindow { driver.waitAndFindElement(cheapestTicket).click() }
    }

    fun openFirstTicket(){
        driver.switchToAnotherWindow { driver.waitAndFindElement(firstTicket).click() }
    }
    fun closeGoogleIFrameIfExist() {
        try {
            driver.switchTo().frame(driver.waitAndFindElement(By.xpath("/html/body/div[20]/iframe"), timeToWait = 1))
            driver.waitAndFindElement(By.xpath("/html/body/div/div[1]/div/div[1]/div[2]"), timeToWait = 1).click()
            driver.switchTo().parentFrame()
        } catch (_: Exception){}
    }

    fun openSupport(){
        driver.switchToAnotherWindow { driver.waitAndFindElement(supportButton).click() }
    }

    fun openVkLoginForm() {
        driver.switchToAnotherWindow { driver.waitAndFindElement(loginUsingVK).click() }
    }
}