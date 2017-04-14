package com.studyingapp.southfang.moralebooster;

/**
 * Created by juandavid on 13/04/17.
 */
@SuppressWarnings("SpellCheckingInspection")
public class MessageItem {

	private String fecha;
	private String contenido;
	private TipoMensaje tipoMensaje;
	private int imagen;

	public MessageItem(String fecha, String contenido, TipoMensaje tipoMensaje, int imagen) {
		this.fecha = fecha;
		this.contenido = contenido;
		this.tipoMensaje = tipoMensaje;
		this.imagen = imagen;
	}

	public String getFecha() {
		return fecha;
	}

	public String getContenido() {
		return contenido;
	}

	public TipoMensaje getTipoMensaje() {
		return tipoMensaje;
	}

	public int getImagen() {
		return imagen;
	}

	public enum TipoMensaje {
		MEDIJERON, LOPIENSO, LOHICE;

		public static TipoMensaje getTipoMensaje(String text) {
			switch (text) {
				case "Estoy orgulloso de mi":return LOHICE;
				case "LOHICE":return LOHICE;
				case "Me lo dijo alguien":return MEDIJERON;
				case "MEDIJERON":return MEDIJERON;
				case "Lo pienso de mi":return LOPIENSO;
				case "LOPIENSO":return LOPIENSO;
			}
			return null;
		}
	}
}
