# Reto 02 Corriendo un servidor de cassandra

## Objetivo

* Aprender a correr un server básico con Apache Cassandra

## Nota:
Recuerda que esta sesión final es una mera introducción de lo más elemental de Cassandra, por tanto, puedes desarrollar el reto en solitario pero lo recomendable es desarrollarlo junto con tu Experto o TA. En modulos posteriores profundizarás en su aplicación y desarrollo

## Desarrollo

Primero debemos checar que versioon de java tenemos:

```
$ java –version
```

Dado que estamos ejecutando un solo nodo, podemos omitir configuraciones e iniciar directamente nuestro nodo. Ejecute el nodo Cassandra usando el comando para la instalación de tarball:

```
$ bin/Cassandra
```

Podemos parar el server con el siguiente commando:

```
$ pgrep -u `whoami` -f cassandra | xargs kill -9
```

Ahora veamos el status del nodo con el siguiente comando:

```
$ nodetool status

```

Ahora, usemos el comando nodetool info para verificar las estadísticas de los nodos individuales, como su tiempo de actividad, detalles de almacenamiento en caché, detalles de carga, etc. Analizaremos el almacenamiento en caché de Cassandra:

```
$ nodetool info
```
```
ID               : 2f9bb0a9-db48-4146-83c6-4ce06bd22259
Gossip active    : true
Thrift active    : true
Native Transport active: true
Load             : 179.4 MB
Generation No    : 1422937400
Uptime (seconds) : 593431
Heap Memory (MB) : 474.63 / 920.00
Data Center      : datacenter1
Rack             : rack1
Exceptions       : 0
Key Cache        : entries 226, size 23.07 KB, capacity 45 MB, 4714 hits, 5006 requests, 0.942 recent hit rate, 14400 save period in seconds
Row Cache        : entries 0, size 0 bytes, capacity 600 MB, 0 hits, 0 requests, NaN recent hit rate, 3000 save period in seconds
Counter Cache    : entries 0, size 0 bytes, capacity 22 MB, 0 hits, 0 requests, NaN recent hit rate, 7200 save period in seconds
Token            : (invoke with -T/--tokens to see all 256 tokens)
```

¡Enhorabuena! prosigamos al siguiente ejemplo donde aprenderemos como crear una database y un schema en Cassandra.