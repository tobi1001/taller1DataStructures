public class herencia {
    public static void main(String[] args){
    Qarray<Integer> cola1 = new Qarray<>();
    cola1.pushBack(1);
    cola1.pushBack(1);
    cola1.pushBack(1);
    cola1.pushBack(1);
    cola1.popFront();
    cola1.popFront();
    cola1.pushBack(2);
    cola1.pushBack(2);

    for(int i = 0; i <= 3;i++){
        System.out.println(cola1.get(i));
    }
    System.out.println("-----------------");
    System.out.println(cola1.head);
    System.out.println(cola1.tail);
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
            head = tail;
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

    public boolean empty(){
        return head == null;
    }
}

@SuppressWarnings("unchecked")

class Qarray<T> extends LinkedList<T>{

    int head;   //Marca la posicion del elemento a desencolar
    int tail;   //Marca la posicion del elemento a encolar
    private T[] qarray; //declaracion del array que hará de cola
    private int count;

    Qarray(int i){                       //el array por defecto tiene head y tail = 0, la cola tendrá i espacios para elementos
        head = 0;
        tail = 0;
        count = 0;
        qarray = (T[]) new Object[i];
    }

    Qarray(){
        this(4);                       //Constructor base para una cola de 4 elementos
    }

    @Override
    public void pushBack(T element){
        if(full())
        throw new RuntimeException("La cola ya está llena");        
        
        qarray[tail] = element;          //Coloca el elemento en el tail de la cola                          //Suma 1 al tail para que ahora esté asignado en la siguinte casilla de la cola
        tail = (tail+1) % qarray.length;
        count++;     //En caso de que el tail sea igual o mas grande que la length de la cola, este sera pasado por un modulo,
    }                                    //es decir, si tail y length = 4, el modulo hará a tail 0, si el tail es 5, pasará a ser 1

    @Override
    public T popFront(){
        T item = null;
        if(empty())
            throw new RuntimeException("La cola ya está vacía");
        item = qarray[head];  //El head apunta al elemento que desea desencolar, por eso ahora se hace null                                       //Se suma 1 al head para que sepa que elemento sigue en caso de desencolar
        head = (head+1) % qarray.length; 
        count--;    //En caso de que el head sea igual o mas grande que la length de la cola, este será pasado por un modulo,
        return item;             //es decir, 
    }

    public boolean full(){
        return count >= qarray.length;
    }

    @Override 
    public boolean empty(){
        return count <= 0;
    }

    public T get(int i){
        if(i >= qarray.length || i<0)
            throw new RuntimeException("Índice inaccesible");
        return qarray[i];
    }
}