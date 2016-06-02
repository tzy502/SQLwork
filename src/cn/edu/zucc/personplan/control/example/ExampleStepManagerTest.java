package cn.edu.zucc.personplan.control.example;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.control.example.*;

public class ExampleStepManagerTest {

	@Test
	public void test() throws BaseException {
		ExampleStepManager a=new ExampleStepManager();
		Date date=new Date();
		a.add(new ExamplePlanManager().SearchPlan(1), "fdsf", date, date);
	//	fail("Not yet implemented");
	}

}
