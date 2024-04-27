

import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

public class AddressBook {


    public static void main(String[] args) {



        HashMap<String, String> mapaCon = new HashMap<String, String>(); // creacion del objeto de tipo hashmap
        Scanner leer = new Scanner(System.in);


        int opc, ban = 0;
        String telefono, nombre;
        System.out.println("**AGENDA TELEFONICA**\n");

        do
        {
            try{

                System.out.println("\nSeleccione la opción deseada:\n");
                System.out.println("CARGAR DATOS  -------------  [1]");
                System.out.println("GUARDAR DATOS -------------  [2]");
                System.out.println("MOSTRAR CONTACTOS ---------  [3]");
                System.out.println("NUEVO NUMERO  -------------  [4]");
                System.out.println("BORRAR CONTACTO -----------  [5]");
                System.out.println("SALIR  --------------------  [6]");

                opc = leer.nextInt();

                /// Menu de opciones
                switch(opc)
                {
                    case 1:
                        load(mapaCon); // llamado a metodo cargar datos
                        break;

                    case 2:
                        save(mapaCon); // llamado a metodo guardar datos
                        break;

                    case 3:
                        list(mapaCon);  // llamado a mostrar datos de hasmap
                        break;

                    case 4:

                        System.out.println("Inserte el nuevo telefono: ");
                        telefono = leer.next();
                        System.out.println("Inserte el nombre del contacto: ");
                        nombre = leer.next();
                        create(mapaCon,telefono,nombre);  // llamado a metodo crear contacto
                        break;
                    case 5:

                        System.out.println("Inserte el telefono a eliminar: ");
                        telefono = leer.next();

                        delete(mapaCon, telefono);  // llamado a metodo de eliminar contacto
                        break;

                    case 6:
                        System.out.println("Saliendo\n");
                        ban = 1;
                        break;

                    default:
                        System.out.println("Opción incorrecta\n");
                        break;


                }
            }
            catch (Exception e)
            {
                System.out.println("ERROR !!\n");
                break;

            }
        }while(ban == 0);

    }

    public static void list(HashMap<String, String> mapaCon)
    {
        Iterator<String> iterator = mapaCon.keySet().iterator();  //creo un iterador para poder recorrer el hashmap

        System.out.println("Contactos:\n");
        System.out.println("  Telefono\t|\tNombre   ");
        System.out.println("------------------------");
        while(iterator.hasNext())                                //en un ciclo recorremos el hashmap
        {
            String llave = iterator.next();                      // el iterador se llama llave que esta funcionando como referencia en el key del hashmap

            System.out.println("  "+llave+"\t|\t"+mapaCon.get(llave)); // se muestra en pantalla el contenido del hashmap
        }

    }

    // metodo de añadir contaacto
    public static void create(HashMap<String, String> mapaCon, String tel, String nom)
    {

        if(mapaCon.containsKey(tel))
        {
            System.out.println("\nError!\nNo se puede registrar dos veces el mismo telefono\n"); // mensaje si el telefono ya existe
        }

        else
        {
            mapaCon.put(tel, nom);  //si no existe se crea el contacto
            System.out.println("\nContacto agregado");
        }

    }

    // metodo de borrar
    public static void delete(HashMap<String, String> mapaCon, String tel)
    {
        if(mapaCon.containsKey(tel))        //si el telefono existe se procede a removerlo del hashmap
        {
            System.out.println("\nContacto eliminado: "+mapaCon.get(tel)+"\n");
            mapaCon.remove(tel);            // se remueven las coincidencias con el dato clave

        }
        else
            System.out.println("\nEl Telefono no existe\n"); // si no entonces se manda un mensaje


    }

    // metodo de cargar datos desde archivo csv
    public static void load(HashMap<String, String> m)
    {
        String inputFilename = "C:\\Users\\JAIR\\IdeaProjects\\Actividad12\\src\\Contactos\\input.csv";
        String a [];                /// array auxiliar

        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                a = line.split(",");        // se llena el array con los datos separados por la ","
                m.put(a[0],a[1]);           // se llena el hashmap con los datos del array
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println("\nContactos cargados");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }

    }

    //metodo de guardar datos en archivo csv
    public static void save(HashMap<String, String> m)
    {
        Iterator<String> iterator = m.keySet().iterator();
        String inputFilename = "C:\\Users\\JAIR\\IdeaProjects\\Actividad12\\src\\Contactos\\input.csv";

        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(inputFilename));

            while(iterator.hasNext())                                //en un ciclo recorremos el hashmap
            {
                String llave = iterator.next();                      // el iterador se llama llave que esta funcionando como referencia en el key del hashmap


                bufferedWriter.write(llave+","+m.get(llave)+"\n");   // Se escribe el hashmap en el archivo input
            }

        }
        catch(IOException e) {
            System.out.println("IOException catched while writing: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                    System.out.println("\nCambios guardados");
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }
}
