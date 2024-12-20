import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

abstract class ComponenteAplicacion {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerEntrada(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public abstract void cerrarAplicacion();

    public void cerrarScanner() {
        scanner.close();
    }
}

class CreditosApp extends ComponenteAplicacion {
    @Override
    public void cerrarAplicacion() {
        mostrarMensaje("¡Gracias por usar Flashmind!");
        cerrarScanner();
        System.exit(0);
    }

    public void mostrarCreditos() {
        mostrarMensaje("Créditos de Flashmind:");
        mostrarMensaje("Desarrollado por Tuite Juan Ignacio");
        mostrarMensaje("Versión 1.0.0");
        mostrarMensaje("Inspirado en: *Aprende inglés en 7 días* de R. Campayo (2007).");
    }
}

class FlashcardApp extends ComponenteAplicacion {
    private Mazo mazo;

    public void iniciar() {
        mostrarMensaje("Bienvenido a Flashmind!");
        Resultados resultados = new Resultados();
        boolean continuar = true;

        String directorioBase = leerEntrada("Introduce la ruta del directorio donde están las carpetas de los mazos: ");
        
        while (continuar) {
            mostrarMensaje("\n--- FLASHMIND ---");
            String opcionPrincipal;
            while (true) {
                opcionPrincipal = leerEntrada("1. Seleccionar Mazo\n2. Terminar programa\n3. Créditos\n\nElige una opción: ");
                if (opcionPrincipal.equals("1") || opcionPrincipal.equals("2") || opcionPrincipal.equals("3")) {
                    break;
                } else {
                    mostrarMensaje("Opción no válida. Por favor elige una opción válida.");
                }
            }
            
            if (opcionPrincipal.equals("2")) {
                continuar = false;
                cerrarAplicacion();
                break;
            }

            if (opcionPrincipal.equals("3")) {
                CreditosApp creditosApp = new CreditosApp();
                creditosApp.mostrarCreditos();
                continue;
            }

            File directorio = new File(directorioBase);
            String[] carpetas = directorio.list((current, name) -> new File(current, name).isDirectory());

            if (carpetas == null || carpetas.length == 0) {
                mostrarMensaje("No se encontraron carpetas de mazos en la ruta especificada.");
                continue;
            }

            int seleccionMazo;
            while (true) {
                mostrarMensaje("\nSelecciona el Mazo disponible:");
                for (int i = 0; i < carpetas.length; i++) {
                    mostrarMensaje((i + 1) + ". " + carpetas[i]);
                }
                seleccionMazo = Integer.parseInt(leerEntrada("Elige una opción: ")) - 1;
                if (seleccionMazo >= 0 && seleccionMazo < carpetas.length) {
                    break;
                } else {
                    mostrarMensaje("Selección no válida. Intenta de nuevo.");
                }
            }

            String nombreCarpetaMazo = carpetas[seleccionMazo];
            String rutaMazo = directorioBase + File.separator + nombreCarpetaMazo;

            int direccion;
            while (true) {
                String direccionInput = leerEntrada("\nDirección de Traducción:\n1. Español/Inglés\n2. Inglés/Español\nElige una opción: ");
                if (direccionInput.equals("1") || direccionInput.equals("2")) {
                    direccion = Integer.parseInt(direccionInput);
                    break;
                } else {
                    mostrarMensaje("Opción no válida. Intenta de nuevo.");
                }
            }

            boolean aleatorio;
            while (true) {
                String ordenInput = leerEntrada("\nOrden de Barajado:\n1. Aleatorio\n2. Secuencia fija\nElige una opción: ");
                if (ordenInput.equals("1")) {
                    aleatorio = true;
                    break;
                } else if (ordenInput.equals("2")) {
                    aleatorio = false;
                    break;
                } else {
                    mostrarMensaje("Opción no válida. Intenta de nuevo.");
                }
            }

            mazo = new Mazo(rutaMazo);

            List<Flashcard> flashcards = mazo.getFlashcards(aleatorio);
            for (Flashcard flashcard : flashcards) {
                String pregunta = flashcard.getPregunta(direccion);
                mostrarMensaje("***********************************************");
                mostrarMensaje("\nPregunta: " + pregunta);
                leerEntrada("Presiona Enter para ver la respuesta...");
                String respuesta = flashcard.getRespuesta(direccion);
                mostrarMensaje("");
                mostrarMensaje("Respuesta: " + respuesta);
                mostrarMensaje("Mnemotecnia: " + flashcard.getMnemotecnia());

                int calificacion;
                while (true) {
                    String calificacionInput = leerEntrada("Califica tu respuesta (1: Mala, 2: Regular, 3: Excelente): ");
                    if (calificacionInput.equals("1") || calificacionInput.equals("2") || calificacionInput.equals("3")) {
                        calificacion = Integer.parseInt(calificacionInput);
                        resultados.agregarCalificacion(calificacion);
                        break;
                    } else {
                        mostrarMensaje("Calificación no válida. Intenta de nuevo.");
                    }
                }
            }
            resultados.mostrarResultados();
            resultados.resetearResultados();
        }
    }

    @Override
    public void cerrarAplicacion() {
        mostrarMensaje("¡Gracias por usar Flashmind!");
        cerrarScanner();
        System.exit(0);
    }
}

class Flashcard {
    protected String espanol;
    protected String ingles;
    protected String mnemotecnia;

    public Flashcard(String espanol, String ingles, String mnemotecnia) {
        this.espanol = espanol;
        this.ingles = ingles;
        this.mnemotecnia = mnemotecnia;
    }

    public String getPregunta(int direccion) {
        return (direccion == 1) ? espanol : ingles;
    }

    public String getRespuesta(int direccion) {
        return (direccion == 1) ? ingles : espanol;
    }

    public String getMnemotecnia() {
        return mnemotecnia;
    }
}

class Resultados {
    private int malas = 0;
    private int regulares = 0;
    private int excelentes = 0;

    public void agregarCalificacion(int calificacion) {
        switch (calificacion) {
            case 1 -> malas++;
            case 2 -> regulares++;
            case 3 -> excelentes++;
            default -> System.out.println("Calificación no válida.");
        }
    }

    public void mostrarResultados() {
        System.out.println("\n*** Resultados ***");
        System.out.println("Malas: " + malas);
        System.out.println("Regulares: " + regulares);
        System.out.println("Excelentes: " + excelentes);
        System.out.println("******************");
    }

    public void resetearResultados() {
        malas = 0;
        regulares = 0;
        excelentes = 0;
    }
}

class Mazo {
    private List<Flashcard> flashcards = new ArrayList<>();

    public Mazo(String rutaMazo) {
        cargarFlashcards(rutaMazo);
    }

    private void cargarFlashcards(String rutaMazo) {
        List<String> espanolList = cargarArchivoCSV(rutaMazo + File.separator + "espanol.csv");
        List<String> inglesList = cargarArchivoCSV(rutaMazo + File.separator + "ingles.csv");
        List<String> mnemotecniaList = cargarArchivoCSV(rutaMazo + File.separator + "mnemotecnia.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaMazo + File.separator + "Mazo.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] partes = line.split(",");
                int idEspanol = Integer.parseInt(partes[1]) - 1;
                int idIngles = Integer.parseInt(partes[2]) - 1;
                int idMnemotecnia = Integer.parseInt(partes[3]) - 1;

                String espanol = espanolList.get(idEspanol);
                String ingles = inglesList.get(idIngles);
                String mnemotecnia = mnemotecniaList.get(idMnemotecnia);

                flashcards.add(new Flashcard(espanol, ingles, mnemotecnia) {});
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las flashcards: " + e.getMessage());
        }
    }

    private List<String> cargarArchivoCSV(String archivo) {
        List<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                lista.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo CSV: " + e.getMessage());
        }
        return lista;
    }

    public List<Flashcard> getFlashcards(boolean aleatorio) {
        if (aleatorio) {
            Collections.shuffle(flashcards, new Random());
        }
        return flashcards;
    }
}

public class FlashMind {
    public static void main(String[] args) {
        FlashcardApp app = new FlashcardApp();
        app.iniciar();
    }
}
