package application.model;

public class Maltbatch {
    private String kornsort;
    private String mark;
    private String gård;
    private String dyrketAf;
    private boolean økologisk;

    public Maltbatch(String kornsort, String mark, String gård, String dyrketAf, boolean økologisk) {
        this.kornsort = kornsort;
        this.mark = mark;
        this.gård = gård;
        this.dyrketAf = dyrketAf;
        this.økologisk = økologisk;
    }

    public String getKornsort() {
        return kornsort;
    }

    public String getMark() {
        return mark;
    }

    public String getGård() {
        return gård;
    }

    public String getDyrketAf() {
        return dyrketAf;
    }

    public boolean isØkologisk() {
        return økologisk;
    }
}
