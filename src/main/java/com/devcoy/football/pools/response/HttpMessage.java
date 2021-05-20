package com.devcoy.football.pools.response;

public interface HttpMessage {
	
	// CRUD
		public static final String CREATED = "Se ha creado el registro correctamente.";
		public static final String READED = "Se han obtenido los registros correctamente.";
		public static final String UPDATED = "Se ha actualizado el registro correctamente.";
		public static final String DELETED = "Se ha eliminado el registro correctamente.";
		public static final String FILE_UPLOADED = "El archivo se ha subido correctamente.";

		// 2xx
		public static final String OK = "La solicitud se ha realizado correctamente.";
		public static final String ACCEPTED = "Hemos recibido la solicitud, danos unos minutos en lo que la procesamos.";
		public static final String NO_CONTENT = "Los registros que intentas obtener al parecer no existen.";

		// 4xx
		public static final String NOT_FOUND = "El contenido al que intentas acceder, al parecer no existe.";

		// 5xx
		public static final String DB_EXCEPTION = "Estamos experiementando fallas en la Base de Datos.";

}
