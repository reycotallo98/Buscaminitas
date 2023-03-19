package Servidor;
import java.util.ArrayList;

import Servidor.*;

public class Servidor implements Runnable {
	// Si no quiero limitar la escucha a una interfaz
Jugador jugador1;
Jugador jugador2;
ArrayList<ArrayList<Integer>> tablero;
int players = 0;
public Servidor( ) {
	super();
	
	
}
@Override
public void run() {
	// TODO Auto-generated method stub
	jugador1.tablero = tablero;
	jugador2.tablero = tablero;
	jugador1.run();
	jugador2.run();
	jugador1.turno = true;
	while(true) {
		jugador1.turno = true;
		jugador1.tableroOculto = jugador2.tableroOculto;
		while(jugador1.turno != true) {
			
			
			if(jugador1.ganador == false) {
				jugador2.ganar();
				jugador1.perder();
			}
		}
		jugador2.turno = true;
		jugador2.tableroOculto = jugador1.tableroOculto;
		while(jugador2.turno ) {
			
			
			if(jugador2.ganador == false) {
				jugador2.perder();
				jugador1.ganar();
				
			}
		}
		
	}
	
}


	     }
