package andr7st.conectar;

/**
 * @author Andrés Segura.
 */
import java.sql.*;

import andr7st.config.LeerConfig;


public class Consultar {

    int id;
    String nombre;
    String descripcion;

    public int getID()             { return this.id;     }
    public String getNombre()      { return nombre;      }
    public String getDescripcion() { return descripcion; }

    private Connection conexionSQL = null;


    public void postgreSQL() {


        ///// Leyendo el archivo de configuración, dentro de este archivo están las credenciales.
        
        LeerConfig archivoConfig = new LeerConfig("config.dat", true); // PostgreSQL es true.

        String ip           = archivoConfig.getIP();
        String puerto       = archivoConfig.getPuerto();
        String nombreDB     = archivoConfig.getNombreDB();
        String contrasenaDB = archivoConfig.getContrasena();

        /////--------------------------------------------------------------------------------------

        String nombreUsuario = "postgres"; /// postgres

        try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            
            String dbURL = "jdbc:postgresql://".concat(ip).concat(":").concat(puerto).concat("/").concat(nombreDB);
            
            // Database connect
            // Conectamos con la base de datos
            this.conexionSQL = DriverManager.getConnection(dbURL, nombreUsuario, contrasenaDB);

            String comandoPostgreSQL = "SELECT * FROM " + "animales" + " where id = 1";
            
            PreparedStatement pstm = this.conexionSQL.prepareStatement(comandoPostgreSQL);
            ResultSet resultset = pstm.executeQuery();
            while(resultset.next()) {

                this.id           = resultset.getInt   ("id"         );
                this.nombre       = resultset.getString("nombre"     );
                this.descripcion  = resultset.getString("descripcion");
            }
            
            boolean valid = this.conexionSQL.isValid(50000);
            
            System.out.println(valid ? "PostgreSQL conected OK" : "PostgreSQL conect FAIL");
        } 
        
        catch (java.sql.SQLException sqle) {
            System.out.println("Error:\n" + sqle);
        }
        
        finally {
            /// Cerrar para evitar consumo de recursos innecesarios
            if(this.conexionSQL != null) {
                
                try { 
                    this.conexionSQL.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
    
}
