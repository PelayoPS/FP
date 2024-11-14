
import psycopg2

def setup(user1, password1):
    
    # Comprueba si la base de datos ya existe
    try:
        conn = psycopg2.connect(f"dbname=productividad user={user1} password={password1}")
        return True, "Conexión a la base de datos establecida"
    except Exception:
        print("Error al conectar a la base de datos")
        # si el error es que la base de datos no existe, la crea
        try:
            return True, crear_database(user1, password1)
        except Exception:
            return False, "Error al crear la base de datos"
    
def crear_database(user1, password1):
    conn = psycopg2.connect(f"dbname=postgres user={user1} password={password1}")
    conn.autocommit = True
    cur = conn.cursor()
    cur.execute("CREATE DATABASE productividad")
    cur.close()
    conn.close()
    conn = psycopg2.connect(f"dbname=productividad user={user1} password={password1}")
    return "Base de datos creada"
    
