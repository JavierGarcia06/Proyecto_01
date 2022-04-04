
package Inicio;
import java.util.Scanner;

import Sub_Menus.arena;
import Sub_Menus.creativo;
import Sub_Menus.vs;

/**
 *
 * @author Jose_Garcia :D
 */
public class Menu {
    
    public Menu(){
        
    }
    
    public void Iniciar() throws InterruptedException{
        Scanner entrada=new Scanner(System.in);
        int opcion;
        System.out.println("Bienvenido a SUPER_AUTO_PETS, la copia barata... no espere mucho jeje");
        System.out.println("Escoja un modo de juego:");
        do{
        System.out.println("1) Modo Arena -Gana 10 partidas-");
        System.out.println("2) Modo Versus -Pelea con otros jugadores-");
        System.out.println("3) Modo Creativo -Arma tu dream team-");
        System.out.println("0) Salida");
        opcion=entrada.nextInt();
        if(opcion==1){
            arena partida=new arena();
            partida.inicio();
        }else if(opcion==2){
            vs partida= new vs();
            partida.inicio();
        }else if(opcion==3){
            creativo partida=new creativo();
            partida.inicio();
        }else if(opcion==0){
            System.out.println("Te perdiste del mejor juego ;c");
        }
        
        }while(opcion!=0);
    }
    
    
    
}
