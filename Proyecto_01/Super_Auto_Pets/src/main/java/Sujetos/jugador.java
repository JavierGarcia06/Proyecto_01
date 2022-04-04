
package Sujetos;
/**
 *private animales Mascotas[]=new animales[3];
 * @author Jose_Garcia :D
 */
public class jugador extends sujeto {
    private int Corazones;
    private int Copas;
    
    
    public jugador(){
        Corazones=10;
        Copas=0;
    }
    
    public void Quitar_Corazones(int Quitar_Corazones){
        int Corazones_Final=Corazones-Quitar_Corazones;
        Corazones_Final=Corazones_Final >= 0 ? Corazones_Final: 0;
        Corazones -= Corazones_Final;
    }
    
    public boolean Muerto_Vivo(){
        return Corazones>=0;
    }
    
    public void Ganar_Copas(int Mas_Copas){
        Copas=Copas+Mas_Copas;
    }
    
    public void Mostrar_Datos(){
        System.out.println("---------------------------------------");
        System.out.println("|| Oro: "+Oro+"|| Vida: "+Corazones+"||");
        System.out.println("---------------------------------------");
    }

    public void Oro_Creativo() {
        Oro=150;
    }
}
