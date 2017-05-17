package hireapro.himanshu.hireapro;

/**
 * Created by root on 17/5/17.
 */

public class ProfessionalType {
    private int imageID;
    private String proType;

    public ProfessionalType()
    {

    }

    public ProfessionalType(int imageID,String proType)
    {
        this.imageID = imageID;
        this.proType = proType;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }
}
