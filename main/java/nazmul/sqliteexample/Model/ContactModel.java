package nazmul.sqliteexample.Model;

/**
 * Created by BITM Trainer - 401 on 7/18/2016.
 */
public class ContactModel {
    private int id;
    private String name;
    private String phoneNumber;

    public ContactModel(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public ContactModel(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public ContactModel() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    @Override
    public String toString() {
        return name;
    }
}
