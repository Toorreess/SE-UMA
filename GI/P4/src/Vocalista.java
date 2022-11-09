import java.util.*;

public class Vocalista extends Musico 
{   
    private String ESTILO;    
    private String REGISTRO;

	public static List<Musico> ListaVocalistas()
	{
		// Devuelve la lista de todos los Vocalistas
		BD miBD = new BD(BD_SERVER, BD_NAME);
		List<Musico> lista = new ArrayList<>();
		for(Object[] tupla : miBD.Select("SELECT * FROM Vocalista;")){
			lista.add(new Vocalista((String) tupla[0]));
		}
		return lista;
	}
	
	public static List<Musico> ListaVocalistas(Grupo g)
	{
		// Devuelve la lista de todos los Vocalistas que cantan en el grupo g
		BD miBD = new BD(BD_SERVER, BD_NAME);
		List<Musico> lista = new ArrayList<>();
		for(Object[] tupla : miBD.Select("SELECT * FROM Vocalista WHERE ID_GRUPO=" +g.getID() + ";")){
			lista.add(new Vocalista((String) tupla[0]));
		}
		return lista;
	}
	
	public Vocalista(String nif)
	{
		// Carga los atributos desde la base de datos
		super(nif);
		BD miBD = new BD(BD_SERVER,BD_NAME);
		Object[] tupla = miBD.Select("SELECT * FROM Vocalista WHERE NIF=" + nif + ";").get(0);

		ESTILO = (String) tupla[1];
		REGISTRO = (String) tupla[2];
	}
	
	public Vocalista(String nif, String nombre, Grupo g, String estilo, String registro)
	{
		// Inserta los atributos en la base de datos y en memoria
		super(nif, nombre, g);
		BD miBD = new BD(BD_SERVER, BD_NAME);
		miBD.Insert("INSERT INTO Vocalista VALUES ('" + nif + "', '" + estilo + "', '" + registro + "');");
	}

	public String getESTILO() 
	{
		return ESTILO;
	}

	public void setESTILO(String e) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE Vocalista SET ESTILO = '" + e + "' WHERE NIF='"+ getNIF() + "';");

		ESTILO = e;
	}

	public String getREGISTRO() 
	{
		return REGISTRO;
	}

	public void setREGISTRO(String r) 
	{
		BD miBD = new BD(BD_SERVER,BD_NAME);
		miBD.Update("UPDATE Vocalista SET REGISTRO = '" + r + "' WHERE NIF='"+ getNIF() + "';");

		REGISTRO = r;
	}

	/** borrarVocalista()
	 * He interpretado que un musico solo puede tener un rol, por lo que la solución propuesta
	 * es llamar al super.
	 */
	public void borrarVocalista()
	{
		// Elimina el objeto de la base de datos e invalida sus atributos en memoria
		super.borrarMusico();
		ESTILO = null;
		REGISTRO = null;
	}
	
    public String toString()
    {
    	// Devuelve el objeto serializado en CSV 
    	return super.toString()	+ ";" + this.ESTILO + ";" + this.REGISTRO;
    }

}
