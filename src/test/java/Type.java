import pages.TypingPage;
import org.testng.annotations.*;
import utils.ConstantsClass;

public class Type {
    TypingPage typingPage = new TypingPage();
    ConstantsClass cons;

    @Test
    public void typing() throws InterruptedException {
        typingPage.waitForManualFingerValidation();
        typingPage.typeAllRequiredKeyboardValues(cons.NUMBER_OF_COMPLETIONS);
    }

    @AfterTest
    public void quit() {
        typingPage.getDriver().quit();
    }
}
