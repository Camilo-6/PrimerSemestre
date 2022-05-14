def encripta_cadena(s):
    if len(s) == 1 or s == "":
        return s
    numerito = len(s) // 2
    if len(s) % 2 == 0:
        numerito -= 1
    return s[numerito] + encripta_cadena(s[:numerito]) + encripta_cadena(s[numerito+1:])

def es_primo(n):
    if n == 1:
        return False
    contador = n
    return es_primo_auxi(n, contador-1)

def es_primo_auxi(n, contador):
    if contador == 1:
        return True
    if (n%contador) == 0:
        return False
    return es_primo_auxi(n, contador-1)


def decimal_binario(n):
    if n == 0:
        return 0
    cadenita = ""
    cadenita = decimal_binario_auxi(n, cadenita)
    final = int(cadenita)
    return final

def decimal_binario_auxi(n, cadenita):
    if n == 0:
        return cadenita
    cadenita = str(n%2) + cadenita
    nuevo = int(n/2)
    return decimal_binario_auxi(nuevo, cadenita)

def suma_triangulo(lista):
    if len(lista) == 0:
        return lista
    if len(lista) == 1:
        return lista
    nueva = suma_tria_auxi1(lista)
    if len(nueva) != 1:
        nueva = suma_triangulo(nueva)
    nueva.append(lista)
    return nueva

def suma_tria_auxi1(lista):
    if len(lista) == 0 or len(lista) == 1:
        return lista
    nueva = []
    actual = 0
    return suma_tria_auxi2(lista, nueva, actual)

def suma_tria_auxi2(original, lista, actual):
    if actual == (len(original))-1:
        return lista
    agregar = original[actual+1] + original[actual]
    lista.append(agregar)
    return suma_tria_auxi2(original, lista, actual+1)

def suma_consecutivos(lista):
    if len(lista) == 0 or len(lista) == 1:
        return lista
    nueva = []
    actual = 0
    return suma_consecutivos_auxi(lista, nueva, actual)

def suma_consecutivos_auxi(original, lista, actual):
    if actual == (len(original))-1:
        return lista
    agregar = original[actual+1] + original[actual]
    lista.append(agregar)
    return suma_consecutivos_auxi(original, lista, actual+1)

def suma_potencias(numero, potencia):
    algo = str(numero)
    resultado = 0
    contador = 0
    resultado = suma_potencial_auxi(algo, potencia, contador, resultado)
    return resultado

def suma_potencial_auxi(algo, potencia, contador, resultado):
    if contador == len(algo):
        return resultado
    numerito = int(algo[contador])
    resultado = resultado + pow(numerito, potencia)
    return suma_potencial_auxi(algo, potencia, contador+1, resultado)

def oculta_caracter(caracter, veces):
    contador = 0
    return oculta_caracter_auxi(caracter, veces, contador)

def oculta_caracter_auxi(caracter, veces, contador):
    if contador >= veces:
        return caracter
    agregar = "()"
    caracter = agregar[:1] + caracter + agregar[1:]
    return oculta_caracter_auxi(caracter, veces, contador+1)

def numero_de_formas(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    if n == 2:
        return 2
    if n == 3:
        return 4
    return numero_de_formas(n-1) + numero_de_formas(n-2) + numero_de_formas(n-3)

def consonante_a_f(cadena):
    total = len(cadena)
    contador = 0
    return consonan_auxi(cadena, total, contador)

def consonan_auxi(cadena, total, contador):
    if contador >= total:
        return cadena
    wow = list(cadena)
    if (cadena[contador] == "a" or cadena[contador] == "e" or cadena[contador] == "i" or cadena[contador] == "o" or cadena[contador] == "u" or cadena[contador] == " "):
        return consonan_auxi(cadena, total, contador+1)
    wow[contador] = "f"
    cadena = "".join(wow)
    return consonan_auxi(cadena, total, contador+1)