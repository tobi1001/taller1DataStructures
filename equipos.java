import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class equipos {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        int scenario = 1;
        while(true){
            int t = Integer.parseInt(br.readLine().trim());
            if(t == 0)
                break;
            StringBuilder output = new StringBuilder();
            output.append("Scenario #").append(scenario++).append("\n");
            procesarCaso(br, t, output);
            System.out.println(output);
        }
    }    
                 
            
private static void procesarCaso(BufferedReader br, int t, StringBuilder output) throws IOException{           
            Equipo[] equipos = new Equipo[t];  
            for(int i = 0; i<t;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int tamanoCola = Integer.parseInt(st.nextToken());
                Equipo equipo = new Equipo(i, tamanoCola);
                for(int j = 0; j< tamanoCola; j++){
                    int tarea = Integer.parseInt(st.nextToken());
                    equipo.cola_entrada.pushBack(tarea);
                }
                equipos[i] = equipo;
            }
            String comando;
            LinkedList lista_tareas = new LinkedList();
            while(!(comando = br.readLine()).equals("STOP")){
                if(comando.startsWith("ENQUEUE")){
                    int elemento = Integer.parseInt((comando.substring(8).trim()));
                    for(int i = 0; i<t;i++){
                        if(lista_tareas.empty() & (true == equipos[i].cola_entrada.find(elemento))){
                            lista_tareas.pushBack(elemento);
                            equipos[i].current = lista_tareas.head;
                            equipos[i].count++;
                            break;
                            
                        }
                        else if(true == equipos[i].cola_entrada.find(elemento) & equipos[i].current.key == -1){
                            lista_tareas.pushBack(elemento);
                            equipos[i].current = lista_tareas.tail; 
                            equipos[i].count++;
                            break;
                            
                        }
                        else if(true == equipos[i].cola_entrada.find(elemento) & equipos[i].current.key != -1){
                            lista_tareas.addAfter(equipos[i].current, elemento);
                            equipos[i].current = equipos[i].current.next;
                            equipos[i].count++;
                            break;
                        }                       
                        }
                    }
                if(comando.equals("DEQUEUE")){
                    int eliminado = 0;
                    for(int i = 0; i<t; i++){
                        if(equipos[i].cola_entrada.find(lista_tareas.head.key) == true){
                        eliminado = lista_tareas.popFront();
                        equipos[i].count--;
                        output.append(eliminado).append("\n");  
                        if(equipos[i].count == 0){
                            equipos[i].current = new Node(-1);
                        }
                        break;
                        }                               
                    }                     
                }
            }   
             
    }
}

class Equipo{

    int numero;
    LinkedList cola_entrada;
    Node current;
    int count;

    public Equipo(int i, int k){
        numero = i;
        cola_entrada = new LinkedList();
        current = new Node(-1);
        count = 0;
    }

}

class Node{
    
    int key;
    Node next;

    Node(int key){
        this.key = key;
        this.next = null;
    }
}

class LinkedList{

    Node head;
    Node tail;

    public LinkedList(){
        head = tail = null;
    }

    public void pushFront(int element){
        Node nodo = new Node(element);         //Creación del nodo
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
        }   //Caso por si la lista enlazada tenia solo 1 elemento y lo eliminamos            
        return p.key;
    }

    public void pushBack(int element){
        Node nodo = new Node(element);
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

    public int popBack(){
        if(tail == null)
            throw new RuntimeException("La lista ya está vacía");
        if(head == tail){
            head = tail = null;
            return 0;
        }
        else{
        Node p = tail;
        Node current = head;
        while(current.next.next != null){
            current = current.next;
        }
        current.next = null;
        tail = current;
        return p.key;
        }
    }

    public void addAfter(Node current, int value){
        if(empty())
            throw new RuntimeException("La lista ya está vacía, no se puede añadir después de nada");
        Node nodo = new Node(value);
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

    public boolean find(int keyToFind) {
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

class Qarray extends LinkedList{

    int head;   //Marca la posicion del elemento a desencolar
    int tail;   //Marca la posicion del elemento a encolar
    int[] qarray; //declaracion del array que hará de cola
    private int count;

    Qarray(int i){                       //el array por defecto tiene head y tail = 0, la cola tendrá i espacios para elementos
        head = 0;
        tail = 0;
        count = 0;
        qarray = new int[i];
    }

    Qarray(){
        this(4);                       //Constructor base para una cola de 4 elementos
    }

    @Override
    public void pushBack(int element){
        if(full())
        throw new RuntimeException("La cola ya está llena");        
        
        qarray[tail] = element;          //Coloca el elemento en el tail de la cola                          //Suma 1 al tail para que ahora esté asignado en la siguinte casilla de la cola
        tail = (tail+1) % qarray.length;
        count++;     //En caso de que el tail sea igual o mas grande que la length de la cola, este sera pasado por un modulo,
    }                                    //es decir, si tail y length = 4, el modulo hará a tail 0, si el tail es 5, pasará a ser 1

    @Override
    public int popFront(){
        int item = -1;
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

    public int gethead(){
        return qarray[head];
    }
}