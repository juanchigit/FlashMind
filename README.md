# Flashmind - Herramienta de Estudio de Vocabulario en Inglés

Flashmind es un software diseñado para facilitar el aprendizaje y la retención de vocabulario en inglés a través de tarjetas de estudio interactivas. Basado en el enfoque propuesto por Campayo (2007), Flashmind promueve un aprendizaje activo mediante la memorización y asociación de palabras. Este programa está orientado al autoestudio, permitiendo a los usuarios crear y utilizar sus propios mazos de tarjetas de vocabulario, adaptados a sus necesidades.

## Descripción del Sistema

Flashmind permite a los usuarios estudiar vocabulario en inglés de forma estructurada. El programa utiliza "mazos" de tarjetas organizados en directorios, y cada mazo contiene vocabulario en dos idiomas (español e inglés), junto con ayudas mnemotécnicas. Los mazos son creados a través de archivos CSV, y el sistema funciona mediante una interfaz de consola de texto, sin elementos gráficos.

## Alcance del Programa

- **Interfaz de consola:** El sistema se ejecuta en una consola de texto, sin interfaces gráficas.
- **Archivos CSV:** El usuario debe cargar manualmente los archivos CSV que contienen las palabras y las asociaciones mnemotécnicas.
- **Sin almacenamiento persistente:** Los resultados de cada sesión no se guardan una vez que el programa se cierra.
- **Sin soporte de audio o pronunciaciones:** El programa no ofrece transcripciones fonéticas ni pronunciaciones de las palabras.

## Requisitos Funcionales

1. **Directorio de Mazos:** El usuario debe proporcionar la ruta de un directorio que contenga los mazos.
2. **Menú Principal:** El sistema presenta un menú donde el usuario puede elegir entre:
    - Seleccionar un mazo.
    - Terminar el programa.
    - Ver los créditos del programa.
3. **Selección de Mazo:** El usuario puede elegir un mazo de los disponibles en el directorio.
4. **Dirección de Traducción:** El usuario puede seleccionar la dirección de traducción de las tarjetas (Español a Inglés o Inglés a Español).
5. **Barajado de Tarjetas:** El sistema permite al usuario configurar el orden de las tarjetas: aleatorio o secuencia fija.
6. **Presentación de Preguntas:** El programa muestra una tarjeta en el idioma de origen y espera que el usuario ingrese la respuesta. Luego, muestra la traducción correcta.
7. **Evaluación del Desempeño:** Después de cada tarjeta, el usuario evalúa su desempeño, lo que permite autoevaluarse.
8. **Resumen de Resultados:** Al finalizar la sesión, el sistema muestra un resumen del desempeño del usuario.
9. **Regreso al Menú Principal:** Después de la evaluación, el sistema regresa al menú principal, permitiendo seleccionar nuevas opciones.

## Estructura de los Archivos

Para que el programa funcione correctamente, los mazos deben estar organizados de la siguiente manera:

- **espanol.csv**: Contiene las palabras en español.
- **ingles.csv**: Contiene las palabras correspondientes en inglés.
- **mnemotecnia.csv**: Contiene ayudas mnemotécnicas para facilitar la memorización.
- **Mazo.csv**: Actúa como un índice y asigna IDs a las palabras y las ayudas mnemotécnicas.

### Detalles sobre los IDs

- **No es necesario colocar IDs en los archivos `espanol.csv`, `ingles.csv`, y `mnemotecnia.csv**: El programa detecta automáticamente el ID de cada palabra a partir del número de fila en el archivo CSV correspondiente.
  
- **Mazo.csv**: Este archivo contiene los IDs. Si decides normalizar las palabras, el ID de cada palabra debe coincidir con el número de fila correspondiente en los archivos CSV, con el fin de optimizar y economizar datos.

### Cómo crear un nuevo mazo

1. Crea un directorio para el mazo dentro de la carpeta madre.
2. Añade los archivos CSV mencionados con los nombres exactos: `espanol.csv`, `ingles.csv`, `mnemotecnia.csv`, y `Mazo.csv`.
3. Llena los archivos con las palabras/frases correspondientes.

## Requisitos

- Java 23 o superior.

## Uso

1. Inicia el programa.
2. Introduce la ruta del directorio que contiene los mazos.
3. Selecciona un mazo y la dirección de traducción (Español a Inglés o Inglés a Español).
4. Estudia las tarjetas, proporcionando tus respuestas y evaluando tu desempeño.
5. Al finalizar, obtendrás un resumen de tus resultados y podrás regresar al menú principal.

## Créditos

Este proyecto fue creado por Juan T y está basado en el enfoque propuesto por Campayo (2007) para el aprendizaje y memorización de vocabulario.

## Licencia

Este proyecto está licenciado bajo la Licencia Pública General de **GNU v3.0**. Consulta el archivo `LICENSE` para más detalles.

## Contacto

Si tienes alguna pregunta o sugerencia, puedes contactar a Juan Ignacio Tuite a través de [juanituite@gmail.com].

---

## Instrucciones para clonar el repositorio

1. **Copia la dirección del repositorio**: https://github.com/juanchigit/FlashMind.git
2. **Abre tu terminal o Git Bash**:
- En **Windows**, usa Git Bash.
- En **macOS** o **Linux**, abre la terminal.

3. **Escribe el siguiente comando en la terminal**:

```bash
git clone https://github.com/juanchigit/FlashMind.git
4. Presiona Enter.
