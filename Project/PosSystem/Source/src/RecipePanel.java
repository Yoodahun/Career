import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class RecipePanel extends JPanel {
	
	private JTable tableOrdered; //주문내역이 표시되는 표
	private JTable tableMaterial; //재고의 재료명과 재료량이 표시되는 표
	private JTable tableSalesCheck; // 구간별 매출이 표시되는 표
	private JTable tableRecipt; // 영수증 내용이 표시되는 표
	private JTable tableYear; // 월별매출이 표시되는 표
	private JTable tableRecipe; //레시피 정보가 표시되는 표

	/* 네 번째 탭의 패널들*/
	JPanel recipePanel; //네 번째 탭. 레시피.
	JPanel recipeMenuButtonPanel; //음식의 버튼이 담긴 패널.
	JPanel recipeTablePanel; //레시피의 정보가 표시되는 표가 담긴 패널.
	JPanel recipeInformationPanel; //레시피에 필요한 재료명과 재료량의 레이블과 텍스트영역이 담긴 패널.
	JPanel recipeManagementPanel; //레시피 추가, 수정, 삭제 버튼이 담긴 패널.
	
	/* 네 번째 탭의 버튼들*/
	JButton recipeMenuButton1;//레시피의 정보가 담긴 버튼. 짜장면.
	JButton recipeMenuButton2;//짬뽕
	JButton recipeMenuButton3;//탕수육
	JButton recipeMenuButton4;  //볶음짬뽕
	JButton recipeAddButton;//레시피 추가
	JButton	recipeModifyButton;//레시피 수정
	JButton recipeDeleteButton;//레시피 삭제
	
	/* 네 번째 탭의 텍스트영역들 */
	JTextField recipeMaterialNameText; //레시피 재료명 텍스트필드
	JTextField recipeMaterialQuantityText; //레시피 재료량 텍스트필드
	
	Recipe button1Recipe = new Recipe();//짜장면
	Recipe button2Recipe = new Recipe();//짬뽕
	Recipe button3Recipe = new Recipe();//탕수육
	Recipe button4Recipe = new Recipe();//볶음짬뽕
	
	
	
	/**
	 * Create the panel.
	 */
	public RecipePanel() {
		
		
		
		setLayout(null); //Absolute Layout
		
		recipeMenuButtonPanel = new JPanel(); //레시피의 정보가 담기는 음식의 버튼이 달리는 패널
		recipeMenuButtonPanel.setBounds(6, 6, 235, 298);
		add(recipeMenuButtonPanel);
		recipeMenuButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5)); //FlowLayout.
		
		String[] recipeColNames = {"재료량", "재료명"}; //컬럼헤드
		String[][] recipeRowData = { }; //데이터. 이것은 예시입니다.
		DefaultTableModel recipeModel = new DefaultTableModel(recipeRowData, recipeColNames); //초기값 설정.
	
		tableRecipe = new JTable(recipeModel); //생성자에 초기값 설정해주기.
		tableRecipe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableRecipe.getSelectedRow();
				if(tableRecipe.getModel() == button1Recipe.recipeAccess()){
					recipeMaterialNameText.setText(button1Recipe.recipeAccess().getValueAt(index, 0).toString());
					recipeMaterialQuantityText.setText(button1Recipe.recipeAccess().getValueAt(index, 1).toString());
				}
				if(tableRecipe.getModel() == button2Recipe.recipeAccess()){
					recipeMaterialNameText.setText(button2Recipe.recipeAccess().getValueAt(index, 0).toString());
					recipeMaterialQuantityText.setText(button2Recipe.recipeAccess().getValueAt(index, 1).toString());
				}
				if(tableRecipe.getModel() == button3Recipe.recipeAccess()){
					recipeMaterialNameText.setText(button3Recipe.recipeAccess().getValueAt(index, 0).toString());
					recipeMaterialQuantityText.setText(button3Recipe.recipeAccess().getValueAt(index, 1).toString());
				}
				if(tableRecipe.getModel() == button4Recipe.recipeAccess()){
					recipeMaterialNameText.setText(button4Recipe.recipeAccess().getValueAt(index, 0).toString());
					recipeMaterialQuantityText.setText(button4Recipe.recipeAccess().getValueAt(index, 1).toString());
				}
			}
		});
	
			recipeMenuButton1 = new JButton("짜장면"); //버튼. 짜장면.
			recipeMenuButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button1Recipe.setFoodName("짜장면");
					tableRecipe.setModel(button1Recipe.recipeAccess());
				}
			});
			recipeMenuButtonPanel.add(recipeMenuButton1);
			
			recipeMenuButton2 = new JButton("짬뽕"); //짬뽕.
			recipeMenuButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button2Recipe.setFoodName("짬뽕");
					tableRecipe.setModel(button2Recipe.recipeAccess());
					
				}
			});
			recipeMenuButtonPanel.add(recipeMenuButton2);
			
			recipeMenuButton3 = new JButton("탕수육"); //탕수육
			recipeMenuButton3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button3Recipe.setFoodName("탕수육");
					tableRecipe.setModel(button3Recipe.recipeAccess());
				}
			});
			recipeMenuButtonPanel.add(recipeMenuButton3);
			
			recipeMenuButton4 = new JButton("볶음짬뽕"); //볶음짬뽕
			recipeMenuButton4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					button4Recipe.setFoodName("볶음짬뽕");
					tableRecipe.setModel(button4Recipe.recipeAccess());
				}
			});
			recipeMenuButtonPanel.add(recipeMenuButton4);
			
			//레시피의 정보를 보여주는 표가 담기 패널
			recipeTablePanel = new JPanel();
			recipeTablePanel.setBounds(253, 6, 284, 167);
			add(recipeTablePanel);
				
				JScrollPane	recipeScroll = new JScrollPane(tableRecipe); //표에 스크롤 달기.
				recipeScroll.setPreferredSize(new Dimension(280, 165)); //스크롤(표)의 크기 설정.
		
				recipeTablePanel.add(recipeScroll); //붙이기
				
				
			//재료명과 재료량 추가패널
			recipeInformationPanel = new JPanel();
			
				recipeInformationPanel.setBounds(253, 179, 284, 51);
				add(recipeInformationPanel);
				recipeInformationPanel.setLayout(null); //Absolute Layout
				
				JLabel recipeMaterialNameLabel = new JLabel("재료명"); //라벨
				recipeMaterialNameLabel.setBounds(6, 6, 61, 16);
				recipeInformationPanel.add(recipeMaterialNameLabel);
				
				JLabel recipeMaterialQuantityLabel = new JLabel("재료량");
				recipeMaterialQuantityLabel.setBounds(6, 29, 61, 16);
				recipeInformationPanel.add(recipeMaterialQuantityLabel);
				
				recipeMaterialNameText = new JTextField(); //레시피 재료명 텍스트필드
				recipeMaterialNameText.setBounds(148, 1, 130, 26);
				recipeInformationPanel.add(recipeMaterialNameText);
				recipeMaterialNameText.setColumns(10);
				
				recipeMaterialQuantityText = new JTextField(); //레시피 재료량 텍스트필드
				recipeMaterialQuantityText.setColumns(10);
				recipeMaterialQuantityText.setBounds(148, 24, 130, 26);
				recipeInformationPanel.add(recipeMaterialQuantityText);
				
			//레시피 추가, 수정, 삭제 버튼이 달리는 패널
			recipeManagementPanel = new JPanel();
			recipeManagementPanel.setBounds(253, 242, 284, 62);
			add(recipeManagementPanel);
			recipeManagementPanel.setLayout(new BorderLayout(0, 0)); //Border Layout
			
			recipeAddButton = new JButton("추가"); //추가 버튼.
			recipeAddButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(tableRecipe.getModel() == button1Recipe.recipeAccess()) {
						button1Recipe.recipeAdd(recipeMaterialNameText.getText(), recipeMaterialQuantityText.getText());
						//tableRecipe.setModel(button1Recipe.recipeTableModel());
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
					}
					if(tableRecipe.getModel() == button2Recipe.recipeAccess()) {
						button2Recipe.recipeAdd(recipeMaterialNameText.getText(), recipeMaterialQuantityText.getText());
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
					}
					if(tableRecipe.getModel() == button3Recipe.recipeAccess()) {
						button3Recipe.recipeAdd(recipeMaterialNameText.getText(), recipeMaterialQuantityText.getText());
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
					}
					if(tableRecipe.getModel() == button4Recipe.recipeAccess()) {
						button4Recipe.recipeAdd(recipeMaterialNameText.getText(), recipeMaterialQuantityText.getText());
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
					}
					
				}
			});
			recipeManagementPanel.add(recipeAddButton, BorderLayout.WEST);
			
			recipeModifyButton = new JButton("수정"); //수 버튼.
			recipeModifyButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = tableRecipe.getSelectedRow();
					if(tableRecipe.getModel() == button1Recipe.recipeAccess()) {
					button1Recipe.recipeModify(recipeMaterialQuantityText.getText(), index, 1);
					recipeMaterialNameText.setText("");
					recipeMaterialQuantityText.setText("");
					
					}
					if(tableRecipe.getModel() == button2Recipe.recipeAccess()) {
						button2Recipe.recipeModify(recipeMaterialQuantityText.getText(), index, 1);
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
						
						}
					if(tableRecipe.getModel() == button3Recipe.recipeAccess()) {
						button3Recipe.recipeModify(recipeMaterialQuantityText.getText(), index, 1);
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
						}
					if(tableRecipe.getModel() == button4Recipe.recipeAccess()) {
						button4Recipe.recipeModify(recipeMaterialQuantityText.getText(), index, 1);
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
						}
					
				}
			});
			recipeManagementPanel.add(recipeModifyButton, BorderLayout.CENTER);
			
			recipeDeleteButton = new JButton("삭제"); //삭제 버튼.
			recipeDeleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = tableRecipe.getSelectedRow();
					if(tableRecipe.getModel() == button1Recipe.recipeAccess()) {
						button1Recipe.recipeDelete(recipeMaterialNameText.getText(), recipeMaterialQuantityText.getText(), index);
						button1Recipe.recipeAccess().removeRow(index);
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
						}
				
					
					if(tableRecipe.getModel() == button2Recipe.recipeAccess()) {
						button2Recipe.recipeDelete(recipeMaterialNameText.getText(), recipeMaterialQuantityText.getText(), index);
						button2Recipe.recipeAccess().removeRow(index);
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
						}
					if(tableRecipe.getModel() == button3Recipe.recipeAccess()) {
						button3Recipe.recipeDelete(recipeMaterialNameText.getText(), recipeMaterialQuantityText.getText(), index);
						button3Recipe.recipeAccess().removeRow(index);
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
						}
					if(tableRecipe.getModel() == button4Recipe.recipeAccess()) {
						button4Recipe.recipeDelete(recipeMaterialNameText.getText(), recipeMaterialQuantityText.getText(), index);
						button4Recipe.recipeAccess().removeRow(index);
						recipeMaterialNameText.setText("");
						recipeMaterialQuantityText.setText("");
						}
				}
				
			});
			recipeManagementPanel.add(recipeDeleteButton, BorderLayout.EAST);
				
		
			
			
	}

}
