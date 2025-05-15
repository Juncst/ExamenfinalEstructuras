import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar número");
            System.out.println("2. Eliminar número");
            System.out.println("3. Vaciar árbol");
            System.out.println("4. Mostrar árbol");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Ingrese un número válido: ");
                scanner.next();
            }

            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Número a insertar: ");
                    arbol.insertar(scanner.nextInt());
                    break;
                case 2:
                    System.out.print("Número a eliminar: ");
                    arbol.eliminar(scanner.nextInt());
                    break;
                case 3:
                    arbol.vaciarArbol();
                    break;
                case 4:
                    arbol.printTree();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        scanner.close();
    }
}
