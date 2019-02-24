
public class Payment {
	
	private int receiptNumber;
	private int paymentType;
	private int paymentState;
	private int paymentDate;
	private int paymentPrice;
	
	Object saleAccess(int paymentDate) {
		return null;
		//결제일자, 영수증번호, 결제방식, 결제금액
		
	}
	
	Object monthlySaleAccess(int paymentDate){
		return null;
		//결제일자, 결제방식, 결제금액
	
	}
	
	Object receiptAccess(int paymentDate){
		return null;
		//영수증번호, 결제방식, 결제상태, 결제금액
		
	}
	
	void receiptPrint(int receiptNumber){
		
	}

}
