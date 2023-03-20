package Servidor;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import Servidor.*;

public class Servidor implements Runnable {
	// Si no quiero limitar la escucha a una interfaz
Jugador jugador1;
Jugador jugador2;
ArrayList<ArrayList<Integer>> tablero;
ArrayList<ArrayList<Integer>> tableroOculto;
int players = 0;

public Servidor( ) {
	super();
	
	
}
@Override
public void run() {
	System.out.println(tablero);
	jugador1.tablero = tablero;
	jugador2.tablero = tablero;
	jugador1.turno = true;
	tableroOculto = new ArrayList<>();
	   
	for (int i = 0; i < tablero.size(); i++) {
		ArrayList<Integer> a = new ArrayList<>();
		for (int j = 0; j < tablero.size(); j++) {
		a.add(-2);

		}
		tableroOculto.add(a);
		System.out.println(tablero.get(i));
		}
	
	while(true) {
		
		
		
		jugador1.setTurno(true);
		vamos(jugador1);
		jugador1.setTurno(true);
		vamos(jugador2);
		if(jugador1.ganador == false) {
		ganar(jugador2);
			perder(jugador1);
		}
		if(jugador2.ganador == false) {
			ganar(jugador1);
			perder(jugador2);
		}
	}
	
}

public void vamos(Jugador jugador) {

	   
	      
	  
		
	 
	     
	    	  
	    	  
	    	
	    		  enviarMensaje("empieza",jugador);
	    		  enviarMensaje("mueve,",jugador);
	    		 
	    		  try {
	  				actualizarTablero(jugador);
	  			} catch (IOException e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
	    	 
	    	  
	    	  
		      System.out.println(" Esperando movimiento...");
		      // RECIBO DATAGRAMA
		    
		      String[] cadena = null;
			
				cadena = (new String(recibirmensaje(jugador).getData())).split(",");

			System.out.println("Recibido");
		      if(cadena[0].equals("mov")  ) {
		    	  int x = Integer.parseInt(cadena[1]);
		       int y = Integer.parseInt(cadena[2].toString());
		       
		       try {
				movimiento(x, y,jugador);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      
		       
		    
			
		    
		      }else {
		    	  System.out.println("nu c");
		      }
		      
		      enviarMensaje("espera", jugador);
		      }
		      
	    	  
	    	  
	    	  
	    	  
	    	  


private int contar(int x, int y) {
	// TODO Auto-generated method stub
	int contador = 0;
	
	if(tableroOculto.get(x).get(y) == -2){
		System.out.println("Estoy contando..");
	
	for (int i = -1; i < 2; i++) {
		for (int j = -1; j < 2; j++) {
			if (i+x<0 || i+x>1 ||j+y<0||j+y>1 ) {
				
			}		else {
		if(tablero.get(x+i).get(y+j) == -1 ) {
			contador++;
			
		}
		}}
		
	}
	tableroOculto.get(x).set(y, contador);
		if(contador==0) {
			
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (i+x<0 || i+x>1 ||j+y<0||j+y>1 ) {
					}else{revelar(x+i,y+j);
				}}
		}
			
		}

	
	
	}
return contador;
}
private void revelar(int i,int o) {
	int contado = contar(i,o);
	
	if (contado == 0) {
		tableroOculto.get(i).set(o,0) ;
	}
	
}
private void movimiento(int x, int y,Jugador a) throws IOException {
	// TODO Auto-generated method stub
	
	System.out.println("voy a contar esto, aquí hay "+ tablero.get(x).get(y));
	 if (tablero.get(x).get(y) == -1) {
		 
		 enviarMensaje("resultado,bomba,",a);
	 }else if (tablero.get(x).get(y) == -2){
		 enviarMensaje("resultado,np,",a);
		 contar(x,y);
	 }
	actualizarTablero(a);
	a.turno = false;
	       
	
	
	      }
public void actualizarTablero(Jugador a) throws IOException {
	 // Creamos el socket y establecemos la conexión con el servidor
   for (ArrayList<Integer> arrayList : tableroOculto) {
		String enviado="";
   	for (Integer integer : arrayList) {
			enviado += integer.toString();
			enviado += ",";
		}
   	
   	enviarMensaje(enviado,a);
	}
}
private static DatagramPacket recibirmensaje(Jugador a)  {
	
    byte[] buffer = new byte[1024];
    String mensaje = "";
    
    DatagramSocket socket = a.server;
    // Crea un objeto DatagramPacket que se utilizará para almacenar los datos recibidos
    DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
    
    try {
        // Crea un objeto DatagramSocket que se utilizará para recibir paquetes de datos
        
        // Utiliza el método receive del objeto DatagramSocket para recibir el paquete de datos
        socket.receive(paquete);
        
        // Convierte los datos recibidos en el tipo de dato adecuado para su procesamiento
        byte[] datosRecibidos = Arrays.copyOfRange(paquete.getData(), 0, paquete.getLength());
        mensaje = new String(datosRecibidos);
        
        // Imprime el mensaje recibido
        System.out.println("Mensaje recibido: " + mensaje);
        
        // Cierra el objeto DatagramSocket
       
        
    } catch (Exception e) {
        System.out.println("Error al recibir el mensaje: " + e.getMessage());
    }
	return paquete;
}
private static void enviarMensaje(String mensaje,Jugador a) {
	
	
    byte[] datos = mensaje.getBytes();
    
    try {
        // Crea un objeto DatagramSocket para enviar y recibir paquetes de datos
        DatagramSocket socket = Jugador.server;
        
        // Crea un objeto InetAddress que represente la dirección IP del destinatario
        InetAddress direccionDestinatario = Jugador.Ip;
        
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

public void perder(Jugador a) {
	enviarMensaje("resultado:perdido",a);
	
}
public void ganar(Jugador a) {
	enviarMensaje("ganado",a);
	a.ganador = !a.ganador;
}

	     }
