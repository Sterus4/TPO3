import objects.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.openqa.selenium.By
import java.time.Duration
import kotlin.test.BeforeTest
import kotlin.test.Test

class HomePageTest : SeleniumTest(AviasalesPage.page_url){

    private lateinit var aviasalesPage: AviasalesPage
    private lateinit var aviasalesSearchPage: AviasalesSearchPage
    private lateinit var aviasalesFaqPage: AviasalesFaqPage
    private lateinit var aviasalesBookingPage: AviasalesBookingPage
    private lateinit var aviasalesFavoritesAviaPage: AviasalesFavoritesAviaPage
    @BeforeTest
    override fun initPages() {
        aviasalesPage = AviasalesPage(driver)
        aviasalesSearchPage = AviasalesSearchPage(driver)
        aviasalesFaqPage = AviasalesFaqPage(driver)
        aviasalesBookingPage = AviasalesBookingPage(driver)
        aviasalesFavoritesAviaPage = AviasalesFavoritesAviaPage(driver)
        aviasalesPage.acceptCookiesIfExist()
        aviasalesPage.closeGoogleIFrameIfExist()
    }

    @Test
    @Disabled
    fun favoritesTest(){
        loginByVk()
        Thread.sleep(6000)
        aviasalesPage.selectFromCity("Челяб")
        aviasalesPage.selectToCity("Санкт")
        aviasalesPage.openFirstTicket()
        try {
            driver.waitAndFindElement(By.xpath("//*[@id=\"avs-modal-container\"]/div/div/div/div/div[1]/div/div/div[3]/div/button")).click()
        } catch (_:Exception) {}
        aviasalesSearchPage.likeQuery()
        try {
            driver.waitAndFindElement(By.xpath("//*[@id=\"avs-modal-container\"]/div/div/div/div/div[1]/div/div/div[3]/div/button")).click()
        } catch (_:Exception) {}
        aviasalesSearchPage.closeQuery()
        try {
            driver.waitAndFindElement(By.xpath("//*[@id=\"avs-modal-container\"]/div/div/div/div/div[1]/div/div/div[3]/div/button")).click()
        } catch (_:Exception) {}
        aviasalesPage.openFavorites()
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "EUR",
        "GBP"
    ])
    fun changeCurrentCurrency(curr: String){
        aviasalesPage.changeCurrencyTo(curr)
    }

    @Test
    @Disabled
    fun loginByGoogle(){
        aviasalesPage.openProfilePopUbDialog()
        aviasalesPage.openLoginPopUp()
        aviasalesPage.loginUsingGoogle()
    }

    @Test
    fun loginWithWrongPasswordTest() {
        aviasalesPage.openProfilePopUbDialog()
        aviasalesPage.openLoginPopUp()
        aviasalesPage.openVkLoginForm()
        driver.waitAndFindElement(
            By.xpath("/html/body/div/div/div/div/div/div/div/div[2]/div/div/div/form/div[1]/section/div/div/div/div[3]/span/div/div[2]/input")
        ).sendKeys("9511124405")
        driver.waitAndFindElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/div/div[2]/div/div/div/form/div[2]/div[1]/button[1]")).click()
        // Вводим неверный пароль
        driver.waitAndFindElement(
            By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div/div/div/div/form/div[1]/div[3]/div/div/input"),
            20
        ).sendKeys("12321")
        driver.waitAndFindElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div/div/div/div/form/div[2]/button")).click()
        driver.waitAndFindElement(
            By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div/div/div/div/form/div[1]/div[3]/div/div/div[2]"),
            timeToWait = 20
        )
    }
    @Test
    fun loginByVk(){
        aviasalesPage.openProfilePopUbDialog()
        aviasalesPage.openLoginPopUp()
        aviasalesPage.loginUsingVK("9511124405")

    }

    @ParameterizedTest
    @CsvSource(
        "Челяб, Санкт, 19.04.2025, 20.04.2025"
    )
    fun travelCheapestByAviakassaTest(from: String, to: String, whenToGo: String, whenToComeBack: String){
        aviasalesPage.selectFromCity(from)
        aviasalesPage.selectToCity(to)
        aviasalesPage.selectWhenToGo(whenToGo)
        aviasalesPage.selectWhenToComeBack(whenToComeBack)
        aviasalesPage.showCheapestTicket()
        aviasalesSearchPage.buyByAviakassa()
    }
    @ParameterizedTest
    @CsvSource(
        "Челяб, Санкт, 18.04.2025, 20.04.2025"
    )
    fun travelCheapestByCupi(from: String, to: String, whenToGo: String, whenToComeBack: String){
        aviasalesPage.selectFromCity(from)
        aviasalesPage.selectToCity(to)
        aviasalesPage.selectWhenToGo(whenToGo)
        aviasalesPage.selectWhenToComeBack(whenToComeBack)
        aviasalesPage.showCheapestTicket()
        aviasalesSearchPage.buyByCupiBilet()
    }

    @ParameterizedTest
    @CsvSource(
        "Челяб, Санкт, 2025-04-25"
    )
    fun showAndSubmitPriceChart(from: String, to: String, whenToGo: String) {
        aviasalesPage.selectFromCity(from)
        aviasalesPage.selectToCity(to)
        aviasalesPage.chooseOnPriceChartAndCommit(whenToGo)
        aviasalesPage.showCheapestTicket()
        Thread.sleep(5000)
    }

    @ParameterizedTest
    @CsvSource(
        "Челяб, Санкт, true"
    )
    fun fillInformationAboutMeUsingAviasales(
        from: String, to: String, extraBaggage: Boolean
    ){
        aviasalesPage.selectFromCity(from)
        aviasalesPage.selectToCity(to)

        aviasalesPage.openFirstTicket()
        aviasalesSearchPage.buyUsingAviasales()
        aviasalesBookingPage.fiilInformation(
            "someEmail@mail.com",
            "79511124406",
            false,
            "Бомбардиро",
            "Крокодило",
            "Владимирович",
            "28.09.2002",
            "2241910384",
            extraBaggage
        )
        aviasalesBookingPage.nextWindow()
        Thread.sleep(2000)
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "Про пересадки"
    ])
    fun showFAQQuestionsTest(title: String){
        aviasalesPage.openSupport()
        aviasalesFaqPage.listToTitle(title)
        Thread.sleep(2000)
    }

    @ParameterizedTest
    @CsvSource(
        "Как работает Авиасейлс, true",
        "Как работает Авиасейлс, false"
    )
    fun showFAQQuestionAndLikeTest(question: String, like: Boolean){
        aviasalesPage.openSupport()
        aviasalesFaqPage.openQuestion(question)
        if(like) aviasalesFaqPage.likeQuestion() else aviasalesFaqPage.dislikeQuestion()
        Thread.sleep(2000)
    }

}