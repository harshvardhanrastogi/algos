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
		heapify(i);
	}

	public void heapify(int i) {
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

			if (right == null && left != null && root > left) {
				nodes.set(i, left);
				nodes.set(2 * i, root);
			} else if (left != null && right != null) {
				int minIndex = left > right ? 2 * i + 1 : 2 * i;
				if (nodes.get(minIndex) < root) {
					Integer t = nodes.get(minIndex);
					nodes.set(i, t);
					nodes.set(minIndex, root);
				}
			}

			i /= 2;
		}
	}

	public void delete(Integer e) {
		int index = nodes.indexOf(e);
		if (index == -1) return;

		nodes.set(index, Integer.MIN_VALUE);
		index /= 2; //start from parent
		heapify(index);
		removeTop();
	}

	public Integer peekTop() {
		return nodes.size() > 1 ? nodes.get(1) : null;
	}

	public Integer removeTop() {
		// root is null 
		if (nodes.get(1) == null) return null;

		int rootIndex = 1, newRootIndex = -1, result = nodes.get(1);
		nodes.set(1, getLastElement());
		removeLastElement();
		while (2 * rootIndex < nodes.size()) { // atleast left child is there
			
			int root = nodes.get(rootIndex); 
			int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;

			if (2 * rootIndex < nodes.size()) {// check again as we removed last element
				left = nodes.get(2 * rootIndex); 
			}

			if ((2 * rootIndex) + 1 < nodes.size()) {
				right = nodes.get(2 * rootIndex + 1); 
			}

			if (left == Integer.MIN_VALUE) {
				return result;
			}

			if (right == Integer.MIN_VALUE && left < root) {// single left child
				nodes.set(2 * rootIndex, root);
				nodes.set(rootIndex, left);
				newRootIndex = 2 * rootIndex;
			} else  {
				int minIndex = 2 * rootIndex;//minimum child index
				if (right != Integer.MIN_VALUE) {
					minIndex = left > right ? (2 * rootIndex) + 1 : 2 * rootIndex;
				}  
				if (nodes.get(minIndex) < root) {
					int child = nodes.get(minIndex);
					nodes.set(minIndex, root);
					nodes.set(rootIndex, child);
					newRootIndex = minIndex;
				}
			}

			if (newRootIndex == -1) return result; //already valid

			rootIndex = newRootIndex;
			newRootIndex = -1;
		}
		
		return result;
	}

	private int getLastElement() {
		return nodes.get(nodes.size() - 1);
	}

	private void removeLastElement() {
		nodes.remove(nodes.size() - 1);
	}

	public void printAll() {
		for (int i = 1; i < nodes.size(); i++) {
			System.out.print(nodes.get(i)+" ");	
		}
		System.out.println();	
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
		heap.delete(25);
		heap.delete(15);
		heap.printAll();
	}
}