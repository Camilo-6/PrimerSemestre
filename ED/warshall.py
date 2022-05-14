"""
ejemplo de funcionamiento:

Introduce el numero de filas: 2
Introduce el numero de columnas: 3
1 0 1 0 1 1

Original

1 0 1
0 1 1

Transitiva

1 0 1
0 1 1

"""
import numpy as np

def warshall(a):
    assert (len(row) == len(a) for row in a)
    n = len(a)
    for k in range(n):
        for i in range(n):
            for j in range(n):
                a[i][j] = a[i][j] or (a[i][k] and a[k][j])
    return a

def mostrar(e):
    print()
    print("Original")
    print()
    for line in e:
        print(*line)
    patito = warshall(e)
    print()
    print("Transitiva")
    print()
    for line in patito:
        print(*line)
    print()

def funcionar():
    print()
    print("---------Bienvenido al programa de Camilo---------\npara obtener la clausula transitiva de una relacion")
    print()
    input("..........Presione una tecla para empezar..........")
    print()
    print("Primero veamos la matriz de la relacion")
    print()
    R = int(input("Introduce el numero de filas: "))
    C = int(input("Introduce el numero de columnas: "))
    print()
    if R <= 0 or C <= 0:
        print("No puedes usar 0 o negativos como fila o columna, adios")
    else:
        print("Introduzca las entradas en una sola línea (separadas por espacios):")
        entries = list(map(int, input().split()))
        matrix = np.array(entries).reshape(R, C)
        mostrar(matrix)

funcionar()