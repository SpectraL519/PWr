package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;




public class ThirdPartyDoorObjectAdapter implements Door {

    public ThirdPartyDoor thirdPartyDoor;

    public ThirdPartyDoorObjectAdapter() {
        this.thirdPartyDoor = new ThirdPartyDoor();
    }

    @Override
    public void open (String code) throws IncorrectDoorCodeException {
        try {
            thirdPartyDoor.unlock(code);
            thirdPartyDoor.setState(ThirdPartyDoor.DoorState.OPEN);
        } 
        catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        } 
        catch (CannotChangeStateOfLockedDoor e) {}

    }

    @Override
    public void close() {
        try {
            thirdPartyDoor.setState(ThirdPartyDoor.DoorState.CLOSED);
        } 
        catch (CannotChangeStateOfLockedDoor e) {
            throw new RuntimeException(e);
        }
        thirdPartyDoor.lock();
    }

    @Override
    public boolean isOpen() {
        return thirdPartyDoor.getState().equals(ThirdPartyDoor.DoorState.OPEN);
    }

    @Override
    public void changeCode (String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException{
        if(newCode.equals(newCodeConfirmation)){
            if(thirdPartyDoor.getState().equals(ThirdPartyDoor.DoorState.CLOSED)){
                open(oldCode);
            }
            try {
                thirdPartyDoor.setNewLockCode(newCode);
            } 
            catch (CannotChangeCodeForUnlockedDoor e) {}
        }
        else {throw new CodeMismatchException();}
    }

    @Override
    public boolean testCode (String code) {
        try {
            open(code);
            close();
            return true;
        }
        catch (IncorrectDoorCodeException e){
            return false;
        }
    }
}
