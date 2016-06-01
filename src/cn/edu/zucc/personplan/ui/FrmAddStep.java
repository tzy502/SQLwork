package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.eltima.components.ui.DatePicker;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmAddStep extends JDialog implements ActionListener {
	public BeanPlan plan;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelName = new JLabel("计划步骤名称：");
	private JLabel labelPlanStartDate = new JLabel("计划开始日期：");
	private JLabel labelPlanFinishDate = new JLabel("计划完成日期：");
	private static final String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
	private Font font=new Font("Times New Roman", Font.BOLD, 14);
	private JTextField edtPlanStartDate = new JTextField(20);
	private JTextField edtPlanFinishDate = new JTextField(20);
	Date date=new Date();
	DatePicker datepickbegintime;
	private DatePicker datepickendtime;
	private Dimension dimension=new Dimension(227,24);
	private JTextField edtName = new JTextField(20);
	public boolean isadd=false;
	public FrmAddStep(JFrame f, String s, boolean b,BeanPlan plan) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelPlanStartDate);

		datepickbegintime = new DatePicker(date,DefaultFormat,font,dimension);
		datepickbegintime.setBounds(74, 38, 284, 21);
		datepickbegintime.setLocale(Locale.CHINA);
		datepickbegintime.setTimePanleVisible(true);
		workPane.add(datepickbegintime);
		
		
	//	workPane.add(edtPlanStartDate);
		workPane.add(labelPlanFinishDate);
		
		datepickendtime = new DatePicker(date,DefaultFormat,font,dimension);
		datepickendtime.setBounds(74, 68, 284, 21);
		datepickendtime.setLocale(Locale.CHINA);
		datepickendtime.setTimePanleVisible(true);
		workPane.add(datepickendtime);
		
		
	//	workPane.add(edtPlanFinishDate);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(380, 180);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String name=this.edtName.getText();
			
			Date begin=(Date)datepickbegintime.getValue();
			Date end=(Date)datepickendtime.getValue();
			try {
				PersonPlanUtil.stepManager.add(plan,name,begin,end);
				isadd=true;
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
	
}
