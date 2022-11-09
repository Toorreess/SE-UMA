import java.util.ArrayList;
import java.util.List;

public class Guitarrista extends Musico 
{
    private String MARCA_GUITARRA;    
    
	public static List<Musico> ListaGuitarristas()
	{
		// Devuelve la lista de todos los Guitarristas
		// Completar
	}
	
	public static List<Musico> ListaGuitarristas(Grupo g)
	{
		// Devuelve la lista de todos los Guitarristas que togan en el grupo g
		// Completar
	}
	
	public Guitarrista(String nif)
	{
		// Completar
		// Carga los atributos desde la base de datos
	}
	
	public Guitarrista(String nif, String nombre, Grupo g, String marca, int nc)
	{
		// Completar
		// Inserta los atributos en la base de datos y en memoria
	}

	public String getMARCA_GUITARRA() 
	{
		return MARCA_GUITARRA;
	}

	public void setMARCA_GUITARRA(String m) 
	{
		// Completar
	}

	public void borrarGuitarrista()
	{
		// Completar
		// Elimina el objeto de la base de datos e invalida sus atributos en memoria
	}
	
    public String toString()
    {
    	// Devuelve el objeto serializado en CSV 
    	return super.toString() + ";" + this.MARCA_GUITARRA ;
    }
}
