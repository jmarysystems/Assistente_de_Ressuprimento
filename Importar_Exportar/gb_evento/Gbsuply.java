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
@Table(name = "GBSUPLY", catalog = "", schema = "SCHEMAJMARY")
@NamedQueries({
    @NamedQuery(name = "Gbsuply.findAll", query = "SELECT g FROM Gbsuply g"),
    @NamedQuery(name = "Gbsuply.findById", query = "SELECT g FROM Gbsuply g WHERE g.id = :id"),
    @NamedQuery(name = "Gbsuply.findByEstabelecimento", query = "SELECT g FROM Gbsuply g WHERE g.estabelecimento = :estabelecimento"),
    @NamedQuery(name = "Gbsuply.findByMaterial", query = "SELECT g FROM Gbsuply g WHERE g.material = :material"),
    @NamedQuery(name = "Gbsuply.findByNomArea", query = "SELECT g FROM Gbsuply g WHERE g.nomArea = :nomArea"),
    @NamedQuery(name = "Gbsuply.findByNomSetor", query = "SELECT g FROM Gbsuply g WHERE g.nomSetor = :nomSetor"),
    @NamedQuery(name = "Gbsuply.findByNomSecao", query = "SELECT g FROM Gbsuply g WHERE g.nomSecao = :nomSecao"),
    @NamedQuery(name = "Gbsuply.findByNomGrupo", query = "SELECT g FROM Gbsuply g WHERE g.nomGrupo = :nomGrupo"),
    @NamedQuery(name = "Gbsuply.findByNomSubgrupo", query = "SELECT g FROM Gbsuply g WHERE g.nomSubgrupo = :nomSubgrupo"),
    @NamedQuery(name = "Gbsuply.findByNomFornecedorgrupoeconomico", query = "SELECT g FROM Gbsuply g WHERE g.nomFornecedorgrupoeconomico = :nomFornecedorgrupoeconomico"),
    @NamedQuery(name = "Gbsuply.findByNomClasse2", query = "SELECT g FROM Gbsuply g WHERE g.nomClasse2 = :nomClasse2"),
    @NamedQuery(name = "Gbsuply.findByCodFornecedorlof", query = "SELECT g FROM Gbsuply g WHERE g.codFornecedorlof = :codFornecedorlof"),
    @NamedQuery(name = "Gbsuply.findByStsFonecedortop", query = "SELECT g FROM Gbsuply g WHERE g.stsFonecedortop = :stsFonecedortop"),
    @NamedQuery(name = "Gbsuply.findByStsEstoquetipo", query = "SELECT g FROM Gbsuply g WHERE g.stsEstoquetipo = :stsEstoquetipo")})
public class Gbsuply implements Serializable {
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
    @Column(name = "NOM_AREA", length = 300)
    private String nomArea;
    @Column(name = "NOM_SETOR", length = 300)
    private String nomSetor;
    @Column(name = "NOM_SECAO", length = 300)
    private String nomSecao;
    @Column(name = "NOM_GRUPO", length = 300)
    private String nomGrupo;
    @Column(name = "NOM_SUBGRUPO", length = 300)
    private String nomSubgrupo;
    @Column(name = "NOM_FORNECEDORGRUPOECONOMICO", length = 300)
    private String nomFornecedorgrupoeconomico;
    @Column(name = "NOM_CLASSE2", length = 300)
    private String nomClasse2;
    @Column(name = "COD_FORNECEDORLOF", length = 300)
    private String codFornecedorlof;
    @Column(name = "STS_FONECEDORTOP", length = 300)
    private String stsFonecedortop;
    @Column(name = "STS_ESTOQUETIPO", length = 300)
    private String stsEstoquetipo;

    public Gbsuply() {
    }

    public Gbsuply(Integer id) {
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

    public String getNomArea() {
        return nomArea;
    }

    public void setNomArea(String nomArea) {
        String oldNomArea = this.nomArea;
        this.nomArea = nomArea;
        changeSupport.firePropertyChange("nomArea", oldNomArea, nomArea);
    }

    public String getNomSetor() {
        return nomSetor;
    }

    public void setNomSetor(String nomSetor) {
        String oldNomSetor = this.nomSetor;
        this.nomSetor = nomSetor;
        changeSupport.firePropertyChange("nomSetor", oldNomSetor, nomSetor);
    }

    public String getNomSecao() {
        return nomSecao;
    }

    public void setNomSecao(String nomSecao) {
        String oldNomSecao = this.nomSecao;
        this.nomSecao = nomSecao;
        changeSupport.firePropertyChange("nomSecao", oldNomSecao, nomSecao);
    }

    public String getNomGrupo() {
        return nomGrupo;
    }

    public void setNomGrupo(String nomGrupo) {
        String oldNomGrupo = this.nomGrupo;
        this.nomGrupo = nomGrupo;
        changeSupport.firePropertyChange("nomGrupo", oldNomGrupo, nomGrupo);
    }

    public String getNomSubgrupo() {
        return nomSubgrupo;
    }

    public void setNomSubgrupo(String nomSubgrupo) {
        String oldNomSubgrupo = this.nomSubgrupo;
        this.nomSubgrupo = nomSubgrupo;
        changeSupport.firePropertyChange("nomSubgrupo", oldNomSubgrupo, nomSubgrupo);
    }

    public String getNomFornecedorgrupoeconomico() {
        return nomFornecedorgrupoeconomico;
    }

    public void setNomFornecedorgrupoeconomico(String nomFornecedorgrupoeconomico) {
        String oldNomFornecedorgrupoeconomico = this.nomFornecedorgrupoeconomico;
        this.nomFornecedorgrupoeconomico = nomFornecedorgrupoeconomico;
        changeSupport.firePropertyChange("nomFornecedorgrupoeconomico", oldNomFornecedorgrupoeconomico, nomFornecedorgrupoeconomico);
    }

    public String getNomClasse2() {
        return nomClasse2;
    }

    public void setNomClasse2(String nomClasse2) {
        String oldNomClasse2 = this.nomClasse2;
        this.nomClasse2 = nomClasse2;
        changeSupport.firePropertyChange("nomClasse2", oldNomClasse2, nomClasse2);
    }

    public String getCodFornecedorlof() {
        return codFornecedorlof;
    }

    public void setCodFornecedorlof(String codFornecedorlof) {
        String oldCodFornecedorlof = this.codFornecedorlof;
        this.codFornecedorlof = codFornecedorlof;
        changeSupport.firePropertyChange("codFornecedorlof", oldCodFornecedorlof, codFornecedorlof);
    }

    public String getStsFonecedortop() {
        return stsFonecedortop;
    }

    public void setStsFonecedortop(String stsFonecedortop) {
        String oldStsFonecedortop = this.stsFonecedortop;
        this.stsFonecedortop = stsFonecedortop;
        changeSupport.firePropertyChange("stsFonecedortop", oldStsFonecedortop, stsFonecedortop);
    }

    public String getStsEstoquetipo() {
        return stsEstoquetipo;
    }

    public void setStsEstoquetipo(String stsEstoquetipo) {
        String oldStsEstoquetipo = this.stsEstoquetipo;
        this.stsEstoquetipo = stsEstoquetipo;
        changeSupport.firePropertyChange("stsEstoquetipo", oldStsEstoquetipo, stsEstoquetipo);
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
        if (!(object instanceof Gbsuply)) {
            return false;
        }
        Gbsuply other = (Gbsuply) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gb_evento.Gbsuply[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
