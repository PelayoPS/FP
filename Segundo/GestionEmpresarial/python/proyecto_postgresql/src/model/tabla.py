class Tabla:
    def __init__(self, usuario, tareas, tareas_completadas, dia):
        self.usuario = usuario
        self.tareas = tareas
        self.tareas_completadas = tareas_completadas
        self.dia = dia

    def __repr__(self):
        return f"Tabla(usuario='{self.usuario}', tareas={self.tareas}, tareas_completadas={self.tareas_completadas}, dia='{self.dia}')"
    
    

