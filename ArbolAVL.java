public class ArbolAVL {
    Nodo raiz;
    private boolean valorFueEliminado = false; // Flag para mensajes de eliminación

    public ArbolAVL() {
        this.raiz = null;
    }

    // Método para obtener la altura de un nodo
    private int getAltura(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    // Método para obtener el máximo de dos enteros
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Método para obtener el factor de balance de un nodo
    private int getFactorBalance(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.izquierda) - getAltura(nodo.derecha);
    }

    // Rotación simple a la derecha
    private Nodo rotarDerecha(Nodo y) {
        System.out.println("INFO: Ejecutando Rotación Derecha en el nodo con valor: " + y.valor);
        Nodo x = y.izquierda;
        Nodo T2 = x.derecha;

        x.derecha = y;
        y.izquierda = T2;

        y.altura = max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
        x.altura = max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;

        return x;
    }

    // Rotación simple a la izquierda
    private Nodo rotarIzquierda(Nodo x) {
        System.out.println("INFO: Ejecutando Rotación Izquierda en el nodo con valor: " + x.valor);
        Nodo y = x.derecha;
        Nodo T2 = y.izquierda;

        y.izquierda = x;
        x.derecha = T2;

        x.altura = max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
        y.altura = max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;

        return y;
    }

    // Método público para insertar un valor en el árbol
    public void insertar(int valor) {
        System.out.println("\n-> Insertando valor: " + valor);
        this.raiz = insertarRecursivo(this.raiz, valor);
        System.out.println("Árbol después de insertar " + valor + ":");
        printTree();
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierda = insertarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = insertarRecursivo(nodo.derecha, valor);
        } else {
            System.out.println("INFO: El valor " + valor + " ya existe. No se insertará.");
            return nodo;
        }

        nodo.altura = 1 + max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
        int factorBalance = getFactorBalance(nodo);

        if (factorBalance > 1 && valor < nodo.izquierda.valor) { // LL
            System.out.println("INFO: Desbalance (LL) tras inserción en nodo " + nodo.valor);
            return rotarDerecha(nodo);
        }
        if (factorBalance < -1 && valor > nodo.derecha.valor) { // RR
            System.out.println("INFO: Desbalance (RR) tras inserción en nodo " + nodo.valor);
            return rotarIzquierda(nodo);
        }
        if (factorBalance > 1 && valor > nodo.izquierda.valor) { // LR
            System.out.println("INFO: Desbalance (LR) tras inserción en nodo " + nodo.valor);
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }
        if (factorBalance < -1 && valor < nodo.derecha.valor) { // RL
            System.out.println("INFO: Desbalance (RL) tras inserción en nodo " + nodo.valor);
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    private Nodo encontrarMinimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        return actual;
    }

    public void eliminar(int valor) {
        System.out.println("\n-> Intentando eliminar valor: " + valor);
        valorFueEliminado = false; // Resetear la bandera antes de la operación
        this.raiz = eliminarRecursivo(this.raiz, valor);

        if (valorFueEliminado) {
            System.out.println("INFO: Valor " + valor + " eliminado exitosamente.");
        } else if (this.raiz != null) { // Si no se eliminó pero el árbol no está vacío (valor no encontrado)
             System.out.println("INFO: Valor " + valor + " no encontrado en el árbol.");
        } else if (!valorFueEliminado && this.raiz == null) { // Si no se eliminó y el árbol está vacío
             System.out.println("INFO: Valor " + valor + " no encontrado (árbol vacío o valor no existente).");
        }
        
        System.out.println("Árbol después del intento de eliminar " + valor + ":");
        printTree();
    }


    private Nodo eliminarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            // valorFueEliminado se mantiene false si llegamos aquí
            return null;
        }

        if (valor < nodo.valor) {
            nodo.izquierda = eliminarRecursivo(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = eliminarRecursivo(nodo.derecha, valor);
        } else {
            // Nodo encontrado
            valorFueEliminado = true; // Marcar que el valor fue encontrado para eliminación
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

        if (nodo == null) {
            return null;
        }

        nodo.altura = 1 + max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
        int factorBalance = getFactorBalance(nodo);

        if (factorBalance > 1 && getFactorBalance(nodo.izquierda) >= 0) { // LL o L-Balanceado
            System.out.println("INFO: Desbalance (LL) tras eliminación en nodo " + nodo.valor);
            return rotarDerecha(nodo);
        }
        if (factorBalance > 1 && getFactorBalance(nodo.izquierda) < 0) { // LR
            System.out.println("INFO: Desbalance (LR) tras eliminación en nodo " + nodo.valor);
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }
        if (factorBalance < -1 && getFactorBalance(nodo.derecha) <= 0) { // RR o R-Balanceado
            System.out.println("INFO: Desbalance (RR) tras eliminación en nodo " + nodo.valor);
            return rotarIzquierda(nodo);
        }
        if (factorBalance < -1 && getFactorBalance(nodo.derecha) > 0) { // RL
            System.out.println("INFO: Desbalance (RL) tras eliminación en nodo " + nodo.valor);
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    public void vaciarArbol() {
        this.raiz = null;
        System.out.println("\nINFO: El árbol ha sido vaciado.");
        printTree();
    }

    public void printTree() {
        if (raiz == null) {
            System.out.println("(Árbol vacío)");
            System.out.println("------------------------------------");
            return;
        }
        System.out.println("Representación del Árbol AVL:");
        printTreeRecursivo(this.raiz, 0);
        System.out.println("------------------------------------");
    }

    private void printTreeRecursivo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        printTreeRecursivo(nodo.derecha, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("    "); // 4 espacios por nivel para indentación
        }
        System.out.println(nodo.valor);
        printTreeRecursivo(nodo.izquierda, nivel + 1);
    }
}