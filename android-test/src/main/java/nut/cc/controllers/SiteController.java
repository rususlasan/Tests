package nut.cc.controllers;
import nut.cc.android.AndroidTestRunner;
import nut.cc.entities.TestCase;
import nut.cc.entities.TestPlan;
import nut.cc.repositories.TestPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/")
public class SiteController {

    @Autowired
    private TestPlanRepository testPlanRepository;

    @RequestMapping
    public ModelAndView index() {
        Map<String, List<TestPlan>> model = new HashMap<>();
        model.put("testPlans", testPlanRepository.findAll());

        return new ModelAndView("/index", model);
    }

    @RequestMapping(value = "addtestcase", method = RequestMethod.POST)
    public String createTestCase(@RequestParam String expression,
                                 @RequestParam String expectedResult,
                                 @RequestParam String testPlanID)
    {
        int tp_id = Integer.parseInt(testPlanID);
        TestPlan boundedTestPlan = testPlanRepository.findOne(tp_id);

        List<TestCase> testCases = new ArrayList<TestCase>(){{
            add(new TestCase(expression, expectedResult, boundedTestPlan));
        }};

        boundedTestPlan.setTestCases(testCases);

        testPlanRepository.save(new ArrayList<TestPlan>(){{
            add(boundedTestPlan);
        }});
        return "redirect:/";
    }

    @RequestMapping(value = "addtestplan", method = RequestMethod.GET)
    public String createTestPlan() {
        testPlanRepository.save(new TestPlan());

        return "redirect:/";
    }

    @RequestMapping(value = "runtest", method = RequestMethod.POST)
    public ModelAndView runTest(@RequestParam String tpNumber, @RequestParam String tcNumber) {
        //хранит результаты теста
        List<String> res;

        int testPlanId = Integer.parseInt(tpNumber);
        TestPlan currTestPlan =testPlanRepository.findOne(testPlanId);

        //если параметр tcNumber не передан, то выполняется весь тест-план
        if (tcNumber.length() == 0) {
            res = AndroidTestRunner.runTest(currTestPlan);
        } else {
            //номер тест-кейса в тест-плане
            int testCaseNumber = Integer.parseInt(tcNumber);
            TestCase currTestCase = currTestPlan.getTestCases().get(testCaseNumber - 1);

            res = Collections.singletonList(
                    AndroidTestRunner.runTest(currTestCase)
                    );
        }
        //закрываем драйвер
        AndroidTestRunner.close();

        Map<String, List<String>> model = new HashMap<>();
        model.put("resultsOfTest", res);

        return new ModelAndView("/testresult", model);

    }

    @RequestMapping(value = "return", method = RequestMethod.GET)
    public String returnToHomePage() {
        return "redirect:/";
    }
}
