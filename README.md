Si al descargar descargar el archivo y importarlo al eclipse no funcion sigue los siguientes pasos
1. Abre las propiedades del proyecto
2. Selecciona la opcion de "Java Build Path"
3. Selecciona la opcion "Source"
4. Haz clic en "Add Folder"
5. Selecciona la carpeta "resources" y aplica los cambios
6. Intente ejecutar el proyecto de nuevo

**Es posible que aún asi esta configuración no funcione**

Como eclipse es un programa que deja mucho que desear, para que el proyecto funcione correctamente debes:
1. buscar la clase Utilidades
2. Escribir manualmente en el metodo "leerArchivo" la ruta absoluta de tu archivo en el ordenador.

Esto se debe a que el Maven en Eclipse no funciona de forma nativa y presenta muchos errores. Como Visual Studio hace esto de forma automatica, al cambiar el proyecto desde ese IDE el propio programa Eclipse desconfiguro toda la estructura de paquetes.
