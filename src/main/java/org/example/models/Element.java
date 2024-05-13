package org.example.models;

public enum Element {

    FRAME("Mark4"),
    MOTORS("eMax"),
    STACK("speedybee"),
    VTX("max_solo"),
    VIDEO_ANTENNA("rush"),
    CAMERA("caddex"),
    RECEIVER("erls");
    private String logo;

    private Element(String logo){
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
