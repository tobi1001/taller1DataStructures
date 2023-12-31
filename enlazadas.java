public class enlazadas {
    public static void main(String[] args){
        LinkedList<Integer> listalink1 = new LinkedList<>();
        listalink1.pushBack(3);
        listalink1.pushBack(4);
        listalink1.pushFront(2);
        listalink1.pushFront(1);
        listalink1.popBack();
        listalink1.popFront();
        Node<Integer> i = new Node<>(0);
        i = listalink1.head;
        while(i != null){
            System.out.println(i.key);
            i = i.next;
        }
    }
}

class Node<T>{
    
    T key;
    Node<T> next;

    Node(T key){
        this.key = key;
        this.next = null;
    }
}

class LinkedList<T>{

    Node<T> head;
    Node<T> tail;

    public LinkedList(){
        head = tail = null;
    }

    public void pushFront(T element){
        Node<T> nodo = new Node<>(element);         //Creación del nodo
        nodo.key = element;                         //La llave del nodo se asigna al valor pasado a la función
        nodo.next = head;                           //La direccion del head pasará a ser el next del nodo que pusheamos
        head = nodo;                                //Ahora el head será el nodo que pusheamos...si se hiciera al reves el nodo que ahora es nuestro head no tendría un next 
    }

    public void popFront(){
        if(head == null)
            throw new RuntimeException("La lista enlazada está vacía"); //En caso de que la lista esté vacía
        head = head.next;   //La cabeza ahora será el nodo siguiente                                                             
        if(head == null)    //Caso por si la lista enlazada tenia solo 1 elemento y lo eliminamos
            head = tail;
    }

    public void pushBack(T element){
        Node<T> nodo = new Node<>(element);
        nodo.key = element;
        nodo.next = null;
        if(tail == null){
            head = nodo;
            tail = nodo;
        }
        else{
            tail.next = nodo;
            tail = nodo;
        }
    }

    public void popBack(){
        if(tail == null)
            throw new RuntimeException("La lista ya está vacía");
        if(head == tail)
            head = tail = null;
        else{
        Node<T> current = head;
        while(current.next.next != null){
            current = current.next;
        }
        current.next = null;
        tail = current;
        }
    }

    public void addAfter(Node<T> current, T value){
        if(empty())
            throw new RuntimeException("La lista ya está vacía, no se puede añadir después de nada");
        Node<T> nodo = new Node<T>(value);
        nodo.next = current.next;
        current.next = nodo;
        if(tail == current)
            tail = nodo;         
    }

    public boolean empty(){
        return head == null;
    }
}