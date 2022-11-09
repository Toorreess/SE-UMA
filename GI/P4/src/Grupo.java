import java.util.*;


public class Grupo implements Comparable<Grupo>
{
    private static String BD_SERVER = "localhost";
    private static String BD_NAME = "GI2223";
    
    private int ID;
    private String NOMBRE;    
    private List<Cancion> TOCAN;
    private List<Musico> COMPONENTES;

	public static List<Grupo> ListaGrupos()
	{
		List<Grupo> lista = new ArrayList<Grupo>(); 
		// Retorna una lista con todos los objetos de la clase almacenados en la base de datos
		BD miBD = new BD(BD_SERVER,BD_NAME);
					
		for(Object[] tupla: miBD.Select("SELECT ID_GRUPO FROM Grupo;"))
		{
			lista.add(new Grupo((Integer)tupla[0]));
		}
		return lista;
	}
	
	public static List<Grupo> ListaGruposTocan(Cancion c)
	{
		List<Grupo> lista = new ArrayList<Grupo>(); 
		// Retorna una lista con todos los objetos de la clase almacenados en la base de datos
		BD miBD = new BD(BD_SERVER,BD_NAME);
					
		for(Object[] tupla: miBD.Select("SELECT ID_GRUPO FROM Tocan WHERE ID_CANCION = " + c.getID_CANCION() + ";"))
		{
			lista.add(new Grupo((Integer)tupla[0]));
		}
		return lista;
	}

		
    public Grupo(int id)
    {
		// Crea el objeto cargando sus valores de la base de datos
      	BD miBD = new BD(BD_SERVER,BD_NAME);			
        Object[] tupla = miBD.Select("SELECT * FROM Grupo WHERE ID_GRUPO="+id+";").get(0);
      		
        ID = (Integer)tupla[0];
        NOMBRE = (String)tupla[1];     
        TOCAN = null; // Lazzy activation
        COMPONENTES = null; // Lazzy activation 
    }
    
    public Grupo(int id, String name)
    {
		// Crea el objeto y lo inserta en la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);			
        miBD.Insert("INSERT INTO Grupo VALUES (" + id + ", '" + name +"');");
        this.ID = id;
        this.NOMBRE = name;
        this.TOCAN = null; // Lazzy activation
        this.COMPONENTES = null;
    }
	    
    public int getID() 
    { 
    	return ID; 
    }
        
    public void setID(int value) 
    { 
		// Actualiza el atributo en memoria y en la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	miBD.Update("UPDATE Grupo SET ID_GRUPO = " + value 
    			+ " WHERE ID_GRUPO ="+ this.ID + ";");
    	
    	ID = value;    	
    }

    public String getNombre() 
    { 
    	return NOMBRE; 
    }
    
    public void setNombre(String value) 
    {
		// Actualiza el atributo en memoria y en la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	miBD.Update("UPDATE Grupo SET NOMBRE = '" + value 
    			+ "' WHERE ID_GRUPO ="+ this.ID + ";");
    	
    	NOMBRE = value;    	
    }
    
    public List<Cancion> getTocan() 
    { 
    	if (TOCAN==null) TOCAN = Cancion.ListaCancionesTocadas(this);
    	return TOCAN; 
    }
    
    public List<Musico> getCOMPONENTES() 
    {     	
    	if (COMPONENTES == null) this.COMPONENTES = Musico.ListaMusicos(this);
    	return this.COMPONENTES; 
    }
    
    public void addCancion(Cancion c) 
    {
		// Actualiza el atributo en memoria y en la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	try
    	{
    		miBD.Insert("INSERT INTO Tocan VALUES("+this.ID + "," + c.getID_CANCION() + ");");
    	}
    	catch (Exception ex) 
    	{
    		// Ya estaba insertada .. lo ignoro
    	}
    	if (TOCAN==null) 	TOCAN = Cancion.ListaCancionesTocadas(this);
    	else 				TOCAN.add(c);
    }


    public void borrarGrupo() 
    {
		// Borra el objeto de la base de datos
    	BD miBD = new BD(BD_SERVER,BD_NAME);
    	miBD.Delete("DELETE FROM Grupo WHERE ID_GRUPO ="+ this.ID + ";");
    	ID = -1;
    	NOMBRE = null;
    	TOCAN = null;
    }
    
    public String toString()
    {
    	// Devuelve el grupo serializado en CSV
    	String res = this.ID + ";" + this.NOMBRE + ";";
    	for(Musico m:this.getCOMPONENTES())
    	{
    		res+=(m.getClass().getName()+ ";" + m);
    	}
    	
    	return res;
    }

    public boolean equals(Object obj)
    {
        return obj instanceof Grupo 
        		&& (((Grupo)obj).getID() == this.ID);
    }

    public int hashCode()
    {
        return Integer.valueOf(this.ID).hashCode();
    }

	@Override
	public int compareTo(Grupo o) 
	{
		return Integer.valueOf(this.ID).compareTo(o.getID());
	}
}
