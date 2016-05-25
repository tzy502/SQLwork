package cn.edu.zucc.personplan.model;

public class BeanPlan {
	public static final String[] tableTitles={"编号","名称","步骤数","已完成数"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	private int PlanId;
	private String PlanName;
	private int StepNum;
	private int CompleteNum;
	
	public String getCell(int col){
		if(col==0) return "1";
		else if(col==1) return "示例计划";
		else if(col==2) return "2";
		else if(col==3) return "1";
		else return "";
	}

	public int getPlanId() {
		return PlanId;
	}

	public void setPlanId(int planId) {
		PlanId = planId;
	}

	public String getPlanName() {
		return PlanName;
	}

	public void setPlanName(String planName) {
		PlanName = planName;
	}

	public int getStepNum() {
		return StepNum;
	}

	public void setStepNum(int stepNum) {
		StepNum = stepNum;
	}

	public int getCompleteNum() {
		return CompleteNum;
	}

	public void setCompleteNum(int completeNum) {
		CompleteNum = completeNum;
	}
	
	
}
