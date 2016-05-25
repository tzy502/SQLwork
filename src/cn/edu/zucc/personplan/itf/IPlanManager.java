package cn.edu.zucc.personplan.itf;

import java.util.List;

import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.util.BaseException;

public interface IPlanManager {
	/**
	 * 添加计划
	 * @param name  计划名称
	 * @throws BaseException
	 */
	public BeanPlan addPlan(String name,int Stepnum) throws BaseException;
	/**
	 * 提取所有计划
	 * @return
	 * @throws BaseException
	 */
	public List<BeanPlan> loadAll()throws BaseException;
	/**
	 * 删除计划，如果计划下存在步骤，则不允许删除
	 * @param plan
	 * @throws BaseException
	 */
	public void deletePlan(BeanPlan plan)throws BaseException;
	/**
	 * 精确查找计划
	 * @param plan
	 * @return
	 * @throws BaseException
	 */	
	public BeanPlan SearchPlan(int PlanId)throws BaseException;
	/**
	 * 模糊查找计划
	 * @param plan
	 * @return
	 * @throws BaseException
	 */		
	public BeanPlan SearchPlan2(String name) throws BaseException;
	/**
	 *修改计划
	 * @param plan
	 * @throws BaseException
	 */		
	public void modifyPlan(BeanPlan plan)throws BaseException;
}
