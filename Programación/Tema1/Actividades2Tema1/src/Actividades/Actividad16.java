package Actividades;

import java.util.Scanner;

public class Actividad16 {
	
	public static void main(String[] args) {
		/*
		 * Dada una hora por teclado (horas, minutos y segundos) realizar un 
		 * algoritmo que muestre la hora después de incrementar un segundo. 
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduce la hora en formato hh:mm:ss");
		try {
			//regex para asegurar formato hh:mm:ss en 24h
			String time = keyboard.next( "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]" );
			String[] timeArray = time.split(":");
			int hours = Integer.parseInt(timeArray[0]);
			int mins = Integer.parseInt(timeArray[1]);
			int secs = Integer.parseInt(timeArray[2]);
			//Controla el incremento para no salirse del rango 24h 60 min 60 sec
			secs++;
			if(secs == 60) {
				mins++;
				secs = 00;
				if(mins == 60) {
					mins = 00;
					hours++;
					if(hours == 24) {
						hours = 00;
					}
				}
			}
			//Formatea las string del tiempo para que no se vean 1:1:1 sino 01:01:01
			String h = (hours +"").length() == 1 ? ("0" + hours) : hours+"";
			String m = (mins +"").length() == 1 ? ("0" + mins) : mins+"";
			String s = (secs +"").length() == 1 ? ("0" + secs) : secs+"";
			System.out.printf("new Time: %s:%s:%s ",h,m,s);
			
		} catch (Exception e) {
			//Avisa del error
			System.err.println("The time is not on the right hh:mm:ss format");
		}
		keyboard.close();
		
		
		
	}

}
