# Ejemplo 1 - Transformaciones

# ¿Qué es una transformación?

Como sugiere el nombre, las transformaciones nos ayudan a transformar los RDD existentes. Como resultado, siempre crean un nuevo RDD que se calcula de forma perezosa. En los ejemplos anteriores, hemos discutido muchas transformaciones, como map(), filter() y reduceByKey().

Las transformaciones son de dos tipos:

- Transformaciones estrechas (Narrow transformations)
- Transformaciones amplias (Wide transformations)
 
Las transformaciones estrechas transforman los datos sin ningún tipo de mezcla mayor, es decir, estas transformaciones transforman los datos por partición: cada elemento del RDD de salida se puede calcular sin involucrar ningún elemento de diferentes particiones. Esto lleva a un punto importante: el nuevo RDD siempre tendrá el mismo número de particiones que sus RDD principales, y es por eso que son fáciles de volver a calcular en caso de que falle algo. Entendamos esto con el siguiente ejemplo:

![2](assets/2.png)

Entonces, tenemos un RDD-A y realizamos una transformación estrecha, como map () o filter (), y obtenemos un nuevo RDD-B con la misma cantidad de particiones que RDD-A. En la parte (B), tenemos dos, RDD-A y RDD-B, y realizamos otro tipo de transformación estrecha como union (), y obtenemos un nuevo RDD-C con el número de particiones igual a la suma de particiones de sus RDD principales (A y B). Veamos algunos ejemplos de transformaciones estrechas:

# map()

map() aplica una función determinada a cada elemento de un RDD y devuelve un RDD nuevo con el mismo número de elementos. Por ejemplo, en el siguiente código, los números del 1 al 10 se multiplican por el número 2:

```
#Python
spark.sparkContext.parallelize(range(1,11)).map(lambda x : x * 2).collect()
```

# flatMap()

Esto aplica una función determinada que devuelve un iterador a cada elemento de un RDD y devuelve un RDD nuevo con más elementos. En algunos casos, es posible que necesite varios elementos de un solo elemento. Por ejemplo, en el siguiente código, un RDD que contiene líneas se convierte en otro RDD que contiene palabras:

```
#Python
spark.sparkContext.parallelize(["Esta super divertido aprender Spark!","Este es un ejemplazo de flatMap con Python"]).flatMap(lambda x : x.split(" ")).collect()
```

# filter()

La transformación filter() aplica una función que filtra los elementos que no pasan los criterios de condición, como se muestra en el siguiente código. Por ejemplo, si necesitamos números mayores que 5, podemos pasar esta condición a la transformación filter (). Creemos un RDD de números del 1 al 10 y filtremos los números que son mayores que 5:

```
#Python
spark.sparkContext.parallelize(range(1,11)).filter(lambda x : x > 5).collect()
```

# union()

La transformación union() toma otro RDD como entrada y produce un nuevo RDD que contiene elementos de ambos RDD, como se muestra en el siguiente código. Creemos dos RDD: uno con los números del 1 al 5 y otro con los números del 5 al 10, y luego concatenémoslos para obtener un nuevo RDD con los números del 1 al 10:

```
#Python
firstRDD = spark.sparkContext.parallelize(range(1,6))
secordRDD = spark.sparkContext.parallelize(range(5,11))
firstRDD.union(secordRDD).collect()
```
## _Nota importante:_
La transformación union () no elimina los duplicados. Si viene de un entorno SQL, union () realiza la misma operación que Union All en SQL.

# mapPartitions()

La transformación mapPartitions () es similar a map (). También permite a los usuarios manipular elementos de un RDD, pero proporciona más control por partición. Aplica una función que acepta un iterador como argumento y devuelve un iterador como salida (Un ejemplo en el que puede usar mapPartitions () es cuando necesita abrir una conexión de base de datos al comienzo de cada partición.).

```
# Ejemplo en Python
spark.sparkContext.parallelize(range(1,11), 2).mapPartitions(lambda iterOfElements : [e*2 for e in iterOfElements]).collect()
```

# Transformaciones amplias

Las transformaciones amplias implican una mezcla de datos entre las particiones. Algunas funciones tales como GroupByKey(), reduceByKey(), join(), distinct() e intersect() son algunos ejemplos de transformaciones amplias. En el caso de estas transformaciones, el resultado se calculará utilizando datos de varias particiones y, por lo tanto, requiere una reproducción aleatoria. Las transformaciones amplias son similares a la fase de orden aleatorio de MapReduce. Entendamos el concepto con la ayuda del siguiente ejemplo:

Tenemos un RDD-A y realizamos una transformación amplia como groupByKey () y obtenemos un nuevo RDD-B con menos particiones. RDD-B tendrá datos agrupados por cada clave en el conjunto de datos. En la parte (B), tenemos dos RDD: RDD-A y RDD-B y realizamos otro tipo de transformación amplia como join () o intersection () y obtenemos un nuevo RDD-C. Los siguientes son algunos ejemplos de transformaciones amplias.

![wide](assets/wide.png)

# distinct()

distinct() elimina los elementos duplicados y devuelve un nuevo RDD con elementos únicos como se muestra. Creemos un RDD con algunos elementos duplicados (1,2,3,4) y usemos distintos () para obtener un RDD con números únicos:

```
# Ejemplo en Python
spark.sparkContext.parallelize([1,1,2,2,3,3,4,4]).distinct().collect()
```

#sortBy()

Podemos ordenar un RDD con la ayuda de la transformación sortBy(). Acepta una función que se puede utilizar para ordenar los elementos RDD. En el siguiente ejemplo, ordenamos nuestro RDD en orden descendente utilizando el segundo elemento de la tupla:

```
# Ejemplo en Python
spark.sparkContext.parallelize([('Pepito', 4),('Juancho', 2),('Pablito', 6),('Kaleb', 1)]).sortBy(lambda x : -x[1]).collect()
```

El código previamente hecho debería resultar en lo siguiente:

```
[('Shrey', 6), ('Rahul', 4), ('Aman', 2), ('Akash', 1)]
```

Aún faltan más funciones básicas, pero es tiempo de poner en práctica tus conocimientos. Vamos a desarrollar el Reto 01!:

