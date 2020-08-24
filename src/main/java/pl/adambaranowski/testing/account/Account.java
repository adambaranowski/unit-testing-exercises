package pl.adambaranowski.testing.account;

public class Account {
    private boolean active;
    private Address defaultDeliveryAddress;

    public Account() {
        this.active = false;
    }

    public void activte(){
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public Account(Address defaultDeliveryAddress) {
        this.defaultDeliveryAddress = defaultDeliveryAddress;
        if(getDefaultDeliveryAddress() != null){
            activte();
        }else {
           active = false;

        }
    }

    public Address getDefaultDeliveryAddress() {
        return defaultDeliveryAddress;
    }
}
