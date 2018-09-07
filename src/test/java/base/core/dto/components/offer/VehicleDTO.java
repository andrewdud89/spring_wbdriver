package base.core.dto.components.offer;


import base.core.convertor.Serializable;

import java.util.List;

public class VehicleDTO extends Serializable {
    private String imageUrl;

    private String category;

    private String customCategory;

    private String type;

    private String transmissionType;

    private String fuel;

    private boolean airConditioning;

    private int rearSeats;

    private int frontSeats;

    private List<MultimediaDTO> multimedia;

    private String modelName;

    private int passengersCapacity;

    private String largeBag;

    private String smallBag;

    private int numberOfDoors;

    private String transmissionDrive;

    private String vehicleTypeOwner;

    private String vehicleRentalPrefType;

    private String acrisCode; //todo wait for fix AC-1262

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public String getTransmissionDrive() {
        return transmissionDrive;
    }

    public String getVehicleTypeOwner() {
        return vehicleTypeOwner;
    }

    public String getAcrisCode() {//todo wait for fix AC-1262
        return acrisCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public String getCustomCategory() {
        return customCategory;
    }

    public String getType() {
        return type;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public String getFuel() {
        return fuel;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public int getRearSeats() {
        return rearSeats;
    }

    public int getFrontSeats() {
        return frontSeats;
    }

    public List<MultimediaDTO> getMultimedia() {
        return multimedia;
    }

    public String getModelName() {
        return modelName;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public String getLargeBag() {
        return largeBag;
    }

    public String getVehicleRentalPrefType() {
        return vehicleRentalPrefType;
    }

    public String getSmallBag() {
        return smallBag;
    }
}
