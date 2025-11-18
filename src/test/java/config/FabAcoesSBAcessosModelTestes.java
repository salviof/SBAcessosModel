/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package config;

import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.acoesAutomatizadas.FabTipoAutoExecucaoAcao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.ItensGenericos.basico.UsuarioAnonimo;

@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.PAGINAS_DO_SISTEMA)
public enum FabAcoesSBAcessosModelTestes implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(entidade = UsuarioAnonimo.class)
    USUARIOS_DO_SISTEMA_MB_GESTAO,
    USUARIOS_DO_SISTEMA_FRM_LISTAR,
    USUARIOS_DO_SISTEMA_FRM_EDITAR,
    USUARIOS_DO_SISTEMA_FRM_NOVO,
    @InfoTipoAcaoFormulario(icone = "fa fa-user")
    USUARIOS_DO_SISTEMA_FRM_PERSONALIZADO,
    @InfoTipoAcaoFormulario(icone = "fa fa-user")
    USUARIOS_DO_SISTEMA_FRM_MODAL,
    @InfoTipoAcaoFormulario(icone = "fa fa-user")
    USUARIOS_DO_SISTEMA_FRM_SELECAO_DE_ACAO,
    USUARIOS_DO_SISTEMA_FRM_VISUALIZAR,
    USUARIOS_DO_SISTEMA_CTR_SALVAR_MERGE,
    USUARIOS_DO_SISTEMA_CTR_SALVAR_EDICAO,
    USUARIOS_DO_SISTEMA_CTR_SALVAR_NOVO,
    USUARIOS_DO_SISTEMA_CTR_ALTERAR_STATUS,
    USUARIOS_DO_SISTEMA_CTR_ATIVAR,
    USUARIOS_DO_SISTEMA_CTR_DESATIVAR,
    USUARIOS_DO_SISTEMA_CTR_REMOVER,
    USUARIOS_DO_SISTEMA_CTR_OPERACAO_TESTE,
    @InfoTipoAcaoController(autoExecucao = FabTipoAutoExecucaoAcao.SISTEMA_BOOT)
    USUARIOS_DO_SISTEMA_CTR_OPERACAO_AUTO_EXEC_INIT,
    @InfoTipoAcaoController(autoExecucao = FabTipoAutoExecucaoAcao.LOOP_INFINITO_INTERVALO_30_SEGUNDOS)
    USUARIOS_DO_SISTEMA_CTR_OPERACAO_AUTO_EXEC,

}
