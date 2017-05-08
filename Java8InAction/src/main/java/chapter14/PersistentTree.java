package chapter14;

public class PersistentTree {
    public static void main(String[] args) {
        Tree tree = new Tree("Mary", 22,
                new Tree("Emily", 20,
                        new Tree("Alan", 50, null, null),
                        new Tree("Georgie", 23, null, null)
                ),
                new Tree("Tian", 29,
                        new Tree("Raoul", 23, null, null),
                        null));

        System.out.println(lookup("Raoul", -1, tree));
        System.out.println(lookup("Jeff", -1, tree));

        Tree f = fupdate("Jeff", 80, tree);
        System.out.println(lookup("Jeff", -1, f));

        Tree u = update("Jim", 40, tree);
        System.out.println(lookup("Jeff", -1, u));
        System.out.println(lookup("Jim", -1, u));

        Tree f2 = fupdate("Jeff", 80, tree);
        System.out.println(lookup("Jeff", -1, f2));
        System.out.println(lookup("Jim", -1, f2));
    }

    static class Tree {
        private String key;
        private int val;
        private Tree left, right;

        public Tree(String key, int val, Tree left, Tree right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int lookup(String key, int defaultVal, Tree tree) {
        if (tree == null) {
            return defaultVal;
        }
        if (key.equals(tree.key)) {
            return tree.val;
        }
        return lookup(key, defaultVal, key.compareTo(tree.key) < 0 ? tree.left : tree.right);
    }

    public static Tree update(String key, int newVal, Tree tree) {
        if (tree == null) {
            tree = new Tree(key, newVal, null, null);
        } else if (key.equals(tree.key)) {
            tree.val = newVal;
        } else if (key.compareTo(tree.key) < 0) {
            tree.left = update(key, newVal, tree.left);
        } else {
            tree.right = update(key, newVal, tree.right);
        }

        return tree;
    }

    public static Tree fupdate(String key, int newVal, Tree tree) {
        return tree == null ? new Tree(key, newVal, null, null) :
                key.equals(tree.key) ? new Tree(key, newVal, tree.left, tree.right) :
                        key.compareTo(tree.key) < 0 ?
                                new Tree(tree.key, tree.val, fupdate(key, newVal, tree.left), tree.right) :
                                new Tree(tree.key, tree.val, tree.left, fupdate(key, newVal, tree.right));
    }
}
