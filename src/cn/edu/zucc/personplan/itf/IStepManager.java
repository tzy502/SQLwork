package cn.edu.zucc.personplan.itf;

import java.util.Date;
import java.util.List;

import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.util.BaseException;

public interface IStepManager {
	/**
	 * 添加步骤
	 * 
	 * @param plan
	 * @param name
	 * @param planstartdate
	 * @param planfinishdate
	 * @throws BaseException
	 */
	public void add(BeanPlan plan, String name, Date planstartdate, Date planfinishdate)throws BaseException;

	public List<BeanStep> loadSteps(BeanPlan plan)throws BaseException;

	public void deleteStep(BeanStep step)throws BaseException;

	public void startStep(BeanStep step)throws BaseException;

	public void finishStep(BeanStep step)throws BaseException;

	public void modifyStep(BeanStep step)throws BaseException;
	
	public BeanStep SearchStep(int Id)throws BaseException;
	
	public List<BeanStep> SearchStep2(int planid)throws BaseException;
}

