
package Seres_Vivos;

/**
 *
 * @author Jose_Garcia :D
 */
public class animales implements Cloneable{
    protected String Nombre;
    protected double Habilidad;
    protected int Tipo;
    protected int Tipo_Dos;
    protected int Tipo_Tres;
    protected int Tier;
    protected double Nivel;
    protected int Vida;
    protected int Agravio;
    protected int Efecto;
   
    public animales(String Nombre, double Habilidad, int Tipo, int Tipo_Dos, int Tipo_Tres, int Tier,
                    double Nivel, int Vida, int Agravio, int Efecto) {
        this.Nombre = Nombre;
        this.Habilidad=Habilidad;
        this.Tipo = Tipo;
        this.Tipo_Dos = Tipo_Dos;
        this.Tipo_Tres = Tipo_Tres;
        this.Tier = Tier;
        this.Nivel = Nivel;
        this.Vida = Vida;
        this.Agravio = Agravio;
        this.Efecto=Efecto;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public double getHabilidad(){
        return Habilidad;
    }
    
    public int getTipo(){
        return Tipo;
    }
    
    public int getTipo_Dos(){
        return Tipo_Dos;
    }
    
    public int getTipo_Tres(){
        return Tipo_Tres;
    }
    
    public int getTier(){
        return Tier;
    }
    
    public double getNivel(){
        return Nivel;
    }
    
    public int getVida(){
        return Vida;
    }
    
    public int getAgravio(){
        return Agravio;
    }
    
    public void setNombre(String N){
        Nombre=N;
    }
    
    public void setTipo(int T){
        Tipo=T;
    }
    
    public void setTipo_Dos(int T_Dos){
        Tipo_Dos=T_Dos;
    }
    
    public void setTipo_Tres(int T_Tres){
        Tipo_Tres=T_Tres;
    }
    
    public void setTier(int Ti){
        Tier=Ti;
    }
    
    public int getEfecto(){
        return Efecto;
    }
    
    public void SetEfecto(int Efecto){
        this.Efecto=Efecto;
    }
    
    public void setNivel(double ex){
        Nivel=Nivel+ex;
    }
    
    public void setVida(int Agregar){
        Vida=Vida+Agregar;
    }
    
    public void setAgravio(int Agregar){
        Agravio=Agravio+Agregar;
    }
    
    public void setNivel_Igualar(double ex){
        Nivel=ex;
    }
    
    public void setVida_Igualar(int Agregar){
        Vida=Agregar;
    }
    
    public void setAgravio_Igualar(int Agregar){
        Agravio=Agregar;
    }
    
    
    public void Mostrar(){
        System.out.println("Tier: "+this.Tier+"--Nombre: " + this.Nombre+"--Nivle: "+this.Nivel+"--Agravio: "+this.Agravio+"--Vida: "+this.Vida);
        Habilidad_Texto(Nombre);
        System.out.print("Tipo: ");
        Nombre_Tipo(this.Tipo);
        if(this.Tipo_Dos!=0){
            System.out.print("--");
            Nombre_Tipo(this.Tipo_Dos);
        }
        if(this.Tipo_Tres!=0){
            System.out.print("--");
            Nombre_Tipo(this.Tipo_Tres);
        }
        System.out.println("");
        System.out.println("----------------------------------------");
    }
    
    public void Habilidad_Texto(String Nombre){
        if("Hormiga".equals(Nombre)){
            System.out.println("Compañerismo: Da a un aliado al azar: (+2/+1)(+4/+2)(+6/+3)");
        }else if("Pescado".equals(Nombre)){
            System.out.println("Level-up: Da a todos los aliados: (+1/+1)(+2/+2)");
        }else if("Mosquito".equals(Nombre)){
            System.out.println("Piquete inicial: Al inicio de la batalla realiza 1 de daño a: 1/2/3 enemigos");
        }else if("Grillo".equals(Nombre)){
            System.out.println("Desmayo: Convoca a un grillo zombie con estadisticas: (1/1)(2/2)(3/3)");
        }else if("Castor".equals(Nombre)){
            System.out.println("Represa: Da a 2 aliados al azar: +1/+2/+3 de HP");
        }else if("Caballo".equals(Nombre)){
            System.out.println("Rugido alidao: Da: +1/+2/+3 de ATK hasta ek final de la batalla");
        }else if("Nutria".equals(Nombre)){
            System.out.println("Compra: Da a un aleado aleatorio: (+1/+1)(+2/+2)(+3/+3)");
        }else if("Escarabajo".equals(Nombre)){
            System.out.println("Come comida de la tienda: Otorga a las mascotas de la tienda: +1/+2/+3 de HP");
        }else if("Sapo".equals(Nombre)){
            System.out.println("Metamorfosis: Copia la salud del aliado mas saludable");
        }
    }
    
    public void Nombre_Tipo(int modelo){
        switch (modelo) {
            case 1:
                System.out.print("Insecto");
                break;
            case 2:
                System.out.print("Volador");
                break;
            case 3:
                System.out.print("Acuatico");
                break;
            case 4:
                System.out.print("Terrestre");
                break;
            case 5:
                System.out.print("Reptil");
                break;
            case 6:
                System.out.print("Mamifero");
                break;
            case 7:
                System.out.print("Domestico");
                break;
            case 8:
                System.out.print("Solitario");
                break;
            case 9:
                System.out.print("Desertico");
                break;
            default:
                break;
        }
    }
    
    
    
    
}
