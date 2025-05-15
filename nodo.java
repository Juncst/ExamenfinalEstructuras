public class Nodo {
    int valor;
    Nodo izquierda;
    Nodo derecha;
    int altura;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
        this.altura = 1; // La altura de un nuevo nodo (hoja) es 1
    }
}