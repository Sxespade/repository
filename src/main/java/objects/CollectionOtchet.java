package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.StrokaOtcheta;

public class CollectionOtchet {


    public ObservableList <StrokaOtcheta> strokiOtchetaList = FXCollections.observableArrayList();

    public void add(StrokaOtcheta strokaOtcheta) {strokiOtchetaList.add(strokaOtcheta);}

    public ObservableList <StrokaOtcheta> getstrokiOtchetaList() {
        return strokiOtchetaList;
    }


/*    public void addDataLine( String m1, String m2, String m3, String m4, String m5, String m6, String m7) {
        strokiOtchetaList.add(new objects.StrokaOtcheta(m1,m2,m3,m4,m5,m6,m7));
    }*/


}
