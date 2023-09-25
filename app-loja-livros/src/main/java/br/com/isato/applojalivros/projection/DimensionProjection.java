package br.com.isato.applojalivros.projection;

public interface DimensionProjection {

    Long getId();
    Double getHeight();
    Double getWidth();
    Double getDepth();
    Double getWeight();
    Long getBookId();
}
