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
        int[] finalalturas = new int[n];
        //Pila que contendrá los mínimos a la izquierda de cada torre
        Sarray pila1 = new Sarray(n); 
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i<n; i++){
            alturas[i] = Integer.parseInt(st.nextToken());
            //En caso de la altura ser mayor o igual a la que tomamos en la comparación, sale de la pila
            while(alturas[i] <= alturas[pila1.peek()] && !pila1.empty()){
                pila1.pop();
            }
            //Si la pila no está vacía al final del ciclo se coloca el top en el array alturasfinales y se le suma 1
            if(!pila1.empty()){
                finalalturas[i] = pila1.peek() + 1;
            }
            //Si la pila termina completamente vacía se coloca un 0
            else{
                finalalturas[i] = 0;
            }
            //Se pushea el ultimo elemento comparado a la pila
            pila1.push(i);
        }
        StringBuilder resultadoStr = new StringBuilder();
        for (int i=0; i<n; i++){
            resultadoStr.append(finalalturas[i]+" ");
        }
        System.out.println(resultadoStr.toString());
    }
}

class Sarray{

    private int top;//indica la posicion en donde se insertará un elemento
    private int[] sarray;


    Sarray(int i){
        top = 0;//la posicion inicial de los elementos será 0
        sarray = new int[i];//lista con tamaño en el argumento del constructor
    }

    Sarray(){
        this(4);//el constructor sin argumentos hará a la lista de tamaño 4
    }

    public void push(int element){
        if(full())
            throw new RuntimeException("La pila ya está llena");
        sarray[top] = element;//se llena la posicion top
        top++;  //se incrementa un espacio
    }

    public int pop(){
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

    public int peek(){
        if(!empty()){
            return sarray[top-1]; //indica que posicion es la última en la pila
        }
        else{
            return sarray[0];     //en caso de estar vacía retorna la primera posición
        }
    }

}