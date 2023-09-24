public class herencia {
    public static void main(String[] args){
    Qarray<Integer> cola1 = new Qarray<>();
    cola1.enqueue(1);
    cola1.enqueue(1);
    cola1.enqueue(1);
    cola1.enqueue(1);
    cola1.dequeue();
    cola1.dequeue();
    cola1.enqueue(2);
    cola1.enqueue(2);
    Node<Integer> i = new Node<>(0);
    i = cola1.head;
    while(i != null){
        System.out.println(i.key);
        i = i.next;
    }
    System.out.println("-----------------");
    System.out.println(cola1.head.key);
    System.out.println(cola1.tail.key);
    System.out.println("Caso LinkedList");
    LinkedList<Integer> listalink1 = new LinkedList<>();
    listalink1.pushBack(3);
    listalink1.pushBack(4);
    listalink1.pushFront(2);
    listalink1.pushFront(1);
    listalink1.popBack();
    listalink1.popFront();
    Node<Integer> currentNode = listalink1.head; // Comenzamos desde el nodo de la cabeza
    while (currentNode != null && currentNode.key != 2) {
    // Avanzamos al siguiente nodo hasta encontrar el nodo con el valor 2
    currentNode = currentNode.next;
    }
    if (currentNode != null) {
    // Si encontramos el nodo con el valor 2, agregamos después de él
    listalink1.addAfter(currentNode, 5); // Agregamos el valor 5 después del nodo con el valor 2
    }
    Node<Integer> k = new Node<>(0);
    k = listalink1.head;
    while(k != null){
        System.out.println(k.key);
        k = k.next;
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

    protected Node<T> head;
    protected Node<T> tail;

    public LinkedList(){
        head = tail = null;
    }

    public void pushFront(T element){
        Node<T> nodo = new Node<>(element);         //Creación del nodo
        nodo.key = element;                         //La llave del nodo se asigna al valor pasado a la función
        nodo.next = head;                           //La direccion del head pasará a ser el next del nodo que pusheamos
        head = nodo;                                //Ahora el head será el nodo que pusheamos...si se hiciera al reves el nodo que ahora es nuestro head no tendría un next 
    }

    public T popFront(){
        if(head == null)
            throw new RuntimeException("La lista enlazada está vacía"); //En caso de que la lista esté vacía
        Node<T> p = head;
        head = head.next;   //La cabeza ahora será el nodo siguiente                                                             
        if(head == null){
            tail = null;
        }   //Caso por si la lista enlazada tenia solo 1 elemento y lo eliminamos            
        return p.key;
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

    public T popBack(){
        if(tail == null)
            throw new RuntimeException("La lista ya está vacía");
        if(head == tail){
            head = tail = null;
            return null;
        }
        else{
        Node<T> p = tail;
        Node<T> current = head;
        while(current.next.next != null){
            current = current.next;
        }
        current.next = null;
        tail = current;
        return p.key;
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

    public T erase(T keyToRemove) {
    if (head == null) {
        throw new RuntimeException("La lista está vacía");
    }

    if (head.key == keyToRemove) {
        T removedValue = head.key;
        head = head.next;
        return removedValue;
    }

    Node<T> current = head;
    while (current.next != null) {
        if (current.next.key == keyToRemove) {
            T removedValue = current.next.key;
            current.next = current.next.next;
            return removedValue;
        }
        current = current.next;
    }
    throw new RuntimeException("Elemento no encontrado");
}

    public T findint(T keyToFind) {
    Node<T> current = head;
    while (current != null) {
        if (current.key == keyToFind) {
            return current.key; // Elemento encontrado, retorna el valor
        }
        current = current.next;
    }
    return null; // Elemento no encontrado, retorna null como valor especial
    }

    public boolean findboolean(T keyToFind) {
    Node<T> current = head;
    while (current != null) {
        if (current.key == keyToFind) {
            return true; // Elemento encontrado, retorna el valor
        }
        current = current.next;
    }
    return false; // Elemento no encontrado, retorna -1 como valor especial
    }

    public boolean empty(){
        return head == null;
    }
}


class Qarray<T> extends LinkedList<T>{
    private int count;

    Qarray(){                       //el array por defecto tiene head y tail = 0, la cola tendrá i espacios para elementos
        super();
        count = 0;      
    }

    public void enqueue(T element){
        pushBack(element);
        count++;     //En caso de que el tail sea igual o mas grande que la length de la cola, este sera pasado por un modulo,
    }                                    //es decir, si tail y length = 4, el modulo hará a tail 0, si el tail es 5, pasará a ser 1

    public T dequeue(){
        T p;
        p = popFront();
        count--;
        return p;
    }

    @Override 
    public boolean empty(){
        return count <= 0;
    }

}