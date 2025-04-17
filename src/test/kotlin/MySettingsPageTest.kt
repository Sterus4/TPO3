import objects.AviasalesMySettingsPage
import objects.AviasalesPage
import kotlin.test.BeforeTest
import kotlin.test.Test


class MySettingsPageTest : SeleniumTest(AviasalesPage.page_url) {
    private lateinit var homePage: AviasalesPage
    private lateinit var settingsPage: AviasalesMySettingsPage

    @BeforeTest
    override fun initPages(){
        homePage = AviasalesPage(driver)
        settingsPage = AviasalesMySettingsPage(driver)
    }
    @Test
    fun changethemetodark() {
        homePage.acceptCookiesIfExist()
        homePage.openProfilePopUbDialog()
        homePage.openMySettingsPage()

        settingsPage.changeToDarkTheme()

        //TODO assert theme changed
    }
}
