import java.util.Scanner; // Necesario para leer la entrada del usuario

public class Main {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        Scanner scanner = new Scanner(System.in);
        int opcionNum; // Cambiado a int para evitar parseo repetido si es válido
        String entradaOpcion;

        System.out.println("==========================================");
        System.out.println("  GESTOR DE ÁRBOL AVL DINÁMICO - CONSOLA  ");
        System.out.println("==========================================");

        do {
            System.out.println("\nMenú de Opciones:");
            System.out.println("1. Insertar número");
            System.out.println("2. Eliminar número");
            System.out.println("3. Vaciar el árbol completo");
            System.out.println("4. Mostrar árbol actual");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción (1-5): ");

            entradaOpcion = scanner.nextLine().trim();
            opcionNum = 0; // Resetear para manejar entradas no numéricas

            try {
                opcionNum = Integer.parseInt(entradaOpcion);
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Opción no válida. Por favor, ingrese un número del menú.");
                // opcionNum permanece 0, el switch lo manejará en default
            }

            switch (opcionNum) {
                case 1:
                    System.out.print("Ingrese el número a insertar: ");
                    String entradaInsertar = scanner.nextLine().trim();
                    try {
                        int numeroInsertar = Integer.parseInt(entradaInsertar);
                        arbol.insertar(numeroInsertar);
                    } catch (NumberFormatException e) {
                        System.out.println("¡Error! Entrada no válida. Debe ser un número entero.");
                    }
                    break;
                case 2:
                    if (arbol.raiz == null) {
                        System.out.println("El árbol está vacío. No hay nada que eliminar.");
                        arbol.printTree(); // Muestra el árbol vacío
                        break;
                    }
                    System.out.print("Ingrese el número a eliminar: ");
                    String entradaEliminar = scanner.nextLine().trim();
                    try {
                        int numeroEliminar = Integer.parseInt(entradaEliminar);
                        arbol.eliminar(numeroEliminar);
                    } catch (NumberFormatException e) {
                        System.out.println("¡Error! Entrada no válida. Debe ser un número entero.");
                    }
                    break;
                case 3:
                    arbol.vaciarArbol();
                    break;
                case 4:
                    System.out.println("\nEstado actual del árbol:");
                    arbol.printTree();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    if (!entradaOpcion.isEmpty() && opcionNum == 0 && !esNumero(entradaOpcion)) {
                        // Si la entrada no estaba vacía, no fue parseable a int y el switch llegó a default
                        // (el mensaje de error de parseo ya se mostró)
                    } else if (opcionNum != 0) { // Solo si fue un número fuera de rango
                        System.out.println("¡Error! Opción no válida. Elija un número entre 1 y 5.");
                    }
                    // Si opcionNum es 0 por un error de parseo previo, no se imprime doble mensaje.
                    break;
            }

        } while (opcionNum != 5);

        scanner.close();
        System.out.println("Programa finalizado.");
    }

    // Pequeña función helper para el switch default
    private static boolean esNumero(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}