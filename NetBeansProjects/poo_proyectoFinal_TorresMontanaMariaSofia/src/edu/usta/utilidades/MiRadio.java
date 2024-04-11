package edu.usta.utilidades;

import edu.usta.configuracion.Dominio;
import java.util.Enumeration;
import java.util.Map;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class MiRadio {

    public static Boolean estaSeleccionado(ButtonGroup grupoRadioExterno) {
        Boolean seleccionado = false;

        Enumeration<AbstractButton> grupito = grupoRadioExterno.getElements();
        while (grupito.hasMoreElements()) {
            AbstractButton unRadio = grupito.nextElement();
            if (unRadio.isSelected()) {
                seleccionado = true;
            }
        }
        return seleccionado;
    }

    public static Short codigoSeleccionado(ButtonGroup grupoRadioExterno) {
        Short id = -1;

        Enumeration<AbstractButton> grupito = grupoRadioExterno.getElements();

        while (grupito.hasMoreElements()) {
            AbstractButton unRadio = grupito.nextElement();
            if (unRadio.isSelected()) {
                String nombreTipo = unRadio.getText().toLowerCase();
                for (Map.Entry<Short, String> dato : Dominio.ARREGLO_TIPOS.entrySet()) {
                    Object codigo = dato.getKey();
                    Object valor = dato.getValue().toLowerCase();

                    if (valor.equals(nombreTipo)) {
                        id = (short) codigo;
                    }
                }
            }
        }
        return id;
    }

    public static void apareceRadioSeleccionado(Short seleccionado, ButtonGroup grupoRadioExterno) {
        Enumeration<AbstractButton> grupito = grupoRadioExterno.getElements();

        for (Map.Entry<Short, String> dato : Dominio.ARREGLO_TIPOS.entrySet()) {
            AbstractButton unRadio = grupito.nextElement();
            Object codigo = dato.getKey();
            System.out.println("nom " + codigo + "-" + seleccionado);
            if (codigo.equals(seleccionado)) {
                unRadio.setSelected(true);
                break;
            }
        }
    }

    public static String obtenerTextoRadioTipo(ButtonGroup grupoRadioExterno, Short codigo) {
        String texto = "";

        Enumeration<AbstractButton> grupito = grupoRadioExterno.getElements();

        while (grupito.hasMoreElements()) {
            AbstractButton unRadio = grupito.nextElement();
            String nombreTipo = unRadio.getText().toLowerCase();

            for (Map.Entry<Short, String> dato : Dominio.ARREGLO_TIPOS.entrySet()) {
                Object codigoActual = dato.getKey();

                if (codigo.equals(codigoActual)) {
                    texto = nombreTipo;
                    break;
                }
            }

        }

        return texto;
    }

}
