import java.util.*;

public class mainP4 {

	public static void main(String[] args) 
	{	
		pintaGrupo(new Grupo(19));
		pintaGrupo(new Grupo(4));
	}
	
	private static void pintaGrupo(Grupo g)
	{
		System.out.println(g);
		System.out.println("\tCANCIONES");
		System.out.println("\t======");
		for(Cancion c: g.getTocan())
			System.out.println("\t" + c);
		System.out.println();
	}
	
	
}
