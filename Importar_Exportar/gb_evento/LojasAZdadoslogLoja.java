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
@Table(name = "LOJAS_A_ZDADOSLOG_LOJA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "LojasAZdadoslogLoja.findAll", query = "SELECT l FROM LojasAZdadoslogLoja l"),
    @NamedQuery(name = "LojasAZdadoslogLoja.findById", query = "SELECT l FROM LojasAZdadoslogLoja l WHERE l.id = :id"),
    @NamedQuery(name = "LojasAZdadoslogLoja.findByLoja", query = "SELECT l FROM LojasAZdadoslogLoja l WHERE l.loja = :loja"),
    @NamedQuery(name = "LojasAZdadoslogLoja.findByMaterial", query = "SELECT l FROM LojasAZdadoslogLoja l WHERE l.material = :material"),
    @NamedQuery(name = "LojasAZdadoslogLoja.findByGcmZdadoslog", query = "SELECT l FROM LojasAZdadoslogLoja l WHERE l.gcmZdadoslog = :gcmZdadoslog"),
    @NamedQuery(name = "LojasAZdadoslogLoja.findByFonecedor1Zdadoslog", query = "SELECT l FROM LojasAZdadoslogLoja l WHERE l.fonecedor1Zdadoslog = :fonecedor1Zdadoslog"),
    @NamedQuery(name = "LojasAZdadoslogLoja.findByFonecedor2Zdadoslog", query = "SELECT l FROM LojasAZdadoslogLoja l WHERE l.fonecedor2Zdadoslog = :fonecedor2Zdadoslog")})
public class LojasAZdadoslogLoja implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "LOJA", length = 10)
    private String loja;
    @Column(name = "MATERIAL", length = 25)
    private String material;
    @Column(name = "GCM_ZDADOSLOG", length = 10)
    private String gcmZdadoslog;
    @Column(name = "FONECEDOR_1_ZDADOSLOG", length = 20)
    private String fonecedor1Zdadoslog;
    @Column(name = "FONECEDOR_2_ZDADOSLOG", length = 20)
    private String fonecedor2Zdadoslog;

    public LojasAZdadoslogLoja() {
    }

    public LojasAZdadoslogLoja(Integer id) {
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

    public String getLoja() {
        return loja;
    }

    public void setLoja(String loja) {
        String oldLoja = this.loja;
        this.loja = loja;
        changeSupport.firePropertyChange("loja", oldLoja, loja);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    public String getGcmZdadoslog() {
        return gcmZdadoslog;
    }

    public void setGcmZdadoslog(String gcmZdadoslog) {
        String oldGcmZdadoslog = this.gcmZdadoslog;
        this.gcmZdadoslog = gcmZdadoslog;
        changeSupport.firePropertyChange("gcmZdadoslog", oldGcmZdadoslog, gcmZdadoslog);
    }

    public String getFonecedor1Zdadoslog() {
        return fonecedor1Zdadoslog;
    }

    public void setFonecedor1Zdadoslog(String fonecedor1Zdadoslog) {
        String oldFonecedor1Zdadoslog = this.fonecedor1Zdadoslog;
        this.fonecedor1Zdadoslog = fonecedor1Zdadoslog;
        changeSupport.firePropertyChange("fonecedor1Zdadoslog", oldFonecedor1Zdadoslog, fonecedor1Zdadoslog);
    }

    public String getFonecedor2Zdadoslog() {
        return fonecedor2Zdadoslog;
    }

    public void setFonecedor2Zdadoslog(String fonecedor2Zdadoslog) {
        String oldFonecedor2Zdadoslog = this.fonecedor2Zdadoslog;
        this.fonecedor2Zdadoslog = fonecedor2Zdadoslog;
        changeSupport.firePropertyChange("fonecedor2Zdadoslog", oldFonecedor2Zdadoslog, fonecedor2Zdadoslog);
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
        if (!(object instanceof LojasAZdadoslogLoja)) {
            return false;
        }
        LojasAZdadoslogLoja other = (LojasAZdadoslogLoja) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.LojasAZdadoslogLoja[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
