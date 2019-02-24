package binaryTreeTraversals;


public class AppController implements VisitEventForDictionaryByBinarySearchTree<Integer, Integer> {

	private static final int DEFAULT_DATA_SIZE = 10;
	
	
	
	private AppView  _appView;
	private DictionaryByBinarySearchTree<Integer, Integer> _dictionary;
	private int[] _list;

	
	public AppController() {
		this._appView = new AppView();
		
	}
	public void run() {
		this._list = DataGenerator.randomOrderList(DEFAULT_DATA_SIZE);
		
		this._appView.outputLine("");
		this._dictionary = new DictionaryByBinarySearchTree<Integer, Integer>();
		
		this._dictionary.setVisitEvent(this);
		this.addToBinarySearchTreeAndShowShape();
		this.showInorderOfBinarySearchTree();
		this.removeFromBinarySearchTreeAndShowShape();
		
		
		
	}
	private void addToBinarySearchTreeAndShowShape(){
		this._appView.outputLine("<< 삽입 과정에서의 이진검색트리의 변화 >>");
		for(int i = 0; i <this._list.length; i++) {
			this._dictionary.addKeyAndObject(this._list[i], i);
			this._appView.output(String.format("%d(%2d)", this._list[i], i));
			this._appView.outputLine(" 원소를 삽입한 후의 이진검색트리:");
			this._dictionary.reverseInorder();
		}
		
		
		
	
	}
	private void showInorderOfBinarySearchTree() {
		this._appView.outputLine("<< Inorder Traversal >>");
		this._dictionary.inorder();
		
	}
	private void removeFromBinarySearchTreeAndShowShape() {
		this._appView.outputLine("<< 삭제 과정에서의 이진검색트리의 변화 >>");
		for(int i =0; i < this.DEFAULT_DATA_SIZE; i++) {
			this._dictionary.removeObjectForKey(this._list[i]);
			this._appView.outputLine("Key 값이 " + this._list[i] + "인 원소를 삭제한 후의 이진검색트리: ");
			this._dictionary.reverseInorder();
		}
		
	}
	
	
	@Override
	public void visitForInorder(DictionaryElement<Integer, Integer> anElement, int aLevel) {
		// TODO Auto-generated method stub
		
		
		this._appView.outputLine(String.format("%4d(%2d)", anElement.key(), anElement.object()));
		
	}
	@Override
	public void visitForReverseOfInorder(DictionaryElement<Integer, Integer> anElement, int aLevel) {
		// TODO Auto-generated method stub
		
		for(int i =0; i < aLevel; i++) {
			this._appView.output("      ");
			
		}
		this._appView.outputLine(String.format("%d(%2d)",anElement.key() ,anElement.object()));
		
		
	}
	
}
