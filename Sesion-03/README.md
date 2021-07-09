
# Sesión # 3:MapReduce en Hadoop

## :dart: Objetivos

Durante muchos años, los usuarios que desean almacenar y analizar datos almacenan los datos en una base de datos y los procesan a través de consultas SQL. La Web ha cambiado la mayoría de los supuestos de esta era. En la Web, los datos no están estructurados y son grandes, y las bases de datos no pueden capturar los datos en un esquema ni escalarlos para almacenarlos y procesarlos.

Google fue una de las primeras organizaciones en enfrentar el problema, donde querían descargar todo Internet e indexarlo para admitir consultas de búsqueda. Construyeron un marco para el procesamiento de datos a gran escala tomando prestado de las funciones de "mapa" y "reducción" del paradigma de programación funcional. Llamaron al paradigma MapReduce.

Hadoop es la implementación más conocida y utilizada del paradigma MapReduce. Esta sesión presenta Hadoop, describe cómo instalar Hadoop y le muestra cómo ejecutar su primer trabajo MapReduce con Hadoop.

La instalación de Hadoop consta de cuatro tipos de nodos: un NameNode, DataNodes, JobTracker y TaskTracker Los nodos HDFS (NameNode y DataNodes) proporcionan un sistema de archivos distribuido donde JobTracker administra los trabajos y TaskTracker ejecuta tareas que realizan partes del trabajo. Los usuarios envían trabajos MapReduce al JobTracker, que ejecuta cada una de las partes Map y Reduce del trabajo inicial en TaskTrackers, recopila resultados y finalmente emite los resultados.

Hadoop ofrece tres opciones de instalación:

- Modo local: este es un modo de descomprimir y ejecutar para comenzar de inmediato, donde todas las partes de Hadoop se ejecutan dentro de la misma JVM

- Modo pseudodistribuido: este modo se ejecutará en diferentes partes de Hadoop como diferentes procesadores Java, pero dentro de una sola máquina

- Modo distribuido: esta es la configuración real que abarca varias máquinas



## 📂 Organización de la clase

- [Ejemplo 01:  MapReduce](./Ejemplo-01/README.md)
    - [Reto 01: Aprende a implementar MapReduce 01](./Reto-01/README.md)
- [Ejemplo 02:  Generar un WordCount con MapReduce](./Ejemplo-02/README.md)
    - [Reto 02: Tu propio MapReduce](./Reto-01/README.md)
- [Ejemplo 03:  Configuración de tu nodo inicial](./Ejemplo-03/Ejemplo-03.md)
    - [Reto 03: Abre en el navegador web tu cluster emulado](./Reto-03/reto-03.md)




