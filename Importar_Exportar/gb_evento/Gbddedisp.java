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
@Table(name = "GBDDEDISP", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbddedisp.findAll", query = "SELECT g FROM Gbddedisp g"),
    @NamedQuery(name = "Gbddedisp.findById", query = "SELECT g FROM Gbddedisp g WHERE g.id = :id"),
    @NamedQuery(name = "Gbddedisp.findByDde", query = "SELECT g FROM Gbddedisp g WHERE g.dde = :dde"),
    @NamedQuery(name = "Gbddedisp.findByTop350", query = "SELECT g FROM Gbddedisp g WHERE g.top350 = :top350"),
    @NamedQuery(name = "Gbddedisp.findByTop2000", query = "SELECT g FROM Gbddedisp g WHERE g.top2000 = :top2000"),
    @NamedQuery(name = "Gbddedisp.findByMix", query = "SELECT g FROM Gbddedisp g WHERE g.mix = :mix"),
    @NamedQuery(name = "Gbddedisp.findByTotal", query = "SELECT g FROM Gbddedisp g WHERE g.total = :total")})
public class Gbddedisp implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "DDE", length = 300)
    private String dde;
    @Column(name = "TOP350", length = 300)
    private String top350;
    @Column(name = "TOP2000", length = 300)
    private String top2000;
    @Column(name = "MIX", length = 300)
    private String mix;
    @Column(name = "TOTAL", length = 300)
    private String total;

    public Gbddedisp() {
    }

    public Gbddedisp(Integer id) {
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

    public String getDde() {
        return dde;
    }

    public void setDde(String dde) {
        String oldDde = this.dde;
        this.dde = dde;
        changeSupport.firePropertyChange("dde", oldDde, dde);
    }

    public String getTop350() {
        return top350;
    }

    public void setTop350(String top350) {
        String oldTop350 = this.top350;
        this.top350 = top350;
        changeSupport.firePropertyChange("top350", oldTop350, top350);
    }

    public String getTop2000() {
        return top2000;
    }

    public void setTop2000(String top2000) {
        String oldTop2000 = this.top2000;
        this.top2000 = top2000;
        changeSupport.firePropertyChange("top2000", oldTop2000, top2000);
    }

    public String getMix() {
        return mix;
    }

    public void setMix(String mix) {
        String oldMix = this.mix;
        this.mix = mix;
        changeSupport.firePropertyChange("mix", oldMix, mix);
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        String oldTotal = this.total;
        this.total = total;
        changeSupport.firePropertyChange("total", oldTotal, total);
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
        if (!(object instanceof Gbddedisp)) {
            return false;
        }
        Gbddedisp other = (Gbddedisp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Yyy.Gbddedisp[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
