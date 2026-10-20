/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.super_bits.modulos.SBAcessosModel.model;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

/**
 *
 * @author salvio
 */
@InfoObjetoSB(tags = {"Usuário convidado"}, plural = "Usuário convidadado")
public class ContatoAonimoTransitorio extends UsuarioAnonimo implements ComoUsuario {

    @InfoCampo(tipo = FabTipoAtributoObjeto.TELEFONE_GENERICO)
    private String telefone;

    public ContatoAonimoTransitorio(String pnome, String pTelefone) {
        super(pnome);
        telefone = pTelefone;

    }

}
