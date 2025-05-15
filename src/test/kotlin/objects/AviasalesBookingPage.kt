package objects

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import waitAndFindElement

// page_url = https://www.aviasales.ru/booking
class AviasalesBookingPage(val driver: WebDriver) {
    private val emailInput = By.xpath("//input[@name='contact.email']")
    private val phoneInput = By.xpath("//input[@name='contact.phone']")
    private val maleLabel = By.xpath("//label[@title='Мужчина']")
    private val femaleLabel = By.xpath("//label[@title='Женщина']")
    private val surnameInput = By.xpath("//input[@id='last-name-0']")
    private val nameInput = By.xpath("//input[@id='first-name-0']")
    private val patronymicInput = By.xpath("//input[@id='second-name-0']")
    private val birthdayInput = By.xpath("//input[@id='birth-date-0']")
    private val documentInput = By.xpath("//*[@id=\"document-number-0\"]")
    private val extraBaggageButton = By.xpath("/html/body/div[6]/div[2]/main/div/article/form/div[2]/div/article/div[2]/section/ul/li[2]/div[2]/div/button")
    private val nextStepButton = By.xpath("//button[@data-test-id='next-step-button']")
    fun fiilInformation(
        email: String,
        phone: String,
        male: Boolean,
        surname: String,
        name: String,
        patronymic: String,
        birthday: String,
        document: String,
        baggage: Boolean
    ) {
        try {
            driver.waitAndFindElement(emailInput, 20).sendKeys(email)
            driver.waitAndFindElement(phoneInput).click()
            driver.waitAndFindElement(phoneInput).sendKeys(Keys.CONTROL, "a")
            driver.waitAndFindElement(phoneInput).sendKeys(Keys.BACK_SPACE)
            driver.waitAndFindElement(phoneInput).sendKeys(phone)
        } catch (_: Exception) {}

        if(male){
            driver.waitAndFindElement(maleLabel).click()
        } else {
            driver.waitAndFindElement(femaleLabel).click()
        }
        driver.waitAndFindElement(surnameInput).sendKeys(surname)
        driver.waitAndFindElement(nameInput).sendKeys(name)
        driver.waitAndFindElement(patronymicInput).sendKeys(patronymic)
        driver.waitAndFindElement(birthdayInput).sendKeys(birthday)
        driver.waitAndFindElement(documentInput).sendKeys(document)
        if(baggage){
            driver.waitAndFindElement(extraBaggageButton).click()
        }
    }

    fun nextWindow(){
        driver.waitAndFindElement(nextStepButton).click()
    }
    init {
        PageFactory.initElements(driver, this)
    }

    companion object {
        val page_url = "https://www.aviasales.ru/booking"
    }
}