import tkinter as tk
from tkinter import ttk
from src.model.tabla import Tabla

class App:
    def __init__(self, root, logger):
        # Configuración de la ventana
        self.root = root
        self.root.title("Gestión Empresarial")

        # Campo de usuario
        tk.Label(root, text="Usuario:").grid(row=0, column=0, padx=10, pady=10)
        self.usuario_entry = tk.Entry(root)
        self.usuario_entry.grid(row=0, column=1, padx=10, pady=10)

        # Campo de tareas
        tk.Label(root, text="Tareas:").grid(row=1, column=0, padx=10, pady=10)
        self.tareas_entry = tk.Entry(root)
        self.tareas_entry.grid(row=1, column=1, padx=10, pady=10)

        # Campo de tareas completadas
        tk.Label(root, text="Tareas Completadas:").grid(row=2, column=0, padx=10, pady=10)
        self.tareas_completadas_entry = tk.Entry(root)
        self.tareas_completadas_entry.grid(row=2, column=1, padx=10, pady=10)

        # Combobox de días de la semana sin posibilidad de introducir texto
        tk.Label(root, text="Día:").grid(row=3, column=0, padx=10, pady=10)
        self.dia_combobox = ttk.Combobox(root, values=["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"], state="readonly")
        self.dia_combobox.grid(row=3, column=1, padx=10, pady=10)

        # Botón de enviar
        self.submit_button = tk.Button(root, text="Enviar", command=lambda: self.submit(logger))
        self.submit_button.grid(row=4, column=0, columnspan=2, pady=10)

        # Campo de mensaje de error
        self.error_label = tk.Label(root, text="", fg="red")
        self.error_label.grid(row=5, column=0, columnspan=2, pady=10)

    def submit(self, logger):
        # Obtener los datos de los campos
        usuario = self.usuario_entry.get()
        tareas = self.tareas_entry.get()
        tareas_completadas = self.tareas_completadas_entry.get()
        dia = self.dia_combobox.get()

        # Comprobar que los campos no estén vacíos y mandar un mensaje de error si lo están
        if not usuario or not tareas or not tareas_completadas or not dia:
            error_msg = "Todos los campos son necesarios."
            self.error_label.config(text=error_msg)
            logger.error(error_msg)
            return

        # Mensaje de éxito
        self.error_label.config(text="")
        print(f"Usuario: {usuario}, Tareas: {tareas}, Tareas Completadas: {tareas_completadas}, Día: {dia}")
        logger.info(f"Datos guardados: Usuario: {usuario}, Tareas: {tareas}, Tareas Completadas: {tareas_completadas}, Día: {dia}")
        self.guardar_datos(usuario, tareas, tareas_completadas, dia)

    def guardar_datos(self, usuario, tareas, tareas_completadas, dia):
        tabla = Tabla(usuario, tareas, tareas_completadas, dia)

