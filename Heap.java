public interface Heap {
	void insert(Integer e);
	void delete(Integer e);
	Integer peekTop();
	Integer removeTop(); 
	void printAll();
}