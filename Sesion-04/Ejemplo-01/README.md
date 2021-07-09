# Ejemplo # 1 - Calibrando HDFS

## Objetivo: Escribir una muestra de WordCount MapReduce, empaquetarla y ejecutarla usando Hadoop independiente

Esta Ejercio explica cómo CALIBRAR tu HDFS...

## Manos a la obra:

Change the directory to HADOOP_HOME.

Run the following command to create a new directory called /test:

 ```
>bin/hadoop dfs -mkdir /test
 ```
 
El sistema de archivos HDFS tiene / como directorio raíz al igual que el sistema de archivos Unix. Ejecute el siguiente comando para enumerar el contenido del directorio raíz de HDFS:

 ```
>bin/hadoop dfs -ls /
 ```
Ejecute el siguiente comando para copiar el archivo Léame local en / test

 ```
>bin/hadoop dfs -put README.txt /test
 ```
Ejecute el siguiente comando para listar el directorio / test:

 ```
>bin/hadoop dfs -ls /test

Found 1 items
-rw-r--r--   1 srinath supergroup       1366 2012-04-10 07:06 /test/README.txt
 ```
 
Ejecute el siguiente comando para copiar /test/README.txt al directorio local:

 ```
>bin/hadoop dfs -get /test/README.txt README-NEW.txt
 ```

## Cómo funciona...
Cuando se emite un comando, el cliente hablará con HDFS NameNode en nombre del usuario y llevará a cabo la operación. Generalmente, nos referimos a un archivo o carpeta que usa la ruta que comienza con /; por ejemplo, / data, y el cliente recogerá el NameNode de las configuraciones en el directorio HADOOP_HOME / conf.

Sin embargo, si es necesario, podemos usar una ruta completamente calificada para forzar al cliente a hablar con un NameNode específico. Por ejemplo, hdfs: //bar.foo.com: 9000 / data le pedirá al cliente que hable con NameNode que se ejecuta en bar.foo.com en el puerto 9000.

Hay más...
HDFS admite la mayoría de los comandos de Unix, como cp, mv y chown, y siguen el mismo patrón que los comandos descritos anteriormente. El documento http://hadoop.apache.org/docs/r1.0.3/file_system_shell.html proporciona una lista de todos los comandos. Usaremos estos comandos en lo que hagamos de aquí en adelante.
