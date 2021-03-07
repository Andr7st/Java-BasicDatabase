package andr7st;
/**
 * @author Andrés Segura.
 */
public class Main {

    public static void init() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new andr7st.interfaz.Ventana().setVisible(true);
            }
        });
    }
    public static void main(String[] args) {

       init();        
    }
}