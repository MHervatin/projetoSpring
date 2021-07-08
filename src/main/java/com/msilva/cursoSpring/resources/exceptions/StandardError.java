package com.msilva.cursoSpring.resources.exceptions;

import java.io.Serializable;

/**
 *
 * @author Mateus
 */
public class StandardError implements Serializable{
    private Integer statusHttp;
    private String mensagem;
    private Long timeStamp;

    public StandardError(Integer statusHttp, String mensagem, Long timeStamp) {
        this.statusHttp = statusHttp;
        this.mensagem = mensagem;
        this.timeStamp = timeStamp;
    }

    public Integer getStatusHttp() {
        return statusHttp;
    }

    public void setStatusHttp(Integer statusHttp) {
        this.statusHttp = statusHttp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
