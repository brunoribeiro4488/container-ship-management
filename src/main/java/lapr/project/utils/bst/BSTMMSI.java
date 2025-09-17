package lapr.project.utils.bst;

import lapr.project.model.Ship;

public class BSTMMSI extends BST{
    /** Nested static class for a binary search tree node. */

    public Ship find(String mmsi){
        if (isEmpty()){
            return null;
        }else {
            BST.Node<Ship> result = find(root(), mmsi);
            if (result == null){
                return null;
            }else return result.getElement();
        }
    }

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element    the element to find
     * @return the Node that contains the Element, or null otherwise
     *
     */
    protected BST.Node<Ship> find(BST.Node<Ship> node, String element){
        if (node== null){
            return null;
        }

        if (node.getElement().compareToMMSI(element)==0){
            return node;
        }else if (node.getElement().compareToMMSI(element)==1){
            return find(node.getLeft(),element);
        }
        return find(node.getRight(), element);
    }

    /*
     * Inserts an element in the tree.
     */
    public void insert(Ship element){
        root = insert(element,root());
    }

    private BST.Node<Ship> insert(Ship element, BST.Node<Ship> node){

        if (node == null)
            return new BST.Node(element, null, null);
        if (node.getElement().compareToMMSI(element.getMmsi()) == 1) {
            node.setLeft(insert(element, node.getLeft()));
        } else if (node.getElement().compareToMMSI( element.getMmsi())==-1 ) {
            node.setRight(insert(element, node.getRight()));
        }else {
            node = new BST.Node<>(element,node.getLeft(),node.getRight());
        }
        return node;
    }

}
