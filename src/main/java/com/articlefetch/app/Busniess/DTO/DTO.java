package com.articlefetch.app.Busniess.DTO;

import com.articlefetch.app.Controller.JacksonModels.JacksonObject;
import com.articlefetch.app.DataAccess.ModelDomain.Entity;

public interface DTO {
    public JacksonObject convertToJackson();

    public Entity convertToModel();
}
