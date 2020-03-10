package com.articlefetch.app.Busniess.DTO;

import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.Category;
import com.articlefetch.app.Controller.JacksonModels.JacksonObject;
import com.articlefetch.app.DataAccess.ModelDomain.Entity;

public class DTOFactory {

    public DTO createDTO(JacksonObject obj) {
       if(obj instanceof Account) {
           return new AccountDTO(obj);

       } else if(obj instanceof Category) {
           return new CategoryDTO(obj);
       }
    }

    public DTO createDTO(Entity obj) {
        if(obj instanceof Account) {
            return new AccountDTO(obj);

        } else if(obj instanceof Category) {
            return new CategoryDTO(obj);
        }
    }
}
