package ebrithilapi.rest;

import com.google.common.collect.ImmutableSet;
import restx.factory.*;
import ebrithilapi.rest.HelloResource;

@Machine
public class HelloResourceFactoryMachine extends SingleNameFactoryMachine<ebrithilapi.rest.HelloResource> {
    public static final Name<ebrithilapi.rest.HelloResource> NAME = Name.of(ebrithilapi.rest.HelloResource.class, "HelloResource");

    public HelloResourceFactoryMachine() {
        super(0, new StdMachineEngine<ebrithilapi.rest.HelloResource>(NAME, 0, BoundlessComponentBox.FACTORY) {


            @Override
            public BillOfMaterials getBillOfMaterial() {
                return new BillOfMaterials(ImmutableSet.<Factory.Query<?>>of(

                ));
            }

            @Override
            protected ebrithilapi.rest.HelloResource doNewComponent(SatisfiedBOM satisfiedBOM) {
                return new HelloResource(

                );
            }
        });
    }

}
