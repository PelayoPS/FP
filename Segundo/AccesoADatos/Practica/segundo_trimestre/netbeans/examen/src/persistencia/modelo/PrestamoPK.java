/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author pelay
 */
@Embeddable
public class PrestamoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "nRegistro")
    private int nRegistro;
    @Basic(optional = false)
    @Column(name = "nExpediente")
    private int nExpediente;

    public PrestamoPK() {
    }

    public PrestamoPK(int nRegistro, int nExpediente) {
        this.nRegistro = nRegistro;
        this.nExpediente = nExpediente;
    }

    public int getNRegistro() {
        return nRegistro;
    }

    public void setNRegistro(int nRegistro) {
        this.nRegistro = nRegistro;
    }

    public int getNExpediente() {
        return nExpediente;
    }

    public void setNExpediente(int nExpediente) {
        this.nExpediente = nExpediente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nRegistro;
        hash += (int) nExpediente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrestamoPK)) {
            return false;
        }
        PrestamoPK other = (PrestamoPK) object;
        if (this.nRegistro != other.nRegistro) {
            return false;
        }
        if (this.nExpediente != other.nExpediente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.modelo.PrestamoPK[ nRegistro=" + nRegistro + ", nExpediente=" + nExpediente + " ]";
    }
    
}
