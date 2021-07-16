# Reto 3 SOLUCIÓN - Primeros pasos con la API de Python en Spark

La función propuesta para resolver el ejercicio es la siguiente:

```
#Python
filePath = "/FileStore/tables/sampleFile.log"

logRDD = spark.sparkContext.textFile(filePath)

resultRDD = logRDD.filter(lambda line : line.split(" - ")[2] in ['INFO','ERROR'])\
                  .map(lambda line : (line.split(" - ")[2], 1))\            
                  .reduceByKey(lambda x, y : x + y)                 

resultRDD.collect()



```

El Output o resultado de la función dbería ser el siguiente:

```
Out[27]: [('INFO', 2), ('ERROR', 3)]
```

