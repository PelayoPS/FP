class Usuario:
    def __init__(self, nombre, tareas=0, tareas_completadas=0):
        self._nombre = nombre
        self._tareas = tareas
        self._tareas_completadas = tareas_completadas

    # Getters
    @property
    def nombre(self):
        return self._nombre

    @property
    def tareas(self):
        return self._tareas

    @property
    def tareas_completadas(self):
        return self._tareas_completadas

    @property
    def eficiencia(self):
        return self._eficiencia

    # Setters
    @nombre.setter
    def nombre(self, nombre):
        self._nombre = nombre

    @tareas.setter
    def tareas(self, tareas):
        self._tareas = tareas

    @tareas_completadas.setter
    def tareas_completadas(self, tareas_completadas):
        self._tareas_completadas = tareas_completadas

    def calcular_eficiencia(self):
        if self._tareas == 0:
            self._eficiencia = 0
        else:
            self._eficiencia = self._tareas_completadas / self._tareas

    def __repr__(self):
        return f"Usuario(nombre='{self._nombre}', tareas={self._tareas}, tareas_completadas={self._tareas_completadas}, eficiencia={self._eficiencia})"
