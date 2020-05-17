class AvlTree {

    static class Node {
        int val;    //Value
        int ht;        //Height
        Node left;    //Left child
        Node right;    //Right child

        public Node(int val) {
            this.val = val;
        }

        public String toString() {
            return String.valueOf(val);
        }

    }

    static class Int {
        int d;

        public Int(int d) {
            this.d = d;
        }
    }

    static Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);
        }

        insertAndInc(root, val);
        Node z = findImbalancedNode(root);
        if (z == null) {
            updateHeight(root);
            return root;
        }

        if (val < z.val && val < z.left.val) { //left -left
            int zv = z.val;
            z.val = z.left.val;
            Node zr = z.right;
            Node yr = z.left.right;
            z.left = z.left.left;
            z.right = new Node(zv);
            z.right.left = yr;
            z.right.right = zr;

        } else if (val < z.val && val > z.left.val) { // left -right
            int zv = z.val;
            z.val = z.left.right.val;
            Node xr = z.left.right.right;
            z.left.right = z.left.right.left;
            Node zr = z.right;
            z.right = new Node(zv);
            z.right.left = xr;
            z.right.right = zr;
        } else if (val > z.val && val > z.right.val) { // right right
            int zv = z.val;
            z.val = z.right.val;
            Node zl = z.left;
            Node yl = z.right.left;
            z.right = z.right.right;
            z.left = new Node(zv);
            z.left.right = yl;
            z.left.left = zl;
        } else {  // right left
            int zv = z.val;
            z.val = z.right.left.val;
            Node xl = z.right.left.left;
            z.right.left = z.right.left.right;
            Node zl = z.left;
            z.left = new Node(zv);
            z.left.right = xl;
            z.left.left = zl;
        }

        updateHeight(root);
        return root;
    }

    static void updateHeight(Node root) {
        if (root == null) {
            return;
        }

        updateHeight(root.left);
        updateHeight(root.right);

        int lht = height(root.left);
        int rht = height(root.right);

        root.ht = Math.max(lht, rht) + 1;
    }

    static Node findImbalancedNode(Node root) {
        Node result = new Node(-1);
        Int v = new Int(-1);
        checkImbal(root, v);
        return v.d == -1 ? null : find(root, v.d);
    }

    static Node find(Node root, int val) {
        if (val < root.val) {
            return find(root.left, val);
        } else if (val > root.val) {
            return find(root.right, val);
        }
        return root;
    }

    static void checkImbal(Node root, Int result) {
        if (root == null) {
            return;
        }
        int lh = height(root.left);
        int rh = height(root.right);

        if (Math.abs(lh - rh) > 1) {
            result.d = root.val;
        }

        checkImbal(root.left, result);
        checkImbal(root.right, result);
    }


    static void insertAndInc(Node root, int val) {

        if (val < root.val) {
            if (root.left != null) {
                insert(root.left, val);
                int rh = height(root.right);
                root.ht = Math.max(rh, root.left.ht) + 1;
            } else {
                Node l = new Node(val);
                root.left = l;
                root.ht++;
            }
        } else {
            if (root.right != null) {
                insert(root.right, val);
                int lh = height(root.left);
                root.ht = Math.max(lh, root.right.ht) + 1;
            } else {
                Node r = new Node(val);
                root.right = r;
                root.ht++;
            }
        }
    }

    public static void main(String[] args) {
        Node root = insert(null, 5);
        insert(root, 4);
        insert(root, 11);
        insert(root, 27);
        insert(root, 10);

        printBF(root);
    }

    static int height(Node n) {
        return n == null ? -1 : n.ht;
    }

    static void printBF(Node root) {
        if (root == null) {
            return;
        }
        printBF(root.left);
        int d = (root.left == null ? -1 : root.left.ht) - (root.right == null ? -1 : root.right.ht);
        System.out.print(root + "(BF=" + d + ") ");
        printBF(root.right);
    }


}
