package lapr.project.utils.bst;

import java.awt.geom.Point2D;
import java.util.Comparator;

    public class KD_Tree<T> {

        protected static class Node<T> {
            protected Point2D.Double coords;
            protected T info;
            protected Node<T> left;     // a reference to the left child (if any)
            protected Node<T> right;   // a reference to the right child (if any)

            public Node(double x,double y,T info, Node<T> left, Node<T> right) {
                this.coords = new Point2D.Double(x,y);
                this.info = info;
                this.left = left;
                this.right = right;
            }


            // accessor methods
            public Point2D.Double getCoords() { return coords; }
            public Node<T> getLeft() { return left; }
            public Node<T> getRight() { return right; }
            public Double getX() { return coords.getX(); }
            public Double getY() { return coords.getY(); }
            public T getInfo() { return info; }

            // update methods
            public void setCoords(Point2D.Double coords) { this.coords = coords; }
            public void setLeft(Node<T> leftChild) { left = leftChild; }
            public void setRight(Node<T> rightChild) { right = rightChild; }
            public void setInfo(T info) { this.info = info; }

            public void setObject(Node<T> node) {
                setCoords(node.getCoords());
                setInfo(node.getInfo());
                setLeft(node.getLeft());
                setRight(node.getRight());
            }
        }

        //----------- end of nested Node class -----------

        private final Comparator<Node<T>> cmpX = new Comparator<Node<T>>(){
            @Override
            public int compare(Node<T> p1, Node<T> p2) {
                return Double.compare(p1.getX(), p2.getX());
            }
        };
        private final Comparator<Node<T>> cmpY = new Comparator<Node<T>>(){
            @Override
            public int compare(Node<T> p1, Node<T> p2) {
                return Double.compare(p1.getY(), p2.getY());
            }
        };

        //------------Comparators--------------------------

        protected Node<T> root ;     // root of the tree


        /* Constructs an empty binary search tree. */
        public KD_Tree() {
            root = null;
        }

        /*
         * @return root Node of the tree (or null if tree is empty)
         */
        protected Node<T> root() {
            return root;
        }

        /*
         * Verifies if the tree is empty
         * @return true if the tree is empty, false otherwise
         */
        public boolean isEmpty(){
            return root==null;
        }

        /*
         * Inserts an element in the tree.
         */
        public void insert(double x, double y,T info){
            Node<T> node = new Node<>(x, y, info , null, null);
            if (isEmpty()) root=node;

            insert(root(),node,true);
        }

        protected Node<T> insert(Node<T> currentNode, Node<T> node, boolean divX){
            if (node.coords.equals(currentNode.coords))
                return currentNode;
            int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);
            if (cmpResult < 0) {
                if (currentNode.left == null) {
                    currentNode.left = node;
                } else {
                    insert(currentNode.left, node, !divX);
                }
            }else {
                if (currentNode.right == null) {
                    currentNode.right = node;
                }else {
                    insert(currentNode.right, node, !divX);
                }
            }
            return node;
        }

        public T find(double x , double y){
            if (isEmpty()){
                return null;
            }else {
                Node<T> node = new Node<T>(x,y,null,null,null);
                Node<T> result = find(root(), node, true);
                if(result == null){
                    return null;
                }else
                    return result.getInfo();
            }
        }


        protected Node<T> find(Node<T> currentNode, Node<T> nodeprocura,boolean divX){
            if (nodeprocura.coords.equals(currentNode.coords)) {
                return currentNode;
            }

            int cmpResult = (divX ? cmpX : cmpY).compare(nodeprocura, currentNode);
            if (cmpResult < 0) {
                if (currentNode.left == null) {
                    return null;
                } else {
                    return find(currentNode.left, nodeprocura, !divX);
                }
            }else {
                if (currentNode.right == null) {
                    return null;
                }else {
                    return find(currentNode.right, nodeprocura, !divX);
                }
            }
        }

        /*
         * Returns the number of nodes in the tree.
         * @return number of nodes in the tree
         */
        public int size(){
            if (isEmpty()){
                return 0;
            }else {
                return size(root())+1 ;
            }
        }

        private int size(Node<T> node){
            int size =0;
            if (node==null){
                return size;
            }
            if (node.getRight()!= null){
                size += size(node.getRight());
                size++;
            }
            if (node.getLeft()!=null){
                size+= size(node.getLeft());
                size++;
            }
            return size;
        }


        public T findNearestNeighbour(double x , double y){
            if (isEmpty()) return null;

            return findNearestNeighbour(root(),x,y,root(),true);
        }

        protected T findNearestNeighbour(Node<T> node, double x, double y,Node<T> closestNode, boolean divX) {
            if (node == null) {
                return null;
            }


            double d = Point2D.distanceSq(node.getCoords().getX(), node.getCoords().getY(), x, y);
            double closestDist = Point2D.distanceSq(closestNode.getCoords().getX(), closestNode.getCoords().getY(), x, y);


            if (closestDist > d) {
                closestNode.setObject(node);
            }
                double delta = divX ? x - node.coords.x : y - node.coords.y;
                double delta2 = delta * delta;
                Node<T> node1 = delta < 0 ? node.left : node.right;
                Node<T> node2 = delta < 0 ? node.right : node.left;

                findNearestNeighbour(node1, x, y, closestNode, !divX);

                if (delta2 < closestDist) {
                    findNearestNeighbour(node2, x, y, closestNode, !divX);
                }
            return closestNode.info;
        }
}
