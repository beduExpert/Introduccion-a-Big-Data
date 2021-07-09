## Reto 03: Usar la interfaz de usuario de monitoreo HDFS

HDFS viene con una consola web de monitoreo para verificar la instalación y monitorear el clúster HDFS. También permite a los usuarios explorar el contenido del sistema de archivos HDFS. En esta receta, veremos cómo podemos acceder a la IU de monitoreo de HDFS y verificar la instalación.

## Manos a la obra

- Deberás iniciar el HDFS como se describio en el ejemplo pasado, de tal formar que seas capaz de ver el MasterNode en tu navegador web. Guiate en el ejemplo anterior para hacer este reto. En caso de no tener éxito, pide auxilio del Experto

# Solución

Acceda al enlace  ``` http: // MASTER_NODE: 50070 /  ``` usando su navegador y verifique que puede ver la página de inicio de HDFS. Aquí, reemplace MASTER_NODE con la dirección IP del nodo maestro que ejecuta HDFS NameNode.

La siguiente captura de pantalla muestra el estado actual de la instalación de HDFS, incluido el número de nodos, el almacenamiento total y el almacenamiento que ocupa cada nodo. También permite a los usuarios navegar por el sistema de archivos HDFS.

![1](assets/1.jpg)

