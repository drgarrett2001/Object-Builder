class Node<T> {
    T value;
    Node next;
}
class NewStack <T> {
    //initialize head
    Node head;
    Node tail;
    String name;

    public void push(T v){ //adding value to top of the list
        Node<T> node = new Node<T>();

        if(head==null) { //making head
            node.value = v;
            head = node;
            tail = head;
        }
        else {

            node.value = v;
            tail.next = node;
            tail = node;
        }
    }//end push method
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public T pop(){ // removing values
        Node<T> top = new Node<T>();
        top = head;
        head = head.next;
        return top.value;
    }//end pop method
}//end class NewStack