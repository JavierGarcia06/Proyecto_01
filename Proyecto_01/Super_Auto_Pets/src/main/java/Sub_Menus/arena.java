
package Sub_Menus;
import Objetos.alimentacion;
import java.util.Scanner;
import Seres_Vivos.animales;
import Seres_Vivos.mascotas;
import Seres_Vivos.mascotas_ia;
import Objetos.comida;
import Sujetos.ia;
import Sujetos.jugador;
import Sujetos.sujeto;
/**
 *
 * @author Jose_Garcia :D
 */
public class arena {
    private ia IA;
    private jugador Jugador;
    private herramientas_sb sb;
    int Ronda=1;
    int Cantidad_Mascotas=0, Cantidad_Alimentacion=0;
    int Cantidad_MascotasIA=0, Cantidad_AlimentacionIA=0;
    
    mascotas[] Mascotas;
    alimentacion[] Alimentos;
    mascotas_ia[] MascotasIA;
    alimentacion[] AlimentosIA;
    
    animales[] Animales_Tienda;
    comida[] Comida_Tienda;
    
    int Cantidad_Animales=12/*55*/;
    animales[] Animales_Definidos;
    int Cantidad_Comida=3/*18*/;
    comida[] Comida;
    
    public arena(){
        IA=new ia();
        Jugador=new jugador();
        sb=new herramientas_sb(Jugador);
        
        Mascotas=new mascotas[5];
        Alimentos=new alimentacion[5];
        MascotasIA=new mascotas_ia[5];
        AlimentosIA=new alimentacion[5];
        
        Animales_Tienda =new animales[5];
        Comida_Tienda=new comida[3];
        
        Animales_Definidos=new animales[Cantidad_Animales];
        Comida=new comida[Cantidad_Comida];
    }
    
    public void inicio(){
        Scanner entrada=new Scanner(System.in);
        int Opcion=0, Cantidad_TiendaA=3, Cantidad_TiendaC=2;
        
        Mascotas=sb.Llenar();
        Alimentos=sb.LlenarC();
        
        Animales_Definidos= sb.Animales_Especificados();
        Comida=sb.Comida_Especificados();
        
        //agreaga objtos a la tienda
        sb.Actualizar_Tienda(Jugador, Ronda, Opcion, Cantidad_Animales, Cantidad_TiendaA, Animales_Tienda,
                            Animales_Definidos,Cantidad_TiendaC, Comida, Comida_Tienda);
        //carga el oro inicial
        
        System.out.println("-------MODO AREMA-------");
        System.out.println("--Se le dara una dotacion inicial de 10 de oro para que arme su equipo--");
        while(Jugador.Muerto_Vivo()){
            Jugador.Mostrar_Datos();
            System.out.println("--Cuando este preparado puche la opcion batalla--");
            System.out.println(" 1) Animales de tienda");
            System.out.println(" 2) Comida de tienda");
            System.out.println(" 3) Actualizar tienda");
            System.out.println(" 4) Comprar animal");
            System.out.println(" 5) Comprar comida");
            System.out.println(" 6) Mirar Mascotas");
            System.out.println(" 8) Batalla");
            Opcion=entrada.nextInt();
            if(Opcion==1){
                sb.Mostrar_Animales(Cantidad_TiendaA, Animales_Tienda);
            }else if(Opcion==2){
                sb.Mostrar_Tienda(Comida_Tienda, Cantidad_TiendaC);
            }else if(Opcion==3){
                Cantidad_TiendaC=2;
                Cantidad_TiendaA=No_Ronda(Ronda);
                sb.Actualizar_Tienda(Jugador, Ronda, Opcion, Cantidad_Animales, Cantidad_TiendaA, Animales_Tienda,
                                    Animales_Definidos, Cantidad_TiendaC,Comida, Comida_Tienda);
            }else if(Opcion==4){
                System.out.println("-----------------------------");
                System.out.println("--Que animal desea comprar?--");
                int Animal=entrada.nextInt();
                // se le envia el valor de la posicion en que este el animal en tienda
                //y la cantidad de animales que hay en tienda
                Cantidad_TiendaA=Comprar_Animal(Jugador, Animal-1, Cantidad_TiendaA, Cantidad_Mascotas, Mascotas, Animales_Tienda);
            }else if(Opcion==5){
                System.out.println("-----------------------------");
                System.out.println("--Que comida desea comprar?--");
                int C=entrada.nextInt();
                Cantidad_TiendaC=Comprar_Comida(Jugador,C-1, Cantidad_TiendaC,Cantidad_Alimentacion,Mascotas, Comida_Tienda, Alimentos);
            }else if(Opcion==6){
                sb.Menu_Mascotas(Jugador, Cantidad_Mascotas, Mascotas, Alimentos);
            }else if(Opcion==8){
                Espacio_IA();
                System.out.println("Cosas de la IA");
                sb.Mostrar_Animales(Cantidad_MascotasIA, MascotasIA);
                sb.Mostrar_Tienda(AlimentosIA, Cantidad_AlimentacionIA);
                Ronda++;
                Jugador.Oro_Ronda();
                IA.Oro_Ronda();
            }else if(Opcion==0){
                break;
            }else{
                System.out.println("------------------------");
                System.out.println("--Esa no es una opcion--");
            }
        }
        System.out.println("Esta muerto");
    }
    
    //dependiendo de la ronde que este, la tiende tendra cierta cantidad de inventario
    public int No_Ronda(int Ronda){
        int Cantidad_TiendaA=0;
        if(Ronda>0&&Ronda<=3){
            Cantidad_TiendaA=3;
            return Cantidad_TiendaA;
        }else if(Ronda>3&&Ronda<=6){
            return Cantidad_TiendaA=4;
        }else if(Ronda>=7){
            return Cantidad_TiendaA=5;
        }else{
            return Cantidad_TiendaA;
        }
    }
    
    //Opcion 4
    public int Comprar_Animal(sujeto Quien, int Animal, int Cantidad_TiendaA,
        int Cantidad_M, animales[] Mascotas, animales[] Animales_Tienda){
        
        int Existencia=5, Vacio=0, Nivel=0;
        // si la seleccion del animal en tienda es coherente y
        //si se tiene el oro suficiente se procede con la primera sentencia de seleccion
        if(Cantidad_M<5&&(Animal>=0&&Animal<=Cantidad_TiendaA)&&(Quien.getOro()>=3)){
            if(Cantidad_M>0){
                for(int i=0; i<Cantidad_M;i++){
                    if(Animales_Tienda[Animal].getNombre().equals(Mascotas[i].getNombre())){
                        Existencia=i;
                        break;
                    }
                }
                for(int i=0; i<Cantidad_M;i++){
                    if(Mascotas[i].getNivel()==3){
                        Nivel=i;
                        break;
                    }
                }
            }
            for (int i=0; i<Cantidad_M;i++) {
                if ("".equals(Mascotas[i].getNombre()))Vacio=i;
            }
            if(Cantidad_M>0&&Animales_Tienda[Animal].getNombre()==(Mascotas[Existencia].getNombre())&&Mascotas[Nivel].getNivel()!=3){
                int Sub_Menu=0;
                if(Quien instanceof jugador){
                    Scanner entrada=new Scanner(System.in);
                    System.out.println("--Ya tiene una mascota de esta clase--");
                    System.out.println("--Desea combinarlo?--");
                    System.out.println("  1)Si");
                    System.out.println("  2)No");
                    Sub_Menu=entrada.nextInt();
                }else if(Quien instanceof ia){
                    int Numero=(int) (Math.random()*(10));
                    if(Numero<=8){Sub_Menu=1;
                    } else if(Numero>8&&Numero<=10){Sub_Menu=2;}
                }
                if(Sub_Menu==1){
                    sb.Sumar_Mascotas(Existencia, Animal, Mascotas, Animales_Tienda);
                }else{
                    sb.Compra_A(Cantidad_M, Animal, Mascotas, Animales_Tienda);
                    if(Quien instanceof jugador)Cantidad_Mascotas+=1;
                    if(Quien instanceof ia)Cantidad_MascotasIA+=1;
                }
                
            }else if(Cantidad_M>0&&("".equals(Mascotas[Vacio].getNombre()))){
                sb.Compra_A(Vacio, Animal, Mascotas, Animales_Tienda);
            
            }else{
                sb.Compra_A(Cantidad_M, Animal, Mascotas, Animales_Tienda);
                if(Quien instanceof jugador)Cantidad_Mascotas+=1;
                if(Quien instanceof ia)Cantidad_MascotasIA+=1;
            }
            // se sobre escribe la tienda para eliminar el animal comprado omitiento 
            //la posicion 5 ya que es de tipo null, debo de cambiar eso
            for(int i=(Animal);i<Animales_Tienda.length-1;i++){
                Animales_Tienda[i]=Animales_Tienda[i+1];
            }
            Quien.Quitar_Oro(3);
            return Cantidad_TiendaA-1;
        }else if(Cantidad_M==5){
            for (int i=0; i<Cantidad_M;i++) {
                if ("".equals(Mascotas[i].getNombre())) {
                   sb.Compra_A(i, Animal, Mascotas, Animales_Tienda);
                   Cantidad_TiendaA=Cantidad_TiendaA-1;
                   break;
                }else if(i==4&&Quien instanceof jugador){
                    System.out.println("----------------------------");
                    System.out.println("Perdon pero no tiene espacio");
                }
            }
            return Cantidad_TiendaA;
        }else if(Quien.getOro()<3){
            System.out.println("-----------------------");
            System.out.println("No tiene suficiente oro");
            return Cantidad_TiendaA;
        }else {
            System.out.println("--------------------");
            System.out.println("Esa no es una opcion");
            return Cantidad_TiendaA;
        }
        
    }
    
    //Opcion 5
    public int Comprar_Comida(sujeto Quien, int Comida, int Cantidad_TiendaC,int Cantidad_A, animales M[], comida C_T[], comida A[]){
        Scanner entrada=new Scanner(System.in);
        if(Comida>=0&&Comida<2&&Quien.getOro()>3){ 
            if(C_T[Comida].getTipo_Efecto()==1){
                // espacio donde se se le suma vida y agravio a los animales de tienda
                for(int i=0;i<Animales_Definidos.length;i++){
                    Animales_Definidos[i].setVida(C_T[Comida].getDar_Vida());
                    Animales_Definidos[i].setVida(C_T[Comida].getDar_Agravio());
                }
            }else if(C_T[Comida].getTipo_Efecto()==1.1){
                System.out.println("--Ahora no joven plisss--");
                //Espacio para darle comida a mascotas a azar
                for(int i=(Comida);i<C_T.length-1;i++){
                    C_T[i]=C_T[i+1];
                }
                Quien.Quitar_Oro(3);
                return Cantidad_TiendaC-1;
            }else if(C_T[Comida].getTipo_Efecto()>1.1){
                // si la comida es mayor a 1.1 nos indica que la comida sera solo para una mscota
                int Mascota=0;
                if(Quien instanceof jugador){
                    System.out.println("--A que mascota le quiere dar la comida?--");
                    Mascota=entrada.nextInt()-1;
                }else if(Quien instanceof ia){
                    Mascota=(int)(Math.random())*Cantidad_MascotasIA;
                }
                if(M[Mascota].getNombre()!=""){
                    if(C_T[Comida].getTipo_Efecto()==1.2){
                        //tipo 1.2 solo aumaneta algun atribito de los siguienes, si es mayor a 0
                        M[Mascota].setVida(C_T[Comida].getDar_Vida());
                        M[Mascota].setAgravio(C_T[Comida].getDar_Agravio());
                        M[Mascota].setNivel(C_T[Comida].getDar_Nivel());
                        for(int i=(Comida);i<C_T.length-1;i++){
                                C_T[i]=C_T[i+1];
                        }
                        Quien.Quitar_Oro(3);
                        return Cantidad_TiendaC-1;
                    }else if(C_T[Comida].getTipo_Efecto()>1.2){
                        //los tipo 2.0 en adelante son aliemtos que se ejecutan durante batalla
                        if(M[Mascota].getEfecto()!=5){
                            //si el espacio ya tiene una comida se debera preguntar cambio o permanecer
                            int Opcion=0;
                            if(Quien instanceof jugador){
                            System.out.println("Comida almacenada: "+A[M[Mascota].getEfecto()].getNombre());
                            System.out.println("--Desea sustitur el alimento anterior?--");
                            System.out.println("   1)Si");
                            System.out.println("   2)No");
                            Opcion=entrada.nextInt();
                            }else if(Quien instanceof jugador){
                                Opcion=(int)(Math.random())*(1)+1;
                            }
                            if(Opcion==1){
                                sb.Comprar_C(M[Mascota].getEfecto(), Comida, C_T, A);
                                M[Mascota].SetEfecto(Comida);
                                for(int i=(Comida);i<C_T.length-1;i++){
                                    C_T[i]=C_T[i+1];
                                }
                                Quien.Quitar_Oro(3);
                                return Cantidad_TiendaC-1;
                            }
                        }else if(M[Mascota].getEfecto()==5){
                            //si el espacio esta vacio, no hay problema
                            if(Cantidad_A==5){
                                for(int i=0; i<A.length;i++){
                                    if(A[i].getNombre()==""){
                                        sb.Comprar_C(i, Comida, C_T, A);
                                        M[Mascota].SetEfecto(i);
                                        break;
                                    }
                                }
                            }else{
                                sb.Comprar_C(Cantidad_A, Comida, C_T, A);
                                M[Mascota].SetEfecto(Cantidad_A);
                                if(Quien instanceof jugador)Cantidad_Alimentacion+=1;
                                if(Quien instanceof ia)Cantidad_AlimentacionIA+=1;
                            }
                            for(int i=(Comida);i<C_T.length-1;i++){
                                C_T[i]=C_T[i+1];
                            }
                            Quien.Quitar_Oro(3);
                            return Cantidad_TiendaC-1;
                        }else{
                            if(Quien instanceof jugador){
                                System.out.println("--Perdon pero tendra que escoger --");
                                System.out.println("--otra vez la comida y el aminal --");
                            }
                        }
                    }
                }else{
                    if(Quien instanceof jugador){
                        System.out.println("--No tiene mascota en esa posicion--");
                    }else if(Quien instanceof ia){}
                }
            }
            return Cantidad_TiendaC;
        }else if(Quien.getOro()<3){
            System.out.println("---------------------------");
            System.out.println("--No tiene suficiente oro--");
            return Cantidad_TiendaC;
        }else{
            System.out.println("------------------------");
            System.out.println("--Esa no es una opcion--");
            return Cantidad_TiendaC;
        }
    }
    
    public void Espacio_IA(){
        int Opcion=0, Cantidad_TiendaA=3, Cantidad_TiendaC=2;
      
        AlimentosIA=sb.LlenarC();
        MascotasIA=sb.Llenar_ia();
        
        //Animales_Definidos= sb.Animales_Especificados();
        Comida=sb.Comida_Especificados();
        //agreaga objtos a la tienda
        sb.Actualizar_Tienda(IA, Ronda, Opcion, Cantidad_Animales, Cantidad_TiendaA, Animales_Tienda,
                            Animales_Definidos,Cantidad_TiendaC, Comida, Comida_Tienda);
        do{
            int Numero=(int) (Math.random()*(80));
                if(Numero<=5){Opcion=1;
                }else if(Numero>5&&Numero<=45){Opcion=2;
                }else if(Numero>45&&Numero<=65){Opcion=3;
                }else if(Numero>65&&Numero<=80){Opcion=4;}
            if(Opcion==1&&IA.getOro()>=1){
                    Cantidad_TiendaC=2;
                    Cantidad_TiendaA=No_Ronda(Ronda);
                    sb.Actualizar_Tienda(IA, Ronda, Opcion, Cantidad_Animales, Cantidad_TiendaA, Animales_Tienda,
                                        Animales_Definidos, Cantidad_TiendaC,Comida, Comida_Tienda);
                }else if(Opcion==2&&IA.getOro()>=3){
                    //se debera de realizar una seleccion al azar de los animales de tienda
                    int Numero_Dos=(int) (Math.random()*Cantidad_TiendaA);
                    int Animal;
                     Animal=Numero_Dos;
                    Cantidad_TiendaA=Comprar_Animal(IA, Animal, Cantidad_TiendaA, Cantidad_MascotasIA, MascotasIA, Animales_Tienda);
                }else if(Opcion==3&&IA.getOro()>=3){
                    
                    int C=(int) (Math.random()*Cantidad_TiendaC);
                    //Comprar_Comida(sujeto Quien, int Comida, int Cantidad_TiendaC,int Cantidad_A, animales M[], comida C_T[], comida A[]
                    Cantidad_TiendaC=Comprar_Comida(IA, C, Cantidad_TiendaC, Cantidad_AlimentacionIA, MascotasIA,Comida_Tienda, AlimentosIA);
                }else if(Opcion==4){
                    sb.Menu_Mascotas(IA, Cantidad_Mascotas, Mascotas, Alimentos);
                }
        }while(IA.getOro()!=0);
    }
    
    
    
    
    
    public void Habilidad_AP(int M, int Cantidad_Mas){
        if("Hormiga".equals(Mascotas[M].getNombre())){
            int Ciclo_Fin=0;
            if(Cantidad_Mas!=0){
                do{
                    int Habilidad_Azar=(int) (Math.random()*(Cantidad_Mas));
                    if(Habilidad_Azar!=M){
                        Mascotas[Habilidad_Azar].setAgravio((int)Mascotas[M].getNivel()*2);
                        Mascotas[Habilidad_Azar].setVida((int)Mascotas[M].getNivel()*1);
                        Ciclo_Fin=1;
                    }
                }while(Ciclo_Fin==1);
            }
        }else if("Hormiga".equals(Mascotas[M].getNombre())){
            
        }else if("Hormiga".equals(Mascotas[M].getNombre())){
            
        }
    }
    
    
    
    
    
    
}
