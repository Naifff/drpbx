package alg;

public class Tree {
    private TreeNode root;

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }

    /**
     * **** Создать программу для построения двоичного дерева.
     * В цикле построить двадцать деревьев из 32-63 элементов.
     * Данные, которыми необходимо заполнить узлы деревьев представляются в
     * виде чисел типа int. Число, которое попадает в узел, должно
     * генерироваться случайным образом в диапазоне от -100 до 100.
     * Запустить программу.
     *
     * **** Проанализировать, какой процент созданных деревьев является
     * несбалансированными.
     * */

    public void insert(int p) {
        TreeNode node = new TreeNode(p);
        if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true) {
                parent = current;
                if (p < current.value) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }

                }
            }
        }
    }

    public TreeNode getNode(int key) {
        TreeNode current = root;
        while (current.value != key) {
            if (key < current.value)
                current = current.left;
            else
                current = current.right;

            if (current == null)
                return null;
        }
        return current;
    }

    private void inOrderTravers(TreeNode root) {
        if (root != null) {
            inOrderTravers(root.left);
            System.out.println(root);
            inOrderTravers(root.right);
        }
    }

    public boolean isBalansed(){
        
        return true;
    }

    public void displayTreeToConsole() {
        inOrderTravers(root);
    }

    public TreeNode min() {
        TreeNode current = root;
        TreeNode last = null;
        while (current != null) {
            last = current;
            current = current.left;
        }
        return last;
    }

    public boolean isBalanced(){
        return Math.abs(countDepth(root.left)-countDepth(root.right))<=((true)?0:1);
    }

    private int countDepth(TreeNode t){
        if (t == null){ return 0;}

        int left=0;
        int right=0;

        if(t.left!=null){left=countDepth(t.left);}

        if(t.right!=null){right=countDepth(t.right);}

        return 1+((left>right)?left:right);
    }

    public boolean delete (int id) {
        TreeNode current = root;
        TreeNode parent = root;
        boolean isLeftChild = true;

        // searching for node
        while (current.value != id) {
            parent = current;
            if (id < current.value) {
                current = current.left;
                isLeftChild = true;
            } else {
                current = current.right;
                isLeftChild = false;
            }
            if (current == null)
                return false;
        }

        //if it is a leaf
        if (current.left == null && current.right == null) {
            if (current == root)
                root = null;
            else if (isLeftChild)
                parent.left = null;
            else
                parent.right = null;
        // if only one child exists
        } else if (current.right == null) {
            if (isLeftChild)
                parent.left = current.left;
            else
                parent.right = current.left;
        } else if (current.left == null) {
            if (isLeftChild)
                parent.left = current.right;
            else
                parent.right = current.right;
        // if both children exist
        } else {
            TreeNode successor = getSuccessor(current);
            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.left = successor;
            else
                parent.right = successor;
            successor.left = current.left;
        }
        return true;
    }

    private TreeNode getSuccessor(TreeNode node) {
        TreeNode sParent = node;
        TreeNode s = node;
        TreeNode current = node.right;
        while (current != null) {
            sParent = s;
            s = current;
            current = current.left;
        }
        if (s != node.right) {
            sParent.left = s.right;
            s.right = node.right;
        }
        return s;
    }
}
