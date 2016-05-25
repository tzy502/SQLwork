package cn.edu.zucc.personplan.control.example;

import cn.edu.zucc.personplan.util.BusinessException;

import java.sql.Connection;
import java.sql.SQLException;

import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;

public class ExampleUserManager implements IUserManager {
	public static BeanUser currentUser=null;
	@Override
	public BeanUser reg(String userid,String username ,String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		if(userid==null || "".equals(userid) || userid.length()>20){
			throw new BusinessException("登陆账号必须是1-20个字");
		}
		if(pwd.equals(pwd2)==false){
			throw new BusinessException("两次密码不相同");	
		}
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from planuser where userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("登陆账号已经存在");
			rs.close();
			pst.close();
			sql="insert into planuser(userid,password,username) values(?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			pst.setString(3, username);
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
	public BeanUser login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT password,username FROM planuser where userid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不 存在");
			BeanUser u=new BeanUser();
			u.setUserId(userid);
			u.setPassword(rs.getString(1));
			u.setUserName(rs.getString(2));
			rs.close();
			pst.close();
			return u;
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
	public void changePwd(BeanUser user, String oldPwd,String newPwd, String newPwd2)throws BaseException, SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;

		try {
			conn=DBUtil.getConnection();
			String sql="SELECT userid,password,username FROM planuser where userid=? and password=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getUserId());
			pst.setString(2,oldPwd);
			java.sql.ResultSet rs=pst.executeQuery();
			
			
			
			if(!rs.next()) 
				throw new BusinessException("登陆账号不 存在");

			if(newPwd.equals(newPwd2)){
				System.out.println("123");
				sql="update [planuser] set password=? where userid=?";
				pst=conn.prepareStatement(sql);	
				pst.setString(1, newPwd);
				pst.setString(2, user.getUserId());
				pst.execute();
				pst.close();
			}

			else
				throw new BusinessException("前后两次密码不一样");	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(e);
		}

	}
}
