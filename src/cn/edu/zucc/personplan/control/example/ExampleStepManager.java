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
			totalstep(plan);
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
	public void startStep(BeanStep step) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE [SQLwork].[dbo].[step]"
					+ "   SET [Abegintime] = ? "
					+ "WHERE stepid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			Date date=new Date();
			pst.setTimestamp(1, new Timestamp(date.getTime()));
			pst.setInt(2, step.getStepId());
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
	public void finishStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE [SQLwork].[dbo].[step]"
					+ "   SET [Aendtime] = ? ,isend=?"
					+ "WHERE stepid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			Date date=new Date();
			pst.setTimestamp(1, new Timestamp(date.getTime()));
			pst.setInt(2, 1);
			pst.setInt(3, step.getStepId());
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

	public BeanStep SearchStep(int Id)throws BaseException {
		BeanStep Step =new BeanStep();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT [stepid]      ,[stepname]      ,[Pbegintime]      ,[Pendtime]      ,[Abegintime]      ,[Aendtime]      ,[isend]      ,[planid]"
					+"  FROM [SQLwork].[dbo].[step]"
					+ "WHERE stepid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, Id);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Step.setPlanId(rs.getInt(1));
				Step.setStepName(rs.getString(2));
				Timestamp Pbegintime=rs.getTimestamp(3);
				java.util.Date PBegin=new java.util.Date(Pbegintime.getTime());
				Step.setPBeginTime(PBegin);
				Timestamp Pendtime=rs.getTimestamp(4);
				java.util.Date PEnd=new java.util.Date(Pendtime.getTime());
				Step.setPEndTime(PEnd);
				Timestamp Abegintime=rs.getTimestamp(5);
				java.util.Date Abegin=new java.util.Date(Abegintime.getTime());
				Step.setABeginTime(Abegin);
				Timestamp Aendtime=rs.getTimestamp(6);
				java.util.Date Aend=new java.util.Date(Aendtime.getTime());
				Step.setAEndtime(Aend);
				int isend=rs.getInt(7);
				if (isend==0){
					Step.setIsEnd(false);
				}
				else{
					Step.setIsEnd(true);
				}
				Step.setPlanId(rs.getInt(8));
			}
			rs.close();
			pst.close();
			return Step;
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
	public List<BeanStep> SearchStep2(int PLANId)throws BaseException {
		List<BeanStep> result=new ArrayList<BeanStep>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT [stepid]      ,[stepname]      ,[Pbegintime]      ,[Pendtime]      ,[Abegintime]      ,[Aendtime]      ,[isend]      ,[planid]"
					+"  FROM [SQLwork].[dbo].[step]"
					+ "WHERE planid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, PLANId);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				BeanStep Step =new BeanStep();
				Step.setPlanId(rs.getInt(1));
//				Step.setStepName(rs.getString(2));
//				Timestamp Pbegintime=rs.getTimestamp(3);
//				java.util.Date PBegin=new java.util.Date(Pbegintime.getTime());
//				Step.setPBeginTime(PBegin);
//				Timestamp Pendtime=rs.getTimestamp(4);
//				java.util.Date PEnd=new java.util.Date(Pendtime.getTime());
//				Step.setPEndTime(PEnd);
//				Timestamp Abegintime=rs.getTimestamp(5);
//				java.util.Date Abegin=new java.util.Date(Abegintime.getTime());
//				Step.setABeginTime(Abegin);
//				Timestamp Aendtime=rs.getTimestamp(6);
//				java.util.Date Aend=new java.util.Date(Aendtime.getTime());
//				Step.setAEndtime(Aend);
//				int isend=rs.getInt(7);
//				if (isend==0){
//					Step.setIsEnd(false);
//				}
//				else{
//					Step.setIsEnd(true);
//				}
//				Step.setPlanId(rs.getInt(8));
				result.add(Step);
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
	public void modifyStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			if(SearchStep(step.getPlanId())!=null){
				String sql="UPDATE [SQLwork].[dbo].[step]   SET [stepname] = ?      ,[Pbegintime] = ?      ,[Pendtime] = ?      ,[Abegintime] =?      ,[Aendtime] =?      ,[isend] = ?      ,[planid] = ? WHERE WHERE [stepid] = ?";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				pst.setString(1, step.getStepName());
				pst.setTimestamp(2, new Timestamp(step.getABeginTime().getTime()));
				pst.setTimestamp(3, new Timestamp(step.getAEndtime().getTime()));
				pst.setTimestamp(4, new Timestamp(step.getPBeginTime().getTime()));
				pst.setTimestamp(5, new Timestamp(step.getPEndTime().getTime()));
				if(step.isIsEnd()==true){
					pst.setInt(6, 1);
				}
				else{
					pst.setInt(6, 0);
				}
				pst.setInt(7, step.getPlanId());
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
	public void totalstep(BeanPlan plan) throws DbException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE [SQLwork].[dbo].[Plan]"
					   +" SET stepnum = (select count(*)	from [step]	where planid=?	)"
					   +" WHERE planid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, plan.getPlanId());
			pst.setInt(2, plan.getPlanId());
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
	public void totalendstep(BeanPlan plan) throws DbException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE [SQLwork].[dbo].[Plan]"
					   +"SET Completenum = (select count(*)	from [step]	where planid=? and isend=1	)"
					   +" WHERE planid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, plan.getPlanId());
			pst.setInt(2, plan.getPlanId());
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
	public void ClearStep() throws DbException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE FROM [SQLwork].[dbo].[step]";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
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
