package binaryTreeTraversals;

public interface VisitEventForDictionaryByBinarySearchTree<K extends Comparable <K>, O> {
	public void visitForInorder(DictionaryElement<K, O> anElement, int aLevel);
	public void visitForReverseOfInorder(DictionaryElement<K, O> anElement, int aLevel);

}
