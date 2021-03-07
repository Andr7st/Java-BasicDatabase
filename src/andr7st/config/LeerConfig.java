package andr7st.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LeerConfig {

    public String rutaJSON;
    public boolean baseDeDatos;

    /// credenciaales
    public String iP;
    public String puerto;
    public String nombreDB;
    public String contrasena;

    public String getIP         () { return this.iP         ; }
    public String getPuerto     () { return this.puerto     ; }
    public String getNombreDB   () { return this.nombreDB   ; }
    public String getContrasena () { return this.contrasena ; }

    public LeerConfig(String rutaJSON, boolean baseDeDatos) { // true PostgreSQL, faslse MySQL

        this.baseDeDatos = baseDeDatos;
        this.rutaJSON = rutaJSON;

        JSONParser leerJson = new JSONParser();

        File archivoConfig = new File(rutaJSON);

        if(archivoConfig.exists()) { // Ejecuta esto si el archivo existe
            
            try {
    
                Object obj = leerJson.parse(new FileReader(rutaJSON));

                JSONObject archivoLeido = (JSONObject) obj;

                JSONObject consultaSQL = null;

                if(this.baseDeDatos) {
                    /// consulta PostgreSQL
                    consultaSQL = (JSONObject) archivoLeido.get("PostgreSQL");
                }
                else {
                    /// Consulta MySQL
                    consultaSQL = (JSONObject) archivoLeido.get("MySQL");
                }   
                
                this.iP         = String.valueOf(consultaSQL.get("ip"));
                this.puerto     = String.valueOf(consultaSQL.get("puerto"));
                this.nombreDB   = String.valueOf(consultaSQL.get("nombreDB"));
                this.contrasena = String.valueOf(consultaSQL.get("contrasenaDB"));
            } 
            
            //catch (Exception e) {}
            catch(FileNotFoundException e){} // Se supone que archivo ya existe, quita esta linea.
            catch(IOException e){}
            catch(ParseException e){} 
        }
    }
}
