import logging

def log_setup():
    # Configuración del logger
    logger = logging.getLogger('mi_logger')
    logger.setLevel(logging.DEBUG)

    # Formato del logger
    formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')

    # Handler para guardar en archivo
    file_handler = logging.FileHandler('src/log/logfile.log', encoding='utf-8')
    file_handler.setFormatter(formatter)
    logger.addHandler(file_handler)

    # Handler para mostrar en consola con colores
    class ColorHandler(logging.StreamHandler):
        COLORS = {
            'DEBUG': '\033[94m',  # Azul
            'INFO': '\033[92m',   # Verde
            'WARNING': '\033[93m', # Amarillo
            'ERROR': '\033[91m',  # Rojo
            'CRITICAL': '\033[95m' # Magenta
        }

        def emit(self, record):
            try:
                message = self.format(record)
                color = self.COLORS.get(record.levelname, '\033[0m')
                self.stream.write(color + message + '\033[0m' + self.terminator)
                self.flush()
            except Exception:
                self.handleError(record)

    console_handler = ColorHandler()
    console_handler.setFormatter(formatter)
    logger.addHandler(console_handler)

    return logger

# Ejemplo de uso
if __name__ == "__main__":
    logger = log_setup()
    logger.debug("Este es un mensaje de depuración")
    logger.info("Este es un mensaje informativo")
    logger.warning("Este es un mensaje de advertencia")
    logger.error("Este es un mensaje de error")
    logger.critical("Este es un mensaje crítico")