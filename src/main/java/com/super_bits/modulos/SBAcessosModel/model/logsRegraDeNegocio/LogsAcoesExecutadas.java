/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.logsRegraDeNegocio;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;

/**
 *
 * @author desenvolvedor
 */
public class LogsAcoesExecutadas extends LogRegraDeNegocio {

    private String justificativa;

    private AcaoDoSistema acaoExecutada;

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public AcaoDoSistema getAcaoExecutada() {
        return acaoExecutada;
    }

    public void setAcaoExecutada(AcaoDoSistema acaoExecutada) {
        this.acaoExecutada = acaoExecutada;
    }

}
