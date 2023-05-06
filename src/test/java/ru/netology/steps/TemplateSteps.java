package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.*;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransactionPage;
import ru.netology.page.VerificationPage;

import static org.junit.Assert.assertEquals;
import static ru.netology.data.DataHelper.getFirstCardNumber;
import static ru.netology.data.DataHelper.getSecondCardNumber;

public class TemplateSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransactionPage transactionPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}, вводит проверочный код 'из смс' {string}")
    public void openAuthPage1(String login, String password, String verificationCode) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = loginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.verifyIsDashboardPage();
    }

    @Когда("пользователь переводит {int} рублей с карты с номером '5559 0000 0000 0002' на свою '1' карту с главной страницы")
    public void transferForFirstCard(int amount){
        transactionPage = dashboardPage.transferMoney(getFirstCardNumber());
        dashboardPage = transactionPage.transferOfMoneyValid(String.valueOf(amount),getSecondCardNumber());
    }

    @Тогда("баланс его '1' карты из списка на главной странице должен стать {int} рублей")
    public void balanceForFirstCard(int expectedBalanceFirst, DataHelper.CardsInfo firstCard) {
        var actualBalanceFirst = dashboardPage.getCardBalance(firstCard);
        assertEquals(expectedBalanceFirst, actualBalanceFirst);
    }
}