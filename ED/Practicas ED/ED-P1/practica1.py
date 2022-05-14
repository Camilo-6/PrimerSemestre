#ejercicio1
def maximo_comun_divisor(x, y):
    if x == 0:
        return y
    elif y == 0:
        return x
    minimo = 0
    if x > y:
        divisor = y
    elif x < y:
        divisor = x
    else :
        divisor = x
    while divisor > 0:
        if x % divisor == 0 and y % divisor == 0:
            return divisor
        divisor -= 1

#ejercicio2
def es_palindroma(cadena):
    cadenita = cadena.lower()
    cadenita = cadenita.replace(" ","")
    cadena_comp = ""
    for i in range(len(cadenita)):
        cadena_comp = cadenita[i] + cadena_comp
    if cadenita == cadena_comp:
        return True
    else : return False

#ejercicio3
def promedio_tuplas(tuplas):
    listita = []
    for i in range(len(tuplas)):
        promedio = 0
        promedio = (sum(tuplas[i])/len(tuplas[i]))
        listita.append(promedio)
    return listita

#ejercicio4
def permutaciones(nums):
    if len(nums) == 0:
        return []
    elif len(nums) == 1:
        return [nums]
    else :
        listita = []
        for i in range(len(nums)):
            x = nums[i]
            xs = nums[:i] + nums[i+1:]
            for p in permutaciones(xs):
                listita.append([x]+p)
        return listita

#ejercicio5
def filtrar_pares(lista):
    listita = []
    lista_pares = filter((lambda x: x%2==0),lista)
    listita += lista_pares
    return listita

#ejercicio6
def frecuencia(cadena):
    palabritas = cadena.split()
    diccionario_frecuencia = {}
    for palabra in palabritas:
        if palabra in diccionario_frecuencia:
            diccionario_frecuencia[palabra] += 1
        else:
            diccionario_frecuencia[palabra] = 1
    listita = []
    for palabra in diccionario_frecuencia:
        veces = diccionario_frecuencia[palabra]
        listita.append((palabra,veces))
    return listita

#ejercicio7
def suma_digitos(num):
    sumita = 10
    while sumita > 9:
        sumita = 0
        numero_raro = str(num)
        for i in range(len(numero_raro)):
            numero_medio_raro = int(numero_raro[i])
            sumita += numero_medio_raro
        num = sumita
    return sumita

#ejercicio8
def reemplaza_espacios(cadena):
    cadenita = ""
    cadena1 = cadena.replace(" ","+")
    cadena2 = ""
    for i in range(len(cadena1)) :
        if cadena1[i] == "+":
            cadena2 = cadena1[i] + cadena2
    cadena1 = cadena1.replace("+","")
    cadenita = cadena2 + cadena1
    return cadenita

#ejercicio9
def dibuja_triangulo(n):
    cadenita = ""
    for triangulo in range(1,n+1):
        for renglones in range(1,triangulo+1):
            for espacios in range(n-renglones):
                cadenita += " "
            for estrellitas in range(renglones):
                cadenita += "* "
            cadenita += "\n"
        cadenita += "\n"
    return cadenita

#ejercicio10
def pascal(n):
    cadenita_total = ""
    listita = []
    z = 0
    for x in range(n):
        listita.append([])
    for x in range(n):
        for y in range(x+1):
            if y == 0 or y == x:
                listita[x].append(1)
            else:
                z = listita[x-1][y]+listita[x-1][y-1]
                listita[x].append(z)
        cadena_wow = str(listita[x])
        cadena_wow = cadena_wow.replace(",","")
        cadenita_total = cadenita_total + cadena_wow + "\n"
    return cadenita_total
