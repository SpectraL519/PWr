package eu.jpereira.trainings.designpatterns.creational.prototype;

import static org.junit.Assert.*;

import java.beans.Transient;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.creational.prototype.exception.CannotHaveZeroPartsException;
import eu.jpereira.trainings.designpatterns.creational.prototype.exception.CouldNotCloneLastObjectException;
import eu.jpereira.trainings.designpatterns.creational.prototype.exception.NeedToPackLastVehicleException;
import eu.jpereira.trainings.designpatterns.creational.prototype.exception.VehicleDoesNotHavePartsException;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.Shell;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.Tire;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.Vehicle;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.VehiclePartEnumeration;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.VehiclePartPropertiesEnumeration;
import eu.jpereira.trainings.designpatterns.creational.prototype.model.Window;



public class VehicleCloneTest {
    
    @Test
    public void testCloneVehicle ()  throws CloneNotSupportedException,
                                            NeedToPackLastVehicleException,
                                            CouldNotCloneLastObjectException,
                                            CouldNotCloneLastObjectException,
                                            CannotHaveZeroPartsException {
                                                
        // Vehicle creating as in ClientTest
        Client client = new Client();
		
		//create a bus car
		//Create props for tire
		Properties tiresProps = new Properties();
		tiresProps.put(VehiclePartPropertiesEnumeration.SIZE,10);

		//Create props for shell
		Properties shellProps = new Properties();
		shellProps.put(VehiclePartPropertiesEnumeration.COLOR,"blue");

		
		Properties windowProps = new Properties();
		windowProps.put(VehiclePartPropertiesEnumeration.WIDTH,20);
		windowProps.put(VehiclePartPropertiesEnumeration.WIDTH,20);

		//client.createVehicle().with(new Tires()).times(4).
		Vehicle vehicle = client.vehicleBuilder().createVehicle().with(new Tire(tiresProps)).times(3).with(new Window(windowProps)).times(8).with(new Shell(shellProps)).times(1).packIt();
		
		Vehicle vehicle_clone = vehicle.clone();
        assert(vehicle != vehicle_clone) : "The objects are copies...not clones!";
        assertEquals(vehicle, vehicle_clone);
    }
}
