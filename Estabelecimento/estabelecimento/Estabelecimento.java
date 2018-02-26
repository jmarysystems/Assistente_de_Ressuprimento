/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estabelecimento;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "ESTABELECIMENTO", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Estabelecimento.findAll", query = "SELECT e FROM Estabelecimento e"),
    @NamedQuery(name = "Estabelecimento.findById", query = "SELECT e FROM Estabelecimento e WHERE e.id = :id"),
    @NamedQuery(name = "Estabelecimento.findByDataCadastro", query = "SELECT e FROM Estabelecimento e WHERE e.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Estabelecimento.findByIdUsuario", query = "SELECT e FROM Estabelecimento e WHERE e.idUsuario = :idUsuario"),
    @NamedQuery(name = "Estabelecimento.findByObservacao", query = "SELECT e FROM Estabelecimento e WHERE e.observacao = :observacao"),
    @NamedQuery(name = "Estabelecimento.findByImagemLogotipo", query = "SELECT e FROM Estabelecimento e WHERE e.imagemLogotipo = :imagemLogotipo"),
    @NamedQuery(name = "Estabelecimento.findByRazaoSocial", query = "SELECT e FROM Estabelecimento e WHERE e.razaoSocial = :razaoSocial"),
    @NamedQuery(name = "Estabelecimento.findByNomeFantasia", query = "SELECT e FROM Estabelecimento e WHERE e.nomeFantasia = :nomeFantasia"),
    @NamedQuery(name = "Estabelecimento.findByCnpj", query = "SELECT e FROM Estabelecimento e WHERE e.cnpj = :cnpj"),
    @NamedQuery(name = "Estabelecimento.findByInscricaoEstadual", query = "SELECT e FROM Estabelecimento e WHERE e.inscricaoEstadual = :inscricaoEstadual"),
    @NamedQuery(name = "Estabelecimento.findByInscricaoEstadualSt", query = "SELECT e FROM Estabelecimento e WHERE e.inscricaoEstadualSt = :inscricaoEstadualSt"),
    @NamedQuery(name = "Estabelecimento.findByInscricaoMunicipal", query = "SELECT e FROM Estabelecimento e WHERE e.inscricaoMunicipal = :inscricaoMunicipal"),
    @NamedQuery(name = "Estabelecimento.findByInscricaoJuntaComercial", query = "SELECT e FROM Estabelecimento e WHERE e.inscricaoJuntaComercial = :inscricaoJuntaComercial"),
    @NamedQuery(name = "Estabelecimento.findByDataInscJuntaComercial", query = "SELECT e FROM Estabelecimento e WHERE e.dataInscJuntaComercial = :dataInscJuntaComercial"),
    @NamedQuery(name = "Estabelecimento.findBySuframa", query = "SELECT e FROM Estabelecimento e WHERE e.suframa = :suframa"),
    @NamedQuery(name = "Estabelecimento.findByTipo", query = "SELECT e FROM Estabelecimento e WHERE e.tipo = :tipo"),
    @NamedQuery(name = "Estabelecimento.findByDataInicioAtividades", query = "SELECT e FROM Estabelecimento e WHERE e.dataInicioAtividades = :dataInicioAtividades"),
    @NamedQuery(name = "Estabelecimento.findByContato", query = "SELECT e FROM Estabelecimento e WHERE e.contato = :contato"),
    @NamedQuery(name = "Estabelecimento.findByTelefone", query = "SELECT e FROM Estabelecimento e WHERE e.telefone = :telefone"),
    @NamedQuery(name = "Estabelecimento.findByFone2", query = "SELECT e FROM Estabelecimento e WHERE e.fone2 = :fone2"),
    @NamedQuery(name = "Estabelecimento.findByFone3", query = "SELECT e FROM Estabelecimento e WHERE e.fone3 = :fone3"),
    @NamedQuery(name = "Estabelecimento.findByEmail", query = "SELECT e FROM Estabelecimento e WHERE e.email = :email"),
    @NamedQuery(name = "Estabelecimento.findByOutroemail", query = "SELECT e FROM Estabelecimento e WHERE e.outroemail = :outroemail"),
    @NamedQuery(name = "Estabelecimento.findByFacebook", query = "SELECT e FROM Estabelecimento e WHERE e.facebook = :facebook"),
    @NamedQuery(name = "Estabelecimento.findByCrt", query = "SELECT e FROM Estabelecimento e WHERE e.crt = :crt"),
    @NamedQuery(name = "Estabelecimento.findByTipoRegime", query = "SELECT e FROM Estabelecimento e WHERE e.tipoRegime = :tipoRegime"),
    @NamedQuery(name = "Estabelecimento.findByAliquotaPis", query = "SELECT e FROM Estabelecimento e WHERE e.aliquotaPis = :aliquotaPis"),
    @NamedQuery(name = "Estabelecimento.findByAliquotaCofins", query = "SELECT e FROM Estabelecimento e WHERE e.aliquotaCofins = :aliquotaCofins"),
    @NamedQuery(name = "Estabelecimento.findByAliquotaSat", query = "SELECT e FROM Estabelecimento e WHERE e.aliquotaSat = :aliquotaSat"),
    @NamedQuery(name = "Estabelecimento.findByCep", query = "SELECT e FROM Estabelecimento e WHERE e.cep = :cep"),
    @NamedQuery(name = "Estabelecimento.findByLogradouro", query = "SELECT e FROM Estabelecimento e WHERE e.logradouro = :logradouro"),
    @NamedQuery(name = "Estabelecimento.findByNumero", query = "SELECT e FROM Estabelecimento e WHERE e.numero = :numero"),
    @NamedQuery(name = "Estabelecimento.findByComplemento", query = "SELECT e FROM Estabelecimento e WHERE e.complemento = :complemento"),
    @NamedQuery(name = "Estabelecimento.findByBairro", query = "SELECT e FROM Estabelecimento e WHERE e.bairro = :bairro"),
    @NamedQuery(name = "Estabelecimento.findByCodigoIbgeCidade", query = "SELECT e FROM Estabelecimento e WHERE e.codigoIbgeCidade = :codigoIbgeCidade"),
    @NamedQuery(name = "Estabelecimento.findByCodigoIbgeUf", query = "SELECT e FROM Estabelecimento e WHERE e.codigoIbgeUf = :codigoIbgeUf"),
    @NamedQuery(name = "Estabelecimento.findByCidade", query = "SELECT e FROM Estabelecimento e WHERE e.cidade = :cidade"),
    @NamedQuery(name = "Estabelecimento.findByEstado", query = "SELECT e FROM Estabelecimento e WHERE e.estado = :estado"),
    @NamedQuery(name = "Estabelecimento.findByPais", query = "SELECT e FROM Estabelecimento e WHERE e.pais = :pais"),
    @NamedQuery(name = "Estabelecimento.findByCodigoTerceiros", query = "SELECT e FROM Estabelecimento e WHERE e.codigoTerceiros = :codigoTerceiros"),
    @NamedQuery(name = "Estabelecimento.findByCodigoGps", query = "SELECT e FROM Estabelecimento e WHERE e.codigoGps = :codigoGps"),
    @NamedQuery(name = "Estabelecimento.findByCodigoCnaePrincipal", query = "SELECT e FROM Estabelecimento e WHERE e.codigoCnaePrincipal = :codigoCnaePrincipal"),
    @NamedQuery(name = "Estabelecimento.findByCei", query = "SELECT e FROM Estabelecimento e WHERE e.cei = :cei"),
    @NamedQuery(name = "Estabelecimento.findByFpas", query = "SELECT e FROM Estabelecimento e WHERE e.fpas = :fpas")})
public class Estabelecimento implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "DATA_CADASTRO")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO", nullable = false)
    private int idUsuario;
    @Column(name = "OBSERVACAO", length = 900)
    private String observacao;
    @Column(name = "IMAGEM_LOGOTIPO", length = 300)
    private String imagemLogotipo;
    @Basic(optional = false)
    @Column(name = "RAZAO_SOCIAL", nullable = false, length = 300)
    private String razaoSocial;
    @Column(name = "NOME_FANTASIA", length = 300)
    private String nomeFantasia;
    @Basic(optional = false)
    @Column(name = "CNPJ", nullable = false, length = 300)
    private String cnpj;
    @Column(name = "INSCRICAO_ESTADUAL", length = 300)
    private String inscricaoEstadual;
    @Column(name = "INSCRICAO_ESTADUAL_ST", length = 300)
    private String inscricaoEstadualSt;
    @Column(name = "INSCRICAO_MUNICIPAL", length = 300)
    private String inscricaoMunicipal;
    @Column(name = "INSCRICAO_JUNTA_COMERCIAL", length = 300)
    private String inscricaoJuntaComercial;
    @Column(name = "DATA_INSC_JUNTA_COMERCIAL")
    @Temporal(TemporalType.DATE)
    private Date dataInscJuntaComercial;
    @Column(name = "SUFRAMA", length = 300)
    private String suframa;
    @Column(name = "TIPO", length = 300)
    private String tipo;
    @Column(name = "DATA_INICIO_ATIVIDADES")
    @Temporal(TemporalType.DATE)
    private Date dataInicioAtividades;
    @Column(name = "CONTATO", length = 300)
    private String contato;
    @Column(name = "TELEFONE", length = 300)
    private String telefone;
    @Column(name = "FONE2", length = 300)
    private String fone2;
    @Column(name = "FONE3", length = 300)
    private String fone3;
    @Column(name = "EMAIL", length = 300)
    private String email;
    @Column(name = "OUTROEMAIL", length = 300)
    private String outroemail;
    @Column(name = "FACEBOOK", length = 300)
    private String facebook;
    @Column(name = "CRT", length = 300)
    private String crt;
    @Column(name = "TIPO_REGIME", length = 300)
    private String tipoRegime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ALIQUOTA_PIS", precision = 52)
    private Float aliquotaPis;
    @Column(name = "ALIQUOTA_COFINS", precision = 52)
    private Float aliquotaCofins;
    @Column(name = "ALIQUOTA_SAT", precision = 52)
    private Float aliquotaSat;
    @Column(name = "CEP", length = 300)
    private String cep;
    @Column(name = "LOGRADOURO", length = 300)
    private String logradouro;
    @Column(name = "NUMERO", length = 300)
    private String numero;
    @Column(name = "COMPLEMENTO", length = 300)
    private String complemento;
    @Column(name = "BAIRRO", length = 300)
    private String bairro;
    @Column(name = "CODIGO_IBGE_CIDADE", length = 300)
    private String codigoIbgeCidade;
    @Column(name = "CODIGO_IBGE_UF", length = 300)
    private String codigoIbgeUf;
    @Basic(optional = false)
    @Column(name = "CIDADE", nullable = false)
    private int cidade;
    @Basic(optional = false)
    @Column(name = "ESTADO", nullable = false)
    private int estado;
    @Basic(optional = false)
    @Column(name = "PAIS", nullable = false)
    private int pais;
    @Column(name = "CODIGO_TERCEIROS", length = 300)
    private String codigoTerceiros;
    @Column(name = "CODIGO_GPS", length = 300)
    private String codigoGps;
    @Column(name = "CODIGO_CNAE_PRINCIPAL", length = 300)
    private String codigoCnaePrincipal;
    @Column(name = "CEI", length = 300)
    private String cei;
    @Column(name = "FPAS", length = 300)
    private String fpas;

    public Estabelecimento() {
    }

    public Estabelecimento(Integer id) {
        this.id = id;
    }

    public Estabelecimento(Integer id, int idUsuario, String razaoSocial, String cnpj, int cidade, int estado, int pais) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        Date oldDataCadastro = this.dataCadastro;
        this.dataCadastro = dataCadastro;
        changeSupport.firePropertyChange("dataCadastro", oldDataCadastro, dataCadastro);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        int oldIdUsuario = this.idUsuario;
        this.idUsuario = idUsuario;
        changeSupport.firePropertyChange("idUsuario", oldIdUsuario, idUsuario);
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        String oldObservacao = this.observacao;
        this.observacao = observacao;
        changeSupport.firePropertyChange("observacao", oldObservacao, observacao);
    }

    public String getImagemLogotipo() {
        return imagemLogotipo;
    }

    public void setImagemLogotipo(String imagemLogotipo) {
        String oldImagemLogotipo = this.imagemLogotipo;
        this.imagemLogotipo = imagemLogotipo;
        changeSupport.firePropertyChange("imagemLogotipo", oldImagemLogotipo, imagemLogotipo);
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        String oldRazaoSocial = this.razaoSocial;
        this.razaoSocial = razaoSocial;
        changeSupport.firePropertyChange("razaoSocial", oldRazaoSocial, razaoSocial);
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        String oldNomeFantasia = this.nomeFantasia;
        this.nomeFantasia = nomeFantasia;
        changeSupport.firePropertyChange("nomeFantasia", oldNomeFantasia, nomeFantasia);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        String oldCnpj = this.cnpj;
        this.cnpj = cnpj;
        changeSupport.firePropertyChange("cnpj", oldCnpj, cnpj);
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        String oldInscricaoEstadual = this.inscricaoEstadual;
        this.inscricaoEstadual = inscricaoEstadual;
        changeSupport.firePropertyChange("inscricaoEstadual", oldInscricaoEstadual, inscricaoEstadual);
    }

    public String getInscricaoEstadualSt() {
        return inscricaoEstadualSt;
    }

    public void setInscricaoEstadualSt(String inscricaoEstadualSt) {
        String oldInscricaoEstadualSt = this.inscricaoEstadualSt;
        this.inscricaoEstadualSt = inscricaoEstadualSt;
        changeSupport.firePropertyChange("inscricaoEstadualSt", oldInscricaoEstadualSt, inscricaoEstadualSt);
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        String oldInscricaoMunicipal = this.inscricaoMunicipal;
        this.inscricaoMunicipal = inscricaoMunicipal;
        changeSupport.firePropertyChange("inscricaoMunicipal", oldInscricaoMunicipal, inscricaoMunicipal);
    }

    public String getInscricaoJuntaComercial() {
        return inscricaoJuntaComercial;
    }

    public void setInscricaoJuntaComercial(String inscricaoJuntaComercial) {
        String oldInscricaoJuntaComercial = this.inscricaoJuntaComercial;
        this.inscricaoJuntaComercial = inscricaoJuntaComercial;
        changeSupport.firePropertyChange("inscricaoJuntaComercial", oldInscricaoJuntaComercial, inscricaoJuntaComercial);
    }

    public Date getDataInscJuntaComercial() {
        return dataInscJuntaComercial;
    }

    public void setDataInscJuntaComercial(Date dataInscJuntaComercial) {
        Date oldDataInscJuntaComercial = this.dataInscJuntaComercial;
        this.dataInscJuntaComercial = dataInscJuntaComercial;
        changeSupport.firePropertyChange("dataInscJuntaComercial", oldDataInscJuntaComercial, dataInscJuntaComercial);
    }

    public String getSuframa() {
        return suframa;
    }

    public void setSuframa(String suframa) {
        String oldSuframa = this.suframa;
        this.suframa = suframa;
        changeSupport.firePropertyChange("suframa", oldSuframa, suframa);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public Date getDataInicioAtividades() {
        return dataInicioAtividades;
    }

    public void setDataInicioAtividades(Date dataInicioAtividades) {
        Date oldDataInicioAtividades = this.dataInicioAtividades;
        this.dataInicioAtividades = dataInicioAtividades;
        changeSupport.firePropertyChange("dataInicioAtividades", oldDataInicioAtividades, dataInicioAtividades);
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        String oldContato = this.contato;
        this.contato = contato;
        changeSupport.firePropertyChange("contato", oldContato, contato);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        String oldTelefone = this.telefone;
        this.telefone = telefone;
        changeSupport.firePropertyChange("telefone", oldTelefone, telefone);
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        String oldFone2 = this.fone2;
        this.fone2 = fone2;
        changeSupport.firePropertyChange("fone2", oldFone2, fone2);
    }

    public String getFone3() {
        return fone3;
    }

    public void setFone3(String fone3) {
        String oldFone3 = this.fone3;
        this.fone3 = fone3;
        changeSupport.firePropertyChange("fone3", oldFone3, fone3);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public String getOutroemail() {
        return outroemail;
    }

    public void setOutroemail(String outroemail) {
        String oldOutroemail = this.outroemail;
        this.outroemail = outroemail;
        changeSupport.firePropertyChange("outroemail", oldOutroemail, outroemail);
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        String oldFacebook = this.facebook;
        this.facebook = facebook;
        changeSupport.firePropertyChange("facebook", oldFacebook, facebook);
    }

    public String getCrt() {
        return crt;
    }

    public void setCrt(String crt) {
        String oldCrt = this.crt;
        this.crt = crt;
        changeSupport.firePropertyChange("crt", oldCrt, crt);
    }

    public String getTipoRegime() {
        return tipoRegime;
    }

    public void setTipoRegime(String tipoRegime) {
        String oldTipoRegime = this.tipoRegime;
        this.tipoRegime = tipoRegime;
        changeSupport.firePropertyChange("tipoRegime", oldTipoRegime, tipoRegime);
    }

    public Float getAliquotaPis() {
        return aliquotaPis;
    }

    public void setAliquotaPis(Float aliquotaPis) {
        Float oldAliquotaPis = this.aliquotaPis;
        this.aliquotaPis = aliquotaPis;
        changeSupport.firePropertyChange("aliquotaPis", oldAliquotaPis, aliquotaPis);
    }

    public Float getAliquotaCofins() {
        return aliquotaCofins;
    }

    public void setAliquotaCofins(Float aliquotaCofins) {
        Float oldAliquotaCofins = this.aliquotaCofins;
        this.aliquotaCofins = aliquotaCofins;
        changeSupport.firePropertyChange("aliquotaCofins", oldAliquotaCofins, aliquotaCofins);
    }

    public Float getAliquotaSat() {
        return aliquotaSat;
    }

    public void setAliquotaSat(Float aliquotaSat) {
        Float oldAliquotaSat = this.aliquotaSat;
        this.aliquotaSat = aliquotaSat;
        changeSupport.firePropertyChange("aliquotaSat", oldAliquotaSat, aliquotaSat);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        String oldCep = this.cep;
        this.cep = cep;
        changeSupport.firePropertyChange("cep", oldCep, cep);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        String oldLogradouro = this.logradouro;
        this.logradouro = logradouro;
        changeSupport.firePropertyChange("logradouro", oldLogradouro, logradouro);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        String oldNumero = this.numero;
        this.numero = numero;
        changeSupport.firePropertyChange("numero", oldNumero, numero);
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        String oldComplemento = this.complemento;
        this.complemento = complemento;
        changeSupport.firePropertyChange("complemento", oldComplemento, complemento);
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        String oldBairro = this.bairro;
        this.bairro = bairro;
        changeSupport.firePropertyChange("bairro", oldBairro, bairro);
    }

    public String getCodigoIbgeCidade() {
        return codigoIbgeCidade;
    }

    public void setCodigoIbgeCidade(String codigoIbgeCidade) {
        String oldCodigoIbgeCidade = this.codigoIbgeCidade;
        this.codigoIbgeCidade = codigoIbgeCidade;
        changeSupport.firePropertyChange("codigoIbgeCidade", oldCodigoIbgeCidade, codigoIbgeCidade);
    }

    public String getCodigoIbgeUf() {
        return codigoIbgeUf;
    }

    public void setCodigoIbgeUf(String codigoIbgeUf) {
        String oldCodigoIbgeUf = this.codigoIbgeUf;
        this.codigoIbgeUf = codigoIbgeUf;
        changeSupport.firePropertyChange("codigoIbgeUf", oldCodigoIbgeUf, codigoIbgeUf);
    }

    public int getCidade() {
        return cidade;
    }

    public void setCidade(int cidade) {
        int oldCidade = this.cidade;
        this.cidade = cidade;
        changeSupport.firePropertyChange("cidade", oldCidade, cidade);
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        int oldEstado = this.estado;
        this.estado = estado;
        changeSupport.firePropertyChange("estado", oldEstado, estado);
    }

    public int getPais() {
        return pais;
    }

    public void setPais(int pais) {
        int oldPais = this.pais;
        this.pais = pais;
        changeSupport.firePropertyChange("pais", oldPais, pais);
    }

    public String getCodigoTerceiros() {
        return codigoTerceiros;
    }

    public void setCodigoTerceiros(String codigoTerceiros) {
        String oldCodigoTerceiros = this.codigoTerceiros;
        this.codigoTerceiros = codigoTerceiros;
        changeSupport.firePropertyChange("codigoTerceiros", oldCodigoTerceiros, codigoTerceiros);
    }

    public String getCodigoGps() {
        return codigoGps;
    }

    public void setCodigoGps(String codigoGps) {
        String oldCodigoGps = this.codigoGps;
        this.codigoGps = codigoGps;
        changeSupport.firePropertyChange("codigoGps", oldCodigoGps, codigoGps);
    }

    public String getCodigoCnaePrincipal() {
        return codigoCnaePrincipal;
    }

    public void setCodigoCnaePrincipal(String codigoCnaePrincipal) {
        String oldCodigoCnaePrincipal = this.codigoCnaePrincipal;
        this.codigoCnaePrincipal = codigoCnaePrincipal;
        changeSupport.firePropertyChange("codigoCnaePrincipal", oldCodigoCnaePrincipal, codigoCnaePrincipal);
    }

    public String getCei() {
        return cei;
    }

    public void setCei(String cei) {
        String oldCei = this.cei;
        this.cei = cei;
        changeSupport.firePropertyChange("cei", oldCei, cei);
    }

    public String getFpas() {
        return fpas;
    }

    public void setFpas(String fpas) {
        String oldFpas = this.fpas;
        this.fpas = fpas;
        changeSupport.firePropertyChange("fpas", oldFpas, fpas);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estabelecimento)) {
            return false;
        }
        Estabelecimento other = (Estabelecimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yyy.Estabelecimento[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
