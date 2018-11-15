@Entity
@InfoClasse(tags = {"Comprador", "Cliente", "Compra", "Colaborador", "Parceiro"}, icone = "fa fa-shopping-cart", plural = "Compradores")
public class CompradorTest3 extends EntidadeContatoCorporativo {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;

@InfoCampo(tipo = FabCampos.AAA_DESCRITIVO, label = "nome descritivo", descricao = "Descreve o nome da entidade")
@Column(length = 0, nullable = false)
private String nome;

@InfoCampo(tipo = FabCampos.TEXTO_SIMPLES, label = "texto simples", descricao = "Um campo de texto simples")
@Column(length = 0, nullable = false)
private String razaoSocial;

@InfoCampo(tipo = FabCampos.TELEFONE_FIXO_NACIONAL, label = "Telefone", descricao = "Telefone fixo nacional")
@Column(length = 0, nullable = false)
private String telefone;

@InfoCampo(tipo = FabCampos.CNPJ, label = "CNPJ", descricao = "NUmero de registro da empresa")
@Column(length = 0, nullable = false)
private String cnpj;

@InfoCampo(tipo = FabCampos.LISTA_OBJETOS, label = "Filiais", descricao = "Filiais do Comprador")
@OneToMany(targetEntity = FilialComprador.class, cascade = CascadeType.ALL, orphanRemoval = true)
private List<FilialComprador>filiais;

@InfoCampo(tipo = FabCampos.OBJETO_DE_UMA_LISTA, label = "Filial Principal", descricao = "Filial Principal do Comprador")
@OneToMany(targetEntity = FilialComprador)
private List<FilialComprador>filialPrincipal;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "Compradores_Permitidos", joinColumns = @JoinColumn(name = "acesso_id"), inverseJoinColumns = @JoinColumn(name = "comprador_id"))
private List<Comprador>compradoresPermitidos;

@Transient
@ListaCampanha(lista = ListasCampanha.LISTA_PEDIDOS_CAMPANHA)
private List<Pedido> declarado;

@InfoCampo(tipo = FabCampos.QUANTIDADE, label = "Total", descricao = "totalCalculado")
@CalculoCampanha(calculo = CalculosCompradorTest3.CALCULA_TOTAL)
private int valorTotal;

public List<Pedido> getdeclarado{

return pedidosEmAtraso;

}

public int getcalcula_total {

Object resultado = getRetornoSoma();

if (resultado != null) {

return (int) resultado;

} else {

return 0;

}

public int getid(){

return id;

}

public void setid(int id) {

this.id = id;

}

public String getnome(){

return nome;

}

public void setnome(String nome) {

this.nome = nome;

}

public String getrazaoSocial(){

return razaoSocial;

}

public void setrazaoSocial(String razaoSocial) {

this.razaoSocial = razaoSocial;

}

public String gettelefone(){

return telefone;

}

public void settelefone(String telefone) {

this.telefone = telefone;

}

public String getcnpj(){

return cnpj;

}

public void setcnpj(String cnpj) {

this.cnpj = cnpj;

}

}
