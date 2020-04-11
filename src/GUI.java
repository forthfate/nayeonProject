import java.awt.BorderLayout;
import java.awt.List;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI {
	//Chapter.1 MVC(GUI)
	//Chapter.2 FileInputStream
	//Chapter.3 getDirectories, getRegistry
	//Chapter.4 make check tool-button action
	
	private final static int RULE_NO = 0;
	private final static int RULE_NAME = 1;
	private final static int RULE_CHECK = 2;
	static int lognum = 1;
	
	
	public static void main(String[] args) {
		new Frame();
			
	}
	
	private static class Frame {
		Frame(){
			//Frame
			JFrame frame = new JFrame();
			frame.setSize(500,400);
			frame.setTitle("O365_Auto_check");
			frame.setLocationRelativeTo(null);
			frame.setLayout(new BorderLayout());
			
			JPanel northPanel = new JPanel();
			JPanel southPanel = new JPanel();
			JPanel centerPanel = new JPanel();
			
			frame.add(northPanel, BorderLayout.NORTH);
			frame.add(southPanel, BorderLayout.SOUTH);
			frame.add(centerPanel, BorderLayout.CENTER);
			
			
			//Model(Dataを入れる枠を作る)
			DefaultTableModel model = new DefaultTableModel();
			String[] column = {"Number", "RuleName", "Check"};
			model.setColumnIdentifiers(column);
			
			//View（ユーザーに見せるようにする）
			JButton btnRun = new JButton("Run");
			btnRun.addActionListener(e -> {
				System.out.println("Job start!");
				run(model);
				System.out.println("Done!");
				
			});
			
			
			JTable table = new JTable(model);
			southPanel.add(btnRun);
			northPanel.add(new JLabel("It is about your O365"));
			centerPanel.add(new JScrollPane(table));
			
			//setData(Controller)
			setData(model);
			
			//show UI
			frame.setVisible(true);
			
		}
		
		void setData(DefaultTableModel targetModel) {
			DefaultTableModel model = targetModel;
			
			//一個一個入れたくないからString型にして順序に入れている
			Object[] sec1 = {"1.1", "MFA"};
			Object[] sec2 = {"1.2", "about_admin"};
			//Object[] sec3 = {"1.3", "about_sec_admin"};
			//Object[] sec4 = {"1.4", "about_help_admin"};
			
					
			//３
			upload(sec1, model);
			upload(sec2, model);
			
		}
		
		//機能を追加する
		//２
		void upload(Object[] value, DefaultTableModel targetModel) {
			targetModel.addRow(value);

			System.out.println("["+lognum++ +"] logging-> name:"+value[RULE_NO]+", value:" +value[RULE_NAME]+", Data:"+Arrays.asList(value));
		}
		
	}
	
	private static void run(DefaultTableModel targetModel) {
		
		for(int i = 0; i < targetModel.getRowCount(); i++) {
			try{
				targetModel.setValueAt(FileExample.getValue((String)targetModel.getValueAt(i, RULE_NAME)), i, RULE_CHECK);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
