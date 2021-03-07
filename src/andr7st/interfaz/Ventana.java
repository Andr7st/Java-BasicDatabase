package andr7st.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import andr7st.conectar.Consultar;

public class Ventana extends JFrame {

    final int VENTANA_X_ANCHO = 720;
    final int VENTANA_Y_ALTO  = 480;

    /// Fuente perteneciente al paquete "tipografias"
    public Font fuente1 = new andr7st.tipografias.Tipografia().getFuente(1, 1, 28);

    public Ventana() {
        
        this.setTitle("Andr7st");
        this.setSize(VENTANA_X_ANCHO, VENTANA_Y_ALTO);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

  /*       LayoutManager layout = new BorderLayout();
        setLayout(layout);
 */
        add(new PanelPrincipal());
    }

    public class PanelPrincipal extends JPanel {
        public PanelPrincipal() {

            this.setBackground(Color.GRAY);
            this.setLayout(null);
            this.add(new PanelControl());
            this.setLayout(null);

            txtID     = new JTextField("ID"); // Id en la base de datos
            txtNombre = new JTextField("Nombre");

            txtID        .setBounds(220, 70, 120, 26);
            txtNombre    .setBounds( 10, 70, 200, 26);

            txtID.setEditable(false);

            areaTexto = new JTextArea("Descripcion");
            areaTexto.setLineWrap(true);
            areaTexto.setWrapStyleWord(true);
            areaTexto.setEditable(true); 
            areaTexto.setFont(fuente1);
            JScrollPane sp = new JScrollPane(areaTexto);

            sp.setBounds( 10, 110,(VENTANA_X_ANCHO -20 ) - (VENTANA_X_ANCHO / 4) ,320);

            this.add(txtID);
            this.add(txtNombre);
            this.add(sp);

        }
    }

    public class PanelControl extends JPanel {

        public PanelControl() {

            //// Ajustando tamaño y posicion del panel
            int  anchoPanelControl = VENTANA_X_ANCHO / 4;
            this.setBounds((VENTANA_X_ANCHO - anchoPanelControl),0,  anchoPanelControl, VENTANA_Y_ALTO );
            this.setBackground(Color.GRAY.brighter());
            this.setLayout(null);

            /// 
            pSQL = new JRadioButton("PostgreSQL");
            mSQL = new JRadioButton("MySQL");
            pSQL.setSelected(true);
            
            //// Poniendo los radioButtons en un grupo
            ButtonGroup grupoRadio = new ButtonGroup();
            grupoRadio.add(pSQL);
            grupoRadio.add(mSQL);
            
            //// Botones
            JButton btn1 = new JButton("Agregar nuevo");
            JButton btn2 = new JButton("Consultar");
            
            /// Accion al dar click al boton.
            btn2.addActionListener(new ActionListener(){
                @Override
				public void actionPerformed(ActionEvent e) {
                    
                    if(mSQL.isSelected()) {
                        System.out.println("MySQL consulta.");
                    }

                    if(pSQL.isSelected()) { 
                        ///// Consulta la BD postgreSQl
                
                        Consultar consulta = new Consultar();
                
                        consulta.postgreSQL();

                        txtID.setText    (String.valueOf(consulta.getID()));
                        txtNombre.setText(consulta.getNombre());
                        areaTexto.setText(consulta.getDescripcion());
                    }
                
                }
                    
            });
            
            //
            txtConsulta = new JTextField("Consulta");
            
            
            /// Ajustando tamaño y posicion de los objetos graficos.
            pSQL.setBounds(22, (VENTANA_Y_ALTO - 440), 120, 24);
            mSQL.setBounds(22, (VENTANA_Y_ALTO - 380), 120, 24);
            btn1.setBounds(22, (VENTANA_Y_ALTO - 240), 120, 24);
            btn2.setBounds(22, (VENTANA_Y_ALTO - 100), 120, 24);
            txtConsulta.setBounds(22, (VENTANA_Y_ALTO - 150), 120, 24);
            
            //// Agregando los objetos al panel.
            this.add(pSQL);
            this.add(mSQL);
            this.add(btn1);
            this.add(btn2);
            this.add(txtConsulta);

        }
    }

    public JTextArea  areaTexto ;
    public JTextField txtConsulta;
    public JTextField txtID;
    public JTextField txtNombre;

    public JRadioButton pSQL;
    public JRadioButton mSQL;
}
