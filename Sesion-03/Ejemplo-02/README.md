# Ejemplo #2 Corriebndo tareas MapReduce y genera uno que te permita contar palabras

## Objetivo

* En este breve ejempo, serás capaz de empezar a contar palabras. Vamos allá

Ahora podemos ejecutar el trabajo MAPREDUCE para procesar archivos de datos almacenados en HDFS. A continuación, se ofrece en primer lugar un ejemplo que analiza archivos de texto extraídos del conjunto de datos DBLP. A continuación, sugerimos algunas mejoras y experimentos. Primero debe iniciar los servidores MAPREDUCE:
start-mapred.sh
Nuestro ejemplo procesa archivos de datos extraídos del conjunto de datos DBLP y transformados en archivos de texto plano para simplificar. Puede tomar estas entradas de datos de varios tamaños, denominadas autores-xxx.txt del sitio web del libro, junto con el código Java. El tamaño de archivo más pequeño es de unos pocos KB, el más grande de 300 MB. Se puede decir que estos son conjuntos de datos pequeños para HADOOP, pero suficientes para una práctica inicial.
El formato de archivo es bastante simple. Consiste en una línea para cada par (autor, título), con campos separados por tabulaciones, como se muestra a continuación.
<nombre del autor> <título> <año>
Nuestro trabajo MAPREDUCE cuenta el número de publicaciones encontradas para cada autor. Descomponemos el código en dos archivos Java, disponibles como de costumbre en el sitio. El primero, a continuación, proporciona una implementación de las operaciones MAP y REDUCE.

```
package myHadoop; 
 
/** 
 * Import the necessary Java packages 
 */ 
 
import java.io.IOException; 
import java.util.Scanner; 
import org.apache.hadoop.io.*; 
import org.apache.hadoop.mapreduce.Mapper; 
import org.apache.hadoop.mapreduce.Reducer; 
 
/** 
 * A Mapreduce example for Hadoop. It extracts some basic 
 * information from a text file derived from the DBLP data set. 
 */ 
public class Authors { 
 
  /** 
   * The Mapper class -- it takes a line from the input file and 
   * extracts the string before the first tab (= the author name) 
   */ 
  public static class AuthorsMapper extends 
          Mapper<LongWritable, Text, Text, IntWritable> { 
 
        private final static IntWritable one = new IntWritable(1); 
        private Text author = new Text(); 
 
        public void map(LongWritable key, Text value, Context context) 
                throws IOException, InterruptedException { 
 
          /* Open a Java scanner object to parse the line */ 
          Scanner line = new Scanner(value.toString()); 
          line.useDelimiter("\t"); 
          author.set(line.next()); 
          context.write(author, one); 
        } 
  } 
 
  /** 
   * The Reducer class -- receives pairs (author name, <list of counts>) 
   * and sums up the counts to get the number of publications per author 
   */ 
  public static class CountReducer extends 
          Reducer<Text, IntWritable, Text, IntWritable> { 
        private IntWritable result = new IntWritable(); 
 
        public void reduce(Text key, Iterable<IntWritable> values, 
              Context context) 
                throws IOException, InterruptedException { 
 
          /* Iterate on the list to compute the count */ 
          int count = 0; 
          for (IntWritable val : values) { 
                count += val.get(); 
          } 
          result.set(count); 
          context.write(key, result); 
        } 
  } 
}

## Desarrollo

>**💡 Nota para experto(a)**
>
> Este es un ejemplo por si el experto necesita tener en cuenta un punto clave durante el ejemplo.
>Si no es necesario, puedes borrar esta nota.

Aquí se debe agregar el desarrollo del ejemplo
```

