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
@Table(name = "LOJAS_A_ZDADOSLOG_CD", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "LojasAZdadoslogCd.findAll", query = "SELECT l FROM LojasAZdadoslogCd l"),
    @NamedQuery(name = "LojasAZdadoslogCd.findById", query = "SELECT l FROM LojasAZdadoslogCd l WHERE l.id = :id"),
    @NamedQuery(name = "LojasAZdadoslogCd.findByLoja", query = "SELECT l FROM LojasAZdadoslogCd l WHERE l.loja = :loja"),
    @NamedQuery(name = "LojasAZdadoslogCd.findByMaterial", query = "SELECT l FROM LojasAZdadoslogCd l WHERE l.material = :material"),
    @NamedQuery(name = "LojasAZdadoslogCd.findByPosDpstZdadoslog", query = "SELECT l FROM LojasAZdadoslogCd l WHERE l.posDpstZdadoslog = :posDpstZdadoslog"),
    @NamedQuery(name = "LojasAZdadoslogCd.findByPerfDistZdadoslog", query = "SELECT l FROM LojasAZdadoslogCd l WHERE l.perfDistZdadoslog = :perfDistZdadoslog"),
    @NamedQuery(name = "LojasAZdadoslogCd.findByFonecedor1Zdadoslog", query = "SELECT l FROM LojasAZdadoslogCd l WHERE l.fonecedor1Zdadoslog = :fonecedor1Zdadoslog"),
    @NamedQuery(name = "LojasAZdadoslogCd.findByFonecedor2Zdadoslog", query = "SELECT l FROM LojasAZdadoslogCd l WHERE l.fonecedor2Zdadoslog = :fonecedor2Zdadoslog")})
public class LojasAZdadoslogCd implements Serializable {

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
    @Column(name = "POS_DPST_ZDADOSLOG", length = 40)
    private String posDpstZdadoslog;
    @Column(name = "PERF_DIST_ZDADOSLOG", length = 20)
    private String perfDistZdadoslog;
    @Column(name = "FONECEDOR_1_ZDADOSLOG", length = 20)
    private String fonecedor1Zdadoslog;
    @Column(name = "FONECEDOR_2_ZDADOSLOG", length = 20)
    private String fonecedor2Zdadoslog;

    public LojasAZdadoslogCd() {
    }

    public LojasAZdadoslogCd(Integer id) {
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

    public String getPosDpstZdadoslog() {
        return posDpstZdadoslog;
    }

    public void setPosDpstZdadoslog(String posDpstZdadoslog) {
        String oldPosDpstZdadoslog = this.posDpstZdadoslog;
        this.posDpstZdadoslog = posDpstZdadoslog;
        changeSupport.firePropertyChange("posDpstZdadoslog", oldPosDpstZdadoslog, posDpstZdadoslog);
    }

    public String getPerfDistZdadoslog() {
        return perfDistZdadoslog;
    }

    public void setPerfDistZdadoslog(String perfDistZdadoslog) {
        String oldPerfDistZdadoslog = this.perfDistZdadoslog;
        this.perfDistZdadoslog = perfDistZdadoslog;
        changeSupport.firePropertyChange("perfDistZdadoslog", oldPerfDistZdadoslog, perfDistZdadoslog);
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
        if (!(object instanceof LojasAZdadoslogCd)) {
            return false;
        }
        LojasAZdadoslogCd other = (LojasAZdadoslogCd) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.LojasAZdadoslogCd[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
