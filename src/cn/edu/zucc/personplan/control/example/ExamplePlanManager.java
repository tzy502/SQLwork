package cn.edu.zucc.personplan.control.example;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.personplan.itf.IPlanManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.util.BaseException;

public class ExamplePlanManager implements IPlanManager {

	@Override
	public BeanPlan addPlan(String name) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BeanPlan> loadAll() throws BaseException {
		List<BeanPlan> result=new ArrayList<BeanPlan>();
		BeanPlan p=new BeanPlan();
		result.add(p);
		return result;
	}

	@Override
	public void deletePlan(BeanPlan plan) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BeanPlan SearchPlan(int PlanId) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanPlan SearchPlan2(String name) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyPlan(BeanPlan plan) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
