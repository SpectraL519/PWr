package eu.jpereira.trainings.designpatterns.structural.bridge.protocol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;



public class ExtendedProtocolSelector extends ProtocolSelector {
    public ExtendedProtocolSelector () {}

    @Override
    protected Collection<Protocol> createAditionalProtocols() {
        ArrayList<Protocol> btp = new ArrayList<Protocol> ();
        btp.add(new BluetoothProtocol ());
		return btp;
	}
}
