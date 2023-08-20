import java.io.*;

public class Prefijas {

    public static void main(String[] args) throws IOException {
        String archivo = "src\\car_sales.csv";

        BufferedReader csvLector = new BufferedReader(new FileReader(archivo));

        String ruta = "src\\resultado.csv";
        FileWriter archivoCSV = new FileWriter(ruta);
        BufferedWriter csvEscritor = new BufferedWriter(archivoCSV);

        String linea;
        int columnaID = 0; // Índice de la columna id
        int columna = 4; // Índice de la columna de precios
        double suma = 0;

        csvEscritor.write("id,Venta,Suma Prefija\n"); //division y escritura de columnas

        while ((linea = csvLector.readLine()) != null) {
            String[] fila = linea.split(","); //división de columnas

            if (fila.length > columna) {
                String valorID = fila[columnaID];
                String ingresos = fila[columna];

                //elimina todos los caracteres que no sean números o puntos
                String ingresosNum = ingresos.replaceAll("[^\\d.]", "");

                if (!ingresosNum.isEmpty()) {
                    try {
                        double ingresoDouble = Double.parseDouble(ingresosNum);
                        suma += ingresoDouble;

                        //escribe los valores en los resultados
                        csvEscritor.write(valorID + "," + ingresos + "," + suma + "\n");

                    } catch (NumberFormatException e) {
                        System.out.println("No se pudo convertir: " + ingresos);
                    }
                }
            }
        }

        csvLector.close();
        csvEscritor.close();

        System.out.println("Tabla de sumas prefijas guardada en " + ruta);
    }
}
