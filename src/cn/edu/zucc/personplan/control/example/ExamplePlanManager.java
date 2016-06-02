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
	public BeanPlan addPlan(String name) throws BaseException {
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
			plan.setStepNum(0);
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
			return result;
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
	}

	@Override
	public void deletePlan(BeanPlan plan) throws BaseException {
		// TODO Auto-generated method stub
		ExampleStepManager Step=new ExampleStepManager();
		if(Step.SearchStep2(plan.getPlanId())==null){
			Connection conn=null;
			try {
				conn=DBUtil.getConnection();
				String sql="DELETE FROM [SQLwork].[dbo].[Plan] "
						+ "WHERE planid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setInt(1, plan.getPlanId());
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
		}
		
			
	}

	@Override
	public BeanPlan SearchPlan(int PlanId) throws BaseException {
		BeanPlan result=new BeanPlan();
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT [planid],[planname],[stepnum],[Completenum]FROM [SQLwork].[dbo].[Plan] "
					+ "WHERE planid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, PlanId);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				
				result.setPlanId(rs.getInt(1));
				result.setPlanName(rs.getString(2));
				result.setStepNum(rs.getInt(3));
				result.setCompleteNum(rs.getInt(4));
			}
			rs.close();
			pst.close();
			return result;
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
	}

	@Override
	public BeanPlan SearchPlan2(String name) throws BaseException {
		BeanPlan result=new BeanPlan();
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT [planid],[planname],[stepnum],[Completenum]FROM [SQLwork].[dbo].[Plan] "
					+ "where [planname]like ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				
				result.setPlanId(rs.getInt(1));
				result.setPlanName(rs.getString(2));
				result.setStepNum(rs.getInt(3));
				result.setCompleteNum(rs.getInt(4));
			}
			rs.close();
			pst.close();
			return result;
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
	}

	@Override
	public void modifyPlan(BeanPlan plan,BeanPlan newplan) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			if(SearchPlan(plan.getPlanId())!=null){
				String sql="UPDATE [SQLwork].[dbo].[Plan]   SET       [planname] =?      ,[stepnum] = ?      ,[Completenum] = ? WHERE planid=?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, newplan.getPlanName());
				pst.setInt(2, newplan.getStepNum());
				pst.setInt(3, newplan.getCompleteNum());
				pst.setInt(4, plan.getPlanId());
				pst.execute();
				pst.close();

			}


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

		
		
		
	}
	public static void main(String[] args) throws BaseException {
		ExamplePlanManager a=new ExamplePlanManager();
	//	a.addPlan("asd", 5);
	}
}
