import objects.AviasalesPage
import objects.AviasalesSearchPage
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.BeforeTest

class SearchPageTest: SeleniumTest(AviasalesPage.page_url) {

    private lateinit var aviasalesPage: AviasalesPage
    private lateinit var aviasalesSearchPage: AviasalesSearchPage
    @BeforeTest
    override fun initPages() {
        aviasalesPage = AviasalesPage(driver)
        aviasalesSearchPage = AviasalesSearchPage(driver)
        aviasalesPage.closeGoogleIFrameIfExist()
        aviasalesPage.acceptCookiesIfExist()
    }

    @ParameterizedTest
    @CsvSource(
        "Москв, Санкт-Петер, Челябинск"
    )
    fun multiPathTest(city1: String, city2: String, city3: String){
        aviasalesPage.createMultiPath()
        aviasalesSearchPage.createMultiPath(city1, city2, city3, "20.04.2025", "24.04.2025")
        aviasalesPage.findTickets()
        aviasalesSearchPage.findRandomTicket()
    }

}