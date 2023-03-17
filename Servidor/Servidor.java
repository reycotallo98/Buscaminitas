package Servidor;
import Servidor.*;

public class Servidor implements Runnable {
	// Si no quiero limitar la escucha a una interfaz
Jugador jugador1;
Jugador jugador2;
Integer[][] tablero;
int players = 0;
public Servidor( Integer[][] tablero) {
	super();
	
	this.tablero = tablero;
}
@Override
public void run() {
	// TODO Auto-generated method stub
	while(true) {
		jugador1.setTurno(true);
		jugador1.tablero = tablero;
		jugador2.tablero = tablero;

		jugador1.run();
		jugador2.run();
		while(jugador1.turno != true) {
			jugador2.turno = true;
			jugador1.tablero = jugador2.tablero;
			if(jugador1.ganador == false) {
				jugador2.ganar();
				jugador1.perder();
			}
		}
		while(jugador2.turno != true) {
			jugador1.turno = true;
			jugador2.tablero = jugador1.tablero;
			if(jugador2.ganador == false) {
				jugador2.perder();
				jugador1.ganar();
				
			}
		}
		
	}
	
}


	     }
