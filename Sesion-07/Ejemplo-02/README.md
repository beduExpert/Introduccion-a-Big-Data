# Ejemplo 2 - Wide transformations o Transformaciones Amplias

## Objetivo

* Comprender el uso y aplicación de las transformaciones amplias para el análisis y administración de Grandes Datos.

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

# intersection()

La transformación intersection() nos permite encontrar elementos comunes entre dos RDD. Al igual que la transformación union (), intersection () también es una operación de conjunto entre dos RDD, pero implica una mezcla. Los siguientes ejemplos muestran cómo encontrar elementos comunes entre dos RDD usando intersection ():

```
# Ejemplo en Python
firstRDD = spark.sparkContext.parallelize(range(1,6))
secordRDD = spark.sparkContext.parallelize(range(5,11))
firstRDD.intersection(secordRDD).collect()
```

El código previo daría como resultado 5 (literalmete, la intersección entre ambos arrays)

# subtract()
Puede usar la transformación subtract() para eliminar el contenido de un RDD usando otro RDD. Creemos dos RDD: el primero tiene números del 1 al 10 y el segundo tiene elementos del 6 al 10. Si usamos restar (), obtenemos un nuevo RDD con números del 1 al 5:

```
#Ejemplo en Python
firstRDD = spark.sparkContext.parallelize(range(1,11))
secordRDD = spark.sparkContext.parallelize(range(6,11))
firstRDD.subtract(secordRDD).collect()
```
En el ejemplo anterior, tenemos dos RDD: el primer RDD contiene elementos del 1 al 10 y el segundo RDD contiene los elementos del 6 al 10. Después de aplicar la transformación subtract (), obtenemos un nuevo RDD que contiene elementos del 1 al 5.

# cartesian()

305 / 5000
Translation results
La transformación cartesiana o cartesian() puede unir elementos de un RDD con todos los elementos de otro RDD y da como resultado el producto cartesiano de dos. En los siguientes ejemplos, firstRDD tiene elementos [0, 1, 2] y secondRDD tiene elementos ['A', 'B', 'C']. Usamos cartesiano () para obtener el producto cartesiano de dos RDD: 

```
# Usando Python
firstRDD = spark.sparkContext.parallelize(range(3))
secordRDD = spark.sparkContext.parallelize(['A','B','C'])
firstRDD.cartesian(secordRDD).collect()
```

Aquí está el resultado del ejemplo anterior:
```
Array((0,A), (0,B), (0,C), (1,A), (1,B), (1,C), (2,A), (2,B), (2,C))
```
# NOTAS IMPORTANTES SOBRE cartesian() :
- Recuerde que estas operaciones implican una reproducción aleatoria y, por lo tanto, requieren muchos recursos informáticos, como memoria, disco y ancho de banda de red.
- textFile() y wholeTextFiles() también se consideran transformaciones, ya que crean un nuevo RDD a partir de datos externos.