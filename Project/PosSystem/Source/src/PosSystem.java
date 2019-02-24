import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PosSystem extends JFrame  {

	private JPanel contentPane;
	private CardLayout cl_tablePanel = new CardLayout(0, 0);
	
	private JTable tableOrdered; //주문내역이 표시되는 표
	private JTable tableMaterial; //재고의 재료명과 재료량이 표시되는 표
	private JTable tableSalesCheck; // 구간별 매출이 표시되는 표
	private JTable tableRecipt; // 영수증 내용이 표시되는 표
	private JTable tableYear; // 월별매출이 표시되는 표
	private JTable tableRecipe; //레시피 정보가 표시되는 표
	
	/* 첫 번째 탭 ----------------------------------------------------------------------------------------------*/
	/* 첫 번째 탭의 패널들 */
	JPanel tablePanel; //테이블 패널
	JPanel tableMainPanel; //테이블 메인 패널
	JPanel tableDetailPanel; //테이블 주문내역 패널
	JPanel jTablePanel; //테이블 주문내역 패널의 표가 담긴 패널
	JPanel menuAddPanel; //테이블 주문내역의 메뉴 추가 패널
	JPanel priceDetailPanel;//테이블 주문내역의 총주문금액, 받은금액, 거스름돈 이 표시되는 패널
	JPanel paymentPanel;//결제종류의 버튼이 있는 패널.
	
	/* 첫 번째 탭의 버튼들 */
	JButton tableDetailButton1; //1번 테이블
	JButton tableDetailButton2; //2번 테이블
	JButton tableDetailButton3; //3번 테이블
	JButton tableDetailButton4; //4번 테이블
	JButton menuButton1; //메뉴 추가 버튼. 짬뽕.
	JButton menuButton2; //메뉴 추가 버튼. 짜장면.
	JButton menuButton3; //메뉴 추가 버튼. 탕수육.
	JButton menuButton4; //메뉴 추가 버튼.볶음짬뽕
	JButton cashButton;//현금결제 버튼.
	JButton creditCardButton;//신용카드 결제 버튼.
	JButton backToTableMainPanelButton; //테이블 메인화면으로 돌아가는 뒤로가기 버튼.
	
	/* 첫 번째 탭의 텍스트영역 */
	JTextPane totalOrderPriceTextPane; //총 주문금액이 보여지는 텍스트구역
	JTextField getPriceTextField;//받은금액 입력 텍스트필드
	JTextPane changeTextPane; //거스름돈이 보여지는 텍스트구역
	
	
	/* 두번째 탭 ----------------------------------------------------------------------------------------------*/
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


	

	/* 세번째 탭 ----------------------------------------------------------------------------------------------*/
	/* 세번째 탭의 패널들*/
	JPanel salesCheckPanel;//매출조회.
	JPanel buttonPanel;//월별매출통계와 영수증조회버튼이 담겨있는 패널
	JPanel todaySalesCheckpanel; //당일 매출을 보여주는 레이블이 담긴 패널.
	JPanel sectionPanel; //카드들이 올라가는 담긴 패널.
	JPanel sectionPaymentPanel; //구간별매출조회 패널. 메인패널.
	JPanel saleCheckTablePanel; //구간별 매출의 표가 담기는 패널.
	JPanel receiptAccessPanel; //영수증 조회 패널.
	JPanel reciptTablePanel; //영수증의 표가 담기는 패널.
	JPanel monthlyPaymentAccessPanel; //월별매출통계 패널
	JPanel yearStaticsPanel; //월별매출통계 표가 담기는 패널.
	
	/* 세 번째 탭의 버튼들 */
	JButton monthlyButton; //월별매출통계 버튼.
	JButton receiptButton; //영수증 조회 버튼.
	
	/* 세 번째 탭의 텍스트영역들 */
	JTextArea creditCardPaymentTextArea; //카드매출텍스트영역
	JTextArea cashPaymentTextArea; //현금매출 텍스트영역
	JTextArea totalPaymentTextArea; //총매출금액 텍스트영역
	
	
	
	/* 네 번째 탭 ----------------------------------------------------------------------------------------------*/
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
	
	



	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PosSystem frame = new PosSystem();
					frame.setTitle("음식점 POS System");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PosSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel presentsClockLabel = new JLabel("현재 시각 : " );
		presentsClockLabel.setBounds(438, 6, 61, 16);
		contentPane.add(presentsClockLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); //탭의 기반이 되는 패널
		tabbedPane.setBounds(6, 40, 564, 356);
		contentPane.add(tabbedPane);
		
		

			//첫번째 탭 ----------------------------------------------------------------------------------------------
				TablePanel tablePanel = new TablePanel();
				tabbedPane.addTab("테이블", null, tablePanel, null);
				tablePanel.setLayout(cl_tablePanel);
						
						
				//두번째 탭 ----------------------------------------------------------------------------------------------
				MaterialManagementPanel materialManagementPanel = new MaterialManagementPanel();
				tabbedPane.addTab("재고관리", null, materialManagementPanel, null);
				materialManagementPanel.setLayout(null);
				// 세 번째 탭	 ----------------------------------------------------------------------------------------------
				SalesCheckPanel salesCheckPanel = new SalesCheckPanel();
				tabbedPane.addTab("매출 통계", null, salesCheckPanel, null);
				salesCheckPanel.setLayout(null);
				
				//네번째 탭 ----------------------------------------------------------------------------------------------
				RecipePanel recipePanel = new RecipePanel();
				tabbedPane.addTab("레시피 관리", null, recipePanel, null);
				recipePanel.setLayout(null);
	
	}				
}



