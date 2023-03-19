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
	    partidas.add(new Servidor());
	    while(true){
	      System.out.println(" Esperando Datagrama...");
	      // RECIBO DATAGRAMA
	      DatagramPacket paqRecibido=recibirmensaje();
	      cadena=new String(paqRecibido.getData());
	      
	      if (cadena.contains("q")) {
	      //DIRECCION ORIGEN
	    	  Jugador nuevo = new Jugador(paqRecibido.getAddress(),false,serverSocket,paqRecibido.getPort() );
	    	  System.out.println("nuevo jugador");
	    	  for(Servidor partida : partidas) {
	    	  if (partida.players<2) {
	    		  if(partida.players<1) {
	    			  partida.jugador1=nuevo;  
	    			  partida.players = partida.players +1;
	    			  System.out.println("Nueva partida");
	    			  ArrayList<ArrayList<Integer>> a = new ArrayList<>();
	    			  
	    			  for(int i = 0; i < 8;i++ ) {
	    				  ArrayList<Integer> ll = new ArrayList<>();
	    				  int num = (int) (Math.random()*7);
	    				  for(int b = 0; b < 8;b++ ) {
	    					  
		    				  if(b < num) {
		    					  ll.add(-1);
		    				  }else {
		    					  ll.add(-2);
		    				  }
		    				 Collections.shuffle(ll);
		    				  
		    				  
	    				  }
	    				  System.out.println(ll.toString());
	    				  a.add(ll);
	    				  }
	    			  partida.tablero = a;
	    			 
	    			  break;
	    		  }else if(partida.players<2){
	    			  partida.jugador2=nuevo;
	    			  partida.players++;
	    			  partida.players = partida.players +1;
	    			  Thread nevo = new Thread(partida);
	    			  nevo.run();
	    			  break;
	    		  }else {
	    			 
	    			  partidas.add(new Servidor());
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
        return paquete;
        
    } catch (Exception e) {
        System.out.println("Error al recibir el mensaje: " + e.getMessage());
    }
	return paquete;
}

}
