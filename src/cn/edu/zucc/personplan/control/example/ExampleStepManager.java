package cn.edu.zucc.personplan.control.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.personplan.itf.IStepManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;
import java.sql.Timestamp;

public class ExampleStepManager implements IStepManager {

	@Override
	public void add(BeanPlan plan, String name, Date planstartdate,Date planfinishdate) throws BaseException {
		BeanStep step=new BeanStep();
		step.setStepName(name);
		step.setPBeginTime(planstartdate);
		step.setPEndTime(planfinishdate);
		step.setIsEnd(false);
		Connection conn=null;
		try {
			int totalnum=0;
			conn=DBUtil.getConnection();
			ExamplePlanManager a=new ExamplePlanManager();
			String sql="select max([stepid]) from [step]";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				totalnum=rs.getInt(1)+1;
			}

			rs.close();
			pst.close();
			sql="INSERT INTO [SQLwork].[dbo].[step]"
				+ " ([stepid],[stepname],[Pbegintime],[Pendtime],[Abegintime],[Aendtime],[isend],[planid]) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
		//	rs=pst.executeQuery();
			pst.setInt(1, totalnum);
			pst.setString(2,name);
			pst.setTimestamp(3, new Timestamp(planstartdate.getTime()));
			pst.setTimestamp(4, new Timestamp(planfinishdate.getTime()));
			pst.setTimestamp(5, null);
			pst.setTimestamp(6, null);
			pst.setInt(7, 0);
			pst.setInt(8, plan.getPlanId());

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

	@Override
	public List<BeanStep> loadSteps(BeanPlan plan) throws BaseException {
		List<BeanStep> result=new ArrayList<BeanStep>();
		
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT [stepid],[stepname],[Pbegintime],[Pendtime],[Abegintime],[Aendtime],[isend],[planid]FROM [SQLwork].[dbo].[step] where planid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,plan.getPlanId());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanStep p=new BeanStep();
				p.setStepId(rs.getInt(1));
				p.setStepName(rs.getString(2));
				p.setPBeginTime((Date)rs.getTimestamp(3));
				p.setPEndTime((Date)rs.getTimestamp(4));
				p.setABeginTime((Date)rs.getTimestamp(5));
				p.setAEndtime((Date)rs.getTimestamp(6));
				int isend=rs.getInt(7);
				if(isend==1)
					p.setIsEnd(true);
				else
					p.setIsEnd(false);
				p.setPlanId(rs.getInt(8));
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
	public void deleteStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE FROM [SQLwork].[dbo].[step] "
					+ "WHERE stepid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, step.getStepId());
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

	@Override
	public void startStep(BeanStep step) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE [SQLwork].[dbo].[step]"
					+ "   SET [Abegintime] = ? "
					+ "WHERE planid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			Date date=new Date();
			pst.setTimestamp(1, new Timestamp(date.getTime()));
			pst.setInt(2, step.getPlanId());
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

	@Override
	public void finishStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE [SQLwork].[dbo].[step]"
					+ "   SET [Aendtime] = ? "
					+ "WHERE planid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			Date date=new Date();
			pst.setTimestamp(1, new Timestamp(date.getTime()));
			pst.setInt(2, step.getPlanId());
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

	@Override
	public void modifyStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
//		Connection conn=null;
//		try {
//			conn=DBUtil.getConnection();
//			if(SearchPlan(step.getPlanId())!=null){
//				String sql="UPDATE [SQLwork].[dbo].[Plan]   SET       [planname] =?      ,[stepnum] = ?      ,[Completenum] = ? WHERE planid=?";
//				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
//				pst.setString(1, newplan.getPlanName());
//				pst.setInt(2, newplan.getStepNum());
//				pst.setInt(3, newplan.getCompleteNum());
//				pst.setInt(4, step.getPlanId());
//				pst.execute();
//				pst.close();
//
//			}
//
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DbException(e);
//		}
//		finally{
//			if(conn!=null)
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//
//		
//		
	}

}
