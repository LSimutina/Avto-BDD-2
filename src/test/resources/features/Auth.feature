#language:ru

Функциональность: Авторизация

  # пример теста с одним набором параметров
  Сценарий: : Перевод 5000 со второй карты на первую
    Пусть пользователь залогинен с именем "vasya" и паролем "qwerty123", вводит проверочный код 'из смс' "12345"
    Когда пользователь переводит 5000 рублей с карты с номером '5559 0000 0000 0002' на свою первую карту с главной страницы
    Тогда баланс его '1' карты из списка на главной странице должен стать 15000 рублей