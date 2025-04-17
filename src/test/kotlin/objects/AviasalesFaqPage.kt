package objects

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import waitAndFindElement
import java.util.List

// page_url = https://www.aviasales.ru/faq
class AviasalesFaqPage(val driver: WebDriver) {

    private val likeButton = By.xpath("/html/body/div[6]/div/div/section[2]/div/div[1]/div/div[2]/button[1]")
    private val dontLikeButton = By.xpath("/html/body/div[6]/div/div/section[2]/div/div[1]/div/div[2]/button[2]")
    init {
        PageFactory.initElements(driver, this)
    }

    companion object {
        val page_url = "https://www.aviasales.ru/faq"
    }

    fun listToTitle(title: String){
        driver.waitAndFindElement(By.xpath("//a[contains(text(), '${title}')]")).click()
    }

    fun openQuestion(question: String){
        driver.waitAndFindElement(By.xpath("//div[contains(text(), '${question}')]")).click()
    }

    fun likeQuestion(){
        driver.waitAndFindElement(likeButton).click()
    }

    fun dislikeQuestion(){
        driver.waitAndFindElement(dontLikeButton).click()
    }
}