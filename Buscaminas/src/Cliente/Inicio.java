package Cliente;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

public class Inicio {
  private static DatagramSocket server;
  private static Integer[][] array = new Integer[8][8];
public static void main(String[] args) throws IOException, URISyntaxException {
	 server = new DatagramSocket(3000);
	
	 
	  byte[] recibidos=new byte[1024];
	    byte[] enviados=new byte[1024];
	    String cadena;
	    boolean turno = false;
		boolean jugando = false;
			for (int i = 0; i < array.length; i++) {
for (int j = 0; j < array.length; j++) {
array[i][j] = -3;

}
}
Tablero tablero;

enviarMensaje("q");
System.out.println("Esperando oponente....");










while(true) {
	cadena= recibirmensaje(); 
	  
	if (cadena.contains("empieza")){
		
	System.out.println("Empiezo");

	
	actualizartablero();
	


	}else if(cadena.contains("mueve")) {
  turno = true;
  tablero = new Tablero(array, turno);
  
  
}else if(cadena.contains("resultado")){
	
	String men = cadena.substring(cadena.indexOf(":")+1);
	int opcion = JOptionPane.showConfirmDialog(null, "Has "+men+"¿Quieres volver a jugar?", "Sí", JOptionPane.OK_CANCEL_OPTION);

	if(opcion == JOptionPane.OK_OPTION) {
	    // el usuario ha pulsado Aceptar
		String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
		File currentJar = new File(Inicio.class.getProtectionDomain().getCodeSource().getLocation().toURI());

		/* is it a jar file? */
		if(!currentJar.getName().endsWith(".java"))
		    return;

		/* Build command: java -jar application.jar */
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-java");
		command.add(currentJar.getPath());

		ProcessBuilder builder = new ProcessBuilder(command);
		builder.start();
		System.exit(0);
	} else {
	    // el usuario ha pulsado Cancelar
		System.exit(0);
	}
//}else if(cadena.contains("revelado")) {
//	String sub = cadena.substring(cadena.indexOf(":"));
//	String aux = "";
//	ArrayList<Integer[]> au = new ArrayList<>();
//	for(char b : sub.toCharArray()) {
//		if(b == '-') {
//			Integer[] a = {0,0};
//			a[0] = Integer.parseInt(aux.substring(0,aux.indexOf(",")));
//			a[1] = Integer.parseInt(aux.substring(0));
//			au.add(a);
//			aux = "";
//		}else {
//			aux += b;
//		}
//			
//			
//		}
//	
//	for (Integer[] integers : au) {
//		array[integers[0]][integers[1]] = 0;
//	}
	}}
}
		
		
		
  
  
  
  
  
  
  
private static void actualizartablero () throws IOException {
	   
		for (int i = 0; i < array.length; i++) {
	String[] a = recibirmensaje().split(",");
	
	
			for (int j = 0; j < array.length; j++) {
				
				
				array[i][j] = Integer.parseInt(a[j]);
			}
			
		}
		}
		  
	   
   

private static String recibirmensaje() {
	
    byte[] buffer = new byte[1024];
    String mensaje = "";
    try {
        // Crea un objeto DatagramSocket que se utilizará para recibir paquetes de datos
        DatagramSocket socket = server;
        
        // Crea un objeto DatagramPacket que se utilizará para almacenar los datos recibidos
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
        
        // Utiliza el método receive del objeto DatagramSocket para recibir el paquete de datos
        socket.receive(paquete);
        
        // Convierte los datos recibidos en el tipo de dato adecuado para su procesamiento
        mensaje = new String(paquete.getData());
        mensaje.split("");
        // Imprime el mensaje recibido
        System.out.println("Mensaje recibido: " + mensaje);
        
        // Cierra el objeto DatagramSocket
      
        
    } catch (Exception e) {
        System.out.println("Error al recibir el mensaje: " + e.getMessage());
    }
	return mensaje;
}

private static void enviarMensaje(String mensaje) {
	
	
    byte[] datos = mensaje.getBytes();
    
    try {
        // Crea un objeto DatagramSocket para enviar y recibir paquetes de datos
        DatagramSocket socket = server;
        
        // Crea un objeto InetAddress que represente la dirección IP del destinatario
        InetAddress direccionDestinatario = InetAddress.getByName("nube5.anti-palilleros.com");
        
        // Crea un objeto DatagramPacket que contenga los datos a enviar, la dirección IP y el puerto del destinatario
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccionDestinatario, 3000);
        
        // Utiliza el método send del objeto DatagramSocket para enviar el paquete de datos al destinatario
        socket.send(paquete);
        
        // Cierra el objeto DatagramSocket
      
        System.out.println("enviado");
    } catch (SocketException e) {
        System.out.println("Error al crear el objeto DatagramSocket: " + e.getMessage());
    } catch (UnknownHostException e) {
        System.out.println("Error al obtener la dirección IP del destinatario: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error al enviar el mensaje: " + e.getMessage());
    }
}

private static void perdido() {
	// TODO Auto-generated method stub
	
}}
