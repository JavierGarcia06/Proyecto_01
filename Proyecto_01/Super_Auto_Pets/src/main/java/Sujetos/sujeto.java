
package Sujetos;
/**
 *
 * @author Jose_Garcia:D
 */
public class sujeto {
    protected int Oro;
    
    public sujeto(){
        Oro=10;
    }
    
    public int getOro(){
        return Oro;
    }
    
    public void Oro_Ronda() {
        Oro=10;
    }
    
    public void Agregar_Oro(int Agregar_Oro){
        Oro=Oro+Agregar_Oro;
    }
    
    public void Quitar_Oro(int Quitar_Oro){
        int Oro_Final=Oro-Quitar_Oro;
        Oro_Final=Oro_Final >= 0 ? Oro_Final: 0;
        Oro = Oro_Final;
    }
    
    public boolean Bancarrota(){
        return Oro==0;
    }
}
