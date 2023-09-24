import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class equipos{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        int scenario = 1;
        while(true){
            int t = Integer.parseInt(br.readLine().trim());
            //en caso de t = 0, el codigo se pausa, valorando los casos de prueba que se le hayan dado
            if(t == 0)
                break;
            StringBuilder output = new StringBuilder();
            output.append("Scenario #").append(scenario++).append("\n");
            procesarCaso(br, t, output);
            System.out.println(output);
        }
    }    
                          
    private static void procesarCaso(BufferedReader br, int t, StringBuilder output) throws IOException{      
            //lista con todos los equipos     
            Equipo[] equipos = new Equipo[t];  
            for(int i = 0; i<t;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int tamanoCola = Integer.parseInt(st.nextToken());
                //por cada equipo se llena un constructor 
                Equipo equipo = new Equipo(i, tamanoCola);      
                //Las tareas se acumulan una en una en la cola_entrada de cada equipo         
                for(int j = 0; j< tamanoCola; j++){
                    int tarea = Integer.parseInt(st.nextToken());
                    equipo.getColaEntrada().pushBack(tarea);
                }
                //se coloca el equipo en el arreglo estático de equipos
                equipos[i] = equipo;
            }
            String comando;
            LinkedList lista_tareas = new LinkedList();
            while(!(comando = br.readLine()).equals("STOP")){
                //Procedimiento para los enqueues
                if(comando.startsWith("ENQUEUE")){
                    int elemento = Integer.parseInt((comando.substring(8).trim()));
                    //para cada equipo se revisaran los casos:
                    for(int i = 0; i<t;i++){
                        //Si el elemento está en uno de los equipos y no hay elementos del equipo en la lista
                        if(true == equipos[i].getColaEntrada().findboolean(elemento)){
                            if(equipos[i].getCurrent().getKey() == -1){
                                lista_tareas.pushBack(elemento);
                                //Se hace al elemento el ultimo de la lista, y el current del equipo es el nuevo nodo colocado al final de la lista
                                equipos[i].setCurrent(lista_tareas.getTail());
                            }
                            //Si el elemento está en uno de los equipos y hay elementos del equipo en la lista   
                            else{  
                                lista_tareas.addAfter(equipos[i].getCurrent(), elemento);
                                //El current establece el ultimo nodo del equipo
                                equipos[i].setCurrent(equipos[i].getCurrent().getNext());
                            }                  
                            equipos[i].setCount(1);
                            break;                          
                        }                                         
                    }
                }
                //Procedimiento para los dequeues
                if(comando.equals("DEQUEUE")){
                    int eliminado = 0;
                    for(int i = 0; i<t; i++){
                        //Se busca por cada equipo si la cola contiene el elemento que se desea eliminar
                        if(equipos[i].getColaEntrada().findboolean(lista_tareas.getHead().getKey()) == true){
                        eliminado = lista_tareas.popFront();
                        //Se decrementa el count cuya responsabilidad es saber si el equipo tiene un elemento en la lista
                        equipos[i].setCount(-1);
                        output.append(eliminado).append("\n");
                        //en caso de que el count sea 0, se coloca el nodo estandar de current, o sea con una key = -1 y next = null  
                        if(equipos[i].getCount() == 0){
                            equipos[i].setCurrent(new Node(-1));
                        }
                        //en caso de encontrar el elemento deseado y sacarlo de la lista, se rompe el ciclo
                        break;
                    }                               
                }                     
            }
        }               
    }
}

class Equipo{
    //Cada equipo cuenta con una cola de tareas, un nodo current que indica el ultimo nodo del equipo colocado en la lista de tareas global
    //y un count que indica la cantidad de tareas del equipo en la cola global
    private LinkedList cola_entrada;
    private Node current;
    private int count;

    public Equipo(int i, int k){

        cola_entrada = new LinkedList();
        current = new Node(-1);
        count = 0;
    }

    public LinkedList getColaEntrada(){
        return cola_entrada;
    }

    public Node getCurrent(){
        return current;
    }

    public void setCurrent(Node n){
        current = n;
    }

    public void setCount(int n){
        count = count + n;
    }

    public int getCount(){
        return count;
    }
}

class Node{
    
    int key;
    Node next;

    Node(int key){
        this.key = key;
        this.next = null;
    }

    public void setKey(int key){
        this.key = key;
    }

    public void setNext(Node n){
        this.next = n;
    }

    public int getKey(){
        return this.key;
    }

    public Node getNext(){
        return this.next;
    }
}

class LinkedList{

    Node head;
    Node tail;

    public void setHead(Node n){
        head = n;
    }

    public void setTail(Node n){
        tail = n;
    }

    public Node getHead(){
        return head;
    }

    public Node getTail(){
        return tail;
    }

    public LinkedList(){
        head = tail = null;
    }

    public void pushFront(int element){
        Node nodo = new Node(element);              //Creación del nodo
        nodo.key = element;                         //La llave del nodo se asigna al valor pasado a la función
        nodo.next = head;                           //La direccion del head pasará a ser el next del nodo que pusheamos
        head = nodo;                                //Ahora el head será el nodo que pusheamos...si se hiciera al reves el nodo que ahora es nuestro head no tendría un next 
    }

    public int popFront(){
        if(head == null)
            throw new RuntimeException("La lista enlazada está vacía"); //En caso de que la lista esté vacía
        Node p = head;
        head = head.next;   //La cabeza ahora será el nodo siguiente                                                             
        if(head == null){
            tail = null;
        }                   //Caso por si la lista enlazada tenia solo 1 elemento y lo eliminamos            
        return p.key;
    }

    public void pushBack(int element){
        Node nodo = new Node(element); //se hace un nuevo nodo
        nodo.key = element;
        nodo.next = null;
        if(tail == null){              //caso por si la lista estaba vacía
            head = nodo;
            tail = nodo;
        }
        else{
            tail.next = nodo;          //si no está vacía
            tail = nodo;
        }
    }

    public int popBack(){
        if(tail == null)
            throw new RuntimeException("La lista ya está vacía");
        if(head == tail){           //si solo hay un elemento, se hace la lista vacía
            head = tail = null;
            return 0;
        }
        else{
        Node p = tail;
        Node current = head; 
        //Se busca el nodo hasta encontrar el elemento anterior al tail       
        while(current.next.next != null){
            current = current.next;
        }
        //una vez encontrado el current se hace a este el tail
        current.next = null;
        tail = current;
        return p.key;
        }
    }

    public void addAfter(Node current, int value){
        if(empty())
            throw new RuntimeException("La lista ya está vacía, no se puede añadir después de nada");
        Node nodo = new Node(value);
        //Creado el nodo, se copia el next del nodo que deseamos sea anterior a nuestro nuevo nodo, y hacemos el next del nodo anterior nuestro nodo nuevo
        nodo.next = current.next;
        current.next = nodo;
        if(tail == current)
            tail = nodo;         
    }

    public int erase(int keyToRemove) {
    if (head == null) {
        throw new RuntimeException("La lista está vacía");
    }

    if (head.key == keyToRemove) {
        int removedValue = head.key;
        head = head.next;
        return removedValue;
    }

    Node current = head;
    while (current.next != null) {
        if (current.next.key == keyToRemove) {
            int removedValue = current.next.key;
            current.next = current.next.next;
            return removedValue;
        }
        current = current.next;
    }

    throw new RuntimeException("Elemento no encontrado");
}

    public int findint(int keyToFind) {
    Node current = head;
    while (current != null) {
        if (current.key == keyToFind) {
            return current.key; // Elemento encontrado, retorna el valor
        }
        current = current.next;
    }
    return -1; // Elemento no encontrado, retorna -1 como valor especial
    }

    public boolean findboolean(int keyToFind) {
    Node current = head;
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