package com.articlefetch.app.Busniess.DTO;

import com.articlefetch.app.Controller.JacksonModels.JacksonConversion;
import com.articlefetch.app.DataAccess.ModelDomain.EntityConvert;

public interface DTO<E> {

    public JacksonConversion convertToJackson(DTO d);

    public E convertToDTO();


}
