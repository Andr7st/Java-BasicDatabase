package andr7st.tipografias;

/**
 * @author Andrés Segura
 * 
 * Manejar fuentes 
 */
import java.awt.Font;
import java.io.InputStream;

public class Tipografia {

    int fuenteActual = 0;

    private Font font = null;

    
    public Font  getFuente(int numFuente, int estilo, int tamano) {
        
        /// Arrego de String con las fuentes  

        String[] arregoFuente = {
            "NotoSans-Regular.ttf",
            "Stick-Regular.ttf"
        };


        try {
            //Se carga la fuente
            InputStream is =  getClass().getResourceAsStream(arregoFuente[numFuente]);
            font = Font.createFont(Font.TRUETYPE_FONT, is);


            System.out.println("Fuente OK");


        } catch (Exception ex) {


            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(arregoFuente[numFuente] + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);            
        } 


        Font fuente = font.deriveFont(estilo, tamano);


        return fuente;

    }
}
