package com.pizzaria.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;

@MappedSuperclass
abstract class Versao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Versao)) return false;

        Versao versao = (Versao) o;

        return getVersion().equals(versao.getVersion());
    }

    @Override
    public int hashCode() {
        return getVersion().hashCode();
    }
}
