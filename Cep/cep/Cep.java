/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cep;

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
@Table(name = "CEP", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Cep.findAll", query = "SELECT c FROM Cep c"),
    @NamedQuery(name = "Cep.findById", query = "SELECT c FROM Cep c WHERE c.id = :id"),
    @NamedQuery(name = "Cep.findByDataCadastro", query = "SELECT c FROM Cep c WHERE c.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Cep.findByIdUsuario", query = "SELECT c FROM Cep c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "Cep.findByIdPais", query = "SELECT c FROM Cep c WHERE c.idPais = :idPais"),
    @NamedQuery(name = "Cep.findByIdUf", query = "SELECT c FROM Cep c WHERE c.idUf = :idUf"),
    @NamedQuery(name = "Cep.findByIdMunicipio", query = "SELECT c FROM Cep c WHERE c.idMunicipio = :idMunicipio"),
    @NamedQuery(name = "Cep.findByCep", query = "SELECT c FROM Cep c WHERE c.cep = :cep"),
    @NamedQuery(name = "Cep.findByLogradouro", query = "SELECT c FROM Cep c WHERE c.logradouro = :logradouro"),
    @NamedQuery(name = "Cep.findByComplemento", query = "SELECT c FROM Cep c WHERE c.complemento = :complemento"),
    @NamedQuery(name = "Cep.findByBairro", query = "SELECT c FROM Cep c WHERE c.bairro = :bairro")})
public class Cep implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "ID_PAIS", nullable = false)
    private int idPais;
    @Basic(optional = false)
    @Column(name = "ID_UF", nullable = false)
    private int idUf;
    @Basic(optional = false)
    @Column(name = "ID_MUNICIPIO", nullable = false)
    private int idMunicipio;
    @Basic(optional = false)
    @Column(name = "CEP", nullable = false, length = 300)
    private String cep;
    @Basic(optional = false)
    @Column(name = "LOGRADOURO", nullable = false, length = 300)
    private String logradouro;
    @Column(name = "COMPLEMENTO", length = 300)
    private String complemento;
    @Basic(optional = false)
    @Column(name = "BAIRRO", nullable = false, length = 300)
    private String bairro;

    public Cep() {
    }

    public Cep(Integer id) {
        this.id = id;
    }

    public Cep(Integer id, int idUsuario, int idPais, int idUf, int idMunicipio, String cep, String logradouro, String bairro) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPais = idPais;
        this.idUf = idUf;
        this.idMunicipio = idMunicipio;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
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

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        int oldIdPais = this.idPais;
        this.idPais = idPais;
        changeSupport.firePropertyChange("idPais", oldIdPais, idPais);
    }

    public int getIdUf() {
        return idUf;
    }

    public void setIdUf(int idUf) {
        int oldIdUf = this.idUf;
        this.idUf = idUf;
        changeSupport.firePropertyChange("idUf", oldIdUf, idUf);
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        int oldIdMunicipio = this.idMunicipio;
        this.idMunicipio = idMunicipio;
        changeSupport.firePropertyChange("idMunicipio", oldIdMunicipio, idMunicipio);
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cep)) {
            return false;
        }
        Cep other = (Cep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yyy.Cep[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
