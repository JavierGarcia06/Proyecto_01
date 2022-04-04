
package Objetos;

/**
 *
 * @author Jose:D
 */
public class comida {
    protected String Nombre;
    protected int Tier;
    protected int Dar_Vida;
    protected int Dar_Agravio;
    protected int Dar_Nivel;
    protected double Tipo_Efecto;
    
    public comida(String Nombre, int Tier, int Dar_Vida, int Dar_Agravio, int Dar_Nivel, double Tipo_Efecto){
        this.Nombre=Nombre;
        this.Tier=Tier;
        this.Dar_Vida=Dar_Vida;
        this.Dar_Agravio=Dar_Agravio;
        this.Dar_Nivel=Dar_Nivel;
        this.Tipo_Efecto=Tipo_Efecto;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getTier() {
        return Tier;
    }

    public int getDar_Vida() {
        return Dar_Vida;
    }

    public int getDar_Agravio() {
        return Dar_Agravio;
    }

    public int getDar_Nivel() {
        return Dar_Nivel;
    }

    public double getTipo_Efecto() {
        return Tipo_Efecto;
    }
    
    public void setNombre(String Nombre){
        this.Nombre=Nombre;
    }
    
    public void setTier(int Tier){
        this.Tier=Tier;
    }

    public void setDar_Vida(int Dar_Vida) {
        this.Dar_Vida = Dar_Vida;
    }

    public void setDar_Agravio(int Dar_Agravio) {
        this.Dar_Agravio = Dar_Agravio;
    }

    public void setDar_Nivel(int Dar_Nivel) {
        this.Dar_Nivel = Dar_Nivel;
    }

    public void setTipo_Efecto(double Tipo_Efecto) {
        this.Tipo_Efecto = Tipo_Efecto;
    }
    
    public void Mostrar_Comida(){
        System.out.println("Nombre: " +Nombre);
        if(Dar_Vida!=0){
            System.out.print("Vida: +"+Dar_Vida);
            System.out.print("--");
        }
        if(Dar_Agravio!=0){
            System.out.print("Agravio: +"+Dar_Agravio);
            System.out.print("--");
        }
        System.out.println("");
        if(Dar_Nivel!=0){
            System.out.print("Nivel: +"+Dar_Nivel);
            System.out.print("--");
        }
        if(Dar_Nivel!=0){
            System.out.print("Efecto: "/*Debo de dar una pequeña descripcion*/);
            System.out.print("--");
        }
        System.out.println("");
        System.out.println("----------------------------------------");
    }
    
    
}
