import psycopg2
import csv
import os

def export_all_tables_to_csv(user, password):
    try:
        conn = psycopg2.connect(f"dbname=productividad user={user} password={password}")
        cur = conn.cursor()
        cur.execute("SELECT table_name FROM information_schema.tables WHERE table_schema='public'")
        tables = cur.fetchall()

        output_dir = 'src'
        os.makedirs(output_dir, exist_ok=True)
        for table in tables:
            table_name = table[0]
            cur.execute(f"SELECT * FROM {table_name}")
            rows = cur.fetchall()
            colnames = [desc[0] for desc in cur.description]

            output_file = os.path.join(output_dir, f"{table_name}.csv")
            with open(output_file, 'w', newline='', encoding='utf-8') as csvfile:
                csvwriter = csv.writer(csvfile)
                csvwriter.writerow(colnames)
                csvwriter.writerows(rows)
            return (f"Tabla {table_name} exportada a {output_file} correctamente.")

        cur.close()
        conn.close()
        return True, "Tablas exportadas correctamente"
    except Exception as e:
        return False, (f"Error al exportar las tablas a CSV: {e}")
