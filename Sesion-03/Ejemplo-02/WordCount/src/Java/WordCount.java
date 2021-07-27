/**
 * El siguiente ejemplo fue extraído de: 
 * http://wiki.apache.org/hadoop/WordCount. 
 */
package chapter1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;


/**
 * <p> Este ejemplo de recuento de palabras cuenta el número de ocurrencias de palabras dentro de un conjunto de documentos de entrada
 * utilizando MapReduce. El código tiene tres partes: mapeador, reductor y programa principal.</p>
 * @author Srinath Perera (srinath@wso2.com)
 */
public class WordCount {

  /**
   * <p>
   * El mapeador se extiende desde la interfaz org.apache.hadoop.mapreduce.Mapper. Cuando se ejecuta Hadoop,
   * recibe cada nueva línea en los archivos de entrada como una entrada para el asignador. La función mapa 
   * tokeniza la línea, y para cada token (palabra) emite (palabra, 1) como salida. </p>
   */
  public static class TokenizerMapper 
       extends Mapper<Object, Text, Text, IntWritable>{
    
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
      
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        context.write(word, one);
      }
    }
  }
  
  /**
   * <p> La función Reduce recibe todos los valores que tienen la misma clave que la entrada y genera la clave 
   * y el número de apariciones de la clave como salida. </p>  
   */
  public static class IntSumReducer 
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values, 
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

  /**
   * <p> Como entrada, este programa toma cualquier archivo de texto. Cree una carpeta llamada entrada en HDFS (o en el directorio local si lo está ejecutando localmente)
   * <ol>
   * <li> Opción 1: puede compilar la muestra por Apache Ant desde el directorio de muestras. Para hacer esto, necesita tener Apache Ant instalado en su sistema.
    * De lo contrario, puede utilizar el jar incluido con el código fuente. Cambie el directorio a HADOOP_HOME y copie WordCount.jar en HADOOP_HOME.
    * Luego ejecute el comando> bin / hadoop jar hadoop-cookbook.jar .WordCount input output. </li>
    * <li> Como paso opcional, copie la entrada al directorio de nivel superior del proyecto basado en IDE (proyecto eclipse) que creó para las muestras. Ahora puedes correr
    * la clase WordCount directamente desde su IDE pasando la entrada a la salida como argumentos. Esto ejecutará la muestra igual que antes. Ejecutar trabajos de MapReduce desde su IDE de esta manera es muy útil
    * para depurar sus trabajos de MapReduce. </li>
   * </ol> 
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
      JobConf conf = new JobConf();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    if (otherArgs.length != 2) {
      System.err.println("Usage: wordcount <in> <out>");
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
  }
}


