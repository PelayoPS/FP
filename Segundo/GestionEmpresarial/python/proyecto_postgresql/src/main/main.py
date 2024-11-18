import psycopg2
import src.data.database_setup as database_setup
from datetime import date
import src.log.logger as logger
import src.data.export_database as export_database
import tkinter as tk
from src.interface.gui import App

def main():
    """
    Función principal de la aplicación.

    Configura el logger, solicita las credenciales de la base de datos al usuario,
    configura la base de datos y exporta las tablas a archivos CSV.
    """
    my_logger = logger.log_setup()
    # Variables de configuración
    user1 = input("Introduce el nombre de usuario de la base de datos: ")
    password1 = input("Introduce la contraseña de la base de datos: ")

    # Configuración de la base de datos
    result, message = database_setup.setup(user1, password1)
    if not result:
        my_logger.error(message)
        return
    else:
        my_logger.info(message)
        # Cargar el módulo de la interfaz gráfica
        my_logger.info("Cargando interfaz gráfica...")
        root = tk.Tk()
        app = App(root, my_logger)
        root.mainloop()
        # Después de que la interfaz gráfica se cierre, podemos guardar los datos en la base de datos usando una lista

        # Exportar tablas a CSV
        my_logger.info("Exportando tablas a CSV...")
        result, message = export_database.export_all_tables_to_csv(user1, password1)
        if not result:
            my_logger.error(message)
            return
        else:
            my_logger.info(message)
            my_logger.info("Proceso finalizado correctamente")

if __name__ == "__main__":
    main()