package cn.edu.zucc.personplan.control.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.personplan.itf.IPlanManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

public class ExamplePlanManager implements IPlanManager {

	@Override
	public BeanPlan addPlan(String name,int Stepnum) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			int totalnum=0;
			conn=DBUtil.getConnection();
			String sql="select count(*) from [plan]";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				totalnum=rs.getInt(1)+1;
			}
			rs.close();
			pst.close();
			BeanPlan plan=new BeanPlan();
			plan.setPlanId(totalnum);
			plan.setPlanName(name);
			plan.setStepNum(Stepnum);
			plan.setCompleteNum(0);
			sql="INSERT INTO [SQLwork].[dbo].[Plan]([planid],[planname],[stepnum],[Completenum])VALUES(?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, plan.getPlanId());
			pst.setString(2, plan.getPlanName());
			pst.setInt(3, plan.getStepNum());
			pst.setInt(4, plan.getCompleteNum());
			
			
			pst.execute();
			pst.close();
			
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

	@Override
	public List<BeanPlan> loadAll() throws BaseException {
		List<BeanPlan> result=new ArrayList<BeanPlan>();
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT [planid],[planname],[stepnum],[Completenum]FROM [SQLwork].[dbo].[Plan]";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanPlan p=new BeanPlan();
				p.setPlanId(rs.getInt(1));
				p.setPlanName(rs.getString(2));
				p.setStepNum(rs.getInt(3));
				p.setCompleteNum(rs.getInt(4));
				result.add(p);
			}
			rs.close();
			pst.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
			
		
		
		
		
		
		
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
	public static void main(String[] args) throws BaseException {
		ExamplePlanManager a=new ExamplePlanManager();
	//	a.addPlan("asd", 5);
	}
}
