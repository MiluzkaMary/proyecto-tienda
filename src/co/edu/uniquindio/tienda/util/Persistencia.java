package co.edu.uniquindio.tienda.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import co.edu.uniquindio.tienda.model.Tienda;

public class Persistencia {
	
	/**
	 * Metodo que guarda los datos en un archivo
	 * @param nombreArchivo Nombre del archivo donde se guarda los datos
	 * @param data Datos que se van a guardar
	 */
	public static void serialize(String nombreArchivo, Object data) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(nombreArchivo);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(data);
			oos.close();
			fos.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que obtiene los datos guardados en un archivo
	 * @param nombreArchivo Nombre del archivo de donde se consiguen los datos 
	 * @return Datos obtenidos
	 */
	public static Object deserialize(String nombreArchivo) {
		Object data = null;
		try {
			FileInputStream fis = new FileInputStream(nombreArchivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			data =  ois.readObject();
			ois.close();
			fis.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Metodo que obtiene los datos guardados
	 * @param ruta Archivo donde se guardan los datos
	 * @return Datos obtenidos
	 */
	public static Tienda realizarCarga(String ruta) 
	{  
		Tienda  p=null;
		Tienda datosObtenidos=null;
		File fOrig;
		fOrig = new File(ruta);
		if(fOrig.exists ( ))
		{
			p= (Tienda) Persistencia.deserialize(ruta) ;
			datosObtenidos=p;
		}

		return datosObtenidos;
	}

}
