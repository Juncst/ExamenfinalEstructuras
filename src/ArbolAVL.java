public class ArbolAVL {
    Nodo raiz;
//Creamos metodos importantes para el funcionamiento del arbol
    private int getAltura(Nodo nodo) { 
        if (nodo == null) return 0;
        return nodo.altura;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b; //Esta funcion nos ayudara a si a es mayor que b entonces dame a, sino dame b 
    }

    private int getFactorBalance(Nodo nodo) {
        if (nodo == null) return 0;
        return getAltura(nodo.izquierda) - getAltura(nodo.derecha); //aqui calculamos el factor balance del nodo que seria la diferencia de hijo izquierdo y derecho
    }

    private Nodo rotarDerecha(Nodo y) {
        Nodo x = y.izquierda;
        Nodo T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;

        y.altura = max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        x.altura = max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;

        return x;
    }

    private Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.derecha;
        Nodo T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura = max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        y.altura = max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;

        return y;
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor); //Este metodo nos ayuda a insertar el valor en el nodo correspondiente
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) return new Nodo(valor);

        if (valor < nodo.valor) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = insertarRecursivo(nodo.derecha, valor);
        } else {
            return nodo;
        }

        nodo.altura = 1 + max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
        int balance = getFactorBalance(nodo);

        if (balance > 1 && valor < nodo.izquierda.valor) return rotarDerecha(nodo);
        if (balance < -1 && valor > nodo.derecha.valor) return rotarIzquierda(nodo);
        if (balance > 1 && valor > nodo.izquierda.valor) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && valor < nodo.derecha.valor) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private Nodo eliminarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) return nodo;

        if (valor < nodo.valor) {
            nodo.izquierda = eliminarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = eliminarRecursivo(nodo.derecha, valor);
        } else {
            if (nodo.izquierda == null || nodo.derecha == null) {
                Nodo temp = (nodo.izquierda != null) ? nodo.izquierda : nodo.derecha;
                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                Nodo temp = encontrarMinimo(nodo.derecha);
                nodo.valor = temp.valor;
                nodo.derecha = eliminarRecursivo(nodo.derecha, temp.valor);
            }
        }

        if (nodo == null) return nodo;

        nodo.altura = 1 + max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
        int balance = getFactorBalance(nodo);

        if (balance > 1 && getFactorBalance(nodo.izquierda) >= 0) return rotarDerecha(nodo);
        if (balance > 1 && getFactorBalance(nodo.izquierda) < 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && getFactorBalance(nodo.derecha) <= 0) return rotarIzquierda(nodo);
        if (balance < -1 && getFactorBalance(nodo.derecha) > 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
    while (nodo.izquierda != null) {
        nodo = nodo.izquierda;
        }
        return nodo;
    }

    public void vaciarArbol() { //metodo para vaciar por completo el arbol
        raiz = null;
    }

    public void printTree() { //este metodo nos ayuda a imprimir el arbol en la terminal
        if (raiz == null) {
            System.out.println("(Árbol vacío)");
        } else {
            printTreeRecursivo(raiz, 0, "R");
        }
        System.out.println("--------------------------------"); 
    }

    private void printTreeRecursivo(Nodo nodo, int nivel, String direccion) {//Este metodo nos ayudara a imprimir el arbol como estructura para poder visualizarlo en la terminal.
        if (nodo == null) return;

        printTreeRecursivo(nodo.derecha, nivel + 1, "/");

        for (int i = 0; i < nivel; i++) System.out.print("    "); //identación para que se vea mejor estructurado el arbol
        System.out.println(direccion + " " + nodo.valor);

        printTreeRecursivo(nodo.izquierda, nivel + 1, "\\"); 
    }
}
