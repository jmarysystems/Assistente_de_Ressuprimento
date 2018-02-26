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
@Table(name = "ANALISEDEVOLUTIVA", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Analisedevolutiva.findAll", query = "SELECT a FROM Analisedevolutiva a"),
    @NamedQuery(name = "Analisedevolutiva.findById", query = "SELECT a FROM Analisedevolutiva a WHERE a.id = :id"),
    @NamedQuery(name = "Analisedevolutiva.findByMaterial", query = "SELECT a FROM Analisedevolutiva a WHERE a.material = :material"),
    @NamedQuery(name = "Analisedevolutiva.findByQtdSolicitada", query = "SELECT a FROM Analisedevolutiva a WHERE a.qtdSolicitada = :qtdSolicitada"),
    @NamedQuery(name = "Analisedevolutiva.findByQtdDevolutiva", query = "SELECT a FROM Analisedevolutiva a WHERE a.qtdDevolutiva = :qtdDevolutiva"),
    @NamedQuery(name = "Analisedevolutiva.findByQtdDevolutivaMe80fn", query = "SELECT a FROM Analisedevolutiva a WHERE a.qtdDevolutivaMe80fn = :qtdDevolutivaMe80fn")})
public class Analisedevolutiva implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "MATERIAL", length = 300)
    private String material;
    @Column(name = "QTD_SOLICITADA")  // MUDOU PARA PLANILHAS_RA_LISTADOS
    private Integer qtdSolicitada;
    @Column(name = "QTD_DEVOLUTIVA") // MUDOU PARA PLANILHAS_RA_ALTERADOS
    private Integer qtdDevolutiva;
    @Column(name = "QTD_DEVOLUTIVA_ME80FN") // NÃO UTILIZADO
    private Integer qtdDevolutivaMe80fn;

    public Analisedevolutiva() {
    }

    public Analisedevolutiva(Integer id) {
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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        String oldMaterial = this.material;
        this.material = material;
        changeSupport.firePropertyChange("material", oldMaterial, material);
    }

    public Integer getQtdSolicitada() {
        return qtdSolicitada;
    }

    public void setQtdSolicitada(Integer qtdSolicitada) {
        Integer oldQtdSolicitada = this.qtdSolicitada;
        this.qtdSolicitada = qtdSolicitada;
        changeSupport.firePropertyChange("qtdSolicitada", oldQtdSolicitada, qtdSolicitada);
    }

    public Integer getQtdDevolutiva() {
        return qtdDevolutiva;
    }

    public void setQtdDevolutiva(Integer qtdDevolutiva) {
        Integer oldQtdDevolutiva = this.qtdDevolutiva;
        this.qtdDevolutiva = qtdDevolutiva;
        changeSupport.firePropertyChange("qtdDevolutiva", oldQtdDevolutiva, qtdDevolutiva);
    }

    public Integer getQtdDevolutivaMe80fn() {
        return qtdDevolutivaMe80fn;
    }

    public void setQtdDevolutivaMe80fn(Integer qtdDevolutivaMe80fn) {
        Integer oldQtdDevolutivaMe80fn = this.qtdDevolutivaMe80fn;
        this.qtdDevolutivaMe80fn = qtdDevolutivaMe80fn;
        changeSupport.firePropertyChange("qtdDevolutivaMe80fn", oldQtdDevolutivaMe80fn, qtdDevolutivaMe80fn);
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
        if (!(object instanceof Analisedevolutiva)) {
            return false;
        }
        Analisedevolutiva other = (Analisedevolutiva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Analisedevolutiva[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
