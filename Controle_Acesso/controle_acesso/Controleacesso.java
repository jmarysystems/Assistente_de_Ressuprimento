/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle_acesso;

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
@Table(name = "CONTROLEACESSO", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Controleacesso.findAll", query = "SELECT c FROM Controleacesso c"),
    @NamedQuery(name = "Controleacesso.findById", query = "SELECT c FROM Controleacesso c WHERE c.id = :id"),
    @NamedQuery(name = "Controleacesso.findByDataCadastro", query = "SELECT c FROM Controleacesso c WHERE c.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "Controleacesso.findByIdUsuario", query = "SELECT c FROM Controleacesso c WHERE c.idUsuario = :idUsuario"),
    @NamedQuery(name = "Controleacesso.findByIdAcesso", query = "SELECT c FROM Controleacesso c WHERE c.idAcesso = :idAcesso"),
    @NamedQuery(name = "Controleacesso.findByIdEstabelecimento", query = "SELECT c FROM Controleacesso c WHERE c.idEstabelecimento = :idEstabelecimento")})
public class Controleacesso implements Serializable {
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
    @Column(name = "ID_ACESSO", nullable = false)
    private int idAcesso;
    @Basic(optional = false)
    @Column(name = "ID_ESTABELECIMENTO", nullable = false)
    private int idEstabelecimento;

    public Controleacesso() {
    }

    public Controleacesso(Integer id) {
        this.id = id;
    }

    public Controleacesso(Integer id, int idUsuario, int idAcesso, int idEstabelecimento) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idAcesso = idAcesso;
        this.idEstabelecimento = idEstabelecimento;
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

    public int getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(int idAcesso) {
        int oldIdAcesso = this.idAcesso;
        this.idAcesso = idAcesso;
        changeSupport.firePropertyChange("idAcesso", oldIdAcesso, idAcesso);
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        int oldIdEstabelecimento = this.idEstabelecimento;
        this.idEstabelecimento = idEstabelecimento;
        changeSupport.firePropertyChange("idEstabelecimento", oldIdEstabelecimento, idEstabelecimento);
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
        if (!(object instanceof Controleacesso)) {
            return false;
        }
        Controleacesso other = (Controleacesso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "xxxx.Controleacesso[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
