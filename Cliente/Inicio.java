package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Inicio {
  public static void main(String[] args) throws IOException {
	  
	  Integer[][] array = new Integer[8][8];
	  byte[] recibidos=new byte[1024];
	    byte[] enviados=new byte[1024];
	    String cadena;
	    boolean turno = false;
		try (DatagramSocket serverSocket = new DatagramSocket(3000)) {
			InetSocketAddress ip = new InetSocketAddress("nube5.anti-palilleros.com", 3000);
String ipclean = ip.toString().substring(ip.toString().indexOf('/')+1,ip.toString().indexOf(':'));
			System.out.println("Conectado");
			for (int i = 0; i < array.length; i++) {
for (int j = 0; j < array.length; j++) {
array[i][j] = -3;

}
}
Tablero tablero;
byte[] mensaje = "quierojugar".getBytes();
DatagramPacket paqEnviado=new DatagramPacket(mensaje,mensaje.length,ip);
try {
	
serverSocket.send(paqEnviado);
System.out.println("Conectado");
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}




DatagramPacket paqRecibido=new DatagramPacket(recibidos,recibidos.length);  
serverSocket.receive(paqRecibido);
cadena=new String(paqRecibido.getData()); 
  
if (cadena.contains("empieza")){
	

}





while(true) {
//	try {
//		//array = (Integer[][]) objeto.readObject();
//	} catch (ClassNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}	
	tablero = new Tablero(array, turno);

paqRecibido=new DatagramPacket(recibidos,recibidos.length);  
serverSocket.receive(paqRecibido);
cadena=new String(paqRecibido.getData()); 
if(cadena.contains("mueve")) {
  turno = true;
  
  
  
}else if(cadena.contains("movimiento")){
	int x = Integer.parseInt(cadena.substring(cadena.indexOf(":	"), cadena.indexOf(",")));
	int y = Integer.parseInt(cadena.substring(cadena.indexOf(","), cadena.indexOf(",", cadena.indexOf(","))));
	String cod = cadena.substring(cadena.indexOf("-"));
	if(cod.contains("numeroBombas")) {
		cod = cod.substring(0, 11);
	}

	switch (cod) {
	case "bomba": {
		
		tablero.setTitle("Has Perdido");
	}
	case "numeroBombas": {
		
		array[x][y] =Integer.parseInt(cadena.substring(cadena.indexOf(".")));
		System.out.println(array[x][y]);
		turno = !turno;
		tablero = new Tablero(array,turno);
	}	
	default:
		throw new IllegalArgumentException("Unexpected value: " + cod);
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
	}else {
//		try {
//			//array = (Integer[][]) objeto.readObject();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}
}
		
		
		}
  
  
  
  
  
  }
  














private static void perdido() {
	// TODO Auto-generated method stub
	
}}
