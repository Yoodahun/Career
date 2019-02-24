import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends JPanel {
	
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

	/**
	 * Create the panel.
	 */
	public TablePanel() {
		
		
			setLayout(cl_tablePanel); //카드레이아웃 적용

			//테이블 패널의 첫번째 카드. 테이블 메인 화면
			tableMainPanel = new JPanel();
			add(tableMainPanel, "name_21586802484896"); //첫번재 카드패널의 이름은 임의로 지정되어버림.
			tableMainPanel.setLayout(null); //Absolute Layout 적용.

				tableDetailButton1 = new JButton("1"); //1번 테이블
				tableDetailButton1.addActionListener(new ActionListener() { //1번 테이블버튼에 액션리스너
					public void actionPerformed(ActionEvent e) { //액션 메소드
						removeAll(); //메인테이블을 다 지우고
						add(tableDetailPanel); //주문내역패널을 추가
						repaint(); //그리기
						revalidate(); //유효하게
					}
				});
				tableDetailButton1.setBounds(31, 43, 163, 80);
				tableMainPanel.add(tableDetailButton1); //1번 테이블을 테이블 메인화면에 추가
		
				tableDetailButton2 = new JButton("2"); 
				tableDetailButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tablePanel.removeAll(); //메인테이블을 다 지우고
						tablePanel.add(tableDetailPanel); //주문내역패널을 추가
						tablePanel.repaint(); //그리기
						tablePanel.revalidate(); //유효하게
					}
				});
				tableDetailButton2.setBounds(313, 43, 163, 80);
				tableMainPanel.add(tableDetailButton2);
		
				tableDetailButton3 = new JButton("3");
				tableDetailButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tablePanel.removeAll(); //메인테이블을 다 지우고
						tablePanel.add(tableDetailPanel); //주문내역패널을 추가
						tablePanel.repaint(); //그리기
						tablePanel.revalidate(); //유효하게
					}
				});
				tableDetailButton3.setBounds(31, 176, 163, 80);
				tableMainPanel.add(tableDetailButton3);
		
				tableDetailButton4 = new JButton("4");
				tableDetailButton4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tablePanel.removeAll(); //메인테이블을 다 지우고
						tablePanel.add(tableDetailPanel); //주문내역패널을 추가
						tablePanel.repaint(); //그리기
						tablePanel.revalidate(); //유효하게
					}
				});
				tableDetailButton4.setBounds(313, 176, 163, 80);
				tableMainPanel.add(tableDetailButton4);

				
			//테이블 패널의 두번째 카드. 주문내역조회.
			tableDetailPanel = new JPanel();
			add(tableDetailPanel, "name_18130680992588"); //주문내역조회 카드의 이름은 임의로 지정되어버림.
			tableDetailPanel.setLayout(null); //Absolute Layout

				jTablePanel = new JPanel(); //주문내역 표가 담기는 패널
				jTablePanel.setBounds(6, 6, 298, 164);
				tableDetailPanel.add(jTablePanel);

					String[] orderColNames = { "번호", "상품명", "금액", "수량" }; //주문내역의 컬럼헤드.
					String[][] orderRowData = { { "1", "Java", "2000", "1" } }; //주문내역의 열. 이것은 예시입니다. 2차원 리스트.
					DefaultTableModel orderModel = new DefaultTableModel(orderRowData, orderColNames); //표의 디폴트 값입니다.
					
					tableOrdered = new JTable(orderModel); //표 생성. 표의 디폴트값을 생성자 초기값으로 넘겨줍니다.
					JScrollPane orderScroll = new JScrollPane(tableOrdered); //표에 스크롤을 달아줘야 제대로 보입니다. 스크롤 달아주는 작업.
					orderScroll.setPreferredSize(new Dimension(295, 160)); //스크롤(표)의 사이즈입니다. 패널보다 조금 작게 값을 주었습니다. x, y값
					jTablePanel.add(orderScroll); // 주문내역표가 담기는 패널에 표 붙이기.
					

				menuAddPanel = new JPanel(); //음식추가하는 버튼이 담긴 패널
				menuAddPanel.setBounds(335, 6, 202, 164);
				tableDetailPanel.add(menuAddPanel);
				menuAddPanel.setLayout(null); //Absolute Layout
			
					menuButton1 = new JButton("짜장면"); //표시하고 싶은 텍스트 값을 초기 생성자값으로 전해줍니다.
					menuButton1.setBounds(0, 6, 65, 54);
					menuAddPanel.add(menuButton1); //패널에 붙이기
			
					menuButton2 = new JButton("짬뽕");
					menuButton2.setBounds(77, 6, 65, 54);
					menuAddPanel.add(menuButton2);
			
					menuButton3 = new JButton("탕수육");
					menuButton3.setBounds(0, 72, 65, 54);
					menuAddPanel.add(menuButton3);
					
					menuButton4 = new JButton("볶음짬뽕");
					menuButton4.setBounds(77, 72, 65, 54);
					menuAddPanel.add(menuButton4);

				priceDetailPanel = new JPanel(); // 총주문금액, 받은금액, 거스름돈의 레이블과 텍스트영역이 붙는 패널.
				priceDetailPanel.setBounds(6, 186, 298, 118);
				tableDetailPanel.add(priceDetailPanel);
				priceDetailPanel.setLayout(null); //Absolute Layout

					JLabel totalOrderPriceLabel = new JLabel("총 주문금액"); //라벨들
					totalOrderPriceLabel.setBounds(29, 20, 61, 16);
					priceDetailPanel.add(totalOrderPriceLabel);
			
					JLabel getPriceLabel = new JLabel("받은 금액");
					getPriceLabel.setBounds(29, 50, 61, 16);
					priceDetailPanel.add(getPriceLabel);
			
					JLabel changeLabel = new JLabel("거스름돈");
					changeLabel.setBounds(29, 81, 61, 16);
					priceDetailPanel.add(changeLabel);
			
					totalOrderPriceTextPane = new JTextPane(); //총주문금액 텍스트영역. textPane을 다른걸로 바꾸셔도 될 듯 합니다..Pane이 뭘 제공하는지 까먹어서 그냥 붙였습니다;
					totalOrderPriceTextPane.setBounds(136, 20, 123, 16);
					priceDetailPanel.add(totalOrderPriceTextPane);
			
					getPriceTextField = new JTextField(); //받은 금액 텍스트영역. 여긴 텍스트필드입니다.
					getPriceTextField.setBounds(136, 45, 123, 26);
					priceDetailPanel.add(getPriceTextField);
					getPriceTextField.setColumns(10);
			
					changeTextPane = new JTextPane(); //거스름돈 텍스트영역.
					changeTextPane.setBounds(136, 81, 123, 16);
					priceDetailPanel.add(changeTextPane);

				paymentPanel = new JPanel(); //결제방법 버튼이 달리는 패널.
				paymentPanel.setBounds(335, 186, 202, 118);
				tableDetailPanel.add(paymentPanel);

					cashButton = new JButton("현금결제"); //현금결제.
					paymentPanel.add(cashButton);
			
					creditCardButton = new JButton("카드 결제"); //카드결제.
					paymentPanel.add(creditCardButton);
					
				
					backToTableMainPanelButton = new JButton("뒤로");	//뒤로버튼
					backToTableMainPanelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							tablePanel.removeAll();//다지우고
							tablePanel.add(tableMainPanel);//메인화면을 다시 붙이고
							tablePanel.repaint(); //그리기
							tablePanel.revalidate();
						}
					});
					paymentPanel.add(backToTableMainPanelButton);

	}

}
