package OOP;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Main {
	
	private static Scanner teclado = new Scanner(System.in);
	
    public static void main(String[] args) throws IOException, ParseException {

    	CLI cli = new CLI();
    	
    	cli.loadUsers();
    	System.out.println("Hello");
		String estado = teclado.nextLine();
		String [] parts = estado.split(" ");
		while(!parts[0].equals("stop")) {
			
			if(parts[0].equals("createuser")) {
				/*
				 * crear nuevo usuario
				 */
				cli.createUser(parts[1],parts[2],Integer.parseInt(parts[3]));
				System.out.println("Please log in...");
			}else if(parts[0].equals("filter")) {
				/*
				 * filtar mensajes
				 */  
				System.out.println(cli.filterAll(parts[1], parts[2]));
			}else if(parts[0].equals("logas")) {
				String user = parts[1];
				if(cli.getMailsystem().getMailBox().containsKey(user)) {
					cli.logUser(parts[1]);
					//el usuario ha salido
					System.out.println("exit...");
				}else System.out.println("No existe el usuario, registrate");
				
				cli.helpMenu();
			}else cli.helpMenu();
			
			
			estado = teclado.nextLine();
			parts = estado.split(" ");
		}
    	
    }
}
