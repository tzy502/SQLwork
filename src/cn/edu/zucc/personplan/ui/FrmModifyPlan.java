package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.control.example.ExamplePlanManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.util.BaseException;


public class FrmModifyPlan extends JDialog implements ActionListener {
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelName = new JLabel("名称：");	
	private JTextField edtName = new JTextField(20);
	boolean iscreat=false;
	public BeanPlan plan1;
	public void FrmModifyPlan(String s, boolean b,BeanPlan plan) {
		plan1=plan;
		final JFrame JF=new JFrame(s);
		edtName.setText(plan.getPlanName());
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		JF.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		JF.getContentPane().add(workPane, BorderLayout.CENTER);
		JF.setSize(320, 180);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		JF.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		JF.validate();
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				try {
					String name=edtName.getText();
					PersonPlanUtil.planManager.modifyPlan(plan1,name);
					iscreat=true;
					JF.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				JF.setVisible(false);
				return;
			}
		});
		JF.setVisible(b);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){

			try {
				String name=this.edtName.getText();
				PersonPlanUtil.planManager.modifyPlan(plan1,name);
				iscreat=true;
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
	public boolean isture(){
		return iscreat;
	}

	
}
