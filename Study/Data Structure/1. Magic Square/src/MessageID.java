
public enum MessageID { //메세지 종류를 가진 enum
	Notice_BeginMagicSquare, //게임을 처음 시작할 때.
	Notice_EndMagicSquare, //게임이 끝났을 때.
	
	Error_OrderIsTooSmall, // 차수가 너무 작을 때.
	Error_OrderIsTooLarge, // 차수가 너무 클 때.
	Error_OrderIsNotOddNumber; // 차수가 홀수가 아닐 때.
}
