/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package municipio;

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
@Table(name = "MUNICIPIO", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findById", query = "SELECT m FROM Municipio m WHERE m.id = :id"),
    @NamedQuery(name = "Municipio.findByDataCadastro", query = "SELECT m FROM Municipio m WHERE m.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Municipio.findByIdUsuario", query = "SELECT m FROM Municipio m WHERE m.idUsuario = :idUsuario"),
    @NamedQuery(name = "Municipio.findByIdPais", query = "SELECT m FROM Municipio m WHERE m.idPais = :idPais"),
    @NamedQuery(name = "Municipio.findByIdUf", query = "SELECT m FROM Municipio m WHERE m.idUf = :idUf"),
    @NamedQuery(name = "Municipio.findByNome", query = "SELECT m FROM Municipio m WHERE m.nome = :nome"),
    @NamedQuery(name = "Municipio.findByCodigoIbge", query = "SELECT m FROM Municipio m WHERE m.codigoIbge = :codigoIbge"),
    @NamedQuery(name = "Municipio.findByCodigoReceitaFederal", query = "SELECT m FROM Municipio m WHERE m.codigoReceitaFederal = :codigoReceitaFederal"),
    @NamedQuery(name = "Municipio.findByCodigoEstadual", query = "SELECT m FROM Municipio m WHERE m.codigoEstadual = :codigoEstadual")})
public class Municipio implements Serializable {
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
    @Column(name = "NOME", nullable = false, length = 300)
    private String nome;
    @Basic(optional = false)
    @Column(name = "CODIGO_IBGE", nullable = false, length = 300)
    private String codigoIbge;
    @Column(name = "CODIGO_RECEITA_FEDERAL", length = 300)
    private String codigoReceitaFederal;
    @Column(name = "CODIGO_ESTADUAL", length = 300)
    private String codigoEstadual;

    public Municipio() {
    }

    public Municipio(Integer id) {
        this.id = id;
    }

    public Municipio(Integer id, int idUsuario, int idPais, int idUf, String nome, String codigoIbge) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idPais = idPais;
        this.idUf = idUf;
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

    public int getIdUf() {
        return idUf;
    }

    public void setIdUf(int idUf) {
        int oldIdUf = this.idUf;
        this.idUf = idUf;
        changeSupport.firePropertyChange("idUf", oldIdUf, idUf);
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

    public String getCodigoReceitaFederal() {
        return codigoReceitaFederal;
    }

    public void setCodigoReceitaFederal(String codigoReceitaFederal) {
        String oldCodigoReceitaFederal = this.codigoReceitaFederal;
        this.codigoReceitaFederal = codigoReceitaFederal;
        changeSupport.firePropertyChange("codigoReceitaFederal", oldCodigoReceitaFederal, codigoReceitaFederal);
    }

    public String getCodigoEstadual() {
        return codigoEstadual;
    }

    public void setCodigoEstadual(String codigoEstadual) {
        String oldCodigoEstadual = this.codigoEstadual;
        this.codigoEstadual = codigoEstadual;
        changeSupport.firePropertyChange("codigoEstadual", oldCodigoEstadual, codigoEstadual);
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
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yyy.Municipio[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
