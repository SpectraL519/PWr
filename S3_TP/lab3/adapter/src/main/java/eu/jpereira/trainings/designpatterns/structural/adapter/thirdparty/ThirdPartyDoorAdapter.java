package eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty;

import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.CodeMismatchException;
import eu.jpereira.trainings.designpatterns.structural.adapter.exceptions.IncorrectDoorCodeException;
import eu.jpereira.trainings.designpatterns.structural.adapter.model.Door;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeCodeForUnlockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotChangeStateOfLockedDoor;
import eu.jpereira.trainings.designpatterns.structural.adapter.thirdparty.exceptions.CannotUnlockDoorException;



public class ThirdPartyDoorAdapter extends ThirdPartyDoor implements Door {
    
    public ThirdPartyDoorAdapter () {} // empty constructor

    @Override
    public void open (String code) throws IncorrectDoorCodeException {
        try {
            unlock(code);
            setState(DoorState.OPEN);
        }
        catch (CannotUnlockDoorException e) {
            throw new IncorrectDoorCodeException();
        }
        catch (CannotChangeStateOfLockedDoor e) {}
    }

    @Override
    public void close() {
        lock();
    }

    @Override
    public boolean isOpen() {
        LockStatus lockStatus = getLockStatus();
        return !lockStatus.equals(LockStatus.LOCKED);
    }

    @Override
    public void changeCode (String oldCode, String newCode, String newCodeConfirmation) throws IncorrectDoorCodeException, CodeMismatchException {
        if(newCode.equals(newCodeConfirmation)) {
            try {
                setNewLockCode(newCode);
            } 
            catch (CannotChangeCodeForUnlockedDoor e1) {
                try {
                    unlock(oldCode);
                    setNewLockCode(newCode);
                } 
                catch (CannotUnlockDoorException e2) {
                    throw new IncorrectDoorCodeException();
                } 
                catch (CannotChangeCodeForUnlockedDoor e2) {}

            }
        }
        else{
            throw new CodeMismatchException();
        }
    }

    @Override
    public boolean testCode (String code) {
        try{
            unlock(code);
            lock();
            return true;
        }
        catch (CannotUnlockDoorException e) {
            return false;
        }
    }
}
