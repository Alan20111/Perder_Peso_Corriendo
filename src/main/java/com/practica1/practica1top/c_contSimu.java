package com.practica1.practica1top;

import com.practica1.practica1top.models.c_modSimu;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class c_contSimu {

    @FXML private TextField v_tfPeso;
    @FXML private TextField v_tfPerd;
    @FXML private TextField v_tfMinutos;
    @FXML private Slider v_slVelo;
    @FXML private Label v_lbVeloTitl;

    @FXML private Label v_lbKcalTota;
    @FXML private Label v_lbKcalMin;
    @FXML private Label v_lbKcalHora;
    @FXML private Label v_lbHoraTota;

    @FXML private Label v_lbSess30;
    @FXML private Label v_lbSess45;
    @FXML private Label v_lbSess60;
    @FXML private Label v_lbSess75;
    @FXML private Label v_lbSess90;
    @FXML private Label v_lbSess120;

    @FXML private Label v_lbSessUser;

    @FXML private ImageView v_imgFoto;

    private c_modSimu a_modCalc = new c_modSimu();

    @FXML
    public void initialize() {
        try {
            Image v_fotoPerfil = new Image(getClass().getResourceAsStream("perfil.jpeg"));
            v_imgFoto.setImage(v_fotoPerfil);

            Circle v_clip = new Circle(50, 50, 50);
            v_imgFoto.setClip(v_clip);

        } catch (Exception e) {
            System.out.println("No se encontro el archivo 'perfil.jpeg'. Revisa la extension pa.");
        }

        v_slVelo.valueProperty().addListener((obs, oldVal, newVal) -> {
            double v_val = newVal.doubleValue();
            double v_minPorKm = 60 / v_val;
            int v_minutos = (int) v_minPorKm;
            int v_segundos = (int) ((v_minPorKm - v_minutos) * 60);

            v_lbVeloTitl.setText(String.format("Velocidad de carrera: %.1f km/h (%d'%02d\"/km)", v_val, v_minutos, v_segundos));
            m_ejecCalc();
        });

        v_tfPeso.textProperty().addListener((obs, old, nue) -> m_ejecCalc());
        v_tfPerd.textProperty().addListener((obs, old, nue) -> m_ejecCalc());
        v_tfMinutos.textProperty().addListener((obs, old, nue) -> m_ejecCalc());

        m_ejecCalc();
    }

    private void m_ejecCalc() {
        try {
            double v_peso = Double.parseDouble(v_tfPeso.getText());
            double v_perd = Double.parseDouble(v_tfPerd.getText());
            double v_velo = v_slVelo.getValue();

            int v_minUser = 30;
            if (!v_tfMinutos.getText().isEmpty()) {
                v_minUser = Integer.parseInt(v_tfMinutos.getText());
            }

            a_modCalc.set_aPesoActu(v_peso);
            a_modCalc.set_aKiloPerd(v_perd);
            a_modCalc.set_aVeloCorr(v_velo);

            v_lbKcalTota.setText(String.format("%.0f kg", v_perd));
            v_lbKcalMin.setText(String.format("%.1fk", a_modCalc.m_calcCaloTota() / 1000));
            v_lbKcalHora.setText(String.format("%.0f", a_modCalc.m_calcKcalHora()));
            v_lbHoraTota.setText(String.format("%.1fh", a_modCalc.m_calcHoraTota()));

            v_lbSess30.setText(String.valueOf(a_modCalc.m_calcNumSesi(30)));
            v_lbSess45.setText(String.valueOf(a_modCalc.m_calcNumSesi(45)));
            v_lbSess60.setText(String.valueOf(a_modCalc.m_calcNumSesi(60)));
            v_lbSess75.setText(String.valueOf(a_modCalc.m_calcNumSesi(75)));
            v_lbSess90.setText(String.valueOf(a_modCalc.m_calcNumSesi(90)));
            v_lbSess120.setText(String.valueOf(a_modCalc.m_calcNumSesi(120)));

            int v_sesionesUser = a_modCalc.m_calcNumSesi(v_minUser);
            v_lbSessUser.setText("Necesitas " + v_sesionesUser + " sesiones de " + v_minUser + " min");

        } catch (NumberFormatException e) {
        }
    }
}