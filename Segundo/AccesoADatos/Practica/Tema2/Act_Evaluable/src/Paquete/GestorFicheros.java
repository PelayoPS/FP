package Paquete;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorFicheros {

	/**
	 * Crea una estructura de carpetas y ficheros con la siguiente estructura Carp1
	 * Carp1/Carp2/Fich1 Carp1/Carp3/Fich2 , Carp1/Carp3/Fich3
	 * Carp1/Carp4/Carp5/Fich4 , Carp1/Carp4/Fich5
	 * 
	 * Control de errores: 
	 * - si la estructura completa ya existe se manda un mensaje "La estructura ya existe" 
	 * - si la estructura existe de forma parcial se completa la estructura
	 */
	public void crearEstructura() {
		
		boolean parcial = false;// variable de control para el mensaje final en caso de completar la estructura
		boolean archivoCreado = false;// variable de control para el mensaje final en caso de crear un archivo

		try {
			Path path = Paths.get("Carp1/Carp2/Fich1");
			// si el fichero ya existe se acctualiza la variable parcial sino se crea
			if (Files.exists(path)) {
				parcial = true;
			} else {
				Files.createDirectories(path.getParent());
				Files.createFile(path);
				archivoCreado = true;
			}
			
			path = Paths.get("Carp1/Carp3/Fich2");
			// si el fichero ya existe se acctualiza la variable parcial sino se crea
			if (Files.exists(path)) {
				parcial = true;
			} else {
				Files.createDirectories(path.getParent());
				Files.createFile(path);
				archivoCreado = true;
			}
			path = Paths.get("Carp1/Carp3/Fich3");
			// si el fichero ya existe se acctualiza la variable parcial sino se crea
			if (Files.exists(path)) {
				parcial = true;
			} else {
				Files.createDirectories(path.getParent());
				Files.createFile(path);
				archivoCreado = true;
			}
			path = Paths.get("Carp1/Carp4/Carp5/Fich4");
			// si el fichero ya existe se acctualiza la variable parcial sino se crea
			if (Files.exists(path)) {
				parcial = true;
			} else {
				Files.createDirectories(path.getParent());
				Files.createFile(path);
				archivoCreado = true;
			}
			path = Paths.get("Carp1/Carp4/Fich5");
			// si el fichero ya existe se acctualiza la variable parcial sino se crea
			if (Files.exists(path)) {
				parcial = true;
			} else {
				Files.createDirectories(path.getParent());
				Files.createFile(path);
				archivoCreado = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String mensaje = parcial && !archivoCreado ? "La estructura ya existe" : "La estructura existe parcialmente y se ha completado" ;
		System.out.println(mensaje);
	}

	/**
	 * Recibe como parámetro el nombre de la carpeta donde se crea el fichero
	 * 
	 * Control de errores:
	 * - si la carpeta no existe
	 * - si el fichero ya existe
	 * 
	 * @param carpeta carpeta donde se crea el fichero
	 * @param ficher  fichero a crear
	 */
	public void crearFichero(String carpeta, String fichero) {

		try {
			// se crea el path de la carpeta raíz
			Path root = Paths.get("Carp1");
			// itera sobre los ficheros y si son un archivo busca el fichero a crear
			
			// si la carpeta no existe se manda un mensaje de error
			Files.walk(root).filter(Files::isDirectory).forEach(f -> {
				if (f.getFileName().toString().equals(carpeta)) {
					// sabiendo la carpeta donde se encuentra
					try {
						Object[] files = Files.walk(root).filter(Files::isRegularFile).toArray();
						for (Object file : files) {
							// si el fichero ya existe se manda un mensaje de error
							if (file.toString().contains(fichero)) {
								System.out.println("El fichero ya existe");
								return;
							}
						}						
						// se crea el fichero
						Files.createFile(f.resolve(fichero));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			
					

		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	/**
	 * Recibe como parámetro el nombre de la carpeta donde se encuentra el fichero a
	 * borrar
	 * 
	 * Control de errores: 
	 * - si el fichero no existe
	 * - si la carpeta no existe
	 * 
	 * @param carpeta carpeta donde se encuentra el fichero a borrar
	 * @param fichero fichero a borrar
	 */
	public void borrarFichero(String fichero) {
		try {
			// se crea el path de la carpeta raíz
			Path root = Paths.get("Carp1");
			// itera sobre los ficheros y si son un archivo busca el fichero a borrar
			Files.walk(root).filter(Files::isRegularFile).forEach(f -> {
				// si encuentra el fichero se borra
				if (f.getFileName().toString().equals(fichero)) {
					try {
						// se borra el fichero
						Files.delete(f);
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();	
		}

	}

	/**
	 * Recibe como parámetro el nombre de la carpeta origen y destino
	 * Duplica el contenido de la carpeta origen en la carpeta destino colocándola como hermana
	 * 
	 * Control de errores:
	 * - si la carpeta origen no existe
	 * - si la carpeta destino existe
	 * 
	 * @param origen carpeta origen
	 * @param destino carpeta destino
	 */
	public void copiar(String origen, String destino) {
		try {
			// se crea el path de la carpeta raíz
			Path root = Paths.get("Carp1");
			// itera sobre las subcarpetas y si encuentra la carpeta origen la duplica en la carpeta destino
			// como hermana
			Files.walk(root).filter(Files::isDirectory).forEach(f -> {
				// si la carpeta destino ya existe se manda un mensaje de error
				if (Files.exists(f.resolveSibling(destino))) {
					System.out.println("La carpeta destino ya existe");
					return;
				}
				if (f.getFileName().toString().equals(origen)) {
					try {
						
						// se crea la carpeta destino
						Files.createDirectory(f.resolveSibling(destino));
						// irea sobre los ficheros 
						Files.walk(f).filter(Files::isRegularFile).forEach(file -> {
							try {
								// se copian los ficheros en la carpeta destino como carpetas hermanas
								Files.copy(file, Paths.get(f.resolveSibling(destino).toString(), file.getFileName().toString()));
							} catch (IOException e) {
								e.printStackTrace();
							}
						});
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		} catch(IOException e) {
			e.printStackTrace();
		}

	}


}