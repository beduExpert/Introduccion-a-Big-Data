# Ejemplo 3: Programando RDD

Un RDD puede ser creado en cuatro maneras. Vas a aprenderlas a detalle a continuaicón:

# Método 1: Paralelización de una colección
Esta es una de las formas más sencillas de crear un RDD. Puedes usar la colección existente de sus programas, como List, Array o Set, así como otros, y pedirle a Spark que distribuya esa colección en el clúster para procesarla en paralelo. Una colección se puede distribuir con la ayuda de paralelize (), como se muestra aquí, con un ejemplo muy básico y elemetal con un array básico compuesto por 10 elementos que van del número 1 natural hasta el 10 natural:

## NOTA IMPORTANTE:
Previamente a correr este Script, debes cargar previamente la instalación de Python y Spark que aprendimos a hacer en la sesión pasada

```
# Código escrito en Python
numberRDD = spark.sparkContext.parallelize(range(1,10))
numberRDD.collect()

Out[4]: [1, 2, 3, 4, 5, 6, 7, 8, 9]
```

El siguiente código genera la misma tarea pero en Lenguaje Scala:

```
// Codigo escrito en scala
val numberRDD = spark.sparkContext.parallelize(1 to 10)
numberRDD.collect()

res4: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
```

# Método 2: Desde un conjunto de datos externo: 
Aunque paralelizar una colección es la forma más fácil de crear un RDD, no es la forma recomendada para los grandes conjuntos de datos. Los grandes conjuntos de datos generalmente se almacenan en sistemas de archivos como HDFS, y sabemos que Spark está diseñado para procesar grandes volúmenes de datos. Por lo tanto, Spark proporciona una serie de API para leer datos de los conjuntos de datos externos. Uno de los métodos para leer datos externos es el archivo TextFile(). Este método acepta un nombre de archivo y crea un RDD, donde cada elemento del RDD es la línea del archivo de entrada.

En el siguiente ejemplo, primero inicializamos una variable con la ruta del archivo y luego usamos la variable filePath como argumento del método textFile ():

```
// Escrito en Lenguaje Scala
val filePath = "/FileStore/tables/sampleFile.log"
val logRDD = spark.sparkContext.textFile(filePath)
logRDD.collect()
```

Si sus datos están presentes en varios archivos, puede utilizar WholeTextFiles() en lugar de utilizar el método textFile (). El argumento de wholeTextFiles() es el nombre del directorio que contiene todos los archivos. Cada elemento se representará como un par key-value, donde la clave será el nombre del archivo y el valor será todo el contenido de ese archivo. Esto es útil en escenarios en los que tiene muchos archivos pequeños y desea procesar cada archivo por separado.

## Nota:
Los archivos JSON y XML son entradas comunes de wholeTextFiles (), ya que puede analizar cada archivo por separado utilizando una biblioteca de analizador.

# Método 3: Desde otro RDD
Como se discutió en la primera sección, los RDD son de naturaleza inmutable. No se pueden modificar, pero podemos transformar un RDD en otro RDD con la ayuda de los métodos proporcionados por Spark. Discutiremos estos métodos con más detalle en este ejemplo. El siguiente ejemplo usa filter() para transformar nuestro numberRDD en evenNumberRDD en Python. De manera similar, también usa filter() para crear oddNumberRDD en Scala.

Desde Python:

```
#Python
evenNumberRDD = numberRDD.filter(lambda num : num%2 == 0 )
evenNumberRDD.collect()

Out[10]: [2, 4, 6, 8]
```

Desde Scala:

```
//Scala 
val oddNumberRDD = numberRDD.filter( num => num%2 != 0 )
oddNumberRDD.collect()

res8: Array[Int] = Array(1, 3, 5, 7, 9)
```

# Método 4: Desde un DataFrame o un DataSet:
Debes estar pensando, ¿por qué crearíamos un RDD a partir de un DataFrame? Después de todo, un DataFrame es una abstracción sobre un RDD. ¡Bueno, tienes razón! Debido a esto, es recomendable utilizar DataFrames o un conjunto de datos sobre un RDD, porque un DataFrame aporta beneficios de rendimiento. Es posible que deba convertir un RDD de un DataFrame en algunos escenarios donde se aplica lo siguiente:

- Los datos están muy desestructurados.
- Los datos se reducen a un tamaño manejable después de cálculos pesados, como uniones o agregaciones, y desea tener más control sobre la distribución física de los datos mediante particiones personalizadas.
- Tiene algún código escrito en un lenguaje de programación diferente o código RDD heredado.

Vamos a crear un DataFrame y convertirlo en un RDD:

```
#Hecho con Python
rangeDf = spark.range(1,5)
rangeRDD = rangeDf.rdd
rangeRDD.collect()

Out[15]: [Row(id=1), Row(id=2), Row(id=3), Row(id=4)]
```

El Out de ese código debería ser así:
```
Out[15]: [Row(id=1), Row(id=2), Row(id=3), Row(id=4)]
```

¿Qué acabamos de hacer? primero creamos un rangeDfDataFrame con una columna id (el nombre de columna predeterminado) usando el método range () de Spark, que creó 4 filas, de 1 a 4. Luego usamos el método RDD para convertirlo en rangeRDD (NOTA: range(N) crea valores desde 0 hasta N-1).

Ahora bien, hagamos algo muy sencillo pero importante: leamos el SampleFile.log:

```
$ cat sampleFile.log 
```

Lo que te debería aparecer en pantalla debe ser algo así:

```
2018-03-19 17:10:26 - myApp - DEBUG - debug message 1
2018-03-19 17:10:27 - myApp - INFO - info message 1
2018-03-19 17:10:28 - myApp - WARNING - warn message 1
2018-03-19 17:10:29 - myApp - ERROR - error message 1
2018-03-19 17:10:32 - myApp - CRITICAL - critical message with some error 1
2018-03-19 17:10:33 - myApp - INFO - info message 2
2018-03-19 17:10:37 - myApp - WARNING - warn message
2018-03-19 17:10:41 - myApp - ERROR - error message 2
2018-03-19 17:10:41 - myApp - ERROR - error message 3
```