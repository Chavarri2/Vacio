Si al descargar descargar el archivo y importarlo al eclipse no funcion sigue los siguientes pasos
1. Abre las propiedades del proyecto
2. Selecciona la opcion de "Java Build Path"
3. Selecciona la opcion "Source"
4. Haz clic en "Add Folder"
5. Selecciona la carpeta "resources" y aplica los cambios
6. Intente ejecutar el proyecto de nuevo

Como eclipse es un programa que deja mucho que desear, para que el proyecto funcione correctamente debes buscar la clase Utilidades y escribir manualmente en el metodo "leerArchivo" la ruta absoluta de tu archivo en el ordenador. Esto se debe a que de los contrario habria que configurar muchisimas cosas y configurar Mavenmanualmente. Cosa que visual Studio hace de forma automatica, al cambiar el proyecto el propio programa eclipse desconfiguro toda la estructura de paquetes.
