
package Sub_Menus;

import Objetos.alimentacion;
import Objetos.comida;
import Seres_Vivos.animales;
import Seres_Vivos.mascotas;
import Sujetos.jugador;
import java.util.Scanner;

/**
 *
 * @author Jose_Garcia:D
 */
public class creativo {
    private jugador Jugador;
    private herramientas_sb sb;
    
    int Cantidad_Mascotas;
    int Cantidad_Alimentacion;
    
    mascotas[] Mascotas;
    alimentacion[] Alimentos;
    
    int Cantidad_Animales=12/*55*/;
    animales[] Animales_Definidos;
    int Cantidad_Comida=3/*18*/;
    comida[] Comida;
    
    public creativo(){
        Jugador=new jugador();
        sb=new herramientas_sb(Jugador);
        
        Mascotas=new mascotas[6];
        
        Alimentos=new alimentacion[5];
        
        Animales_Definidos=new animales[Cantidad_Animales];
        Comida=new comida[Cantidad_Comida];
    }
    
    public void inicio() throws InterruptedException{
        Scanner entrada=new Scanner(System.in);
        int Opcion=0, Ronda=1;
        Cantidad_Mascotas=0;
        Cantidad_Alimentacion=0;
        
        Comida=sb.Comida_Especificados();
        Alimentos=sb.LlenarC();
        
        Mascotas=sb.Llenar();
        Animales_Definidos= sb.Animales_Especificados();
        
        Jugador.Oro_Creativo();
        System.out.println("-------MODO CREATIVO-------");
        System.out.println("--En este espacio podra crear un dream team");
        System.out.println("--Para que luche con otros jugadores--");
        System.out.println("--Se le dara una dotacion de: "+Jugador.getOro()+"--");
        while(Jugador.Muerto_Vivo()){
            Jugador.Mostrar_Datos();
            System.out.println("--Cuando este preparado puche la opcion batalla--");
            System.out.println(" 1) Clasificacion de animales");
            System.out.println(" 2) Comprar animal");
            System.out.println(" 3) Comprar comida");
            System.out.println(" 4) Mirar Mascotas");
            System.out.println(" 5) Batalla");
            Opcion=entrada.nextInt();
            if(Opcion==1){
            System.out.println("  1) Clasificacion por Tier");
            System.out.println("  2) Clasificacion por Vida");
            System.out.println("  3) Clasificacion por Agravio");
            int Opcion_Dos=entrada.nextInt();
                if(Opcion_Dos==1){
                    int Orden;
                    do{
                        Orden=Tipo_Orden();
                    }while(Orden==0);
                    Por_Tier(Orden);
                }else if(Opcion_Dos==2){
                    int Orden;
                    do{
                        Orden=Tipo_Orden();
                    }while(Orden==0);
                    Por_Vida(Orden);
                }else if(Opcion_Dos==3){
                    int Orden;
                    do{
                        Orden=Tipo_Orden();
                    }while(Orden==0);
                    Por_Agravio(Orden);
                }
            }else if(Opcion==2){
                System.out.println("---------------------------------------------------------");
                System.out.println("--Escriba el Nombre del animal con su inicial mayuscula--");
                System.out.println("--Que animal desea comprar? --");
                String Animal=entrada.next();
                // se le envia el valor de la posicion en que este el animal en tienda
                //y la cantidad de animales que hay en tienda
                Comprar_Animal(Animal, Mascotas);
            }else if(Opcion==3){
                sb.Mostrar_Tienda(Comida, Cantidad_Comida);
                System.out.println("-----------------------------");
                System.out.println("--Que comida desea comprar?--");
                int C=entrada.nextInt();
                Comprar_Comida(Mascotas, C-1, Cantidad_Comida, Comida, Alimentos);
            }else if(Opcion==4){
                sb.Menu_Mascotas(Jugador, Cantidad_Mascotas, Mascotas, Alimentos);
            }else if(Opcion==5){
                Jugador.Oro_Creativo();
                Ronda+=1;
            }else if(Opcion==0){
                break;
            }else{
                System.out.println("------------------------");
                System.out.println("--Esa no es una opcion--");
            }
        }
        
        
    }
    
    public int Tipo_Orden(){
        Scanner entrada =new Scanner(System.in);
        System.out.println("Desea ver el orden en forma...");
        System.out.println("  1) De menor a mayor");
        System.out.println("  2) De mayor a menor");
        int Orden=entrada.nextInt();
        if(Orden==1||Orden==2){
        return Orden;    
        }else{
            System.out.println("Esa no es una opcion");
            Orden=0;
            return Orden;
        }
        
    }
    
    public void Por_Tier(int Orden) throws InterruptedException{
        if(Orden==1){
            for(int i=0;i<Animales_Definidos.length;i++){
                for(int j=1;j<Animales_Definidos.length-i;j++){
                    if(Animales_Definidos[j-1].getTier()>Animales_Definidos[j].getTier()){
                        animales temp=Animales_Definidos[j];
                        Animales_Definidos[j]=Animales_Definidos[j-1];
                        Animales_Definidos[j-1]=temp;
                        
                    }
                }
            }
            for(int i=0;i<Animales_Definidos.length;i++){
                System.out.println((i+1)+")--------------------------------------");
                Animales_Definidos[i].Mostrar();
                if(i>0&&i%3==0){
                    System.out.println("Espere...");
                    Thread.sleep(2000);
                }
            }
        }else if(Orden==2){
            for(int i=0;i<Animales_Definidos.length;i++){
                for(int j=1;j<Animales_Definidos.length-i;j++){
                    if(Animales_Definidos[j-1].getTier()<Animales_Definidos[j].getTier()){
                        animales temp=Animales_Definidos[j];
                        Animales_Definidos[j]=Animales_Definidos[j-1];
                        Animales_Definidos[j-1]=temp;
                        
                    }
                }
            }
            for(int i=Animales_Definidos.length-1;i>=0;i--){
                System.out.println((12-i)+")--------------------------------------");
                Animales_Definidos[i].Mostrar();
                if(i>0&&i%3==0){
                    System.out.println("Espere...");
                    Thread.sleep(2000);
                }
            }
        }
    }
    
    public void Por_Vida(int Orden) throws InterruptedException{
        if(Orden==1){
            for(int i=0;i<Animales_Definidos.length;i++){
                for(int j=1;j<Animales_Definidos.length-i;j++){
                    if(Animales_Definidos[j-1].getVida()>Animales_Definidos[j].getVida()){
                        animales temp=Animales_Definidos[j];
                        Animales_Definidos[j]=Animales_Definidos[j-1];
                        Animales_Definidos[j-1]=temp;
                        
                    }
                }
            }
            for(int i=0;i<Animales_Definidos.length;i++){
                System.out.println((i+1)+")--------------------------------------");
                Animales_Definidos[i].Mostrar();
                if(i>0&&i%3==0){
                    System.out.println("Espere...");
                    Thread.sleep(2000);
                }
            }
            
        }else if(Orden==2){
            for(int i=0;i<Animales_Definidos.length;i++){
                for(int j=1;j<Animales_Definidos.length-i;j++){
                    if(Animales_Definidos[j-1].getVida()<Animales_Definidos[j].getVida()){
                        animales temp=Animales_Definidos[j];
                        Animales_Definidos[j]=Animales_Definidos[j-1];
                        Animales_Definidos[j-1]=temp;
                        
                    }
                }
            }
            for(int i=0;i<Animales_Definidos.length;i++){
                System.out.println((i+1)+")--------------------------------------");
                Animales_Definidos[i].Mostrar();
                if(i>0&&i%3==0){
                    System.out.println("Espere...");
                    Thread.sleep(2000);
                }
            }
        }
    }
    
    public void Por_Agravio(int Orden) throws InterruptedException{
        if(Orden==1){
            for(int i=0;i<Animales_Definidos.length;i++){
                for(int j=1;j<Animales_Definidos.length-i;j++){
                    if(Animales_Definidos[j-1].getAgravio()>Animales_Definidos[j].getAgravio()){
                        animales temp=Animales_Definidos[j];
                        Animales_Definidos[j]=Animales_Definidos[j-1];
                        Animales_Definidos[j-1]=temp;
                        
                    }
                }
            }
            for(int i=0;i<Animales_Definidos.length;i++){
                System.out.println((i+1)+")--------------------------------------");
                Animales_Definidos[i].Mostrar();
                if(i>0&&i%3==0){
                    System.out.println("Espere...");
                    Thread.sleep(2000);
                }
            }
            
        }else if(Orden==2){
            for(int i=0;i<Animales_Definidos.length;i++){
                for(int j=1;j<Animales_Definidos.length-i;j++){
                    if(Animales_Definidos[j-1].getAgravio()<Animales_Definidos[j].getAgravio()){
                        animales temp=Animales_Definidos[j];
                        Animales_Definidos[j]=Animales_Definidos[j-1];
                        Animales_Definidos[j-1]=temp;
                        
                    }
                }
            }
            for(int i=0;i<Animales_Definidos.length;i++){
                System.out.println((i+1)+")--------------------------------------");
                Animales_Definidos[i].Mostrar();
                if(i>0&&i%3==0){
                    System.out.println("Espere...");
                    Thread.sleep(2000);
                }
            }
        }
    }
    
    public void Comprar_Animal(String Animal, mascotas Mascotas[]){
        int Existencia=5, Animal_Tienda=99, Vacio=0, Nivel=0;
        // si la seleccion del animal en tienda es coherente y
        //si se tiene el oro suficiente se procede con la primera sentencia de seleccion
        for(int i=0; i<Animales_Definidos.length;i++){
            if(Animales_Definidos[i].getNombre().equals(Animal)){
                Animal_Tienda=i;
                break;
            }
        }
        
        if(Cantidad_Mascotas<5&&(Animal_Tienda>=0&&Animal_Tienda<=Animales_Definidos.length)&&(Jugador.getOro()>=3)){
            if(Cantidad_Mascotas>0){
                for(int i=0; i<Cantidad_Mascotas;i++){
                    if(Animales_Definidos[Animal_Tienda].getNombre().equals(Mascotas[i].getNombre())){
                        Existencia=i;
                        break;
                    }
                }
                for(int i=0; i<Cantidad_Mascotas;i++){
                    if(Mascotas[i].getNivel()==3){
                        Nivel=i;
                        break;
                    }
                }
            }
            for (int i=0; i<Cantidad_Mascotas;i++) {
                if ("".equals(Mascotas[i].getNombre()))Vacio=i;
            }
            if(Cantidad_Mascotas>0&&Animales_Definidos[Animal_Tienda].getNombre()==(Mascotas[Existencia].getNombre())&&Mascotas[Nivel].getNivel()!=3){
                Scanner entrada=new Scanner(System.in);
                System.out.println("--Ya tiene una mascota de esta clase--");
                System.out.println("--Desea combinarlo?--");
                System.out.println("  1)Si");
                System.out.println("  2)No");
                int Sub_Menu=entrada.nextInt();
                
                if(Sub_Menu==1){
                    sb.Sumar_Mascotas(Existencia, Animal_Tienda, Mascotas, Animales_Definidos);
                }else{
                    sb.Compra_A(Cantidad_Mascotas, Animal_Tienda, Mascotas, Animales_Definidos);
                    Cantidad_Mascotas=Cantidad_Mascotas+1;
                }
                
            }else if(Cantidad_Mascotas>0&&("".equals(Mascotas[Vacio].getNombre()))){
                sb.Compra_A(Vacio, Animal_Tienda, Mascotas, Animales_Definidos);
            
            }else{
                sb.Compra_A(Cantidad_Mascotas, Animal_Tienda, Mascotas, Animales_Definidos);
                Cantidad_Mascotas=Cantidad_Mascotas+1;
            }
            // se sobre escribe la tienda para eliminar el animal comprado omitiento 
            //la posicion 5 ya que es de tipo null, debo de cambiar eso
            
            Jugador.Quitar_Oro(3);
            
            
        }else if(Cantidad_Mascotas==5){
            for (int i=0; i<Cantidad_Mascotas;i++) {
                if ("".equals(Mascotas[i].getNombre())) {
                   sb.Compra_A(i, Animal_Tienda, Mascotas, Animales_Definidos);
                   break;
                }else if(i==4){
                    System.out.println("----------------------------");
                    System.out.println("Perdon pero no tiene espacio");
                }
            }
            
        }else if(Jugador.getOro()<3){
            System.out.println("-----------------------");
            System.out.println("No tiene suficiente oro");
            
        }else {
            System.out.println("--------------------------------------");
            System.out.println("--Escribio mal el nombre del animal,--");
            System.out.println("--O esa no es una opcion--");
            
        }
        
    }
    
    public void Comprar_Comida(mascotas M[], int Comida, int Cantidad_TiendaC, comida C_T[], alimentacion A[]){
        Scanner entrada=new Scanner(System.in);
        if(Comida>=0&&Comida<2&&Jugador.getOro()>3){ 
            if(C_T[Comida].getTipo_Efecto()==1){
                for(int i=0;i<Animales_Definidos.length;i++){
                    Animales_Definidos[i].setVida(C_T[Comida].getDar_Vida());
                    Animales_Definidos[i].setVida(C_T[Comida].getDar_Agravio());
                }
            }else if(C_T[Comida].getTipo_Efecto()==1.1){
                System.out.println("--Ahora no joven plisss--");
                Jugador.Quitar_Oro(3);
            }else if(C_T[Comida].getTipo_Efecto()>1.1){
                System.out.println("--A que mascota le quiere dar la comida?--");
                int Mascota=entrada.nextInt()-1;
                if(M[Mascota].getNombre()!=""){
                    if(C_T[Comida].getTipo_Efecto()==1.2){
                        M[Mascota].setVida(C_T[Comida].getDar_Vida());
                        M[Mascota].setAgravio(C_T[Comida].getDar_Agravio());
                        M[Mascota].setNivel(C_T[Comida].getDar_Nivel());
                        for(int i=(Comida);i<C_T.length-1;i++){
                                C_T[i]=C_T[i+1];
                        }
                        Jugador.Quitar_Oro(3);
                    }if(C_T[Comida].getTipo_Efecto()>1.2){
                        if(M[Mascota].getEfecto()!=5){
                            System.out.println("Comida almacenada: "+M[Mascota].getEfecto());
                            System.out.println("--Desea sustitur el alimento anterior?--");
                            System.out.println("   1)Si");
                            System.out.println("   2)No");
                            int Opcion=entrada.nextInt();

                            if(Opcion==1){
                                sb.Comprar_C(Cantidad_Alimentacion, Comida, C_T, A);
                                Jugador.Quitar_Oro(3);
                            }
                        }else if(M[Mascota].getEfecto()==5){
                            //tengo que mandar a sobre escribir a alimentacion
                            sb.Comprar_C(Cantidad_Alimentacion, Comida, C_T, A);
                            M[Mascota].SetEfecto(Comida);
                            Cantidad_Alimentacion=Cantidad_Alimentacion+1;
                            Jugador.Quitar_Oro(3);
                        }else{
                            System.out.println("--Perdon pero tendra de escoger --");
                            System.out.println("--otra vez la comida y el aminal--");
                        }
                    }
                }else{
                    System.out.println("--No tiene mascota en esa posicion--");
                }
            }
        }else if(Jugador.getOro()<3){
            System.out.println("---------------------------");
            System.out.println("--No tiene suficiente oro--");
        }else{
            System.out.println("------------------------");
            System.out.println("--Esa no es una opcion--");
        }
    }
    
}
