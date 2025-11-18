/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulos.SBAcessosModel.model.acoes.acaoDeEntidade;

import com.super_bits.Controller.Interfaces.acoes.ComoAcaoSecundaria;
import com.super_bits.Controller.Interfaces.permissoes.ItfAcaoGerenciarEntidade;
import com.super_bits.Controller.fabricas.FabTipoAcaoSistemaGenerica;
import com.super_bits.modulosSB.SBCore.fabrica.ComoFabricaAcoes;

/**
 *
 * @author desenvolvedor
 */
public class AcaoFormularioEntidadeEditar extends AcaoFormularioEntidade implements ComoAcaoSecundaria {

    

    public AcaoFormularioEntidadeEditar(ItfAcaoGerenciarEntidade pAcaoPrincipal, Class pClasseRelacionada, String pXhtml,ComoFabricaAcoes enumAcao) {
        super(pAcaoPrincipal, enumAcao, pXhtml);
        tipoAcaoGenerica = FabTipoAcaoSistemaGenerica.FORMULARIO_EDITAR;
    }

}
