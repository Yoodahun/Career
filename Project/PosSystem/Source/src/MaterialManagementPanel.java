import java.awt.BorderLayout;
import java.awt.Dimension;
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

public class MaterialManagementPanel extends JPanel {
	
	private JTable tableOrdered; //주문내역이 표시되는 표
	private JTable tableMaterial; //재고의 재료명과 재료량이 표시되는 표
	private JTable tableSalesCheck; // 구간별 매출이 표시되는 표
	private JTable tableRecipt; // 영수증 내용이 표시되는 표
	private JTable tableYear; // 월별매출이 표시되는 표
	private JTable tableRecipe; //레시피 정보가 표시되는 표
	
	/*두번째 탭의 버튼들 */
	JButton materialAddButton; //재료 추가버튼.
	JButton materialModifyButton;//재료 수정버튼.
	JButton materialDeleteButton;//재료 삭제버튼.
	
	/* 두번째 탭의 패널들 */
	JPanel materialManagementPanel; //두번째 탭. 재고관리.
	JPanel materialPanel; //재고 표가 담긴 패널
	JPanel materialInformationPanel;// 재료명, 재료량의 레이블과 텍스트필드가 들어가는 패널.
	JPanel managePanel;//재료 추가, 수정, 삭제 버튼이 달린 패널.
	
	/*두번째 탭의 텍스트 필드*/
	 JTextField materialNameTextField;
	 JTextField materialQuantityTextField;
	 
	 DefaultTableModel materialModel;
	 
	 Material material = new Material();


	/**
	 * Create the panel.
	 */
	public MaterialManagementPanel() {
		
		
		
		setLayout(null); //Absolute Layout.

			materialPanel = new JPanel(); //재고의 정보가 표시되는 표가 붙는 패널
			materialPanel.setBounds(6, 6, 269, 287);
			add(materialPanel);
			
//				String[] materialColNames = { "재료명", "재료량" }; //재고의 컬럼헤드
//				String[][] materialRowData = { }; //열의 정보. 이것은 예시입니다.
//				materialModel = new DefaultTableModel(materialColNames, 0); //표의 기본값 설정
	
		
				tableMaterial = new JTable(material.materialAccess()); //표에 기본값을 생성자값으로 넘겨줍니다.
				tableMaterial.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int i = tableMaterial.getSelectedRow();
						System.out.println(i);
						
						materialNameTextField.setText(material.materialAccess().getValueAt(i,0).toString());
						materialQuantityTextField.setText(material.materialAccess().getValueAt(i, 1).toString());
						
					}
				});
				JScrollPane materialScroll = new JScrollPane(tableMaterial); //표에 스크롤달아주기
				materialScroll.setPreferredSize(new Dimension(265, 285)); //스크롤(표)의 크기설정. 패널값보다 조금 작게 해주었습니다.
		
				materialPanel.add(materialScroll); //패널에 붙이기
				
			materialInformationPanel = new JPanel(); //재료명과 재료량에 관한 레이블과 텍스트영역이 붙는 패널.
			materialInformationPanel.setBounds(287, 6, 250, 97);
			add(materialInformationPanel);
			materialInformationPanel.setLayout(null);// Absolute Layout
			
				JLabel materialNameLabel = new JLabel("재료명"); //레이블
				materialNameLabel.setBounds(6, 17, 61, 16);
				materialInformationPanel.add(materialNameLabel);
				
				JLabel materialQuantityLabel = new JLabel("재료량");
				materialQuantityLabel.setBounds(6, 50, 61, 16);
				materialInformationPanel.add(materialQuantityLabel);
				
				materialNameTextField = new JTextField(); //재료명 텍스트필드
				materialNameTextField.setBounds(102, 12, 130, 26);
				materialInformationPanel.add(materialNameTextField);
				materialNameTextField.setColumns(10);
				
				materialQuantityTextField = new JTextField(); //재료량 텍스트필드
				materialQuantityTextField.setColumns(10);
				materialQuantityTextField.setBounds(102, 45, 130, 26);
				materialInformationPanel.add(materialQuantityTextField);
				
			managePanel = new JPanel(); //재료 등록, 수정, 삭제가 달리는 패널
			managePanel.setBounds(287, 165, 250, 106);
			add(managePanel);
			managePanel.setLayout(new BorderLayout(0, 0)); //BorderLayout
			
				materialAddButton = new JButton("추가"); //추가버튼
				materialAddButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						material.materialAdd(materialNameTextField.getText(), materialQuantityTextField.getText());
						materialNameTextField.setText("");
						materialQuantityTextField.setText("");
						
						
					}
				});
				managePanel.add(materialAddButton, BorderLayout.WEST);
				
				materialModifyButton = new JButton("수정"); //수정 버튼
				materialModifyButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int index = tableMaterial.getSelectedRow();
						material.materialModify(materialNameTextField.getText(),materialQuantityTextField.getText()
								, index, 1);
						material.materialAccess().setValueAt(materialQuantityTextField.getText(), index, 1);
						
							
						
					}
				});
				
				managePanel.add(materialModifyButton, BorderLayout.CENTER);
				
				materialDeleteButton = new JButton("삭제"); //삭제 버튼
				materialDeleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (tableMaterial.getSelectedRow() == -1) {
							return ;
						} else {
							int index = tableMaterial.getSelectedRow();
							
							material.materialDelete(material.materialAccess().getValueAt(index, 0).toString());
							material.materialAccess().removeRow(index);
							materialNameTextField.setText("");
							materialQuantityTextField.setText("");
							
						}
					}
				});
				managePanel.add(materialDeleteButton, BorderLayout.EAST);

	}

}
