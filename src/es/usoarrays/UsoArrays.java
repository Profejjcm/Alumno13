//La forma de hacer la refactorizacion se realiza directamente sobre el metodo. seleccionamos todo y gestionamos la refactorizacioón según el proceso indicado.
//No he sido capaz de crear la clase padre para mover los atributos públicos
package es.usoarrays;

import java.util.Arrays;

public class UsoArrays {

    public static void main(String[] args) {

        int[] control = new int[NUM_ALUMNOS];
        int[] practicas = new int[NUM_ALUMNOS];
        float[] calificaciones = new float[NUM_ALUMNOS]; //refactorizamos el num 40 a una constante con nombre NUM_ALUMNOS
        int maxNota = 0;
        int minNota = 0;
        int countAprobados = 0;
        int countSuspensos = 0;
        float[] estadistica = new float[10];

        generarNotas(control);

        minNota = buscarMenor(control, minNota);

        maxNota = buscarMayor(control, maxNota);

        //creamos el array de notas "practicas"
        for (int i = 0; i < practicas.length; i++) {
            practicas[i] = (int) (Math.random() * 11);
        }

        generarCalificaciones(control, calificaciones, practicas);

        generarEstadisticas(control, calificaciones, estadistica);

        //Método generarAprobadosSuspensos
        for (int i = 0; i < NUM_ALUMNOS; i++) {
            if (calificaciones[i] < 5) {
                countAprobados += 1;
            } else {
                countSuspensos += 1;
            }
        }
        //Método generarAprobadosSuspensos
        mostrarResultados(minNota, maxNota, control, practicas, calificaciones, countAprobados, countSuspensos);
    }
    public static final int NUM_ALUMNOS = 40;

    private static void mostrarResultados(int minNota, int maxNota, int[] control, int[] practicas, float[] calificaciones, int countAprobados, int countSuspensos) {
        //Método mostrarResultados
        System.out.println("La nota mínima es  : " + minNota);
        System.out.println("La nota máxima es  : " + maxNota);
        System.out.println("Array de Notas     :" + Arrays.toString(control));
        System.out.println("Prácticas          :" + Arrays.toString(practicas));
        System.out.println("Calificaciones     :" + Arrays.toString(calificaciones));
        System.out.println("Número de aprobados: " + countAprobados);
        System.out.println("Número de suspensos: " + countSuspensos);
        //Método mostrarResultados         
    }

    public static void generarEstadisticas(int[] control, float[] calificaciones, float[] estadistica) {
        //Método generarEstadisticas
        //Sacamos la estadística de calificaciones
        //hacemos un array de 10 para la estadística.      
        for (int i = 0; i < 10; i++) {
            float count = 0;
            float sum = 0;
            for (int j = 0; j < control.length; j++) {
                if ((i < calificaciones[j]) && ((i + 1) >= calificaciones[j])) {
                    sum += calificaciones[j];
                    count += 1;
                }
            }
            if (count != 0) {
                estadistica[i] = ((float) count / NUM_ALUMNOS);
            } else {
                estadistica[i] = 0;
            }
            double sol = (Math.round(estadistica[i] * 10000.0)) / 100.0;
            System.out.println("Estadística nota tramo <="
                    + (i + 1) + " = "
                    + sol + "%");
        }
        //Método generarEstadisticas   
    }

    public static void generarCalificaciones(int[] control, float[] calificaciones, int[] practicas) {
        //Método generarCalificaciones
        for (int i = 0; i < control.length; i++) {
            calificaciones[i]
                    = (((float) control[i]
                    + (float) practicas[i])
                    / 2);
        }
        //Método generarCalificaciones       
    }

    public static int buscarMayor(int[] control, int maxNota) {
        //Método buscarMayor
        int postEva2 = 0;
        for (int i = 0; i < control.length; i++) {
            int preEval = control[i];
            if (preEval > postEva2) {
                maxNota = preEval;
                postEva2 = control[i];
            }
        }
        //Método buscarMayor
        return maxNota;
    }

    public static int buscarMenor(int[] control, int minNota) {
        //Método buscarMenor
        int postEval = 11;
        for (int i = 0; i < control.length; i++) {
            int preEval = control[i];
            if (preEval < postEval) {
                minNota = preEval;
                postEval = control[i];
            }
        }
        //Método buscarMenor
        return minNota;
    }

    public static void generarNotas(int[] control) {
        //Método generarNotas
        //Genera notas random entre 1 y 10
        for (int i = 0; i < control.length; i++) {
            control[i] = (int) (Math.random() * 11);
        }
        //Método generarNotas
    }
}