package nut.cc.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "test_plan")
public class TestPlan implements Serializable {

    private static final long serialVersionUID = 201624090403L;

    @Id
    @GenericGenerator(name = "tpgen", strategy = "increment")
    @GeneratedValue(generator = "tpgen")
    private int id;

    @OneToMany(mappedBy = "testPlan", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TestCase> testCases;

    public TestPlan() {}

    public int getId() {
        return id;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

}
