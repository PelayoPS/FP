# Juego de adivinar un número entre 1 y 9
# A partir de ese número el ordenador genera un número entre 1 y 9
# intentando llegar a la siguiente decena del número del jugador
# si uno de los dos se pasa de 50 pierde

import random

def juego50():
    # selecciona un modo de dificultad
    # 1. Fácil (el ordenador elige un número aleatorio entre 1 y 9)
    # 2. Difícil (el ordenador elige un número entre [1, 10-jugador])
    # 3. Imposible (el ordenador elige el número que falta para llegar a 50)
    print("Juego de 50")
    # pregunta el nombre al jugador
    nombre = input("Introduce tu nombre: ")
    print("Hola", nombre)
    print("Selecciona un modo de dificultad:\n1. Fácil\n2. Difícil\n3. Imposible")
    # si no es un número y no está entre 1 y 3 vuelve a pedirlo
    # si no es int vuelve a pedirlo
    dificultad = validar_dificultad()

    print("\n", "Instrucciones:", "\n", "Elige un número entre 1 y 9", "\n", "El ordenador elegirá un número entre 1 y 9", "\n", "El que supere 50 en su turno pierde", "\n")

    # variables de control
    suma = 0
    turno_jugador = True

    # bucle de juego
    suma, turno_jugador = bucle_principal(dificultad, suma, turno_jugador)

    # determinar el ganadora
    if suma >= 50 and not turno_jugador:
        print("¡Has ganado!")
    else:
        print("¡Has perdido!")


def validar_dificultad():
    valido = False
    while not valido:
        try:
            dificultad = int(input("Dificultad: "))
            if dificultad < 1 or dificultad > 3:
                print("Número no válido")
            else:
                valido = True
        except ValueError:
            print("Número no válido")
    return dificultad
def bucle_principal(dificultad, suma, turno_jugador):
    # bucle de juego
    while suma < 50:
        # comprueba que sea un número y que esté entre 1 y 9
        valido = False
        while not valido:
            try :
                jugador = int(input("Elige un número entre 1 y 9: "))
                if jugador < 1 or jugador > 9:
                    print("Número no válido")
                else:
                    valido = True
            except ValueError:
                print("Número no válido")
        turno_jugador = False
        ordenador = run_dificultad(dificultad, jugador)
        print("Ordenador: ", ordenador)
        suma = suma + jugador + ordenador
        print("Suma: ", suma)
        print("")
        turno_jugador = True
    return suma, turno_jugador

def run_dificultad(dificultad, jugador):
    if dificultad == 1:
        ordenador = random.randint(1, 9)
    elif dificultad == 2:
        ordenador = random.randint(1, 10 - jugador)
    elif dificultad == 3:
        ordenador = 10 - jugador

    return ordenador

#main
juego50()
