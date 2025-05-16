public class Nodo {
    int valor;
    Nodo izquierda, derecha;
    int altura; //necesario para calcular el balanceo del arbol

    public Nodo(int valor) { //creamos el constructor
        this.valor = valor; //asignamos el valor que va a recibir la variable valor
        this.altura = 1; // siempre que un nodo nuevo se cree tendra como altura 1
    }
}
