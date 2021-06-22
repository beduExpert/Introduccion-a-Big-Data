# Ejemplo # 1 - MapReduce

## Objetivo: Comprender el funcionamiento

* Agregar los objetivos del ejemplo (Mínimo agregar 2 objetivos y Borrar está linea una vez se hay leido)

## Antes que nada: ¿Qué es MapReduce?

MapReduce es un framework de software para procesar grandes conjuntos de datos en un sistema distribuido con varias computadoras. La idea central detrás de MapReduce es mapear tus datos en una colección de pares <key,value>, y entonces reducir las parejas con el mismo key. Este concepto puede sonar simple pero de hecho debemos tener algunas consideraciones:
- Casi todos los datos pueden ser mapeados como un pair <key, value>
- Tus keys y values podrían ser de cualquier tipo: strings, enteros, dummy e incluso, <kay, value> tal cual

El ejemplo más sencillo para estudiar una breve introducción a MapReduce es contar la frecuencia de palabras en un gran conjunto de datos (lo haremos en ejemplos posteriores). Otros ejemplos de que se podría hacer con MapReduce es:
- Conteo distribuido
- Busqueda distribuida
- Machine Learning

Cuando escribamos un flujo de trabajo de Reducción de mapa, tendremos que crear 2 scripts: el mapa guión y guión derivado. El resto será manejado por AmazonElastic
Marco MapReduce (EMR).

Cuando iniciamos un mapa / reducción del flujo de trabajo, el marco dividirá la entrada en segmentos, pasando cada segmento a una máquina diferente. Cada máquina luego ejecuta el mapeo y la respectiva proporción de datos atribuida a él.
La secuencia de comandos (que usted escribe) toma algunos datos de entrada y la asigna a los pares <clave, valor> de acuerdo con sus especificaciones. Por ejemplo, si queremos frecuencia en el texto, tendremos <palabra, recuento> entre nuestros pares <clave, valor>. Con nuestro map script , entonces, debería ser el par <palabra, 1> antes de cada palabra en el flujo de entrada. Nota
que el mapa no tiene agregación (es decir, un recuento real), esto es lo que
reducecriptitfor. El propósito del modo de evaluación de mapas de datos en la clave <,valor> pares para reducir a agregar.

Emitió pares de <clave, valor> y luego "mezclados" (para usar la diagrama de abajo), que básicamente significa que los pares con la misma clave están agrupados y pasado a una sola máquina, que luego ejecutará un script para convertirlos 2. El guión reducido (que tú escribirás) toma una recopilación de pares <clave, valor> y"Reduce" el texto de acuerdo con el guión reducido especificado por el usuario. Inourwordcount
Por ejemplo, queremos contar el número de peores que ocurren
frecuencias. Por lo tanto, deseamos que nuestro guión reduzca simplemente sumar los valores de la colección de pares <clave, valor> que tienen la misma clave 3.
El diagrama que se muestra a continuación ilustra lo que se describe de forma centrada.

El diagrama que e muestra a coontinuación muestra el esquema MapReduce:
![01](assets/Capture.png)



## Desarrollo

Para el presente ejercicio debiste haber descargado los archivos indicados en el readme de la presente sesión. Deberás tener un directorio 'dblp' en  tu computadora tal como se indica a continuación:

```
-rw-r--r-- 1 webdam webdam    108366  author-medium.txt  
-rw-r--r-- 1 webdam webdam     10070  author-small.txt  
-rw-r--r-- 1 webdam webdam      7878  dblp.dtd  
-rw-r--r-- 1 webdam webdam 720931885  dblp.xml  
-rw-r--r-- 1 webdam webdam    130953  proceedings-medium.txt  
-rw-r--r-- 1 webdam webdam     17151  proceedings-small.txt
```

Una vez formateado el sistema de archivos, debe iniciar el nodo principal de HDFS (namenode). El nodo de nombre es un proceso responsable de administrar los nodos del servidor de archivos (llamados nodos de datos en HADOOP) en el clúster, y lo hace con los comandos ssh. Debe verificar que SSH esté configurado correctamente y, en particular, que pueda iniciar sesión en la máquina local con SSH sin tener que ingresar una contraseña. Prueba el siguiente comando:
```
$ ssh localhost
```
Sino funciona, ejecuta lo siguiente:
```
$ ssh-keygen -t dsa -P ’’ -f "/.ssh/id_dsa  
$ cat "/.ssh/id_dsa.pub >> "/.ssh/authorized_keys
```


