ChallengeConversorDeMonedas
Este es un conversor de monedas desarrollado en Java que utiliza la ExchangeRate-API para obtener las tasas de cambio actualizadas entre diferentes monedas.

Monedas Soportadas
ARS - Peso argentino
BOB - Boliviano boliviano
BRL - Real brasileño
CLP - Peso chileno
COP - Peso colombiano
USD - Dólar estadounidense
Requisitos
Java JDK 11 o superior
IntelliJ IDEA Community Edition 2024.2.2
Biblioteca Gson
Clave de API de ExchangeRate-API
Cómo Ejecutar el Programa
Clona el repositorio o descarga el proyecto.

Abre el proyecto en IntelliJ IDEA.

Importa la biblioteca Gson si aún no lo has hecho:

Ve a File > Project Structure > Libraries y añade la biblioteca Gson.
Configura la clave de API:

Abre el archivo ExchangeRateAPI.java.
Reemplaza "TU_CLAVE_DE_API_AQUÍ" con tu clave de API obtenida de ExchangeRate-API.
java
Copy code
private static final String API_KEY = "TU_CLAVE_DE_API_AQUÍ"; // Reemplaza con tu clave de API
Ejecuta el programa:

Ejecuta la clase Main.java para iniciar el conversor.
Uso
Menú Principal:
Al iniciar el programa, se mostrará un menú con opciones para convertir moneda o salir.
Conversión de Monedas:
Selecciona la opción para convertir moneda.
Elige la moneda de origen y destino ingresando el número correspondiente.
Ingresa la cantidad que deseas convertir.
El programa mostrará el resultado de la conversión.
Autor
Desarrollado por Sergio Carey.
