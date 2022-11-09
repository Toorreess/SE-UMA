import java.util.*;

public abstract class Musico implements Comparable<Musico>
{
	protected static String BD_SERVER = "localhost";
	protected static String BD_NAME = "GI2223";
    
	private String NIF;
	private String NOMBRE;
	private Grupo GRUPO;
	
	public static List<Musico> ListaMusicos()
	{
		// Devuelve la lista de todos los Músicos
		List<Musico> lista = new ArrayList<>();

		return lista;
	}
	
	public static List<Musico> ListaMusicos(Grupo g)
	{		
		// Devuelve la lista de todos los Músicos que tocan en el grupo g
		BD miBD = new BD(BD_SERVER, BD_NAME);
		List<Musico> lista = new ArrayList<>();
		return lista;
	}
	
	public Musico(String nif)
	{
		// Carga los atributos desde la base de datos
		BD miBD = new BD(BD_SERVER,BD_NAME);
		Object[] tupla = miBD.Select("SELECT * FROM Musico WHERE NIF='"+nif+"';").get(0);

		NIF = (String) tupla[0];
		NOMBRE = (String) tupla[1];
		GRUPO = new Grupo((Integer) tupla[2]);
	}
	
	public Musico(String nif, String nombre, Grupo g)
	{
		// Inserta los atributos en la base de datos y en memoria
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Insert("INSERT INTO Musico VALUES ('" + nif + "', '" + nombre +"', " + g.getID() + ");");
		this.NIF = nif;
		this.NOMBRE = nombre;
		this.GRUPO = g;
	}
	
	public String getNIF() 
	{
		return NIF;
	}
	
	public void setNIF(String nif) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);			
        miBD.Update("UPDATE Musico SET NIF = '" + nif + "' WHERE NIF = '"+ this.NIF + "';");
        
		this.NIF = nif;
	}
	
	public String getNOMBRE() 
	{
		return NOMBRE;
	}
	
	public void setNOMBRE(String nombre) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);			
        miBD.Update("UPDATE Musico SET NOMBRE = '" + nombre + "' WHERE NIF = '"+ this.NIF + "';");
        
		this.NOMBRE = nombre;
	}
	
	public Grupo getGRUPO() 
	{
		return GRUPO;
	}
	
	public void setGRUPO(Grupo g) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);			
        miBD.Update("UPDATE Musico SET ID_GRUPO = " + g.getID() + " WHERE NIF = '"+ this.NIF + "';");
        
		this.GRUPO = g;
	}
	
	public void borrarMusico()
	{
		// Elimina el objeto de la base de datos e invalida sus atributos en memoria       
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Delete("DELETE FROM Musico WHERE NIF = '"+ this.NIF + "';");
		NIF = null;
		NOMBRE = null;
		GRUPO = null;
	}
	
    public String toString()
    {
    	// Devuelve el grupo serializado en CSV  	
    	return this.NIF + ";" + this.NOMBRE + ";" + this.GRUPO.getNombre() +";";
    }

    public boolean equals(Object obj)
    {
        return obj instanceof Musico 
        		&& (((Musico)obj).getNIF().equals(this.NIF));
    }

    public int hashCode()
    {
        return Integer.valueOf(this.NIF).hashCode();
    }

	@Override
	public int compareTo(Musico o) 
	{
		return this.NIF.compareTo(o.getNIF());
	}
	
}
