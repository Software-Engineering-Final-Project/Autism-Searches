package com.articlefetch.app.Busniess.Service;

public interface Conversion<D, J> {
    D convertToDAO(J obj);
    J convertToJackson(D obj);
}
