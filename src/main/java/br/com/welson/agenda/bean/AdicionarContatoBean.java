package br.com.welson.agenda.bean;

import br.com.welson.agenda.model.Contato;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@RequestScoped
public class AdicionarContatoBean implements Serializable {

    private Contato contato = new Contato();

    public void adicionar() {
        try {
            contato.adicionar();
            contato = new Contato();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Contato adicionado com sucesso!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
