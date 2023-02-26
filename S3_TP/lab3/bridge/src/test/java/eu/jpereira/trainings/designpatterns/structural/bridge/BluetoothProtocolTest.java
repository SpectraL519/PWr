package eu.jpereira.trainings.designpatterns.structural.bridge;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.bridge.protocol.Protocol;
import eu.jpereira.trainings.designpatterns.structural.bridge.protocol.BluetoothProtocol;

import static org.junit.Assert.*;



public class BluetoothProtocolTest extends ProtocolTest {

    @Override
    protected Protocol createProtocolUnderTest () {
        return new BluetoothProtocol();
    }
}
