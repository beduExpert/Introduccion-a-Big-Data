# Ejemplo 1 - Modos de ejecución

Spark se puede ejecutar en diferentes modos, que se clasifican según dónde y cómo queremos configurar el maestro y cuáles son los requisitos de recursos del ejecutor.

El maestro puede ejecutarse en la misma máquina local, junto con los ejecutores; también puede ejecutarse en una máquina específica con el host y el puerto proporcionados. Si configuramos YARN como un administrador de recursos de Spark, el maestro puede ser perfectamente administrado por YARN (abre tu Command Prompt, vamos a ejecutar una serie de comandos):

## Corramos una aplicación en 8 cores dentro de un mismo nodo

```
./bin/spark-submit \
 --class org.apache.spark.examples.SparkPi \
 --master local[8] \
  /path/to/examples.jar \
  100
```

De esta forma, has distribuido tu administración de tu trabajo en 8 diferentes sub-cores. Ahora lo que vamos a desarrollar a continuación es correr en modo Standalone:

```
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master spark://host-ip:7077 \
  --executor-memory 20G \
  --total-executor-cores 100 \
  /path/to/examples.jar \
  1000
```
Ahora, lo que haremos a continuación es correr en modo YARN Clúster:

```
export HADOOP_CONF_DIR=XXX
./bin/spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master yarn \
  --deploy-mode cluster \ # modo cliente
  --executor-memory 20G \
  --num-executors 50 \
  /path/to/examples.jar \
  1000
```

# Nota: 
Interfaz de usuario de Spark: Spark proporciona una interfaz web para la ejecución de aplicaciones, a la que se puede acceder de forma predeterminada en el puerto 4040: http://localhost:4040/jobs/

![1](assets/1.png)
