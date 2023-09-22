public class pilas{
    public static void main(String[] args){
        Sarray<Integer> pila1= new Sarray<>();
        pila1.push(1); // se pushean numeros del 1 al 4, limite de la pila
        pila1.push(2); //cambio ligero para el push para github
        pila1.push(3); //version prueba
        pila1.push(4);
        System.out.print(pila1.peek());
    }
}

@SuppressWarnings("unchecked")

class Sarray<T>{

    private int top;//indica la posicion en donde se insertará un elemento
    private T[] sarray;


    Sarray(int i){
        top = 0;//la posicion inicial de los elementos será 0
        sarray = (T[]) new Object[i];//lista con tamaño en el argumento del constructor
    }

    Sarray(){
        this(4);//el constructor sin argumentos hará a la lista de tamaño 4
    }

    public void push(T element){
        if(full())
            throw new RuntimeException("La pila ya está llena");
        sarray[top] = element;//se llena la posicion top
        top++;  //se incrementa un espacio
    }

    public T pop(){
        if(empty())
            throw new RuntimeException("La pila ya está vacía");
        top--;              //se reduce el top
        return sarray[top]; //retorna el elemento que quitamos, no es necesario hacerlo null ya que al hacer un push por ejemplo, este será reemplazado (-operaciones, + eficiencia)
    }

    public boolean full(){
        return top >= sarray.length; //el top solo puede ser igual al length, el ">" es solo una medida preventiva
    }

    public boolean empty(){
        return top <= 0;//el top al ser 0 es vacio ya que es la posicion inicial del array
    }

    public T peek(){
        return sarray[top-1];
    }
}
