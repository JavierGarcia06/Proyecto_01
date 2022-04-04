
package Sub_Menus;
import Objetos.alimentacion;
import Seres_Vivos.animales;
import Seres_Vivos.mascotas;
import Sujetos.jugador;
import Objetos.comida;
import Seres_Vivos.mascotas_ia;
import Sujetos.ia;
import Sujetos.sujeto;
import java.util.Scanner;
/**
 *
 * @author Usuario
 */
public class herramientas_sb {
    private final jugador Jugador;
    
    public herramientas_sb(jugador Jugador) {
        this.Jugador=Jugador;
    }
    
    //despliega una lista de animales que esta en tienda o en nuestro poder
    //Opcion 1 u Opcion 6
    public void Mostrar_Animales(int Cantidad_Animales, animales[] Animales){
        for(int i=0;i<Cantidad_Animales;i++){
            System.out.println((i+1)+")--------------------------------------");
            Animales[i].Mostrar();
        }
    }
    //Opcion 2
    public void Mostrar_Tienda(comida[] Comida_Tienda, int Cantidad_Tienda){
        for(int i=0;i<Cantidad_Tienda;i++){
            System.out.println((i+1)+")--------------------------------------");
            Comida_Tienda[i].Mostrar_Comida();
        }
    }
    
    //Opcion 6
    public void Menu_Mascotas(sujeto Quien, int Cantidad_Mascotas, mascotas Mascotas[], alimentacion Alimentos[]){
        Scanner entrada= new Scanner(System.in);
        int Opcion=0;
        do{
            if(Cantidad_Mascotas>0){
                if(Quien instanceof jugador){
                Mostrar_Animales(Cantidad_Mascotas, Mascotas);
                System.out.println("  1) Ordenar mascotas");
                System.out.println("  2) Sumar mascotas");
                System.out.println("  3) Vender mascota");
                System.out.println("  4) Comida de la mascota");
                System.out.println("  0) Regresar");
                Opcion=entrada.nextInt();
                }else if(Quien instanceof ia){
                    int Numero= (int) (Math.random())*(25);
                    if(Numero>9&&Numero<=13){Opcion=1;}
                    else if(Numero>13&&Numero<=21){Opcion=2;}
                    else if(Numero>21&&Numero<=25){Opcion=3;}
                }
                if(Opcion==1){
                    int Primero=0, Segundo=0; 
                    if(Quien instanceof jugador){
                        System.out.println("--Que mascotas quiere cambiar de posicion?--");
                        //debera de escoger primero el animalito que quiere mover de espacio
                        Primero=entrada.nextInt();
                        System.out.println("--Segundo animalito:");
                        //luego seleccionar en que posicion quiere dejarlo
                        Segundo=entrada.nextInt();
                    }else if(Quien instanceof ia){
                        Primero=(int)(Math.random())*(Cantidad_Mascotas);
                        Segundo=(int)(Math.random())*(Cantidad_Mascotas);
                    }
                    if(Primero>=0&&Primero<=Cantidad_Mascotas&&Segundo>=0&&Segundo<=Cantidad_Mascotas){
                        Ordenar_Mascotas(Primero-1, Segundo-1, Mascotas);
                    }else{
                        if(Quien instanceof jugador){
                            System.out.println("Perdon pero no existe mascota en una de las 2 posicines");
                        }
                    }
                }else if(Opcion==2){
                    int Primero=0, Segundo=0;
                    if(Quien instanceof jugador){
                    System.out.println("--Que mascotas quiere sumar?--");
                    System.out.println("--Recuerde que debe de ser el mismo animalito para ganar experiencia--");
                    //debera de escoger el animalito que quiera sumar
                    Primero=entrada.nextInt();
                    System.out.println("--Segundo animalito:");
                    //luego seleccionar el animalito que es igual
                    Segundo=entrada.nextInt();
                    }else if(Quien instanceof ia){
                        Primero=(int)(Math.random())*(Cantidad_Mascotas);
                        Segundo=(int)(Math.random())*(Cantidad_Mascotas);
                    }
                    
                    if(Mascotas[Primero-1].getEfecto()!=5&&Mascotas[Segundo-1].getEfecto()!=5){
                        int Comidax2=0;
                        if(Quien instanceof jugador){
                        System.out.println("En las dos mascotas que selecciono tiene un alimento");
                        System.out.println("Cual de los 2 alimentos quiere mantener?");
                        System.out.println("1) " +Mascotas[Primero-1].getEfecto()+", de la posicion: "+Primero);
                        System.out.println("2) " +Mascotas[Segundo-1].getEfecto()+", de la posicion: "+Segundo);
                        Comidax2=entrada.nextInt();
                        }else if(Quien instanceof ia){
                            Comidax2=(int)(Math.random())*(1)+1;
                        }
                        if(Comidax2==2){
                            Mascotas[Primero-1].SetEfecto(Mascotas[Segundo-1].getEfecto());
                            
                            Alimentos[Segundo-1]=new alimentacion("",0,0,0,0,0);
                        }
                    }
                    Sumar_Mascotas(Primero-1, Segundo-1, Mascotas, Mascotas);
                    
                }else if(Opcion==3){
                    int M=0;
                    if(Quien instanceof jugador){
                        System.out.println("--Que mascota quiere vender?--");
                        M=entrada.nextInt()-1;
                    }else if(Quien instanceof ia){
                        M=(int)(Math.random())*(Cantidad_Mascotas);
                    }
                    if(M>=0&&M<Cantidad_Mascotas){
                        Jugador.Agregar_Oro((int)Mascotas[M].getNivel());
                        Alimentos[M]=new alimentacion("",0,0,0,0,0);
                        Mascotas[M]=new mascotas("",0,0,0,0,0,0,0,0,5);
                    }else{
                        if(Quien instanceof jugador){
                            System.out.println("------------------------------------");
                            System.out.println("--Solo tiene "+Cantidad_Mascotas+" mascotas--");
                        }
                    }
                }else if(Opcion==4){
                    System.out.println("--De que animal quiere ver la comida?--");
                    int M=entrada.nextInt()-1;
                    
                    if(M<Cantidad_Mascotas){
                        if(Mascotas[M].getNombre()==""){
                            System.out.println("--------------------------------");
                            System.out.println("No hay un animal en esa posicion");
                        }else if(Alimentos[Mascotas[M].getEfecto()].getNombre()!=""){
                            Alimentos[Mascotas[M].getEfecto()].Mostrar_Comida();
                        }else if (Alimentos[Mascotas[M].getEfecto()].getNombre()==""){
                            System.out.println("----------------------------------------");
                            System.out.println("La mascota "+Mascotas[M].getNombre()+" no tiene comida");
                        }
                    }else{
                        System.out.println("--------------------------------------");
                        System.out.println("Solo tiene "+Cantidad_Mascotas+" mascotas");
                    }
                }
            }
        }while(Opcion!=0);
    }
    
    //Opcion 6.1
    public void Ordenar_Mascotas(int Primero, int Segundo, animales Mascotas[]){
        //Espacio donde permite al jugador ordenar a sus mascotas
        animales Mascota_Guardada[]=new animales[2];
        Mascota_Guardada[0]=Mascotas[Primero];
        Mascota_Guardada[1]=Mascotas[Segundo];
        Mascotas[Segundo]=Mascota_Guardada[0];
        Mascotas[Primero]=Mascota_Guardada[1];
    }
    
    
    //A_T(AnimalesTienda)--A_D(Animales_Definidos)
    public void Actualizar_Tienda(sujeto Quien, int Ronda, int Opcion,int Cantidad_Animales, int Cantidad_TiendaA,
                                  animales A_T[], animales A_D[],int Cantidad_TiendaC, comida C[], comida C_T[]){
        
        if(Jugador.Bancarrota()){
            System.out.println("------------------------------------------------");
            System.out.println("No tiene oro suficiente para realiar esta accion");
        }else {
            int Contador=0, Tier;
            Tier=Nivel_Tier(Ronda);
            do{
                //genera un numero al azar de la cantidad de animales que hay definidos
                int Animal_Azar=(int) (Math.random()*(Cantidad_Animales));
                
                
                
                //si el animal se encuentra en el tier por ronda que se establece (si es del tier maximo o es menor que el maximo)
                //podra aparecer en la tienda
                //de lo contrario el ciclo seguira hasta completar el espacio de tienda pemitido por ronda
                if(A_D[Animal_Azar].getTier()<=Tier){
                A_T[Contador]= A_D[Animal_Azar];
                Contador++;
                }
            // si el espacio en tienda se termina ya no agregara objetos a la tienda al momento de actualizar
            }while(Contador<Cantidad_TiendaA);
            Contador=0;
            do{
                //genera un numero al azar de la cantidad de comida definida
                int Comida_Azar=(int) (Math.random()*(C.length));
                
                
                
                //si la se encuentra en el tier por ronda que se establece (si es del tier maximo o es menor que el maximo)
                //podra aparecer en la tienda
                //de lo contrario el ciclo seguira hasta completar el espacio de tienda pemitido por ronda
                if(C[Comida_Azar].getTier()<=Tier){
                C_T[Contador]= C[Comida_Azar];
                Contador++;
                }
            // si el espacio en tienda se termina ya no agregara objetos a la tienda al momento de actualizar
            }while(Contador<Cantidad_TiendaC);
            
            //cada actualizacion es menos uno de oro, si el juego esta iniciando no se
            //restara oro, solo se actualiza para tener algo en tienda
            if(Opcion!=0){
                if(Quien instanceof jugador){
                System.out.println("----------------------------------");
                System.out.println("Tienda actualizada, menos 1 de oro");
                }
                Quien.Quitar_Oro(1);
            }
        }
    }
    
    public int Nivel_Tier(int Ronda){
        int Tier=1;
        if(Ronda<2){
            return Tier;
        }else if(Ronda>=2&&Ronda<4){
            Tier=2;
            return Tier;
        }else if(Ronda>=4&&Ronda<6){
            Tier=3;
            return Tier;
        }else if(Ronda>=6&&Ronda<8){
            Tier=4;
            return Tier;
        }else if(Ronda>=8&&Ronda<10){
            Tier=5;
            return Tier;
        }else if(Ronda>=10&&Ronda<12){
            Tier=6;
            return Tier;
        }else if(Ronda>=12){
            Tier=7;
            return Tier;
        }else{
            return Tier;
        }
    }
    
    public void Sumar_Mascotas(int Primero, int Segundo, animales Animal_Uno[], animales Animal_Dos[]){
        //Espacio donde permite al jugador subir de nivel a sus mascotas
        
        if(Animal_Uno[Primero]!=Animal_Dos[Segundo]&&Animal_Uno[Primero].getNombre().equals(Animal_Dos[Segundo].getNombre())){
            
            
            //sentencia de seleccion, depende del nivel que tenga la mascota principal
            if(Animal_Uno[Primero].getNivel()>=1&&Animal_Uno[Primero].getNivel()<2){
                Cambiar_Vida_Daño(Primero, Segundo, Animal_Uno, Animal_Dos);
                //se le sumara el punteo de ex designado por nivel
                Animal_Uno[Primero].setNivel(0.5);
                
                //si al momento de ganar ex sube de nivel se hara la sumatoria de sus caracteristicas (vida, agravio, habilidad)
                if(Animal_Uno[Primero].getNivel()==2) Mascota_Nivel_Arriba(Primero, Animal_Uno);
                
                //el segundo animal sera borrado del proceso,
                //se toma en cuenta que puede quedar un espacio null si se toma otra opcion para eliminarlo
                Animal_Dos[Segundo]=new mascotas("",0,0,0,0,0,0,0,0,0);
                
            }else if(Animal_Uno[Primero].getNivel()>=2&&Animal_Uno[Primero].getNivel()<3){
                Cambiar_Vida_Daño(Primero, Segundo, Animal_Uno, Animal_Dos);
                //se le sumara el punteo de ex designado por nivel
                Animal_Uno[Primero].setNivel(0.33);
                
                //para tener un nuemro redondo para indicar el nivel exacto
                if(Animal_Uno[Primero].getNivel()==2.99) Animal_Uno[Primero].setNivel(0.01);
                
                //si al momento de ganar ex sube de nivel se hara la sumatoria de sus caracteristicas (vida, agravio, habilidad)
                if(Animal_Uno[Primero].getNivel()==3) Mascota_Nivel_Arriba(Primero, Animal_Uno);
                
                //el segundo animal sera borrado del proceso,
                //se toma en cuenta que puede quedar un espacio null si se toma otra opcion para eliminarlo
                Animal_Dos[Segundo]=new mascotas("",0,0,0,0,0,0,0,0,0);
                
            }else if(Animal_Uno[Primero].getNivel()==3){
                System.out.println("----------------------------------------");
                System.out.println("--El nivel 3 es el nivel maximo, desde--");
                System.out.println("--este nivel solo podra subir         --");
                System.out.println("--vida y agravio                      --");
            }
        }else if(Animal_Uno[Primero]==Animal_Dos[Segundo]){
            System.out.println("---------------------------");
            System.out.println("--La posicion es la misma--");
        }else {
            System.out.println("-------------------------------");
            System.out.println("--Los animales no son iguales--");
        }
    }
    
    public void Cambiar_Vida_Daño(int Primero, int Segundo, animales Animal_Uno[], animales Animal_Dos[]){
        //si el primero es el mas alto sera la base para todo el proceso
        if(Animal_Uno[Primero].getVida()>=Animal_Dos[Segundo].getVida()||Animal_Uno[Primero].getAgravio()>=Animal_Dos[Segundo].getAgravio()){
            Animal_Uno[Primero].setVida(1);
            Animal_Uno[Primero].setAgravio(1);
            //si el segunso es el mas alto se sobre escribe el primero con los datos del segundo
        }else{
            Animal_Uno[Primero].setNivel_Igualar(Animal_Dos[Segundo].getNivel());
            Animal_Uno[Primero].setVida_Igualar(Animal_Dos[Segundo].getVida()+1);
            Animal_Uno[Primero].setAgravio_Igualar(Animal_Dos[Segundo].getAgravio()+1);
        }
    }
    
    public void Mascota_Nivel_Arriba(int Primero, animales Animal_Uno[]){
        Animal_Uno[Primero].setVida(1);
        Animal_Uno[Primero].setAgravio(1);
    }
    
    public void Compra_A(int Cantidad_Mascotas, int Animal, animales Mascotas[], animales Animales_Tienda[]){
        Mascotas[Cantidad_Mascotas].setNombre(Animales_Tienda[Animal].getNombre());
        Mascotas[Cantidad_Mascotas].setTipo(Animales_Tienda[Animal].getTipo());
        Mascotas[Cantidad_Mascotas].setTipo_Dos(Animales_Tienda[Animal].getTipo_Dos());
        Mascotas[Cantidad_Mascotas].setTier(Animales_Tienda[Animal].getTier());
        Mascotas[Cantidad_Mascotas].setNivel(Animales_Tienda[Animal].getNivel());
        Mascotas[Cantidad_Mascotas].setVida(Animales_Tienda[Animal].getVida());
        Mascotas[Cantidad_Mascotas].setAgravio(Animales_Tienda[Animal].getAgravio());
    }
    
    public void Comprar_C(int Cantidad_Alimentacion, int Comida, comida C_T[], comida A[]){
        A[Cantidad_Alimentacion].setNombre(C_T[Comida].getNombre());
        A[Cantidad_Alimentacion].setTier(C_T[Comida].getTier());
        A[Cantidad_Alimentacion].setDar_Vida(C_T[Comida].getDar_Vida());
        A[Cantidad_Alimentacion].setDar_Agravio(C_T[Comida].getDar_Agravio());
        A[Cantidad_Alimentacion].setDar_Nivel(C_T[Comida].getDar_Nivel());
        A[Cantidad_Alimentacion].setTipo_Efecto(C_T[Comida].getTipo_Efecto());
    }

    public animales[] Animales_Especificados(){
        //(String Nombre, double Habilidad, int Tipo, int Tipo_Dos, int Tipo_Tres, int Tier, double Nivel, int Vida, int Agravio, int Efecto) {
        animales[] Animales;
        Animales= new mascotas[]{new mascotas("Hormiga",0.1,1,4,0,1,1,1,2,5),
            new mascotas("Pescado",0.2,3, 0, 0, 1, 1, 3, 2, 5), new mascotas("Mosquito",1.1,2, 0, 0, 1, 1, 2, 2, 5),
            new mascotas("Grillo",1.2,1, 0, 0, 1, 1, 1, 2, 5), new mascotas("Castor",0.1,4, 3, 0, 1, 1, 1, 2, 5), 
            new mascotas("Caballo",1.1,6, 7, 0, 1, 1, 1, 2, 5), new mascotas("Nutria",0.1,6, 0, 0, 1, 1, 1, 2, 5),
            new mascotas("Escarabajo",0.3,1, 0, 0, 1, 1, 2, 3, 5), new mascotas("Sapo",0.4,4, 3, 0, 2, 1, 3, 3, 5), 
            new mascotas("Dodo",1.1,2, 0, 0, 2, 1, 2, 3, 5),new mascotas("Elefante",1.3,6, 4, 0, 2, 1, 3, 5,5), 
            new mascotas("Puerco",1.2,8, 4, 0, 2, 1, 3, 2, 5)};
        return Animales;
    }
    
    public final comida[] Comida_Especificados(){
        //(String Nombre, int Tier, int Dar_Vida, int Dar_Agravio, int Dar_Nivel)
        comida[] C;
        C= new comida[]{new comida("Manzana", 1, 1, 1, 0,1.2), new comida("Naranja", 1, 0, (int) 0.1, 0,2.2),
            new comida("Miel", 1, 0, 0, 0,2.2)};
        return C;
    }
    
    public mascotas[] Llenar(){
        mascotas[] M;
        M= new mascotas[]{new mascotas("",0,0,0,0,0,0,0,0,5), new mascotas("",0,0,0,0,0,0,0,0,5),
           new mascotas("",0,0,0,0,0,0,0,0,5), new mascotas("",0,0,0,0,0,0,0,0,5),
           new mascotas("",0,0,0,0,0,0,0,0,5), new mascotas("",0,0,0,0,0,0,0,0,5)};
        
        return M;
        
    }
    
    public mascotas_ia[] Llenar_ia(){
        mascotas_ia[] M;
        M= new mascotas_ia[]{new mascotas_ia("",0,0,0,0,0,0,0,0,5), new mascotas_ia("",0,0,0,0,0,0,0,0,5),
           new mascotas_ia("",0,0,0,0,0,0,0,0,5), new mascotas_ia("",0,0,0,0,0,0,0,0,5),
           new mascotas_ia("",0,0,0,0,0,0,0,0,5), new mascotas_ia("",0,0,0,0,0,0,0,0,5)};
        
        return M;
        
    }
    
    public alimentacion[] LlenarC(){
        alimentacion[] A;
        A=new alimentacion[]{new alimentacion("",0,0,0,0,0), new alimentacion("",0,0,0,0,0), new alimentacion("",0,0,0,0,0),
        new alimentacion("",0,0,0,0,0), new alimentacion("",0,0,0,0,0), new alimentacion("",0,0,0,0,0),};
        return A;
    }
}
