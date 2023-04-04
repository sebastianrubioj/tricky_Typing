package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TypingPage extends BasePage {

    private static final String COMPLETE_BANNER = "//div[@class='modal sequence show']//div[text()='Practice Complete!']";
    private static final String PROGRESS_BAR = "//div[contains(@class,'progress-bar')]";
    private static final String FINGERS_MANUAL_VALIDATION = "div.modal.sequence.show modal-header.ng-scope";
    private static final String LETTERS = "div.line span.ng-binding";
    private static final String LESSON_SCREEN = "lesson";
    private static final String SAVING_PROGRESS_MESSAGE = "//small[text()='Saving Progress']";

    @FindBy(xpath = COMPLETE_BANNER)
    WebElement completeBanner;
    @FindBy(css = LETTERS)
    List<WebElement> letters;
    @FindBy(id = LESSON_SCREEN)
    WebElement lessonScreen;
    @FindBy(css = FINGERS_MANUAL_VALIDATION)
    WebElement fingersManualValidation;
    @FindBy(xpath = PROGRESS_BAR)
    WebElement progressBar;
    @FindBy(xpath = SAVING_PROGRESS_MESSAGE)
    List<WebElement> savingProgressMessage;

    @FindBy(xpath = SAVING_PROGRESS_MESSAGE)
    WebElement savingProgressMessageElement;

    public void waitForManualFingerValidation() {
        getWait().until(ExpectedConditions.visibilityOf(fingersManualValidation));
        //then user type manually the image keyboard validation
        getWait().until(ExpectedConditions.invisibilityOf(fingersManualValidation));
    }

    public void typeAllRequiredKeyboardValues(int numberOfCompletions) throws InterruptedException {
        int counter =0;
        while (numberOfCompletions>counter) {
            getWait().until(ExpectedConditions.invisibilityOf(fingersManualValidation));
            getWait().until(ExpectedConditions.visibilityOf(lessonScreen));
            for (WebElement element : letters) {
                Thread.sleep(100);
                getDriver().findElement(By.xpath("//body")).sendKeys(element.getText());
            }
            Thread.sleep(1500);
            if(savingProgressMessage.size()>0 || progressBar.getAttribute("aria-valuenow").equals("100")){
                getWait().until(ExpectedConditions.invisibilityOf(savingProgressMessageElement));
                getWait().until(ExpectedConditions.visibilityOf(completeBanner));
                getWait().until(ExpectedConditions.invisibilityOf(completeBanner));
                counter ++;
            }
        }
    }
}
