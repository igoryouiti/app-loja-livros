package br.com.isato.applojalivros.projection;

import br.com.isato.applojalivros.model.Book;

public interface CategoryProjection {
    Long getId();

    String getDescription();

    Long getBookId();
}
