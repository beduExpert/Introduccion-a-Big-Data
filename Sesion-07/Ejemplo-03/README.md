# Ejemplo 3 - Acciones en Spark 

## Objetivo

* Comprender el uso y aplicación de las Acciones para el análisis y administración de Grandes Datos (las cuales, se diferencian ampliamente de las Transformaciones Cortas como Amplias que estudiamos a detalle en ejemplos y retos anteriores).

# Acciones o Actions

Habrá notado que en el ejemplo que usamos, el método collect () nos ofrece justamente un output compuesto. Para devolver el resultado final , Spark proporciona otro tipo de operaciones mejor conocidas como Acciones o Actions. En el momento en el que se dan las transformaciones, Spark encadena estas operaciones y construye un DAG (un DAG es un gráfico directo finito sin ciclos dirigidos. Hay un número finito de vértices y aristas, donde cada arista se dirige de un vértice a otro. Contiene una secuencia de vértices de modo que cada borde se dirige de antes a más adelante en la secuencia.), pero no se ejecuta nada. Una vez que se realiza una acción en un RDD, se "forza" la evaluación de todas las transformaciones necesarias para calcular ese RDD.

Las acciones no crean un nuevo RDD. Se utilizan para lo siguiente:

- Devolver resultados finales
- Generar escritura del resultado final en un almacenamiento externo
- Realizar alguna operación en cada elemento de ese RDD tal como con un foreach ()

Estudiemos algunas acciones básicas

# collect()

La acción collect () devuelve todos los elementos de un RDD al programa controlador. Solo debe usar collect () si está seguro del tamaño de su salida final. Si el tamaño de la salida final es enorme, entonces su programa de controlador podría fallar mientras recibe los datos de los ejecutores. No se recomienda el uso de collect () en producción. El siguiente ejemplo recopila todos los elementos de un RDD que contiene números del 0 al 9:

```
# Ejemplo en Python
spark.sparkContext.parallelize(range(10)).collect()
```

El Output que se genera es el siguiente:
```
Out[1]: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
```

# count()
Use count() para contar el número de elementos en el RDD. El siguiente código de Scala cuenta el número de un RDD y devuelve 10 como salida:

```
//scala
spark.sparkContext.parallelize(1 to 10).count()
```
Output:
```
res17: Long = 10
```

# take()

La acción take() devuelve N número de elementos de un RDD. El siguiente código devuelve los dos primeros elementos de un RDD que contiene los números del 0 al 9:
```
#Python
spark.sparkContext.parallelize(range(10)).take(2)
```
La salida de este código sería:
```
Out[27]: [0, 1]
```

# top()

La acción top() devuelve los N elementos superiores del RDD. El siguiente código devuelve los 2 elementos principales de un RDD:
```
#Python
spark.sparkContext.parallelize(range(10)).top(2)
```
La salida de este código sería:
```
Out[3]: [9, 8]
```

# takeOrdered()

Si desea obtener un elemento N basado en un array ordenado, puede usar una acción takeOrdered(). También puede hacer uso de la transformación sortBy(), seguida de una acción take(). Ambos enfoques son correctos. En el siguiente ejemplo, sacamos 3 elementos del RDD, que contienen números del 0 al 9, proporcionando nuestros propios criterios de clasificación:

```
#Python
spark.sparkContext.parallelize(range(10)).takeOrdered(3, key = lambda x: -x)
```

# El OutPut

```
Out[23]: [9, 8, 7]
```

Aquí, tomamos los primeros 3 elementos en orden decreciente.

# first()

La primera acción () devuelve el primer elemento del RDD. El siguiente ejemplo devuelve el primer elemento del RDD:

```
spark.sparkContext.parallelize(range(10)).first()
```

El resultdo sería el siguiente

```
Out[4]: 0
```

# countByValue()

La acción countByValue () se puede usar para averiguar la ocurrencia de cada elemento en el RDD. El siguiente es el código de Scala que devuelve un mapa de par clave-valor. En la salida, Mapa, la clave es el elemento RDD y el valor es el número de apariciones de ese elemento en el RDD:

```
//Scala
spark.sparkContext.parallelize(Array("A","A","B","C")).countByValue()

res0: scala.collection.Map[String,Long] = Map(A -> 2, B -> 1, C -> 1)
```

# reduce()


La acción countByValue () se puede usar para averiguar la ocurrencia de cada elemento en el RDD. El siguiente es el código de Scala que devuelve un mapa de par clave-valor. En la salida, Mapa, la clave es el elemento RDD y el valor es el número de apariciones de ese elemento en el RDD:

```
//Scala
spark.sparkContext.parallelize(1 to 10).reduce( _ + _ )

res1: Int = 55
```

# saveAsTextFile()


Para guardar los resultados en un "warehouse" de datos externo, podemos hacer uso de saveAsTextFile () para guardar su resultado en un directorio. También puede especificar un códec de compresión para almacenar sus datos en forma comprimida. Escribamos nuestro número RDD en un archivo:

```
# Código en Python
spark.sparkContext.parallelize(range(10)).saveAsTextFile('/FileStore/tables/result')
```

En el ejemplo anterior, proporcionamos un directorio como argumento y Spark escribe datos dentro de este directorio en varios archivos, junto con el archivo de éxito (_success). (NOTA: Si se proporciona un directorio existente como argumento para la acción saveAsTextFile (), el trabajo fallará con la excepción FileAlreadyExistsException. Este comportamiento es importante porque podríamos reescribir accidentalmente un directorio que contenga datos de un trabajo pesado)

# foreach()

La función foreach () aplica una función a cada elemento del RDD. El siguiente ejemplo concatena la cadena Mr. a cada elemento usando foreach():

```
//Scala
spark.sparkContext.parallelize(Array("Smith","John","Brown","Dave")).foreach{ x => println("Mr. "+x) }
```

## NOTA IMPORTANTE:
Si ejecuta el ejemplo anterior en modo local, verá el resultado. Pero, en el caso del modo de clúster, no podrá ver los resultados, porque foreach () realiza la función dada dentro de los ejecutores y no devuelve ningún dato al controlador.

# Enhorabuena!
Ahora conoces de forma general las transformaciones y acciones básicas de Spark. Vamos a aplicar lo aprendido en el Reto 03.

