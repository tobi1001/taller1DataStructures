public class colas {
    public static void main(String[] args){
        Qarray<Integer> cola1 = new Qarray<>();
        cola1.Enqueue(1);
        cola1.Enqueue(2);
        cola1.Enqueue(3);
        cola1.Enqueue(4);
        cola1.Dequeue();
        cola1.Dequeue();
        cola1.Enqueue(5);
        cola1.Enqueue(6);
        for(int i = 0; i <= 3;i++){
            System.out.println(cola1.get(i));
        }
        System.out.println("-----------------");
        System.out.println(cola1.head);
        System.out.println(cola1.tail);
    }
}

@SuppressWarnings("unchecked")

class Qarray<T>{

    public int head;   //Marca la posicion del elemento a desencolar
    public int tail;   //Marca la posicion del elemento a encolar
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

    public void Enqueue(T element){
        if(full())
        throw new RuntimeException("La cola ya está llena");        
        
        qarray[tail] = element;          //Coloca el elemento en el tail de la cola                          //Suma 1 al tail para que ahora esté asignado en la siguinte casilla de la cola
        tail = (tail+1) % qarray.length;
        count++;     //En caso de que el tail sea igual o mas grande que la length de la cola, este sera pasado por un modulo,
    }                                    //es decir, si tail y length = 4, el modulo hará a tail 0, si el tail es 5, pasará a ser 1

    public T Dequeue(){
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

    public boolean empty(){
        return count <= 0;
    }

    public T get(int i){
        if(i >= qarray.length || i<0)
            throw new RuntimeException("Índice inaccesible");
        return qarray[i];
    }
}