/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.model.tokens.tokenRecuperacaoDeSenha;

import com.super_bits.modulos.SBAcessosModel.model.tokens.TokenAcesso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 *
 * @author desenvolvedorninja01
 * @since 19/09/2019
 * @version 1.0
 */
@Entity
@InfoObjetoSB(plural = "Tokens de recuperação de senha", tags = "Token Recuperação de senha", icone = "fa fa-key")
public class TokenRecuperacaoSenha extends TokenAcesso {

}
