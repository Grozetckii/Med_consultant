package com.med_consultant.backend;

public class RedBlackTree {
    private Node root;
    private final Node TNULL;

    private void NLRHelper(Node node){
        if (node != TNULL) {
            System.out.println(node.key + " ");
            NLRHelper(node.left);
            NLRHelper(node.right);
        }
    }

    private void LNR_RemoveHelper(Node node, String fio){
        if (node != TNULL) {
            LNR_RemoveHelper(node.left, fio);
            //int i = 0;
            if(node.list != null){
                for(int i = 0; i < node.list.size(); i++){
                    Hospitals hospital = node.list.get(i);
                    if((hospital.getSurname() + hospital.getName() + hospital.getPatronymic()).equals(fio)){
                        node.list.remove(i);
                        break;
                    }
                }
            }
            LNR_RemoveHelper(node.right, fio);
        }
    }

    private void LRNHelper(Node node){
        if (node != TNULL) {
            LRNHelper(node.left);
            LRNHelper(node.right);
            System.out.println(node.key + " ");
        }
    }

    private Node searchTreeHelper(Node node, int key){
        if (node == TNULL || key == node.key) {
            return node;
        }

        if (key < node.key) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    private void deleteBalance(Node x){
        Node s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                }
                else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            }
            else {
                s = x.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                }
                else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }

    private void rbTransplant(Node u, Node v){
        if (u.parent == null) {
            root = v;
        }
        else if (u == u.parent.left) {
            u.parent.left = v;
        }
        else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteNodeHelper(Node node, int key){
        Node z = TNULL;
        Node x, y;
        while (node != TNULL) {
            if (node.key == key) {
                z = node;
            }

            if (node.key <= key) {
                node = node.right;
            }
            else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Ненаход(");
            return;
        }

        y = z;
        int y_original_color = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        }
        else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        }
        else {
            y = minimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            }
            else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (y_original_color == 0) {
            deleteBalance(x);
        }
    }

    private void insertBalance(Node k){
        Node u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                }
                else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            }
            else {
                u = k.parent.parent.right;

                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                }
                else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }

    private void printHelper(Node root, String indent, boolean last){
        if (root != TNULL) {
            System.out.println(indent);
            if (last) {
                System.out.println("R----");
                indent += "   ";
            }
            else {
                System.out.println("L----");
                indent += "|  ";
            }

            String sColor = (root.color == 1) ? "КРАСНЫЙ" : "ЧЁРНЫЙ";
            //System.out.println(root.key + "  (" + sColor + ")" + "   (" + root.time.hour + "ч:" + root.time.minute + "мин:" + root.time.second + "сек)");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }


    public RedBlackTree(){
        TNULL = new Node();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    public void NLR(){
        NLRHelper(this.root);
    }

    public void LNR_Remove(String fio){
        LNR_RemoveHelper(this.root, fio);
    }

    public void LRN(){
        LRNHelper(this.root);
    }

    public Node searchTree(int k){
        return searchTreeHelper(this.root, k);
    }

    public Node minimum(Node node){
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public Node maximum(Node node){
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }

    public void leftRotate(Node x){
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        }
        else if (x == x.parent.left) {
            x.parent.left = y;
        }
        else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x){
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        }
        else if (x == x.parent.right) {
            x.parent.right = y;
        }
        else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(int key, Hospitals hospital){
        Node cur = searchTree(key);
        if(cur != TNULL){
            cur.list.addFirst(hospital);
        }else{
            Node node = new Node();
            node.parent = null;
            node.key = key;
            node.left = TNULL;
            node.right = TNULL;
            node.color = 1;
            node.list = new LinkedList();
            node.list.addFirst(hospital);

            if (node.key == searchTree(node.key).key) {
                System.out.println("Тут уже есть такие!");
                return;
            }

            Node y = null;
            Node x = this.root;

            while (x != TNULL) {
                y = x;
                if (node.key < x.key) {
                    x = x.left;
                }
                else {
                    x = x.right;
                }
            }

            node.parent = y;
            if (y == null) {
                root = node;
            }
            else if (node.key < y.key) {
                y.left = node;
            }
            else {
                y.right = node;
            }

            if (node.parent == null) {
                node.color = 0;
                return;
            }

            if (node.parent.parent == null) {
                return;
            }

            insertBalance(node);
        }
    }

    public Node getRoot(){
        return this.root;
    }

    public void deleteNode(int key){
        deleteNodeHelper(this.root, key);
    }

    public void printTree(){
        if (root != TNULL) {
            printHelper(this.root, "", true);
        }
        else {
            System.out.println("Всё, приплыли, нету дерева");
        }
    }

    public void cleanTree(){
        while (getRoot() != TNULL) {
            deleteNode(getRoot().key);
        }
    }
}
