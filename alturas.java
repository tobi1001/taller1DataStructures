import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class alturas{
    public static void main(String[] args) throws IOException{
        //BufferedReader para leer el tamaño de la entrada
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Se hace int la entrada y se establecen los tamaños de las listas
        int n = Integer.parseInt(br.readLine());
        int[] alturas = new int[n];
        int[] resultados = new int[n];

        Sarray<Integer> pila1 = new Sarray<>(n); 
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){
            alturas[i] = Integer.parseInt(st.nextToken());
            while(!pila1.empty() && alturas[i] <= alturas[pila1.peek()]){
                pila1.pop();
            }
            if(!pila1.empty()){
                resultados[i] = pila1.peek() + 1;
            }
            else{
                resultados[i] = 0;
            }
            pila1.push(i);
        }

        for (int i=0; i<n; i++){
            System.out.print(resultados[i]);
            if(i<n-1){
                System.out.print(" ");
            }
        }
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
        if(!empty()){
            return sarray[top-1]; //indica que posicion es la última en la pila
        }
        else{
            return sarray[0];     //en caso de estar vacía retorna la primera posición
        }
    }

}