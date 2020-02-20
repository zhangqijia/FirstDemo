package week2;

public class BinTree<T extends Comparable<T>> {

    TreeNode root;

    private class TreeNode {
        T e;
        TreeNode right;
        TreeNode left;

        public TreeNode(T e) {
            this(e, null, null);
        }

        public TreeNode(T e, TreeNode right, TreeNode left) {
            this.e = e;
            this.right = right;
            this.left = left;
        }
    }

    public void add(T e) {
        if (root == null) {
            root = new TreeNode(e, null, null);
        } else {
            add(e, root);
        }
    }

    private void add(T e, TreeNode node) {
        if (e.compareTo(node.e) < 0) {
            //right child
            if (node.right == null) {
                node.right = new TreeNode(e);
            } else {
                add(e, node.right);
            }
        } else if (e.compareTo(node.e) > 0) {
            if (node.left == null) {
                node.left = new TreeNode(e);
            } else {
                add(e, node.left);
            }
        } else {
            // e = Node.e
        }
    }

    public void display() {
        if (root == null) {
            System.out.println("");
        } else {
            displayChild(root);
        }
    }

    private void displayChild(TreeNode node) {
        if (node.right != null) {
            displayChild(node.right);
        }
        System.out.println(node.e);
        if (node.left != null) {
            displayChild(node.left);
        }
    }

    public T get(){
        return null;
    }
    public static void main(String[] args) {
        BinTree<Integer> integerBinTree = new BinTree<>();
        integerBinTree.add(100);
        integerBinTree.add(120);
        integerBinTree.add(130);
        integerBinTree.add(101);
        integerBinTree.add(103);
        integerBinTree.add(106);
        integerBinTree.add(108);
        integerBinTree.add(1000);
        integerBinTree.add(10);
        integerBinTree.add(15);
        integerBinTree.add(50);
        integerBinTree.add(700);
        integerBinTree.add(123);
        integerBinTree.add(109);
        integerBinTree.add(108);
        integerBinTree.display();

        BinTree<String> stringBinTree = new BinTree<>();
        stringBinTree.add("hello");
        stringBinTree.add("ni hao");
        stringBinTree.add("hallo");
        stringBinTree.add("hi");
        stringBinTree.add("howAreYou");
        stringBinTree.add("g");
        stringBinTree.add("a");
        stringBinTree.add("c");
        stringBinTree.add("d");
        stringBinTree.add("h");
        stringBinTree.add("z");
        stringBinTree.add("y");
        stringBinTree.add("x");
        stringBinTree.add("o");
        stringBinTree.add("i");
        stringBinTree.add("l");
        stringBinTree.display();
    }
}
