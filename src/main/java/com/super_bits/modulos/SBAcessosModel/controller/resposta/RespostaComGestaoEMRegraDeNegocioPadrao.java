/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulos.SBAcessosModel.controller.resposta;

import com.super_bits.modulos.SBAcessosModel.model.acoes.AcaoDoSistema;
import com.super_bits.modulos.SBAcessosModel.model.logsRegraDeNegocio.LogsAcoesExecutadas;
import com.super_bits.modulosSB.Persistencia.dao.ErroEmBancoDeDados;
import com.super_bits.modulosSB.Persistencia.dao.ItfRespostaComExecucaoDeRegraDeNegocio;
import com.super_bits.modulosSB.Persistencia.dao.RespostaComGestaoEntityManager;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanSimples;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 *
 * @author desenvolvedor
 */
public abstract class RespostaComGestaoEMRegraDeNegocioPadrao extends RespostaComGestaoEntityManager implements ItfRespostaComExecucaoDeRegraDeNegocio {

    private final ItfBeanSimples entidadePrincipalAssociada;
    private boolean executouAcoesFinais = false;

    public boolean validarAtributos(ItfBeanSimples pEntidade) {
        System.out.println("Falta implementar Validação de Atributos via InfoCampo e Validate");

        pEntidade.getCamposInstaciadosInvalidos().forEach((campo) -> {
            UtilSBCoreValidacao.validaSintaxeENulo(campo, campo.getFabricaTipoAtributo());
        });
        return true;
    }

    @Override
    public boolean removerEntidade(final ItfBeanSimples pObjeto) {
        return super.removerEntidade(pObjeto);
    }

    public <I extends ItfBeanSimples> I atualizarEntidade(final ItfBeanSimples pObjeto) {
        boolean umNovoRegistro = false;
        if (!isSucesso()) {
            return null;
        }
        if (pObjeto.getId() == 0) {
            umNovoRegistro = true;
        }

        if (pObjeto instanceof ItfBeanSimples) {
            String mensagem = UtilSBCoreValidacao.getPrimeiraInconsistenciaDeValidacao((ItfBeanSimples) pObjeto);
            if (mensagem != null) {
                addErro(mensagem);
                return null;
            }
        }

        ItfBeanSimples objetoCriado = (ItfBeanSimples) super.atualizarEntidade(pObjeto);
        if (objetoCriado != null) {
            if (umNovoRegistro) {
                for (ItfCampoInstanciado campo : pObjeto.getCamposInstanciados()) {
                    if (campo != null) {
                        if (campo.isUmCampoArquivoEntidade()) {
                            if (campo.getComoArquivoDeEntidade().getIntputTemporario() != null) {
                                SBCore.getCentralDeArquivos().salvarArquivo(
                                        objetoCriado,
                                        campo.getComoArquivoDeEntidade().getIntputTemporario(),
                                        campo.getNomeCamponaClasse(), campo.getValor().toString()
                                );
                            }
                        }
                    }
                }
            }
        }

        return (I) objetoCriado;
    }

    /**
     *
     * @param pResp
     * @param pEntidadePrincipalAssociada
     */
    public RespostaComGestaoEMRegraDeNegocioPadrao(ItfRespostaAcaoDoSistema pResp, ItfBeanSimples pEntidadePrincipalAssociada) {
        super(pResp, false);
        entidadePrincipalAssociada = pEntidadePrincipalAssociada;
        try {
            executarAcoesIniciais();
            try {
                regraDeNegocio();
            } catch (Throwable t) {
                if (isSucesso()) {
                    addErro("Erro indeterminado tentando " + getAcaoVinculada().getNomeAcao());
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro não tratado executando" + getAcaoVinculada() + t.getMessage(), t);
                }
            }
            executarAcoesFinais();
        } catch (ErroEmBancoDeDados pErro) {
            if (pErro.getMensagemUsuario() != null) {
                addErro(pErro.getMensagemUsuario());
            } else {
                addErro("Erro indeterminado tentando " + getAcaoVinculada().getNomeAcao());
            }
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro não tratado executando" + getAcaoVinculada() + pErro.getMessage() + pErro.getMensagemProgrador(), pErro);

        } catch (Throwable t) {
            if (isSucesso()) {
                addErro("Erro indeterminado tentando " + getAcaoVinculada().getNomeAcao());
                SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro não tratado executando" + getAcaoVinculada() + t.getMessage(), t);
            }

        }
    }

    @Override
    public void executarAcoesFinais() throws ErroEmBancoDeDados {
        if (!executouAcoesFinais) {
            executouAcoesFinais = true;
            if (isSucesso()) {
                if (getAcaoVinculada().getComoController().isTemLogExecucao()) {

                    LogsAcoesExecutadas logAlteracoes = new LogsAcoesExecutadas();
                    logAlteracoes.setAcaoExecutada((AcaoDoSistema) getAcaoVinculada());

                    if (getAcaoVinculada().getComoController().isPrecisaJustificativa()) {
                        String justificativa = entidadePrincipalAssociada.getJustificativa(getAcaoVinculada());
                        if (justificativa == null) {
                            addErro("Esta ação não pode ser executada sem uma justificativa");
                        }

                    }
                    if (isSucesso()) {
                        super.atualizarEntidade(logAlteracoes);
                    }
                }
                if (isSucesso()) {
                    super.executarAcoesFinais(); //To change body of generated methods, choose Tools | Templates.
                }
            } else {
                reverterAcoesFinais();
            }
        }
    }

    @Override
    public void executarAcoesIniciais() throws ErroEmBancoDeDados {
        super.executarAcoesIniciais(); //To change body of generated methods, choose Tools | Templates.
    }

}
