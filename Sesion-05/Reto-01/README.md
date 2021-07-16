# Reto 1 - Recordando el análisis de datos con R o Python con datos de la Eurocopa 2020



## Objetivo

* Iremos desarrollando código en Python y R en próximas sesiones para poder manejar Spark. Es por ello que iremos calentando motores con un ejercicioo básicos de análisis de datos.

## Desarrollo

![euro](imgassets/euro.PNG)

A continuación tienes ante ti un dataset sobre los jugadores que participaron en la Eurocopa del año 2020. Estas son las variables con las que cuentas:

- Player: Nombre del jugador
- Country: País al que pertenece
- Position: Posición donde el jugador se desempeña
- Match played: Cantidad de juegos disputados por el jugador n
- Goals: Goles anotados
- Right foot goals: goles anotados con el pie derecho
- Left foot goals: goles anotados con el pie izquierdo
- Header goals: goles anotados con la cabeza
- Assists: Número de asistencias hechas por el jugador n
- Total attempts: Total de tiros a portería

## Instrucciones

Deberás utilizar Python o R (alguno de los dos) para desarrollar las siguientes tareas

- Genera una tabla que resuma el top 15 de los futbolistas con más goles anotados, independientemente de que modo anotaron.

- ¿Cuantos jugadores anotaron goles con el pie derecho?, ¿y con e pie izquierdo?

- ¿En promedio, que nacionalidad anotó más goles por partido?

- ¿En promedio, que nacionalidad generó más asistencias?

- Genera un análisis por cuartiles de las siguientes posiciones:
  - Defensas
  - Medios
  - Defensas

- ¿Qué equipo fue el más goleador del torneo?

- ¿Qué equipo fue el más goleado del torneo?

- ¿Qué equipo fue el más más tiros a portería logró?

## EXTRA: Este paso no es obligatorio pero puedes hacerlo si acabaste antes o tienes el interés en ahondar al respecto

**💡 Estima una regresión lineal que correlacione los intentos de tiros a portería y los goles anotados con la pierna izquierda como con la derecha a través de una variable dummy (donde 1 corresponda a la pierna izquierda y 0 a la pierna derecha). Haga un print de la R cuadrada, el error estandar, la prueba t de los coeficientes de regresión y el término residual.
