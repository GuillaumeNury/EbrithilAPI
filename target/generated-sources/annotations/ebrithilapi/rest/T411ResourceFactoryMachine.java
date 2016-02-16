package ebrithilapi.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import ebrithilapi.rest.T411Resource;

@Machine
public class T411ResourceFactoryMachine extends SingleNameFactoryMachine<ebrithilapi.rest.T411Resource> {
    public static final Name<ebrithilapi.rest.T411Resource> NAME = Name.of(ebrithilapi.rest.T411Resource.class, "T411Resource");

    public T411ResourceFactoryMachine() {
        super(0, new StdMachineEngine<ebrithilapi.rest.T411Resource>(NAME, 0, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected ebrithilapi.rest.T411Resource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new T411Resource(

                );
            }
        });
    }

}
