/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pais_0;

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
@Table(name = "PAIS", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p"),
    @NamedQuery(name = "Pais.findById", query = "SELECT p FROM Pais p WHERE p.id = :id"),
    @NamedQuery(name = "Pais.findByDataCadastro", query = "SELECT p FROM Pais p WHERE p.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Pais.findByIdUsuario", query = "SELECT p FROM Pais p WHERE p.idUsuario = :idUsuario"),
    @NamedQuery(name = "Pais.findByImagemLogotipo", query = "SELECT p FROM Pais p WHERE p.imagemLogotipo = :imagemLogotipo"),
    @NamedQuery(name = "Pais.findByCodigo", query = "SELECT p FROM Pais p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Pais.findByNomePtbr", query = "SELECT p FROM Pais p WHERE p.nomePtbr = :nomePtbr"),
    @NamedQuery(name = "Pais.findByNomeEn", query = "SELECT p FROM Pais p WHERE p.nomeEn = :nomeEn"),
    @NamedQuery(name = "Pais.findBySigla2", query = "SELECT p FROM Pais p WHERE p.sigla2 = :sigla2"),
    @NamedQuery(name = "Pais.findBySigla3", query = "SELECT p FROM Pais p WHERE p.sigla3 = :sigla3")})
public class Pais implements Serializable {
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
    @Column(name = "IMAGEM_LOGOTIPO", length = 300)
    private String imagemLogotipo;
    @Basic(optional = false)
    @Column(name = "CODIGO", nullable = false)
    private int codigo;
    @Basic(optional = false)
    @Column(name = "NOME_PTBR", nullable = false, length = 300)
    private String nomePtbr;
    @Column(name = "NOME_EN", length = 300)
    private String nomeEn;
    @Column(name = "SIGLA2", length = 300)
    private String sigla2;
    @Column(name = "SIGLA3", length = 300)
    private String sigla3;

    public Pais() {
    }

    public Pais(Integer id) {
        this.id = id;
    }

    public Pais(Integer id, int idUsuario, int codigo, String nomePtbr) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.nomePtbr = nomePtbr;
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

    public String getImagemLogotipo() {
        return imagemLogotipo;
    }

    public void setImagemLogotipo(String imagemLogotipo) {
        String oldImagemLogotipo = this.imagemLogotipo;
        this.imagemLogotipo = imagemLogotipo;
        changeSupport.firePropertyChange("imagemLogotipo", oldImagemLogotipo, imagemLogotipo);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        int oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getNomePtbr() {
        return nomePtbr;
    }

    public void setNomePtbr(String nomePtbr) {
        String oldNomePtbr = this.nomePtbr;
        this.nomePtbr = nomePtbr;
        changeSupport.firePropertyChange("nomePtbr", oldNomePtbr, nomePtbr);
    }

    public String getNomeEn() {
        return nomeEn;
    }

    public void setNomeEn(String nomeEn) {
        String oldNomeEn = this.nomeEn;
        this.nomeEn = nomeEn;
        changeSupport.firePropertyChange("nomeEn", oldNomeEn, nomeEn);
    }

    public String getSigla2() {
        return sigla2;
    }

    public void setSigla2(String sigla2) {
        String oldSigla2 = this.sigla2;
        this.sigla2 = sigla2;
        changeSupport.firePropertyChange("sigla2", oldSigla2, sigla2);
    }

    public String getSigla3() {
        return sigla3;
    }

    public void setSigla3(String sigla3) {
        String oldSigla3 = this.sigla3;
        this.sigla3 = sigla3;
        changeSupport.firePropertyChange("sigla3", oldSigla3, sigla3);
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
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "newpackage.Pais[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
