package nut.cc.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TestCase implements Serializable {

    private static final long serialVersionUID = 20160624090103L;

    @Id
    @GeneratedValue
    private int id;

    private String expression;

    private String expectedResult;

    @ManyToOne
    @JoinColumn(name = "test_plan_id")
    private TestPlan testPlan;

    public TestCase() {}

    public TestCase(String expression, String expectedResult, TestPlan testPlan) {
        this.expression = expression;
        this.expectedResult = expectedResult;
        this.testPlan = testPlan;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestCase testCase = (TestCase) o;

        if (id != testCase.id) return false;
        if (!expression.equals(testCase.expression)) return false;
        return expectedResult.equals(testCase.expectedResult);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + expression.hashCode();
        result = 31 * result + expectedResult.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(expression)
                                    .append(" | ")
                                    .append(expectedResult).toString();
    }
}
