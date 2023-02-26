package eu.jpereira.trainings.designpatterns.structural.bridge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import eu.jpereira.trainings.designpatterns.structural.bridge.protocol.Protocol;
import eu.jpereira.trainings.designpatterns.structural.bridge.protocol.ProtocolSelector;
import eu.jpereira.trainings.designpatterns.structural.bridge.protocol.ExtendedProtocolSelector;



public class ExtendedProtocolSelectorTest extends ProtocolSelectorTest {

    @Override
    protected ProtocolSelector createProtocolSelector () {
        return new ExtendedProtocolSelector();
    }

    @Override
    protected int getProtocolCount () {
        return 4;
    }
}
