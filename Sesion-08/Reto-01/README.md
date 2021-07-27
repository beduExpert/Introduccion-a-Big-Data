# Reto #1 - Instalación de Cassandra

## Objetivo

* Este reto es acompañado de tu Experto y TA, por lo tanto, no estarás solo en su desarrollo. Aprenderemos como instalar Apache Cassandra

## Desarrollo

El siguiente método de instalación es menos utilizado. Uno de los casos en los que podríamos usar este método es si estamos haciendo algún trabajo de optimización en Cassandra. Necesitaremos JDK 1.7, ANT 1.8 o versiones posteriores para compilar el código de Cassandra. Opcionalmente, podemos clonar directamente desde el repositorio de Cassandra Git o podemos usar el tarball de origen. Se requerirá el cliente Git 1.7 para clonar el repositorio de git.

Para obtener el último código fuente de Git, use el siguiente comando:

```
$ git clone http://git://git-wip-us.apache.org/repos/asf/cassandra.git Cassandra
```

Para una rama específica, use el siguiente comando:
```
$ git clone -b cassandra-<version> http://git://git-wip-us.apache.org/repos/asf/cassandra.git
```

Por ejemplo, usa este comando para la versión 1.2:

```
$ git clone -b cassandra-2.1.2 http://git://git-wip-us.apache.org/repos/asf/cassandra.git
```

Luego, use el comando ant para construir el código:
```
$ ant
```

Alternativamente, si se necesita un proxy para conectarse a Internet, use el indicador de autoproxy:

```
$ ant –autoproxy
```

o

```
$ export ANT_OPTS="-Dhttp.proxyHost=<your-proxy-host> -Dhttp.proxyPort=<your-proxy-port>"
```

# Instalación clásica desd un archivo binario precompilado:

Hay queescargar un tarball binario del sitio web de Apache; ábralo usando el siguiente comando. Aquí, lo extraeremos en el directorio /opt:

```
$ tar xzf apache-cassandra-<Version>.bin.tar.gz –C /opt
```

Considere el siguiente ejemplo:

