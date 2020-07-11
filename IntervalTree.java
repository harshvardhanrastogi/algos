public class IntervalTree {
	
	public IntervalNode root;

	public IntervalTree() {

	}

	public void put(int lo, int hi, int val) {
		if (root == null) {
			root = new IntervalNode(lo, hi, val);
			return;
		}

		IntervalNode n = root;
		while (n != null) {
			if (lo < n.lo) {
				n = addLeft(n, lo, hi, val);
			} else {
				n = addRight(n, lo, hi, val);
			}
		}

	}

	private IntervalNode addLeft(IntervalNode left, int lo, int hi, int val) {
		if (left.left == null) {
			left.left = new IntervalNode(lo, hi, val);
			return null;
		} else {
			updateNodeValue(left, val);
			return left.left;
		}
	}

	private IntervalNode addRight(IntervalNode right, int lo, int hi, int val) {
		if (right.right == null) {
			right.right = new IntervalNode(lo, hi, val);
			return null;
		} else {
			updateNodeValue(right, val);
			return right.right;
		}

	}

	private void updateNodeValue(IntervalNode n, int val) {
		if (val > n.val) {
			n.val = val;
		}
	}

	void search(IntervalNode root, int lo, int hi, Int o) {
		if (root == null) {
			return;
		}

		if (intersects(root.lo, root.hi, lo, hi)) {
			o.i = o.i + 1;
		}

		if (root.left != null && root.left.val >= lo) {
			search(root.left, lo, hi, o);
		} else if (root.right != null) {
			search(root.right, lo, hi, o);
		}
	}

	boolean intersects(int lo1, int hi1, int lo2, int hi2) {
		return (lo1 <= lo2 && hi1 >= lo2) || (lo1 >= lo2 && lo1 <= hi2);
	}

	
	static class Int {
		public int i;
	}

	static class IntervalNode {
		public final int lo;
		public final int hi;
		public int val;
		public IntervalNode left;
		public IntervalNode right;

		public IntervalNode(int lo, int hi, int val) {
			this.lo = lo;
			this.hi = hi;
			this.val = val;
		}

		public String toString() {
			return "["+lo+", "+hi+", "+val+"]";
		}

	}
}