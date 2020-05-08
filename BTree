public class BTree {

    public static void main(String[] args) {
	insert(root, 10);
	insert(root, 20);
	insert(root, 40);
	insert(root, 50);
	insert(root, 60);
	insert(root, 70);
	insert(root, 80);
	insert(root, 30);
	insert(root, 35);

	printInorder(root);
    }

    static Node root;

    static class Node {
	Node parent;
	Integer[] keys;
	Node[] children;
	int keyCount;

	Node(int val) {
	    keys = new Integer[3];
	    keys[0] = val;
	    children = new Node[4];
	    keyCount++;
	}

	void makeParentSelf() {
	    for (Node child : children) {
		if (child != null) {
		    child.parent = this;
		}
	    }
	}

	void add(int index, int val) {
	    keys[index] = val;
	    keyCount++;
	}

	public String toString() {
	    String result = String.format("keyCount : %d,\n keys:{%s, %s, %s},\n children: {%s, %s, %s, %s}",
		    keyCount,
		    String.valueOf(keys[0]),
		    String.valueOf(keys[1]),
		    String.valueOf(keys[2]),
		    children[0] == null ? "null" : children[0].toString(),
		    children[1] == null ? "null" : children[1].toString(),
		    children[2] == null ? "null" : children[2].toString(),
		    children[3] == null ? "null" : children[3].toString());
	    return result;
	}

    }

    private static void insert(Node root, int val) {
	if (BTree.root == null) {
	    BTree.root = new Node(val);
	    return;
	}

	if (BTree.root == root && root.children[0] == null) {
	    if (root.keyCount < 3) {
		addKey(root, val);
	    } else {
		split(root.parent, splitNode(root, val));
	    }
	    return;
	}


	if (root.children[0] == null) {
	    if (root.keyCount < 3) {
		addKey(root, val);
	    } else {
		split(root.parent, splitNode(root, val));
	    }

	    return;
	}

	Node child = null;
	int index = 0;

	while (index < 3) {
	    if (root.keys[index] != null) {
		if (val < root.keys[index]) {
		    child = root.children[index];
		    break;
		} else {
		    child = root.children[index + 1];
		}
	    }
	    index++;
	}

	insert(child, val);

    }

    private static void split(Node parent, Node[] children) {
	if (parent == null) {
	    Node n = new Node(children[0].keys[1]);
	    delete(children[0]);
	    n.children[0] = children[0];
	    n.children[1] = children[1];
	    n.makeParentSelf();
	    BTree.root = n;
	} else if (parent.keyCount < 3) {
	    addKey(parent, children[0].keys[1], children[0], children[1]);
	    delete(children[0]);
	} else {
	    Node[] nodes = splitNode(parent, children[0].keys[1], children[0], children[1]);
	    delete(children[0]);
	    split(parent.parent, nodes);
	}
    }

    private static Node[] splitNode(Node n, int val) {
	return splitNode(n, val, null, null);
    }

    private static Node[] splitNode(Node n, int val, Node child, Node child1) {
	Node n1, n2;
	int k1 = 0, k2 = 0, k3 = 0, k4 = 0, index = 3, i1 = 0, i2 = 0;
	int[] keys = new int[4];
	Node[] children = new Node[5];

	if (val < n.keys[0]) {
	    index = 0;
	} else if (val < n.keys[1]) {
	    index = 1;
	} else if (val < n.keys[2]) {
	    index = 2;
	}

	while (i2 < 4) {
	    if (i1 < 4) {
		if (index == i1) {
		    keys[k1] = val;
		} else {
		    keys[k1] = n.keys[k2];
		    k2++;
		}
		k1++;
	    }

	    if (index == k3) {
		children[k3] = child;
		children[k3 + 1] = child1;
		k3++;
	    } else {
		children[k3] = n.children[k4];
	    }
	    k3++;
	    k4++;
	    i1++;
	    i2++;
	}

	n1 = new Node(keys[0]);
	n1.add(1, keys[1]);
	n1.children[0] = children[0];
	n1.children[1] = children[1];


	n2 = new Node(keys[2]);
	n2.add(1, keys[3]);
	n2.children[0] = children[2];
	n2.children[1] = children[3];
	n2.children[2] = children[4];
	Node[] nodes = {n1, n2};

	n1.makeParentSelf();
	n2.makeParentSelf();
	return nodes;
    }

    private static void addKey(Node node, int val) {
	addKey(node, val, null, null);
    }

    private static void addKey(Node node, int val, Node child, Node child1) {
	if (node.keys[0] == null || val < node.keys[0]) {
	    Integer t = node.keys[0];
	    node.keys[0] = val;
	    node.keys[2] = node.keys[1];
	    node.keys[1] = t;
	    node.children[3] = node.children[2];
	    node.children[2] = node.children[1];
	    node.children[1] = child1;
	    node.children[0] = child;
	} else if (node.keys[1] == null || val < node.keys[1]) {
	    Integer t = node.keys[1];
	    node.keys[1] = val;
	    node.keys[2] = t;
	    node.children[3] = node.children[2];
	    node.children[2] = child1;
	    node.children[1] = child;
	} else {
	    node.keys[2] = val;
	    node.children[2] = child;
	    node.children[3] = child1;
	}
	node.makeParentSelf();
	node.keyCount++;

    }

    private static void delete(Node n) {
	n.keys[1] = null;
	n.keyCount--;
    }

    private static void printInorder(Node root) {
	if (root == null) {
	    return;
	}
	printInorder(root.children[0]);
	printIfNotNull(root.keys[0]);
	printInorder(root.children[1]);
	printIfNotNull(root.keys[1]);
	printInorder(root.children[2]);
	printIfNotNull(root.keys[2]);
	printInorder(root.children[3]);
    }

    private static void printIfNotNull(Integer key) {
	if (key != null) {
	    System.out.print(key + "\t");
	}
    }
}
