import objects.AviasalesPage
import objects.AviasalesSearchPage
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

}