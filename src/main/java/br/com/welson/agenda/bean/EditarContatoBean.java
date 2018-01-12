package br.com.welson.agenda.bean;

import br.com.welson.agenda.model.Contato;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@ViewScoped
public class EditarContatoBean implements Serializable {

    private Contato contato;

    public void init() throws IOException {
        contato = (Contato) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("contato");
        if(contato == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            return;
        }
    }

    public String alterar() {
        try {
            contato.alterar();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("contato");
            return "index?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
