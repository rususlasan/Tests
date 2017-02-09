package nut.cc.repositories;

import nut.cc.entities.TestPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestPlanRepository extends JpaRepository<TestPlan, Integer> {


}
