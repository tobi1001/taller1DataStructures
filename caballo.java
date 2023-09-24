import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class caballo {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String linea;
        while((linea = br.readLine()) != null && !linea.isEmpty()){
            String inicio = linea.substring(0, 2);
            String finall = linea.substring(3,5);

            int moves = movKnight(inicio, finall);
            StringBuilder output = new StringBuilder();
            output.append("To get from "+ inicio + " to " + finall + " takes " + moves + " knight moves.").append("\n");
            System.out.print(output.toString());
        }
    }

    public static int movKnight(String inicio, String finall){
        int[] desp_filas = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] desp_columnas = {1, 2, 2, 1, -1, -2, -2, -1};
        //inicializamos el tablero en (0,0) y finaliza en (7,7)
        int filainicial = inicio.charAt(1) - '1';
        int colinicial = inicio.charAt(0) - 'a';
        int filafin = finall.charAt(1) - '1';
        int colfin = finall.charAt(0) - 'a';
        //se hace el tablero y una cola con coordenadas
        boolean[][] visitado = new boolean[8][8];
        Qarray<int[]> cola_casillas = new Qarray<>();
        //la cola se llena con la posicion inicial
        cola_casillas.enqueue(new int[]{filainicial, colinicial});
        visitado[filainicial][colinicial] = true;
        int movimientos = 0;
        while(!cola_casillas.empty()){
            //durante cada ciclo, la cola tiene todos los movimientos posibles que se pueden hacer desde una posicion
            int size = cola_casillas.size();
            for(int i = 0; i < size ;i++){
                //cada ciclo quita la posicion que se va a tratar, ahorrando memoria en la cola, por cada movimiento realizado se presenta un abanico de movimientos distintos
                //es una busqueda de anchura a través de todo el tablero
                int[] actual = cola_casillas.dequeue();
                int fila = actual[0];
                int columna = actual[1];
                //En caso de que la fila y columna en la que se estén sean las requeridas, retornará los movimientos
                if(fila == filafin && columna == colfin){
                    return movimientos;
                }
                //Sé hace un ciclo en el que se recorren puntos de tablero con los movimientos demarcados por el caballo de ajedrez
                for(int j = 0; j<8; j++){
                    int nueva_fila = fila + desp_filas[j];
                    int nueva_col = columna + desp_columnas[j];
                    //Se verifica que el caballo no sobrepase los limites del tablero y que no se haya visitado la casilla anteriormente
                    //en caso de haber visitado una casilla anteriormente, existe un cmaino más corto para llegar al punto deseado y no se encola nada
                    if((nueva_fila>=0 && nueva_fila < 8) && (nueva_col >=0 && nueva_col < 8) && !visitado[nueva_fila][nueva_col]){
                        //En caso de cumplir los requerimientos se encola la nueva posicion y se marca como true la posicion nueva
                        cola_casillas.enqueue(new int[]{nueva_fila,nueva_col});
                        visitado[nueva_fila][nueva_col] = true;
                    }
                }
            }
            //Una vez finalizado el movimiento más óptimo, se suma uno a los movimientos totales
            movimientos++;
        }
        return -1; //No se encontraron movimientos para llegar a la casilla  (No es posible)
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

    public int size(){
        return count;
    }

}