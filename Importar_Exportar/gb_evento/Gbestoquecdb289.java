/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gb_evento;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "GBESTOQUECDB289", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbestoquecdb289.findAll", query = "SELECT g FROM Gbestoquecdb289 g"),
    @NamedQuery(name = "Gbestoquecdb289.findById", query = "SELECT g FROM Gbestoquecdb289 g WHERE g.id = :id"),
    @NamedQuery(name = "Gbestoquecdb289.findByEstabelecimento", query = "SELECT g FROM Gbestoquecdb289 g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbestoquecdb289.findByMaterial", query = "SELECT g FROM Gbestoquecdb289 g WHERE g.material = :material"),
    @NamedQuery(name = "Gbestoquecdb289.findByEstoqueCd289", query = "SELECT g FROM Gbestoquecdb289 g WHERE g.estoqueCd289 = :estoqueCd289")})
public class Gbestoquecdb289 implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "ESTABELECIMENTO", length = 300)
    private String estabelecimento;
    @Column(name = "MATERIAL", length = 300)
    private String material;
    @Column(name = "ESTOQUE_CD_289")
    private Integer estoqueCd289;

    public Gbestoquecdb289() {
    }

    public Gbestoquecdb289(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        String oldEstabelecimento = this.estabelecimento;
        this.estabelecimento = estabelecimento;
        changeSupport.firePropertyChange("estabelecimento", oldEstabelecimento, estabelecimento);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    public Integer getEstoqueCd289() {
        return estoqueCd289;
    }

    public void setEstoqueCd289(Integer estoqueCd289) {
        Integer oldEstoqueCd289 = this.estoqueCd289;
        this.estoqueCd289 = estoqueCd289;
        changeSupport.firePropertyChange("estoqueCd289", oldEstoqueCd289, estoqueCd289);
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
        if (!(object instanceof Gbestoquecdb289)) {
            return false;
        }
        Gbestoquecdb289 other = (Gbestoquecdb289) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbestoquecdb289[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
