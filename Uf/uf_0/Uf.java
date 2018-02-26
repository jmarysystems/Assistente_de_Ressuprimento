/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uf_0;

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
@Table(name = "UF", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Uf.findAll", query = "SELECT u FROM Uf u"),
    @NamedQuery(name = "Uf.findById", query = "SELECT u FROM Uf u WHERE u.id = :id"),
    @NamedQuery(name = "Uf.findByDataCadastro", query = "SELECT u FROM Uf u WHERE u.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Uf.findByIdUsuario", query = "SELECT u FROM Uf u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Uf.findByIdPais", query = "SELECT u FROM Uf u WHERE u.idPais = :idPais"),
    @NamedQuery(name = "Uf.findByNome", query = "SELECT u FROM Uf u WHERE u.nome = :nome"),
    @NamedQuery(name = "Uf.findByCodigoIbge", query = "SELECT u FROM Uf u WHERE u.codigoIbge = :codigoIbge"),
    @NamedQuery(name = "Uf.findBySigla", query = "SELECT u FROM Uf u WHERE u.sigla = :sigla")})
public class Uf implements Serializable {
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
    @Column(name = "NOME", nullable = false, length = 300)
    private String nome;
    @Basic(optional = false)
    @Column(name = "CODIGO_IBGE", nullable = false, length = 300)
    private String codigoIbge;
    @Column(name = "SIGLA", length = 300)
    private String sigla;

    public Uf() {
    }

    public Uf(Integer id) {
        this.id = id;
    }

    public Uf(Integer id, int idUsuario, int idPais, String nome, String codigoIbge) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPais = idPais;
        this.nome = nome;
        this.codigoIbge = codigoIbge;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        String oldCodigoIbge = this.codigoIbge;
        this.codigoIbge = codigoIbge;
        changeSupport.firePropertyChange("codigoIbge", oldCodigoIbge, codigoIbge);
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        String oldSigla = this.sigla;
        this.sigla = sigla;
        changeSupport.firePropertyChange("sigla", oldSigla, sigla);
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
        if (!(object instanceof Uf)) {
            return false;
        }
        Uf other = (Uf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Xxx.Uf[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
