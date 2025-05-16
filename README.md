# Proyecto Árbol AVL en Java

## 📌 Descripción

Este proyecto implementa un **Árbol AVL** en Java, un tipo especial de árbol binario de búsqueda que se auto-balancea después de cada inserción o eliminación. Permite insertar números y visualizar el árbol balanceado en consola con estructura de niveles.

## 💡 Problema que resuelve

Los árboles binarios de búsqueda comunes pueden desequilibrarse, aumentando el tiempo de búsqueda. Un árbol AVL mantiene su altura mínima y garantiza operaciones eficientes (O(log n)).

## 📁 Estructura del proyecto
```markdown
| Ruta                     | Descripción                              |
|--------------------------|------------------------------------------|
| ExamenFinalEstructuras/  | Directorio principal del proyecto        |
| ├── Nodo.java            | Clase del nodo del árbol                 |
| ├── ArbolAVL.java        | Implementación completa del árbol AVL    |
| └── Main.java            | Archivo Main, con menú de elección       |
|---------------------------------------------------------------------|

```
## ▶️ Cómo compilar y ejecutar
```markdown
1. Tener Java JDK Instalado
2. Clona el repositorio de github
3. Compila con el siguiente codigo en la terminal: javac -d bin src/*.java
4. Ejecuta el main con el siguiente codigo en la terminal: java -cp bin Main
```
## 🎥 Enlace al video explicativo
```markdown
YouTube - Explicación Árbol AVL en Java Link --> 
```


## 🌳 Ejemplos de Operaciones AVL
```markdown
Caso #1:
Valores:[30, 20, 10]
 
Árbol Resultante:

    20
   /  \
 10    30



Caso #2:
valores = {30, 20, 40, 10, 25, 35, 50, 5};

Árbol Resultante:

       30
     /    \
   20      40
  /  \    /  \
10   25 35   50
 /
5


Caso #3:

valores = {15, 10, 20, 8, 12, 17, 25, 6};

Árbol Balanceado:

       15
     /    \
   10      20
  /  \    /  \
8    12 17   25
/
6

Caso #4:

valores = {50, 30, 70, 20, 40, 60, 80, 35};

Árbol Balanceado:
        50
      /    \
    40      70
   /  \    /  \
 30   35 60   80
 /
20

