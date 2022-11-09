import java.util.ArrayList;
import java.util.List;

public class Bajista extends Musico 
{
    private String MARCA_BAJO;    
    private int NUM_CUERDAS;
    
	public static List<Musico> ListaBajistas()
	{
		// Devuelve la lista de todos los Bajistas
		// Completar
	}
	
	public static List<Musico> ListaBajistas(Grupo g)
	{
		// Devuelve la lista de todos los Bajistas que tocan en el grupo g
		// Completar		
	}
	
	public Bajista(String nif)
	{
		// Completar
		// Carga los atributos desde la base de datos
	}
	
	public Bajista(String nif, String nombre, Grupo g, String marca, int nc)
	{
		// Completar
		// Inserta los atributos en la base de datos y en memoria
	}

	public String getMARCA_BAJO() 
	{
		return MARCA_BAJO;
	}

	public void setMARCA_BAJO(String m) 
	{
		// Completar
	}

	public int getNUM_CUERDAS() 
	{
		return NUM_CUERDAS;
	}

	public void setNUM_CUERDAS(int nc) 
	{
		
		// Completar
	}

	public void borrarBajista()
	{
		// Completar
		// Elimina el objeto de la base de datos e invalida sus atributos en memoria
	}
	
    public String toString()
    {
    	// Devuelve el objeto serializado en CSV 
    	return super.toString() + ";" + this.MARCA_BAJO + ";" + this.NUM_CUERDAS ;
    }
}
