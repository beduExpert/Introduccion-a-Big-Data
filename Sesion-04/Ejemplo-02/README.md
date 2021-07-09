# Ejemplo #2 

## Objetivo: Configuración de Hadoop en un entorno de clúster distribuido

La implementación de Hadoop incluye una implementación HDFS, un único rastreador de trabajos y varios TaskTrackers. En la receta anterior, Configuración de HDFS, analizamos la implementación de HDFS. Para la configuración de Hadoop, necesitamos configurar JobTrackers y TaskTrackers y luego especificar TaskTrackers en el archivo HADOOP_HOME / conf / slaves. Cuando iniciamos JobTracker, iniciará los nodos de TaskTracker. El siguiente diagrama ilustra una implementación de Hadoop:

![1](imgassets/1.jpg)

## Manos a la obra!

Puede seguir esta receta utilizando una sola máquina o varias máquinas. Si está utilizando varias máquinas, debe elegir una máquina como nodo principal donde ejecutará HDFS NameNode y JobTracker. Si está utilizando una sola máquina, utilícela como nodo maestro y como nodo esclavo.

Configuremos Hadoop configurando JobTracker y TaskTrackers para diferentes clusters que pueden ser genuinamente una computadora extra; pero no te preocupes, puedes usar una máquina virtual para este próposito. Vamos allá!



En cada máquina, cree un directorio para los datos de Hadoop. Llamemos a este directorio HADOOP_DATA_DIR. Luego crea tres directorios, HADOOP_DATA_DIR/data, HADOOP_DATA_DIR/local, y HADOOP_DATA_DIR/name.

Configure las claves SSH para todas las máquinas para que podamos iniciar sesión en todas desde el nodo principal. La receta de configuración de HDFS describe la configuración de SSH en detalle.

Descomprima la distribución de Hadoop en la misma ubicación en todas las máquinas usando el comando> tar -zxvf hadoop-1.x.x.tar.gz. Puede utilizar cualquiera de las distribuciones de rama de Hadoop 1.0.

En todas las máquinas, edita el archivo HADOOP_HOME/conf/hadoop-env.sh  descomentando la línea JAVA_HOME apúntelo a su instalación local de Java. Por ejemplo, si Java está en /opt/jdk1.6, cambielo a JAVA_HOME para exportar JAVA_HOME=/opt/jdk1.6.

Coloque la dirección IP del nodo utilizado como maestro (para ejecutar JobTracker y NameNode) en HADOOP_HOME / conf / masters en una sola línea. Si está realizando una implementación de un solo nodo, deje el valor actual, localhost, como está.

 ```
209.126.198.72
 ```
Coloque las direcciones IP de todos los nodos esclavos en el archivo HADOOP_HOME / conf / slaves, cada uno en una línea separada.

 ```
209.126.198.72
209.126.198.71
 ```
Dentro del directorio HADOOP_HOME / conf de cada nodo, agregue lo siguiente a core-site.xml, hdfs-site.xml y mapred-site.xml. Antes de agregar las configuraciones, reemplace MASTER_NODE con la IP del nodo maestro y HADOOP_DATA_DIR con el directorio que creó en el primer paso.

Agregue la URL del NameNode a HADOOP_HOME / conf / core-site.xml.

 ```
<configuration>
<property>
<name>fs.default.name</name>
<value>hdfs://MASTER_NODE:9000/</value>
</property>
</configuration>
 ```
Agregue ubicaciones para almacenar metadatos (nombres) y datos dentro de HADOOP_HOME / conf / hdfs-site.xml para enviar trabajos:

 ```
<configuration>
<property>
<name>dfs.name.dir</name>
<value>HADOOP_DATA_DIR/name</value>
</property>
<property>
<name>dfs.data.dir</name>
<value>HADOOP_DATA_DIR/data</value>
</property>
</configuration>
 ```
Map reduce el directorio local es la ubicación utilizada por Hadoop para almacenar los archivos temporales utilizados. Agregue la ubicación de JobTracker a HADOOP_HOME / conf / mapred-site.xml. Hadoop usará esto para los trabajos. La propiedad final establece el máximo de tareas de mapa por nodo, lo mismo que la cantidad de núcleos (CPU).

 ```
<configuration>
<property>
<name>mapred.job.tracker</name>
<value>MASTER_NODE:9001</value>
</property>
<property>
<name>mapred.local.dir</name>
<value>HADOOP_DATA_DIR/local</value>
</property>
<property>
<name>mapred.tasktracker.map.tasks.maximum</name>
<value>8</value>
</property>
</configuration>
 ```
Para formatear un nuevo sistema de archivos HDFS, ejecute el siguiente comando desde Hadoop NameNode (nodo maestro). Si ha hecho esto como parte de la instalación de HDFS en la receta anterior, puede omitir este paso.

 ```
>bin/hadoop namenode –format
...
/Users/srinath/playground/hadoop-book/hadoop-temp/dfs/name has been successfully formatted.
12/04/09 08:44:51 INFO namenode.NameNode: SHUTDOWN_MSG:
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at Srinath-s-MacBook-Pro.local/172.16.91.1
************************************************************/
 ```
 
En el nodo maestro, cambie el directorio a HADOOP_HOME y ejecute los siguientes comandos:

 ```
>bin/start-dfs.sh
starting namenode, logging to /root/hadoop-setup-srinath/hadoop-1.0.0/libexec/../logs/hadoop-root-namenode-node7.beta.out
209.126.198.72: starting datanode, logging to /root/hadoop-setup-srinath/hadoop-1.0.0/libexec/../logs/hadoop-root-datanode-node7.beta.out
209.126.198.71: starting datanode, logging to /root/hadoop-setup-srinath/hadoop-1.0.0/libexec/../logs/hadoop-root-datanode-node6.beta.out
209.126.198.72: starting secondarynamenode, logging to /root/hadoop-setup-srinath/hadoop-1.0.0/libexec/../logs/hadoop-root-secondarynamenode-node7.beta.out
>bin/start-mapred.sh
starting jobtracker, logging to /root/hadoop-setup-srinath/hadoop-1.0.0/libexec/../logs/hadoop-root-jobtracker-node7.beta.out
209.126.198.72: starting tasktracker, logging to /root/hadoop-setup-srinath/hadoop-1.0.0/libexec/../logs/hadoop-root-tasktracker-node7.beta.out
209.126.198.71: starting tasktracker, logging to /root/hadoop-setup-srinath/hadoop-1.0.0/libexec/../logs/hadoop-root-tasktracker-node6.beta.out
 ```
Verifique la instalación enumerando los procesos a través de ps | comando grep java (si está usando Linux) o mediante el Administrador de tareas (si está en Windows), en el nodo maestro y en los nodos esclavos. El nodo maestro enumerará cuatro procesos: NameNode, DataNode, JobTracker y TaskTracker y los esclavos tendrán un DataNode y TaskTracker.

Explore las páginas de monitoreo basadas en web para namenode y JobTracker:

```
NameNode: http://MASTER_NODE:50070/.

JobTracker: http://MASTER_NODE:50030/.
```

Puede encontrar los archivos de registro en $ {HADOOP_HOME} / logs.

Asegúrese de que la configuración de HDFS sea correcta enumerando los archivos mediante la línea de comandos de HDFS.

```
bin/hadoop dfs -ls /

Found 2 items
drwxr-xr-x   - srinath supergroup    0 2012-04-09 08:47 /Users
drwxr-xr-x   - srinath supergroup    0 2012-04-09 08:47 /tmp
```
