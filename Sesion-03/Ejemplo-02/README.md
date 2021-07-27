# Ejemplo #2 

## Objetivo: Generar un WordCount con MapReduce

A continuación vamos a escribir una muestra de WordCount MapReduce, empaquetarla y ejecutarla usando Hadoop independiente. Este ejercicio explica cómo escribir un programa MapReduce simple y cómo ejecutarlo.

Para ejecutar un trabajo de MapReduce, los usuarios deben proporcionar una función de map, una función de reduce, datos de entrada y una ubicación de datos de salida. Cuando se ejecuta, Hadoop lleva a cabo los siguientes pasos:

Hadoop divide los datos de entrada en varios elementos de datos mediante nuevas líneas y ejecuta la función de map una vez para cada elemento de datos, dando el elemento como entrada para la función. Cuando se ejecuta, la función de map genera uno o más pares key-value.

Hadoop recopila todos los pares clave-valor generados a partir de la función de mapa, los ordena por clave y agrupa los valores con la misma clave.

Para cada clave distinta, Hadoop ejecuta la función de reducción una vez mientras pasa la clave y la lista de valores para esa clave como entrada.

La función de reducción puede generar uno o más pares clave-valor, y Hadoop los escribe en un archivo como resultado final.

## Preparándose
Del código fuente disponible en el presente ejemplo, seleccionaremos el código fuente chapter1_src.zip. Luego, lo configuremos con su Java Integrated Development Environment (IDE) favorito; por ejemplo, Eclipse. Deberemos agregar el archivo JAR hadoop-core en HADOOP_HOME y todos los demás archivos JAR en el directorio HADOOP_HOME / lib al classpath de tu IDE.

Es recomendable que instales Apache Ant: http://ant.apache.org/.

- El ejemplo de WordCount usa MapReduce para contar el número de apariciones de palabras dentro de un conjunto de documentos de entrada. Busque el código de muestra de src/chapter1/Wordcount.java El código tiene tres partes: mapeador, reductor y programa principal.

``` 
public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException 
{
      StringTokenizer itr = new StringTokenizer(value.toString());
       while (itr.hasMoreTokens()) 
       {
          word.set(itr.nextToken());
          context.write(word, new IntWritable(1));
        }
}
```

La función de reducción recibe todos los valores que tienen la misma clave que la entrada y genera la clave y el número de apariciones de la clave como salida.

``` 
public void reduce(Text key, Iterable<IntWritable> values, 
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) 
      {
         sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
}
``` 

El programa principal reúne la configuración y envía el trabajo a Hadoop.

``` 
Configuration conf = new Configuration();
String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
if (otherArgs.length != 2) {
System.err.println("Usage: wordcount <in><out>");
System.exit(2);
}
Job job = new Job(conf, "word count");
job.setJarByClass(WordCount.class);
job.setMapperClass(TokenizerMapper.class);
//Uncomment this to 
//job.setCombinerClass(IntSumReducer.class);
job.setReducerClass(IntSumReducer.class);
job.setOutputKeyClass(Text.class);
job.setOutputValueClass(IntWritable.class);
FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
System.exit(job.waitForCompletion(true) ? 0 : 1);
```

Puede compilar la muestra ejecutando el siguiente comando, que usa Apache Ant, desde el directorio raíz del código de muestra:

```
>ant build
```

Si aún no lo has hecho, debe instalar Apache Ant siguiendo las instrucciones que se proporcionan en http://ant.apache.org/manual/install.html Alternativamente, puede utilizar el archivo JAR compilado incluido con el código fuente.

- Cambie el directorio a HADOOP_HOME y copie el archivo hadoop-cookbook-chapter1.jar en el directorio HADOOP_HOME. Para usarlo como entrada, cree un directorio llamado input en HADOOP_HOME y copie el archivo README.txt al directorio. Alternativamente, puede copiar cualquier archivo de texto al directorio de entrada.

- Ejecute la muestra con el siguiente comando. Aquí, cuenta con capítulo1.WordCount, el cual es el nombre de la clase principal que necesitamos ejecutar. Cuando haya ejecutado el comando, verá la siguiente salida de terminal:

```
>bin/hadoop jar hadoop-cookbook-chapter1.jar chapter1.WordCount input output
12/04/11 08:12:44 INFO input.FileInputFormat: Total input paths to process : 16
12/04/11 08:12:45 INFO mapred.JobClient: Running job: job_local_0001
12/04/11 08:12:45 INFO mapred.Task: Task:attempt_local_0001_m_000000_0 is done. And is in the process of commiting
...........
.....
12/04/11 08:13:37 INFO mapred.JobClient: Job complete: job_local_0001
.....
```

El directorio de salida tendrá un archivo llamado part-r-XXXXX, que tendrá el recuento de cada palabra en el documento. ¡Felicidades! Ha ejecutado con éxito su primer programa MapReduce.

