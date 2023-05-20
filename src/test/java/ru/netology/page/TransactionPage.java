package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransactionPage {
    private SelenideElement sumAmount = $("[data-test-id='amount'] input");
    private SelenideElement fromСard = $("[data-test-id='from'] input");
    private SelenideElement replenish = $("[data-test-id='action-transfer']");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $("[data-test-id='error-message']");
    private SelenideElement errorCard = $("[data-test-id='error-notification'] .notification__content");

    public TransactionPage(){
        transferHead.shouldBe(visible);
    }

    public DashboardPage transferOfMoneyValid(String amount, DataHelper.CardsInfo cardsInfo) {
        sumAmount.setValue(amount);
        fromСard.setValue(cardsInfo.getCardNumber());
        replenish.click();
        return new DashboardPage();
    }

    public DashboardPage transferOfMoneyValid(String amount, String cardNumber) {
        sumAmount.setValue(amount);
        fromСard.setValue(cardNumber.toString());
        replenish.click();
        return new DashboardPage();
    }
}