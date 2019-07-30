package objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StrokaOtcheta {

    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty mounth = new SimpleStringProperty("");
    private SimpleStringProperty financy = new SimpleStringProperty("");
    private SimpleStringProperty dolgi = new SimpleStringProperty("");
    private SimpleStringProperty mikkiOtch = new SimpleStringProperty("");
    private SimpleStringProperty mikSchetchik = new SimpleStringProperty("");
    private SimpleStringProperty donaldOtch = new SimpleStringProperty("");
    private SimpleStringProperty donSchetchik = new SimpleStringProperty("");

    public StrokaOtcheta() {
    }

    public StrokaOtcheta(int id, String mounth, String financy, String dolgi, String mikkiOtch, String mikSchetchik,
                         String donaldOtch, String donSchetchik) {
        this.id = new SimpleIntegerProperty(id);
        this.mounth = new SimpleStringProperty(mounth);
        this.financy = new SimpleStringProperty(financy);
        this.dolgi = new SimpleStringProperty(dolgi);
        this.mikkiOtch = new SimpleStringProperty(mikkiOtch);
        this.mikSchetchik = new SimpleStringProperty(mikSchetchik);
        this.donaldOtch = new SimpleStringProperty(donaldOtch);
        this.donSchetchik = new SimpleStringProperty(donSchetchik);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }



    public String getMounth() {
        return mounth.get();
    }

    public SimpleStringProperty mounthProperty() {
        return mounth;
    }

    public void setMounth(String mounth) {
        this.mounth.set(mounth);
    }

    public String getFinancy() {
        return financy.get();
    }

    public SimpleStringProperty financyProperty() {
        return financy;
    }

    public void setFinancy(String financy) {
        this.financy.set(financy);
    }

    public String getDolgi() {
        return dolgi.get();
    }

    public SimpleStringProperty dolgiProperty() {
        return dolgi;
    }

    public void setDolgi(String dolgi) {
        this.dolgi.set(dolgi);
    }

    public String getMikkiOtch() {
        return mikkiOtch.get();
    }

    public SimpleStringProperty mikkiOtchProperty() {
        return mikkiOtch;
    }

    public void setMikkiOtch(String mikkiOtch) {
        this.mikkiOtch.set(mikkiOtch);
    }

    public String getMikSchetchik() {
        return mikSchetchik.get();
    }

    public SimpleStringProperty mikSchetchikProperty() {
        return mikSchetchik;
    }

    public void setMikSchetchik(String mikSchetchik) {
        this.mikSchetchik.set(mikSchetchik);
    }

    public String getDonaldOtch() {
        return donaldOtch.get();
    }

    public SimpleStringProperty donaldOtchProperty() {
        return donaldOtch;
    }

    public void setDonaldOtch(String donaldOtch) {
        this.donaldOtch.set(donaldOtch);
    }

    public String getDonSchetchik() {
        return donSchetchik.get();
    }

    public SimpleStringProperty donSchetchikProperty() {
        return donSchetchik;
    }

    public void setDonSchetchik(String donSchetchik) {
        this.donSchetchik.set(donSchetchik);
    }
}
