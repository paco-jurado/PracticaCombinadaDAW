package AppBBDD;

/**
 *
 * @author FJJJ
 * Version instalada en SMR2 de prueba
 */

import GestionBD.DBManager;
import java.util.Scanner;


public class GestionClientes {

      /**
       * Atributo de clase que permite acceder a la entrada de teclado
       */
      static Scanner in = null;
   /**
    * Permite pasarle por linea de comando el driver, el servidor, la BBDD a utilizar,...
    * Por defecto, si no se le indica NADA ... ser� el SGBDR MySQL y la BBDD tienda  
    * @param args
    */
    public  static void main(String[] args) {

      if(args.length == 0)
        DBManager.connect();
      else {
                                                                    // Display the arguments from the command line
        for(int counter = 0; counter < args.length; counter++){
            System.out.println("argument index " + counter + ": " + args[counter]);    
        }
 //       DBManager.connect("","","",args[3],"");
        DBManager.connect(args[0],args[1],args[2],args[3],args[4]);
      }
              
        in = new Scanner(System.in); // �nico 

        boolean salir = false;
        do {
            salir = menuPrincipal();
        } while (!salir);

        DBManager.close();

    }

    static boolean menuPrincipal() {
        
        System.out.println("");
        System.out.println("MENU PRINCIPAL");
        System.out.println("1. Listar clientes");
        System.out.println("2. Nuevo cliente");
        System.out.println("3. Modificar cliente");
        System.out.println("4. Eliminar cliente");
        System.out.println("5. Salir");
     
        int opcion = pideInt("Elige una opci�n: ");
        
        switch (opcion) {
            case 1:
                opcionMostrarClientes();
                return false;
            case 2:
                opcionNuevoCliente();
                return false;
            case 3:
                opcionModificarCliente();
                return false;
            case 4:
                opcionEliminarCliente();
                return false;
            case 5:
                return true;
            default:
                System.out.println("Opci�n elegida incorrecta");
                return false;
        }
        
    }
    
    static int pideInt(String mensaje){
        int valor;
        while(true) {
            try {
                System.out.print(mensaje);
                valor = in.nextInt();
                return valor;
            } catch (Exception e) {
                System.out.println("No has introducido un número entero. Vuelve a intentarlo.");
            } finally {
              in.nextLine();
            }
        }
    }
    
    static String  pideLinea(String mensaje){
        String linea;
        
        while(true) {
            try {
                System.out.print(mensaje);
                linea = in.nextLine();
                return linea;
            } catch (Exception e) {
                System.out.println("No has introducido una cadena de texto. Vuelve a intentarlo.");
            }
        }
    }

    static void opcionMostrarClientes() {
        System.out.println("Listado de Clientes:");
        DBManager.printTablaClientes();
    }

    static void opcionNuevoCliente() {
      String nombre, direccion;
      
        System.out.println("Introduce los datos del nuevo cliente:");
        nombre =  pideLinea("Nombre: ");
        direccion = pideLinea("Direcci�n: ");

        boolean res = DBManager.insertCliente(nombre, direccion);

        if (res) {
            System.out.println("Cliente registrado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }

     static void opcionModificarCliente() {
        int id;
        boolean res;

        id = pideInt("Indica el id del cliente a modificar: ");
        if (!DBManager.existsCliente(id)) {                                 // Comprobamos si existe el cliente
            System.out.println("El cliente " + id + " no existe.");
            return;
        }
       
        DBManager.printCliente(id);                                         // Mostramos datos del cliente a modificar
                                                                            // Solicitamos los nuevos datos
        String nombre = pideLinea("Nuevo nombre: ");
        String direccion = pideLinea("Nueva direcci�n: ");
        
        res = DBManager.updateCliente(id, nombre, direccion);               // Registramos los cambios
        if (res) {
            System.out.println("Cliente modificado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }

    static void opcionEliminarCliente() {
      int id;
      boolean res;
      
        id = pideInt("Indica el id del cliente a eliminar: ");
                                                                            // Comprobamos si existe el cliente
        if (!DBManager.existsCliente(id)) {
            System.out.println("El cliente " + id + " no existe.");
            return;
        }
        res = DBManager.deleteCliente(id);                                  // Eliminamos el cliente
        if (res) {
            System.out.println("Cliente eliminado correctamente");
        } else {
            System.out.println("Error :(");
        }
    }
}
