package base.core.dto.response;


import base.core.convertor.Serializable;

public class BookingCancelDTO extends Serializable {

    private boolean successful;

    public boolean isSuccess() {
        return successful;
    }
}
