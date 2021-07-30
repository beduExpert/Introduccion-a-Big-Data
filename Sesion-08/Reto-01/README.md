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

```
$ tar xzf apache-cassandra-2.1.2.bin.tar.gz –C /opt
```

Opcionalmente, puedes crear un "soft link", que nos ayudará en situaciones donde necesitamos cambiar la ubicación de instalación:

```
$ ln –s apache-cassandra-2.1.2 cassandra
```

El diseño de la instalación de Cassandra puede ser diferente según su tipo de instalación. Si está instalando Cassandra usando Debian o un paquete RPM, entonces la instalación creará los directorios requeridos y aplicará los permisos requeridos.

En versiones anteriores de Cassandra, es posible debamos crear directorios de datos y registros de Cassandra antes de ejecutarlo. De forma predeterminada, se instalan en a/var/lib/cassandra y /var/log/Cassandra. La ejecución de Cassandra fallará si el usuario que ejecuta Cassandra no tiene permisos para estos directories. Tú puedes crear y establecer permisos como se te muestra a continuación:

```
$ sudo mkdir -p /var/log/Cassandra
$ sudo chown -R `whoami` /var/log/Cassandra
$ sudo mkdir -p /var/lib/Cassandra
$ sudo chown -R `whoami` /var/lib/cassandra
```

¡Enhorabuena! Has instalado satisfactoriamente Cassandra. En caso de presentar algún problema, por favor indicarlo a tu Experto o TA asignado para resolver tu instalación.