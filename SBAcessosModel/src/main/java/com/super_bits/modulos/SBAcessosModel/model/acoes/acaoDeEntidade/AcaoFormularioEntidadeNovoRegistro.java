/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ComoAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoEntidade;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.fabrica.ComoFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioEntidadeNovoRegistro extends AcaoFormularioEntidade implements ComoAcaoSecundaria {

    

    public AcaoFormularioEntidadeNovoRegistro(ItfAcaoGerenciarEntidade pAcaoPrincipal,ComoFabricaAcoes pFabrica ,String pXhtml) {
        super(pAcaoPrincipal, pFabrica, pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_NOVO_REGISTRO;
    }

}
