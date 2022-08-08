package bike.rapido.paathshala;

public class Car {
    private String carNumber  ;
    private Boolean isParked = false;

    Boolean checkIfCarIsParked()
    {
        return this.isParked == true ;
    }
    void setAsParked(){
        this.isParked = true;
    }

    void setAsUnParked(){
        this.isParked = false ;
    }
    public Car(String number){
        this.carNumber = number ;
    }

}


