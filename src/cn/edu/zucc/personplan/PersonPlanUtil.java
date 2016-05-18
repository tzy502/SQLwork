package cn.edu.zucc.personplan;

import cn.edu.zucc.personplan.control.example.ExamplePlanManager;
import cn.edu.zucc.personplan.control.example.ExampleStepManager;
import cn.edu.zucc.personplan.control.example.ExampleUserManager;
import cn.edu.zucc.personplan.itf.IPlanManager;
import cn.edu.zucc.personplan.itf.IStepManager;
import cn.edu.zucc.personplan.itf.IUserManager;

public class PersonPlanUtil {
	public static IPlanManager planManager=new ExamplePlanManager();//需要换成自行设计的实现类
	public static IStepManager stepManager=new ExampleStepManager();//需要换成自行设计的实现类
	public static IUserManager userManager=new ExampleUserManager();//需要换成自行设计的实现类
	
}
