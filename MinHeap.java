import java.util.*;
public class MinHeap implements Heap {
	private List<Integer> nodes;
	public MinHeap() {
		nodes = new ArrayList<>();
		nodes.add(null);
	}

	public void insert(Integer e) {
		nodes.add(e);
		int i = nodes.size() - 1;
		while (i > 0) {
			Integer root = nodes.get(i);
			Integer left = null;
			Integer right = null;

			if ((2 * i) < nodes.size()) {
				left = nodes.get(2 * i);
			}

			if ((2 * i + 1) < nodes.size()) {
				right = nodes.get(2 * i + 1);
			}

			if (left != null && root > left) {
				nodes.set(i, left);
				nodes.set(2 * i, root);
			} 

			if(right != null && root > right) {
				nodes.set(i, right);
				nodes.set(2 * i + 1, root);
			}

			i /= 2;
		}

	}

	public void delete(Integer e) {

	}

	public Integer peekTop() {
		return null;
	}

	public Integer removeTop() {
		return null;
	}

	public void printAll() {
		for (Integer i : nodes) {
			System.out.print(i +" ");
		}
	}

	public static void main(String[] args) {
		Heap heap = new MinHeap();
		heap.insert(40);
		heap.insert(20);
		heap.insert(15);
		heap.insert(10);
		heap.insert(30);
		heap.insert(25);
		heap.insert(66);
		heap.insert(5);
		heap.insert(4);
		heap.insert(12);
		heap.printAll();
	}
}