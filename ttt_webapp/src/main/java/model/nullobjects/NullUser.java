package model.nullobjects;

import model.TipoDeAtraccion;
import model.Usuario;

public class NullUser extends Usuario {
	
	public static Usuario build() {
		return new NullUser();
	}
	
	public NullUser() {
		super(0, "", 0, 0, TipoDeAtraccion.NULL, false, false);
	}
	
	public boolean isNull() { return true; }

}
