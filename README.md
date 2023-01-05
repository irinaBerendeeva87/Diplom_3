🚀 UI tests Stellar Burgers 🚀

As part of the training, I tested UI of the Stellar Burgers training service using the JUnit 4, Selenium and Allure . 
The project used Java 11 and used the Maven build system and was tested in Google Chrome and  Yandex.Browser.
 
📝 **Written test scenarios** 📝
- registration(successful registration and an error for an incorrect password);
- logIn( different ways);
- switch to different sections and screens;
- logOut.

📝 **It was completed** 📝

- described the necessary locators to automate test scenarios;
- described the necessary locators using Page Object;
- created a separate class with Page Object for each page;

🔖 **Instructions how to run** 🔖

1. Clone this repo and clone your version of the report. 
2. Run AllTest.java
3. Generate a report via command allure serve target/surefire-reports/
