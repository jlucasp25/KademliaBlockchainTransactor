package pt.groupG.core;

public class Contact {
    private Node node;

    public Contact(Node aux) {
        this.node = aux;
    }


    public Node getNode() {
        return node;
    }

    public boolean equals(Contact aux) {
        if (this.node == aux.node) {
            return true;
        }
        else {
            return false;
        }
    }
}
