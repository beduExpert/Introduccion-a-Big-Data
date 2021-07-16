# Ejemplo 2: Spark RDD

Los conjuntos de datos distribuidos resistentes o Resilient Distributed Datasets (RDD) son el componente básico de una aplicación Spark. Un RDD representa una colección de objetos de solo lectura distribuidos en varias máquinas. Spark puede distribuir una colección de registros utilizando un RDD y procesarlos en paralelo en diferentes máquinas.

Para aprender más sobre RDD, abordaremos las siguientes preguntas:

- ¿Qué es un RDD?
- ¿Cómo se crean los RDD?
- Diferentes operaciones disponibles para trabajar en RDD
- Tipos importantes de RDD
- Almacenamiento en caché de un RDD
- Particiones de un RDD
- Inconvenientes de usar RDD

## Nota importantes
En los siguientes ejemplos, usaremos Python y Scala parar poder enfocar y concretar el aprendizaje del dominio sobre RDD, pero si deseas usar ya sea R o Java, puedes consultar la documentación de Spark disponible en: https://spark.apache.org/

## ¿Que es RDD?

Literalmente, RDD podría ser considerado el corazón de todas las aplicaciones de Spark. Entendamos el significado de cada palabra con más detalle:

# Resiliente (Resilient)

Resiliente: Si miramos el significado de resiliente en el diccionario, podemos ver que significa ser: capaz de recuperarse rápidamente de condiciones difíciles. Spark RDD tiene la capacidad de recrearse a sí mismo si algo sale mal. Debes preguntarte, ¿por qué necesita recrearse a sí mismo? ¿Recuerda cómo HDFS y otros almacenes de datos logran la tolerancia a fallas? Sí, estos sistemas mantienen una réplica de los datos en varias máquinas para recuperar en caso de falla. Pero, como se discutió previamente, Spark no es un almacén de datos; Spark es un motor de ejecución. Lee los datos de los sistemas de origen, los transforma y los carga en el sistema de destino. Si algo sale mal al realizar cualquiera de los pasos anteriores, perderemos los datos. Para proporcionar tolerancia a fallos durante el procesamiento, un RDD se vuelve resistente: se puede volver a calcular a sí mismo. Cada RDD mantiene cierta información sobre su RDD principal y cómo se creó a partir de su principal. Esto nos introduce en el concepto de linaje. La información sobre el mantenimiento del padre y la operación se conoce como linaje. El linaje solo se puede lograr si sus datos son inmutables. ¿Qué quiero decir con eso? Si pierde el estado actual de un objeto y está seguro de que el estado anterior nunca cambiará, siempre puede volver atrás y usar su estado anterior con las mismas operaciones, y siempre recuperará el estado actual del objeto. Esto es exactamente lo que sucede en el caso de los RDD. Si le resulta difícil, ¡no se preocupe! Quedará claro cuando veamos cómo se crean los RDD.
La inmutabilidad también aporta otra ventaja: la optimización. Si sabe que algo no cambiará, siempre tiene la oportunidad de optimizarlo. Si presta mucha atención, todos estos conceptos están conectados, como lo ilustra el siguiente diagrama:

![resilient](assets/resilient.png)

Distribuido (Distribuido): como se menciona en la siguiente viñeta, un conjunto de datos no es más que una colección de objetos. Un RDD puede distribuir su conjunto de datos en un conjunto de máquinas, y cada una de estas máquinas será responsable de procesar su partición de datos. Si viene de un fondo de Hadoop MapReduce, puede imaginar las particiones como las divisiones de entrada para la fase del mapa.

Dataset (Conjunto de datos): un conjunto de datos es solo una colección de objetos. Estos objetos pueden ser un objeto complejo Scala, Java o Python; números; instrumentos de cuerda; filas de una base de datos; y más.

![clust](assets/clust.png)

## Metadata en RDD

Como hemos comentado, además de las particiones, un RDD también almacena algunos metadatos dentro de él. Estos metadatos ayudan a Spark a recalcular una partición RDD en caso de falla y también proporcionan optimizaciones mientras se realizan operaciones.

Los metadatos incluyen lo siguiente:

- Una lista de dependencias de RDD principales.
- Una función para calcular una partición a partir de la lista de RDD principales.
- La ubicación preferida para las particiones.
- La información de partición, en caso de par RDD.

Entonces, ¡basta de teoría! Creemos un programa simple y comprendamos los conceptos con más detalle en la siguiente sección.