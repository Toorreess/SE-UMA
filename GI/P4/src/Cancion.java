import java.util.*;


public class Cancion implements Comparable<Cancion>
{
    private static String BD_SERVER = "localhost";
    private static String BD_NAME = "GI2223";
    
    private int ID_CANCION;
    private String NOMBRE;
    private int DURACION;
    private List<Grupo> TOCADA;

	public static List<Cancion> ListaCanciones()
	{
		List<Cancion> lista = new ArrayList<Cancion>(); 
		// Retorna una lista con todos los objetos de la clase almacenados en la base de datos
		BD miBD = new BD(BD_SERVER,BD_NAME);
					
		for(Object[] tupla: miBD.Select("SELECT ID_CANCION FROM Cancion;"))
		{
			lista.add(new Cancion((Integer)tupla[0]));
		}
		return lista;
	}
	
	public static List<Cancion> ListaCancionesTocadas(Grupo g)
	{
		List<Cancion> lista = new ArrayList<Cancion>(); 
		// Retorna una lista con todos los objetos de la clase almacenados en la base de datos
		BD miBD = new BD(BD_SERVER,BD_NAME);
					
		for(Object[] tupla: miBD.Select("SELECT ID_CANCION FROM Tocan WHERE ID_GRUPO = " + g.getID() + ";"))
		{
			lista.add(new Cancion((Integer)tupla[0]));
		}
		return lista;
	}
	
    public Cancion(int id)
    {
		// Crea el objeto cargando sus valores de la base de datos
      	BD miBD = new BD(BD_SERVER,BD_NAME);			
        Object[] tupla = miBD.Select("SELECT * FROM Cancion WHERE ID_CANCION = "+ id +";").get(0);

        this.ID_CANCION = (Integer)tupla[0];
        this.NOMBRE = (String)tupla[1];
        this.DURACION = (Integer)tupla[2]; 
        this.TOCADA = null;
    }
    
    public Cancion(String nombre, int duracion)
    {
		// Crea el objeto y lo inserta en la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	
        miBD.Insert("INSERT INTO Cancion(NOMBRE,DURACION) VALUES ('" + nombre + "', " + duracion + ");");
		ID_CANCION = ((Integer)miBD.SelectEscalar("SELECT MAX(ID_CANCION) FROM Cancion;"));
		this.NOMBRE = nombre;
		this.DURACION = duracion;
		this.TOCADA = null;
    }
	
    public int getID_CANCION() 
    { 
    	return ID_CANCION; 
    }
        
    public String getNombre() 
    { 
    	return NOMBRE; 
    }
    
    public int getDuracion() 
    { 
    	return DURACION; 
    }
        
    public void setNombre(String value) 
    {
		// Actualiza el atributo en memoria y en la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	miBD.Update("UPDATE Cancion SET NOMBRE = '" + value 
    			+ "' WHERE ID_CANCION ="+ this.ID_CANCION + ";");
    	
    	NOMBRE = value;    	
    }

    public void setDuracion(int value) 
    {
		// Actualiza el atributo en memoria y en la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	miBD.Update("UPDATE Cancion SET DURACION = " + value 
    			+ " WHERE ID_CANCION ="+ this.ID_CANCION + ";");
    	
    	DURACION = value;    	
    }
    
    public List<Grupo> getTocada() 
    { 
    	if (TOCADA==null) TOCADA = Grupo.ListaGruposTocan(this);
    	return TOCADA; 
    }
    
    public void addGrupo(Grupo g) 
    {
		// Actualiza el atributo en memoria y en la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	try
    	{
    		miBD.Insert("INSERT INTO Tocan VALUES("+g.getID() + "," + this.ID_CANCION + ");");
    	}
    	catch (Exception ex) 
    	{
    		// Ya estaba insertada .. lo ignoro
    	}
    	if (TOCADA==null) 	TOCADA = Grupo.ListaGruposTocan(this);
    	else 				TOCADA.add(g);
    }
    
    public void BorrarCancion()
    {
		// Borra el objeto de la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	miBD.Delete("DELETE FROM Cancion WHERE ID_CANCION ="+ this.ID_CANCION + ";");    	
		
    	ID_CANCION = -1;
		NOMBRE = null;
		DURACION = -1; 
		TOCADA = null;
    }
    
    public String toString()
    {
    	// Devuelve la canción serializado en CSV
    	return this.ID_CANCION + ";" + this.NOMBRE + ";" + this.DURACION;
    }

    public boolean equals(Object obj)
    {
        return obj instanceof Cancion 
        		&& (((Cancion)obj).getID_CANCION() == this.ID_CANCION);
    }

    public int hashCode()
    {
        return Integer.valueOf(this.ID_CANCION).hashCode();
    }

	@Override
	public int compareTo(Cancion o) 
	{
		return Integer.valueOf(this.ID_CANCION).compareTo(o.getID_CANCION());
	}
}
