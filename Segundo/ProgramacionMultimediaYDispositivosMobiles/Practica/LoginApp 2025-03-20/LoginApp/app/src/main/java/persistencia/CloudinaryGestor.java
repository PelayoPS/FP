package persistencia;

import static claves.ClavesCloudinary.API_KEY;
import static claves.ClavesCloudinary.API_SECRET;
import static claves.ClavesCloudinary.CLOUD_NAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryGestor {

	private Cloudinary cloudinary;
	// patrón singleton
	private static CloudinaryGestor cloudinaryGestor;

	public static CloudinaryGestor getInstance() {
		if (cloudinaryGestor==null) {
			cloudinaryGestor = new CloudinaryGestor();
		}
		return cloudinaryGestor;
	}

	private CloudinaryGestor() {

		Map<String, String> config = new HashMap<>();
		config.put("cloud_name", CLOUD_NAME);
		config.put("api_key", API_KEY);
		config.put("api_secret", API_SECRET);
		this.cloudinary = new Cloudinary(config);

	}

	@SuppressWarnings("unchecked")
	public List<String> getResources() {

		List<String> l = new ArrayList<>();
		Map<String, String> options = new HashMap<>();

		// llamada a la API
		try {
			Object next_cursor = "";
			do {
				ApiResponse ar =
						cloudinary.api().resources(options);
				// saco los resources
				ArrayList<Map<String, Object>> resources =
						(ArrayList<Map<String, Object>>)
								ar.get("resources");
				for(Map<String, Object> elemento: resources) {
					String public_id = elemento.get("public_id").toString();
					l.add(public_id);
				}
				// saco el next_cursor
				//System.out.println(ar);
				next_cursor = ar.get("next_cursor");
				// a opciones si no es null
				if (next_cursor!=null) {
					System.out.println("Llamando...");
					options.put("next_cursor", next_cursor.toString());
				}
			}
			while(next_cursor!=null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}

	@SuppressWarnings("rawtypes")
	/** Dado un archivo y dónde subirlo, devuelve
	 * su public_id*/
	public String upload(String file, String folder){
		String public_id = null;
		// folder va en opciones
		Map<String, Object> options = new HashMap<>();
		options.put("folder", folder);
		// llamada a la API
		try {
			Map ar = cloudinary.uploader().upload(file, options);
			public_id = ar.get("public_id").toString();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return public_id;
	}

	/**Devuelve el URL asociado a un public_id. El URL se
	 * puede usar en los ImageView de Android
	 * @param public_id el public_id del activo
	 * @return la URL pública del activo*/
	@SuppressWarnings("unchecked")
	public String get(String public_id) {
		String url = null;
		Map<String, Object> options = new HashMap<>();
		try {
			// necesita una LISTA de public_ids
			//List.of(public_id);

			ApiResponse ar = cloudinary.
					api().
					resourcesByIds(ObjectUtils.asArray(public_id),
							options);
			/* dentro de la respuesta, resources tiene una LISTA
			 * de Map<String, Object>*/
			//System.out.println(ar);
			List<Map<String, Object>> l =
					(List<Map<String, Object>>) ar.get("resources");
			// saco el primer (y único) elemento
			Map<String, Object> elemento = l.get(0);
			// este elemento tiene un secure_url como clave
			url = elemento.get("secure_url").toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/**Dado un public_id, lo borra (si puede)*/
	public boolean delete(String public_id) {
		boolean exito = false;

		List<String> lista = List.of(public_id);
		Map<String, Object> options = new HashMap<>();

		try {

			ApiResponse ar = cloudinary.
					api().
					deleteResources(lista, options);

			/* dentro de la ar hay un texto "not_found"
			 * si no lo encuentra (y por tanto no lo borra)*/
			exito = !ar.toString().contains("not_found");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	/**Dado un public_id, lo borra (si puede)*/
	@SuppressWarnings("unchecked")
	public boolean destroy(String public_id) {
		boolean exito = false;

		Map<String, Object> options = new HashMap<>();

		try {

			Map<String, Object> ar = cloudinary.
					uploader().
					destroy(public_id, options);
			/* dentro de la ar hay un texto "not_found"
			 * si no lo encuentra (y por tanto no lo borra)*/
			exito = !ar.get("result").toString().contains("not found");
			// exito = ar.get("result").compareTo("ok")==0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	/**Devuelve la lista de los public_id de los assets de un
	 * fichero concreto
	 * @param nameFolder el nombre del fichero con sus assets
	 * @return {@link List} la lista de public ids (vacía si no hay)*/
	@SuppressWarnings("unchecked")
	public List<String> listAssets(String nameFolder) {

		Map<String, String> options = new HashMap<>();
		options.put("max_results", "500");


		List<String> lista = new ArrayList<>();
		Object next_cursor = ""; // luego será null

		try {

			while(next_cursor!=null) {

				// llamada a la API
				ApiResponse ar = cloudinary.
						api().
						resourcesByAssetFolder(nameFolder, options);
				// Devuelve un ArrayList de HashMaps
				//System.out.println(ar);
				ArrayList<HashMap<String, String>> l =
						(ArrayList<HashMap<String, String>>) ar.get("resources");

				for(HashMap<String, String> e: l) {
					lista.add(e.get("public_id"));
				}

				// sacamos el next_cursor
				next_cursor = ar.get("next_cursor");
				if (next_cursor!=null) {
					//System.out.println("Sacando más...");
					options.put("next_cursor", next_cursor.toString());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

}
