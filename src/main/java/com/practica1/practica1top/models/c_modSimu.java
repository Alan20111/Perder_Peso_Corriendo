package com.practica1.practica1top.models;

public class c_modSimu {

    private double a_pesoActu;
    private double a_kiloPerd;
    private double a_veloCorr;

    private final int k_caloKilo = 7700;

    public c_modSimu() {
    }

    public double m_calcCaloTota() {
        return a_kiloPerd * k_caloKilo;
    }

    public double m_obteMetValo() {
        if (a_veloCorr < 8) return 8.3;
        if (a_veloCorr < 10) return 9.8;
        if (a_veloCorr < 12) return 11.0;
        if (a_veloCorr < 14) return 11.8;
        return 12.8;
    }

    public double m_calcKcalMinu() {
        double v_metActu = m_obteMetValo();
        return (v_metActu * a_pesoActu * 3.5) / 200;
    }

    public double m_calcKcalHora() {
        return m_calcKcalMinu() * 60;
    }

    public double m_calcHoraTota() {
        if (m_calcKcalHora() == 0) return 0;
        return m_calcCaloTota() / m_calcKcalHora();
    }

    public int m_calcNumSesi(int p_minuSesi) {
        if (p_minuSesi <= 0) return 0;

        double v_totaMinu = m_calcHoraTota() * 60;

        return (int) Math.ceil(v_totaMinu / p_minuSesi);
    }

    public void set_aPesoActu(double p_peso) { this.a_pesoActu = p_peso; }
    public void set_aKiloPerd(double p_kilo) { this.a_kiloPerd = p_kilo; }
    public void set_aVeloCorr(double p_velo) { this.a_veloCorr = p_velo; }
}