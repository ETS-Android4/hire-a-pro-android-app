package hireapro.himanshu.hireapro.dataclass;

import android.graphics.Bitmap;

import java.io.Serializable;


public class ProfessionalCategory implements Serializable {
    private Bitmap image;
    private String proType;

    public ProfessionalCategory()
    {
super();
    }

    public ProfessionalCategory(Bitmap imageID, String proType)
    {
        this.image = image;
        this.proType = proType;
    }


    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
