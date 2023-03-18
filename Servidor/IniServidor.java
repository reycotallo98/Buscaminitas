package Servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import Servidor.*;
public class IniServidor {
	static DatagramSocket serverSocket;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	serverSocket = new DatagramSocket(3000);
		//DatagramSocket serverSocket=new DatagramSocket(3000,InetAddress.getByName("192.168.204.1"));    

	    String cadena;

	    ArrayList<Servidor> partidas = new ArrayList<>();
	    
	    while(true){
	      System.out.println(" Esperando Datagrama...");
	      // RECIBO DATAGRAMA
	      DatagramPacket paqRecibido=recibirmensaje();
	      cadena=new String(paqRecibido.getData());
	      
	      if (cadena.equals("q")) {
	      //DIRECCION ORIGEN
	    	  Jugador nuevo = new Jugador(paqRecibido.getAddress(),false,serverSocket,paqRecibido.getPort());
	    	  for(Servidor partida : partidas) {
	    	  if (partida.players<2) {
	    		  if(partida.players<1) {
	    			  partida.jugador1=nuevo;  
	    			  partida.players++;
	    			  ArrayList<Integer[]> a = new ArrayList<>();
	    			  for(int i = 0; i < 8;i++ ) {
	    				  for(int b = 0; b < 8;b++ ) {
	    					  ArrayList<Integer> ll = new ArrayList<>();
		    				  if(b < ((int)(Math.random()*7))) {
		    					  ll.add(-1);
		    				  }else {
		    					  ll.add(-2);
		    				  }
		    				 Collections.shuffle(ll);
		    				  a.add((Integer[]) ll.toArray());
		    				  
	    				  }
	    				  }
	    			  partida.tablero = (Integer[][]) a.toArray();
	    			  break;
	    		  }else if(partida.players<2){
	    			  partida.jugador2=nuevo;
	    			  partida.players++;
	    			  partida.run();
	    			  break;
	    		  }
	    		
	    		  
	    		  }
	    	  }
	      
	 
	      
	      
	    }
		
}
	}

private static DatagramPacket recibirmensaje() throws SocketException {
	
    byte[] buffer = new byte[1024];
    String mensaje = "";
 // Crea un objeto DatagramSocket que se utilizará para recibir paquetes de datos
    DatagramSocket socket = serverSocket;
    
    // Crea un objeto DatagramPacket que se utilizará para almacenar los datos recibidos
    DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
    try {
        
        
        // Utiliza el método receive del objeto DatagramSocket para recibir el paquete de datos
        socket.receive(paquete);
        
        // Convierte los datos recibidos en el tipo de dato adecuado para su procesamiento
        byte[] datosRecibidos = Arrays.copyOfRange(paquete.getData(), 0, paquete.getLength());
        mensaje = new String(datosRecibidos);
        
        // Imprime el mensaje recibido
        System.out.println("Mensaje recibido: " + mensaje);
        
        // Cierra el objeto DatagramSocket
        socket.close();
        
    } catch (Exception e) {
        System.out.println("Error al recibir el mensaje: " + e.getMessage());
    }
	return paquete;
}

private static void enviarMensaje(String mensaje) {
	
	
    byte[] datos = mensaje.getBytes();
    
    try {
        // Crea un objeto DatagramSocket para enviar y recibir paquetes de datos
        DatagramSocket socket = serverSocket;
        
        // Crea un objeto InetAddress que represente la dirección IP del destinatario
        InetAddress direccionDestinatario = InetAddress.getByName("nube5.anti-palilleros.com");
        
        // Crea un objeto DatagramPacket que contenga los datos a enviar, la dirección IP y el puerto del destinatario
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, direccionDestinatario, 3000);
        
        // Utiliza el método send del objeto DatagramSocket para enviar el paquete de datos al destinatario
        socket.send(paquete);
        
        // Cierra el objeto DatagramSocket
        socket.close();
        System.out.println("enviado");
    } catch (SocketException e) {
        System.out.println("Error al crear el objeto DatagramSocket: " + e.getMessage());
    } catch (UnknownHostException e) {
        System.out.println("Error al obtener la dirección IP del destinatario: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error al enviar el mensaje: " + e.getMessage());
    }
}

}
