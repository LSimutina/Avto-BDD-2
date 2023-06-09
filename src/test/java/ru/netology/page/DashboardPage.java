package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceFinish = " р.";


    public void verifyIsDashboardPage(){
        heading.shouldBe(visible);
    }

    public int getCardBalance(DataHelper.CardsInfo cardsInfo) {
        var text = cards.findBy(Condition.text(cardsInfo.getCardNumber().substring(15))).getText();
        return extractBalance(text);
    }

    public int getCardBalance(int index){
        var text=cards.get(index).getText();
        return extractBalance(text);
    }

    public TransactionPage transferMoney(DataHelper.CardsInfo cardsInfo) {
        cards.findBy(attribute("data-test-id", cardsInfo.getTestId())).$("button").click();
        return new TransactionPage();
    }

//    public TransactionPage transferMoney(int index) {
//        cards.findBy(attribute("data-test-id", String.valueOf(cards.get(index)))).$("button").click();
//        return new TransactionPage();
//    }

    private int extractBalance(String text) {
        String balanceStart = "баланс: ";
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
