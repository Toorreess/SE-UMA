import java.util.ArrayList;
import java.util.List;

public class Baterista extends Musico 
{
    private String MARCA_BATERIA;    
    private int NUM_BOMBOS;
    private int NUM_PLATOS;
    
	public static List<Musico> ListaBateristas()
	{
		// Devuelve la lista de todos los Bateristas
		// Completar		
	}
	
	public static List<Musico> ListaBateristas(Grupo g)
	{
		// Devuelve la lista de todos los Bateristas que tocan en el grupo g
		// Completar				
	}
	
	public Baterista(String nif)
	{
		// Completar
		// Carga los atributos desde la base de datos
	}
	
	public Baterista(String nif, String nombre, Grupo g, String marca, int nb, int np)
	{
		// Completar
		// Inserta los atributos en la base de datos y en memoria
	}

	public String getMARCA_BATERIA() 
	{
		return MARCA_BATERIA;
	}

	public void setMARCA_BATERIA(String m) 
	{
		// Completar
	}

	public int getNUM_BOMBOS() 
	{
		return NUM_BOMBOS;
	}

	public void setNUM_BOMBOS(int nb) 
	{
		
		// Completar
	}

	public int getNUM_PLATOS() 
	{
		return NUM_PLATOS;
	}

	public void setNUM_PLATOS(int np) 
	{
		
		// Completar
	}
	
	public void borrarBaterista()
	{
		// Completar
		// Elimina el objeto de la base de datos e invalida sus atributos en memoria
	}
	
    public String toString()
    {
    	// Devuelve el objeto serializado en CSV 
    	return super.toString() + ";" + this.MARCA_BATERIA + ";" + this.NUM_BOMBOS+ ";" + this.NUM_PLATOS ;
    }
}
