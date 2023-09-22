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

    Qarray(int i){                       //el array por defecto tiene head y tail = 0, la cola tendrá i espacios para elementos
        head = 0;
        tail = 0;
        qarray = (T[]) new Object[i];
    }

    Qarray(){
        this(4);                       //Constructor base para una cola de 4 elementos
    }

    public void Enqueue(T element){
        if(full())
            throw new RuntimeException("La cola ya está llena");
            
        qarray[tail] = element;          //Coloca el elemento en el tail de la cola
        tail++;                          //Suma 1 al tail para que ahora esté asignado en la siguinte casilla de la cola
        tail = tail % qarray.length;     //En caso de que el tail sea igual o mas grande que la length de la cola, este sera pasado por un modulo,
    }                                    //es decir, si tail y length = 4, el modulo hará a tail 0, si el tail es 5, pasará a ser 1

    public T Dequeue(){
        if(empty())
            throw new RuntimeException("La cola ya está vacía");

        qarray[head] = null;             //El head apunta al elemento que desea desencolar, por eso ahora se hace null             
        head++;                          //Se suma 1 al head para que sepa que elemento sigue en caso de desencolar
        head = head % qarray.length;     //En caso de que el head sea igual o mas grande que la length de la cola, este será pasado por un modulo,
        return qarray[head];             //es decir, 
    }

    public boolean full(){
        return (head % qarray.length == tail % qarray.length) & qarray[head] != null;
    }

    public boolean empty(){
        return (head % qarray.length == tail % qarray.length) & qarray[head] == null;
    }

    public T get(int i){
        if(i >= qarray.length || i<0)
            throw new RuntimeException("Índice inaccesible");
        return qarray[i];
    }
}