package nut.cc.android;

import io.appium.java_client.AppiumDriver;
import nut.cc.entities.TestCase;
import nut.cc.entities.TestPlan;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class AndroidTestRunner {

    private static Properties appElementsLocation = null;

    private static AppiumDriver driver;

    private static void init() {
        appElementsLocation = ApplicationElementsLocations.getInstance();
        driver = DriverProducer.getInstance();
    }

    public static String runTest(TestCase testCase) {
        if (driver == null || appElementsLocation == null) init();

        //хранит выражение которое будем вводить
        String expression = testCase.getExpression().replaceAll(",", ".");
        //хранит ожидаемый результат
        String expectedResult = testCase.getExpectedResult().trim();

        try {
            //начинаем с очистки поля формулы
            driver.findElement(By.id(appElementsLocation.getProperty("formulaField"))).clear();

            //проходимся по каждому еллементу из expression и вызываем метод click() на нем
            for (int i = 0; i < expression.length(); i++) {
                driver.findElement(By.id(
                        appElementsLocation.
                                getProperty(
                                        expression.charAt(i) + ""))
                ).click();
            }
            //нажимаем "="
            driver.findElement(By.id(
                    appElementsLocation.getProperty("=")
            )).click();

            //считываем результат с устройства
            String result = driver.findElement(By.id(
                    appElementsLocation
                            .getProperty("formulaField")))
                    .getText();

            //хранит отчет о результате теста
            StringBuilder res = new StringBuilder("Тест-кейс: ")
                    .append(testCase.toString())
                    .append(" - завершен. ")
                    .append("Получен результат - ")
                    .append(result)
                    .append("\t ");

            //true - тест пройден, false - нет
            boolean passOrNo = false;

            //проверяем соответствие ожидаемого результаты и полученного
            if (result.equals(expectedResult)) {
                passOrNo = true;
            }
            //минус на моем устройстве считывался как char символ 8722,
            //если результат - отрицательное выражение, то откидываем минус и сравниваем оставшиеся строки
            else if ((int)result.charAt(0) == 8722 && expectedResult.charAt(0) == '-'){
                passOrNo = expectedResult.substring(1).equals(result.substring(1));
            }

            if (passOrNo) return res.append(". ПРОЙДЕН").toString();
            else return res.append(" . НЕ ПРОЙДЕН").toString();
        } catch (WebDriverException | IllegalArgumentException e) {
            return new StringBuilder("Во время выполнения теста: ")
                    .append(testCase.toString())
                    .append("\tвозникла ошибка\t")
                    .append(e.getMessage()).toString();
        }
    }

    public static List<String> runTest(TestPlan testPlan) {
        //хранит все тест-кейсы из плана
        List<TestCase> testCases = testPlan.getTestCases();
        //хранит результаты прогона каждого тест-кейса
        List<String> reports = new ArrayList<>();

        //выполняем каждый тест-кейс и записывает результат в reports
        for(TestCase testCase: testCases) {
            reports.add(runTest(testCase));
        }
        return reports;
    }

    public static void close() {
        try {
            driver.quit();
            driver = null;
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }
}
