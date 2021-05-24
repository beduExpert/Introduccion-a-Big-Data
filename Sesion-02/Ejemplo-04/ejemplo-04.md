# Tu primer script en Hadoop: contemos palabras

## Para el reto actual, debes bajar en tu PC el archivo llamado 'bible+shakes.nopunc' que está en la misma carpeta del ejemplo 04 de la actual sesión: vamos a contar las palabras en este comprimido pero todo esto hecho desde Hadoop.

El siguiente programa es un programa de conteo de palabras. Este ejemplo lee archivos de texto y cuenta la frecuencia con la que ocurren las palabras. La entrada son archivos de texto y la salida son archivos de texto, cada línea de los cuales contiene una palabra y el recuento de la frecuencia con la que ocurrió, separados por una pestaña.

Cada mapeador toma una línea como entrada y la divide en palabras. Luego emite un par clave / valor de la palabra y 1. Cada reductor suma los recuentos de cada palabra y emite una única clave / valor con la palabra y la suma. Como optimización, el reductor también se utiliza como combinador en las salidas del mapa. Esto reduce la cantidad de datos enviados a través de la red al combinar cada palabra en un solo registro.

Antes de que pueda ejecutar el ejemplo, deberá copiar algunos datos en el HDFS. Aquí crearemos un directorio de entrada y copiaremos las obras completas de Shakespeare y la Biblia (un gran corpus estándar para minería de texto).

Recuerda poner ese archivo en una carpeta ubicable. Vamos hasta ahí y genemos el programa:

```
$ hadoop fs -mkdir /user/USERNAME/wordcount
$ hadoop fs -mkdir /user/USERNAME/wordcount/input
$ hadoop fs -put /bluearc/data/schatz/data/textmining/bible+shakes.nopunc/user/mschatz/wordcount/input

```

## Ahora vamos a contar las palabras:

$ hadoop jar /usr/lib/hadoop/hadoop-examples.jar wordcount \
             /user/USERNAME/wordcount/input \
             /user/USERNAME/wordcount/output
             
             
Ahora decarga los resultados a tu repo principal ambiente en Roll

```
$ hadoop fs -get /user/USERNAME/wordcount/output output
```
¡Listo, has contado palabras en un fichero comprimido en Hadoop!



