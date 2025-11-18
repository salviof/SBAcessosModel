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
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.modulos.CEP.Bairro;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreValidacao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.ItfAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.UtilSBCoreArquivos;
import com.super_bits.modulosSB.SBCore.modulos.ManipulaArquivo.interfaces.ItfCentralDeArquivos;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import com.super_bits.modulosSB.SBCore.modulos.geradorCodigo.model.EstruturaDeEntidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoEntidadeSimples;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreReflexaoObjeto;
import java.util.List;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoCidade;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.cep.ComoLocal;

/**
 *
 *
 * @author desenvolvedor
 */
public abstract class RespostaComGestaoEMRegraDeNegocioPadrao extends RespostaComGestaoEntityManager implements ItfRespostaComExecucaoDeRegraDeNegocio {

    private final ComoEntidadeSimples entidadePrincipalAssociada;
    private boolean executouAcoesFinais = false;

    public boolean validarAtributos(ComoEntidadeSimples pEntidade) {
        System.out.println("Falta implementar Validação de Atributos via InfoCampo e Validate");

        pEntidade.getCamposInstaciadosInvalidos().forEach((campo) -> {
            UtilSBCoreValidacao.validacoesBasicas(campo, campo.getFabricaTipoAtributo());
        });
        return true;
    }

    @Override
    public boolean removerEntidade(final ComoEntidadeSimples pObjeto) {
        return super.removerEntidade(pObjeto);
    }

    public void atualizarValoresDinamicos(ComoEntidadeSimples pObjeto) {

        EstruturaDeEntidade est = MapaObjetosProjetoAtual.getEstruturaObjeto(UtilSBCoreReflexaoObjeto.getClassExtraindoProxy(pObjeto.getClass().getSimpleName()));
        if (est != null) {
            est.getCalculos().stream().forEach(calc -> {
                try {
                    if (calc.isAtualizarValorLogicoAoPersistir()) {
                        pObjeto.getCampoInstanciadoByNomeOuAnotacao(calc.getNomeDeclarado()).getValor();
                    }
                } catch (Throwable t) {
                    SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro atualizando informação de calculo" + est.getNomeEntidade() + "." + calc.getNome(), t);
                }

            });
        } else {
            throw new UnsupportedOperationException("Imposível localizar o objeto de referencia para" + pObjeto);
        }
    }

    public <I extends ComoEntidadeSimples> I persistirEntidadeNova(final ComoEntidadeSimples pObjeto) throws ErroRegraDeNegocio {
        if (pObjeto.getId() != 0) {
            throw new ErroRegraDeNegocio("Utilize persistir entidade modo merge para persisitir uma entidade que já existe");
        }
        return atualizarEntidade(pObjeto, true);
    }

    public <I extends ComoEntidadeSimples> I persistirEntidadeModoMerge(final ComoEntidadeSimples pObjeto) throws ErroRegraDeNegocio {
        return atualizarEntidade(pObjeto, true);
    }

    public <I extends ComoEntidadeSimples> I atualizarEntidade(final ComoEntidadeSimples pObjeto) throws ErroRegraDeNegocio {
        return atualizarEntidade(pObjeto, true);
    }

    public <I extends ComoEntidadeSimples> I atualizarEntidade(final ComoEntidadeSimples pObjeto, boolean validarTodosOsCampos) throws ErroRegraDeNegocio {
        boolean umNovoRegistro = false;
        if (!isSucesso()) {
            return null;
        }
        if (pObjeto.getId() == null || pObjeto.getId() == null) {
            umNovoRegistro = true;
        }

        if (pObjeto.isTemCampoAnotado(FabTipoAtributoObjeto.LC_LOCALIZACAO)) {
            ItfCampoInstanciado cpLocalizacao = pObjeto.getCampoInstanciadoByAnotacao(FabTipoAtributoObjeto.LC_LOCALIZACAO);
            if (!cpLocalizacao.isUmCampoNaoInstanciado() && cpLocalizacao.getValor() != null) {
                List<String> mensagensValidaca = UtilSBCoreValidacao.gerarMensagensValidacao(cpLocalizacao, cpLocalizacao.getValor(),
                        cpLocalizacao.getValorComoItemSimples().getId() == null
                        || cpLocalizacao.getValorComoItemSimples().getId() == null, false);
                if (!mensagensValidaca.isEmpty()) {
                    throw new ErroRegraDeNegocio("Endereço inválido: " + mensagensValidaca.get(0));

                } else {

                    Bairro b = (Bairro) cpLocalizacao.getComoCampoLocalizacao().getBairro();
                    ComoCidade c = (ComoCidade) cpLocalizacao.getComoCampoLocalizacao().getCidade();
                    if (b != null) {
                        b.configIDPeloNome();
                        Bairro bload = UtilSBPersistencia.loadEntidade(b, getEm());
                        if (bload != null) {
                            ComoLocal local = (ComoLocal) cpLocalizacao.getValor();
                            local.setBairro(bload);
                        }
                    }
                    if (c != null) {
                        c.configIDPeloNome();
                        ComoCidade cload = UtilSBPersistencia.loadEntidade(c, getEm());

                        if (cload != null) {
                            ComoLocal local = (ComoLocal) cpLocalizacao.getValor();
                            local.getBairro().setCidade(cload);

                        }
                    }
                    //  ComoLocal localizacaoAtualizada = (ComoLocal) cpLocalizacao.getValor();
                    //  localizacaoAtualizada = UtilSBPersistencia.mergeRegistro(cpLocalizacao.getValor(), getEm());
                    //  cpLocalizacao.setValor(localizacaoAtualizada);
                }
            }

        }

        if (pObjeto instanceof ComoEntidadeSimples) {
            if (validarTodosOsCampos) {
                String mensagem = UtilSBCoreValidacao.getPrimeiraInconsistenciaDeValidacao((ComoEntidadeSimples) pObjeto);
                if (mensagem != null) {
                    throw new ErroRegraDeNegocio(mensagem);

                }
            }
        }
        atualizarValoresDinamicos(pObjeto);
        //TODO rever esta abordagem, Tavez seja melhor usar com o AssitenteDeLocalizacaoInput

        if (umNovoRegistro) {

            String imagemPequeno = SBCore.getCentralDeArquivos().getEndrLocalImagem(pObjeto, FabTipoAtributoObjeto.IMG_PEQUENA, SBCore.getCentralDeSessao().getSessaoAtual());;
            String imagemMedio = SBCore.getCentralDeArquivos().getEndrLocalImagem(pObjeto, FabTipoAtributoObjeto.IMG_MEDIA, SBCore.getCentralDeSessao().getSessaoAtual());;
            String imagemGrande = SBCore.getCentralDeArquivos().getEndrLocalImagem(pObjeto, FabTipoAtributoObjeto.IMG_GRANDE, SBCore.getCentralDeSessao().getSessaoAtual());;

            ComoEntidadeSimples objetoCriado = (ComoEntidadeSimples) super.atualizarEntidade(pObjeto);
            if (objetoCriado == null) {
                throw new ErroRegraDeNegocio("Falha salvando objeto");
            }
            if (objetoCriado != null) {
                if (UtilSBCoreArquivos.isArquivoExiste(imagemPequeno)) {
                    if (UtilSBCoreArquivos.copiarArquivos(imagemPequeno, SBCore.getCentralDeArquivos().getEndrLocalImagem(objetoCriado, FabTipoAtributoObjeto.IMG_MEDIA, SBCore.getCentralDeSessao().getSessaoAtual()))) {
                        UtilSBCoreArquivos.removerArquivoLocal(imagemPequeno);
                    }
                }
                if (UtilSBCoreArquivos.isArquivoExiste(imagemMedio)) {
                    if (UtilSBCoreArquivos.copiarArquivos(imagemMedio, SBCore.getCentralDeArquivos().getEndrLocalImagem(objetoCriado, FabTipoAtributoObjeto.IMG_MEDIA, SBCore.getCentralDeSessao().getSessaoAtual()))) {
                        UtilSBCoreArquivos.removerArquivoLocal(imagemMedio);
                    } else {
                        throw new ErroRegraDeNegocio("Erro salvando imagem de referencia");

                    }
                }

                if (UtilSBCoreArquivos.isArquivoExiste(imagemGrande)) {
                    if (UtilSBCoreArquivos.copiarArquivos(imagemGrande, SBCore.getCentralDeArquivos().getEndrLocalImagem(objetoCriado, FabTipoAtributoObjeto.IMG_MEDIA, SBCore.getCentralDeSessao().getSessaoAtual()))) {
                        UtilSBCoreArquivos.removerArquivoLocal(imagemGrande);
                    }
                }

                for (ItfCampoInstanciado campo : pObjeto.getCamposInstanciados()) {
                    if (campo != null) {
                        if (campo.isUmCampoArquivoEntidade()) {
                            if (campo.getComoArquivoDeEntidade().getIntputTemporario() != null) {
                                ItfCentralDeArquivos servicoArquivosDeEntidade = SBCore.getServicoArquivosDeEntidade();
                                servicoArquivosDeEntidade.salvarArquivo(
                                        objetoCriado,
                                        campo.getComoArquivoDeEntidade().getIntputTemporario(),
                                        campo.getNomeCamponaClasse(), campo.getValor().toString()
                                );
                            }
                        }
                    }
                }
            }
            return (I) objetoCriado;
        } else {
            ComoEntidadeSimples entidadeAtualizada = super.atualizarEntidade(pObjeto);
            if (entidadeAtualizada == null) {
                throw new ErroRegraDeNegocio("Os dados foram considerado inconsistentes ");
            } else {
                return (I) entidadeAtualizada;
            }
        }

    }

    /**
     *
     * @param pObjeto
     * @return
     * @throws ErroRegraDeNegocio
     * @deprecated Utilize atualizarEntidadeSetRetorno
     */
    @Deprecated
    public ComoEntidadeSimples atualizarEntidadeConfigRetorno(ComoEntidadeSimples pObjeto) throws ErroRegraDeNegocio {
        return atualizarEntidadeSetRetorno(pObjeto);

    }

    /**
     *
     * @param pObjeto
     * @return Objeto Gerenciado pelo entityManager via Merg
     * @throws ErroRegraDeNegocio
     */
    public ComoEntidadeSimples atualizarEntidadeSetRetorno(ComoEntidadeSimples pObjeto) throws ErroRegraDeNegocio {

        ComoEntidadeSimples registroAtualizado = atualizarEntidade((ComoEntidadeSimples) pObjeto, true);
        if (registroAtualizado != null) {
            setRetorno(registroAtualizado);
        }
        return registroAtualizado;

    }

    /**
     *
     * @param pResp
     * @param pEntidadePrincipalAssociada
     */
    public RespostaComGestaoEMRegraDeNegocioPadrao(ItfRespostaAcaoDoSistema pResp, ComoEntidadeSimples pEntidadePrincipalAssociada) {
        super(pResp, false);
        entidadePrincipalAssociada = pEntidadePrincipalAssociada;
        try {
            executarAcoesIniciais();
            try {
                lancarErroAPartirDaResposta(getResposta());
                regraDeNegocio();
            } catch (ErroRegraDeNegocio er) {
                addErro(er.getMessage());
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

    @Override
    public ItfRespostaAcaoDoSistema setProximoFormulario(ItfAcaoFormulario pFormulario) {
        return (ItfRespostaAcaoDoSistema) super.setProximoFormulario(pFormulario);
    }

    protected void assertValorPositivo(String pMensagem, boolean pValor) throws ErroRegraDeNegocio {
        if (!pValor) {
            throw new ErroRegraDeNegocio(pMensagem);
        }
    }

}
