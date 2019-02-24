import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class SalesCheckPanel extends JPanel {
	
	private JTable tableOrdered; //주문내역이 표시되는 표
	private JTable tableMaterial; //재고의 재료명과 재료량이 표시되는 표
	private JTable tableSalesCheck; // 구간별 매출이 표시되는 표
	private JTable tableRecipt; // 영수증 내용이 표시되는 표
	private JTable tableYear; // 월별매출이 표시되는 표
	private JTable tableRecipe; //레시피 정보가 표시되는 표
	
	
	
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
	
	/**
	 * Create the panel.
	 */
	public SalesCheckPanel() {
		
		
		
			setLayout(null); //Absolute Layout.
		
			buttonPanel = new JPanel(); //월별 매출통계와 영수증조회 버튼이 달리는 패널.
			buttonPanel.setBounds(6, 6, 234, 91);
			add(buttonPanel);
			buttonPanel.setLayout(new BorderLayout(0, 0)); //BorderLayout.
			
				monthlyButton = new JButton("월별 매출 통계"); //월별 매출 통계 버튼
				monthlyButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						sectionPanel.removeAll();
						sectionPanel.add(monthlyPaymentAccessPanel); //월별 매출 통계 패널로 넘어갑니다.
						sectionPanel.repaint();
						sectionPanel.revalidate();
						
					}
				});
				buttonPanel.add(monthlyButton, BorderLayout.WEST); //버튼 붙이기
				
				receiptButton = new JButton("영수증 조회"); //영수증 조회 버튼
				receiptButton.addActionListener(new ActionListener() { 
					public void actionPerformed(ActionEvent e) {
						sectionPanel.removeAll();
						sectionPanel.add(receiptAccessPanel); // 영수증 조회 패널로 넘어갑니다.
						sectionPanel.repaint();
						sectionPanel.revalidate();
					}
				});
				buttonPanel.add(receiptButton, BorderLayout.EAST);
			
			todaySalesCheckpanel = new JPanel(); //당일매출조회 패널
			todaySalesCheckpanel.setBounds(252, 6, 274, 91);
			add(todaySalesCheckpanel);
			todaySalesCheckpanel.setLayout(null); //Absolute Layout.
			
				JLabel creditCardLabel = new JLabel("신용카드"); //라벨들
				creditCardLabel.setBounds(6, 6, 61, 16);
				todaySalesCheckpanel.add(creditCardLabel);
				
				JLabel cashLabel = new JLabel("현금결제");
				cashLabel.setBounds(6, 34, 61, 16);
				todaySalesCheckpanel.add(cashLabel);
				
				JLabel totalLabel = new JLabel("총 매출액");
				totalLabel.setBounds(6, 62, 61, 16);
				todaySalesCheckpanel.add(totalLabel);
				
				creditCardPaymentTextArea = new JTextArea(); //신용카드 매출이 표시되는 텍스트영역
				creditCardPaymentTextArea.setBounds(105, 6, 140, 16);
				todaySalesCheckpanel.add(creditCardPaymentTextArea);
				
				cashPaymentTextArea = new JTextArea(); //현금 매출이 표시되는 영역
				cashPaymentTextArea.setBounds(105, 34, 140, 16);
				todaySalesCheckpanel.add(cashPaymentTextArea);
				
				totalPaymentTextArea = new JTextArea(); //총 매출액이 표시되는 영역
				totalPaymentTextArea.setBounds(105, 62, 140, 16);
				todaySalesCheckpanel.add(totalPaymentTextArea);
				
				//카드패널
				sectionPanel = new JPanel();
				sectionPanel.setBounds(6, 109, 531, 195);
				add(sectionPanel);
				sectionPanel.setLayout(new CardLayout(0, 0));//Card Layout
				
				
					//첫번째 메인카드, 구간별 매출조회. 맨 처음으로 보여지는 화면입니다.
					sectionPaymentPanel = new JPanel();
					sectionPanel.add(sectionPaymentPanel, "name_24595201015109");
					sectionPaymentPanel.setLayout(null);
						//콤보박스들
					
						
						String[] month = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}; //12달
						
					
						JComboBox yearComboBox1 = new JComboBox(); //년
						yearComboBox1.setBounds(6, 6, 68, 27);
						sectionPaymentPanel.add(yearComboBox1);
						
						JComboBox monthComboBox1 = new JComboBox(month); //월이 표시되는 콤보박스 생성자 초기값으로 month를 넣습니다. 이것은 예시.
						monthComboBox1.setBounds(86, 6, 68, 27);
						sectionPaymentPanel.add(monthComboBox1);
						
						JComboBox dayComboBox1 = new JComboBox(); //일
						dayComboBox1.setBounds(166, 6, 68, 27);
						sectionPaymentPanel.add(dayComboBox1);
						
						JLabel lblNewLabel_2 = new JLabel("~"); //라벨
						lblNewLabel_2.setBounds(257, 10, 61, 16);
						sectionPaymentPanel.add(lblNewLabel_2);
						
						JComboBox yearComboBox2 = new JComboBox();
						yearComboBox2.setBounds(283, 6, 68, 27);
						sectionPaymentPanel.add(yearComboBox2);
						
						JComboBox monthComboBox2 = new JComboBox(month);
						monthComboBox2.setBounds(363, 6, 68, 27);
						sectionPaymentPanel.add(monthComboBox2);
						
						JComboBox dayComboBox2 = new JComboBox();
						dayComboBox2.setBounds(443, 6, 68, 27);
						sectionPaymentPanel.add(dayComboBox2);
						
						
						saleCheckTablePanel = new JPanel(); //구간별 매출 통계가 보여지는 표가 담긴 패널
						saleCheckTablePanel.setBounds(16, 45, 495, 144);
						sectionPaymentPanel.add(saleCheckTablePanel);
						
							String[] salesColNames = {"결제일자", "결제방식", "영수증 번호", "결제금액" }; //컬럼헤드
							String[][] salseRowData = { { "2016-11-26","신용카드", "00001", "12000" }, {"2016-11-23","현금", "00001", "9000"} }; //데이터값. 이것은 예시입니다.
							DefaultTableModel salesModel = new DefaultTableModel(salseRowData, salesColNames); //초기값 설정
							
					
							tableSalesCheck = new JTable(salesModel); //표의 초기 생성자값으로 넣어줍니다.
							JScrollPane	salesScroll = new JScrollPane(tableSalesCheck); //표에 스크롤 달기.
							salesScroll.setPreferredSize(new Dimension(490, 140)); //크기 설정.
					
							saleCheckTablePanel.add(salesScroll); // 붙이기.
					
							
					//두번째 카드. 영수증조회 패널
					receiptAccessPanel = new JPanel();
					sectionPanel.add(receiptAccessPanel, "name_26518666945853"); //패널의 이름은 임의로 붙습니다.
					receiptAccessPanel.setLayout(null); //Absolute Layout
					
						JComboBox receiptYearComboBox = new JComboBox(); //년
						receiptYearComboBox.setBounds(6, 6, 67, 27);
						receiptAccessPanel.add(receiptYearComboBox);
						
						JComboBox receiptMonthComboBox = new JComboBox(); //월
						receiptMonthComboBox.setBounds(85, 6, 67, 27);
						receiptAccessPanel.add(receiptMonthComboBox);
						
						JComboBox receiptDayComboBox = new JComboBox(); //일
						receiptDayComboBox.setBounds(164, 6, 67, 27);
						receiptAccessPanel.add(receiptDayComboBox);
						
						
						JPanel receiptTablePanel = new JPanel(); //영수증의 간략정보가 표시되는 표가 담기는 패널
						receiptTablePanel.setBounds(16, 45, 214, 144);
						receiptAccessPanel.add(receiptTablePanel);
						

							String[] reciptColNames = {" ", "영수증 번호", "금액" }; //컬럼헤드
							String[][] reciptRowData = { { "1","0001", "12000" }, {"2","00002", "9000"} }; //열의 정보. 이것은 예시입니다.
							DefaultTableModel reciptModel = new DefaultTableModel(reciptRowData, reciptColNames); //초기값
						
							tableRecipt= new JTable(reciptModel); //생성자에 초기값 집어넣어주기.
							JScrollPane	reciptScroll = new JScrollPane(tableRecipt); //스크롤 달기.
							reciptScroll.setPreferredSize(new Dimension(210, 140)); //스크롤(표)의 크기 설정.
					
							receiptTablePanel.add(reciptScroll); //붙이기.
							
							
					//세번째 카드. 월별매출통계 패널
					monthlyPaymentAccessPanel = new JPanel();
					sectionPanel.add(monthlyPaymentAccessPanel, "name_26986463812171"); //이름은 임의로 붙여집니다.
					monthlyPaymentAccessPanel.setLayout(null); //Absolute Layout
							
						JComboBox monthlyPaymentComboBox = new JComboBox(); //년
						monthlyPaymentComboBox.setBounds(6, 6, 69, 27);
						monthlyPaymentAccessPanel.add(monthlyPaymentComboBox);
						
						yearStaticsPanel = new JPanel();
						yearStaticsPanel.setBounds(0, 37, 519, 152); //월별 매출 통계의 표가 붙는 패널.
						monthlyPaymentAccessPanel.add(yearStaticsPanel);
						

							String[] yearColNames = {"월", "카드결제 건수", "현금결제 건수","매출액"}; //컬럼
							String[][] yeartRowData = { { "1","50", "12","600000" }, {"2","40","10", "510000"} }; //데이터. 이것은 예시입니다.
							DefaultTableModel yearModel = new DefaultTableModel(yeartRowData, yearColNames); //초기값
					
							tableYear= new JTable(yearModel); //표에 생성자값에 초기값 넣어주기.
							JScrollPane	yearScroll = new JScrollPane(tableYear); //스크롤 달아주기.
							yearScroll.setPreferredSize(new Dimension(515, 150)); //스크롤(표)의 크기 설정
					
							yearStaticsPanel.add(yearScroll); //

	}

}
